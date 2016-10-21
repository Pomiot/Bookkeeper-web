package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.exceptions.UnauthorizedModificationException;
import com.pomiot.bookkeeper.model.Book;
import com.pomiot.bookkeeper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Book.class)
public class BookEventHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @HandleBeforeCreate
    public void addOwnerToCreatedBook(Book book){
        User owner = getCurrentUser();
        book.setOwner(owner);
    }

    @HandleBeforeSave
    @HandleBeforeDelete
    public void verifyIfBookBelongsToCurrentUser(Book book){
        User currentUser = getCurrentUser();
        Book bookToModify = bookRepository.findOne(book.getId());
        if(!bookToModify.getOwner().equals(currentUser)){
            throw new UnauthorizedModificationException();
        }
    }


    private User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }
}

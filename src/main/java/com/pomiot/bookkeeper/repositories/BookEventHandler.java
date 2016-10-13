package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.Book;
import com.pomiot.bookkeeper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Book.class)
public class BookEventHandler {

    @Autowired
    private UserRepository userRepository;

    @HandleBeforeCreate
    public void handleBookCreate(Book book){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userRepository.findByUsername(username);

        book.setOwner(owner);
    }
}

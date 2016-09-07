package com.pomiot.bookkeeper.services;

import com.pomiot.bookkeeper.model.Book;
import com.pomiot.bookkeeper.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findOne(id);
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.delete(id);
    }
}

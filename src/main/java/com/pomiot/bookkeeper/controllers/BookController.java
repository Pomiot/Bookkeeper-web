package com.pomiot.bookkeeper.controllers;

import com.pomiot.bookkeeper.model.Book;
import com.pomiot.bookkeeper.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(path = "/book", method = RequestMethod.GET)
    public Iterable<Book> listAllBooks(){
        return bookService.getAllBooks();
    }

    @RequestMapping(path = "/book/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @RequestMapping(path = "/book/", method = RequestMethod.POST)
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @RequestMapping(path = "/book/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

}

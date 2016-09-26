package com.pomiot.bookkeeper.controllers;

import com.pomiot.bookkeeper.model.Book;
import com.pomiot.bookkeeper.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(path = "/book", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Book>> listAllBooks(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @RequestMapping(path = "/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable long id){
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @RequestMapping(path = "/book/", method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return ResponseEntity.created(URI.create("")).body(bookService.addBook(book));
    }

    @RequestMapping(path = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity.HeadersBuilder<?> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent();
    }

    @RequestMapping(path = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book){
        return ResponseEntity.ok().body(bookService.updateBook(id, book));
    }
}

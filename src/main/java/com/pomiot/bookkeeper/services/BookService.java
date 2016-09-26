package com.pomiot.bookkeeper.services;

import com.pomiot.bookkeeper.model.Book;
import com.pomiot.bookkeeper.repositories.AuthorRepository;
import com.pomiot.bookkeeper.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findOne(id);
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.delete(id);
    }

    public Book updateBook(Long id, Book book) {
        Book bookFromRepository = bookRepository.findOne(id);
        bookFromRepository.setTitle(book.getTitle());
        bookFromRepository.setAuthor(book.getAuthor());
        bookFromRepository.setDescription(book.getDescription());

        //this is potentially extremely bad
        authorRepository.save(book.getAuthor());

        return bookRepository.save(bookFromRepository);
    }
}

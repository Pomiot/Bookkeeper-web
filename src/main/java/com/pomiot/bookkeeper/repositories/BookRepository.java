package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}

package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {


}

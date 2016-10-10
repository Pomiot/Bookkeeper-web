package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book,Long> {

    @Override
    @Query("select b from Book b where b.owner.username = ?#{ principal?.username }")
    Iterable<Book> findAll();

    List<Book> findBookByTitleLikeIgnoreCase(@Param("title") String title);

    List<Book> findBookByAuthorLikeIgnoreCase(@Param("author") String author);
}

package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


@RepositoryRestResource
@PreAuthorize("hasRole('USER')")
public interface BookRepository extends CrudRepository<Book,Long> {

    @Override
    @PostFilter("filterObject.owner.username == authentication.name")
    Iterable<Book> findAll();

    @PostFilter("filterObject.owner.username == authentication.name")
    Iterable<Book> findBookByTitleLikeIgnoreCase(@Param("title") String title);

    @PostFilter("filterObject.owner.username == authentication.name")
    Iterable<Book> findBookByAuthorLikeIgnoreCase(@Param("author") String author);
}

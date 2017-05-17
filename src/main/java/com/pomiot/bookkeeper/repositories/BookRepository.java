package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    List<Book> findBookByTitleLikeIgnoreCase(@Param("title") String title);

    List<Book> findBookByAuthorLikeIgnoreCase(@Param("author") String author);
}

package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface UserRepository extends CrudRepository<User, String>{

    User findByUsername(String username);
}

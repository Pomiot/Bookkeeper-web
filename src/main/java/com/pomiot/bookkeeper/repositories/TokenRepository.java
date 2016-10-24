package com.pomiot.bookkeeper.repositories;

import com.pomiot.bookkeeper.model.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<VerificationToken, Long> {

    VerificationToken getByToken(String token);
}

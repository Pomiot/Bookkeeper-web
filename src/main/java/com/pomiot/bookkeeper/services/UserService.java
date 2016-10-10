package com.pomiot.bookkeeper.services;

import com.pomiot.bookkeeper.model.User;

public interface UserService {
    User getUserByUsername(String username);
}

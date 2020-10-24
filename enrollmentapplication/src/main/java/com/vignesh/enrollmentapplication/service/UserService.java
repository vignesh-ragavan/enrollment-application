package com.vignesh.enrollmentapplication.service;

import com.vignesh.enrollmentapplication.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUserName(String userName);

    List<String> findUsers(List<Long> idList);
}

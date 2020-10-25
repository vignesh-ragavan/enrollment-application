package com.vignesh.enrollmentapplication.service;

import com.vignesh.enrollmentapplication.model.User;
import com.vignesh.enrollmentapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl  implements  UserService{

    @Autowired
    UserRepository userRepository;



    @Override
    public  User saveUser(User user) {
       return userRepository.save(user);
    }
    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<String> findUsers(List<Long> idList) {
        return userRepository.findUserNames(idList);
    }



}

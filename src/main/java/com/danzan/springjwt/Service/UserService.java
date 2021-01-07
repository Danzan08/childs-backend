package com.danzan.springjwt.Service;

import com.danzan.springjwt.models.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);
    List<User> findAllUser();

    User saveUser(User user);

    void deleteById(Long id);
}

package com.danzan.springjwt.Childs.Service;

import com.danzan.springjwt.Childs.models.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);
    List<User> findAllUser();

    User saveUser(User user);

    void deleteById(Long id);
}

package com.danzan.springjwt.Service;

import com.danzan.springjwt.models.User;
import com.danzan.springjwt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserServiceImpl implements UserService  {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // выдать только определенную карту
    @Override
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }
    // выдать все карты
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    // сохранение карты
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // удаление карты
    @Override
    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}

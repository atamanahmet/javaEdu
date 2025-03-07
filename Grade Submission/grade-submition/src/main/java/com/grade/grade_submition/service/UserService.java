package com.grade.grade_submition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.User;
import com.grade.grade_submition.exceptions.ContentNotFoundException;
import com.grade.grade_submition.exceptions.UserAlreadyExistException;
import com.grade.grade_submition.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("User already exist.");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUserById(Long id) throws Exception {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
        else
            throw new ContentNotFoundException("User", id);
    }

    public User findUserByUserName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unWrapUser(user, username);

    }

    public boolean isExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    static User unWrapUser(Optional<User> user, String username) {
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ContentNotFoundException("Username doesn't exist ", 404L);

        }
    }

}
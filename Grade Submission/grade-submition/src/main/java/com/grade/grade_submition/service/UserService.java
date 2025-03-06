package com.grade.grade_submition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.User;
import com.grade.grade_submition.exceptions.ContentNotFoundException;
import com.grade.grade_submition.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User user) {
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

    public Optional<User> findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

}

// @Override
// public long count() {
// return 0;
// }

// @Override
// public void delete(User entity) {

// }

// @Override
// public void deleteAll() {

// }

// @Override
// public void deleteAll(Iterable<? extends User> entities) {

// }

// @Override
// public void deleteAllById(Iterable<? extends Long> ids) {

// }

// @Override
// public void deleteById(Long id) {

// }

// @Override
// public boolean existsById(Long id) {
// return false;
// }

// @Override
// public Iterable<User> findAll() {
// return null;
// }

// @Override
// public Iterable<User> findAllById(Iterable<Long> ids) {
// return null;
// }

// @Override
// public Optional<User> findById(Long id) {
// return Optional.empty();
// }

// @Override
// public <S extends User> S save(S entity) {
// return null;
// }

// @Override
// public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
// return null;
// }

package com.grade.grade_submition.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.User;
import com.grade.grade_submition.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUserById(Long id) {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
    }

    public Optional<User> findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean isExistsById(Long id) {
        return userRepository.existsById(id);
    }

}

// @Override
// public long count() {
// // TODO Auto-generated method stub
// return 0;
// }

// @Override
// public void delete(User entity) {
// // TODO Auto-generated method stub

// }

// @Override
// public void deleteAll() {
// // TODO Auto-generated method stub

// }

// @Override
// public void deleteAll(Iterable<? extends User> entities) {
// // TODO Auto-generated method stub

// }

// @Override
// public void deleteAllById(Iterable<? extends Long> ids) {
// // TODO Auto-generated method stub

// }

// @Override
// public void deleteById(Long id) {
// // TODO Auto-generated method stub

// }

// @Override
// public boolean existsById(Long id) {
// // TODO Auto-generated method stub
// return false;
// }

// @Override
// public Iterable<User> findAll() {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public Iterable<User> findAllById(Iterable<Long> ids) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public Optional<User> findById(Long id) {
// // TODO Auto-generated method stub
// return Optional.empty();
// }

// @Override
// public <S extends User> S save(S entity) {
// // TODO Auto-generated method stub
// return null;
// }

// @Override
// public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
// // TODO Auto-generated method stub
// return null;
// }

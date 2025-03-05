package com.grade.grade_submition.repository;

import org.springframework.data.repository.CrudRepository;

import com.grade.grade_submition.domain.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String userName);

}

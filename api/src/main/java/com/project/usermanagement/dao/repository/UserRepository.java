package com.project.usermanagement.dao.repository;

import com.project.usermanagement.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByIsEnabled(Integer isEnabled);
    Optional<User> findBySub(String sub);
}

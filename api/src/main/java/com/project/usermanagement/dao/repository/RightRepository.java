package com.project.usermanagement.dao.repository;

import com.project.usermanagement.dao.entity.Right;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RightRepository extends JpaRepository<Right,Long> {

    Right findByNameAndIsEnabled(String name,Integer isEnabled);

    List<Right> findAllByIsEnabled(Integer isEnabled);
}

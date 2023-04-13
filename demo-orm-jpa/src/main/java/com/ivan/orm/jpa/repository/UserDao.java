package com.ivan.orm.jpa.repository;

import com.ivan.orm.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    List<User> findUserByName(String name);
}

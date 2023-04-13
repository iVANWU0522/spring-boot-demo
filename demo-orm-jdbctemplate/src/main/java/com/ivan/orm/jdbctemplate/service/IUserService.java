package com.ivan.orm.jdbctemplate.service;

import com.ivan.orm.jdbctemplate.entity.User;

import java.util.List;

public interface IUserService {
    Boolean save(User user);
    Boolean delete(Long id);
    Boolean update(User user, Long id);
    User getUser(Long id);
    List<User> getUser(User user);
}

package com.ivan.orm.jdbctemplate.dao;

import com.ivan.orm.jdbctemplate.dao.base.BaseDao;
import com.ivan.orm.jdbctemplate.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends BaseDao<User, Long> {

    public UserDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Integer insert(User user) {
        return super.insert(user, true);
    }

    public Integer delete(Long id) {
        return super.deleteById(id);
    }

    public Integer update(User user, Long id) {
        return super.updateById(user, id, true);
    }

    public User selectById(Long id) {
        return super.findOneById(id);
    }

    public List<User> selectUserList(User user) {
        return super.findByExample(user);
    }
}

package com.ivan.orm.jdbctemplate.entity;

import com.ivan.orm.jdbctemplate.annotation.Column;
import com.ivan.orm.jdbctemplate.annotation.Pk;
import com.ivan.orm.jdbctemplate.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "orm_user")
public class User implements Serializable {
    @Pk
    private Long id;

    private String name;

    private String password;

    private String salt;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;
}

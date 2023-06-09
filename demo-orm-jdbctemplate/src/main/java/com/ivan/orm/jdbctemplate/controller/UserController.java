package com.ivan.orm.jdbctemplate.controller;

import cn.hutool.core.lang.Dict;
import com.ivan.orm.jdbctemplate.entity.User;
import com.ivan.orm.jdbctemplate.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public Dict save(@RequestBody User user) {
        Boolean save = userService.save(user);
        return Dict.create().set("code", save ? 200 : 500).set("msg", save ? "success" : "fail").set("data", save ? user : null);
    }

    @DeleteMapping("/user/{id}")
    public Dict delete(@PathVariable Long id) {
        Boolean delete = userService.delete(id);
        return Dict.create().set("code", delete ? 200 : 500).set("msg", delete ? "success" : "fail");
    }

    @PutMapping("/user/{id}")
    public Dict update(@RequestBody User user, @PathVariable Long id) {
        Boolean update = userService.update(user, id);
        return Dict.create().set("code", update ? 200 : 500).set("msg", update ? "success" : "fail").set("data", update ? user : null);
    }

    @GetMapping("/user/{id}")
    public Dict getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return Dict.create().set("code", 200).set("msg", "success").set("data", user);
    }

    @GetMapping("/user")
    public Dict getUser(User user) {
        List<User> userList = userService.getUser(user);
        return Dict.create().set("code", 200).set("msg", "success").set("data", userList);
    }
}

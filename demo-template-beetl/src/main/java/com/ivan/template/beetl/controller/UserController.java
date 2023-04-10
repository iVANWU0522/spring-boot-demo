package com.ivan.template.beetl.controller;

import com.ivan.template.beetl.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject(user);
        modelAndView.setViewName("redirect:/");

        request.getSession().setAttribute("user", user);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() { return new ModelAndView("page/login.btl"); }
}

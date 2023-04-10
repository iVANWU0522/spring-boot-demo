package com.ivan.template.enjoy.controller;

import cn.hutool.core.util.ObjectUtil;
import com.ivan.template.enjoy.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtil.isNull(user)) {
            modelAndView.setViewName("redirect:/user/login");
        } else {
            modelAndView.setViewName("page/index");
            modelAndView.addObject(user);
        }

        return modelAndView;
    }
}

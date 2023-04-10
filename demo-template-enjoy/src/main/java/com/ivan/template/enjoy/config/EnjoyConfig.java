package com.ivan.template.enjoy.config;

import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.jfinal.template.source.ClassPathSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;

@Configuration
public class EnjoyConfig {
    @Autowired
    private ServletContext servletContext;

    @Bean(name = "jfinalViewResolver")
    public JFinalViewResolver getJFinalViewResolver() {
        JFinalViewResolver jfinalViewResolver = new JFinalViewResolver();
        jfinalViewResolver.setDevMode(true);
        // use ClassPathSourceFactory to load template from class path and jar
        jfinalViewResolver.setSourceFactory(new ClassPathSourceFactory());
        JFinalViewResolver.engine.setBaseTemplatePath("/templates/");

        jfinalViewResolver.setSessionInView(true);
        jfinalViewResolver.setSuffix(".html");
        jfinalViewResolver.setContentType("text/html;charset=UTF-8");
        jfinalViewResolver.setOrder(0);

        jfinalViewResolver.setServletContext(servletContext);

        return jfinalViewResolver;
    }
}

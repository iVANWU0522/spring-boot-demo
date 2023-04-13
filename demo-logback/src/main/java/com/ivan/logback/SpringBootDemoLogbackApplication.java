package com.ivan.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringBootDemoLogbackApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoLogbackApplication.class, args);
        int length = context.getBeanDefinitionNames().length;
        log.info("SpringBootDemoLogbackApplication start success with {} Bean!", length);
        log.error("SpringBootDemoLogbackApplication start success with {} Bean!", length);
        log.debug("SpringBootDemoLogbackApplication start success with {} Bean!", length);
        log.warn("SpringBootDemoLogbackApplication start success with {} Bean!", length);
        log.trace("SpringBootDemoLogbackApplication start success with {} Bean!", length);

        try {
            int i = 0;
            int j = 1 / i;
        } catch (Exception e) {
            log.error("[SpringBootDemoLogbackApplication]boot with error：", e);
        }
    }
}
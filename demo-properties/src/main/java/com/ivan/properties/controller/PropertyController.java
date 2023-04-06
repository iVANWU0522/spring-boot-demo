package com.ivan.properties.controller;

import cn.hutool.core.lang.Dict;
import com.ivan.properties.property.ApplicationProperty;
import com.ivan.properties.property.DeveloperProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

    @Autowired
    private ApplicationProperty applicationProperty;

    @Autowired
    private DeveloperProperty developerProperty;

    @GetMapping("/property")
    public Dict property() {
        return Dict.create()
                .set("applicationProperty", applicationProperty)
                .set("developerProperty", developerProperty);
    }
}
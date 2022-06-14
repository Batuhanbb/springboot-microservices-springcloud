package com.limitsservice.limitsservice.controller;

import com.limitsservice.limitsservice.bean.Limit;
import com.limitsservice.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
@Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public Limit retrieveAllLimits(){
        return new Limit(configuration.getMinimum(), configuration.getMaximum());
    }
}

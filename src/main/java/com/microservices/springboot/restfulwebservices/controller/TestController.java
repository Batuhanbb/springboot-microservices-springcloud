package com.microservices.springboot.restfulwebservices.controller;

import com.microservices.springboot.restfulwebservices.controller.CustomString;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping(path="/test")
    public String testMethod(){
        return "TEST";
    }

    @GetMapping(path="teststring")
    public CustomString testString(){
        return new CustomString("TESTSTRING");
    }

    @GetMapping(path="/user/{name}")
    public CustomString testo(@PathVariable String name){
        return new CustomString(String.format("HELLO %s", name));
    }
}

package com.microservices.springboot.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
    @GetMapping(value="/versions", params="version=1")
    public String versionOne(){
        return "v1";
    }
@GetMapping(value = "/versions", params = "version=2")
    public String versionTwo(){
        return "v2";
    }
}

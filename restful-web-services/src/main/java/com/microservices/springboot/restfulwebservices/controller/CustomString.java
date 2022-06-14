package com.microservices.springboot.restfulwebservices.controller;

public class CustomString {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomString(String message) {
        this.message = message;
    }
}

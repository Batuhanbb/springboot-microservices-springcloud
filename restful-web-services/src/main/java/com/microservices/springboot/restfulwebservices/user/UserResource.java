package com.microservices.springboot.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {
    @Autowired
    private UserDAO service;

    @GetMapping("/users")
    public List<User> getUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        User user =  service.findOne(id);
        if (user == null){
            throw new UserNotFoundException(id + "id not found");
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user){

        User addedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id){
        User user =  service.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException(id + "id not found");
        }
    }

}

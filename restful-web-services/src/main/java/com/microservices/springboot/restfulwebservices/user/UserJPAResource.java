package com.microservices.springboot.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("jpa/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        Optional<User> user =  userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException(id + "id not found");
        }
        EntityModel<User> model = EntityModel.of(user.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @PostMapping("jpa/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user){

        User addedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("jpa/users/{id}")
    public void deleteUserById(@PathVariable int id){
       userRepository.deleteById(id);
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id){
        Optional<User> user =  userRepository.findById(id);
        if (!user.isPresent()){
            throw new UserNotFoundException(id + "id not found");
        }

        return user.get().getPost();
    }

    @PostMapping("jpa/users/{id}/posts")
    public ResponseEntity<Object> addPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> optionalUser =  userRepository.findById(id);
        if (!optionalUser.isPresent()){
            throw new UserNotFoundException(id + "id not found");
        }

        User user = optionalUser.get();
        post.setUser(user);
        postRepository.save(post);

        User addedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}

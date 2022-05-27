package com.microservices.springboot.restfulwebservices.user;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private String post_content;
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    protected Post(){}

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post_content='" + post_content + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post(int id, String post_content, User user) {
        this.id = id;
        this.post_content = post_content;
        this.user = user;
    }
}

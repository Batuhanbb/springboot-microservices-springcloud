package com.microservices.springboot.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Entity
public class User {
@Id
    private int id;
@Size(min = 2, message = "Name must be atleast 2 characters")
    private String name;
@Past(message = "Date must be past")
    private Date date;
@OneToMany(mappedBy = "user")
private List<Post> post;
protected User(){}

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(date, user.date);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
}

package com.microservices.springboot.restfulwebservices.user;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Component
public class UserDAO {

    private static List<User> users = new ArrayList<User>();
    private int userCount = 3;

    static{
        users.add(new User(1, "Batu", new Date()));
        users.add(new User(2, "Batuke", new Date()));
        users.add(new User(3, "Batuhanke", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        users.add(user);
        return user;
    }

    public User findOne(int id){
       for(User user:users){
           if (user.getId() == id){
               return user;
           }
       }
       return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if (user.getId() == id){
                iterator.remove();
                return user;
            }

       }
        return null;
    }
}

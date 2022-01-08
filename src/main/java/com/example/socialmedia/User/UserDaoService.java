package com.example.socialmedia.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Michael Awad", new Date()));
        users.add(new User(2, "Catherine Awad", new Date()));
        users.add(new User(3, "Christine  Awad", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
    public User findById(int id){
        for (User user: users){
            if (user.getId() == id)
                return user;
        }
        return null;
    }
}

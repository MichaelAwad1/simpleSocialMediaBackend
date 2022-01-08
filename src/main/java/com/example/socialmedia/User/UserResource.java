package com.example.socialmedia.User;

import com.example.socialmedia.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable("id") int id){
        User user = userDaoService.findById(id);
        if (user == null){
            throw new UserNotFoundException("id " +id + " not found");
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        User savedUser =  userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build() ;
    }

}

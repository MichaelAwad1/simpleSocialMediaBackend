package com.example.socialmedia.User;

import com.example.socialmedia.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public EntityModel<User> getUser(@PathVariable("id") int id){
        User user = userDaoService.findById(id);
        if (user == null){
            throw new UserNotFoundException("id " +id + " not found");
        }
        EntityModel model = EntityModel.of(user);

        WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        model.add(builder.withRel("all-users"));
        return model ;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user){
        User savedUser =  userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build() ;
    }

    @DeleteMapping(path = "/users/{id}")
    public User deleteUser(@PathVariable("id") int id){
        User user = userDaoService.deleteById(id);
        if (user == null){
            throw new UserNotFoundException("id " +id + " not found");
        }
        return user;
    }
}

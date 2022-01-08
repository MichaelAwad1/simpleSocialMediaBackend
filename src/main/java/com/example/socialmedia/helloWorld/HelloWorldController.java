package com.example.socialmedia.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello/{name}")
    public HelloWorld helloWorld(@PathVariable String name){
        return new HelloWorld(name);
    }

}

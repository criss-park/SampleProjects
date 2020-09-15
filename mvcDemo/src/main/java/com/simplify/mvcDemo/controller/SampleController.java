package com.simplify.mvcDemo.controller;

import com.simplify.mvcDemo.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @GetMapping(value = "/getMapping")
    public String getMappingTest(@RequestParam(name = "simple", defaultValue = "name", required = false) String name) {
        return name;
    }

    @PostMapping(value = "/postMapping")
    public User add(@RequestBody User user){
        return new User(user.getName() + "_new");
    }

    @GetMapping(value = "/newUser/{name}")
    public User newUser(@PathVariable(name = "name", required = true) String name){
        return new User(name);
    }
}

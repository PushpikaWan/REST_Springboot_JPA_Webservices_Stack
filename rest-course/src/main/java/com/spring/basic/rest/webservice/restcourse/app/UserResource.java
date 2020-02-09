package com.spring.basic.rest.webservice.restcourse.app;

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
    public List<User> retriveAllUsers() {
        return userDaoService.getAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id) {
        User user =  userDaoService.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id - " + id);
        }

        //HATEOAS
        EntityModel<User> resource = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void DeleteUser(@PathVariable int id) {
        User user =  userDaoService.deleteById(id);
        if(user == null){
            throw new UserNotFoundException("id - " + id);
        }
    }

}

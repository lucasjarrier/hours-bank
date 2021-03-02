package com.project.hourbank.controller;

import com.project.hourbank.models.User;
import com.project.hourbank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //OK
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userService.save((user));
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //OK
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    //OK
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id){
        User idUser = userService.findById(id);
        return ResponseEntity.ok().body(idUser);
    }

    //OK
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> Delete(@PathVariable(value = "id") long id) {
        User userDeleted = userService.deleteUSerId(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //OK
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(value="id") Long id, @RequestBody User user) {
        User userUpdate = userService.update(id, user);
        return ResponseEntity.ok(userUpdate);
    }

    //LOGIN DE USUARIO -> INICIANDO

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam String mail, @RequestParam String password) {
        try {
            return new ResponseEntity<>(userService.login(mail, password), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}

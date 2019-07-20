package org.edwardlol.petrohead.controllers;

import org.edwardlol.petrohead.entities.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    ResponseEntity<User> getUserByUsername(@RequestParam(value = "username") String username);

    List<User> getAllUsers();

    ResponseEntity<String> updateUser(@Valid @RequestBody User user);

}

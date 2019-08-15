package org.edwardlol.petrohead.controllers.impl;


import org.edwardlol.petrohead.controllers.UserService;
import org.edwardlol.petrohead.entities.user.User;
import org.edwardlol.petrohead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.List;


@RestController
@RequestMapping(path = "/user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    //---------- Retrieve Users ----------------

    @GetMapping(path = "/get")
    @Override
    public ResponseEntity<User> getUserByUsername(@RequestParam(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("There isn't a user with username: " + username));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //---------- Create a User -----------------

    /**
     * must create a new user
     * or return 500 error "attempted to assign id from null one-to-one property"
     */
    @PostMapping(path = "/create")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {

        User newUser = User.buider()
                .username(user.getUsername())
                .gender(user.getProfile().getGender().orElse(null))
                .build();
        newUser.setEmailAddress(user.getEmailAddress().orElse(null));

        newUser = userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    //---------- Update a User -----------------

    @PutMapping(path = "/update")
    @Override
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
        User userInDB = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("This user does not exists."));

        // TODO: 2019/7/3 update logic deal birthday empty atttributes

        userInDB = userRepository.save(userInDB);
        return new ResponseEntity<>("The user has been updated.", HttpStatus.OK);
    }

}

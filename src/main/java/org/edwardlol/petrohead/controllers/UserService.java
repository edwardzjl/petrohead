package org.edwardlol.petrohead.controllers;


import org.edwardlol.petrohead.entities.user.User;
import org.edwardlol.petrohead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //---------- Retrieve Users ----------------

    @GetMapping(path = "/get")
    public ResponseEntity<?> getUserByUsername(@RequestParam(value = "username") String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("There isn't a user with username: " + username, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //---------- Create a User -----------------

    // TODO: 2019-07-02 check this

    /**
     * must create a new user
     * or return 500 error "attempted to assign id from null one-to-one property"
     */
    @PostMapping(path = "/create")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
//        System.out.println(user.getCreateTime());
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
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        User userInDB = userRepository.findById(user.getId()).orElse(null);
        if (userInDB == null) {
            return new ResponseEntity<>("This user does not exists.", HttpStatus.NOT_FOUND);
        }

        // TODO: 2019/7/3 update logic deal birthday empty atttributes

        userInDB = userRepository.save(userInDB);
        return new ResponseEntity<>("The user has been updated.", HttpStatus.OK);
    }

}

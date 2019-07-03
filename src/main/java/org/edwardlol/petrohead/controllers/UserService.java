package org.edwardlol.petrohead.controllers;


import org.edwardlol.petrohead.entities.user.Profile;
import org.edwardlol.petrohead.entities.user.User;
import org.edwardlol.petrohead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //---------- Retrieve Users ----------------

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //---------- Create a User -----------------

    // TODO: 2019-07-02 check this

    /**
     *
     * must create a new user
     * or return 500 error "attempted to assign id from null one-to-one property"
     *
     * @param user
     * @return
     */
    @PostMapping(path = "/create")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {

        User newUser = User.create(user.getUsername());
        Profile profile = new Profile(newUser, user.getProfile().getGender());
        user.setProfile(profile);

        newUser = userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    //---------- Update a User -----------------

    @PutMapping(path = "update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
        User userInDB = userRepository.findById(user.getId()).orElse(null);
        if (userInDB == null) {
            return new ResponseEntity<>("This user does not exists.", HttpStatus.NOT_FOUND);
        }


        userInDB = userRepository.save(userInDB);
        return new ResponseEntity<>("The user has been updated.", HttpStatus.OK);
    }

}

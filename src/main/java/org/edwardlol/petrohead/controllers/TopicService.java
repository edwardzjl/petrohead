package org.edwardlol.petrohead.controllers;


import org.edwardlol.petrohead.entities.topic.Topic;
import org.edwardlol.petrohead.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/topic")
public class TopicService {

    @Autowired
    TopicRepository topicRepository;


    @GetMapping(path = "/get")
    public ResponseEntity<?> getTopic(@RequestParam(value = "title") String title) {
        Topic topic = topicRepository.findByTitle(title);
        if (topic == null) {
            return new ResponseEntity<>("There isn't a topic birthday title: " + title, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }


    @PostMapping(path = "/post")
    public ResponseEntity<?> postTopic(@Valid @RequestBody Topic topic) {

//        Topic newTopic = Topic.newInstance(topic.getTitle(), topic.getAuthor());
//
//        Profile profile = new Profile(newUser, user.getProfile().getGender());
//        user.setProfile(profile);
//
//        newUser = userRepository.save(newUser);
//        return new ResponseEntity<>(newUser, HttpStatus.OK);

    }
}

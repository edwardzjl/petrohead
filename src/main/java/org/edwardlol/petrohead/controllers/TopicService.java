package org.edwardlol.petrohead.controllers;


import org.edwardlol.petrohead.entities.topic.Topic;
import org.edwardlol.petrohead.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>("There isn't a topic with title: " + title, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }



}

package org.edwardlol.petrohead.repositories;

import org.edwardlol.petrohead.entities.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByTitle(String title);

}

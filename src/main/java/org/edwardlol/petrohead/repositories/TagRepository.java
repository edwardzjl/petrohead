package org.edwardlol.petrohead.repositories;

import org.edwardlol.petrohead.entities.topic.Tag;
import org.edwardlol.petrohead.entities.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Topic> findAllByName(String name);

}

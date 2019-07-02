package org.edwardlol.petrohead.repositories;

import org.edwardlol.petrohead.entities.post.Tag;
import org.edwardlol.petrohead.entities.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Post> findAllByName(String name);

}

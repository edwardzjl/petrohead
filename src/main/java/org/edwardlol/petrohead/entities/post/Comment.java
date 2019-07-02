package org.edwardlol.petrohead.entities.post;


import org.edwardlol.petrohead.entities.user.User;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private User author;

    private String content;
}

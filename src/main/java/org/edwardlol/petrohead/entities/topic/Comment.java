package org.edwardlol.petrohead.entities.topic;


import org.edwardlol.petrohead.entities.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// TODO: 2019-07-05 finish this
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private User author;

    private String content;



    public Comment() {
    }



    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

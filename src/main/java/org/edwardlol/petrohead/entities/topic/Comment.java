package org.edwardlol.petrohead.entities.topic;


import org.edwardlol.petrohead.entities.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private final User author;

    private String content;

    private final LocalDateTime createTime;

    private LocalDateTime lastModifiedTime;

    private Boolean isAnonymous;


    public Comment(User author) {
        this.author = author;
        this.createTime = LocalDateTime.now();
    }


    public Long getId() {
        return this.id;
    }

    public User getAuthor() {
        return this.author;
    }


    @Override
    public String toString() {
        return "comment{author: " + author.toString() + ", content: " + content + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Comment)) return false;

        Comment other = (Comment) obj;
        return this.id != null && this.id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}

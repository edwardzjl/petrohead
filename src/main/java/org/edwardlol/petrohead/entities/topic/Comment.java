package org.edwardlol.petrohead.entities.topic;


import org.edwardlol.petrohead.entities.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;


/**
 *
 */
@Entity
@Table(name = "comments")
public class Comment {

    //----------- instance fields -----------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private final User author;

    private String content;

    private final Instant createTime;

    private Instant lastModifiedTime;

    private Boolean isAnonymous;

    //----------- constructors -----------

    public Comment(User author) {
        this.author = author;
        this.createTime = Instant.now();
    }

    //----------- getter / setters -----------

    public Long getId() {
        return this.id;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        // TODO: 2019-07-09 some constraints
        this.content = content;
        setLastModifiedTime(Instant.now());
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public Instant getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    private void setLastModifiedTime(Instant lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Boolean getIsAnonymous() {
        return this.isAnonymous;
    }

    public void setIsAnonymous(Boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
        setLastModifiedTime(Instant.now());
    }

    //----------- object methods -----------

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

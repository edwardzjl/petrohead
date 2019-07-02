package org.edwardlol.petrohead.entities.post;

import org.edwardlol.petrohead.entities.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // required

    @NotNull
    private String title; // required

    @NotNull
    private String link; // required

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private User author; // required

    @ManyToMany
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags;

    private String content;

    private Boolean stickied;

    private Integer numberOfComments;

    private Timestamp created, modified;


    public Post() {
    }




    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public Boolean getStickied() {
        return this.stickied;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Post other = (Post) o;

        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}

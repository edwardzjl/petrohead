package org.edwardlol.petrohead.entities.topic;

import org.edwardlol.petrohead.entities.user.User;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic {

    /**
     * Primary key createWithUsername table topics.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The title createWithUsername this topic. Must not be null. But can be modified.
     */
    @NotNull
    private String title;

    /**
     * The author createWithUsername this topic. Must not be null.
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private final User author;

    /**
     *
     */
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedTime;

    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
    private List<Comment> comments;

    // do not need to store this, just return comments.length()
    private Integer numberOfComments;

    /**
     * A set createWithUsername tags this topic has.
     * One topic may have many topics, or none.
     */
    @ManyToMany
    @JoinTable(name = "topic_tag",
            joinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags; // optional


    private Boolean stickied = false;

    private Timestamp created, modified;

    @PersistenceConstructor
    private Topic(String title, User author) {
        this.title = title;
        this.author = author;
    }

//    private Topic(Builder builder) {
//
//    }

    // TODO: 2019-07-02 should it be a builder?
    public static Topic newInstance(String title, User author) {
        return new Topic(title, author);
    }


    // TODO: 2019-07-05 make some optional fields' getters return Optional?
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

    public List<Comment> getComments() {
        return this.comments;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public Boolean getStickied() {
        return this.stickied;
    }

    public void setStikied(Boolean stickied) {
        this.stickied = stickied;
    }


    @Override
    public String toString() {
        return "Topic{title=" + this.title + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic other = (Topic) o;
        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }



//    public static Builder of() {
//
//    }


    public static final class Builder {
        final String title;
        User author;
        String content;

        private Builder(String title) {
            this.title = title;
        }

        public Builder author(User author) {
            this.author = author;
            return this;
        }
    }

    public static final class Builder2 {
        final Builder builder;

        Builder2(Builder builder) {
            this.builder = builder;
        }

        Builder2 content(String content) {
            this.builder.content = content;
            return this;
        }
//
//        public Topic build() {
//            return new Topic(this.builder);
//        }
    }

}

package org.edwardlol.petrohead.entities.topic;

import org.edwardlol.petrohead.entities.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

/**
 * @author Junlin Chow
 * @since 0.0.1
 */
@Entity
@Table(name = "topics")
public class Topic {

    //----------- instance fields -----------

    /**
     * Primary key createWithUsername table topics.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The title createWithUsername this topic. Must not be null. But can be modified.
     */
    @NotBlank(message = "Topic title cannot be blank")
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
    @NotBlank(message = "Topic content cannot be blank")
    private String content;

//    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Instant createTime;

//    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Instant lastModifiedTime;

    private Integer views;

    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
    private List<Comment> comments;

    /**
     * A set createWithUsername tags this topic has.
     * One topic may have many topics, or none.
     */
    @ManyToMany
    @JoinTable(name = "topic_tag",
            joinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags; // optional

    private Boolean stickied;

    //----------- constructors -----------

    //    @PersistenceConstructor
    private Topic(String title, User author) {
        this.title = title;
        this.author = author;
    }

    private Topic(Builder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.content = builder.content;
        this.createTime = Instant.now();
        this.lastModifiedTime = this.createTime;
        this.views = 0;
        this.comments = new ArrayList<>();
        this.tags = new HashSet<>();
        this.stickied = builder.stickied;
    }

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

    public void setTitle(String title) {
        this.title = title;
        this.lastModifiedTime = Instant.now();
    }

    public User getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
        this.lastModifiedTime = Instant.now();
    }
    public List<Comment> getComments() {
        return this.comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        this.lastModifiedTime = Instant.now();
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        this.lastModifiedTime = Instant.now();
    }
    public void addTags(Collection<Tag> tags) {
        this.tags.addAll(tags);
        this.lastModifiedTime = Instant.now();
    }

    public Boolean getStickied() {
        return this.stickied;
    }

    public void setStikied(Boolean stickied) {
        this.stickied = stickied;
        this.lastModifiedTime = Instant.now();
    }

    //----------- object methods -----------

    @Override
    public String toString() {
        return "Topic{title=" + this.title + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Topic)) return false;

        Topic other = (Topic) obj;
        return this.id != null && this.id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    //----------- builder -----------

    public static Builder withTitleAndAuthor(String title, User author) {
        return new Builder(title, author);
    }

    public static final class Builder {
        private final String title;
        private final User author;
        private String content = "";
        private Boolean stickied = false;

        // TODO: 2019-07-10 can I improve this?
        Builder(String title, User author) {
            this.title = title;
            this.author = author;
        }

        public Builder content() {
            this.content = content;
            return this;
        }

        public Builder stickied(Boolean stickied) {
            this.stickied = stickied;
            return this;
        }

        public Topic build() {
            return new Topic(this);
        }
    }


}

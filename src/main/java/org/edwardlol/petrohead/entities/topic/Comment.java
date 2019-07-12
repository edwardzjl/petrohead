package org.edwardlol.petrohead.entities.topic;


import org.edwardlol.petrohead.entities.user.User;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;


/**
 * Comment of a {@code Topic}
 *
 * @author Junlin Chow
 * @since 0.0.1
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

    private final Instant createTime;

    @NotBlank(message = "Comment content cannot be blank")
    private String content;

    private Instant lastModifiedTime;

    private Boolean anonymous;

    //----------- constructors -----------

    @PersistenceConstructor
    private Comment(Builder builder) {
        this.author = builder.author;
        this.createTime = builder.createTime;
        this.content = builder.content;
        this.lastModifiedTime = this.createTime;
        this.anonymous = builder.anonymous;
    }

    //----------- getter / setters -----------

    private static void checkContent(String content) {
        // TODO: 2019-07-10  check comment length, and maybe other constrains
        if (false) {
            throw new IllegalArgumentException("Comment must be at least xxx words.");
        }
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
        checkContent(content);
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

    public Boolean getAnonymous() {
        return this.anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
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

    //----------- builder -----------

    public static Builder createWithAuthor(User author) {
        return new Builder(author);
    }

    public static final class Builder implements Author, Content {
        private final User author;
        private final Instant createTime;
        private String content = "";
        private Boolean anonymous = false;

        private Builder(User author) {
            this.author = author;
            this.createTime = Instant.now();
        }

        /**
         * Mandatory, must be followed by {@link Author#author(User)}
         */
        @Override
        public Content author(User author) {
            return this;
        }

        /**
         *
         */
        @Override
        public Builder content(String content) {
            return this;
        }
        //        public Builder content(String content) {
//            checkContent(content);
//            this.content = content;
//            return this;
//        }

        public Builder anonymous(Boolean anonymous) {
            this.anonymous = anonymous;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    interface Author {
        public Content author(User author);
    }

    interface Content {
        public Builder content(String content);
    }


}

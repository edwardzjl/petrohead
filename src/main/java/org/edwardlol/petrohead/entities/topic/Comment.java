package org.edwardlol.petrohead.entities.topic;


import org.edwardlol.petrohead.entities.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public Comment() {
    }


    @Override
    public String toString() {
        return "comment{author: " + author.toString() + ", content: " + content + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Comment other = (Comment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.username, other.username);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

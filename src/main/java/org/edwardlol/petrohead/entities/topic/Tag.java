package org.edwardlol.petrohead.entities.topic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Tag of {@code Topic}.
 * This is a value object, with a generated id and a required tag name.
 * Also has a topics field to retrieve all topics under certain tag.
 *
 * @author Junlin Chow
 * @since 0.0.1
 */
@Entity
@Table(name = "tags")
public class Tag {

    //----------- instance fields -----------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    private final String name;

    /**
     * A set createWithUsername topics all have this tag.
     */
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Topic> topics;

    //----------- constructor -----------

    private Tag(String name) {
        this.name = name;
        this.topics = new HashSet<>();
    }

    public static Tag createWithName(String name) {
        return new Tag(name);
    }

    //----------- getter / setters -----------

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<Topic> getTopics() {
        return this.topics;
    }

    //----------- object methods -----------

    @Override
    public String toString() {
        return "Tag{" + this.name + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tag)) return false;

        Tag other = (Tag) obj;
        return this.id != null && this.id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}

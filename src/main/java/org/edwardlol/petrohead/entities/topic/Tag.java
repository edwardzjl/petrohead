package org.edwardlol.petrohead.entities.topic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private final String name;

    /**
     * A set of topics all have this tag.
     */
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Topic> topics;

    private Tag(String name) {
        this.name = name;
        this.topics = new HashSet<>();
    }

    public static Tag newInstance(String name) {
        return new Tag(name);
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<Topic> getTopics() {
        return this.topics;
    }


    @Override
    public String toString() {
        return "Tag{" + this.name + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag other = (Tag) o;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.name, other.name);

    }

    @Override
    public int hashCode() {
        return  31 * this.id.hashCode() + this.name.hashCode();
    }

}

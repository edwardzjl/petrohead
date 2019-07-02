package org.edwardlol.petrohead.entities.post;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Post> posts;

    public Tag() {
    }

    private Tag(String name) {
        this.name = name;
    }

    // TODO: 2019-07-02 should it be a builder?
    public static Tag create(Long id, String name) {
        return new Tag(name);
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
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
        int result = this.id != null ? this.id.hashCode() : 0;

        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);

        return result;
    }

}

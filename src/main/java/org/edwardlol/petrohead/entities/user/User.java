package org.edwardlol.petrohead.entities.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Profile profile;

    public User() {
    }

    private User(String name) {
        this.name = name;
    }

    // TODO: 2019-07-02 should it be a builder?
    public static User create(Long id, String name) {
        return new User(name);
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Profile getProfile() {
        return this.profile;
    }




    @Override
    public String toString() {
        return "User{name=" + this.name + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User other = (User) o;

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

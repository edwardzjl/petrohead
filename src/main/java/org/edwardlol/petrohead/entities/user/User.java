package org.edwardlol.petrohead.entities.user;

import org.apache.commons.validator.routines.EmailValidator;

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
    private String username;

    // TODO: 2019-07-02 go into security service before do anything here.
    private String passwordHash;
    
    private String emailAddress;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Profile profile;

    public User() {
    }

    private User(String username) {
        this.username = username;
    }

    // TODO: 2019-07-02 should it be a builder?
    public static User create(String name) {
        return new User(name);
    }


    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) throws IllegalArgumentException {
        if (EmailValidator.getInstance().isValid(emailAddress)) {
            this.emailAddress = emailAddress;
        } else {
            throw new IllegalArgumentException("The input is not a valid email!");
        }
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    @Override
    public String toString() {
        return "User{username=" + this.username + "}";
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
        return Objects.equals(this.username, other.username);
    }

    @Override
    public int hashCode() {
        return 31 * this.id.hashCode() + this.username.hashCode();
    }

}

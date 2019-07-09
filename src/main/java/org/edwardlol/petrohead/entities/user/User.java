package org.edwardlol.petrohead.entities.user;

import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;

/**
 *
 * The {@code User} class.
 * The optional information is stored in {@code Profile} class, this class mainly stores the identity information.
 *
 * @author  Junlin Chow
 * @since   0.0.1
 */
@Entity
@Table(name = "users")
public class User {

    // TODO: 2019-07-08 make this configurable by a config file
    /**
     * user can change their username after every certain perioid.
     */
    static Period CHANGE_NAME_PERIODS = Period.ofMonths(1);

    /**
     * Primary key createWithUsername table users.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO: 2019-07-08 should be unique in db.
    /**
     * The username createWithUsername this user. Must not be null, but can be modified.
     */
    @NotNull
    private String username;

    /**
     * When this user is created.
     */
    private final LocalDateTime createTime;

    /**
     * Last time this user changed his username.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime usernameLastModifiedTime;

    // TODO: 2019-07-02 dive into security service before do anything here.
    private String passwordHash;
    
    private String emailAddress;

    /**
     * A user instance must be created before the creation of his/her profile,
     * so this field should be set after the creation of user, not in the constructor or {@code Builder}.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Profile profile;



    private User(Builder builder) {
        this.username = builder.username;
        this.createTime = builder.createTime;
        this.usernameLastModifiedTime = builder.usernameLastModifiedTime;
        this.passwordHash = builder.passwordHash;
        this.emailAddress = builder.emailAddress;
    }



    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        // TODO: 2019-07-08 check last modified time
        this.username = username;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public LocalDateTime getUsernameLastModifiedTime() {
        return this.usernameLastModifiedTime;
    }

    public String getPasswordHash() {
        return this.passwordHash;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.username, other.username);
    }

    @Override
    public int hashCode() {
        return 31 * this.id.hashCode() + this.username.hashCode();
    }




    public static Builder createWithUsername(String username) {
        return new Builder(username);
    }

    public static class Builder {

        private final String username;
        private final LocalDateTime createTime;
        private LocalDateTime usernameLastModifiedTime;
        private String passwordHash;
        private String emailAddress;

        private Builder(String username) {
            this.username = username;
            this.createTime = LocalDateTime.now();
            this.usernameLastModifiedTime = LocalDateTime.now();
        }

        public Builder withPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder withEmail(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

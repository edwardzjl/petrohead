package org.edwardlol.petrohead.entities.user;


import com.google.common.base.Preconditions;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.*;
import java.util.Optional;

/**
 * The {@code User} class.
 * The optional information is stored in {@code Profile} class, this class mainly stores the identity information.
 *
 * @author Junlin Chow
 * @since 0.0.1
 */
@Entity
@Table(name = "users")
public class User {

    // TODO: 2019-07-09 follower, following, collections

    //----------- statis fields -----------

    // TODO: 2019-07-08 make this configurable by a config file?
    /**
     * user can change their username after every certain perioid.
     */
    private static Integer CHANGE_NAME_PERIOD_MONTHS = 1;

    private static Integer CHANGE_NAME_POINTS = 100;

    //----------- instance fields -----------

    /**
     * Primary key createWithUsername table users.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The username createWithUsername this user. Must not be null, but can be modified.
     */
    @Column(unique = true)
    @NotBlank(message = "username cannot be blank")
    private String username;

    /**
     * When this user is created.
     */
    @NotNull
    private final Instant createTime;

    /**
     * Last time this user changed his username.
     */
//    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Instant usernameLastModifiedTime;

    // TODO: 2019-07-02 dive into security service before do anything here.
    @NotBlank
    private String passwordHash;

    @Column(unique = true)
    private String emailAddress;

    private Instant lastlogin;

    /**
     * A user instance must be created before the creation of his/her profile,
     * so this field should be set after the creation of user, not in the constructor or {@code Builder}.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private Profile profile;

    //----------- constructors -----------

    private User(Builder builder) {
        this.username = builder.username;
        this.createTime = builder.createTime;
        this.usernameLastModifiedTime = this.createTime;
        this.passwordHash = builder.passwordHash;
        this.emailAddress = builder.emailAddress;
    }

    //----------- getter / setters -----------

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        Preconditions.checkArgument(this.profile.getPoints() > CHANGE_NAME_POINTS,
                "You don't have enough points!");

        Instant now = Instant.now();
        LocalDate start = LocalDateTime.ofInstant(now, ZoneId.systemDefault()).toLocalDate();
        LocalDate end = LocalDateTime.ofInstant(this.usernameLastModifiedTime, ZoneId.systemDefault()).toLocalDate();

        Preconditions.checkArgument(Period.between(start, end).getMonths() > CHANGE_NAME_PERIOD_MONTHS,
                "You can only change your ");

        this.username = username;
        setUsernameLastModifiedTime(now);
    }

    public Instant getCreateTime() {
        return this.createTime;
    }

    public Instant getUsernameLastModifiedTime() {
        return this.usernameLastModifiedTime;
    }

    private void setUsernameLastModifiedTime(Instant usernameLastModifiedTime) {
        this.usernameLastModifiedTime = usernameLastModifiedTime;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public Optional<String> getEmailAddress() {
        return Optional.ofNullable(this.emailAddress);
    }

    public void setEmailAddress(String emailAddress) throws IllegalArgumentException {
        if (EmailValidator.getInstance().isValid(emailAddress)) {
            this.emailAddress = emailAddress;
        } else {
            throw new IllegalArgumentException("The input is not a valid email!");
        }
    }

    public Instant getLastlogin() {
        return this.lastlogin;
    }

    public void setLastlogin(Instant lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    //----------- object methods -----------

    @Override
    public String toString() {
        return "User{username=" + this.username + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;

        User other = (User) obj;
        return this.id != null && this.id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    //----------- builder -----------

    public static Builder createWithUsername(String username) {
        return new Builder(username);
    }

    public static final class Builder {
        // TODO: 2019-07-09 build a profile here?
        private final String username;
        private final Instant createTime;
        private String passwordHash;
        private String emailAddress;

        private Builder(String username) {
            this.username = username;
            this.createTime = Instant.now();
        }

        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder email(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

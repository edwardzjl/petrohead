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

    //----------- cascade getter / setters from Profile -----------

    public String getAvatar() {
        return this.profile.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.profile.setAvatar(avatar);
    }

    public Optional<Gender> getGender() {
        return this.profile.getGender();
    }

    public void setGender(Gender gender) {
        this.profile.setGender(gender);
    }

    public Optional<LocalDate> getBirthday() {
        return this.profile.getBirthday();
    }

    public void setBirthday(LocalDate birthday) {
        this.profile.setBirthday(birthday);
    }

    public String getDescription() {
        return this.profile.getDescription();
    }

    public void setDescription(String description) {
        this.profile.setDescription(description);
    }

    public Integer getPoints() {
        return this.profile.getPoints();
    }

    public void setPoints(Integer points) {
        this.profile.setPoints(points);
    }

    public Rank getRank() {
        return this.profile.getRank();
    }

    public void setRank(Rank rank) {
        this.profile.setRank(rank);
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

    public interface Username {
        Builder username(String username);
    }

    public static Username newBuider() {
        return new Builder();
    }

    public static final class Builder implements Username {
        // mandatory
        private String username;
        private Instant createTime;

        // optional
        private String passwordHash;
        private String emailAddress;

        private String avatar;
        private Gender gender;
        private LocalDate birthday;
        private String description = "";
        private Integer points = 0;
        private Rank rank = Rank.Private;


        @Override
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder email(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder points(Integer points) {
            this.points = points;
            return this;
        }

        public Builder rank(Rank rank) {
            this.rank = rank;
            return this;
        }


        public User build() {
            User user = new User(this);
            Profile profile = Profile.of(user)
                    .avatar(this.avatar)
                    .gender(this.gender)
                    .birthday(this.birthday)
                    .description(this.description)
                    .points(this.points)
                    .rank(this.rank)
                    .build();
            user.setProfile(profile);
            return user;
        }
    }

}

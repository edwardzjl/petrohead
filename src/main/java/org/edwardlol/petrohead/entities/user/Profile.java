package org.edwardlol.petrohead.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;


/**
 * The {@code Profile} class contains optional information createWithUsername a {@code User}.
 *
 * @author Junlin Chow
 * @since 0.0.1
 */
@Entity
@Table(name = "profiles")
public class Profile {

    //----------- instance fields -----------

    /**
     * Primary key createWithUsername table profiles.
     */
    @Id
    private Long id;

    /**
     * The user which this profile belongs to.
     */
    @OneToOne
    @JoinColumn(nullable = false)
    @MapsId
    @JsonIgnore
    @NotNull
    private User user;

    private String avatar;

    private Gender gender;

    private LocalDate birthday;

    private String description;

    private Integer points;

    private Rank rank;

    //----------- constructors -----------

    protected Profile() {
    }

    private Profile(Builder builder) {
        this.user = builder.user;
        this.avatar = builder.avatar;
        this.gender = builder.gender;
        this.birthday = builder.birthday;
        this.description = builder.description;
        this.points = builder.points;
        this.rank = builder.rank;
    }

    //----------- getter / setters -----------

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        // TODO: 2019-07-09 check avatar url
        this.avatar = avatar;
    }

    public Optional<Gender> getGender() {
        return Optional.ofNullable(this.gender);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Optional<LocalDate> getBirthday() {
        return Optional.ofNullable(this.birthday);
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Description cannot be null, the default value is "".
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Points cannot be null, the default value is 0.
     */
    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * Rank cannot be null, the default value is {@code Rank.Private}
     */
    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    //----------- object methods -----------

    @Override
    public String toString() {
        return "Profile of user " + this.user.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Profile)) return false;

        Profile other = (Profile) obj;
        return this.id != null && this.id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    //----------- builder -----------

    public interface ofUser {
        Builder user(User user);
    }

    public static ofUser buider() {
        return new Builder();
    }

    public static final class Builder implements ofUser {

        private User user;
        // TODO: 2019-07-26 there should be a default avatar
        private String avatar;
        private Gender gender;
        private LocalDate birthday;
        private String description = "";
        private Integer points = 0;
        private Rank rank = Rank.Private;

        @Override
        public Builder user(User user) {
            this.user = user;
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

        public Profile build() {
            return new Profile(this);
        }
    }
}

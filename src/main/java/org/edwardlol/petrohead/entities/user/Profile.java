package org.edwardlol.petrohead.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 *
 * The {@code Profile} class contains optional information of a {@code User}.
 *
 * @author  Junlin Chow
 * @since   0.0.1
 */
@Entity
@Table(name = "profiles")
public class Profile {

    /**
     * Primary key of table profiles.
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
    private final User user;

    /**
     * Can be modified...
     */
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birthday;


    private String description;

    private Integer points;

    private Rank rank;



    private Profile(Builder builder) {
        this.user = builder.user;
        this.gender = builder.gender;
        this.birthday = builder.birthday;
        this.description = builder.description;
        this.points = builder.points;
        this.rank = builder.rank;
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }




    @Override
    public String toString() {
        return "Profile of " + this.user.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }




    public static Builder of(User user) {
        return new Builder(user);
    }

    // TODO: 2019-07-02 add a builder class


    public static class Builder {

        private final User user;
        private Gender gender;
        private Date birthday;
        private String description;
        private Integer points;
        private Rank rank;

        private Builder(User user) {
            this.user = user;
        }

        public Builder with(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder with(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder with(String description) {
            this.description = description;
            return this;
        }

        public Builder with(Integer points) {
            this.points = points;
            return this;
        }

        public Builder with(Rank rank) {
            this.rank = rank;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }
    }
}

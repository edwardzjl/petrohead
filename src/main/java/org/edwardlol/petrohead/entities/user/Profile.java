package org.edwardlol.petrohead.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


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

    //----------- constructors -----------

    private Profile(Builder builder) {
        this.user = builder.user;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    //----------- object methods -----------

    @Override
    public String toString() {
        return "Profile createWithUsername " + this.user.toString();
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

    public static Builder of(User user) {
        return new Builder(user);
    }


    public static class Builder {

        private final User user;
        private Gender gender;
        private Date birthday;
        private String description = "";
        private Integer points = 0;
        private Rank rank = Rank.Private;

        private Builder(User user) {
            this.user = user;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthday(Date birthday) {
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

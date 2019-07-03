package org.edwardlol.petrohead.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    @MapsId
    @JsonIgnore
    private User user;

    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    public Profile() {
    }

    // TODO: 2019-07-02 builder?
    public Profile(User user, Gender gender) {
        this.user = user;
        this.gender = gender;
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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


    // TODO: 2019-07-02 add a builder class
}

package org.edwardlol.petrohead.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.*;

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

    private String emailAddress;

    private Gender gender;

    public Profile() {
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
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

    public Gender gender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

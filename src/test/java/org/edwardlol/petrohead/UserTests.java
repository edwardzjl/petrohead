package org.edwardlol.petrohead;

import org.edwardlol.petrohead.controllers.UserService;
import org.edwardlol.petrohead.entities.user.Gender;
import org.edwardlol.petrohead.entities.user.Profile;
import org.edwardlol.petrohead.entities.user.User;
import org.edwardlol.petrohead.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserTests {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() {
        User judy = User.create("Judy");
        Profile judyProfile = new Profile(judy, Gender.Female);

        try {
            judyProfile.setBirthday(new SimpleDateFormat("MM/dd/yyyy").parse(("4/12/2010")));
        } catch (ParseException e) {
            System.err.println("birthday format error");
        }

        judy.setProfile(judyProfile);
        judy = userRepository.save(judy);
//        entityManager.flush();
        userRepository.flush();


//        userRepository.findAll();

        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();

    }


    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        User judy = User.create("Judy");
        Profile judyProfile = new Profile(judy, Gender.Female);

        try {
            judyProfile.setBirthday(new SimpleDateFormat("MM/dd/yyyy").parse(("4/12/2010")));
        } catch (ParseException e) {
            System.err.println("birthday format error");
        }

        judy.setProfile(judyProfile);

        entityManager.persist(judy);
        entityManager.flush();

        // when
        User found = userRepository.findByUsername(judy.getUsername());

        // then
        assertThat(found.getUsername()).isEqualTo(judy.getUsername());

    }
}

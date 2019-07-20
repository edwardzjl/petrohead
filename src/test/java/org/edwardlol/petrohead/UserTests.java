package org.edwardlol.petrohead;

import org.edwardlol.petrohead.controllers.UserService;
import org.edwardlol.petrohead.controllers.impl.UserServiceImpl;
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
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void createUser() throws ParseException {
        User judy = User.buider().username("Judy1").build();
        Profile judyProfile = Profile.buider().user(judy)
                .gender(Gender.Female)
                .birthday(LocalDate.parse("2010-04-12"))
                .build();

        judy.setProfile(judyProfile);
        judy = userRepository.save(judy);
        userRepository.flush();

        UserService userService = new UserServiceImpl();
        List<User> users = userService.getAllUsers();
    }


    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        User judy = User.buider()
                .username("judy1")
                .gender(Gender.Female)
                .birthday(LocalDate.parse("2010-04-12"))
                .build();

        entityManager.persist(judy);
        entityManager.flush();

        // when
        User found = userRepository.findByUsername(judy.getUsername()).orElse(null);

        // then
        assertThat(found.getUsername()).isEqualTo(judy.getUsername());

    }
}

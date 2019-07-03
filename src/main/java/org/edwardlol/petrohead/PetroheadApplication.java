package org.edwardlol.petrohead;

import org.edwardlol.petrohead.controllers.UserService;
import org.edwardlol.petrohead.entities.user.Gender;
import org.edwardlol.petrohead.entities.user.Profile;
import org.edwardlol.petrohead.entities.user.User;
import org.edwardlol.petrohead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@EnableJpaRepositories(basePackages = "org.edwardlol.petrohead.repositories")
@SpringBootApplication
public class PetroheadApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PetroheadApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
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

//		UserService userService = new UserService();
//		List<User> users = userService.getAllUsers();
	}
}

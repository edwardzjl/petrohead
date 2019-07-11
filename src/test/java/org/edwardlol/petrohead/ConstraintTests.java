package org.edwardlol.petrohead;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ConstraintTests {

    static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    public void notBlank() {

        Person person = new Person("");
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> message = violations.stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
                .collect(Collectors.toList());

        message.forEach(System.out::println);
    }



    class Person {
        @NotBlank(message = "name cannot be blank")
        private String name;

        Person(String name) {
            this.name = name;
        }
    }
}

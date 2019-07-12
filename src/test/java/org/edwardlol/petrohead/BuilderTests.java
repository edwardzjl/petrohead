package org.edwardlol.petrohead;

import org.edwardlol.petrohead.entities.user.Gender;
import org.edwardlol.petrohead.entities.user.Rank;
import org.edwardlol.petrohead.entities.user.User;
import org.junit.Test;

import java.time.LocalDate;

public class BuilderTests {


    @Test
    public void printBuilderType() {
        System.out.println(User.buider().getClass().getSimpleName());
    }

    @Test
    public void UserBuilderTest() {
        User jude = User.buider()
                .username("jude")
                .gender(Gender.Female)
                .description("haha")
                .build();
        System.out.println(jude.toString());
        System.out.println(jude.getProfile().getGender());
    }

    @Test
    public void UserBuilderFullParamTest() {
        User james = User.buider()
                .username("james")
                .gender(Gender.Male)
                .passwordHash("test")
                .email("sunfish@gmail.com")
                .avatar("uri.test")
                .birthday(LocalDate.parse("1990-03-15"))
                .description("hehe")
                .points(15)
                .rank(Rank.KnightChampion)
                .build();


        System.out.println(james);
        System.out.println();

    }
}

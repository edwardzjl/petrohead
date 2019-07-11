package org.edwardlol.petrohead;

import com.sun.tools.javac.jvm.Gen;
import org.edwardlol.petrohead.entities.user.Gender;
import org.edwardlol.petrohead.entities.user.Rank;
import org.edwardlol.petrohead.entities.user.User;
import org.junit.Test;

import java.time.LocalDate;

public class BuilderTests {



    @Test
    public void UserBuilderTest() {
        User jude = User.newBuider()
                .username("jude")
                .gender(Gender.Female)
                .description("haha")
                .build();
        System.out.println(jude.toString());
        System.out.println(jude.getProfile().getGender());
    }

    @Test
    public void UserBuilderFullParamTest() {
        User james = User.newBuider()
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

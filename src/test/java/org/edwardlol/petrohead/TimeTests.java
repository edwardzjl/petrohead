package org.edwardlol.petrohead;

import org.junit.Test;

import java.time.*;

public class TimeTests {


    @Test
    public void durationCompare() {
        Duration duration = Duration.ofDays(1);
        Duration duration2 = Duration.ofDays(3);

        System.out.println(duration.compareTo(duration2));
    }


    @Test
    public void periodGetMonth() {
        LocalDate start = LocalDate.of(1990, 3, 15);
        LocalDate end = LocalDate.of(1990, 4, 1);
        System.out.println(Period.between(start, end).getMonths());


        start = LocalDate.of(1990, 3, 15);
        end = LocalDate.of(1990, 4, 14);
        System.out.println(Period.between(start, end).getMonths());

        start = LocalDate.of(1990, 3, 15);
        end = LocalDate.of(1990, 4, 15);
        System.out.println(Period.between(start, end).getMonths());


        start = LocalDate.of(1990, 3, 15);
        end = LocalDate.of(1990, 4, 25);
        System.out.println(Period.between(start, end).getMonths());
    }

    @Test
    public void instantVsLocalDateTime() {
        Instant _start = Instant.parse("1990-03-15T16:01:13.419Z");
        Instant _end = Instant.parse("1990-04-14T16:01:13.419Z");
        LocalDate start = LocalDateTime.ofInstant(_start, ZoneId.systemDefault()).toLocalDate();
        LocalDate end = LocalDateTime.ofInstant(_end, ZoneId.systemDefault()).toLocalDate();
        System.out.println(Period.between(start, end));
    }
}

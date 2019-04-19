import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateTest {
    public static void main(String[] args) {

        System.out.println("LocalDate.now():" + LocalDate.now());
        System.out.println("LocalTime.now():" + LocalTime.now());

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthOfYear();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d, Month : %d, day : %d \t %n", year, month, day);


        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }
}

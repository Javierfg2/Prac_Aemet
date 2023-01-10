package API_REST;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Api_controller {

    public static ArrayList<LocalDate> days_between(LocalDate first_day, LocalDate second_day) {

        second_day = second_day.plusDays(1);

        ArrayList<LocalDate> listOfDates = (ArrayList<LocalDate>) first_day.datesUntil(second_day)
                .collect(Collectors.toList());

        return listOfDates;
    }

    public static ArrayList<String> get_places_with_max_temperature(LocalDate first_day, LocalDate second_day) {
        ArrayList<LocalDate> listOfDates = days_between(first_day, second_day);
        ArrayList<String> places = new ArrayList<>();

        for (LocalDate day : listOfDates) {
            String date = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String place = Datamart_accsesor.select_hottest_place(date);
            if (!places.contains(place)) {
                places.add(place);
            }
        }

        return places;
    }

    public static ArrayList<String> get_places_with_min_temperature(LocalDate first_day, LocalDate second_day) {
        ArrayList<LocalDate> listOfDates = days_between(first_day, second_day);
        ArrayList<String> places = new ArrayList<>();

        for (LocalDate day : listOfDates) {
            String date = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String place = Datamart_accsesor.select_coldest_place(date);
            if (!places.contains(place)) {
                places.add(place);
            }
        }

        return places;
    }


}

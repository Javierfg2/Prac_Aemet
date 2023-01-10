package API_REST;


import java.time.LocalDate;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        LocalDate first = LocalDate.parse("2023-01-08");
        LocalDate second = LocalDate.parse("2023-01-10");


        ArrayList<String> hot = Api_controller.get_places_with_max_temperature(first, second);

        ArrayList<String> cold = Api_controller.get_places_with_min_temperature(first, second);

        System.out.println("Coldest places:");

        for (String c : cold) {
            System.out.println(c);
        }

        System.out.println("Hottest places:");

        for (String h : hot) {
            System.out.println(h);
        }


    }

}

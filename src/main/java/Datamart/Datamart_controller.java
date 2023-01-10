package Datamart;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class Datamart_controller {

    public static String datalake_name() {

        String time = String.valueOf((LocalDateTime.now().minusHours(1)));
        String name = time.replaceAll("-", "");
        name = "C:\\Users\\usuario\\Documents\\2 DATOS\\DACD\\Datalake to Datamart\\aemet_data\\" + name.substring(0, 8) + ".events.txt";

        return name;
    }

    public static String get_max_temperature(String filename) {
        String max_line = null;
        float max = 0;
        Boolean first = true;

        File file = new File(filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(line);
                float temperature = Float.parseFloat(String.valueOf(json.get("max_temperature")));

                if (first) {
                    max_line = line;
                    first = false;
                    max = temperature;

                } else {
                    if (temperature > max) {
                        max_line = line;
                        max = temperature;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return max_line;

    }

    public static String get_min_temperature(String filename) {
        String min_line = null;
        float min = 0;
        Boolean first = true;

        File file = new File(filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                JsonParser parser = new JsonParser();
                JsonObject json = (JsonObject) parser.parse(line);
                float temperature = Float.parseFloat(String.valueOf(json.get("min_temperature")));

                if (first) {
                    min_line = line;
                    first = false;
                    min = temperature;

                } else {
                    if (temperature < min) {
                        min_line = line;
                        min = temperature;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return min_line;

    }

    public static void add_max_temperature(String filename) {

        String max_line = Datamart_controller.get_max_temperature(filename);
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(max_line);

        String datetime = String.valueOf(json.getAsJsonObject().get("timestamp"));
        String date = datetime.substring(3, 13);
        String time = datetime.substring(14, 22);
        String place = String.valueOf(json.getAsJsonObject().get("place"));
        place = place.replaceAll("\"", "");
        place = place.replaceAll("\\\\", "");
        String station = String.valueOf(json.getAsJsonObject().get("station")).substring(3, 8);
        float value = Float.parseFloat(String.valueOf(json.getAsJsonObject().get("max_temperature")));

        Database.delete_max_temperature(date);

        Database.insert_max_temperature(date, time, place, station, value);

    }

    public static void add_min_temperature(String filename) {

        String min_line = Datamart_controller.get_min_temperature(filename);
        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(min_line);

        String datetime = String.valueOf(json.getAsJsonObject().get("timestamp"));
        String date = datetime.substring(3, 13);
        String time = datetime.substring(14, 22);
        String place = String.valueOf(json.getAsJsonObject().get("place"));
        place = place.replaceAll("\"", "");
        place = place.replaceAll("\\\\", "");
        String station = String.valueOf(json.getAsJsonObject().get("station")).substring(3, 8);
        float value = Float.parseFloat(String.valueOf(json.getAsJsonObject().get("min_temperature")));

        Database.delete_min_temperature(date);

        Database.insert_min_temperature(date, time, place, station, value);

    }


}

package Datalake;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.time.LocalDateTime;

public class Aemet_controller {

    public static boolean is_last_hour(String date) {

        date = date.replaceAll("\"", "");

        String time_now = String.valueOf((LocalDateTime.now().minusHours(1)));
        String time = time_now.substring(0, 14) + "00:00";

        return time.equals(date);

    }

    public static boolean is_gc(String longitude, String latitude) {
        float lon = Float.parseFloat(longitude);
        float lat = Float.parseFloat(latitude);

        if ((-16 < lon) && (lon < -15) && (27.5 < lat) && (lat < 28.4)) {
            return true;
        } else {
            return false;
        }
    }

    public static JsonArray filter_meditions(JsonArray all_meditions) {
        JsonArray filtered = new JsonArray();

        for (JsonElement medition : all_meditions) {
            String lon = String.valueOf(medition.getAsJsonObject().get("lon"));
            String lat = String.valueOf(medition.getAsJsonObject().get("lat"));
            String date = String.valueOf(medition.getAsJsonObject().get("fint"));

            if (is_last_hour(date) && is_gc(lon, lat)) {
                filtered.add(medition);
            }

        }

        return filtered;
    }


}

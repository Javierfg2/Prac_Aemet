package Datalake;

import com.google.gson.*;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Datalake {

    public static String get_filename(JsonElement medition) {

        String date = String.valueOf(medition.getAsJsonObject().get("fint"));
        date = date.replaceAll("\"", "");
        date = date.replaceAll("-", "");
        date = date.substring(0, 8);

        return "C:\\Users\\usuario\\Documents\\2 DATOS\\DACD\\Datalake to Datamart\\aemet_data\\" + date + ".events.txt";
    }


    public static String json_format(Event event) {
        Gson g = new Gson();
        String str = g.toJson(event);

        return str;
    }

    public static void create_datalake() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        JsonArray jsonarray = Aemet_accesor.get_connection();
        JsonArray filtered = Aemet_controller.filter_meditions(jsonarray);

        for (JsonElement medition : filtered) {
            String filename = get_filename(medition);
            Event event = Event.create_event(medition);
            String jsonstring = json_format(event);
            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(jsonstring);

            File file = new File(filename);
            if (file.createNewFile()) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(json + "\n");
                    writer.close();
                    System.out.println("File created");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
                    writer.write(json + "\n");
                    writer.close();
                    System.out.println("File updated");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }


}

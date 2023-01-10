package Datalake;

import com.google.gson.JsonElement;

public class Event {

    String timestamp;
    String station;
    String place;
    float max_temperature;
    float min_temperature;

    public Event(String timestamp, String station, String place, float max_temperature, float min_temperature) {
        this.timestamp = timestamp;
        this.station = station;
        this.place = place;
        this.max_temperature = max_temperature;
        this.min_temperature = min_temperature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public float getMax_temperature() {
        return max_temperature;
    }

    public void setMax_temperature(float max_temperature) {
        this.max_temperature = max_temperature;
    }

    public float getMin_temperature() {
        return min_temperature;
    }

    public void setMin_temperature(float min_temperature) {
        this.min_temperature = min_temperature;
    }

    public static Event create_event(JsonElement medition) {
        float max_temperature;
        float min_temperature;
        String timestamp = String.valueOf(medition.getAsJsonObject().get("fint"));
        String station = String.valueOf(medition.getAsJsonObject().get("idema"));
        String place = String.valueOf(medition.getAsJsonObject().get("ubi"));
        if (medition.getAsJsonObject().get("tamax") == null) {
            max_temperature = 0;
        } else {
            max_temperature = Float.parseFloat(String.valueOf(medition.getAsJsonObject().get("tamax")));
        }
        if (medition.getAsJsonObject().get("tamin") == null) {
            min_temperature = 100;
        } else {
            min_temperature = Float.parseFloat(String.valueOf(medition.getAsJsonObject().get("tamin")));
        }


        Event event = new Event(timestamp, station, place, max_temperature, min_temperature);
        return event;
    }

}

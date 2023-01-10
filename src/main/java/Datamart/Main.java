package Datamart;


import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        Database.createNewDatabase();
        Database.create_max_temperature_table();
        Database.create_min_temperature_table();

        Timer timer = new Timer();

        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                String filename = Datamart_controller.datalake_name();

                Datamart_controller.add_max_temperature(filename);
                Datamart_controller.add_min_temperature(filename);
            }
        };

        timer.schedule(timertask, 0, 3600000);


    }
}

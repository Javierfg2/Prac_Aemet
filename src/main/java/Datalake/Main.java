package Datalake;


import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {

        File aemet_data = new File("C:\\Users\\usuario\\Documents\\2 DATOS\\DACD\\Datalake to Datamart\\aemet_data");
        aemet_data.mkdir();

        Timer timer = new Timer();

        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                try {
                    Datalake.create_datalake();
                } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        timer.schedule(timertask, 0, 3600000);

    }
}




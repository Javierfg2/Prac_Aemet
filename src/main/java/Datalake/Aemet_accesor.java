package Datalake;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Aemet_accesor {

    public static JsonArray get_connection() throws IOException, NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new TrustAllTrustManager()}, null);
        SSLContext.setDefault(sslContext);

        String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXZpZXJmcmFuY29nb256YWxlejAyQGdtYWlsLmNvbSIsImp0aSI" +
                "6ImNiNjNiOTc0LTFiMWUtNDg5Yi05MjUyLThkNWExYjZlYjQ2YSIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjcyNTc5OT" +
                "U0LCJ1c2VySWQiOiJjYjYzYjk3NC0xYjFlLTQ4OWItOTI1Mi04ZDVhMWI2ZWI0NmEiLCJyb2xlIjoiIn0.JMh-2Kf1irO" +
                "KI37JgT6lpfleLEyReBUXu3riJlIeUVY";


        String response = Jsoup.connect(url)
                .timeout(12000)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();


        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(response);
        String link = String.valueOf(json.get("datos")).replaceAll("\"", "");


        String response2 = Jsoup.connect(link)
                .timeout(12000)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();


        JsonParser parser2 = new JsonParser();
        JsonArray jsonarray = (JsonArray) parser2.parse(response2);

        return jsonarray;
    }

}


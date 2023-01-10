package API_REST;

import java.sql.*;

public class Datamart_accsesor {

    public static String select_hottest_place(String date) {

        Connection conn = null;
        String place = null;
        try {

            conn = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/datamart.db");

            Statement stmt = conn.createStatement();

            String sql = "SELECT place FROM Max_temperature WHERE date = '" + date + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                place = rs.getString("place");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (place == null) {
            return "No data found for this day";
        }

        return place;

    }

    public static String select_coldest_place(String date) {

        Connection conn = null;
        String place = null;
        try {

            conn = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/datamart.db");

            Statement stmt = conn.createStatement();

            String sql = "SELECT place FROM Min_temperature WHERE date = '" + date + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                place = rs.getString("place");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (place == null) {
            return "No data found for this day";
        }

        return place;

    }
}

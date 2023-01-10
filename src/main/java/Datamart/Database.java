package Datamart;

import java.sql.*;

public class Database {

    public static void createNewDatabase() throws ClassNotFoundException {

        String url = "jdbc:sqlite:C:/sqlite/db/datamart.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void create_max_temperature_table() {

        String url = "jdbc:sqlite:C://sqlite/db/datamart.db";

        String sql = "CREATE TABLE IF NOT EXISTS Max_temperature (\n"
                + "	date TEXT,\n"
                + "	time TEXT,\n"
                + "	place TEXT,\n"
                + "	station TEXT,\n"
                + "	value INTEGER\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void create_min_temperature_table() {

        String url = "jdbc:sqlite:C://sqlite/db/datamart.db";

        String sql = "CREATE TABLE IF NOT EXISTS Min_temperature (\n"
                + "	date TEXT,\n"
                + "	time TEXT,\n"
                + "	place TEXT,\n"
                + "	station TEXT,\n"
                + "	value INTEGER\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection connect() {
        String url = "jdbc:sqlite:C://sqlite/db/datamart.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void insert_max_temperature(String date, String time, String place, String station, float value) {
        String sql = "INSERT INTO Max_temperature(date, time, place, station, value) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, time);
            pstmt.setString(3, place);
            pstmt.setString(4, station);
            pstmt.setFloat(5, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert_min_temperature(String date, String time, String place, String station, float value) {
        String sql = "INSERT INTO Min_temperature(date, time, place, station, value) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, time);
            pstmt.setString(3, place);
            pstmt.setString(4, station);
            pstmt.setFloat(5, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete_max_temperature(String date) {
        String sql = "DELETE FROM Max_temperature WHERE date = '" + date + "'";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete_min_temperature(String date) {
        String sql = "DELETE FROM Min_temperature WHERE date = '" + date + "'";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

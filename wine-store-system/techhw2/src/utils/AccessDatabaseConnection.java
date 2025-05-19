package utils;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class AccessDatabaseConnection {

    public static Connection getConnection() {
        try {
            // JDBC URL for UCanAccess
            String jdbcUrl = "jdbc:ucanaccess://src/resources/Cheers_System1.accdb;COLUMNORDER=DISPLAY";
            // Establish connection
            Connection connection = DriverManager.getConnection(jdbcUrl);
           // System.out.println("Connected to Access database successfully.");
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

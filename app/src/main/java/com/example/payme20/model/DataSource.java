package com.example.payme20.model;

import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.lang.Object.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import android.database.sqlite.*;


public class DataSource {

    private static final String DB_PATH = "members.db";

    public Connection connection() throws SQLException {
        Connection connection = null;
        String url = "jdbc:sqlite:" + DB_PATH;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection successful");

        }catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return connection;
    }

}

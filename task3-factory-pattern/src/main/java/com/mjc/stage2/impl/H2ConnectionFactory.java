package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    @Override
    public Connection createConnection() {
        Connection conn = null;
        try {
            Properties appProps = new Properties();
            appProps.load(H2ConnectionFactory.class.getClassLoader().getResourceAsStream("h2database.properties"));

            String driver = appProps.getProperty("jdbc_driver");
            String url = appProps.getProperty("db_url");
            String user = appProps.getProperty("user");
            String password = appProps.getProperty("password");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}


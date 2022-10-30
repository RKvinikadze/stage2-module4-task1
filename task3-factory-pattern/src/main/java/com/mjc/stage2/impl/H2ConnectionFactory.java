package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    @Override
    public Connection createConnection() {
        Connection conn = null;
        try {
            String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
            String appConfigPath = rootPath + "h2database.properties";

            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));

            String driver= appProps.getProperty("postgres.driver");
            String url = appProps.getProperty("postgres.url");
            String user = appProps.getProperty("postgres.password");
            String password = appProps.getProperty("postgres.name");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}


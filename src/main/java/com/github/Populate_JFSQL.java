package com.github;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Properties;
import org.apache.commons.io.FileUtils;

public class Populate_JFSQL {

    public static void main(final String[] args) throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty("persistence", "json");
        try (final Connection connection = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING, properties);
            final Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            final Collection<File> files = FileUtils.listFiles(new File(Constants.SCRIPTS_FOLDER), new String[]{"sql"},
                false);
            for (final File file : files) {
                try (final BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        statement.execute(line);
                    }
                } catch (final Exception e) {
                    System.out.println("Error executing SQL script: " + e.getMessage());
                }
            }
            connection.commit();
        }
    }
}

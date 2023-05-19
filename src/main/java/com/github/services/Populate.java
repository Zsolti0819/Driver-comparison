package com.github.services;

import com.github.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Populate {

    public void populate() {
        final File file = new File(
            Constants.SCRIPTS_FOLDER + File.separator + "populate.sql");
        Arrays.stream(Constants.CONNECTION_STRINGS).forEach(connectionString -> {
            try (final Connection connection = DriverManager.getConnection(connectionString);
                final Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                final long startTime = System.nanoTime();
                try (final BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        statement.execute(line);
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                }

                connection.commit();
                final long endTime = System.nanoTime() - startTime;
                System.out.println(
                    "Populated the database " + connectionString + " duration: " + endTime / 1000000 + "ms");
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

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
public class Update {

    public void update() {
        final File file = new File(
            Constants.SCRIPTS_FOLDER + File.separator + "updates.sql");
        Arrays.stream(Constants.CONNECTION_STRINGS).forEach(connectionString -> {
            try (final Connection connection = DriverManager.getConnection(connectionString);
                final Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                final long startTime = System.nanoTime();
                int i = 0;
                while (i != 10000) {
                    try (final BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            statement.execute(line);
                            i++;
                        }
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
                connection.commit();
                final long endTime = System.nanoTime() - startTime;
                System.out.println(
                    "Executed 10 000 UPDATE statements on " + connectionString + " - Duration: " + endTime / 1000000
                        + "ms");
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

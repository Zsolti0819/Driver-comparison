package com.github.comparisons;

import com.github.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Stream;

public class Update {

    public static void main(final String[] args) {
        final Stream<String> connectionStringStream = Arrays.stream(Constants.CONNECTION_STRINGS);
        final File file = new File(
            Constants.SCRIPTS_FOLDER + File.separator + "update" + File.separator + "updates.sql");
        connectionStringStream.forEach(connectionString -> {
            System.out.println("Currently using: " + connectionString);
            try (final Connection connection = DriverManager.getConnection(connectionString);
                final Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                final long startTime = System.nanoTime();
                int i = 0;
                while (i != 10000) {
                    final long loopStartTime = System.nanoTime();
                    try (final BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            statement.execute(line);
                            i++;
                        }
                    } catch (final Exception e) {
                        System.out.println("Error executing SQL script: " + e.getMessage());
                    }
                    final long loopEndTime = System.nanoTime() - loopStartTime;
                    System.out.println("After " + i + " statements executed, loop time: " + loopEndTime / 10000000 + "ms");
                }
                connection.commit();
                final long endTime = System.nanoTime() - startTime;
                System.out.println(connectionString + " duration: " + endTime / 10000000 + "ms");
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

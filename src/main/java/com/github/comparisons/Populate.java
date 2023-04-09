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
import java.util.Collection;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;

public class Populate {

    public static void main(final String[] args) {
        final Stream<String> connectionStringStream = Arrays.stream(Constants.CONNECTION_STRINGS);
        connectionStringStream.forEach(connectionString -> {
            System.out.println("Currently using: " + connectionString);
            try (final Connection connection = DriverManager.getConnection(connectionString);
                final Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                final Collection<File> files = FileUtils.listFiles(
                    new File(Constants.SCRIPTS_FOLDER + File.separator + "populate"), new String[]{"sql"},
                    false);
                final long startTime = System.nanoTime();
                for (final File file : files) {
                    try (final BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            statement.execute(line);
                        }
                    } catch (final Exception e) {
                        System.out.println("Error executing SQL script: " + e.getMessage());
                    }
                }
                connection.commit();
                final long endTime = System.nanoTime() - startTime;
                System.out.println("duration: " + endTime / 10000000 + "ms");
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

package com.github.jfsql;

import com.github.Constants;
import com.github.util.ResultSetPrinter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

public class Select {

    public static void main(final String[] args) {
        try (final Connection connection = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
            final Statement statement = connection.createStatement()) {
            final Collection<File> files = FileUtils.listFiles(new File(Constants.SCRIPTS_FOLDER + File.separator + "select"), new String[]{"sql"},
                false);
            for (final File file : files) {
                try (final BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        final long startTime = System.nanoTime();
                        System.out.println(line);
                        statement.execute(line);
                        final long endTime = System.nanoTime() - startTime;
                        System.out.println("duration: " + endTime / 10000000 + "ms");
                    }
                } catch (final Exception e) {
                    System.out.println("Error executing SQL script: " + e.getMessage());
                }
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}

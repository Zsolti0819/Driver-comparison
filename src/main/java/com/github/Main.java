package com.github;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    private static final String INSERT_TABLE = "myTable";
    private static final String[] INSERT_STATEMENTS = new String[] {
        "INSERT INTO myTable (col1, col2) VALUES (1, 'value1')",
        "INSERT INTO myTable (col1, col2) VALUES (2, 'value2')",
        // add more insert statements here...
    };

    private static final String[] UPDATE_STATEMENTS = new String[] {
        "UPDATE myTable SET col2 = 'new_value1' WHERE col1 = 1",
        "UPDATE myTable SET col2 = 'new_value2' WHERE col1 = 2",
        // add more update statements here...
    };

    public static void main(String[] args) {
        // execute some initial insert statements
        insertInitialData();

        // execute the insert and update statements in parallel
        executeParallel(Arrays.stream(INSERT_STATEMENTS), Arrays.stream(UPDATE_STATEMENTS));
    }

    private static void insertInitialData() {
        try (Connection conn = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
            Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE myTable (col1 INTEGER, col2 TEXT)");
            for (int i = 1; i <= 50; i++) {
                String insertStmt = "INSERT INTO " + INSERT_TABLE + " (col1, col2) VALUES (" + i + ", 'initial_value')";
                stmt.executeUpdate(insertStmt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeParallel(Stream<String> insertStream, Stream<String> updateStream) {
        try (Connection conn = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
            Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            // execute the insert statements in parallel
            insertStream.parallel().forEach(insertStmt -> {
                try {
                    stmt.executeUpdate(insertStmt);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            // execute the update statements in parallel
            updateStream.parallel().forEach(updateStmt -> {
                try {
                    stmt.executeUpdate(updateStmt);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

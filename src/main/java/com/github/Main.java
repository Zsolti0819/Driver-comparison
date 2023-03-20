package com.github;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    private static final String[] INSERT_STATEMENTS = new String[]{
        "INSERT INTO myTable (col1, col2) VALUES (51, 'value51')",
        "INSERT INTO myTable (col1, col2) VALUES (52, 'value52')",
    };

    private static final String[] UPDATE_STATEMENTS = new String[]{
        "UPDATE myTable SET col2 = 'new_value1' WHERE col1 = 1",
        "UPDATE myTable SET col2 = 'new_value2' WHERE col1 = 2",
    };

    public static void main(final String[] args) {
        try (final Connection conn = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
            final Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS myTable");
            stmt.execute("CREATE TABLE myTable (col1 INTEGER, col2 TEXT)");
            conn.setAutoCommit(false);
            for (int i = 1; i <= 50; i++) {
                final String insertStmt =
                    "INSERT INTO myTable (col1, col2) VALUES (" + i + ", 'initial_value')";
                stmt.executeUpdate(insertStmt);
                conn.commit();
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }

        // execute the insert and update statements in parallel
        executeParallel(Arrays.stream(INSERT_STATEMENTS), Arrays.stream(UPDATE_STATEMENTS));
    }

    private static void executeParallel(final Stream<String> insertStream, final Stream<String> updateStream) {
        try (final Connection conn = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
            final Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            // execute the insert statements in parallel
            insertStream.parallel().forEach(insertStmt -> {
                try {
                    stmt.executeUpdate(insertStmt);
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            });

            // execute the update statements in parallel
            updateStream.parallel().forEach(updateStmt -> {
                try {
                    stmt.executeUpdate(updateStmt);
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            });

            conn.commit();
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}

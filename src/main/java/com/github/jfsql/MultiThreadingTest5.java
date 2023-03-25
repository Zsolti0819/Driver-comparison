package com.github.jfsql;

import com.github.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiThreadingTest5 {

    public static void main(final String[] args) {

        try (final Connection connection = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING)) {
            connection.setAutoCommit(false);
            createTable(connection);
            insertInitialData(connection);
            connection.commit();
        } catch (final SQLException e) {
            e.printStackTrace();
            return;
        }

        final Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            final int threadNumber = i + 1;
            threads[i] = new Thread(() -> {
                try (final Connection connection = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING)) {
                    System.out.println("Thread " + threadNumber + " connected to database");
                    if (threadNumber % 2 == 0) {
                        insertStatements(connection, threadNumber);
                    } else {
                        executeUpdates(connection, threadNumber);
                    }
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        for (final Thread thread : threads) {
            thread.start();
        }

        for (final Thread thread : threads) {
            try {
                thread.join();
            } catch (final InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }

    private static void createTable(final Connection connection) throws SQLException {
        try (final Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS myTable");
            statement.executeUpdate("CREATE TABLE myTable (id TEXT, threadId TEXT)");
        }
        System.out.println("Table myTable created");
    }

    private static void insertInitialData(final Connection connection) throws SQLException {
        final String sql = "INSERT INTO myTable (id, threadId) VALUES (?, ?)";
        try (final PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < 50; i++) {
                statement.setString(1, "Id" + i);
                statement.setString(2, "InitialData");
                statement.executeUpdate();
            }
        }
    }

    private static void insertStatements(final Connection connection, final int threadNumber) throws SQLException {
        for (int i = 50 + threadNumber; i < 60 + threadNumber; i++) {
            try (final Statement statement = connection.createStatement()) {
                final String sql =
                    "INSERT INTO myTable (id, threadId) VALUES ('Id" + i + "', 'Thread" + threadNumber + "')";
                statement.executeUpdate(sql);
                System.out.println("Thread " + Thread.currentThread().getName() + " executing: " + sql);
                System.out.println("Thread " + Thread.currentThread().getName() + " inserted row " + i);
            }
        }
    }

    private static void executeUpdates(final Connection connection, final int threadNumber) throws SQLException {
        for (int i = 0; i < 50; i++) {
            try (final Statement statement = connection.createStatement()) {
                final String sql =
                    "UPDATE myTable SET threadId = 'ThreadIdEdited" + threadNumber + "' WHERE id = 'Id" + i + "'";
                statement.execute(sql);
                System.out.println("Thread " + Thread.currentThread().getName() + " executing: " + sql);
                System.out.println("Thread " + Thread.currentThread().getName() + " updated row " + i);
            }
        }
    }
}


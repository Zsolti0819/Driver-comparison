package com.github.jfsql;

import com.github.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This case tests parallel insert when there is conflict between tables. Inserts committed at once (it's a transaction)
 * per thread. 9 out of 10 threads will be stopped due PessimisticLockException, and only one thread's inserts will be
 * persisted and committed.
 */
public class MultiThreadingTest3 {

    private static final int NUM_THREADS = 10;

    public static void main(final String[] args) throws Exception {
        final Connection[] connections = new Connection[NUM_THREADS];
        final Statement[] statements = new Statement[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            connections[i] = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
            statements[i] = connections[i].createStatement();
            if (i == 0) {
                statements[i].execute("DROP TABLE IF EXISTS myTable");
                statements[i].execute("CREATE TABLE myTable (id TEXT, threadId TEXT)");
            }
        }

        // Spawn multiple threads to execute database operations
        final Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new DatabaseWorker(connections[i]));
        }

        // Wait for all threads to finish
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        // Close all database connections
        for (int i = 0; i < NUM_THREADS; i++) {
            connections[i].close();
        }
    }

    private static class DatabaseWorker implements Runnable {

        private final Connection connection;
        private final Statement statement;

        public DatabaseWorker(final Connection connection) throws SQLException {
            this.connection = connection;
            statement = connection.createStatement();
        }

        @Override
        public void run() {
            try {
                connection.setAutoCommit(false);
                final long threadId = Thread.currentThread().getId();
                for (int i = 1; i <= 10; i++) {
                    statement.execute(
                        "INSERT INTO myTable (id, threadId) VALUES (" + i + ", " + threadId + ")");
                }
                connection.commit();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

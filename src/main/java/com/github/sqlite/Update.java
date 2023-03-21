package com.github.sqlite;

import com.github.Constants;
import com.github.ResultSetPrinter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {

    public static void main(final String[] args) {
        final long startTime = System.nanoTime();
        try (final Connection connection = DriverManager.getConnection(Constants.SQLITE_CONNECTION_STRING);
            final Statement statement = connection.createStatement()) {
            ResultSetPrinter.printResultSet(update1(statement));
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        final long endTime = System.nanoTime() - startTime;
        System.out.println("duration: " + endTime / 1000000000 + "s");
    }

    private static ResultSet update1(final Statement statement) throws SQLException {
        for (int i = 0; i < 100; i++) {
            statement.execute("UPDATE Car SET color = 'blue' WHERE car_id = " + i);
        }
        return statement.executeQuery("SELECT * FROM Car WHERE car_id <= 100");
    }

}

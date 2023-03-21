package com.github.jfsql;

import com.github.Constants;
import com.github.ResultSetPrinter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

    public static void main(final String[] args) {
        final long startTime = System.nanoTime();
        try (final Connection connection = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
            final Statement statement = connection.createStatement()) {
            ResultSetPrinter.printResultSet(select1(statement));
            ResultSetPrinter.printResultSet(select2(statement));
            ResultSetPrinter.printResultSet(select3(statement));
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        final long endTime = System.nanoTime() - startTime;
        System.out.println("duration: " + endTime / 1000000000 + "s");
    }

    private static ResultSet select1(final Statement statement) throws SQLException {
        return statement.executeQuery(
            "SELECT * FROM Car INNER JOIN Sales ON Car.car_id = Sales.car_id INNER JOIN Owner ON Sales.owner_id = Owner.owner_id WHERE first_name = 'Nick' AND last_name = 'Mertz';");
    }

    private static ResultSet select2(final Statement statement) throws SQLException {
        return statement.executeQuery(
            "SELECT Dealership.dealership_id, email, phone_number, address, sale_id, Sales.car_id, owner_id, Sales.dealership_id, sale_date, sale_price, Car.car_id, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors FROM Dealership INNER JOIN Sales ON Dealership.dealership_id = Sales.dealership_id INNER JOIN Car ON Sales.car_id = Car.car_id WHERE make = 'Toyota' AND model = 'Supra';\n");
    }

    private static ResultSet select3(final Statement statement) throws SQLException {
        return statement.executeQuery(
            "SELECT Car.car_id, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors, sale_id, Sales.car_id, owner_id, dealership_id, sale_date, sale_price FROM Car LEFT OUTER JOIN Sales ON Car.car_id = Sales.car_id WHERE Sales.car_id = 1;\n");
    }
}

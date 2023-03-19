package com.github;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Select_SQLite {

    public static void main(final String[] args) {
        final long startTime = System.nanoTime();
        try (final Connection connection = DriverManager.getConnection(Constants.SQLITE_CONNECTION_STRING);
            final Statement statement = connection.createStatement()) {
            select1(statement);
            select2(statement);
            select3(statement);
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        final long endTime = System.nanoTime() - startTime;
        System.out.println("duration: " + endTime / 1000000000 + "s");
    }

    private static void select1(final Statement statement) throws SQLException {
        final ResultSet resultSet = statement.executeQuery(
            "SELECT * FROM Car INNER JOIN Sales ON Car.car_id = Sales.car_id INNER JOIN Owner ON Sales.owner_id = Owner.owner_id WHERE first_name = 'Nick' AND last_name = 'Mertz' ORDER BY Car.car_id ASC;");
        final List<Integer> carIdsFromCars = new ArrayList<>();
        final List<String> makes = new ArrayList<>();
        final List<String> models = new ArrayList<>();
        final List<Integer> years = new ArrayList<>();
        final List<String> colors = new ArrayList<>();
        final List<String> transmissionTypes = new ArrayList<>();
        final List<String> fuelTypes = new ArrayList<>();
        final List<Double> engineSizes = new ArrayList<>();
        final List<Integer> numberOfDoors = new ArrayList<>();
        final List<Integer> salesIds = new ArrayList<>();
        final List<Integer> carIdsFromSales = new ArrayList<>();
        final List<Integer> ownerIdsFromSales = new ArrayList<>();
        final List<Integer> dealershipIds = new ArrayList<>();
        final List<String> salesDates = new ArrayList<>();
        final List<Double> salesPrices = new ArrayList<>();
        final List<Integer> ownerIdsFromOwner = new ArrayList<>();
        final List<String> firstNames = new ArrayList<>();
        final List<String> lastNames = new ArrayList<>();
        final List<String> emails = new ArrayList<>();
        final List<String> phoneNumbers = new ArrayList<>();
        final List<String> addresses = new ArrayList<>();
        while (resultSet.next()) {
            carIdsFromCars.add(resultSet.getInt("car_id"));
            makes.add(resultSet.getString("make"));
            models.add(resultSet.getString("model"));
            years.add(resultSet.getInt("year"));
            colors.add(resultSet.getString("color"));
            transmissionTypes.add(resultSet.getString("transmission_type"));
            fuelTypes.add(resultSet.getString("fuel_type"));
            engineSizes.add(resultSet.getDouble("engine_size"));
            numberOfDoors.add(resultSet.getInt("number_of_doors"));
            salesIds.add(resultSet.getInt("sale_id"));
            carIdsFromSales.add(resultSet.getInt("car_id"));
            ownerIdsFromSales.add(resultSet.getInt("owner_id"));
            dealershipIds.add(resultSet.getInt("dealership_id"));
            salesDates.add(resultSet.getString("sale_date"));
            salesPrices.add(resultSet.getDouble("sale_price"));
            ownerIdsFromOwner.add(resultSet.getInt("owner_id"));
            firstNames.add(resultSet.getString("first_name"));
            lastNames.add(resultSet.getString("last_name"));
            emails.add(resultSet.getString("email"));
            phoneNumbers.add(resultSet.getString("phone_number"));
            addresses.add(resultSet.getString("address"));
        }

        System.out.println("carIdsFromCars = " + carIdsFromCars);
        System.out.println("makes = " + makes);
        System.out.println("models = " + models);
        System.out.println("years = " + years);
        System.out.println("colors = " + colors);
        System.out.println("transmissionTypes = " + transmissionTypes);
        System.out.println("fuelTypes = " + fuelTypes);
        System.out.println("engineSizes = " + engineSizes);
        System.out.println("numberOfDoors = " + numberOfDoors);
        System.out.println("salesIds = " + salesIds);
        System.out.println("carIdsFromSales = " + carIdsFromSales);
        System.out.println("ownerIdsFromSales = " + ownerIdsFromSales);
        System.out.println("dealershipIds = " + dealershipIds);
        System.out.println("salesDates = " + salesDates);
        System.out.println("salesPrices = " + salesPrices);
        System.out.println("ownerIdsFromOwner = " + ownerIdsFromOwner);
        System.out.println("firstNames = " + firstNames);
        System.out.println("lastNames = " + lastNames);
        System.out.println("emails = " + emails);
        System.out.println("phoneNumbers = " + phoneNumbers);
        System.out.println("addresses = " + addresses);
    }

    private static void select2(final Statement statement) throws SQLException {
        final ResultSet resultSet = statement.executeQuery(
            "SELECT Dealership.dealership_id as d_id1, email, phone_number, address, sale_id, Sales.car_id AS c_id1, owner_id, Sales.dealership_id AS d_id2, sale_date, sale_price, Car.car_id AS c_id2, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors FROM Dealership INNER JOIN Sales ON Dealership.dealership_id = Sales.dealership_id INNER JOIN Car ON Sales.car_id = Car.car_id WHERE make = 'Toyota' AND model = 'Supra';\n");
        final List<Integer> carIdsFromCars = new ArrayList<>();
        final List<String> makes = new ArrayList<>();
        final List<String> models = new ArrayList<>();
        final List<Integer> years = new ArrayList<>();
        final List<String> colors = new ArrayList<>();
        final List<String> transmissionTypes = new ArrayList<>();
        final List<String> fuelTypes = new ArrayList<>();
        final List<Double> engineSizes = new ArrayList<>();
        final List<Integer> numberOfDoors = new ArrayList<>();
        final List<Integer> salesIds = new ArrayList<>();
        final List<Integer> carIdsFromSales = new ArrayList<>();
        final List<Integer> ownerIdsFromSales = new ArrayList<>();
        final List<Integer> dealershipIds = new ArrayList<>();
        final List<Integer> dealershipIdsFromSales = new ArrayList<>();
        final List<String> salesDates = new ArrayList<>();
        final List<Double> salesPrices = new ArrayList<>();
        final List<String> emails = new ArrayList<>();
        final List<String> phoneNumbers = new ArrayList<>();
        final List<String> addresses = new ArrayList<>();
        while (resultSet.next()) {
            dealershipIds.add(resultSet.getInt("d_id1"));
            emails.add(resultSet.getString("email"));
            phoneNumbers.add(resultSet.getString("phone_number"));
            addresses.add(resultSet.getString("address"));
            salesIds.add(resultSet.getInt("sale_id"));
            carIdsFromSales.add(resultSet.getInt("c_id1"));
            ownerIdsFromSales.add(resultSet.getInt("owner_id"));
            dealershipIdsFromSales.add(resultSet.getInt("d_id2"));
            salesDates.add(resultSet.getString("sale_date"));
            salesPrices.add(resultSet.getDouble("sale_price"));
            carIdsFromCars.add(resultSet.getInt("c_id2"));
            makes.add(resultSet.getString("make"));
            models.add(resultSet.getString("model"));
            years.add(resultSet.getInt("year"));
            colors.add(resultSet.getString("color"));
            transmissionTypes.add(resultSet.getString("transmission_type"));
            fuelTypes.add(resultSet.getString("fuel_type"));
            engineSizes.add(resultSet.getDouble("engine_size"));
            numberOfDoors.add(resultSet.getInt("number_of_doors"));
        }

        System.out.println("dealershipIds = " + dealershipIds);
        System.out.println("emails = " + emails);
        System.out.println("phoneNumbers = " + phoneNumbers);
        System.out.println("addresses = " + addresses);
        System.out.println("salesIds = " + salesIds);
        System.out.println("carIdsFromSales = " + carIdsFromSales);
        System.out.println("ownerIdsFromSales = " + ownerIdsFromSales);
        System.out.println("dealershipIdsFromSales = " + dealershipIdsFromSales);
        System.out.println("salesDates = " + salesDates);
        System.out.println("salesPrices = " + salesPrices);
        System.out.println("carIdsFromCars = " + carIdsFromCars);
        System.out.println("makes = " + makes);
        System.out.println("models = " + models);
        System.out.println("years = " + years);
        System.out.println("colors = " + colors);
        System.out.println("transmissionTypes = " + transmissionTypes);
        System.out.println("fuelTypes = " + fuelTypes);
        System.out.println("engineSizes = " + engineSizes);
        System.out.println("numberOfDoors = " + numberOfDoors);
    }

    private static void select3(final Statement statement) throws SQLException {
        final ResultSet resultSet = statement.executeQuery(
            "SELECT Car.car_id AS c_id1, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors, sale_id, Sales.car_id AS c_id2, owner_id, dealership_id, sale_date, sale_price FROM Car LEFT OUTER JOIN Sales ON Car.car_id = Sales.car_id WHERE Sales.car_id = 1;\n");

        final List<Integer> carIdsFromCars = new ArrayList<>();
        final List<String> makes = new ArrayList<>();
        final List<String> models = new ArrayList<>();
        final List<Integer> years = new ArrayList<>();
        final List<String> colors = new ArrayList<>();
        final List<String> transmissionTypes = new ArrayList<>();
        final List<String> fuelTypes = new ArrayList<>();
        final List<Double> engineSizes = new ArrayList<>();
        final List<Integer> numberOfDoors = new ArrayList<>();
        final List<Integer> carIdsFromSales = new ArrayList<>();
        final List<Integer> ownerIdsFromSales = new ArrayList<>();
        final List<Integer> dealershipIds = new ArrayList<>();
        final List<String> salesDates = new ArrayList<>();
        final List<Double> salesPrices = new ArrayList<>();

        while (resultSet.next()) {
            carIdsFromCars.add(resultSet.getInt("c_id1"));
            makes.add(resultSet.getString("make"));
            models.add(resultSet.getString("model"));
            years.add(resultSet.getInt("year"));
            colors.add(resultSet.getString("color"));
            transmissionTypes.add(resultSet.getString("transmission_type"));
            fuelTypes.add(resultSet.getString("fuel_type"));
            engineSizes.add(resultSet.getDouble("engine_size"));
            numberOfDoors.add(resultSet.getInt("number_of_doors"));
            carIdsFromSales.add(resultSet.getInt("c_id2"));
            ownerIdsFromSales.add(resultSet.getInt("owner_id"));
            dealershipIds.add(resultSet.getInt("dealership_id"));
            salesDates.add(resultSet.getString("sale_date"));
            salesPrices.add(resultSet.getDouble("sale_price"));
        }

        System.out.println("carIdsFromCars = " + carIdsFromCars);
        System.out.println("makes = " + makes);
        System.out.println("models = " + models);
        System.out.println("years = " + years);
        System.out.println("colors = " + colors);
        System.out.println("transmissionTypes = " + transmissionTypes);
        System.out.println("fuelTypes = " + fuelTypes);
        System.out.println("engineSizes = " + engineSizes);
        System.out.println("numberOfDoors = " + numberOfDoors);
        System.out.println("carIdsFromSales = " + carIdsFromSales);
        System.out.println("ownerIdsFromSales = " + ownerIdsFromSales);
        System.out.println("dealershipIds = " + dealershipIds);
        System.out.println("salesDates = " + salesDates);
        System.out.println("salesPrices = " + salesPrices);
    }
}

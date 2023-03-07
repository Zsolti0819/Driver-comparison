package com.github;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select2_JFSQL {

    public static void main(final String[] args) {
        try (final Connection connection = DriverManager.getConnection(Constants.JFSQL_CONNECTION_STRING);
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT Dealership.dealership_id, email, phone_number, address, sale_id, Sales.car_id, owner_id, Sales.dealership_id, sale_date, sale_price, Car.car_id, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors FROM Dealership INNER JOIN Sales ON Dealership.dealership_id = Sales.dealership_id INNER JOIN Car ON Sales.car_id = Car.car_id WHERE make = 'Toyota' AND model = 'Supra';\n");
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
                dealershipIds.add(resultSet.getInt("Dealership.dealership_id"));
                emails.add(resultSet.getString("email"));
                phoneNumbers.add(resultSet.getString("phone_number"));
                addresses.add(resultSet.getString("address"));
                salesIds.add(resultSet.getInt("sale_id"));
                carIdsFromSales.add(resultSet.getInt("Sales.car_id"));
                ownerIdsFromSales.add(resultSet.getInt("owner_id"));
                dealershipIdsFromSales.add(resultSet.getInt("Sales.dealership_id"));
                salesDates.add(resultSet.getString("sale_date"));
                salesPrices.add(resultSet.getDouble("sale_price"));
                carIdsFromCars.add(resultSet.getInt("Car.car_id"));
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

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}

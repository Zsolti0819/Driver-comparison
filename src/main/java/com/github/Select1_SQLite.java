package com.github;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select1_SQLite {

    public static void main(final String[] args) {
        try (final Connection connection = DriverManager.getConnection(Constants.SQLITE_CONNECTION_STRING);
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Car INNER JOIN Sales ON Car.car_id = Sales.car_id INNER JOIN Owner ON Sales.owner_id = Owner.owner_id WHERE first_name = 'Nick' AND last_name = 'Mertz' ORDER BY Car.car_id ASC;");
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

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.github;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Select3_SQLite {

    public static void main(final String[] args) {
        try (final Connection connection = DriverManager.getConnection(Constants.SQLITE_CONNECTION_STRING);
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT Car.car_id AS c_id1, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors, sale_id, Sales.car_id AS c_id2, owner_id, dealership_id, sale_date, sale_price FROM Car LEFT OUTER JOIN Sales ON Car.car_id = Sales.car_id WHERE Sales.car_id = 1;\n");

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

        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
}

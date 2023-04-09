package com.github.util;

import com.github.javafaker.Faker;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SqlScriptGenerator {

    private static final List<String> CAR_MAKES = List.of("Toyota", "Ford", "Honda", "Chevrolet", "Nissan", "Jeep", "Subaru", "BMW", "Audi", "Mercedes-Benz", "Mazda", "Kia", "Hyundai", "Volkswagen", "Lexus", "Volvo", "Chrysler", "Dodge", "GMC");
    private static final List<String> TOYOTA_MODELS = List.of("Corolla", "Camry", "Prius", "Supra", "Tacoma", "Tundra");
    private static final List<String> FORD_MODELS = List.of("F-150", "Mustang", "Explorer", "Expedition", "Escape", "Focus");
    private static final List<String> HONDA_MODELS = List.of("Civic", "Accord", "Fit", "CR-V", "Odyssey", "Pilot");
    private static final List<String> CHEVROLET_MODELS = List.of("Silverado", "Equinox", "Malibu", "Traverse", "Camaro", "Corvette");
    private static final List<String> NISSAN_MODELS = List.of("Altima", "Sentra", "Maxima", "Rogue", "Pathfinder", "Frontier");
    private static final List<String> JEEP_MODELS = List.of("Grand Cherokee", "Wrangler", "Cherokee", "Compass", "Renegade", "Gladiator");
    private static final List<String> SUBARU_MODELS = List.of("Outback", "Impreza", "Legacy", "Crosstrek", "Ascent", "Forester");
    private static final List<String> BMW_MODELS = List.of("3 Series", "5 Series", "7 Series", "X1", "X5", "M3");
    private static final List<String> AUDI_MODELS = List.of("A4", "Q5", "A6", "Q7", "S5", "TT");
    private static final List<String> MERCEDES_MODELS = List.of("C-Class", "E-Class", "S-Class", "GLC", "GLE", "AMG GT");
    private static final List<String> MAZDA_MODELS = List.of("Mazda3", "Mazda6", "MX-5 Miata", "CX-30", "CX-5", "CX-9");
    private static final List<String> KIA_MODELS = List.of("Soul", "Forte", "Optima", "Seltos", "Sportage", "Stinger");
    private static final List<String> HYUNDAI_MODELS = List.of("Elantra", "Sonata", "Tucson", "Santa Fe", "Kona", "Venue");
    private static final List<String> VOLKSWAGEN_MODELS = List.of("Jetta", "Passat", "Tiguan", "Atlas", "Golf GTI", "Arteon");
    private static final List<String> LEXUS_MODELS = List.of("ES", "IS", "GS", "NX", "RX", "LC");
    private static final List<String> VOLVO_MODELS = List.of("S60", "S90", "V60", "V90", "XC40", "XC90");
    private static final List<String> CHRYSLER_MODELS = List.of("300", "Pacifica", "Voyager", "Challenger", "Charger", "Durango");
    private static final List<String> DODGE_MODELS = List.of("Challenger", "Charger", "Ram 1500", "Ram 2500", "Ram 3500", "Journey");
    private static final List<String> GMC_MODELS = List.of("1500", "2500", "3500", "Sierra 1500", "Sierra 2500HD", "Canyon");

    private static final Faker FAKER = new Faker();

    // 10 000 cars, 20 dealerships, 5 000 owners, 10 000 services, 8 000 sales
    private static String insertIntoCars(final int index) {
        final String make = CAR_MAKES.get(FAKER.random().nextInt(0, CAR_MAKES.size() - 1));
        final String model;
        switch (make) {
            case "Toyota":
                model = TOYOTA_MODELS.get(FAKER.random().nextInt(0, TOYOTA_MODELS.size() - 1));
                break;
            case "Ford":
                model = FORD_MODELS.get(FAKER.random().nextInt(0, FORD_MODELS.size() - 1));
                break;
            case "Honda":
                model = HONDA_MODELS.get(FAKER.random().nextInt(0, HONDA_MODELS.size() - 1));
                break;
            case "Chevrolet":
                model = CHEVROLET_MODELS.get(FAKER.random().nextInt(0, CHEVROLET_MODELS.size() - 1));
                break;
            case "Nissan":
                model = NISSAN_MODELS.get(FAKER.random().nextInt(0, NISSAN_MODELS.size() - 1));
                break;
            case "Jeep":
                model = JEEP_MODELS.get(FAKER.random().nextInt(0, JEEP_MODELS.size() - 1));
                break;
            case "Subaru":
                model = SUBARU_MODELS.get(FAKER.random().nextInt(0, SUBARU_MODELS.size() - 1));
                break;
            case "BMW":
                model = BMW_MODELS.get(FAKER.random().nextInt(0, BMW_MODELS.size() - 1));
                break;
            case "Audi":
                model = AUDI_MODELS.get(FAKER.random().nextInt(0, AUDI_MODELS.size() - 1));
                break;
            case "Mercedes-Benz":
                model = MERCEDES_MODELS.get(FAKER.random().nextInt(0, MERCEDES_MODELS.size() - 1));
                break;
            case "Mazda":
                model = MAZDA_MODELS.get(FAKER.random().nextInt(0, MAZDA_MODELS.size() - 1));
                break;
            case "Kia":
                model = KIA_MODELS.get(FAKER.random().nextInt(0, KIA_MODELS.size() - 1));
                break;
            case "Hyundai":
                model = HYUNDAI_MODELS.get(FAKER.random().nextInt(0, HYUNDAI_MODELS.size() - 1));
                break;
            case "Volkswagen":
                model = VOLKSWAGEN_MODELS.get(FAKER.random().nextInt(0, VOLKSWAGEN_MODELS.size() - 1));
                break;
            case "Lexus":
                model = LEXUS_MODELS.get(FAKER.random().nextInt(0, LEXUS_MODELS.size() - 1));
                break;
            case "Volvo":
                model = VOLVO_MODELS.get(FAKER.random().nextInt(0, VOLVO_MODELS.size() - 1));
                break;
            case "Chrysler":
                model = CHRYSLER_MODELS.get(FAKER.random().nextInt(0, CHRYSLER_MODELS.size() - 1));
                break;
            case "Dodge":
                model = DODGE_MODELS.get(FAKER.random().nextInt(0, DODGE_MODELS.size() - 1));
                break;
            case "GMC":
                model = GMC_MODELS.get(FAKER.random().nextInt(0, GMC_MODELS.size() - 1));
                break;
            default:
                throw new IllegalStateException("Unexpected car make");

        }
        final int year = FAKER.number().numberBetween(1990, 2023);
        final String color = FAKER.color().name();
        final String transmissionType = FAKER.options().option("Automatic", "Manual");
        final String fuelType = FAKER.options().option("Gasoline", "Diesel", "Electric");
        final double engineSize = FAKER.number().randomDouble(1, (int) 1.0, (int) 5.0);
        final int numberOfDoors = FAKER.number().numberBetween(2, 5);

        final String sql = String.format("INSERT INTO Car (car_id, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors) VALUES (%d, '%s', '%s', %d, '%s', '%s', '%s', %.1f, %d);",
                index, make, model, year, color, transmissionType, fuelType, engineSize, numberOfDoors);

        System.out.println(sql);
        return sql;
    }

    private static void createCarScript() {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/scripts/cars.sql"))) {
            for (int i = 0; i < 10000; i++) {
                if (i == 0) {
                    writer.write("DROP TABLE IF EXISTS Car;\n" +
                            "CREATE TABLE Car (car_id INTEGER NOT NULL, make TEXT NOT NULL, model TEXT NOT NULL, year INTEGER NOT NULL, color TEXT NOT NULL, transmission_type TEXT NOT NULL, fuel_type TEXT NOT NULL, engine_size REAL NOT NULL, number_of_doors INTEGER NOT NULL);\n");
                }
                final String insertStatement = insertIntoCars(i);
                writer.write(insertStatement + "\n");
            }
        } catch (final IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static String insertIntoOwners(final int index) {
        final String firstName = FAKER.name().firstName().replace("'", " ");
        final String lastName = FAKER.name().lastName().replace("'", " ");
        final String email = FAKER.internet().emailAddress();
        final String phone = FAKER.phoneNumber().phoneNumber();
        final String address = FAKER.address().fullAddress().replace("'", " ");
        final String sql = String.format("INSERT INTO Owner (owner_id, first_name, last_name, email, phone_number, address) VALUES (%d, '%s', '%s', '%s', '%s', '%s');", index, firstName, lastName, email, phone, address);
        System.out.println(sql);
        return sql;

    }

    private static void createOwnerScript() {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/scripts/owner.sql"))) {
            for (int i = 0; i < 5000; i++) {
                if (i == 0) {
                    writer.write("DROP TABLE IF EXISTS Owner;\n" +
                            "CREATE TABLE Owner (owner_id INTEGER NOT NULL, first_name TEXT NOT NULL, last_name TEXT NOT NULL, email TEXT NOT NULL, phone_number TEXT NOT NULL, address TEXT NOT NULL);\n");
                }
                final String insertStatement = insertIntoOwners(i);
                writer.write(insertStatement + "\n");
            }
        } catch (final IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static String insertIntoDealership(final int index) {
        final String name = FAKER.company().name().replace("'", " ");
        final String email = FAKER.internet().emailAddress();
        final String phone = FAKER.phoneNumber().phoneNumber();
        final String address = FAKER.address().fullAddress().replace("'", " ");
        final String sql = String.format("INSERT INTO Dealership (dealership_id, name, email, phone_number, address) VALUES (%d, '%s', '%s', '%s', '%s');", index, name, email, phone, address);
        System.out.println(sql);
        return sql;
    }

    private static void createDealershipScript() {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/scripts/dealership.sql"))) {
            for (int i = 0; i < 20; i++) {
                if (i == 0) {
                    writer.write("DROP TABLE IF EXISTS Dealership;\n" +
                            "CREATE TABLE Dealership (dealership_id INTEGER NOT NULL, name TEXT NOT NULL, email TEXT NOT NULL, phone_number TEXT NOT NULL, address TEXT NOT NULL);\n");
                }
                final String insertStatement = insertIntoDealership(i);
                writer.write(insertStatement + "\n");
            }
        } catch (final IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static String insertIntoSales(final int index) {
        final int carId = FAKER.number().numberBetween(0, 10000);
        final int dealershipId = FAKER.number().numberBetween(0, 20);
        final int ownerId = FAKER.number().numberBetween(0, 5000);
        final String salesDate = FAKER.date().between(Faker.instance().date().past(365 * 2, TimeUnit.DAYS), Faker.instance().date().future(365 * 2, TimeUnit.DAYS)).toString();
        final double salePrice = FAKER.number().randomDouble(2, 5000, 50000);
        final String sql = String.format("INSERT INTO Sales (sale_id, car_id, owner_id, dealership_id, sale_date, sale_price) VALUES (%d, %d, %d, %d, '%s', %.2f);", index, carId, dealershipId, ownerId, salesDate, salePrice);
        System.out.println(sql);
        return sql;
    }

    private static void createSalesScript() {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/scripts/sales.sql"))) {
            for (int i = 0; i < 8000; i++) {
                if (i == 0) {
                    writer.write("DROP TABLE IF EXISTS Sales;\n" +
                            "CREATE TABLE Sales (sale_id INTEGER NOT NULL, car_id INTEGER NOT NULL, owner_id INTEGER NOT NULL, dealership_id INTEGER NOT NULL, sale_date TEXT NOT NULL, sale_price REAL NOT NULL);\n");
                }
                final String insertStatement = insertIntoSales(i);
                writer.write(insertStatement + "\n");
            }
        } catch (final IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static String insertIntoService(final int index) {
        final int carId = Faker.instance().number().numberBetween(1, 10000);
        final int dealershipId = Faker.instance().number().numberBetween(0, 20);
        final String serviceDate = Faker.instance().date().past(3, TimeUnit.DAYS).toString();
        final String serviceType = Faker.instance().options().option("Oil Change", "Tire Rotation", "Brake Inspection");
        final String serviceDescription = Faker.instance().lorem().sentence(10);
        final double serviceCost = Faker.instance().number().randomDouble(2, 50, 500);

        final String sql = String.format("INSERT INTO Service (service_id, car_id, dealership_id, service_date, service_type, service_description, service_cost) " +
                        "VALUES (%d, %d, %d, '%s', '%s', '%s', %.2f);",
                index, carId, dealershipId, serviceDate, serviceType, serviceDescription, serviceCost);
        System.out.println(sql);
        return sql;

    }

    private static void createServiceScript() {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/scripts/service.sql"))) {
            for (int i = 0; i < 10000; i++) {
                if (i == 0) {
                    writer.write("DROP TABLE IF EXISTS Service;\n" +
                            "CREATE TABLE Service (service_id INTEGER NOT NULL, car_id INTEGER NOT NULL, dealership_id INTEGER NOT NULL, service_date TEXT NOT NULL, service_type TEXT NOT NULL, service_description TEXT NOT NULL, service_cost REAL NOT NULL);\n");
                }
                final String insertStatement = insertIntoService(i);
                writer.write(insertStatement + "\n");
            }
        } catch (final IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(final String[] args) throws IOException {

        Files.createDirectories(Path.of("src/main/resources/scripts/"));

        createCarScript();

        createDealershipScript();

        createOwnerScript();

        createSalesScript();

        createServiceScript();
    }
}

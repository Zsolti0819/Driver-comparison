DELETE FROM Car WHERE car_id < 1234;
DELETE FROM Car WHERE make = 'Toyota';
DELETE FROM Dealership WHERE dealership_id = 1;
DELETE FROM Dealership WHERE name LIKE '%Cole, Klocko and Schuster%';
DELETE FROM Owner WHERE owner_id = 685;
DELETE FROM Owner WHERE last_name = 'Smith';
DELETE FROM Sales WHERE sale_id = 456;
DELETE FROM Sales WHERE car_id = 1234;
DELETE FROM Service WHERE service_id = 789;
DELETE FROM Service WHERE dealership_id = 6;
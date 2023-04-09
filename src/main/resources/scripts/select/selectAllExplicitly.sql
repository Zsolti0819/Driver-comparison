SELECT car_id, make, model, year, color, transmission_type, fuel_type, engine_size, number_of_doors FROM Car;
SELECT dealership_id, name, email, phone_number, address FROM Dealership;
SELECT owner_id, first_name, last_name, email, phone_number, address FROM Owner;
SELECT sale_id, car_id, owner_id, dealership_id, sale_date, sale_price FROM Sales;
SELECT service_id, car_id, dealership_id, service_date, service_type, service_description, service_cost FROM Service;
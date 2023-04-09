SELECT * FROM Car INNER JOIN Sales ON Car.car_id = Sales.car_id;
SELECT * FROM Dealership INNER JOIN Sales ON Dealership.dealership_id = Sales.dealership_id;
SELECT * FROM Owner INNER JOIN Sales ON Owner.owner_id = Sales.owner_id;
SELECT * FROM Car INNER JOIN Service ON Car.car_id = Service.car_id;
SELECT * FROM Dealership INNER JOIN Service ON Dealership.dealership_id = Service.dealership_id;
SELECT * FROM Car INNER JOIN Sales ON Car.car_id = Sales.car_id INNER JOIN Owner ON Sales.owner_id = Owner.owner_id;
SELECT * FROM Car INNER JOIN Sales ON Car.car_id = Sales.car_id INNER JOIN Dealership ON Sales.dealership_id = Dealership.dealership_id;
SELECT * FROM Owner INNER JOIN Sales ON Owner.owner_id = Sales.owner_id INNER JOIN Dealership ON Sales.dealership_id = Dealership.dealership_id;
SELECT * FROM Car INNER JOIN Service ON Car.car_id = Service.car_id INNER JOIN Dealership ON Service.dealership_id = Dealership.dealership_id;
SELECT * FROM Car INNER JOIN Service ON Car.car_id = Service.car_id INNER JOIN Sales ON Car.car_id = Sales.car_id;
SELECT * FROM Car LEFT JOIN Sales ON Car.car_id = Sales.car_id;
SELECT * FROM Car LEFT JOIN Dealership ON Car.car_id = Dealership.dealership_id;
SELECT * FROM Car LEFT JOIN Owner ON Car.car_id = Owner.owner_id;
SELECT * FROM Car LEFT JOIN Service ON Car.car_id = Service.car_id;
SELECT * FROM Sales LEFT JOIN Car ON Sales.car_id = Car.car_id;
SELECT * FROM Sales LEFT JOIN Dealership ON Sales.dealership_id = Dealership.dealership_id;
SELECT * FROM Sales LEFT JOIN Owner ON Sales.owner_id = Owner.owner_id;
SELECT * FROM Sales LEFT JOIN Service ON Sales.car_id = Service.car_id;
SELECT * FROM Dealership LEFT JOIN Car ON Dealership.dealership_id = Car.car_id;
SELECT * FROM Service LEFT JOIN Dealership ON Service.dealership_id = Dealership.dealership_id;
SELECT * FROM Car WHERE car_id = 5000;
SELECT * FROM Car WHERE car_id < 5000;
SELECT * FROM Car WHERE car_id > 5000;
SELECT * FROM Dealership WHERE dealership_id = 10;
SELECT * FROM Dealership WHERE dealership_id < 10;
SELECT * FROM Dealership WHERE dealership_id > 10;
SELECT * FROM Owner WHERE owner_id = 2500;
SELECT * FROM Owner WHERE owner_id < 2500;
SELECT * FROM Owner WHERE owner_id > 2500;
SELECT * FROM Sales WHERE sale_id = 4000;
SELECT * FROM Sales WHERE sale_id < 4000;
SELECT * FROM Sales WHERE sale_id > 4000;
SELECT * FROM Service WHERE service_id = 5000;
SELECT * FROM Service WHERE service_id < 5000;
SELECT * FROM Service WHERE service_id > 5000;
SELECT * FROM Car WHERE make LIKE '%Audi%' AND color LIKE '%black%';
SELECT * FROM Dealership WHERE email LIKE '%@hotmail.com%';
SELECT * FROM Owner WHERE address LIKE '%North Marco%';
SELECT * FROM Sales WHERE sale_date LIKE '%Aug 19%';
SELECT * FROM Service WHERE service_type LIKE '%Brake Inspection%';

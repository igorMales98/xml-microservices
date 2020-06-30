/* brands */
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('1', 'Volvo', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('2', 'Saab', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('3', 'Mercedes Benz', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('4', 'BMW', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('5', 'Audi', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('6', 'Opel', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('7', 'Peugeot', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('8', 'Renault', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('9', 'Ford', b'0');
INSERT INTO `xml`.`car_brand` (`id`, `name`, `deleted`) VALUES ('10', 'Hyundai', b'0');

/* models */
/* volvo */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('1', 'S60', '1', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('2', 'S80', '1', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('3', 'V70', '1', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('4', 'XC90', '1', b'0');
/* saab */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('5', '9-5', '2', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('6', '9-3', '2', b'0');
/* mercedes */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('7', 'A-Class', '3', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('8', 'S-Class', '3', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('9', 'ML-Class', '3', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('10', '124', '3', b'0');
/* bmw */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('11', '520', '4', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('12', 'M3', '4', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('13', 'X7', '4', b'0');
/*audi */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('14', 'A4', '5', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('15', 'A6', '5', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('16', 'A8', '5', b'0');
/* opel */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('17', 'Astra', '6', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('18', 'Corsa', '6', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('19', 'Vectra', '6', b'0');
/* peugeot */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('20', '307', '7', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('21', '407', '7', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('22', '5008', '7', b'0');
/* renault */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('23', 'Clio', '8', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('24', 'Megane', '8', b'0');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('25', 'Laguna', '8', b'0');
/* ford */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('26', 'Focus', '9', b'0');
/* hyundai */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`, `deleted`) VALUES ('27', 'Tucson', '10', b'0');

/* classes */
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('1', 'Sedan', b'0');
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('2', 'Hatchback', b'0');
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('3', 'Roadster', b'0');
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('4', 'Minivan', b'0');
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('5', 'SUV', b'0');
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('6', 'Pickup', b'0');
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('7', 'Cabriolet', b'0');
INSERT INTO `xml`.`car_class` (`id`, `name`, `deleted`) VALUES ('8', 'Caravan', b'0');

/* fuel types */
INSERT INTO `xml`.`fuel_type` (`id`, `name`, `deleted`) VALUES ('1', 'Petrol', b'0');
INSERT INTO `xml`.`fuel_type` (`id`, `name`, `deleted`) VALUES ('2', 'Diesel', b'0');
INSERT INTO `xml`.`fuel_type` (`id`, `name`, `deleted`) VALUES ('3', 'Petrol + Gas', b'0');
INSERT INTO `xml`.`fuel_type` (`id`, `name`, `deleted`) VALUES ('4', 'Electric', b'0');
INSERT INTO `xml`.`fuel_type` (`id`, `name`, `deleted`) VALUES ('5', 'Hybrid', b'0');

/* transmission types */
INSERT INTO `xml`.`transmission_type` (`id`, `name`, `deleted`) VALUES ('1', 'Manual', b'0');
INSERT INTO `xml`.`transmission_type` (`id`, `name`, `deleted`) VALUES ('2', 'Automatic', b'0');
INSERT INTO `xml`.`transmission_type` (`id`, `name`, `deleted`) VALUES ('3', 'Semi-automatic', b'0');

/* pricelists */
INSERT INTO `xml`.`pricelist` (`id`, `price_forcdw`, `price_per_day`, `price_per_km`, `deleted`) VALUES ('1', '100', '10', '3', b'0');
INSERT INTO `xml`.`pricelist` (`id`, `price_forcdw`, `price_per_day`, `price_per_km`, `deleted`) VALUES ('2', '150', '20', '7', b'0');
INSERT INTO `xml`.`pricelist` (`id`, `price_forcdw`, `price_per_day`, `price_per_km`, `deleted`) VALUES ('3', '120', '30', '2', b'0');
INSERT INTO `xml`.`pricelist` (`id`, `price_forcdw`, `price_per_day`, `price_per_km`, `deleted`) VALUES ('4', '80', '15', '1', b'0');
INSERT INTO `xml`.`pricelist` (`id`, `price_forcdw`, `price_per_day`, `price_per_km`, `deleted`) VALUES ('5', '300', '17', '10', b'0');
INSERT INTO `xml`.`pricelist` (`id`, `price_forcdw`, `price_per_day`, `price_per_km`, `deleted`) VALUES ('6', '250', '12', '7', b'0');
INSERT INTO `xml`.`pricelist` (`id`, `price_forcdw`, `price_per_day`, `price_per_km`, `deleted`) VALUES ('7', '50', '11', '1', b'0');

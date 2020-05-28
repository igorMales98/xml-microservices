/* brands */
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('1', 'Volvo');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('2', 'Saab');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('3', 'Mercedes Benz');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('4', 'BMW');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('5', 'Audi');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('6', 'Opel');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('7', 'Peugeot');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('8', 'Renault');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('9', 'Ford');
INSERT INTO `xml`.`car_brand` (`id`, `name`) VALUES ('10', 'Hyundai');

/* models */
/* volvo */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('1', 'S60', '1');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('2', 'S80', '1');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('3', 'V70', '1');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('4', 'XC90', '1');
/* saab */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('5', '9-5', '2');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('6', '9-3', '2');
/* mercedes */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('7', 'A-Class', '3');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('8', 'S-Class', '3');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('9', 'ML-Class', '3');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('10', '124', '3');
/* bmw */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('11', '520', '4');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('12', 'M3', '4');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('13', 'X7', '4');
/*audi */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('14', 'A4', '5');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('15', 'A6', '5');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('16', 'A8', '5');
/* opel */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('17', 'Astra', '6');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('18', 'Corsa', '6');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('19', 'Vectra', '6');
/* peugeot */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('20', '307', '7');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('21', '407', '7');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('22', '5008', '7');
/* renault */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('23', 'Clio', '8');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('24', 'Megane', '8');
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('25', 'Laguna', '8');
/* ford */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('26', 'Focus', '9');
/* hyundai */
INSERT INTO `xml`.`car_model` (`id`, `name`, `car_brand_id`) VALUES ('27', 'Tucson', '10');

/* classes */
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('1', 'Sedan');
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('2', 'Hatchback');
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('3', 'Roadster');
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('4', 'Minivan');
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('5', 'SUV');
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('6', 'Pickup');
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('7', 'Cabriolet');
INSERT INTO `xml`.`car_class` (`id`, `name`) VALUES ('8', 'Caravan');

/* fuel types */
INSERT INTO `xml`.`fuel_type` (`id`, `name`) VALUES ('1', 'Petrol');
INSERT INTO `xml`.`fuel_type` (`id`, `name`) VALUES ('2', 'Diesel');
INSERT INTO `xml`.`fuel_type` (`id`, `name`) VALUES ('3', 'Petrol + Gas');
INSERT INTO `xml`.`fuel_type` (`id`, `name`) VALUES ('4', 'Electric');
INSERT INTO `xml`.`fuel_type` (`id`, `name`) VALUES ('5', 'Hybrid');

/* transmission types */
INSERT INTO `xml`.`transmission_type` (`id`, `name`) VALUES ('1', 'Manual');
INSERT INTO `xml`.`transmission_type` (`id`, `name`) VALUES ('2', 'Automatic');
INSERT INTO `xml`.`transmission_type` (`id`, `name`) VALUES ('3', 'Semi-automatic');

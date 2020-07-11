/* rent-requests */
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('1', 'PENDING', '2020-07-12 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('2', 'PENDING', '2020-07-13 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('3', 'PENDING', '2020-07-14 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('4', 'PENDING', '2020-07-15 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('5', 'CANCELLED', '2020-07-16 17:00:00.000000', '2020-08-01 17:00:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('6', 'CANCELLED', '2020-07-17 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('7', 'PAID', '2020-07-18 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('8', 'PAID', '2020-07-19 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('9', 'PAID', '2020-07-20 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`, `advertiser_id`) VALUES ('10', 'PAID', '2020-07-21 17:58:00.000000', '2020-08-01 17:58:00.000000', '3', '2020-07-11 17:58:00.000000', '2');

/* rented-advertisements */
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('1', '1');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('2', '2');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('3', '3');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('4', '4');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('5', '4');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('6', '4');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('7', '5');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('8', '6');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('9', '7');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('10', '8');

/* reports */
INSERT INTO `xml`.`report` (`id`, `additional_information`, `km`, `rent_request_id`) VALUES ('1', 'Isao je lepo bas', '100', '8');
INSERT INTO `xml`.`report` (`id`, `additional_information`, `km`, `rent_request_id`) VALUES ('2', 'Bas je udoban', '200', '9');


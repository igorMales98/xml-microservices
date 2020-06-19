/* rent-requests */
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('1', 'PENDING', '2020-05-11 17:58:00.000000', '2020-05-11 17:58:00.000000', '3', '2020-05-11 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('2', 'PENDING', '2020-05-11 17:58:00.000000', '2020-05-11 17:58:00.000000', '3', '2020-05-12 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('3', 'PENDING', '2020-05-11 17:58:00.000000', '2020-05-11 17:58:00.000000', '3', '2020-05-13 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('4', 'PENDING', '2020-05-11 17:58:00.000000', '2020-05-11 17:58:00.000000', '3', '2020-05-14 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('5', 'RESERVED', '2020-05-10 17:00:00.000000', '2020-05-15 17:00:00.000000', '3', '2020-05-15 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('6', 'RESERVED', '2020-06-01 17:58:00.000000', '2020-06-10 17:58:00.000000', '3', '2020-05-16 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('7', 'RESERVED', '2020-05-05 17:58:00.000000', '2020-06-10 17:58:00.000000', '3', '2020-05-17 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('8', 'PAID', '2020-05-01 17:58:00.000000', '2020-05-02 17:58:00.000000', '3', '2020-05-18 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('9', 'PAID', '2020-05-01 17:58:00.000000', '2020-05-03 17:58:00.000000', '3', '2020-05-19 17:58:00.000000');
INSERT INTO `xml`.`rent_request` (`id`, `status`, `reserved_from`, `reserved_to`, `customer_id`, `created`) VALUES ('10', 'PAID', '2020-04-11 17:58:00.000000', '2020-04-21 17:58:00.000000', '3', '2020-05-20 17:58:00.000000');

/* rented-advertisements */
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('5', '3');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('5', '4');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('6', '6');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('7', '7');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('8', '1');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('9', '2');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('9', '3');
INSERT INTO `xml`.`rented_advertisements` (`rent_request_id`, `advertisement_id`) VALUES ('10', '5');

/* reports */
INSERT INTO `xml`.`report` (`id`, `additional_information`, `km`, `rent_request_id`) VALUES ('1', 'Isao je lepo bas', '100', '8');
INSERT INTO `xml`.`report` (`id`, `additional_information`, `km`, `rent_request_id`) VALUES ('2', 'Bas je udoban', '200', '9');


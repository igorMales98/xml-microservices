/* users */
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`, `deleted`) VALUES ('ADMIN', '1', 'Puskinova 8', 'Novi Sad', 'Serbia', 'marko@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '063555333', 'jovanjo', 'Jovanovic', 'Jovan','0', b'0');
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`, `deleted`) VALUES ('AGENT', '2', 'Cara Dusana 51', 'Novi Sad', 'Serbia', 'mara@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '063555333', 'maricm', 'Maric', 'Mara','0', b'0');
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`, `deleted`) VALUES ('CUSTOMER', '3', 'Laze Kostica 36', 'Indjija', 'Serbia', 'petar@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '063555333', 'peraPetar', 'Peric', 'Pera','0', b'0');
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`, `deleted`) VALUES ('CUSTOMER', '4', 'Janka Veselinovica 1', 'Novi Sad', 'Serbia', 'marko@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '0651111111', 'markomarkovic', 'Markovic', 'Marko','0', b'0');
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`, `deleted`) VALUES ('CUSTOMER', '5', 'Kralja Petra', 'Belgrade', 'Serbia', 'katarina@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '0612233444', 'katarinak', 'Katic', 'Katarina','0', b'0');

/* authorities */
INSERT INTO `xml`.`authority` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `xml`.`authority` (`id`, `name`) VALUES ('2', 'ROLE_AGENT');
INSERT INTO `xml`.`authority` (`id`, `name`) VALUES ('3', 'ROLE_CUSTOMER');

/* user_authorities */
INSERT INTO `xml`.`user_authority` (`user_id`, `authority_id`) VALUES ('1', '1');
INSERT INTO `xml`.`user_authority` (`user_id`, `authority_id`) VALUES ('2', '2');
INSERT INTO `xml`.`user_authority` (`user_id`, `authority_id`) VALUES ('3', '3');

/* permissions */
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('1', 'READ_REGISTRATION_REQUESTS');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('2', 'DELETE_REGISTRATION_REQUESTS');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('3', 'CONFIRM_REGISTRATION_REQUESTS');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('4', 'READ_USER');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('5', 'WHO_AM_I');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('6', 'UPDATE_POSTS');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('7', 'CREATE_PHYSICAL_USER');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('8', 'READ_CUSTOMERS');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('9', 'EDIT_CUSTOMERS');
INSERT INTO `xml`.`permission` (`id`, `name`) VALUES ('10', 'CREATE_AGENTS');




/* authorities_permission */
/* admin = authority = 1 */
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '1');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '2');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '3');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '4');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '5');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '8');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '9');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('1', '10');


/* agent = authority = 2 */
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('2', '4');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('2', '5');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('2', '7');

/* customer = authority = 3 */
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('3', '4');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('3', '5');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('3', '6');
INSERT INTO `xml`.`authority_permission` (`authority_id`, `permission_id`) VALUES ('3', '7');

/* registration_requests */
INSERT INTO `xml`.`registration_request` (`id`, `username`, `password`, `first_name`, `last_name`, `country`, `city`, `email`, `phone`, `address`, `deleted`) VALUES ('1', 'peraperic', '123123', 'Pera', 'Peric', 'Serbia', 'Novi Sad', 'periccc@gmail.com', '0625555555', 'Kopernikova', b'0');
INSERT INTO `xml`.`registration_request` (`id`, `username`, `password`, `first_name`, `last_name`, `country`, `city`, `email`, `phone`, `address`, `deleted`) VALUES ('2', 'stefanovics', '123123', 'Stefan', 'Stefanovic', 'Serbia', 'Belgrade', 'stefanoviccc@gmail.com', '0611111111', 'Cvijiceva', b'0');
INSERT INTO `xml`.`registration_request` (`id`, `username`, `password`, `first_name`, `last_name`, `country`, `city`, `email`, `phone`, `address`, `deleted`) VALUES ('3', 'markoviccc', '123123', 'Marko', 'Markovic', 'Serbia', 'Belgrade', 'markoviccc@gmail.com', '0693322111', 'Vojislava Ilica', b'0');

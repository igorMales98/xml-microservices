/* users */
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`) VALUES ('ADMIN', '1', 'Puskinova 8', 'Novi Sad', 'Serbia', 'marko@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '063555333', 'jovanjo', 'Jovanovic', 'Jovan','0');
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`) VALUES ('AGENT', '2', 'Cara Dusana 51', 'Novi Sad', 'Serbia', 'mara@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '063555333', 'maricm', 'Maric', 'Mara','0');
INSERT INTO `xml`.`users` (`type`, `id`, `address`, `city`, `country`, `email`, `enabled`, `password`, `phone`, `username`, `last_name`, `first_name`,`advertisements_posted`) VALUES ('CUSTOMER', '3', 'Laze Kostica 36', 'Indjija', 'Serbia', 'petar@gmail.com', b'1', '$2a$10$1hoVKaxcWC6/T/.NSGcOm.F7E8vT2xbuJfFuIWEl4hexg28P09CJm', '063555333', 'peraPetar', 'Peric', 'Pera','0');

/* authorities */
INSERT INTO `xml`.`authority` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `xml`.`authority` (`id`, `name`) VALUES ('2', 'ROLE_AGENT');
INSERT INTO `xml`.`authority` (`id`, `name`) VALUES ('3', 'ROLE_CUSTOMER');

/* user_authorities */
INSERT INTO `xml`.`user_authority` (`user_id`, `authority_id`) VALUES ('1', '1');
INSERT INTO `xml`.`user_authority` (`user_id`, `authority_id`) VALUES ('2', '2');
INSERT INTO `xml`.`user_authority` (`user_id`, `authority_id`) VALUES ('3', '3');

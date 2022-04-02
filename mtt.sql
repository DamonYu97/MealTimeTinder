DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `hunter`;
DROP TABLE IF EXISTS `restaurant`;
DROP TABLE IF EXISTS `user`;


CREATE TABLE `user`  (
     `id` bigint NOT NULL,
     `username` varchar(20) NOT NULL,
     `password` varchar(255) NOT NULL,
     `status` enum('REGISTERED', 'PENDING', 'DELETED') NOT NULL,
     `role` enum('ADMIN', 'HUNTER', 'RESTAURANT') NOT NULL,
     `address` varchar(255) NULL,
     `postcode` varchar(8) NULL,
     PRIMARY KEY (`id`),
     INDEX `idx_username`(`username`)
);

CREATE TABLE `admin`  (
      `id` bigint NOT NULL,
      `phone_number` varchar(10) NOT NULL,
      `name` varchar(255) NOT NULL,
      `birth_date` datetime(0) NOT NULL,
      PRIMARY KEY (`id`),
      FOREIGN KEY (id) REFERENCES user(id)
);

CREATE TABLE `restaurant`  (
      `name` varchar(255) NOT NULL,
      `phone_number` varchar(10) NOT NULL,
      `id` bigint NOT NULL,
      `description` varchar(255) NULL,
      PRIMARY KEY (`id`),
      FOREIGN KEY (id) REFERENCES user(id)
);

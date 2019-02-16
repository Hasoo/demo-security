DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `fee` double(11,2) NOT NULL,
  `groupname` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `account_roles`;
CREATE TABLE `account_roles` (
  `account_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FKtp61eta5i06bug3w1qr6286uf` (`account_id`),
  CONSTRAINT `FKtp61eta5i06bug3w1qr6286uf` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

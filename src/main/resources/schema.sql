DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `groupname` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `whiteip` varchar(255) DEFAULT NULL,
  `fee` double(11,2) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `account_roles`;
CREATE TABLE `account_roles` (
  `username` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  KEY `FKtp61eta5i06bug3w1qr6286uf` (`username`),
  CONSTRAINT `FKtp61eta5i06bug3w1qr6286uf` FOREIGN KEY (`username) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `account` (
  `groupname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `whiteip` varchar(255) DEFAULT NULL,
  `fee` double(11,2) NOT NULL,
  `failure_count` int(11) DEFAULT 0,
  `enabled` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `account_roles` (
  `username` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  KEY `FKtp61eta5i06bug3w1qr6286uf` (`username`),
  CONSTRAINT `FKtp61eta5i06bug3w1qr6286uf` FOREIGN KEY (`username`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `history_log` (
  `type` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `memo` varchar(255) NOT NULL,
  `res_date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

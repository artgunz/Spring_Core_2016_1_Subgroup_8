-- --------------------------------------------------------
-- Host:                         192.168.56.11
-- Server version:               5.5.45 - MySQL Community Server (GPL)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for cinema
CREATE DATABASE IF NOT EXISTS `cinema` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cinema`;


-- Dumping structure for table cinema.event
CREATE TABLE IF NOT EXISTS `event` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` char(25) NOT NULL,
  `price` bigint(20) DEFAULT NULL,
  `rating` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table cinema.event_shows
CREATE TABLE IF NOT EXISTS `event_shows` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `event` bigint(20) DEFAULT NULL,
  `auditorium` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table cinema.price
CREATE TABLE IF NOT EXISTS `price` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `currency` char(3) DEFAULT NULL,
  `value` float DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table cinema.rating
CREATE TABLE IF NOT EXISTS `rating` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` char(10) NOT NULL,
  `price_increment` float NOT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table cinema.user
CREATE TABLE IF NOT EXISTS `user` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL,
  `name` char(25) NOT NULL,
  `email` char(25) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `statistic` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee_gral`
--

DROP TABLE IF EXISTS `employee_gral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_gral` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `birthdate` varchar(255) DEFAULT NULL,
  `cell_phone` varchar(255) DEFAULT NULL,
  `civil_status` varchar(255) DEFAULT NULL,
  `curp` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `email_personal` varchar(255) DEFAULT NULL,
  `emergency_phone` varchar(255) DEFAULT NULL,
  `emergency_phone_call` varchar(255) DEFAULT NULL,
  `imss` varchar(255) DEFAULT NULL,
  `military_primer` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `rfc` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_el7ymsqlveumko5dm7xeaoggv` (`user_id`),
  CONSTRAINT `FKeh0wnj7vciwdrqvfk7qttqfkn` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_gral`
--

LOCK TABLES `employee_gral` WRITE;
/*!40000 ALTER TABLE `employee_gral` DISABLE KEYS */;
INSERT INTO `employee_gral` VALUES (2,1,'1212-12-12','5568903613','Soltero','123456787632345676543q','2019-06-24 15:45:48','ing.dazaeev@gmail.com','123456787632345676543','guadalupe','1234567823456789','123456787632345676543q','2019-08-19 16:18:56','Mexican','5526159275','GOHN860412','Hombre',1),(4,1,'1212-12-12','123456787654','Soltero','123456787654qwwwww','2019-08-21 12:10:16','jplata@gmail.com','123456787654','123456787654q','123456787654q','123456787654q','2019-08-21 12:10:16','12345678987654321','123456787654','123456787654q','Mujer',35),(5,1,'1212-12-12','654321234567','Soltero','32128532356787654323','2019-08-21 12:26:01','emiliano@gmail.com','654323456','765432345','1212121212121','123456787654321','2019-08-21 12:26:01','1212121212121212','234567845678765432567','6765432123456787654','Hombre',36);
/*!40000 ALTER TABLE `employee_gral` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:10

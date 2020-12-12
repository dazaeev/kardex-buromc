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
-- Table structure for table `employee_labor`
--

DROP TABLE IF EXISTS `employee_labor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_labor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `business_mail` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `date_admission` varchar(255) DEFAULT NULL,
  `employee_number` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `policy_sgmm` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `employee_gral` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n1ipg4d3kgj2p3bakgl2hbu3s` (`employee_gral`),
  CONSTRAINT `FK5glqiqifc9e298l687vvg9une` FOREIGN KEY (`employee_gral`) REFERENCES `employee_gral` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_labor`
--

LOCK TABLES `employee_labor` WRITE;
/*!40000 ALTER TABLE `employee_labor` DISABLE KEYS */;
INSERT INTO `employee_labor` VALUES (1,1,'SSTI Soluciones de TI','ngonzalez@buromc.com','2019-07-02 10:37:37','1212-12-12','12344321234','2019-07-02 10:37:37','1234543212345432','Ingeniero de Desarrollo SR',2),(2,1,'Infraestructura','jplata@buromc.com','2019-08-21 12:11:18','1212-12-12','121234543','2019-08-21 12:25:22','123456543212345432','Ingeniero de Infraestructura',4),(3,1,'Seguridad','emiliano@buromc.com','2019-08-21 12:26:34','1212-12-12','1234567876543235434444','2019-08-21 12:26:34','12345678987654321','Ingeniero de Seguridad Informática',5);
/*!40000 ALTER TABLE `employee_labor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:22

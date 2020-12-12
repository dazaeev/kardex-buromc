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
-- Table structure for table `employee_files_system_personal`
--

DROP TABLE IF EXISTS `employee_files_system_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_files_system_personal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `birth_certificate` varchar(255) DEFAULT NULL,
  `certifications` varchar(255) DEFAULT NULL,
  `curp` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `imss` varchar(255) DEFAULT NULL,
  `infonavit` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `no_criminal_record` varchar(255) DEFAULT NULL,
  `official_identification` varchar(255) DEFAULT NULL,
  `passport_visa` varchar(255) DEFAULT NULL,
  `personal_references` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `professional_curriculum` varchar(255) DEFAULT NULL,
  `proof_address` varchar(255) DEFAULT NULL,
  `proof_studies` varchar(255) DEFAULT NULL,
  `recommendation_letter` varchar(255) DEFAULT NULL,
  `title_professional_license` varchar(255) DEFAULT NULL,
  `employee_gral_id` int(11) DEFAULT NULL,
  `rfc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgypw3qdhyolsipk28o6f0gmfa` (`employee_gral_id`),
  CONSTRAINT `FKgypw3qdhyolsipk28o6f0gmfa` FOREIGN KEY (`employee_gral_id`) REFERENCES `employee_gral` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_files_system_personal`
--

LOCK TABLES `employee_files_system_personal` WRITE;
/*!40000 ALTER TABLE `employee_files_system_personal` DISABLE KEYS */;
INSERT INTO `employee_files_system_personal` VALUES (1,1,'ANÁLISIS DE CÓDIGO.html____text/html','','','2019-08-03 13:44:58','','','2019-08-27 14:19:08','','','','','','','','E-R-Kardex_v2.png____image/png','','',2,'BeITalent (Notificacion 1).html____text/html');
/*!40000 ALTER TABLE `employee_files_system_personal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:15

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
-- Table structure for table `open_certifications`
--

DROP TABLE IF EXISTS `open_certifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `open_certifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `certification` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `date_expiration` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `name_certification` varchar(255) DEFAULT NULL,
  `sending_expired_mail` int(11) DEFAULT NULL,
  `employee_studies_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrophhomu6c9f244cbsg4h3vhp` (`employee_studies_id`),
  CONSTRAINT `FKrophhomu6c9f244cbsg4h3vhp` FOREIGN KEY (`employee_studies_id`) REFERENCES `employee_studies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `open_certifications`
--

LOCK TABLES `open_certifications` WRITE;
/*!40000 ALTER TABLE `open_certifications` DISABLE KEYS */;
INSERT INTO `open_certifications` VALUES (1,1,'1234','2019-08-03 15:17:00','0321-04-23','234','2019-08-03 15:17:00','blog-p6.html____text/html',1,2),(2,1,'SCRUM MASTER','2019-08-06 18:00:17','2019-08-06','AGILE','2019-08-09 16:10:12','validar-actividad.html____text/html',1,2);
/*!40000 ALTER TABLE `open_certifications` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:25

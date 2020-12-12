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
-- Table structure for table `employee_studies`
--

DROP TABLE IF EXISTS `employee_studies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_studies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `educational_level_latest` varchar(255) DEFAULT NULL,
  `educational_level_previous` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `school_latest` varchar(255) DEFAULT NULL,
  `school_previous` varchar(255) DEFAULT NULL,
  `status_latest` varchar(255) DEFAULT NULL,
  `status_previous` varchar(255) DEFAULT NULL,
  `employee_gral` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l5m6ssd726pfyjkhijx2i60tp` (`employee_gral`),
  CONSTRAINT `FK9vx664hyffx9ys6kvw7a8j78d` FOREIGN KEY (`employee_gral`) REFERENCES `employee_gral` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_studies`
--

LOCK TABLES `employee_studies` WRITE;
/*!40000 ALTER TABLE `employee_studies` DISABLE KEYS */;
INSERT INTO `employee_studies` VALUES (2,1,'2019-07-02 10:34:29','Educación Primaria','Educación Primaria','2019-07-02 10:34:29','ITO','ITOS','Estudiante','Estudiante',2);
/*!40000 ALTER TABLE `employee_studies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:29

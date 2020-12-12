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
-- Table structure for table `catalog_fase`
--

DROP TABLE IF EXISTS `catalog_fase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_fase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `certification_track_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd4ww5ipycvomla6k8hwo98q67` (`certification_track_id`),
  CONSTRAINT `FKd4ww5ipycvomla6k8hwo98q67` FOREIGN KEY (`certification_track_id`) REFERENCES `certification_track` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog_fase`
--

LOCK TABLES `catalog_fase` WRITE;
/*!40000 ALTER TABLE `catalog_fase` DISABLE KEYS */;
INSERT INTO `catalog_fase` VALUES (10,1,'2010-10-10 00:00:00','TRONCO COMÚN','2019-07-17 17:26:30','Fase 0',1),(12,1,'2019-07-12 11:56:42','ENGINEER','2019-07-15 16:59:30','Fase 1',1),(13,1,'2019-07-12 13:29:54','ESPECIALIST','2019-07-15 15:59:54','Fase 2',1),(14,1,'2019-07-12 15:34:36','PROFESSIONAL','2019-07-15 16:12:45','Fase 3',1),(51,1,'2019-08-21 12:13:21','TRONCO COMÚN','2019-08-21 12:13:21','Fase 0',3),(52,1,'2019-08-21 12:20:42','TRONCO COMÚN','2019-08-21 12:20:42','Fase 0',14);
/*!40000 ALTER TABLE `catalog_fase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:12

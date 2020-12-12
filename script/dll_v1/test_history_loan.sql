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
-- Table structure for table `history_loan`
--

DROP TABLE IF EXISTS `history_loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `amount_loan` varchar(255) DEFAULT NULL,
  `concept_loan` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `date_loan` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `employee_economics_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9dcb9kdk5xrgjbja6b443lxc3` (`employee_economics_id`),
  CONSTRAINT `FK9dcb9kdk5xrgjbja6b443lxc3` FOREIGN KEY (`employee_economics_id`) REFERENCES `employee_economics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_loan`
--

LOCK TABLES `history_loan` WRITE;
/*!40000 ALTER TABLE `history_loan` DISABLE KEYS */;
INSERT INTO `history_loan` VALUES (1,1,'39000','Personal','2019-06-24 16:12:08','1111-11-11','2019-06-24 16:12:08','Pendiente',2),(2,1,'23000','casa','2019-06-24 16:15:25','1010-10-10','2019-06-24 16:15:25','Pendiente',2),(3,1,'45000','CASAS','2019-06-24 16:47:03','1010-10-10','2019-06-24 16:47:03','Pendiente',2);
/*!40000 ALTER TABLE `history_loan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:23

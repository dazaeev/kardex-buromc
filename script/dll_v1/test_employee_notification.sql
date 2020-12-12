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
-- Table structure for table `employee_notification`
--

DROP TABLE IF EXISTS `employee_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `modified` datetime DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhlua71i52mlpp91xm0eqhbtxh` (`user_id`),
  CONSTRAINT `FKhlua71i52mlpp91xm0eqhbtxh` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=293 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_notification`
--

LOCK TABLES `employee_notification` WRITE;
/*!40000 ALTER TABLE `employee_notification` DISABLE KEYS */;
INSERT INTO `employee_notification` VALUES (268,1,'2019-08-19 16:06:37','2019-08-19 16:06:37','Se creo usuario: aramirez@buromc.com',NULL),(269,1,'2019-08-19 16:06:37','2019-08-19 16:06:37','Bienvenido al portal.',33),(271,1,'2019-08-21 11:42:27','2019-08-21 11:42:27','Nuevo curso solicitado de ngonzalez@buromc.com',NULL),(272,1,'2019-08-21 11:42:28','2019-08-21 11:42:28','Solicitaste el curso \"Fundamentos de Lenguajes de programación\" enviada.',1),(273,1,'2019-08-21 12:08:41','2019-08-21 12:08:41','Se creo usuario: jplata@buromc.com',NULL),(274,1,'2019-08-21 12:08:41','2019-08-21 12:08:41','Bienvenido al portal.',35),(275,1,'2019-08-21 12:23:01','2019-08-21 12:23:01','Se creo usuario: emedina@buromc.com',NULL),(276,1,'2019-08-21 12:23:01','2019-08-21 12:23:01','Bienvenido al portal.',36),(277,1,'2019-08-21 20:31:28','2019-08-21 20:31:28','Se creo usuario: rh@buromc.com',NULL),(278,1,'2019-08-21 20:31:28','2019-08-21 20:31:28','Bienvenido al portal.',37),(279,1,'2019-08-22 00:24:53','2019-08-22 00:24:53','Nuevo curso solicitado de ngonzalez@buromc.com',NULL),(280,1,'2019-08-22 00:24:53','2019-08-22 00:24:53','Solicitaste el curso \"CCNA Security\" enviada.',1),(281,1,'2019-08-22 00:26:23','2019-08-22 00:26:23','Nuevo curso solicitado de ngonzalez@buromc.com',NULL),(282,1,'2019-08-22 00:26:23','2019-08-22 00:26:23','Solicitaste el curso \"CompTIA: Server+ Guía de Estudio\" enviada.',1),(283,1,'2019-08-22 00:50:53','2019-08-22 00:50:53','Nuevo curso solicitado de ngonzalez@buromc.com',NULL),(284,1,'2019-08-22 00:50:53','2019-08-22 00:50:53','Solicitaste el curso \"CompTIA: Linux+ Guía de Estudio\" enviada.',1),(285,1,'2019-08-27 23:41:32','2019-08-27 23:41:32','Nuevo curso solicitado de ngonzalez@buromc.com',NULL),(286,1,'2019-08-27 23:41:33','2019-08-27 23:41:33','Solicitaste el curso \"Java\" enviada.',1),(287,1,'2019-08-27 23:42:39','2019-08-27 23:42:39','Nuevo curso solicitado de ngonzalez@buromc.com',NULL),(288,1,'2019-08-27 23:42:39','2019-08-27 23:42:39','Solicitaste el curso \"Fundamentos de Sistemas Operativos\" enviada.',1),(289,1,'2019-08-27 23:44:06','2019-08-27 23:44:06','Nuevo curso solicitado de ngonzalez@buromc.com',NULL),(290,1,'2019-08-27 23:44:06','2019-08-27 23:44:06','Solicitaste el curso \"Fundamentos de Sistemas Operativos\" enviada.',1),(291,1,'2019-08-29 12:28:06','2019-08-29 12:28:06','Se creo usuario: dmartinez@buromc.com',NULL),(292,1,'2019-08-29 12:28:07','2019-08-29 12:28:07','Bienvenido al portal.',38);
/*!40000 ALTER TABLE `employee_notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:36

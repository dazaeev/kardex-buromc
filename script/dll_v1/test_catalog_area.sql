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
-- Table structure for table `catalog_area`
--

DROP TABLE IF EXISTS `catalog_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog_area`
--

LOCK TABLES `catalog_area` WRITE;
/*!40000 ALTER TABLE `catalog_area` DISABLE KEYS */;
INSERT INTO `catalog_area` VALUES (1,1,'2019-02-25 13:58:11','Dirección General','2019-04-11 17:06:41','Dirección General','Dirección General'),(2,1,'2019-02-25 13:58:20','Recursos Humanos','2019-02-25 13:58:20','Recursos Humanos','Recursos Humanos'),(3,1,'2019-02-25 13:58:27','Jurídico','2019-02-25 13:58:27','Jurídico','Jurídico'),(4,1,'2019-02-25 13:58:35','Preventa','2019-02-25 13:58:35','Preventa','Preventa'),(5,1,'2019-02-25 13:58:43','Marketing','2019-02-25 13:58:43','Marketing','Marketing'),(6,1,'2019-02-25 13:58:51','Administración','2019-02-25 13:58:51','Administración','Administración'),(7,1,'2019-02-25 13:59:01','Tecnologías de la Información','2019-02-25 13:59:01','Tecnologías de la Información','Tecnologías de la Información'),(8,1,'2019-02-25 13:59:10','Seguridad e Innovación','2019-02-25 13:59:10','Seguridad e Innovación','Seguridad e Innovación'),(9,1,'2019-02-25 13:59:18','Seguridad','2019-02-25 13:59:18','Seguridad','Seguridad'),(10,1,'2019-02-25 13:59:26','Redes','2019-02-25 13:59:26','Redes','Redes'),(11,1,'2019-02-25 13:59:43','Infraestructura','2019-02-26 20:51:21','Infraestructura','Infraestructura'),(12,1,'2019-02-25 13:59:51','Procesos','2019-02-25 13:59:51','Procesos','Procesos'),(13,1,'2019-02-25 14:00:01','SSTI Soluciones de TI','2019-02-25 14:00:01','SSTI Soluciones de TI','SSTI Soluciones de TI'),(14,1,'2019-02-25 14:00:10','SSTI Servicios','2019-02-25 14:00:10','SSTI Servicios','SSTI Servicios');
/*!40000 ALTER TABLE `catalog_area` ENABLE KEYS */;
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

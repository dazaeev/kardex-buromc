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
-- Table structure for table `catalog_fase_block_technology`
--

DROP TABLE IF EXISTS `catalog_fase_block_technology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_fase_block_technology` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `modified` datetime DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `technology` varchar(255) DEFAULT NULL,
  `catalog_fase_block_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh5rcjka0em14ohgwe4k0g4kh0` (`catalog_fase_block_id`),
  CONSTRAINT `FKh5rcjka0em14ohgwe4k0g4kh0` FOREIGN KEY (`catalog_fase_block_id`) REFERENCES `catalog_fase_block` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog_fase_block_technology`
--

LOCK TABLES `catalog_fase_block_technology` WRITE;
/*!40000 ALTER TABLE `catalog_fase_block_technology` DISABLE KEYS */;
INSERT INTO `catalog_fase_block_technology` VALUES (1,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Sistemas Operativos','TRONCO COMÚN - MARCO TEÓRICO',13),(2,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Agunlar - fundamentos','ANGULAR',15),(3,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Redes','TRONCO COMÚN - MARCO TEÓRICO',13),(4,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Seguridad de la Información','TRONCO COMÚN - MARCO TEÓRICO',13),(5,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Network+ Guía de Estudio ','TRONCO COMÚN - CompTIA',13),(6,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Server+ Guía de Estudio','TRONCO COMÚN - CompTIA',13),(7,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Linux+ Guía de Estudio','TRONCO COMÚN - CompTIA',13),(8,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CCNA Routing and Switching','TRONCO COMÚN - CCNA',13),(9,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CCNA Security','TRONCO COMÚN - CCNA',13),(10,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','TypeScrypt - fundamentos','TypeScrypt',15),(11,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Hipervisor Microsfot - Admon basica','Hipervisor Microsfot',15),(12,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Herramientas de monitoreo, desarrollo de interfaces gráficas - evaluación','Herramientas de monitoreo, desarrollo de interfaces gráficas',15),(13,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Lenguajes de programación','TRONCO COMÚN - MARCO TEÓRICO',13),(14,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos OO','TRONCO COMÚN - MARCO TEÓRICO',13),(15,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Java','TRONCO COMÚN - MARCO TEÓRICO',13),(16,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','.net','TRONCO COMÚN - MARCO TEÓRICO',13),(17,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Python','TRONCO COMÚN - MARCO TEÓRICO',13),(18,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Kotlin - fundamentos','Kotlin',16),(19,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Django - estructura','Django',16),(20,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','GEMALTO - arquitectura','GEMALTO',16),(21,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','ORACLE ORDS APEX - integracion y desarrollo','ORACLE ORDS APEX',17),(22,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','AZURE - integración y desarrollo','AZURE',17),(23,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','ASP .NET - DevOps','ASP .NET',17),(24,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','BlackBerry - Conocimiento general de la solución y habilidades de preventa','BlackBerry',17),(25,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','BLOCK CHAIN - Estructura y aplicación de BlockChain en sistemas y aplicaciones','BLOCK CHAIN',18),(26,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','DevOps - Conocimiento y estructura de este framework para desarrollo Web','DevOps',18),(27,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Check Marx - Conocimiento y aplicación de la herramienta Codigo','Check Marx',18),(28,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','PMP - Certificación Project Manager Professional','PMP',19),(29,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','GIAC - Conocimiento de técnicas de ingeniería reversa en elementos de malware','GIAC',19),(30,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','SCRUM MASTER - Certificiación','SCRUM MASTER',19),(31,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','PgMP - Manejo y dominio de Gestión de Programas','PgMP',20),(32,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','TOGAF - Conocimiento de este marco de arquitectura empresarial','TOGAF',20),(52,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Sistemas Operativos','TRONCO COMÚN - MARCO TEÓRICO',58),(53,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Redes','TRONCO COMÚN - MARCO TEÓRICO',58),(54,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Seguridad de la Información','TRONCO COMÚN - MARCO TEÓRICO',58),(55,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Network+ Guía de Estudio ','TRONCO COMÚN - CompTIA',58),(56,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Server+ Guía de Estudio','TRONCO COMÚN - CompTIA',58),(57,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Linux+ Guía de Estudio','TRONCO COMÚN - CompTIA',58),(58,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CCNA Routing and Switching','TRONCO COMÚN - CCNA',58),(59,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CCNA Security','TRONCO COMÚN - CCNA',58),(60,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Sistemas Operativos','TRONCO COMÚN - MARCO TEÓRICO',59),(61,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Redes','TRONCO COMÚN - MARCO TEÓRICO',59),(62,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','Fundamentos de Seguridad de la Información','TRONCO COMÚN - MARCO TEÓRICO',59),(63,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Network+ Guía de Estudio ','TRONCO COMÚN - CompTIA',59),(64,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Server+ Guía de Estudio','TRONCO COMÚN - CompTIA',59),(65,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CompTIA: Linux+ Guía de Estudio','TRONCO COMÚN - CompTIA',59),(66,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CCNA Routing and Switching','TRONCO COMÚN - CCNA',59),(67,1,'2010-10-10 00:00:00','2010-10-10 00:00:00','CCNA Security','TRONCO COMÚN - CCNA',59);
/*!40000 ALTER TABLE `catalog_fase_block_technology` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:39

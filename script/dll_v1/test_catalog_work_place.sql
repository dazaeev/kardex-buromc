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
-- Table structure for table `catalog_work_place`
--

DROP TABLE IF EXISTS `catalog_work_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_work_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `catalog_area_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK58t2jlpv2rk8vti9l56y7tnaf` (`catalog_area_id`),
  CONSTRAINT `FK58t2jlpv2rk8vti9l56y7tnaf` FOREIGN KEY (`catalog_area_id`) REFERENCES `catalog_area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog_work_place`
--

LOCK TABLES `catalog_work_place` WRITE;
/*!40000 ALTER TABLE `catalog_work_place` DISABLE KEYS */;
INSERT INTO `catalog_work_place` VALUES (1,1,'2019-02-25 14:04:31','Director General','2019-02-25 14:04:31','Director General','Director General',1),(2,1,'2019-02-25 14:04:44','Asistente de Dirección','2019-02-25 14:04:44','Asistente de Dirección','Asistente de Dirección',1),(3,1,'2019-02-25 14:04:58','Auxiliar de Dirección','2019-02-25 14:04:58','Auxiliar de Dirección','Auxiliar de Dirección',1),(4,1,'2019-02-25 14:56:44','Analista de Recursos Humanos','2019-02-25 15:06:18','Analista de Recursos Humanos','Analista de Recursos Humanos',2),(5,1,'2019-02-25 14:03:48','Gerente  de Recursos Humanos','2019-02-25 14:56:36','Gerente  de Recursos Humanos','Gerente  de Recursos Humanos',2),(6,1,'2019-02-25 14:56:58','Gerente Jurídico','2019-02-25 14:56:58','Gerente Jurídico','Gerente Jurídico',3),(7,1,'2019-02-25 14:57:08','Auxiliar Jurídico','2019-02-25 14:57:08','Auxiliar Jurídico','Auxiliar Jurídico',3),(8,1,'2019-02-25 14:58:13','Director Preventa ','2019-02-25 14:58:13','Director Preventa ','Director Preventa ',4),(9,1,'2019-02-25 14:58:27','Ingeniero Preventa ','2019-02-25 14:58:27','Ingeniero Preventa ','Ingeniero Preventa ',4),(10,1,'2019-02-25 14:58:37','Consultor Comercial','2019-02-25 15:06:58','Consultor Comercial','Consultor Comercial',4),(11,1,'2019-02-25 14:58:47','Ejecutivo de Cuenta','2019-02-25 14:58:47','Ejecutivo de Cuenta','Ejecutivo de Cuenta',4),(12,1,'2019-02-25 14:59:07','Consultor Comercial Zona Bajio','2019-02-25 14:59:07','Consultor Comercial Zona Bajio','Consultor Comercial Zona Bajio',4),(13,1,'2019-02-25 14:59:29','Gerente  de Marketing','2019-02-25 14:59:29','Gerente  de Marketing','Gerente  de Marketing',5),(14,1,'2019-02-25 14:59:40','Diseñador Gráfico','2019-02-25 14:59:40','Diseñador Gráfico','Diseñador Gráfico',5),(15,1,'2019-02-25 14:59:48','Ejecutivo de Marketing','2019-02-25 14:59:48','Ejecutivo de Marketing','Ejecutivo de Marketing',5),(16,1,'2019-02-25 14:59:57','Ejecutivo de Marketing Digital','2019-02-25 15:07:28','Ejecutivo de Marketing Digital','Ejecutivo de Marketing Digital',5),(17,1,'2019-02-25 15:00:06','Ejecutivo de Telemarketing','2019-02-25 15:00:06','Ejecutivo de Telemarketing','Ejecutivo de Telemarketing',5),(18,1,'2019-02-25 15:00:15','Diseñador Gráfico Jr','2019-02-25 15:00:15','Diseñador Gráfico Jr','Diseñador Gráfico Jr',5),(19,1,'2019-02-25 15:00:28','Gerente de Administración','2019-02-25 15:00:28','Gerente de Administración','Gerente de Administración',6),(20,1,'2019-02-25 15:00:35','Operaciones','2019-02-25 15:00:35','Operaciones','Operaciones',6),(21,1,'2019-02-25 15:00:42','Analista  Administrativo','2019-02-25 15:00:42','Analista  Administrativo','Analista  Administrativo',6),(22,1,'2019-02-25 15:00:50','Logística','2019-02-25 15:00:50','Logística','Logística',6),(23,1,'2019-02-25 15:00:58','Recepcionista','2019-02-25 15:00:58','Recepcionista','Recepcionista',6),(24,1,'2019-02-25 15:01:16','Gerente de TI','2019-02-25 15:01:16','Gerente de TI','Gerente de TI',7),(25,1,'2019-02-25 15:01:23','Ingeniero de Soporte','2019-02-25 15:01:23','Ingeniero de Soporte','Ingeniero de Soporte',7),(26,1,'2019-02-25 15:01:36','Ingeniero de Investigación','2019-02-25 15:08:19','Ingeniero de Investigación','Ingeniero de Investigación',8),(27,1,'2019-02-25 15:01:51','Líder en Seguridad','2019-02-25 15:01:51','Líder en Seguridad','Líder en Seguridad',9),(28,1,'2019-02-25 15:01:58','Ingeniero de Seguridad Informática','2019-02-25 15:08:37','Ingeniero de Seguridad Informática','Ingeniero de Seguridad Informática',9),(29,1,'2019-02-25 15:02:11','Líder en Redes','2019-02-25 15:08:55','Líder en Redes','Líder en Redes',10),(30,1,'2019-02-25 15:02:19','Ingeniero de Redes TI','2019-02-25 15:08:47','Ingeniero de Redes TI','Ingeniero de Redes TI',10),(31,1,'2019-02-25 15:02:32','Líder en Infraestructura','2019-02-25 15:09:08','Líder en Infraestructura','Líder en Infraestructura',11),(32,1,'2019-02-25 15:02:40','Ingeniero de Infraestructura','2019-02-25 15:09:18','Ingeniero de Infraestructura','Ingeniero de Infraestructura',11),(33,1,'2019-02-25 15:02:51','Líder en Procesos','2019-02-25 15:09:34','Líder en Procesos','Líder en Procesos',12),(34,1,'2019-02-25 15:03:16','Ingeniero de Desarrollo Jr','2019-02-25 15:03:16','Ingeniero de Desarrollo Jr','Ingeniero de Desarrollo Jr',13),(35,1,'2019-02-25 15:03:26','Ingeniero de Desarrollo SR','2019-02-25 15:03:26','Ingeniero de Desarrollo SR','Ingeniero de Desarrollo SR',13),(36,1,'2019-02-25 15:03:33','Ingeniero de Robótica','2019-02-25 15:09:45','Ingeniero de Robótica','Ingeniero de Robótica',13),(37,1,'2019-02-25 15:03:45','Coordinador de Mesa de Servicio','2019-02-25 15:10:06','Coordinador de Mesa de Servicio','Coordinador de Mesa de Servicio',14),(38,1,'2019-02-25 15:03:56','Ingeniero de Soporte','2019-02-25 15:03:56','Ingeniero de Soporte','Ingeniero de Soporte',14),(39,1,'2019-02-25 15:04:03','Becario de Soporte','2019-02-25 15:10:27','Becario de Soporte','Becario de Soporte',14),(40,1,'2019-02-25 15:04:10','Ingeniero  de Soporte','2019-02-25 15:10:17','Ingeniero  de Soporte','Ingeniero  de Soporte',14),(41,1,'2019-04-18 17:21:07','Becario','2019-04-18 17:21:07','Becario de Recursos Humanos','Recursos Humanos',2);
/*!40000 ALTER TABLE `catalog_work_place` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:18

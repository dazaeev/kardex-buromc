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
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `html` varchar(500) DEFAULT NULL,
  `menu` varchar(200) DEFAULT NULL,
  `role_id_menu` int(11) NOT NULL,
  `sub_menu` varchar(200) DEFAULT NULL,
  `href` varchar(500) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (2,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-gral\">Datos Básicos</a></li>','Administrar',1,'Basica','/adm/employee-gral','Datos Básicos','../images/employee-gral.png'),(3,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-studies\">Datos de Estudios</a></li>','Administrar',1,'Estudios','/adm/employee-studies','Datos de Estudios','../images/employee-studies.png'),(4,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-demographics\">Datos Demograficos</a></li>','Administrar',1,'Geograficos','/adm/employee-demographics','Datos Demográficos','../images/employee-demographics.png'),(5,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-workexperience\">Experiencia Laboral</a></li>','Administrar',1,'Laboral','/adm/employee-workexperience','Experiencia Laboral','../images/employee-workexperience.png'),(6,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-labor\">Datos Laborales</a></li>','Administrar',1,'Laborales','/adm/employee-labor','Datos Laborales','../images/employee-labor.png'),(7,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-economics\">Datos Economicos</a></li>','Administrar',1,'LEcnomicos','/adm/employee-economics','Datos Económicos','../images/employee-economics.png'),(8,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-legal\">Datos Legales</a></li>','Administrar',1,'Legal','/adm/employee-legal','Datos Legales','../images/employee-legal.png'),(9,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-system-personal\">Archivos Personales</a></li>','Administrar',1,'SistemaPersonal','/adm/employee-system-personal','Archivos Personales','../images/employee-system-personal.png'),(10,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/registration-user\">Registrar Empleado</a></li>','Administrar',1,'Z','/registration-user','Registrar Empleado','../images/registration-user.png'),(11,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/catalogs-area\">Catálogo Área</a></li>','Catalogos',1,'CatalogoArea','/adm/catalogs-area','Catálogo Área','../images/catalogs-area.png'),(12,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/certification-track-fases\">Alta</a></li>','Plan de carrera',1,'PlanCarreraAlta','/adm/certification-track-fases','Alta','../images/certification-track-fases.png'),(13,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/tracing-career-plan\">Seguimiento</a></li>','Plan de carrera',1,'PlanCarreraSeguimiento','/adm/tracing-career-plan','Seguimiento','../images/tracing-career-plan.png'),(14,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/request-of-course\">Solicitud de curso</a></li>','Plan de carrera',1,'PlanCarreraSolicitud','/adm/request-of-course','Solicitud de curso','../images/request-of-course.png'),(16,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-gral\">Datos Basicos</a></li>','Administrar',2,'Basica','/adm/employee-gral','Datos Basicos','../images/employee-gral.png'),(17,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-studies\">Datos de Estudios</a></li>','Administrar',2,'Estudios','/adm/employee-studies','Datos de Estudios','../images/employee-studies.png'),(18,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-demographics\">Datos Demograficos</a></li>','Administrar',2,'Geograficos','/adm/employee-demographics','Datos Demograficos','../images/employee-demographics.png'),(19,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-workexperience\">Experiencia Laboral</a></li>','Administrar',2,'Laboral','/adm/employee-workexperience','Experiencia Laboral','../images/employee-workexperience.png'),(20,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-labor\">Datos Laborales</a></li>','Administrar',2,'Laborales','/adm/employee-labor','Datos Laborales','../images/employee-labor.png'),(21,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/employee-system-personal\">Archivos Personales</a></li>','Administrar',2,'SistemaPersonal','/adm/employee-system-personal','Archivos Personales','../images/employee-system-personal.png'),(23,'<li><i class=\"menu-icon fa fa-sign-in\"></i><a href=\"/adm/request-of-course\">Solicitud de curso</a></li>','Plan de carrera',2,'PlanCarreraSolicitud','/adm/request-of-course','Solicitud de curso','../images/request-of-course.png');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
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

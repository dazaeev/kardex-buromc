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
-- Table structure for table `parameter`
--

DROP TABLE IF EXISTS `parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameter` (
  `id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parameter`
--

LOCK TABLES `parameter` WRITE;
/*!40000 ALTER TABLE `parameter` DISABLE KEYS */;
INSERT INTO `parameter` VALUES (1,1,'Tiempo de expiración en certificación','date-expiration','92'),(2,1,'Tiempo para poder solicitar vacaciones (dias)','date-vacation','331'),(100,1,'IP de Office 365','EMAIL-SETHOSTNAME','smtp.office365.com'),(101,1,'Puerto del correo','EMAIL-SETSMTPPORT','587'),(102,1,'Usuario de Office 365','EMAIL-USERNAME','ngonzalez@buromc.com'),(103,1,'Contraseña de Office 365','EMAIL-KEY','NazarioDaza33v123'),(104,1,'TLS','EMAIL-SETSTARTTLSENABLED','1'),(105,1,'Origen de envio','EMAIL-SETFROM','ngonzalez@buromc.com'),(106,1,'Muestra en consola el proceso de envio de correo','EMAIL-SETDEBUG','1'),(107,1,'Plantilla para vencimiento de correo','EMAIL-CORREO','Estimado colaborador:</br>Se te informa que el registro de tus certificaciones estan prontas a vencer.</br>Sin más por el momento quedo a sus órdenes.'),(108,1,'Destinatario','EMAIL-TO','ngonzalez@buromc.com'),(200,1,'Certificaciones prontas a vencer','JOB-CLOSE-CERTIFICATION','Certificaciones prontas a vencer'),(201,1,'Certificaciones Vencidas','JOB-CLOSE-CERTIFICATION-EXPIRATION','Certificaciones Vencidas'),(900,1,'Jni JAVA para LDAP','LDAP-INITIAL-CONTEXT-FACTORY','com.sun.jndi.ldap.LdapCtxFactory'),(901,1,'Tipo de Conexion','LDAP-SECURITY-AUTHENTICATION','Simple'),(902,1,'Nombre Comun (Usuario)','LDAP-SECURITY-PRINCIPAL','Administrador de Desarrollo'),(903,1,'Clabe','LDAP-SECURITY-CREDENTIALS','Eslmqcerd2019k'),(904,1,'Direccion LDAP','LDAP-PROVIDER-URL','ldap://buromc.mx:389'),(905,1,'Remision','LDAP-REFERRAL','follow'),(906,1,'Control de Dominio','LDAP_DC','DC=buromc,DC=mx'),(907,1,'Unidad Organizacional','LDAP_OU','OU=TI'),(908,1,'Atributo para buscar en LDAP','LDAP_SAMA','sAMAccountName'),(909,1,'Atributo de formulario de LOGIN email','LDAP_PEMAIL','4TDl7THv9y6CffhaPsBjKg=='),(910,1,'Atributo de formulario de LOGIN password','LDAP_PKEY','bWcJ2iIhq8wDEM8n+abWiw==');
/*!40000 ALTER TABLE `parameter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:16

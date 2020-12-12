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
-- Table structure for table `temp_details`
--

DROP TABLE IF EXISTS `temp_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_details` (
  `t_schema` varchar(45) NOT NULL,
  `t_table` varchar(45) NOT NULL,
  `t_field` varchar(45) NOT NULL,
  `t_file` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp_details`
--

LOCK TABLES `temp_details` WRITE;
/*!40000 ALTER TABLE `temp_details` DISABLE KEYS */;
INSERT INTO `temp_details` VALUES ('test','catalog_area','description','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_area__09948388794388984_9.csv'),('test','catalog_area','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_area__05441025724113943_9.csv'),('test','catalog_area','value','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_area__07359962544739211_9.csv'),('test','catalog_fase','description','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_fase__004767358586906789_2.csv'),('test','catalog_fase','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_fase__0030379196657221018_4.csv'),('test','catalog_fase_block','description','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_fase_block__000887525641254639_1.csv'),('test','catalog_fase_block_technology','product','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_fase_block_technology__09532387228247139_30.csv'),('test','catalog_fase_block_technology','technology','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_fase_block_technology__07395678756195753_26.csv'),('test','catalog_work_place','description','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_work_place__08381232403573796_33.csv'),('test','catalog_work_place','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_work_place__09719126056618175_33.csv'),('test','catalog_work_place','value','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/catalog_work_place__03451933379705933_33.csv'),('test','certification_track','area','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/certification_track__08102289036011593_1.csv'),('test','certification_track','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/certification_track__0015564534827661267_2.csv'),('test','employee_demographics','colony','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_demographics__06471359940684736_1.csv'),('test','employee_demographics','delegation_municipality','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_demographics__018898639659302904_1.csv'),('test','employee_gral','email_personal','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_gral__00035240314933695192_1.csv'),('test','employee_gral','nationality','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_gral__045066099842140545_1.csv'),('test','employee_labor','business_mail','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_labor__024273292836056365_1.csv'),('test','employee_labor','position','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_labor__08616816772722469_1.csv'),('test','employee_message','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_message__05802096320131883_3.csv'),('test','employee_notification','value','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_notification__03160031589828461_98.csv'),('test','employee_studies','educational_level_latest','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_studies__08393869296083105_1.csv'),('test','employee_studies','educational_level_previous','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_studies__024892520182665923_1.csv'),('test','employee_studies','status_latest','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_studies__07264652510420095_1.csv'),('test','employee_studies','status_previous','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/employee_studies__08855506804637152_1.csv'),('test','history_bonus','concept_bonus','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/history_bonus__02483576799261921_1.csv'),('test','history_bonus','status','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/history_bonus__058513638897346_2.csv'),('test','history_loan','concept_loan','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/history_loan__018060893582236853_3.csv'),('test','mail_template','body','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/mail_template__01476355429251078_6.csv'),('test','mail_template','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/mail_template__019635093789207836_6.csv'),('test','menu','html','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/menu__05388480914187134_21.csv'),('test','menu','menu','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/menu__010518767415097698_21.csv'),('test','menu','sub_menu','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/menu__09093941272323897_17.csv'),('test','menu','href','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/menu__023140764444266226_21.csv'),('test','menu','title','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/menu__04288558712497874_17.csv'),('test','menu','image','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/menu__04500564536545951_21.csv'),('test','parameter','description','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/parameter__09637146852572586_17.csv'),('test','parameter','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/parameter__046840409605615224_24.csv'),('test','parameter','value','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/parameter__045087645524263054_13.csv'),('test','request_of_courses','company_2','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/request_of_courses__08491700187783409_2.csv'),('test','request_of_courses','company_3','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/request_of_courses__0893220758897458_2.csv'),('test','request_of_courses','cost_w_tax','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/request_of_courses__09185937688859122_2.csv'),('test','request_of_courses','cost_wo_tax','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/request_of_courses__09133065984708318_2.csv'),('test','request_of_courses','justification_2','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/request_of_courses__08107517164300677_2.csv'),('test','request_of_courses','justification_3','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/request_of_courses__031383881747148823_2.csv'),('test','request_of_courses','name_request_program','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/request_of_courses__013693896880088277_2.csv'),('test','role','description','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/role__07431784223235942_2.csv'),('test','role','role','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/role__030507523594896796_1.csv'),('test','temp_details','t_table','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/temp_details__0295840943507702_27.csv'),('test','temp_details','t_field','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/temp_details__05639790404252513_31.csv'),('test','temp_details','t_file','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/temp_details__09323724023367953_50.csv'),('test','user','email','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/user__09699249211418675_4.csv'),('test','user','last_name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/user__005250742943259648_4.csv'),('test','user','name','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/user__0352762414471025_3.csv'),('test','user','password','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/user__06062898147909808_4.csv'),('test','user','account','/opt/filesystem/kardex/db/ngonzalez_buromc_mx/user__09731618612754734_4.csv');
/*!40000 ALTER TABLE `temp_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-02 14:30:09

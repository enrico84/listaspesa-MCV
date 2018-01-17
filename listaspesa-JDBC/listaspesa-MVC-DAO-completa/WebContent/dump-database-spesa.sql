-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: spesa
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Current Database: `listaspesa`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `listaspesa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `listaspesa`;

--
-- Table structure for table `lista`
--

DROP TABLE IF EXISTS `spesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spesa` (
  `idlista` int(11) NOT NULL AUTO_INCREMENT,
  `voce` varchar(50) NOT NULL COMMENT 'voce inserita dall''utente',
  `data` datetime DEFAULT NULL,
  `ord` int(11) NOT NULL COMMENT 'numero d''ordine della voce (a partire da 0)',
  PRIMARY KEY (`idlista`),
  UNIQUE KEY `ord_UNIQUE` (`ord`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista`
--


/*!40000 ALTER TABLE `spesa` DISABLE KEYS */;
INSERT INTO `spesa` VALUES (1,'uno','2011-12-09 00:00:00',1),(2,'seconda','2011-12-09 00:00:00',2),(3,'latte','2011-12-09 00:00:00',4),(4,'prosciutto','2011-12-09 00:00:00',3),(5,'prova',NULL,5),(7,'prova','2011-12-09 14:20:06',6),(8,'fortuna','2011-12-09 14:27:46',7),(9,'xxxx','2011-12-09 14:27:58',8),(10,'olio d\'oliva','2011-12-09 14:28:10',9),(11,'abcd ','2011-12-09 14:30:33',10);
/*!40000 ALTER TABLE `spesa` ENABLE KEYS */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-12-09 14:34:19

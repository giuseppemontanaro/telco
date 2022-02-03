-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: Telco
-- ------------------------------------------------------
-- Server version	5.7.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `isInsolvent` tinyint(1) DEFAULT NULL,
  `isEmployee` tinyint(1) NOT NULL,
  `eMail` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Username_UNIQUE` (`username`),
  UNIQUE KEY `eMail_UNIQUE` (`eMail`)
) ENGINE=InnoDB AUTO_INCREMENT=123457 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'test','test',1,1,'test@mail.it'),(85,'TEST85','test85',0,0,'test85@gmail.com'),(100,'test100','test100',1,1,'test100@mail.it'),(120,'test120','test2',1,1,'test120@mail.it'),(185,'TEST185','test185',0,0,'test185@gmail.com'),(401,'test401','test31',1,1,'test401@mail.it'),(1000,'test1000','test1000',1,1,'test1000@mail.it'),(1001,'test1234','test1244',1,1,'test1234@mail.it'),(123456,'test2349','test239',0,0,'test888@gmail.com');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_product` (
  `order_fk` int(11) NOT NULL,
  `product_fk` int(11) NOT NULL,
  KEY `this_order_idx` (`order_fk`),
  KEY `this_product_idx` (`product_fk`),
  CONSTRAINT `this_order` FOREIGN KEY (`order_fk`) REFERENCES `ordering` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `this_product` FOREIGN KEY (`product_fk`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (3,1);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `monthly_fee` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,10,'test1'),(3,10,'test2');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `total` float NOT NULL,
  `status` varchar(45) NOT NULL,
  `subscription_date` datetime NOT NULL,
  `date` datetime NOT NULL,
  `service_pkg_fk` int(11) DEFAULT NULL,
  `user_foreignk` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `service_pkg_fk_idx` (`service_pkg_fk`),
  KEY `user_foreignk_idx` (`user_foreignk`),
  CONSTRAINT `service_pkg_fk` FOREIGN KEY (`service_pkg_fk`) REFERENCES `service_package` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_foreignk` FOREIGN KEY (`user_foreignk`) REFERENCES `User` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,10,'OK','2000-10-10 00:00:00','2000-10-10 00:00:00',NULL,1),(2,4,'OK','2020-10-03 00:00:00','2020-10-03 00:00:00',NULL,1),(3,4,'OK','2020-10-03 00:00:00','2020-10-03 00:00:00',NULL,NULL),(5,10,'ok','2022-01-21 00:00:00','2022-01-21 00:00:00',1,1),(100,100,'ok','2022-02-01 00:00:00','2022-02-01 00:00:00',2,1000);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `gigabytes` int(11) DEFAULT NULL,
  `gigabytes_extra_fee` float DEFAULT NULL,
  `SMS` int(11) DEFAULT NULL,
  `SMS_extra_fee` float DEFAULT NULL,
  `minutes` int(11) DEFAULT NULL,
  `minutes_extra_fee` float DEFAULT NULL,
  `category` enum('fixed_internet','mobile_internet','fixed_phone','mobile_phone') DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1022 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (2,'test2',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1021,'tEst',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package`
--

DROP TABLE IF EXISTS `service_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `package_user_fk` FOREIGN KEY (`user_id`) REFERENCES `User` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package`
--

LOCK TABLES `service_package` WRITE;
/*!40000 ALTER TABLE `service_package` DISABLE KEYS */;
INSERT INTO `service_package` VALUES (1,'service1',1),(2,'service2',120);
/*!40000 ALTER TABLE `service_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package_product`
--

DROP TABLE IF EXISTS `service_package_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package_product` (
  `service_package_fk` int(11) NOT NULL,
  `product_fk` int(11) NOT NULL,
  PRIMARY KEY (`service_package_fk`,`product_fk`),
  KEY `this_package_fk_idx` (`service_package_fk`),
  KEY `this_product_fk_idx` (`product_fk`),
  CONSTRAINT `this_package_fk` FOREIGN KEY (`service_package_fk`) REFERENCES `service_package` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `this_product_fk` FOREIGN KEY (`product_fk`) REFERENCES `product` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package_product`
--

LOCK TABLES `service_package_product` WRITE;
/*!40000 ALTER TABLE `service_package_product` DISABLE KEYS */;
INSERT INTO `service_package_product` VALUES (1,1),(1,3),(2,3);
/*!40000 ALTER TABLE `service_package_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package_service`
--

DROP TABLE IF EXISTS `service_package_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package_service` (
  `service_package_fk` int(11) NOT NULL,
  `service_fk` varchar(45) NOT NULL,
  PRIMARY KEY (`service_package_fk`,`service_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package_service`
--

LOCK TABLES `service_package_service` WRITE;
/*!40000 ALTER TABLE `service_package_service` DISABLE KEYS */;
INSERT INTO `service_package_service` VALUES (2,'2');
/*!40000 ALTER TABLE `service_package_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_package_validity_period`
--

DROP TABLE IF EXISTS `service_package_validity_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_package_validity_period` (
  `service_package_fk` int(11) NOT NULL,
  `validity_period_fk` int(11) NOT NULL,
  PRIMARY KEY (`service_package_fk`,`validity_period_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_package_validity_period`
--

LOCK TABLES `service_package_validity_period` WRITE;
/*!40000 ALTER TABLE `service_package_validity_period` DISABLE KEYS */;
INSERT INTO `service_package_validity_period` VALUES (2,1);
/*!40000 ALTER TABLE `service_package_validity_period` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validity_period`
--

DROP TABLE IF EXISTS `validity_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validity_period` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `month_number` int(11) DEFAULT NULL,
  `monthly_fee` int(11) DEFAULT NULL,
  `package_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  KEY `package_id_idx` (`package_id`),
  CONSTRAINT `validity_package_fk` FOREIGN KEY (`package_id`) REFERENCES `service_package` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validity_period`
--

LOCK TABLES `validity_period` WRITE;
/*!40000 ALTER TABLE `validity_period` DISABLE KEYS */;
INSERT INTO `validity_period` VALUES (1,2,3,1);
/*!40000 ALTER TABLE `validity_period` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-03 21:27:29

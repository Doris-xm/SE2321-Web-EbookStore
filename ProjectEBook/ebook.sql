-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ebook
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `addressID` mediumtext NOT NULL,
  `userID` mediumtext NOT NULL,
  `address` text NOT NULL,
  `phone` text NOT NULL,
  `receiver` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL,
  `title` text NOT NULL,
  `author` text NOT NULL,
  `price` double NOT NULL,
  `cover` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'The Great Gatsby','F. Scott Fitzgerald',9.99,'https://s1.ax1x.com/2023/04/06/ppIWwC9.jpg'),(2,'To Kill a Mockingbird','Harper Lee',8.99,'https://s1.ax1x.com/2023/04/06/ppIWgED.jpg'),(3,'The Catcher in the Rye','J. D. Salinger',7.99,'https://s1.ax1x.com/2023/04/06/ppIfTzR.png'),(4,'The Grapes of Wrath','John Steinbeck',16.99,'https://s1.ax1x.com/2023/04/06/ppIfTzR.png'),(5,'The Lord of the Rings','J. R. R. Tolkien',25.99,'https://s1.ax1x.com/2023/04/06/ppIfTzR.png'),(6,'The Hobbit','J. R. R. Tolkien',24.99,'https://s1.ax1x.com/2023/04/06/ppIfTzR.png'),(7,'The Adventures of Huckleberry Finn','Mark Twain',13.99,'https://s1.ax1x.com/2023/04/06/ppIfTzR.png'),(8,'The Kite Runner','Khaled Hosseini',20.99,'https://s1.ax1x.com/2023/04/06/ppIfTzR.png');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookorder`
--

DROP TABLE IF EXISTS `bookorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookorder` (
  `orderID` mediumtext NOT NULL,
  `totalPrice` double NOT NULL,
  `bookID` mediumtext NOT NULL,
  `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookorder`
--

LOCK TABLES `bookorder` WRITE;
/*!40000 ALTER TABLE `bookorder` DISABLE KEYS */;
INSERT INTO `bookorder` VALUES ('1',83.94,'7',6),('5',83.96,'8',4),('2',169.9,'4',10),('8',103.96,'5',4),('7',77.97,'5',3),('6',20.99,'8',1),('10',29.97,'1',3),('8',49.95,'1',5),('6',35.96,'2',4),('4',101.94,'4',6),('1',111.92,'7',8),('3',63.92,'3',8),('9',51.98,'5',2),('2',50.97,'4',3),('3',47.94,'3',6);
/*!40000 ALTER TABLE `bookorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myorder`
--

DROP TABLE IF EXISTS `myorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myorder` (
  `orderID` mediumtext NOT NULL,
  `userID` mediumtext NOT NULL,
  `state` int NOT NULL COMMENT '0:not pay,1:paid,2:sent,3:reached,4:signed,5:finished(comment)',
  `address` text NOT NULL,
  `totalprice` double NOT NULL,
  `createtime` datetime NOT NULL,
  `finishtime` datetime DEFAULT NULL,
  `comment` mediumtext,
  PRIMARY KEY (`orderID`(20))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myorder`
--

LOCK TABLES `myorder` WRITE;
/*!40000 ALTER TABLE `myorder` DISABLE KEYS */;
INSERT INTO `myorder` VALUES ('1','2',5,'中国IFS市IFS区',195.68,'2022-07-26 19:49:20','2023-04-18 19:49:55','一般'),('10','2',5,'中国江汉路步行街市江汉路步行街区',29.97,'2022-09-22 19:49:20','2023-04-18 19:50:00','一般'),('2','1',5,'中国观音桥步行街市观音桥步行街区',220.87,'2020-12-17 19:49:20','2023-05-18 19:50:01','一般'),('3','2',5,'中国金鸡湖市金鸡湖区',111.86,'2021-07-13 19:49:20','2023-02-18 19:50:06','一般'),('4','1',3,'中国陆家嘴市陆家嘴区',101.94,'2022-09-15 19:49:20',NULL,''),('5','1',1,'中国科技园市科技园区',83.96,'2022-10-28 19:49:20',NULL,''),('6','2',3,'中国珠江新城市珠江新城区',56.95,'2021-10-29 19:49:20',NULL,''),('7','1',3,'中国中山陵市中山陵区',77.97,'2021-08-07 19:49:20',NULL,''),('8','2',2,'中国西湖市西湖区',153.91,'2022-05-02 19:49:20',NULL,''),('9','2',4,'中国三里屯SOHO市三里屯SOHO区',51.98,'2022-12-24 19:49:20',NULL,'');
/*!40000 ALTER TABLE `myorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-22 12:59:21

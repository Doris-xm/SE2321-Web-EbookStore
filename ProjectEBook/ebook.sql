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
INSERT INTO `book` VALUES (1,'The Great Gatsby','F. Scott Fitzgerald',9.99,'https://th.bing.com/th/id/OIP.fFaX7nKq5_5gf2nSI3QEUgHaLK?pid=ImgDet&rs=1'),(2,'To Kill a Mockingbird','Harper Lee',8.99,'https://cdn2.penguin.com.au/covers/original/9781785151552.jpg'),(3,'The Catcher in the Rye','J. D. Salinger',7.99,'https://th.bing.com/th/id/OIP.PQKfkZFKgDWeNleDPTi9dwAAAA?pid=ImgDet&rs=1'),(4,'The Grapes of Wrath','John Steinbeck',16.99,'https://image.tmdb.org/t/p/original/jRbRDNi9MgvhRc68PSQPMqnG24x.jpg'),(5,'The Lord of the Rings','J. R. R. Tolkien',25.99,'https://d28hgpri8am2if.cloudfront.net/book_images/onix/interior_spreads/9781608873821/the-lord-of-the-rings-9781608873821.in02.jpg'),(6,'The Hobbit','J. R. R. Tolkien',24.99,'https://th.bing.com/th/id/OIP.iSZwVmLboV6alS04ujebKAHaLH?pid=ImgDet&rs=1'),(7,'The Adventures of Huckleberry Finn','Mark Twain',13.99,'https://th.bing.com/th/id/OIP.INzCsbWAEfv9ZNhO2N8N_QHaLc?pid=ImgDet&rs=1'),(8,'The Kite Runner','Khaled Hosseini',20.99,'https://th.bing.com/th/id/OIP.16aZmdjT01t2Mybn0vu83wHaLt?pid=ImgDet&rs=1');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookorder`
--

DROP TABLE IF EXISTS `bookorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookorder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total_price` double NOT NULL,
  `bookID` int NOT NULL,
  `quantity` int NOT NULL,
  `orderID` int NOT NULL,
  UNIQUE KEY `bookorder_pk` (`id`),
  KEY `bookorder_myorder_orderID_fk` (`orderID`),
  CONSTRAINT `bookorder_myorder_orderID_fk` FOREIGN KEY (`orderID`) REFERENCES `myorder` (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookorder`
--

LOCK TABLES `bookorder` WRITE;
/*!40000 ALTER TABLE `bookorder` DISABLE KEYS */;
INSERT INTO `bookorder` VALUES (1,83.94,7,6,1),(2,83.96,8,4,5),(3,169.9,4,10,2),(4,103.96,5,4,8),(5,77.97,5,3,7),(6,20.99,8,1,6),(7,29.97,1,3,10),(8,49.95,1,5,8),(9,35.96,2,4,6),(10,101.94,4,6,4),(12,63.92,3,8,3),(13,51.98,5,2,9),(25,29.97,1,3,16),(26,33.98,4,2,16),(27,29.97,1,3,17),(34,23.97,3,3,25),(35,9.99,1,1,25),(36,19.98,1,2,26),(37,7.99,3,1,26);
/*!40000 ALTER TABLE `bookorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `bookID` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cart_user_id_fk` (`userID`),
  CONSTRAINT `cart_user_id_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (46,2,2,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `myorder`
--

DROP TABLE IF EXISTS `myorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `myorder` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `userID` int NOT NULL,
  `state` int NOT NULL COMMENT '0:not pay,1:paid,2:sent,3:reached,4:signed,5:finished(comment)',
  `address` text NOT NULL,
  `total_price` decimal(10,0) NOT NULL,
  `createtime` datetime NOT NULL,
  `finishtime` datetime DEFAULT NULL,
  `comment` mediumtext,
  `receiver` text NOT NULL,
  `phone` varchar(11) NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `myorder_user_id_fk` (`userID`),
  CONSTRAINT `myorder_user_id_fk` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myorder`
--

LOCK TABLES `myorder` WRITE;
/*!40000 ALTER TABLE `myorder` DISABLE KEYS */;
INSERT INTO `myorder` VALUES (1,2,5,'中国IFS市IFS区',196,'2022-07-26 19:49:20','2023-04-18 19:49:55','一般','123','1331331333'),(2,1,5,'中国观音桥步行街市观音桥步行街区',221,'2020-12-17 19:49:20','2023-05-18 19:50:01','一般','123','1331331333'),(3,2,5,'中国金鸡湖市金鸡湖区',112,'2021-07-13 19:49:20','2023-02-18 19:50:06','一般','123','1331331333'),(4,1,3,'中国陆家嘴市陆家嘴区',102,'2022-09-15 19:49:20',NULL,'','123','1331331333'),(5,1,1,'中国科技园市科技园区',84,'2022-10-28 19:49:20',NULL,'','123','1331331333'),(6,2,3,'中国珠江新城市珠江新城区',57,'2021-10-29 19:49:20',NULL,'','123','1331331333'),(7,1,3,'中国中山陵市中山陵区',78,'2021-08-07 19:49:20',NULL,'','123','1331331333'),(8,2,2,'中国西湖市西湖区',154,'2022-05-02 19:49:20',NULL,'','123','1331331333'),(9,2,4,'中国三里屯SOHO市三里屯SOHO区',52,'2022-12-24 19:49:20',NULL,'','123','1331331333'),(10,2,5,'中国江汉路步行街市江汉路步行街区',30,'2022-09-22 19:49:20','2023-04-18 19:50:00','一般','123','1331331333'),(16,1,1,'中国上海徐汇区',64,'2023-05-04 00:29:52',NULL,NULL,'123','1331331333'),(17,1,1,'中国上海长宁区',30,'2023-05-04 00:42:24',NULL,NULL,'123','1331331333'),(25,2,1,'中国浙江杭州',34,'2023-05-06 10:11:48',NULL,NULL,'陈浩鹏','13313313313'),(26,1,1,'中国浙江杭州',28,'2023-05-06 19:43:25',NULL,NULL,'dxm','13313313313');
/*!40000 ALTER TABLE `myorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` text NOT NULL,
  `email` text,
  `years` int DEFAULT NULL,
  `introduce` text,
  `avatar` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'dxm',NULL,NULL,NULL,'https://th.bing.com/th/id/R.204074c918f10f417423666bdf7a10ea?rik=Q07O5YL2fBEe8A&riu=http%3a%2f%2fimg2.woyaogexing.com%2f2018%2f01%2f09%2f622d25573c861822!400x400_big.jpg&ehk=7GMVLkwKFoS3shKHCXBdnhxLLWZo%2b7EYJKtSc5o15KM%3d&risl=&pid=ImgRaw&r=0'),(2,'dxx',NULL,NULL,NULL,'https://s1.ax1x.com/2023/05/03/p9JJOY9.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_auth` (
  `user_id` int NOT NULL,
  `password` text NOT NULL,
  `user_name` text NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_auth_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
INSERT INTO `user_auth` VALUES (1,'dxm','dxm'),(2,'dxx','dxx');
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-07 15:03:15

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
  `id` int NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `author` text NOT NULL,
  `price` double NOT NULL,
  `cover` text NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `stocks` int NOT NULL DEFAULT '0',
  `sales` int NOT NULL DEFAULT '0',
  `introduce` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'The Great Gatsby','F. Scott Fitzgerald',9.99,'https://th.bing.com/th/id/OIP.fFaX7nKq5_5gf2nSI3QEUgHaLK?pid=ImgDet&rs=1','9781853260414',49,13,'123'),(2,'To Kill a Mockingbird','Harper Lee',8.99,'https://media.movieassets.com/static/images/items/movies/posters/410db45093c02465213de10c1fe67290.jpg','9780060935467',49,0,NULL),(3,'The Catcher in the Rye','J. D. Salinger',7.99,'https://th.bing.com/th/id/OIP.PQKfkZFKgDWeNleDPTi9dwAAAA?pid=ImgDet&rs=1','9780316769174',20,0,NULL),(4,'The Grapes of Wrath','John Steinbeck',16.99,'https://image.tmdb.org/t/p/original/jRbRDNi9MgvhRc68PSQPMqnG24x.jpg','9780143039433',10,0,NULL),(5,'The Lord of the Rings','J. R. R. Tolkien',25.99,'https://d28hgpri8am2if.cloudfront.net/book_images/onix/interior_spreads/9781608873821/the-lord-of-the-rings-9781608873821.in02.jpg','9780358439196',40,2,NULL),(6,'The Hobbit','J. R. R. Tolkien',24.99,'https://th.bing.com/th/id/OIP.iSZwVmLboV6alS04ujebKAHaLH?pid=ImgDet&rs=1','9780547928227',10,4,NULL),(7,'The Adventures of Huckleberry Finn','Mark Twain',13.99,'https://th.bing.com/th/id/OIP.INzCsbWAEfv9ZNhO2N8N_QHaLc?pid=ImgDet&rs=1','9781953649805',49,4,NULL),(8,'The Kite Runner','Khaled Hosseini',20.99,'https://th.bing.com/th/id/OIP.16aZmdjT01t2Mybn0vu83wHaLt?pid=ImgDet&rs=1','9781594631931',100,0,NULL),(9,'hihihihi','123',123,'123','123',-1,6,NULL),(10,'hihihi','阿斯顿',20.9,'https://th.bing.com/th/id/R.d23015b48a5514c8ecb8b02e94291804?rik=ZNkwjlZ%2fyhDcjw&riu=http%3a%2f%2fpic4.nipic.com%2f20091008%2f2032802_102705079548_2.jpg&ehk=BPqfvy0fl0grl3AYx0UNzjyt8HCkpGebGbmluCNgp2w%3d&risl=&pid=ImgRaw&r=0','1234567891234',-1,4,NULL),(11,'dsadf','阿斯顿',20.9,'https://th.bing.com/th/id/R.d23015b48a5514c8ecb8b02e94291804?rik=ZNkwjlZ%2fyhDcjw&riu=http%3a%2f%2fpic4.nipic.com%2f20091008%2f2032802_102705079548_2.jpg&ehk=BPqfvy0fl0grl3AYx0UNzjyt8HCkpGebGbmluCNgp2w%3d&risl=&pid=ImgRaw&r=0','1234567891234',-1,1,NULL),(12,'始于极限','[日] 上野千鹤子 / [日] 铃木凉美',59,'https://ts1.cn.mm.bing.net/th/id/R-C.300c49562f5cd9318307c4a58539dcbc?rik=kF6oyt8Mu1potA&riu=http%3a%2f%2ficdn.520mac.com%2fimages%2f8%2f2022%2f10%2fnOQn3TnZBS478k057NnJgNyz866qGY.jpg&ehk=laW0NaGMiBzBdMfxtZQqkLR4bhwssjWqA3xVwLSrVOM%3d&risl=&pid=ImgRaw&r=0','9787513349369',50,0,'始于极限》是女性主义先驱上野千鹤子与人气作家铃木凉美历时一年的通信。\n\n青春期，上野千鹤子只身前往京都求学，只为逃离父亲与教会；同时期，铃木凉美为了反抗父母，一脚踏入出卖身体的世界。\n\n大学时，上野参加轰轰烈烈的学生运动，却在战壕的另一侧目睹男生只把女生当作解决生理问题的工具；铃木就读于日本最好的私立大学，却要在夜世界寻求自身的价值。\n\n学生时代结束，上野以独立女性自居，结果成了男人挥之即来招之即去的床伴；另一头的铃木开始书写夜世界的魅惑与肮脏，时常遭受来自女性的抨击与批判。\n\n今天，上野已是日本女性学研究第一人，铃木则走到了夜世界的极限，在质疑过往、怀疑自己的同时，犹豫着下一步如何迈出。\n\n她们相差35岁，走过了迥异的人生。在长达一年的通信中，她们围绕恋爱与性、婚姻、工作、独立、男人等话题，把话语的利剑刺向对方，也刺向了自己。\n\n·\n\n每翻一页，体温也随之攀升。铃木凉美的文字，一面极其冷静地自我分析，一面又混杂着活生生的真心话，隐现着无法否定的感情。——作家岛本理生\n\n上野千鹤子解体了铃木凉美，也使得她得以摆脱母亲和男人的手，开始作为一个人生存。与此同时，这本书也是上野千鹤子向包括我在内的众多女性伸出的双手。这本书能够拯救女性。——小说家花房观音\n\n从头到尾，我就像被钝器击中了一般。仿佛有人揪着我的衣领说，“喂！别给我装作没看见！”读了这本书，我想没有女性不会成为女性主义者。——亚马逊读者\n\n我深感女人生存如何艰难。即便如此，读完这本书后，我觉得身为女人果然还是一种福音。——国际政治学家三浦瑠丽'),(13,'可能性的艺术','刘瑜',82,'https://img2.doubanio.com/view/subject/l/public/s34192061.jpg','9787559848048',382,2,'面对林林总总的政治问题，作者带领我们以一种比较的视角，在民主问责和国家能力两个政治比较的核心维度下，建立起观察的参照系，将不同体制、不同经济发展水平的国家纳入比较的视野，去分析我们的时代背景和全球化进程，讨论不同国家的政治转型与国家能力，以及文化和经济对政治变迁的影响。\n\n“政治是可能性的艺术。”当我们将面对的政治现实当作一万种可能性之一来对待时，就能从此时此地抽离，获得一种俯瞰的视角，进而再聚焦定位现实，在浩瀚的可能性中理解我们自身。'),(14,'足利女童连续失踪事件','[日] 清水洁',49,'https://img2.doubanio.com/view/subject/l/public/s34249411.jpg','9787549637317',19,1,'连环女童杀人犯还在外面，你正在和凶手擦肩而过！\n\n★《桶川跟踪狂杀人事件》作者清水洁又一高口碑话题力作！\n\n☆ 清水洁“推理反转式”纪实报道代表作，日本版《杀人回忆》。\n\n☆ 一本书“改写”现实——17年冤案获得重审，蒙冤者无罪释放。'),(15,' 如雪如山','张天翼',45,'https://img2.doubanio.com/view/subject/l/public/s34201041.jpg','9787020166930',60,0,'生活中，“雪”与“山”，都是极其常见却又无法忽视 之物。那如雪般细碎的日常和如山般刻骨的过往，几乎贯穿着每个女性的生命记忆。《如雪如山》正是一个个以女性视角讲述的关于女性生存故事的隐喻。'),(16,'我本芬芳','杨本芬',39.8,'https://img2.doubanio.com/view/subject/l/public/s34072342.jpg','9787559657275',49,1,'世界看不见我，但我看见我。中国式婚姻里，还有多少看不见的女人？继《秋园》《浮木》后，八旬奶奶讲述六十年婚姻故事，写尽那些无人知晓的伤痛与困惑，带给万千女性共鸣与勇气，献给所有不被看见的你我她。'),(17,'看不见的女性','卡罗琳·克里亚多·佩雷斯',69,'https://pic.arkread.com/cover/ebook/f/403037483.1658987012.jpg!cover_default.jpg','9787513349611',49,1,'这本书没有咆哮，只有事实和数字。在告诉我父权制是我想象出来的之前，请你先读读这本书。《看不见的女性》充满启示，令人恐惧，又充满希望。堪称一部现世的《圣经》。  ——珍妮特·温特森，英国知名作家  卡罗琳·克里亚多·佩雷斯简直是掌握数据的西蒙娜·德·波伏瓦。  ——莱昂内尔·巴伯，《金融时报》前主编'),(18,'应得的权利','［澳］凯特·曼恩 / 凯特·曼恩',58,'https://img2.doubanio.com/view/subject/l/public/s34207961.jpg','9787559660947',60,0,'《应得的权利》是康奈尔大学哲学系副教授凯特·曼恩的振聋发聩之作。在本书中，她融合女性主义、哲学、心理学、社会学等知识，犀利地剖析了近些年来引起高度关注的社会事件，案例涉及医疗系统、家务劳动、性暴力、公共事务等多方面，揭示了自由平等表象之下，处处可见的性别不正义现象。从中我们将看到，男性如何仅仅因为性别而获得系统性的优势和特权，从而打压女性应得的权利，并对女性造成伤害。'),(19,'长安的荔枝','马伯庸',45,'https://img2.doubanio.com/view/subject/l/public/s34327482.jpg','9787572608582',59,1,'唐朝诗人杜牧的一句“一骑红尘妃子笑，无人知是荔枝来”一千多年来引发了人们的无限遐想，但鲜荔枝的保鲜时限仅有三天，这场跨越五千余里的传奇转运之旅究竟是如何达成的，谁让杨贵妃在长安吃到了来自岭南的鲜荔枝？作者马伯庸就此展开了一场脑洞非常大的想象。  沿袭马伯庸写作一贯以来的时空紧张感，不仅让读者看到了小人物的乱世生存之道，也感受到了事在人为的热血奋斗。随书附赠“荔枝鲜转运舆图”。');
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookorder`
--

LOCK TABLES `bookorder` WRITE;
/*!40000 ALTER TABLE `bookorder` DISABLE KEYS */;
INSERT INTO `bookorder` VALUES (38,49.95,1,5,27),(39,51.98,5,2,28),(40,99.96,6,4,29),(41,41.97,7,3,30),(42,69.93,1,7,31),(43,83.6,10,4,32),(44,20.9,11,1,32),(45,738,9,6,33),(46,45,19,1,34),(47,49,14,1,34),(48,164,13,2,35),(49,39.8,16,1,35),(50,9.99,1,1,35),(51,13.99,7,1,35),(52,69,17,1,35);
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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (59,3,1,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `myorder`
--

LOCK TABLES `myorder` WRITE;
/*!40000 ALTER TABLE `myorder` DISABLE KEYS */;
INSERT INTO `myorder` VALUES (27,1,2,'中国江苏无锡',50,'2023-06-09 04:20:28',NULL,NULL,'无锡人','13665122739'),(28,1,1,'中国浙江杭州',52,'2023-06-13 04:48:31',NULL,NULL,'dxm','13665122739'),(29,1,1,'中国上海普陀区',100,'2023-06-07 05:00:12',NULL,NULL,'dxm','13555678951'),(30,2,1,'中国浙江杭州',42,'2023-05-20 05:09:45',NULL,NULL,'dxm','13665122739'),(31,2,1,'中国江苏徐州',70,'2023-05-25 05:22:30',NULL,NULL,'dxm','13665122739'),(32,5,1,'中国上海崇明区',105,'2023-06-10 05:46:26',NULL,NULL,'dxm','13665122739'),(33,7,1,'中国浙江宁波',738,'2023-06-16 05:50:16',NULL,NULL,'dxm','13665122739'),(34,1,1,'中国江苏常州',94,'2023-06-02 06:14:36',NULL,NULL,'dxm','13665122739'),(35,1,1,'中国上海闵行区',297,'2023-06-04 16:42:27',NULL,NULL,'12345','13345645612');
/*!40000 ALTER TABLE `myorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nickname` text NOT NULL,
  `email` text,
  `years` int DEFAULT NULL,
  `introduce` text,
  `avatar` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'dxm','dxm@sjtu',NULL,NULL,'https://th.bing.com/th/id/R.204074c918f10f417423666bdf7a10ea?rik=Q07O5YL2fBEe8A&riu=http%3a%2f%2fimg2.woyaogexing.com%2f2018%2f01%2f09%2f622d25573c861822!400x400_big.jpg&ehk=7GMVLkwKFoS3shKHCXBdnhxLLWZo%2b7EYJKtSc5o15KM%3d&risl=&pid=ImgRaw&r=0'),(2,'dxx','dxx@sjtu',NULL,NULL,'https://s1.ax1x.com/2023/05/03/p9JJOY9.png'),(3,'root','root@sjtu',NULL,NULL,'https://s1.ax1x.com/2023/05/03/p9JJOY9.png'),(4,'cuckoo','cuckoo@sjtu.eu.cn',NULL,NULL,'https://pic2.zhimg.com/v2-76a083febca664209097391ed4750769_r.jpg'),(5,'splay','splay@sjtu',NULL,NULL,'https://pic2.zhimg.com/v2-76a083febca664209097391ed4750769_r.jpg'),(6,'hash','hash@sjtu',NULL,NULL,'https://pic2.zhimg.com/v2-76a083febca664209097391ed4750769_r.jpg'),(7,'user1','user1@sjtu.edu.cn',NULL,NULL,'https://pic2.zhimg.com/v2-76a083febca664209097391ed4750769_r.jpg');
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
  `user_mode` int NOT NULL,
  `is_ban` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_auth_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
INSERT INTO `user_auth` VALUES (1,'dxm','dxm',0,0),(2,'dxx','dxx',0,0),(3,'root','root',1,0),(4,'cuckoo','cuckoo',0,0),(5,'splay','splay',0,0),(6,'hash','hash',0,0),(7,'user1','user1',0,0);
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

-- Dump completed on 2023-06-21 23:52:45

-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: diy
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `youtube_video`
--

DROP TABLE IF EXISTS `youtube_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `youtube_video` (
  `y_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `q_id` int(11) DEFAULT NULL,
  `date_entered` date DEFAULT NULL,
  PRIMARY KEY (`y_id`),
  KEY `user_id` (`user_id`),
  KEY `q_id` (`q_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `youtube_video`
--

LOCK TABLES `youtube_video` WRITE;
/*!40000 ALTER TABLE `youtube_video` DISABLE KEYS */;
INSERT INTO `youtube_video` VALUES (1,6,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',2,'2020-10-11'),(2,3,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',7,'2020-10-11'),(3,7,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',7,'2020-10-11'),(4,8,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',8,'2020-10-11'),(5,9,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',4,'2020-10-11'),(6,3,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',6,'2020-10-11'),(7,4,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',4,'2020-10-11'),(8,7,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',2,'2020-10-11'),(9,8,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',6,'2020-10-11'),(10,2,'https://www.youtube.com/embed/eXTiiz99p9o','How to get fit','Simple technique to lose weight within a short timeframe',7,'2020-10-11');
/*!40000 ALTER TABLE `youtube_video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-23 20:04:28

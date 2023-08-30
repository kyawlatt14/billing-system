-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: bill_system_db
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'test-top-up','Top UP');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay`
--

DROP TABLE IF EXISTS `pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` bigint NOT NULL,
  `api_caller` varchar(255) DEFAULT NULL,
  `bill_id` int DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `reference_no` varchar(255) DEFAULT NULL,
  `transaction_date` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay`
--

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;
INSERT INTO `pay` VALUES (1,1000,'Citizen Pay',1,'959698648953','kkh123456',1693380443);
/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLeWF0IEhsYXciLCJpYXQiOjE2OTMzODAzNTMsImV4cCI6MTY5MzM4MDUzM30.iBtl62yzBbTk8VFSI3CcZWUTXcgMsI2a913MspX_tHM','BEARER',1),(2,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLeWF3IEt5YXcgTGF0dCIsImlhdCI6MTY5MzM4MDM3MiwiZXhwIjoxNjkzMzgwNTUyfQ.jkeDppVGgrnoIEpUTgs_qMrFqiu4F_nFaEX60ocHQZE','BEARER',2),(3,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLeWF0IEhsYXciLCJpYXQiOjE2OTMzODAzNzcsImV4cCI6MTY5MzM4MDU1N30.RLbXQxXG59gIf-XRiBMS37k5FQ8BNEXAemZC86lGozI','BEARER',1),(4,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLeWF3IEt5YXcgTGF0dCIsImlhdCI6MTY5MzM4MDQwMSwiZXhwIjoxNjkzMzgwNTgxfQ.OhTkgf67NvLjE_v_XHvWMqC4Qy7yUtGPG96YXD2SR14','BEARER',2),(5,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLeWF3IEt5YXcgTGF0dCIsImlhdCI6MTY5MzM4MDU0OSwiZXhwIjoxNjkzMzgwNzI5fQ.zouXT3TnHYLfNtceA7w3Bs04pFtawZykqqpGKL1j1Kg','BEARER',2),(6,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJLeWF0IEhsYXciLCJpYXQiOjE2OTMzODA1OTksImV4cCI6MTY5MzM4MDc3OX0.X_P8Vm2BOD9Q13r84WsPozU3vBkNJwwvnpJjkZ2zPhU','BEARER',1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` bigint DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1693380353,'hyathlaw14994@gmail.com','$2a$10$BSQ9dcLjGHb0JUe.5MYbxOUN.y3DaXDnH8IWp1L94DhYT8LhmUxVG','********953','USER','Kyat Hlaw'),(2,1693380372,'kyawlatt14994@gmail.com','$2a$10$UfWOSrKnxIpEbToaH3BuxeyT2oPeZKjyjO0ZRK7R2wWoCgG7p9.kW','********2','ADMIN','Kyaw Kyaw Latt');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-30 14:04:22

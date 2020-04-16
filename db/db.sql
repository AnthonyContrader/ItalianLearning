-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: sampledb
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `findAWord`
--

DROP TABLE IF EXISTS `findAWord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `findAWord` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `solution` varchar(32) NOT NULL,
  `definition` varchar(255) NOT NULL,
  `sentence` varchar(255) NOT NULL,
  `idLevel` int NOT NULL,
  `idCategory` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategory` (`idCategory`),
  KEY `idLevel` (`idLevel`),
  CONSTRAINT `findaword_ibfk_1` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  CONSTRAINT `findaword_ibfk_2` FOREIGN KEY (`idLevel`) REFERENCES `level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `findAWord`
--

LOCK TABLES `findAWord` WRITE;
/*!40000 ALTER TABLE `findAWord` DISABLE KEYS */;
/*!40000 ALTER TABLE `findAWord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `findMistake`
--

DROP TABLE IF EXISTS `findMistake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `findMistake` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `solution` varchar(32) NOT NULL,
  `definition` varchar(255) NOT NULL,
  `sentence` varchar(255) NOT NULL,
  `optionA` varchar(50) NOT NULL,
  `optionB` varchar(50) NOT NULL,
  `optionC` varchar(50) NOT NULL,
  `idLevel` int NOT NULL,
  `idCategory` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategory` (`idCategory`),
  KEY `idLevel` (`idLevel`),
  CONSTRAINT `findmistake_ibfk_1` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  CONSTRAINT `findmistake_ibfk_2` FOREIGN KEY (`idLevel`) REFERENCES `level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `findMistake`
--

LOCK TABLES `findMistake` WRITE;
/*!40000 ALTER TABLE `findMistake` DISABLE KEYS */;
/*!40000 ALTER TABLE `findMistake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gamePlaylist`
--

DROP TABLE IF EXISTS `gamePlaylist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gamePlaylist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `typeGame` varchar(64) NOT NULL,
  `idGame` int NOT NULL,
  `idPlaylist` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idPlaylist` (`idPlaylist`),
  CONSTRAINT `gameplaylist_ibfk_1` FOREIGN KEY (`idPlaylist`) REFERENCES `playlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamePlaylist`
--

LOCK TABLES `gamePlaylist` WRITE;
/*!40000 ALTER TABLE `gamePlaylist` DISABLE KEYS */;
/*!40000 ALTER TABLE `gamePlaylist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guessPicture`
--

DROP TABLE IF EXISTS `guessPicture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guessPicture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `solution` varchar(32) NOT NULL,
  `image` longblob NOT NULL,
  `idLevel` int NOT NULL,
  `idCategory` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategory` (`idCategory`),
  KEY `idLevel` (`idLevel`),
  CONSTRAINT `guesspicture_ibfk_1` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  CONSTRAINT `guesspicture_ibfk_2` FOREIGN KEY (`idLevel`) REFERENCES `level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guessPicture`
--

LOCK TABLES `guessPicture` WRITE;
/*!40000 ALTER TABLE `guessPicture` DISABLE KEYS */;
/*!40000 ALTER TABLE `guessPicture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hangman`
--

DROP TABLE IF EXISTS `hangman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hangman` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `solution` varchar(32) NOT NULL,
  `definition` varchar(255) NOT NULL,
  `sentence` varchar(255) NOT NULL,
  `idLevel` int NOT NULL,
  `idCategory` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategory` (`idCategory`),
  KEY `idLevel` (`idLevel`),
  CONSTRAINT `hangman_ibfk_1` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  CONSTRAINT `hangman_ibfk_2` FOREIGN KEY (`idLevel`) REFERENCES `level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hangman`
--

LOCK TABLES `hangman` WRITE;
/*!40000 ALTER TABLE `hangman` DISABLE KEYS */;
/*!40000 ALTER TABLE `hangman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizeSentence`
--

DROP TABLE IF EXISTS `organizeSentence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizeSentence` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `solution` varchar(32) NOT NULL,
  `definition` varchar(255) NOT NULL,
  `sentence` varchar(255) NOT NULL,
  `idLevel` int NOT NULL,
  `idCategory` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategory` (`idCategory`),
  KEY `idLevel` (`idLevel`),
  CONSTRAINT `organizesentence_ibfk_1` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  CONSTRAINT `organizesentence_ibfk_2` FOREIGN KEY (`idLevel`) REFERENCES `level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizeSentence`
--

LOCK TABLES `organizeSentence` WRITE;
/*!40000 ALTER TABLE `organizeSentence` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizeSentence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `id` int NOT NULL AUTO_INCREMENT,
  `score` int NOT NULL,
  `solution` varchar(32) NOT NULL,
  `definition` varchar(255) NOT NULL,
  `sentence` varchar(255) NOT NULL,
  `idLevel` int NOT NULL,
  `idCategory` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCategory` (`idCategory`),
  KEY `idLevel` (`idLevel`),
  CONSTRAINT `quiz_ibfk_1` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  CONSTRAINT `quiz_ibfk_2` FOREIGN KEY (`idLevel`) REFERENCES `level` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistic`
--

DROP TABLE IF EXISTS `statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `idGame` int NOT NULL,
  `typeGame` varchar(32) NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`),
  CONSTRAINT `statistic_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistic`
--

LOCK TABLES `statistic` WRITE;
/*!40000 ALTER TABLE `statistic` DISABLE KEYS */;
/*!40000 ALTER TABLE `statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','ADMIN','admin',1),('user','USER','user',2);
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

-- Dump completed on 2020-04-16 17:53:09

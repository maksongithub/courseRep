-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: birthday_app
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `birthdays`
--

DROP TABLE IF EXISTS `birthdays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `birthdays` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='Дни рождения людей:  ИД человека - Имя человека - Дата дня рождения человека';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `birthdays`
--

LOCK TABLES `birthdays` WRITE;
/*!40000 ALTER TABLE `birthdays` DISABLE KEYS */;
INSERT INTO `birthdays` VALUES (2,'Данилов Климент Егорович','1992-10-14'),(4,'Аксёнов Давид Протасьевич','1996-05-18'),(5,'Гира Виктор Петрович','1998-03-14'),(6,'Суханов Ростислав Михаилович','1998-10-04'),(7,'Овчинников Ираклий Вячеславович','2000-04-02'),(8,'Терентьев Кондратий Всеволодович','2000-12-17'),(9,'Юдин Тимофей Пантелеймонович','2002-11-27'),(10,'Дорофеев Аскольд Ефимович','2003-12-22'),(11,'Блохин Корней Владиславович','2007-06-18'),(12,'Жиляков Александр Евгеньевич','2009-10-23'),(13,'Беляков Гурий Дмитрьевич','2009-10-25'),(14,'Уваров Вилен Парфеньевич','2010-04-16'),(15,'Рожков Зиновий Мартынович','2010-06-23'),(16,'Захаров Лаврентий Феликсович','2012-10-21'),(17,'Кузьмин Пантелеймон Витальевич','2012-12-05'),(18,'Зайцев Савелий Евгеньевич','2013-11-10'),(19,'Ларионов Аким Кириллович','2016-02-21'),(20,'Реле Игорь Николаевич','2016-03-25'),(21,'Кулаков Павел Агафонович','2016-11-21'),(22,'Бирюков Эдуард Петрович','2017-02-01'),(23,'Данилов Гаянэ Васильевич','2019-03-28'),(24,'Антонов Леонтий Геннадьевич','2020-01-23'),(25,'Орехов Ян Христофорович','2021-12-21'),(51,'Шило Андрей Афанасьевич','2000-12-02'),(53,'Никифоров Игорь Николаевич ','2001-02-02'),(54,'Ларин Иван Петрович','2000-01-01'),(55,'Минин Георгий Фаизович','1991-04-02'),(56,'Перельман Виктор Александрович','2004-01-24');
/*!40000 ALTER TABLE `birthdays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'birthday_app'
--

--
-- Dumping routines for database 'birthday_app'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-24 13:35:54

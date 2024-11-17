CREATE DATABASE  IF NOT EXISTS `club-futbol` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `club-futbol`;
-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: club-futbol
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistencia` (
                              `id_asistencia` int NOT NULL AUTO_INCREMENT,
                              `fecha` datetime DEFAULT NULL,
                              `presente` tinyint DEFAULT NULL,
                              `key_asistencia` varchar(100) DEFAULT NULL,
                              `dni` varchar(45) NOT NULL,
                              PRIMARY KEY (`id_asistencia`),
                              KEY `dni_idx` (`dni`),
                              CONSTRAINT `dni` FOREIGN KEY (`dni`) REFERENCES `jugadores` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
                             `id_categoria` int NOT NULL,
                             `descripcion_categoria` varchar(45) DEFAULT NULL,
                             `id_division` int DEFAULT NULL,
                             PRIMARY KEY (`id_categoria`),
                             KEY `id_division_idx` (`id_division`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club` (
                        `id_club` int NOT NULL,
                        `nombre` varchar(45) DEFAULT NULL,
                        `fundacion` datetime DEFAULT NULL,
                        `direccion` varchar(45) DEFAULT NULL,
                        PRIMARY KEY (`id_club`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `division` (
                            `id_division` int NOT NULL,
                            `descripcion_division` varchar(45) DEFAULT NULL,
                            PRIMARY KEY (`id_division`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entrenadores`
--

DROP TABLE IF EXISTS `entrenadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenadores` (
                                `id_entrenadores` int NOT NULL AUTO_INCREMENT,
                                `nombre` varchar(45) DEFAULT NULL,
                                `apellido` varchar(45) DEFAULT NULL,
                                `fecha_nacimiento` datetime DEFAULT NULL,
                                `is_preparador` tinyint DEFAULT NULL,
                                `años_experiencia` int DEFAULT NULL,
                                `division` varchar(45) DEFAULT NULL,
                                PRIMARY KEY (`id_entrenadores`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
                          `id_equipo` int NOT NULL AUTO_INCREMENT,
                          `id_division` int DEFAULT NULL,
                          `id_plantel` int DEFAULT NULL,
                          `id_partido` int DEFAULT NULL,
                          PRIMARY KEY (`id_equipo`),
                          KEY `id_division_idx` (`id_division`),
                          KEY `id_plantel_idx` (`id_plantel`),
                          KEY `id_partido_idx` (`id_partido`),
                          CONSTRAINT `id_division` FOREIGN KEY (`id_division`) REFERENCES `division` (`id_division`),
                          CONSTRAINT `id_partido` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id_partido`),
                          CONSTRAINT `id_plantel_fk` FOREIGN KEY (`id_plantel`) REFERENCES `plantel` (`id_plantel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores` (
                             `dni` varchar(25) NOT NULL,
                             `nombre` varchar(45) DEFAULT NULL,
                             `apellido` varchar(45) DEFAULT NULL,
                             `apto_fisico` tinyint DEFAULT NULL,
                             `id_plantel` int DEFAULT NULL,
                             `id_posicion` int DEFAULT NULL,
                             `fecha_nacimiento` datetime DEFAULT NULL,
                             `categoria` varchar(45) DEFAULT NULL,
                             PRIMARY KEY (`dni`),
                             KEY `id_plantel_idx` (`id_plantel`),
                             KEY `id_posicion_idx` (`id_posicion`),
                             CONSTRAINT `id_plantel` FOREIGN KEY (`id_plantel`) REFERENCES `plantel` (`id_plantel`),
                             CONSTRAINT `id_posicion` FOREIGN KEY (`id_posicion`) REFERENCES `posicion` (`id_posicion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jugadores_x_partido`
--

DROP TABLE IF EXISTS `jugadores_x_partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores_x_partido` (
                                       `dni` varchar(25) NOT NULL,
                                       `id_partido` int NOT NULL,
                                       KEY `id_partido_idx` (`id_partido`),
                                       KEY `dni_idx` (`dni`,`id_partido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jugadores_x_plantel`
--

DROP TABLE IF EXISTS `jugadores_x_plantel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores_x_plantel` (
                                       `dni` int NOT NULL,
                                       `id_plantel` varchar(45) NOT NULL,
                                       PRIMARY KEY (`dni`,`id_plantel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partido` (
                           `id_partido` int NOT NULL AUTO_INCREMENT,
                           `fecha` datetime DEFAULT NULL,
                           `rival` varchar(45) DEFAULT NULL,
                           `id_torneo` int DEFAULT NULL,
                           `gol_club` int DEFAULT NULL,
                           `gol_rival` int DEFAULT NULL,
                           PRIMARY KEY (`id_partido`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plantel`
--

DROP TABLE IF EXISTS `plantel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plantel` (
                           `id_plantel` int NOT NULL,
                           `id_entrenador` int DEFAULT NULL,
                           `id_preparador` int DEFAULT NULL,
                           `id_categoria` int DEFAULT NULL,
                           `id_club` int DEFAULT NULL,
                           `id_equipo` varchar(45) DEFAULT NULL,
                           `id_division` int DEFAULT NULL,
                           PRIMARY KEY (`id_plantel`),
                           KEY `id_club_idx` (`id_club`),
                           KEY `id_categoria_idx` (`id_categoria`),
                           KEY `id_entrenador_idx` (`id_entrenador`),
                           KEY `id_preparador_idx` (`id_preparador`),
                           CONSTRAINT `id_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`),
                           CONSTRAINT `id_club` FOREIGN KEY (`id_club`) REFERENCES `club` (`id_club`),
                           CONSTRAINT `id_entrenador` FOREIGN KEY (`id_entrenador`) REFERENCES `entrenadores` (`id_entrenadores`),
                           CONSTRAINT `id_preparador` FOREIGN KEY (`id_preparador`) REFERENCES `preparadores` (`idpreparadores`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `posicion`
--

DROP TABLE IF EXISTS `posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posicion` (
                            `id_posicion` int NOT NULL,
                            `descripcion_posicion` varchar(45) DEFAULT NULL,
                            PRIMARY KEY (`id_posicion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `preparadores`
--

DROP TABLE IF EXISTS `preparadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preparadores` (
                                `idpreparadores` int NOT NULL AUTO_INCREMENT,
                                `nombre` varchar(45) DEFAULT NULL,
                                `apelllido` varchar(45) DEFAULT NULL,
                                PRIMARY KEY (`idpreparadores`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `torneo` (
                          `id_torneo` int NOT NULL,
                          `descripcion` varchar(45) DEFAULT NULL,
                          PRIMARY KEY (`id_torneo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-16 23:08:36

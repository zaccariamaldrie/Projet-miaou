CREATE DATABASE  IF NOT EXISTS `miaou` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `miaou`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: miaou
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `agents`
--

DROP TABLE IF EXISTS `agents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agents` (
  `ag_matricule` int NOT NULL AUTO_INCREMENT,
  `ag_nom` varchar(45) NOT NULL,
  `ag_password` varchar(45) NOT NULL,
  `fk_se` int NOT NULL,
  `fk_ta` int NOT NULL,
  `fk_ve` char(7) DEFAULT NULL,
  PRIMARY KEY (`ag_matricule`),
  KEY `fk_AGENTS_1_idx` (`fk_se`),
  KEY `fk_AGENTS_2_idx` (`fk_ta`),
  KEY `fk_AGENTS_3_idx` (`fk_ve`),
  CONSTRAINT `fk_AGENTS_1` FOREIGN KEY (`fk_se`) REFERENCES `secteurs` (`se_id`),
  CONSTRAINT `fk_AGENTS_2` FOREIGN KEY (`fk_ta`) REFERENCES `type_agent` (`ta_id`),
  CONSTRAINT `fk_AGENTS_3` FOREIGN KEY (`fk_ve`) REFERENCES `vehicules` (`ve_immat`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agents`
--

LOCK TABLES `agents` WRITE;
/*!40000 ALTER TABLE `agents` DISABLE KEYS */;
INSERT INTO `agents` VALUES (1,'Kevin Tran','Henri',1,1,NULL),(2,'Zaccaria Maldrie','Non',1,1,'XD185XD'),(3,'Cedric Daye','Tonton',2,2,NULL),(5,'Romain Saudemon','Dada',3,2,NULL),(6,'Dorian Pinet','Sirius t bo',4,1,NULL),(7,'Sara Texier','Bichette',4,1,'CU666UM'),(8,'Justin Leroux','Usonami',4,2,NULL),(9,'Mickaël Bonte','Farmer',5,1,NULL),(10,'Alexis Crapet','Banane',1,1,NULL);
/*!40000 ALTER TABLE `agents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fdrm`
--

DROP TABLE IF EXISTS `fdrm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fdrm` (
  `fk_ag` int NOT NULL,
  `fdrm_mois` tinyint NOT NULL,
  `fdrm_reception` date DEFAULT NULL,
  `fdrm_validation` date DEFAULT NULL,
  `fdrm_paiement` date DEFAULT NULL,
  `fdrm_remboursement` date DEFAULT NULL,
  `nbrj_conges` tinyint DEFAULT NULL,
  `fk_ag_comptable` int DEFAULT NULL,
  PRIMARY KEY (`fk_ag`,`fdrm_mois`),
  KEY `fk_FDRM_1_idx` (`fk_ag`),
  KEY `fk_FDRM_2_idx` (`fk_ag_comptable`),
  CONSTRAINT `fk_FDRM_1` FOREIGN KEY (`fk_ag`) REFERENCES `agents` (`ag_matricule`),
  CONSTRAINT `fk_FDRM_2` FOREIGN KEY (`fk_ag_comptable`) REFERENCES `agents` (`ag_matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fdrm`
--

LOCK TABLES `fdrm` WRITE;
/*!40000 ALTER TABLE `fdrm` DISABLE KEYS */;
INSERT INTO `fdrm` VALUES (1,1,'2021-02-01',NULL,NULL,NULL,0,0),(1,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(1,4,NULL,NULL,NULL,NULL,NULL,NULL),(1,5,NULL,NULL,NULL,NULL,NULL,NULL),(1,6,NULL,NULL,NULL,NULL,NULL,NULL),(1,8,NULL,NULL,NULL,NULL,NULL,NULL),(1,9,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'2021-03-12','2021-03-12',NULL,NULL,NULL,NULL),(2,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(2,5,NULL,NULL,NULL,NULL,NULL,NULL),(3,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(5,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(6,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(7,1,'2021-02-01',NULL,NULL,NULL,NULL,NULL),(7,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(8,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(9,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL),(10,2,'2021-03-01',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `fdrm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frais`
--

DROP TABLE IF EXISTS `frais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frais` (
  `fr_id` int NOT NULL AUTO_INCREMENT,
  `fr_libelle_libre` varchar(45) DEFAULT NULL,
  `fr_date` date DEFAULT NULL,
  `fr_quantite` int NOT NULL,
  `fr_montant` decimal(6,2) DEFAULT NULL,
  `fr_taxe` decimal(6,2) NOT NULL,
  `fr_status` tinyint NOT NULL,
  `fk_tre` int DEFAULT NULL,
  `fk_mfr` int DEFAULT NULL,
  `fk_fdrm_ag` int NOT NULL,
  `fk_fdrm_mois` tinyint NOT NULL,
  PRIMARY KEY (`fr_id`),
  KEY `fk_FRAIS_1_idx` (`fk_tre`),
  KEY `fk_FRAIS_2_idx` (`fk_mfr`),
  KEY `fk_FRAIS_3_idx` (`fk_fdrm_ag`,`fk_fdrm_mois`),
  CONSTRAINT `fk_FRAIS_1` FOREIGN KEY (`fk_tre`) REFERENCES `type_refus` (`tre_id`),
  CONSTRAINT `fk_FRAIS_2` FOREIGN KEY (`fk_mfr`) REFERENCES `modele_frais` (`mfr_id`),
  CONSTRAINT `fk_FRAIS_3` FOREIGN KEY (`fk_fdrm_ag`, `fk_fdrm_mois`) REFERENCES `fdrm` (`fk_ag`, `fdrm_mois`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frais`
--

LOCK TABLES `frais` WRITE;
/*!40000 ALTER TABLE `frais` DISABLE KEYS */;
INSERT INTO `frais` VALUES (6,'Resto',NULL,2,15.00,3.00,0,NULL,NULL,1,5),(7,'Resto',NULL,2,15.00,3.00,2,NULL,NULL,2,1),(8,'Hotel',NULL,50,12.00,2.40,0,NULL,NULL,1,6),(9,'Roses rouges',NULL,15,2.00,0.40,1,NULL,NULL,2,1),(12,'Resto',NULL,2,15.00,3.00,0,NULL,NULL,2,2),(13,'Restaurant',NULL,5,11.00,2.20,0,NULL,4,7,1),(14,'Nuitee',NULL,7,18.00,3.60,0,NULL,9,7,1),(15,'Carburant',NULL,86,5.44,1.09,0,NULL,NULL,7,1),(16,'lalala',NULL,3,15.00,3.00,0,NULL,NULL,7,1),(17,'Nuitee','2021-05-10',3,15.00,3.00,0,NULL,6,2,1),(18,'Restaurant','2021-05-10',2,8.00,1.60,0,NULL,1,2,1),(19,'Carburant',NULL,88,5.44,1.09,0,NULL,NULL,2,1),(20,'Festival','2021-05-12',3,56.00,11.20,0,NULL,NULL,2,1),(21,'Restaurant','2021-05-10',3,8.00,1.60,0,NULL,1,2,5);
/*!40000 ALTER TABLE `frais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frais_km`
--

DROP TABLE IF EXISTS `frais_km`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frais_km` (
  `fkm_id` int NOT NULL AUTO_INCREMENT,
  `fkm_cv` int NOT NULL,
  `fkm_carburant` varchar(45) NOT NULL,
  `fkm_remb` decimal(4,2) NOT NULL,
  PRIMARY KEY (`fkm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frais_km`
--

LOCK TABLES `frais_km` WRITE;
/*!40000 ALTER TABLE `frais_km` DISABLE KEYS */;
INSERT INTO `frais_km` VALUES (1,5,'essence',5.44);
/*!40000 ALTER TABLE `frais_km` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modele_frais`
--

DROP TABLE IF EXISTS `modele_frais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modele_frais` (
  `mfr_id` int NOT NULL COMMENT '	',
  `mfr_libelle` varchar(45) NOT NULL,
  `mfr_montant_unitaire` decimal(6,2) NOT NULL,
  PRIMARY KEY (`mfr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modele_frais`
--

LOCK TABLES `modele_frais` WRITE;
/*!40000 ALTER TABLE `modele_frais` DISABLE KEYS */;
INSERT INTO `modele_frais` VALUES (1,'Restaurant',8.00),(2,'Restaurant',9.00),(3,'Restaurant',10.00),(4,'Restaurant',11.00),(5,'Restaurant',12.00),(6,'Nuitee',15.00),(7,'Nuitee',16.00),(8,'Nuitee',17.00),(9,'Nuitee',18.00),(10,'Nuitee',19.00);
/*!40000 ALTER TABLE `modele_frais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametres`
--

DROP TABLE IF EXISTS `parametres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parametres` (
  `pa_id` int NOT NULL AUTO_INCREMENT,
  `validite_depense` int NOT NULL,
  `clorure_envoi_fdrm` int NOT NULL,
  `nbjr_atravaille` int NOT NULL,
  PRIMARY KEY (`pa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametres`
--

LOCK TABLES `parametres` WRITE;
/*!40000 ALTER TABLE `parametres` DISABLE KEYS */;
/*!40000 ALTER TABLE `parametres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secteurs`
--

DROP TABLE IF EXISTS `secteurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secteurs` (
  `se_id` int NOT NULL AUTO_INCREMENT,
  `se_libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`se_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secteurs`
--

LOCK TABLES `secteurs` WRITE;
/*!40000 ALTER TABLE `secteurs` DISABLE KEYS */;
INSERT INTO `secteurs` VALUES (1,'Nord'),(2,'Ouest'),(3,'Est'),(4,'Sud'),(5,'Ile-de-France');
/*!40000 ALTER TABLE `secteurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_agent`
--

DROP TABLE IF EXISTS `type_agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_agent` (
  `ta_id` int NOT NULL AUTO_INCREMENT,
  `ta_libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`ta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_agent`
--

LOCK TABLES `type_agent` WRITE;
/*!40000 ALTER TABLE `type_agent` DISABLE KEYS */;
INSERT INTO `type_agent` VALUES (1,'client'),(2,'comptable');
/*!40000 ALTER TABLE `type_agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_refus`
--

DROP TABLE IF EXISTS `type_refus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_refus` (
  `tre_id` int NOT NULL AUTO_INCREMENT,
  `tre_libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`tre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_refus`
--

LOCK TABLES `type_refus` WRITE;
/*!40000 ALTER TABLE `type_refus` DISABLE KEYS */;
INSERT INTO `type_refus` VALUES (5,'Non conforme');
/*!40000 ALTER TABLE `type_refus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicules`
--

DROP TABLE IF EXISTS `vehicules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicules` (
  `ve_immat` char(7) NOT NULL,
  `ve_marque` varchar(45) NOT NULL,
  `ve_model` varchar(45) NOT NULL,
  `fk_fkm` int NOT NULL,
  PRIMARY KEY (`ve_immat`),
  KEY `fk_VEHICULES_1_idx` (`fk_fkm`),
  CONSTRAINT `fk_VEHICULES_1` FOREIGN KEY (`fk_fkm`) REFERENCES `frais_km` (`fkm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicules`
--

LOCK TABLES `vehicules` WRITE;
/*!40000 ALTER TABLE `vehicules` DISABLE KEYS */;
INSERT INTO `vehicules` VALUES ('CU666UM','Yamaha','1300 xjr rouge',1),('XD185XD','Opel','Corsa',1);
/*!40000 ALTER TABLE `vehicules` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-10 22:57:58

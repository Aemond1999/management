-- MySQL dump 10.13  Distrib 5.5.60, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: bysj
-- ------------------------------------------------------
-- Server version	5.5.60-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'customer',NULL,NULL,NULL,NULL,0),(2,'provider',NULL,NULL,NULL,NULL,0),(3,'warehouse',NULL,NULL,NULL,NULL,0),(4,'employee',NULL,NULL,NULL,NULL,0),(5,'user',NULL,NULL,NULL,NULL,0),(6,'sale',NULL,NULL,NULL,NULL,0),(7,'purchase',NULL,NULL,NULL,NULL,0),(8,'check',NULL,NULL,NULL,NULL,0),(9,'dept',NULL,NULL,NULL,NULL,0),(10,'role',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogue`
--

DROP TABLE IF EXISTS `catalogue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalogue_name` varchar(255) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `unit` char(1) DEFAULT NULL,
  `prov_id` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogue`
--

LOCK TABLES `catalogue` WRITE;
/*!40000 ALTER TABLE `catalogue` DISABLE KEYS */;
INSERT INTO `catalogue` VALUES (1,'键盘',100,'个',1,NULL,NULL,NULL,NULL,0),(2,'鼠标',200,'个',1,NULL,NULL,NULL,NULL,0),(3,'雪糕',8,'个',1,NULL,NULL,NULL,NULL,0),(4,'显卡',6600,'块',2,NULL,NULL,NULL,NULL,0),(5,'主板',1000,'块',2,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `catalogue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_sheet`
--

DROP TABLE IF EXISTS `check_sheet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_sheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `original_number` bigint(20) DEFAULT NULL,
  `real_number` bigint(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `is_audit` bigint(20) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `check_time` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT '-1',
  `Inventory_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_sheet`
--

LOCK TABLES `check_sheet` WRITE;
/*!40000 ALTER TABLE `check_sheet` DISABLE KEYS */;
INSERT INTO `check_sheet` VALUES (1,100,10,0,1,1,'2023-05-03 20:54:46',1,'2023-05-03 20:54:46',0,'2023-05-04 08:45:05',1,1,1,''),(2,10,5,0,1,1,'2023-05-05 14:36:52',1,'2023-05-05 14:36:52',0,'2023-05-05 14:39:41',1,1,1,'无');
/*!40000 ALTER TABLE `check_sheet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) DEFAULT NULL,
  `linkman` varchar(255) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_card` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (3,'阿里巴巴','马云',17815728367,'浙江省杭州市','中国工商银行',123453645123,NULL,NULL,1,'2023-04-21 23:10:00',0),(4,'腾讯','马化腾',17815728367,'广东省深圳市','中国邮政银行',123413413455,NULL,NULL,1,'2023-04-21 23:10:43',0),(5,'2','2',2,'2','2',2,NULL,'2023-04-19 10:37:20',NULL,'2023-04-19 10:40:13',1),(6,'特斯拉','马斯克',1891382512,'美国德克萨斯州奥斯汀','花旗银行',445378451212,1,'2023-04-21 23:11:50',1,'2023-04-21 23:16:21',0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (1,'销售部',NULL,NULL,NULL,NULL,NULL,0),(2,'采购部',NULL,NULL,NULL,NULL,NULL,0),(3,'仓储部',NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `hire_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'小明',1,'男','北京市海淀区',1831636523,'2023-04-19 00:00:00',NULL,NULL,1,'2023-04-23 07:42:30',0),(2,'小红',2,'男','浙江省杭州市余杭区',1357464495,'2023-04-21 00:00:00',NULL,NULL,1,'2023-05-05 10:12:40',0),(3,'小刚',3,'女','浙江省湖州市吴兴区',1308622692,'2023-04-29 00:00:00',NULL,'2023-04-19 20:46:45',1,'2023-05-05 10:12:20',0),(4,'小李',1,'女',NULL,NULL,'2023-05-25 00:00:00',2,'2023-05-05 10:48:42',2,'2023-05-05 10:49:53',1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouse_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `unit` char(1) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'RTX580',77777,5,'块',0,NULL,NULL,1,'2023-05-05 14:39:41'),(2,2,'GTX4090Ti',666,50,'个',0,NULL,NULL,1,'2023-04-21 15:31:28'),(3,1,'罗技GPW',1000,800,'个',0,NULL,NULL,1,'2023-05-07 10:44:39');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prov_name` varchar(255) DEFAULT NULL,
  `linkman` varchar(255) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_card` bigint(20) DEFAULT NULL,
  `create_by` mediumtext,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (1,'宝马','jack',152649245,'德国巴伐利亚','德国银行',6421213434,NULL,NULL,1,'2023-04-21 23:15:46',0),(2,'奔驰','mike',167934253,'德国汉堡市','欧洲银行',134894165,'1','2023-04-21 23:13:41',1,'2023-04-21 23:15:56',0);
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prov_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  `purchase_time` datetime DEFAULT NULL,
  `sum` float DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `is_audit` tinyint(4) DEFAULT '0',
  `message` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
INSERT INTO `purchase_order` VALUES (14,1,1,'2023-05-05 14:19:54',10000,0,1,'',1,'2023-05-05 14:16:17',1,'2023-05-05 14:16:17',0),(15,1,1,'2023-05-07 10:37:17',50000,0,1,'',1,'2023-05-07 10:35:23',1,'2023-05-07 10:35:23',0);
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order_detail`
--

DROP TABLE IF EXISTS `purchase_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalogue_id` bigint(20) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `purchase_number` int(11) DEFAULT NULL,
  `purchase_order_id` bigint(20) DEFAULT NULL,
  `catalogue_name` varchar(255) DEFAULT NULL,
  `unit` char(1) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order_detail`
--

LOCK TABLES `purchase_order_detail` WRITE;
/*!40000 ALTER TABLE `purchase_order_detail` DISABLE KEYS */;
INSERT INTO `purchase_order_detail` VALUES (19,1,100,100,14,'键盘','个',1,'2023-05-05 14:16:17',1,'2023-05-05 14:16:17',0),(20,1,100,100,15,'键盘','个',1,'2023-05-07 10:35:23',1,'2023-05-07 10:35:23',0),(21,2,200,200,15,'鼠标','个',1,'2023-05-07 10:35:23',1,'2023-05-07 10:35:23',0);
/*!40000 ALTER TABLE `purchase_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raw_material`
--

DROP TABLE IF EXISTS `raw_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `raw_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `raw_material_name` varchar(255) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `warning` bigint(20) DEFAULT NULL,
  `unit` char(1) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `catalogue_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raw_material`
--

LOCK TABLES `raw_material` WRITE;
/*!40000 ALTER TABLE `raw_material` DISABLE KEYS */;
INSERT INTO `raw_material` VALUES (12,'键盘',2,100,200,300,'个',1,'2023-04-23 08:02:18',1,'2023-05-06 14:00:32',0,1),(13,'键盘',1,100,200,NULL,'个',1,'2023-05-05 14:19:54',1,'2023-05-07 10:37:17',0,1),(14,'鼠标',1,200,200,NULL,'个',1,'2023-05-07 10:37:17',1,'2023-05-07 10:37:17',0,2);
/*!40000 ALTER TABLE `raw_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'系统管理员','具有创建用户，角色功能，等全部权限',NULL,NULL,NULL,NULL,0),(2,'销售员','负责销售管理',NULL,NULL,NULL,NULL,0),(3,'采购员','负责采购管理',NULL,NULL,NULL,NULL,0),(4,'仓库管理员','负责库存盘点',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_authority`
--

DROP TABLE IF EXISTS `role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `authority_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `role_authority_role_id_fk` (`role_id`),
  KEY `role_authority_authority_id_fk` (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_authority`
--

LOCK TABLES `role_authority` WRITE;
/*!40000 ALTER TABLE `role_authority` DISABLE KEYS */;
INSERT INTO `role_authority` VALUES (1,1,1,NULL,NULL,NULL,NULL,0),(2,1,2,NULL,NULL,NULL,NULL,0),(3,1,3,NULL,NULL,NULL,NULL,0),(4,1,4,NULL,NULL,NULL,NULL,0),(5,1,5,NULL,NULL,NULL,NULL,0),(6,1,6,NULL,NULL,NULL,NULL,0),(7,1,7,NULL,NULL,NULL,NULL,0),(8,1,8,NULL,NULL,NULL,NULL,0),(9,1,9,NULL,NULL,NULL,NULL,0),(10,2,6,NULL,NULL,NULL,NULL,0),(11,3,7,NULL,NULL,NULL,NULL,0),(12,4,3,NULL,NULL,NULL,NULL,0),(13,4,8,NULL,NULL,NULL,NULL,0),(14,1,9,NULL,NULL,NULL,NULL,0),(15,2,9,NULL,NULL,NULL,NULL,0),(16,3,9,NULL,NULL,NULL,NULL,0),(17,4,9,NULL,NULL,NULL,NULL,0),(18,1,10,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_order`
--

DROP TABLE IF EXISTS `sale_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL,
  `warehouse_id` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `is_audit` tinyint(4) DEFAULT '0',
  `sum` float DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` mediumtext,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `sale_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_order`
--

LOCK TABLES `sale_order` WRITE;
/*!40000 ALTER TABLE `sale_order` DISABLE KEYS */;
INSERT INTO `sale_order` VALUES (8,3,1,'',0,1,80000,'2023-05-05 14:31:14',1,'1','2023-05-05 14:31:14',0,'2023-05-05 14:32:52'),(9,3,1,'',0,1,100000,'2023-05-07 10:42:52',1,'1','2023-05-07 10:42:52',0,'2023-05-07 10:44:39');
/*!40000 ALTER TABLE `sale_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_order_detail`
--

DROP TABLE IF EXISTS `sale_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sale_order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `sale_number` int(11) DEFAULT NULL,
  `unit` char(1) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `value` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_order_detail`
--

LOCK TABLES `sale_order_detail` WRITE;
/*!40000 ALTER TABLE `sale_order_detail` DISABLE KEYS */;
INSERT INTO `sale_order_detail` VALUES (11,8,3,'罗技GPW',80,'个',0,1,'2023-05-05 14:31:14',1,'2023-05-05 14:31:14',1000),(12,9,3,'罗技GPW',100,'个',0,1,'2023-05-07 10:42:52',1,'2023-05-07 10:42:52',1000);
/*!40000 ALTER TABLE `sale_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `status` tinyint(4) DEFAULT '1',
  `avatar` varchar(255) DEFAULT NULL,
  `emp_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$NzvdpX1Lv7cL8wUKhQ4HEuc3C6vhlM0zWoanL.xPi/TgszVdMKShC',NULL,NULL,NULL,NULL,0,1,'http://rt9mxmck5.hd-bkt.clouddn.com/2023/04/23eb13beafb3ae4fe1bf79c1db95980473.jpg',1),(2,'test','$2a$10$NzvdpX1Lv7cL8wUKhQ4HEuc3C6vhlM0zWoanL.xPi/TgszVdMKShC',NULL,NULL,NULL,NULL,0,1,'http://rt9mxmck5.hd-bkt.clouddn.com/2023/04/227c2ff9a46fb44dc1ab65604980b4a231.png',2),(5,'1','$2a$10$lbVETaDA2VrNc3w6.vPl6.XFPpuv1HSzJDQgA2b91D1c45xiZwwGy',1,'2023-05-05 13:17:53',1,'2023-05-05 13:23:06',1,1,NULL,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `user_role_user_id_fk` (`user_id`),
  KEY `user_role_role_id_fk` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1,NULL,NULL,NULL,NULL,0),(2,2,3,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouse_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'北京仓','北京朝阳区','无',NULL,NULL,NULL,'2023-04-20 04:32:30',0),(2,'上海仓','上海青浦区','无',NULL,'2023-04-19 20:39:38',1,'2023-04-21 23:13:58',0),(3,'杭州仓','杭州市余杭区','无',1,'2023-04-21 23:14:26',1,'2023-04-21 23:14:26',0),(4,'1','1','1',2,'2023-05-05 10:52:20',2,'2023-05-05 10:52:23',1);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-12 14:38:02

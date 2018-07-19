CREATE DATABASE  IF NOT EXISTS `webelearning` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `webelearning`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: webelearning
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu18.04.1

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
-- Table structure for table `bai`
--

DROP TABLE IF EXISTS `bai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bai` (
  `mabai` varchar(50) NOT NULL,
  `tenbai` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `machuong` varchar(50) DEFAULT NULL,
  `noidung` longtext,
  `trangthai` tinyint(1) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`mabai`),
  KEY `FK5lqxuhta95yy7031lyb5lmt7w` (`machuong`),
  CONSTRAINT `FK5lqxuhta95yy7031lyb5lmt7w` FOREIGN KEY (`machuong`) REFERENCES `chuong` (`machuong`),
  CONSTRAINT `bai_ibfk_1` FOREIGN KEY (`machuong`) REFERENCES `chuong` (`machuong`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bai`
--

LOCK TABLES `bai` WRITE;
/*!40000 ALTER TABLE `bai` DISABLE KEYS */;
INSERT INTO `bai` VALUES ('gt_1_ch_1_b_1','b1.1','gt_1_ch_1','aaa 1.1',1,0),('gt_1_ch_2_b_1','b2.1','gt_1_ch_2','aaa 2.1',1,0),('gt_1_ch_2_b_2','b2.2','gt_1_ch_2','aaa 3.1',1,0),('gt_2_ch_1_b_1','b1.1  Helloworld','gt_2_ch_1','\r\n<p><iframe allow=\"autoplay; encrypted-media\" allowfullscreen=\"\" frameborder=\"0\" height=\"480\" src=\"https://www.youtube.com/embed/FcPG1ZO8t5w\" width=\"850\"></iframe></p>\r\n\r\n<p>&nbsp;</p>\r\n',1,0),('gt_2_ch_1_b_2','b1.1  Biến và kiểu dữ liệu','gt_2_ch_1','&lt;p&gt;bbb 1.2&lt;/p&gt;\r\n\r\n&lt;p&gt;&lt;iframe allow=&quot;autoplay; encrypted-media&quot; allowfullscreen=&quot;&quot; frameborder=&quot;0&quot; height=&quot;480&quot; src=&quot;https://www.youtube.com/embed/UECrDhW1cu4&quot; width=&quot;850&quot;&gt;&lt;/iframe&gt;&lt;/p&gt;\r\n',1,0),('gt_2_ch_2_b_1','b2.1 Toán tử toán học trong Java','gt_2_ch_2','&lt;p&gt;bbb 2.1&lt;/p&gt;\r\n&lt;iframe width=&quot;850&quot; height=&quot;480&quot; src=&quot;https://www.youtube.com/embed/4Ti5to6EW_U&quot; frameborder=&quot;0&quot; allow=&quot;autoplay; encrypted-media&quot; allowfullscreen&gt;&lt;/iframe&gt;\r\n',1,0),('gt_2_ch_3_b_1',' 3. Thực hành toán tử điều kiện IF','gt_2_ch_3','<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n<iframe width=\"850\" height=\"480\" src=\"https://www.youtube.com/embed/V7RihZmtTp0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>',1,0),('gt_3_ch_1_b1','b1.1','gt_3_ch_1','ccc 1.1',1,0),('gt_3_ch_1_b2','b1.2','gt_3_ch_1','ccc 1.2',1,0),('gt_3_ch_1_b3','b1.3','gt_3_ch_1','ccc 1.3',1,0),('gt_3_ch_2_b1','b2.1','gt_3_ch_2','ccc 2.1',1,0),('gt_6_ch_1_b_9','Bai 1 Stream','gt_6_ch_1','Link video: &lt;br/&gt;&lt;video id=&#39;videolocal&#39; class=&#39;video-js&#39; width=&#39;500px&#39; controls=&#39;controls&#39; &gt;&lt;source src=&#39;http://localhost:8080/public/videos/13_cntt_1_1_19072018_090784.webm&#39; type=&#39;video/webm&#39;&gt;&lt;/video&gt;',0,0);
/*!40000 ALTER TABLE `bai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuong`
--

DROP TABLE IF EXISTS `chuong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chuong` (
  `machuong` varchar(50) NOT NULL,
  `tenchuong` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `magt` varchar(50) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`machuong`),
  KEY `FKo3lqmca3vervals9d8a79818c` (`magt`),
  CONSTRAINT `FKo3lqmca3vervals9d8a79818c` FOREIGN KEY (`magt`) REFERENCES `giaotrinh` (`magt`),
  CONSTRAINT `chuong_ibfk_1` FOREIGN KEY (`magt`) REFERENCES `giaotrinh` (`magt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuong`
--

LOCK TABLES `chuong` WRITE;
/*!40000 ALTER TABLE `chuong` DISABLE KEYS */;
INSERT INTO `chuong` VALUES ('gt_1_ch_1','A','gt_1',0),('gt_1_ch_2','B','gt_1',0),('gt_2_ch_1','A','gt_2',0),('gt_2_ch_2','B','gt_2',0),('gt_2_ch_3','C','gt_2',0),('gt_3_ch_1','A','gt_3',0),('gt_3_ch_2','B','gt_3',0),('gt_5_ch_1','NodeJSC1','gt_5',0),('gt_6_ch_1','Ep1','gt_6',0);
/*!40000 ALTER TABLE `chuong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giaotrinh`
--

DROP TABLE IF EXISTS `giaotrinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `giaotrinh` (
  `magt` varchar(50) NOT NULL,
  `tengt` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tgcapnhat` datetime DEFAULT NULL,
  `magv` int(11) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`magt`),
  KEY `FKjjt7qnm3hvohgplmc1t3ivtd6` (`magv`),
  CONSTRAINT `FKjjt7qnm3hvohgplmc1t3ivtd6` FOREIGN KEY (`magv`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `giaotrinh_ibfk_1` FOREIGN KEY (`magv`) REFERENCES `nguoidung` (`mand`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giaotrinh`
--

LOCK TABLES `giaotrinh` WRITE;
/*!40000 ALTER TABLE `giaotrinh` DISABLE KEYS */;
INSERT INTO `giaotrinh` VALUES ('gt_1','Lập trình Java căn bản 1','2017-01-05 14:30:00',3,0),('gt_2','Lập trình Java nâng cao 1','2018-07-19 16:53:46',19,0),('gt_3','Lập trình C# căn bản 1','2017-05-05 09:00:00',3,0),('gt_4','Luyện thi toeic căn bản 1','2017-06-06 10:30:00',4,0),('gt_5','Nodejs','2018-07-17 21:52:53',11,0),('gt_6','Java basic','2018-07-19 11:29:41',13,0);
/*!40000 ALTER TABLE `giaotrinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gvkh`
--

DROP TABLE IF EXISTS `gvkh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gvkh` (
  `magv` int(11) NOT NULL,
  `makh` varchar(50) NOT NULL,
  `ngaytao` datetime DEFAULT NULL,
  PRIMARY KEY (`magv`,`makh`),
  KEY `FK5obthlmjccbbbai994qcip90w` (`makh`),
  CONSTRAINT `FK5obthlmjccbbbai994qcip90w` FOREIGN KEY (`makh`) REFERENCES `khoahoc` (`makh`),
  CONSTRAINT `FKqnrxyw2knhd1rto2mr1wh6vfo` FOREIGN KEY (`magv`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `gvkh_ibfk_1` FOREIGN KEY (`magv`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `gvkh_ibfk_2` FOREIGN KEY (`makh`) REFERENCES `khoahoc` (`makh`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gvkh`
--

LOCK TABLES `gvkh` WRITE;
/*!40000 ALTER TABLE `gvkh` DISABLE KEYS */;
INSERT INTO `gvkh` VALUES (3,'cntt_1_1','2017-07-25 08:10:00'),(3,'cntt_1_2','2017-08-25 09:10:00'),(3,'cntt_2_1','2017-08-26 08:10:00'),(4,'ngng_1_1','2018-01-03 08:15:00'),(11,'cntt_1_1',NULL),(13,'cntt_1_1',NULL),(19,'cntt_1_1','2018-07-19 15:10:28'),(19,'cntt_1_2','2018-07-19 15:10:28'),(19,'cntt_1_3','2018-07-19 15:10:28');
/*!40000 ALTER TABLE `gvkh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hvkh`
--

DROP TABLE IF EXISTS `hvkh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hvkh` (
  `mahv` int(11) NOT NULL,
  `makh` varchar(50) NOT NULL,
  `ngaydk` datetime DEFAULT NULL,
  `sao` double DEFAULT NULL,
  `noidung` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`mahv`,`makh`),
  KEY `FKov1eub8vouhee67dk80pt7i0g` (`makh`),
  CONSTRAINT `FKam3fh0w2lrbomubgno5tvciun` FOREIGN KEY (`mahv`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `FKov1eub8vouhee67dk80pt7i0g` FOREIGN KEY (`makh`) REFERENCES `khoahoc` (`makh`),
  CONSTRAINT `hvkh_ibfk_1` FOREIGN KEY (`mahv`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `hvkh_ibfk_2` FOREIGN KEY (`makh`) REFERENCES `khoahoc` (`makh`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hvkh`
--

LOCK TABLES `hvkh` WRITE;
/*!40000 ALTER TABLE `hvkh` DISABLE KEYS */;
INSERT INTO `hvkh` VALUES (5,'cntt_1_1','2017-08-05 08:10:00',4.5,'Hay lam'),(5,'cntt_1_2','2017-08-03 12:10:00',0,''),(5,'cntt_2_1','2017-08-06 08:10:00',0,''),(6,'cntt_1_1','2017-08-04 07:10:00',5,'Hay lam'),(6,'cntt_2_1','2017-08-05 09:10:00',5,'Hay lam'),(7,'cntt_1_2','2017-08-06 08:10:00',4.5,'Hay lam'),(7,'cntt_2_1','2017-08-05 09:10:00',4.5,'Hay lam'),(8,'cntt_1_2','2017-08-06 09:10:00',4.5,'Hay lam'),(11,'cntt_1_1','2017-08-06 08:10:00',4.5,'Hay lam'),(12,'cntt_1_1','2018-06-14 15:59:50',4,'OK'),(20,'cntt_1_1','2018-07-19 14:54:58',4,'Tam'),(20,'cntt_1_2','2018-07-19 14:54:58',5,'Tot');
/*!40000 ALTER TABLE `hvkh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoa`
--

DROP TABLE IF EXISTS `khoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khoa` (
  `makhoa` varchar(50) NOT NULL,
  `tenkhoa` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`makhoa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoa`
--

LOCK TABLES `khoa` WRITE;
/*!40000 ALTER TABLE `khoa` DISABLE KEYS */;
INSERT INTO `khoa` VALUES ('cntt','Công Nghệ Thông Tin',0),('ngng','Ngoại Ngữ',0);
/*!40000 ALTER TABLE `khoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoahoc`
--

DROP TABLE IF EXISTS `khoahoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khoahoc` (
  `makh` varchar(50) NOT NULL,
  `tenkh` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `maloaikh` varchar(50) DEFAULT NULL,
  `tgbdhoc` date DEFAULT NULL,
  `tgkthoc` date DEFAULT NULL,
  `tgbddk` datetime DEFAULT NULL,
  `tgktdk` datetime DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `soluonght` int(11) DEFAULT NULL,
  `hocphi` double DEFAULT NULL,
  `mota` tinytext CHARACTER SET utf8,
  `hinh` varchar(255) DEFAULT NULL,
  `daduyet` tinyint(1) DEFAULT NULL,
  `sodiem` double DEFAULT NULL,
  `soluot` int(11) DEFAULT NULL,
  `magt` varchar(50) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`makh`),
  KEY `FKbmfqudvudwibc11p7e569uik1` (`magt`),
  KEY `FK834vfhqw4s46llxhwxyfglj1h` (`maloaikh`),
  CONSTRAINT `FK834vfhqw4s46llxhwxyfglj1h` FOREIGN KEY (`maloaikh`) REFERENCES `loaikhoahoc` (`maloaikh`),
  CONSTRAINT `FKbmfqudvudwibc11p7e569uik1` FOREIGN KEY (`magt`) REFERENCES `giaotrinh` (`magt`),
  CONSTRAINT `khoahoc_ibfk_1` FOREIGN KEY (`maloaikh`) REFERENCES `loaikhoahoc` (`maloaikh`),
  CONSTRAINT `khoahoc_ibfk_2` FOREIGN KEY (`magt`) REFERENCES `giaotrinh` (`magt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoahoc`
--

LOCK TABLES `khoahoc` WRITE;
/*!40000 ALTER TABLE `khoahoc` DISABLE KEYS */;
INSERT INTO `khoahoc` VALUES ('cntt_1_1','Lâp trinh Java can ban','cntt_1','2018-08-28','2018-12-08','2018-07-01 00:00:00','2018-08-20 00:00:00',30,1,35,'<p>Học Java cơ bản và nâng cao,tự học lập trình Java cơ bản hay nhất,</p>\r\n','',1,4.5,1,'gt_6',0),('cntt_1_2','Lâp trinh Java nâng cao','cntt_1','2018-08-29','2018-12-09','2018-07-01 00:00:00','2019-08-26 00:00:00',30,4,4,'<p>Tự học java cơ bản đến nâng cao, tài liệu học lập trình java dành cho người mới bắt đầu. </>p','',1,4.5,2,'gt_2',0),('cntt_1_3','Communication Basic','ngng_2','2018-07-31','2019-07-19','2018-07-19 00:00:00','2018-08-19 00:00:00',22,0,199,'<p>English is the international language and under stood all over the world. Here we upload how to better spoken english , IELTS Spoken English. Hope so this video improved your English conversation.</p>\r\n','',1,0,0,'gt_4',0),('cntt_2_1','Lập trình C# căn bản','cntt_2','2018-08-30','2018-12-10','2017-08-01 00:00:00','2019-08-26 00:00:00',30,3,25,'Tự học java cơ bản đến nâng cao, tài liệu học lập trình c sharp dành cho người mới bắt đầu. ','',1,4.8,2,'gt_3',0),('ngng_1_1','Pratice Toiec 1','ngng_1','2018-08-20','2018-12-20','2018-06-04 00:00:00','2018-11-14 00:00:00',20,0,31,'<p>Đạt điểm TOEIC 450-650+ Sau 2 tuần hoặc 1 tháng. Nhận bằng TOEIC tháng 7,8/2018. Cam kết 650. Cam kết 550. Cam kết đầu ra. Cam kết 450.</p>\r\n','',1,0,0,'gt_4',0),('ngng_1_2','EnglishGoo','ngng_1','2018-07-18','2018-10-31','2018-07-18 00:00:00','2018-08-26 00:00:00',20,0,100,'<p>Khóa học tiếng anh cho người bắt đầu </p>\r\n','',0,0,0,'gt_4',1);
/*!40000 ALTER TABLE `khoahoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaikhoahoc`
--

DROP TABLE IF EXISTS `loaikhoahoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaikhoahoc` (
  `maloaikh` varchar(50) NOT NULL,
  `tenloaikh` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `makhoa` varchar(50) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`maloaikh`),
  KEY `FKbfqmb6720eigpv5lgo2jeapl8` (`makhoa`),
  CONSTRAINT `FKbfqmb6720eigpv5lgo2jeapl8` FOREIGN KEY (`makhoa`) REFERENCES `khoa` (`makhoa`),
  CONSTRAINT `loaikhoahoc_ibfk_1` FOREIGN KEY (`makhoa`) REFERENCES `khoa` (`makhoa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaikhoahoc`
--

LOCK TABLES `loaikhoahoc` WRITE;
/*!40000 ALTER TABLE `loaikhoahoc` DISABLE KEYS */;
INSERT INTO `loaikhoahoc` VALUES ('cntt_1','Lập trình Java','cntt',0),('cntt_2','Lập trình C#','cntt',0),('ngng_1','Luyện thi toeic','ngng',0),('ngng_2','Communication','ngng',0);
/*!40000 ALTER TABLE `loaikhoahoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ndtn`
--

DROP TABLE IF EXISTS `ndtn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ndtn` (
  `nguoigui` int(11) NOT NULL,
  `nguoinhan` int(11) NOT NULL,
  `matn` int(11) NOT NULL,
  PRIMARY KEY (`nguoigui`,`nguoinhan`,`matn`),
  KEY `FKogtpogu44tknq9ffmcj5oi8ii` (`nguoinhan`),
  KEY `FKtit0vasv314gvinuru337kqlu` (`matn`),
  CONSTRAINT `FKixnqwhq2f9whsxtcg1qwj5339` FOREIGN KEY (`nguoigui`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `FKogtpogu44tknq9ffmcj5oi8ii` FOREIGN KEY (`nguoinhan`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `FKtit0vasv314gvinuru337kqlu` FOREIGN KEY (`matn`) REFERENCES `tinnhan` (`matn`),
  CONSTRAINT `ndtn_ibfk_1` FOREIGN KEY (`matn`) REFERENCES `tinnhan` (`matn`),
  CONSTRAINT `ndtn_ibfk_2` FOREIGN KEY (`nguoigui`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `ndtn_ibfk_3` FOREIGN KEY (`nguoinhan`) REFERENCES `nguoidung` (`mand`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ndtn`
--

LOCK TABLES `ndtn` WRITE;
/*!40000 ALTER TABLE `ndtn` DISABLE KEYS */;
INSERT INTO `ndtn` VALUES (2,3,1),(2,4,1),(2,5,1),(3,5,2),(2,6,1),(2,7,1),(2,8,1);
/*!40000 ALTER TABLE `ndtn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidung`
--

DROP TABLE IF EXISTS `nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguoidung` (
  `mand` int(11) NOT NULL AUTO_INCREMENT,
  `hovaten` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phai` tinyint(1) DEFAULT NULL,
  `ngaysinh` date DEFAULT NULL,
  `diachi` text,
  `cmnd` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `matkhau` varchar(100) DEFAULT NULL,
  `kichhoat` tinyint(1) DEFAULT NULL,
  `khoabimat` varchar(100) DEFAULT NULL,
  `loaitaikhoan` varchar(100) DEFAULT NULL,
  `hinh` varchar(255) DEFAULT NULL,
  `mota` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `maquyen` int(11) DEFAULT NULL,
  `ngaycap` datetime DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`mand`),
  KEY `FKep3ywkb1h2cy54q0twepsrrad` (`maquyen`),
  CONSTRAINT `FKep3ywkb1h2cy54q0twepsrrad` FOREIGN KEY (`maquyen`) REFERENCES `quyen` (`maquyen`),
  CONSTRAINT `nguoidung_ibfk_1` FOREIGN KEY (`maquyen`) REFERENCES `quyen` (`maquyen`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES (1,'Ngô Thanh Đông',0,'1996-06-04','','12345678','dongnt2015@gmail.com','$10$eKwNVxREFFvnjbdz6.Xf9Oi3dT/AHJe4t/pCYnG67PVsQk.MtGjHq',1,'','local','','',1,'2017-01-05 14:30:00',0),(2,'Thiều Quang Lâm',0,'1996-11-24','','12345678','14110101@student.hcmute.edu.vn','',0,'','local','','',2,'2017-04-05 14:30:00',0),(3,'Nguyễn Văn An',0,'1990-11-30','','12345678','14110039@student.hcmute.edu.vn','',0,'','local','','',3,'2017-05-05 14:30:00',0),(4,'Trần Thị Ngọc Bích',1,'1989-12-05','','12345678','dongcr2016@gmail.com','',0,'','local','','',3,'2017-05-06 14:30:00',0),(5,'Lê Minh Châu',1,'1997-10-19','','12345678','dontnt2015@gmail.com','',0,'','local','','',4,'2017-07-05 14:30:00',0),(6,'Lý Ngọc Dương',0,'1998-07-20','','12345678','quanglam.thieu2@yahoo.com','',0,'','local','','',4,'2017-07-07 14:30:00',0),(7,'Vũ Minh Đạt',0,'1997-03-29','','12345678','trungquang512@gmail.com','',0,'','local','','',4,'2017-07-08 14:30:00',0),(8,'Trần Thị Lý',1,'1996-08-09','','12345678','thieuquanglam24@gmail.com','',0,'','local','','',4,'2017-07-09 14:30:00',0),(9,'Xmaster QLâm',0,NULL,NULL,NULL,'a6201218lam@gmail.com',NULL,1,'116089590987521419653','google','https://lh3.googleusercontent.com/-pZWWqbMVnLA/AAAAAAAAAAI/AAAAAAAAAL0/K8rH089iGBg/photo.jpg',NULL,3,NULL,0),(10,'Thieu Quang Lâm',0,NULL,NULL,NULL,'quanglam.thieu@yahoo.com',NULL,1,'1535395609901912','facebook','https://graph.facebook.com/1535395609901912/picture?type=normal',NULL,4,NULL,0),(11,'Nguyen Quang Vinh',0,NULL,NULL,NULL,'trungquang512@gmail.com',NULL,1,'630748067302036','facebook','https://graph.facebook.com/630748067302036/picture?type=normal',NULL,4,'2018-06-13 09:24:13',0),(12,'Lam',0,NULL,NULL,NULL,'quanglam.thieu@yahoo.com','$2a$10$1WXV9GDwXpcuw/18f2v9n.A5RR012CH3sK.e.oAejEg74KDGqNnKG',1,'b690255b-114a-44b5-9952-513dd5ca4516','local',NULL,NULL,1,NULL,0),(13,'Everything Share',0,NULL,NULL,NULL,'qlamxmaster@gmail.com',NULL,1,'105691292624130315057','google','https://lh5.googleusercontent.com/-xTwwTB0oncA/AAAAAAAAAAI/AAAAAAAAAOA/YZxjEVwn3tQ/photo.jpg',NULL,3,'2018-07-18 08:41:37',0),(18,'Truong Dinh',0,NULL,NULL,NULL,'truongdinh699@gmail.com','$2a$10$YQsYzdWpMcSqOT9KPpMBGuC8QGZmGKY2fIBrqo/mn7JTYKuxoeWv2',1,'f373ddc0-c24b-4a52-853c-c6ba95861720','local',NULL,NULL,2,NULL,0),(19,'QLam',0,'2003-07-19','HCM city 2','123444444','14110100@student.hcmute.edu.vn','$2a$10$ry1qRzHnpcAmE5ayJsBQcetAmvPar3dxckHhYhXWvWxCQAU97GJfO',1,NULL,'local','http://localhost:8080/public/images/HnVmB0-k.jpg','Toi la teacher',3,'2018-07-19 14:39:07',0),(20,'Ho Xung',0,NULL,NULL,NULL,'lehoxung699@gmail.com','$2a$10$PeBrEhI6meZRGhOoLe74GOkL0eGcdthVtJgDhFfDlrY14sO1ysCdi',1,'c5467556-5708-44ad-aab5-339abfa1999c','local',NULL,NULL,4,NULL,0);
/*!40000 ALTER TABLE `nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quyen` (
  `maquyen` int(11) NOT NULL AUTO_INCREMENT,
  `tenquyen` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`maquyen`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES (1,'ROLE_ADMIN',0),(2,'ROLE_STAFF',0),(3,'ROLE_TEACHER',0),(4,'ROLE_STUDENT',0);
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thaoluan`
--

DROP TABLE IF EXISTS `thaoluan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thaoluan` (
  `nguoidang` int(11) NOT NULL,
  `tgdang` datetime NOT NULL,
  `tieude` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `noidung` text,
  `makh` varchar(50) DEFAULT NULL,
  `matl` int(11) DEFAULT NULL,
  PRIMARY KEY (`nguoidang`,`tgdang`),
  KEY `FK3mgd4qgaoc4w98trdg1syebmj` (`makh`),
  CONSTRAINT `FK33c781qmj1ch5xxydwkl92xc3` FOREIGN KEY (`nguoidang`) REFERENCES `nguoidung` (`mand`),
  CONSTRAINT `FK3mgd4qgaoc4w98trdg1syebmj` FOREIGN KEY (`makh`) REFERENCES `khoahoc` (`makh`),
  CONSTRAINT `thaoluan_ibfk_1` FOREIGN KEY (`makh`) REFERENCES `khoahoc` (`makh`),
  CONSTRAINT `thaoluan_ibfk_2` FOREIGN KEY (`nguoidang`) REFERENCES `nguoidung` (`mand`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thaoluan`
--

LOCK TABLES `thaoluan` WRITE;
/*!40000 ALTER TABLE `thaoluan` DISABLE KEYS */;
INSERT INTO `thaoluan` VALUES (3,'2017-09-15 14:10:00','','aaaaaaaaaaaaaaaaaa','cntt_1_1',1),(4,'2017-09-15 14:30:00','','aaaaaaaaaaaaaaaaaa','cntt_1_1',1),(6,'2017-09-15 08:35:00','','bbbbbbbbbbbbbbbbbb','cntt_2_1',3),(6,'2017-09-15 14:00:00','aaa','aaaaaaaaaaaaaaaaaa','cntt_1_1',1),(6,'2017-09-16 14:30:00','ccc','cccccccccccccccccc','cntt_1_2',2),(7,'2017-09-15 08:30:00','bbb','bbbbbbbbbbbbbbbbbb','cntt_2_1',3),(7,'2017-09-16 14:50:00','','cccccccccccccccccc','cntt_1_2',2),(8,'2017-09-15 09:10:00','','bbbbbbbbbbbbbbbbbb','cntt_2_1',3),(10,'2018-06-22 14:07:33','Cau hoi 2','<p>Cau hoi 2 la gi the?</p>\r\n','cntt_1_1',4),(10,'2018-06-30 12:18:21','','<p>123321321</p>\r\n','cntt_1_1',4),(10,'2018-06-30 12:19:53','','<p>hay lam</p>\r\n\r\n<p>&nbsp;</p>\r\n','cntt_1_1',1),(12,'2018-07-14 14:39:44','','<p>dsfdsf</p>\r\n','cntt_1_1',4);
/*!40000 ALTER TABLE `thaoluan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tinnhan`
--

DROP TABLE IF EXISTS `tinnhan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tinnhan` (
  `matn` int(11) NOT NULL AUTO_INCREMENT,
  `tieude` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `tggui` datetime DEFAULT NULL,
  `noidung` text,
  PRIMARY KEY (`matn`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tinnhan`
--

LOCK TABLES `tinnhan` WRITE;
/*!40000 ALTER TABLE `tinnhan` DISABLE KEYS */;
INSERT INTO `tinnhan` VALUES (1,'Tiêu đề 1','2017-08-26 08:00:00','aaaaaaaaaaaaaaaaa'),(2,'Tiêu đề 2','2017-08-27 08:00:00','bbbbbbbbbbbbbbbbb'),(3,'Tiêu đề 3','2017-08-27 08:00:00','ccccccccccccccccc');
/*!40000 ALTER TABLE `tinnhan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-19 17:08:48

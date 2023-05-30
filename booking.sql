-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: booking_hotel
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `admin_users`
--

DROP TABLE IF EXISTS `admin_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_users`
--

LOCK TABLES `admin_users` WRITE;
/*!40000 ALTER TABLE `admin_users` DISABLE KEYS */;
INSERT INTO `admin_users` VALUES (1,'admin','admin@test.com','$2a$10$eJyIn8PG98DA02sZa5.3XeapGQrmuCe6ZZ7rffDaethNHZaD75y5m');
/*!40000 ALTER TABLE `admin_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (4,'Nhung Nhung nn','0123456789','maitrangnhung2002.xuantruongb@gmail.com','$2a$10$Sxww9EPR9pK1Yvwq7zEZrOLvKS7pIvR1LqApVbEBCdJTE./ZBmndm'),(6,'h6bb',NULL,'lanhdahan0706@gmail.com','$2a$10$xqsvAlVWnq0QjIT96GMo6eqvsPEiM2GRUkoPRBfY./QGV3QOKoLbG'),(8,'Nhung Nhung','0123456789','mocuishle2a@gmail.com','$2a$10$Sxww9EPR9pK1Yvwq7zEZrOLvKS7pIvR1LqApVbEBCdJTE./ZBmndm'),(10,'Mai Trang Nhung','0123456789','mocuishle1aa@gmail.com','$2a$10$eJyIn8PG98DA02sZa5.3XeapGQrmuCe6ZZ7rffDaethNHZaD75y5m'),(11,'nnnnn','0123456789','mocuishle2a@gmail.com','$2a$10$3wl361mc0sl1X23PEOH7/.4hshjS9GZqy7TlCY4Cjrqa3QFHFE4pW'),(12,'Mai Trang Nhung','0123456789','lanhdahan07006@gmail.com','$2a$10$hXsjyrnlhL1o1Aemyv0OZubOnvvlFuLuEgJ6CUjuo4Hw7OgJa3BKe');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `cancel_time` datetime DEFAULT NULL,
  `number_of_adults` int DEFAULT '0',
  `number_of_children` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_categories`
--

DROP TABLE IF EXISTS `room_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `max_number_of_people` int NOT NULL,
  `price_of_day` bigint NOT NULL,
  `price_overnight` bigint NOT NULL,
  `price_of_hour` bigint NOT NULL,
  `image_url` text NOT NULL,
  `status` int DEFAULT '10',
  `remark` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_categories`
--

LOCK TABLES `room_categories` WRITE;
/*!40000 ALTER TABLE `room_categories` DISABLE KEYS */;
INSERT INTO `room_categories` VALUES (1,'Premium',3,3541921,1500000,500000,'/images/database/Premium.jpg',90,'Premium là một trong những hạng phòng cao cấp được thiết kế theo phong cách tân cổ điển theo kiến ​​trúc Pháp và Premium được xây dựng dựa trên tiêu chuẩn và chất lượng theo tiêu chuẩn đánh giá cao. Với chất lượng và thiết kế nội thất cao cấp Tiện nghi, phòng Premium là một trong những loại phòng rất được du khách yêu thích và đánh giá cao. Phòng Prenium tại khách sạn LTWEB8 sở hữu không gian rộng thoáng, là diện tích lớn, trên tầng cao, có view ban công tổng thể tạo nên sự hài hòa, tinh tế mang đến nguồn cảm hứng yêu thích cho khách hàng. kỳ nghỉ hoặc chuyến công tác.'),(2,'Deluxe Double',4,3000000,1100000,200000,'/images/database/DeluxeDouble.jpg',10,'Nếu bạn đang tìm kiếm một kỳ nghỉ phù hợp hay chuyến công tác tại Hà Nội, phòng Deluxe của chúng tôi sẽ là sự lựa chọn lý tưởng. Phòng có diện tích rộng rãi, view nhìn ra cửa sổ tạo không gian thoáng đãng và tràn ngập ánh sáng là sự lựa chọn hợp túi tiền cho mọi du khách khi đến đây. Giường lớn hoặc 2 giường đơn đi kèm phòng tắm đứng được trang bị đầy đủ đồ vệ sinh cá nhân. Trang thiết bị trong phòng được khách sạn đầu tư hiện đại với tiêu chuẩn có Wi-Fi miễn phí, máy lạnh, cho phép quý khách tận hưởng kỳ nghỉ dưỡng hay công tác đúng nghĩa.'),(3,'Executive Double',3,3819987,1700000,600000,'/images/database/ExecutiveDouble.jpg',10,'Sự thoải mái và tiện lợi kết hợp với nhau trong các phòng Superior của chúng tôi. Được thiết kế đương đại với nội thất hiện đại và phòng tắm riêng với lựa chọn giường cỡ king hoặc hai giường đơn. Phòng  Executive Double (25 - 28m2) còn có cửa sổ bên trong, mang đến cho bạn sự thoải mái chưa từng có cho một kỳ nghỉ sang trọng và một giấc ngủ ngon'),(4,'Executive Suite',3,6584058,2000000,900000,'/images/database/ExecutiveSuite.jpg',10,'Dành riêng cho những du khách kiếm tìm một không gian nghỉ dưỡng thượng lưu, phòng Executive Suite sở hữu diện tích lên đến 52m2 với phòng khách sang trọng, phòng ngủ ấm cúng và ban công riêng biệt. Không chỉ chú trọng vào kiến trúc trang nhã cùng nội thất mang tính thẩm mỹ cao, các phòng Executive Suite còn làm hài lòng các du khách khó tính nhất khi tất cả các vật dụng và tiện nghi trong phòng từ đệm giường, bàn làm việc, vật dụng phòng tắm, các thiết bị ti vi, âm thanh giải trí… đều đạt tiêu chuẩn hàng đầu.'),(5,'Grand Suite',3,8963838,2500000,120000,'/images/database/GrandSuite.jpg',10,'Phòng Grand Suite là hạng phòng cao cấp nhất tại LTWEB8 Hotel, có diện tích 67m2. Là sự hòa quyện tổng thể của kiến trúc hiện đại lấy phong cách chủ đạo vùng quê lúa, các phòng Grand Suite có tầm nhìn bao quát toàn cảnh thành phố và luôn ngập tràn ánh sáng thiên nhiên. Thiết kế phòng khách riêng biệt và sang trọng, phòng ngủ với giường đôi lớn, phòng tắm được trang bị thêm bồn tắm và các thiết bị vệ sinh hiện đại, Grand Suite sẽ giúp kỳ nghỉ hoặc chuyến công tác của Quý khách trở nên hoàn hảo hơn.');
/*!40000 ALTER TABLE `room_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_reservations`
--

DROP TABLE IF EXISTS `room_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL,
  `reservation_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_reservations_ibfk_1` (`room_id`),
  KEY `room_reservations_ibfk_2` (`reservation_id`),
  CONSTRAINT `room_reservations_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `room_reservations_ibfk_2` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_reservations`
--

LOCK TABLES `room_reservations` WRITE;
/*!40000 ALTER TABLE `room_reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `room_category_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` int DEFAULT '10',
  PRIMARY KEY (`id`),
  KEY `FKpdtwv0pgaj683ec5l5hrw1oys` (`room_category_id`),
  CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`room_category_id`) REFERENCES `room_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_reservations`
--

DROP TABLE IF EXISTS `service_reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `service_id` bigint DEFAULT NULL,
  `reservation_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `service_id` (`service_id`),
  KEY `reservation_id` (`reservation_id`),
  CONSTRAINT `service_reservations_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`),
  CONSTRAINT `service_reservations_ibfk_2` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_reservations`
--

LOCK TABLES `service_reservations` WRITE;
/*!40000 ALTER TABLE `service_reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image_url` text NOT NULL,
  `price` bigint NOT NULL,
  `remark` text,
  `status` int DEFAULT '10',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Restaurant','/images/database/Restaurant.jpg',200000,'Thanh bình và trang nhã, thiết kế Tân cổ điển của Nhà hàng Lotus làm tăng thêm trải nghiệm ẩm thực cao cấp mà bạn có. Với ẩm thực theo mùa kết hợp giữa đặc sản bản địa và quốc tế đa dạng, đội ngũ đầu bếp và nhân viên mang đến cho du khách những trải nghiệm ẩm thực tinh tế. ',10),(2,'Conference room','/images/database/ConferenceRoom.jpg',2000000,'Với lối kiến trúc sang trọng, lịch lãm, LTWEB8 sở hữu đa dạng các loại phòng họp đáp ứng hoàn hảo nhu cầu của Quý khách, Hoàng Sơn White Palace chính là sự lựa chọn tối ưu cho các sự kiện,...',10),(3,'Pool','/images/database/Pool.jpg',20000,' Một không gian sẽ gây hứng thú với nhiều du khách.Bề bơi trong nhà có thể hoạt động 4 mùa,thu hút được đông đảo du khách với không gian thiết kế hiện đại,tinh tế.',10),(4,'Spa','/images/database/Spa.jpg',100000,'Khi đi du lịch nghỉ dưỡng, dịch vụ Spa là một dịch vụ không thể thiếu mà các chị em quan tâm. Đúng với tính chất nghỉ dưỡng, dịch vụ Spa sẽ giúp cho khách hàng có thể thư giãn, giải tỏa được mệt mỏi và căng thẳng.',10),(5,'Gym','/images/database/Gym.jpg',10000,'Du khách có thể tìm thấy mọi thiết bị thể thao cần thiết tại phòng tập gym . Đối với những ai yêu thích bộ môn yoga thì khách sạn cũng có ngay phòng tập yoga trên sân thượng và thái cực quyền.',10),(6,'Bar','/images/database/Bar.jpg',100000,'Dịch vụ quầy bar là dịch vụ được  thu hút rất đông bởi đây là nơi du khách có thể thư giãn, xả stress, thưởng thức các hoạt động vui chơi lành mạnh tại đây.',10);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-09 22:41:55

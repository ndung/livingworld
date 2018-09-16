/*
SQLyog Community v12.2.1 (64 bit)
MySQL - 10.2.14-MariaDB : Database - livingworld
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`livingworld` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `livingworld`;

/*Table structure for table `admin_menu` */

DROP TABLE IF EXISTS `admin_menu`;

CREATE TABLE `admin_menu` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `url_path` varchar(45) DEFAULT NULL,
  `parent` varchar(45) DEFAULT NULL,
  `level` varchar(30) NOT NULL DEFAULT 'AD',
  `icon` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `admin_menu` */

insert  into `admin_menu`(`id`,`name`,`url_path`,`parent`,`level`,`icon`) values 
('010000','User',NULL,'000000','','px-nav-icon ion-android-contacts'),
('010001','My Account','/user/myaccount.html','010000','AD,SD,RS',NULL),
('030002','Special Offers','/offer/offermanagement.html','030000','AD,SD',NULL),
('030000','Event and Offers',NULL,'000000','AD','px-nav-icon ion-ios-briefcase'),
('030001','Event','/event/eventmanagement.html','030000','AD',NULL),
('020002','Message','/member/messages.html','020000','AD,SD,RS',NULL),
('020001','Member List','/member/list.html','020000','AD,SD,RS',NULL),
('020000','Member Area',NULL,'000000','','px-nav-icon ion-ios-chatboxes'),
('010003','User Management','/user/usermanagement.html','010000','AD,SD,RS',NULL),
('010002','Change Password','/user/changepassword.html','010000','AD,SD,RS',NULL),
('040000','Reward and Redeem',NULL,'000000','AD','px-nav-icon ion-heart'),
('040001','Reward','/redeem/rewardmanagement.html','040000','AD',NULL),
('040002','Redeem','/redeem/listredeemed.html','040000','AD',NULL);

/*Table structure for table `admin_role_menu` */

DROP TABLE IF EXISTS `admin_role_menu`;

CREATE TABLE `admin_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `fk_role_menu_1` (`menu_id`),
  KEY `fk_role_menu_2` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `admin_role_menu` */

insert  into `admin_role_menu`(`role_id`,`menu_id`) values 
(1,'010000'),
(1,'010001'),
(1,'010002'),
(1,'010003'),
(1,'020000'),
(1,'020001'),
(1,'020002'),
(1,'020003'),
(1,'030000'),
(1,'030001'),
(1,'030002'),
(1,'040000'),
(1,'040001'),
(1,'040002'),
(2,'010000'),
(2,'010001'),
(2,'010002'),
(2,'040002');

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `city` */

insert  into `city`(`id`,`name`) values 
('7F1BC0FD-D8C8-4A29-A15A-A4EC2B6A5F37','BANDUNG'),
('D47F1BA8-1B87-40FB-A38F-238C341EBCB6','BANJARMASIN'),
('B1CC2B84-BFD5-447F-AB3A-EC2499A1E70F','BEKASI'),
('4E2C8EE5-367C-405A-A3F8-86052D7C8ABA','BOGOR'),
('A50BCE87-39C4-485B-AD85-7AE7DC4B1031','DENPASAR'),
('EB774B84-0553-4B20-AB16-B904910FC3C0','DEPOK'),
('4E2CB25D-FC3D-474E-932C-CDB77F4D2133','JAKARTA BARAT'),
('881E4ABE-1EE4-4E17-B264-813004CFCF36','JAKARTA PUSAT'),
('1B6E2CA3-0FA9-41D5-8E60-49BF51069A16','JAKARTA SELATAN'),
('B4D0ACF8-290F-4D96-96A0-1CC2CF10037A','JAKARTA TIMUR'),
('A11ADCCF-E071-4269-A525-1E04151A7716','JAKARTA UTARA'),
('29E626A0-907F-490E-A17C-AE567782F751','MAKASSAR'),
('3D91AC8A-3EA3-4B0C-88F1-DA18F01451D7','MEDAN'),
('AA8DF2F1-43F1-4DB8-85BB-CB6E73D40A81','OTHERS'),
('952854BF-F74B-4D30-9A58-EF844D82778E','PALEMBANG'),
('8BE114E1-2B0F-4BC6-8B64-0C457569E99E','SEMARANG'),
('2CFCB846-9EF0-4719-B315-45027BF57402','SUKABUMI'),
('553022C0-51B1-49F6-9B7F-EE9BD9041060','SURABAYA'),
('34DBFA58-D208-44EC-83E2-A59A336BFC6B','TANGERANG'),
('CBA1BD77-D6E3-4D5D-B166-5B0CA6E9CD5E','YOGYAKARTA');

/*Table structure for table `current_offer` */

DROP TABLE IF EXISTS `current_offer`;

CREATE TABLE `current_offer` (
  `current_offer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `description` text DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `short_description` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  PRIMARY KEY (`current_offer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `current_offer` */

insert  into `current_offer`(`current_offer_id`,`create_at`,`description`,`end_date`,`short_description`,`start_date`,`title`,`update_at`,`active`) values 
(1,'2018-02-03 16:26:34','Enak banget udah itu murah itu yang penting','2018-09-30 00:00:00','Masakan Sunda','2017-12-13 00:00:00','Restoran Sunda','2018-09-15 20:19:15','Y'),
(2,'2018-02-03 16:26:34','Fusce vehicula dolor arcu, sit amet blandit dolor mollis nec. Donec viverra eleifend lacus, vitae ullamcorper metus. Sed sollicitudin ipsum quis nunc sollicitudin ultrices. Donec euismod scelerisque ligula. Maecenas eu varius risus, eu aliquet arcu. Curabitur fermentum suscipit est, tincidunt mattis lorem luctus id. Donec eget massa a diam condimentum pretium. Aliquam erat volutpat. Integer ut tincidunt orci. Etiam tristique, elit ut consectetur iaculis, metus lectus mattis justo, vel mollis eros neque quis augue. Sed lobortis ultrices lacus, a placerat metus rutrum sit amet. Aenean ut suscipit justo.','2018-09-30 00:00:00','Lorem Ipsum Dolor Sit Amet','2017-12-13 00:00:00','Lorem Ipsum','2018-09-15 20:40:22','Y');

/*Table structure for table `current_offer_image` */

DROP TABLE IF EXISTS `current_offer_image`;

CREATE TABLE `current_offer_image` (
  `current_offer_image_id` varchar(255) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `current_offer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`current_offer_image_id`),
  KEY `FKdoo7iwq3eoysaml16sihpb4q1` (`current_offer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `current_offer_image` */

insert  into `current_offer_image`(`current_offer_image_id`,`create_at`,`update_at`,`current_offer_id`) values 
('c95fc086-3085-41ed-bc8b-a95d10e1d542.jpg','2018-02-03 16:26:35','2018-02-03 16:26:35',1),
('1f632cfb-b3e0-4276-8e94-b504f81070b8.jpg','2018-02-03 16:26:35','2018-02-03 16:26:35',1),
('a04807a9-34c5-4627-b4b9-1eab4171b76e.jpg','2018-02-03 16:26:35','2018-02-03 16:26:35',2),
('05bfe555-8f74-456d-9ad3-6948db85394b.jpg','2018-02-03 16:26:35','2018-02-03 16:26:35',2),
('37ada0d3-8437-4c96-917b-ea7bcc749a8d.jpg','2018-02-03 16:26:35','2018-02-03 16:26:35',1);

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `event` */

insert  into `event`(`event_id`,`create_at`,`description`,`end_date`,`image`,`name`,`start_date`,`update_at`,`active`) values 
(1,NULL,'Celebrate Christmas With Us','2018-12-25 00:00:00','rawpixel-com-445788.png','Christmas & New year<br/>Special','2018-06-01 00:00:00','2018-09-15 20:18:38','Y');

/*Table structure for table `gender` */

DROP TABLE IF EXISTS `gender`;

CREATE TABLE `gender` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `gender` */

insert  into `gender`(`id`,`name`) values 
('M','Male'),
('F','Female');

/*Table structure for table `lucky_draw` */

DROP TABLE IF EXISTS `lucky_draw`;

CREATE TABLE `lucky_draw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8gag6gchiso1wrve3v4d65pse` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;

/*Data for the table `lucky_draw` */

insert  into `lucky_draw`(`id`,`create_at`,`no`,`time`,`user_id`) values 
(1,'2018-06-04 23:25:35','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(2,'2018-06-04 23:25:35','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(3,'2018-06-04 23:25:35','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(4,'2018-06-04 23:25:35','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(5,'2018-06-04 23:25:35','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(6,'2018-06-04 23:25:35','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(7,'2018-06-05 15:02:49','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(8,'2018-06-05 15:02:49','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(9,'2018-06-05 15:02:49','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(10,'2018-06-05 15:02:49','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(11,'2018-06-05 15:02:49','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(12,'2018-06-05 15:02:49','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(13,'2018-06-05 15:15:36','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(14,'2018-06-05 15:15:36','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(15,'2018-06-05 15:15:36','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(16,'2018-06-05 15:15:36','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(17,'2018-06-05 15:15:36','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(18,'2018-06-05 15:15:36','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(19,'2018-06-07 14:58:56','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(20,'2018-06-07 14:58:56','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(21,'2018-06-07 14:58:56','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(22,'2018-06-07 14:58:56','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(23,'2018-06-07 14:58:56','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(24,'2018-06-07 14:58:56','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(25,'2018-06-07 14:59:23','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(26,'2018-06-07 14:59:23','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(27,'2018-06-07 14:59:23','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(28,'2018-06-07 14:59:23','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(29,'2018-06-07 14:59:23','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(30,'2018-06-07 14:59:23','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(31,'2018-06-22 10:54:34','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(32,'2018-06-22 10:54:34','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(33,'2018-06-22 10:54:34','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(34,'2018-06-22 10:54:34','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(35,'2018-06-22 10:54:34','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(36,'2018-06-22 10:54:34','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(37,'2018-06-22 19:29:57','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(38,'2018-06-22 19:29:57','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(39,'2018-06-22 19:29:57','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(40,'2018-06-22 19:29:57','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(41,'2018-06-22 19:29:57','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(42,'2018-06-22 19:29:57','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(43,'2018-06-22 20:04:43','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(44,'2018-06-22 20:04:43','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(45,'2018-06-22 20:04:43','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(46,'2018-06-22 20:04:43','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(47,'2018-06-22 20:04:43','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(48,'2018-06-22 20:04:44','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(49,'2018-07-05 11:37:18','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(50,'2018-07-05 11:37:18','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(51,'2018-07-05 11:37:18','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(52,'2018-07-05 11:37:18','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(53,'2018-07-05 11:37:18','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(54,'2018-07-05 11:37:18','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(55,'2018-07-05 11:50:41','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(56,'2018-07-05 11:50:41','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(57,'2018-07-05 11:50:41','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(58,'2018-07-05 11:50:41','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(59,'2018-07-05 11:50:41','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(60,'2018-07-05 11:50:41','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(61,'2018-07-05 11:50:48','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(62,'2018-07-05 11:50:48','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(63,'2018-07-05 11:50:48','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(64,'2018-07-05 11:50:48','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(65,'2018-07-05 11:50:48','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(66,'2018-07-05 11:50:48','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(67,'2018-07-05 12:11:14','000001','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(68,'2018-07-05 12:11:14','000002','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(69,'2018-07-05 12:11:14','000003','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(70,'2018-07-05 12:11:14','000004','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(71,'2018-07-05 12:11:14','000005','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E'),
(72,'2018-07-05 12:11:14','000006','2018-03-07 14:32:41','38C5EAD7-7E31-4A7E-9545-FEA543B8751E');

/*Table structure for table `marital_status` */

DROP TABLE IF EXISTS `marital_status`;

CREATE TABLE `marital_status` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `marital_status` */

insert  into `marital_status`(`id`,`name`) values 
('S','Single'),
('M','Married');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `card_number` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `home_phone` varchar(255) DEFAULT NULL,
  `idenitity_number` varchar(255) DEFAULT NULL,
  `identity_name` varchar(255) DEFAULT NULL,
  `martial_status` varchar(255) DEFAULT NULL,
  `member_type` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `nationalitly` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`card_number`),
  KEY `FKswb523yn1xw3806ojrfpcyadl` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `member` */

insert  into `member`(`card_number`,`address`,`city`,`create_at`,`date_of_birth`,`email`,`gender`,`home_phone`,`idenitity_number`,`identity_name`,`martial_status`,`member_type`,`mobile_number`,`nationalitly`,`religion`,`update_at`,`zipcode`,`user_id`) values 
('LWG008982','Ghjjjjh','7F1BC0FD-D8C8-4A29-A15A-A4EC2B6A5F37','2018-09-15 23:51:50','2018-09-03','as@s.com','F','ghhjhj','55566665','tsst','S','DCB2E640-C16D-4D55-86D4-DEAB5A7DD092','1234567890','WNI','295CC50F-813D-4873-939A-A83D8BA4EEE2','2018-09-16 17:08:11','5556','fa0082e5-32b5-47c3-9c88-be06d312523b'),
('LWB002999','JL. PINUS VI ELOK D.5/20 SEKTOR 1-1\r\nRT 008/019 RAWABUNTU\r\nSERPONG','34DBFA58-D208-44EC-83E2-A59A336BFC6B',NULL,'1986-12-06','mettanimitha@gmail.com','F','','3674015612860002','DHAMMA MIMITHA','M','38C5EAD7-7E31-4A7E-9545-FEA543B8751E','628170838678','WNI','C8B985F9-EBBF-44EC-A1E1-1107859DD8AB','2018-09-15 22:30:15','','093b57f1-7996-4b51-928e-83648377b877'),
('LWB002875','JL. SALAM RAYA NO. 82 RT.07/03 SUKABUMI UTARA KEBON ','8BE114E1-2B0F-4BC6-8B64-0C457569E99E','2018-09-13 16:41:25','2018-09-10','i.miranti@gmail.com','F','08123456789','3173054301821001','INDAH KALALO','M','38C5EAD7-7E31-4A7E-9545-FEA543B8751E','628118030182','WNI','564227D2-1282-4CA6-8C04-82D813A92202','2018-09-16 17:21:59','12345','33ccfef4-7049-428d-a3f3-c42a3c0ed71f');

/*Table structure for table `member_type` */

DROP TABLE IF EXISTS `member_type`;

CREATE TABLE `member_type` (
  `id` varchar(255) NOT NULL,
  `minimum_transaction` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `member_type` */

insert  into `member_type`(`id`,`minimum_transaction`,`name`) values 
('DCB2E640-C16D-4D55-86D4-DEAB5A7DD092','500000','GREEN'),
('7B1082F6-E2A6-4DBE-843D-F3A08113408A','40000000','PREMIERE'),
('38C5EAD7-7E31-4A7E-9545-FEA543B8751E','10000000','BLUE');

/*Table structure for table `merchant` */

DROP TABLE IF EXISTS `merchant`;

CREATE TABLE `merchant` (
  `merchant_id` varchar(255) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `description` text DEFAULT NULL,
  `merchant_image` varchar(255) DEFAULT NULL,
  `merchant_logo` varchar(255) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `merchant_phone` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `merchant_category_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`merchant_id`),
  KEY `FKtfky3ko131785ftsv05bet8y5` (`merchant_category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `merchant` */

insert  into `merchant`(`merchant_id`,`create_at`,`description`,`merchant_image`,`merchant_logo`,`merchant_name`,`merchant_phone`,`update_at`,`merchant_category_id`) values 
('0DA7E526-4175-4C55-B30C-87AF6A48E19D','2018-09-13 15:48:55','','/img/seller_galery/ACE.jpg','/img/seller_galery/ACE.jpg','ACE HARDWARE','(021) 5312-5501','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('7F135106-2E27-4BB7-91A9-5F7A1D640108','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PERAGRO','','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('89669113-06cf-4c51-a722-6b8efb824abd','2018-09-13 15:48:55','tenant cinema 21','/img/seller_galery/IMG_6648.jpg','/img/seller_galery/IMG_6648.jpg','CINEMA 21','(021) 2435-567_','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('8EFAF349-4E95-47D0-99C1-BE19975FC380','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','LACASSA','','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('B0D8B674-5989-4661-B4E6-3C0F1A68C728','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PENDOPO','02153125488','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('C7F23570-A4E6-4674-8A59-470C4BC554F1','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','LITHTLEIN','02129239497','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('DC1E873F-4008-4622-AE4B-AB2E291FA169','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ZWILLING','02129239491','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('E9DA20BC-8BEB-4182-8C69-8239A00B5F68','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','INFORMA','02153128566','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('ef10f12c-526a-4a05-9541-d425fe0a6d90','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','INFORMA CUSTOME FURNITURE','','2018-09-16 17:27:55','8CE1F8A9-8C7E-41D9-A18D-241EF12F377A'),
('7e66069e-e16c-4f88-875b-2bf54b945a4f','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MINISO','','2018-09-16 17:27:55','32A323F6-6046-49BC-A8E6-31035CA6639C'),
('CB3CD82E-6F97-46A9-A203-BBC287975D91','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BIKE COLONY','021-53125498','2018-09-16 17:27:55','32A323F6-6046-49BC-A8E6-31035CA6639C'),
('D04D652D-1D4A-43F0-A24A-08C51EFDAA43','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DISC TARRA','02129239416','2018-09-16 17:27:55','32A323F6-6046-49BC-A8E6-31035CA6639C'),
('F9AA2AA1-5F6F-4E33-BC4B-FE96F107AB6A','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CRAFT STUDIO','02129239516','2018-09-16 17:27:55','32A323F6-6046-49BC-A8E6-31035CA6639C'),
('DE4C5472-02AB-4CA1-B1DB-2FE0F7E1B5F8','2018-09-13 15:48:55','','/img/seller_galery/Hero.jpg','/img/seller_galery/Hero.jpg','HERO','(021) 5312-5470','2018-09-16 17:27:55','61D4B5CA-40FB-4173-9BB8-51463CAD13A2'),
('4C3776B5-D900-40CE-BF27-4B33E7FD7F39','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','HEADQUARTER','02129239493','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('5974630A-65C6-46A3-AC0C-4CE212D2DC5B','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KIDDY CUTS','02129239492','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('64F0CC7A-C7F8-4C17-9A81-88F3FEC482A0','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','C&F','(021)5312-8009','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('6523D878-F2EE-45B1-A24E-F546488AE3E2','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MARTHA TILAAR','(021)2923-9470','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('669E4A54-C3D1-441B-9EE5-E8C8D8863F21','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PASSION JEWELLERY','','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('79B0C6DB-B665-43F8-9C17-5B3B604401ED','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','THE BODY SHOP','(021)2923-9487','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('7fde5dd2-1d3d-4955-9435-235ae5c33e82','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PAT\'S BARBERSHOP','','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('8391B52C-AAA2-451D-A583-273DC82E931F','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','NAILGROOMED','02129239482','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('9FBF109B-4926-48FD-8F6B-BA8C767C8555','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','THE FACE SHOP','021 ? 45845932','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('a66880c0-6387-447d-9779-3ed2f29d169f','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CELL SCIENCE','','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('AA20AD51-E994-44FB-8BA7-58DE3899C9C4','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','YU MEE NAIL','02153125653','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('acfcf375-dc71-4872-bb31-90fe45b3b184','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SAINBELLE','','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('B42423A5-281E-46D0-AFEA-24B0A23D631A','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','JPP SKIN CARE','','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('DDDEE58A-05C7-466A-8B3D-97925533AAE5','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','JOHNNY ANDREAN','02129239498','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('FA9D38CC-8C37-4205-9A80-7A6CC131B77B','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PAXI & KID','021-23646000','2018-09-16 17:27:55','0CC71E09-D7ED-4A27-8086-530567CD542A'),
('002DDC84-734A-4ADD-A2A1-8209083B3ADC','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','THE EXECUTIVE','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('01233876-6F51-467B-9483-469207C01D63','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TREE HOUSE','(021) 2923-9520','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('01331555-A5E5-47E2-91DD-930AEDD5E8E5','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WACOAL','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('12a77ba1-61e7-436c-90ce-d160c61f2e12','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MENZONE','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('246a85c9-ba28-49e3-b893-544f29ac0338','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','COLORBOX','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('31E7F19F-8BB8-4676-9E81-FAFE763817A0','2018-09-13 15:48:55','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','POLO','021-29239541','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('3E3B3CFE-0025-4F32-B958-6B199D5D9D1C','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','THE LITTLE THINGS SHE NEEDS','(021) 2923-9473','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('430CEB71-F28A-42B3-AC10-7033E42EE249','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','LEAF','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('43BEAAA9-8DD8-456E-A8B5-F339C08B65B0','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','GUARDIAN','(021)5312-5574','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('4a5eed9a-15c1-4613-8d80-3f315a95f678','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CYNDY DAN YAYANG','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('4afa0423-ed9d-44c4-a86f-9740cc0b8d4c','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CINDY YAYANG','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('4e389ef2-9983-4ffa-a9bf-b09b07122955','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','EPRISE','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('537EFB08-11B0-4012-B70D-552F16E7B089','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MINIMAL','02129239447','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('6083db90-4a5a-407d-bf6e-dfda29afecfb','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CLEOPATRA','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('61F32C31-9B25-4605-A901-5A43EDA6289B','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SPORTS STATION','02129239539','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('69ae3c7d-9d05-4076-bc3e-40eb9ea5b80d','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','OOPPAA','','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('69F73593-239F-4CAB-99DD-409DE825A3D8','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SAMUEL & KEVIN','021-29239526','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('7A936DD9-14BB-40C0-8649-4C684EC720FF','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TARI GALERI','02129239588','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('8287F2EB-4EF1-494D-965E-19BCF3C1C712','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BALENO','021-29239526','2018-09-16 17:27:55','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('829d7737-0d6e-467b-b183-d8e29b0d4cf5','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MINISO','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('861e2781-68d0-458c-8b3e-b59b45ff255f','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WAKAI','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('A580F17A-0B80-4AC4-8391-6D2997F478BA','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','RADIANCE WATCH','(021)2923-9500','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('b1c70866-db45-421d-abde-c2c4149c6ed7','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','Salt & Paper','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('BB4D9784-868E-4479-A1D2-7078CC96C822','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PS&M2','02129239550','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('C8270D5F-1E38-4823-9085-90B5CF770549','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','COCONUT ISLAND','02129239522','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('CB963BFD-14CE-4D5B-89DA-7A3A78C3E4EF','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MAAYA','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('CEF1B2B6-B8E2-4A34-920C-C4DFB7FDD595','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CAMEL ACTIVE','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('d70ed382-ea92-4ce9-bcb1-a1c8c00dee75','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','THIS IS APRIL','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('D7190A66-6AA0-4525-802D-C324EB8C619C','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','COCONUT BABY SHOP','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('D7CB1B0F-A0BA-4ACF-8081-D84232B03A35','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CROCS','02129239471-72','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('E257F96B-3FC9-4CC7-924B-9EF00166FB91','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PAYLESS','02129239524','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('F0931D3A-D8FB-4E14-8C32-85D704020E0E','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CERYCHAN','02129239485','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('F7EB1E25-34E0-43BF-A493-6307D8FF5987','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CARATI','','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('FD755971-0345-4946-8DA0-915591CDA827','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','GIORDANO','02129239490','2018-09-16 17:27:56','9E87CE21-CED0-4A99-B56C-5AE324C6C03A'),
('5221f0e5-6cfe-4991-813b-e5d6f6b0c241','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FUN WORLD','','2018-09-16 17:27:56','DE165CC7-FBC6-4606-96CD-614504629719'),
('9E09615C-D56B-42D2-A725-564199A78844','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TOYS KINGDOM','02153128533','2018-09-16 17:27:56','DE165CC7-FBC6-4606-96CD-614504629719'),
('E3A02484-C3B6-4BF1-99B4-4F282CE0BA71','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','LOLLIPOP','','2018-09-16 17:27:56','DE165CC7-FBC6-4606-96CD-614504629719'),
('025829f6-5a6d-4cf3-baaa-973816d29f19','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PLAYWORKS','','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('0A2EECCC-D9F2-4EA2-90A7-B2EA4E3F22A9','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ERAFONE','02129239502','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('18727E68-72D8-49F7-A75F-EB48528FB92F','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ELECTRONIC SOLUTIONS','02129239420','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('272A4C08-00E8-4B45-9838-581FC619F2A0','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SAMSUNG PARTNER PLAZA','021-35881968','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('539E025D-6E1D-4B43-89D0-28B31B62FA5A','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','STORY I','','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('5FC04629-9C5F-4BBB-87F7-1053C56CABDA','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ZOOM/EXPEED','02153128644','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('694C7F86-B041-4E48-8A3B-0E9F1DFC5A3B','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CENTRA 2000','02129239455','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('76B3BB8B-EA77-4A5F-B299-714F9C095B18','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PDA.COM','02129239440','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('A03264C6-89A8-4893-957C-006D800E93B6','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','GLOBAL TELESHOP','02129239467','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('AE141C30-7BB5-451D-B69B-EDCB17C27D94','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SONY CENTER','','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('CE1901F6-8E1A-4243-AD38-228B7E5A3A3D','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','OKE SHOP','02129239430','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('da58e35b-2719-4833-b7a5-2539b8824e73','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PLAYWORKS','','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('DB14E0BA-1A95-4D27-BC1F-E9FBF7370EE8','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','POINT 2000','','2018-09-16 17:27:56','3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED'),
('8C3C2966-37DA-46DC-860F-AB990ED144E0','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','OPTIK SEIS','(021)5312-5605','2018-09-16 17:27:56','62C63B8D-85D2-40D1-9F64-B54C5A52CC82'),
('9D0773CE-4843-4469-B695-CE5F16D07020','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','OPTIK TUNGGAL','(021)5312-5479','2018-09-16 17:27:56','62C63B8D-85D2-40D1-9F64-B54C5A52CC82'),
('C02BFFB1-1FE8-4176-9CE6-2201522E0D8B','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','OPTIK MELAWAI','(021)5312-5493','2018-09-16 17:27:56','62C63B8D-85D2-40D1-9F64-B54C5A52CC82'),
('1E5E30B1-BB82-421C-89F4-8385AAE051D0','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','OFFICE 1','(021) 5312-5510','2018-09-16 17:27:56','C7DDBA6A-2762-487D-A69B-B710974B9EED'),
('32f973f0-f3ba-4d89-a2fb-f392682768e3','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BOOKS & BEYOND','','2018-09-16 17:27:56','C7DDBA6A-2762-487D-A69B-B710974B9EED'),
('087ECEF5-CE0A-4484-AA47-5C492B136600','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WATSONS','','2018-09-16 17:27:56','D4889955-622E-4B7B-B9A9-D9B344488FB3'),
('3D8F9BAF-1A7C-4AA7-A0B4-01880E56626D','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DR KONG','02153128530','2018-09-16 17:27:56','D4889955-622E-4B7B-B9A9-D9B344488FB3'),
('B44D67E7-EDDE-48C7-B39C-24385879A2F6','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CELEBRITY FITNESS','','2018-09-16 17:27:56','D4889955-622E-4B7B-B9A9-D9B344488FB3'),
('B9A54811-9E11-4E52-8EF2-75A4A9895FF8','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CENTURY','02153125477','2018-09-16 17:27:56','D4889955-622E-4B7B-B9A9-D9B344488FB3'),
('2A7BBA9A-386F-4D48-8231-56FCDA213B69','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','GARUDA  INDONESIA','','2018-09-16 17:27:56','3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB'),
('65efbef0-221a-4683-84f8-9e5087c66e10','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DWIDAYA TOUR','','2018-09-16 17:27:56','3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB'),
('6D0AABEF-0144-4872-83B0-7D497EE1B70F','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WF Laundry','(021) 2921-1559','2018-09-16 17:27:56','3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB'),
('72C810D1-7A11-4ABA-AECB-3524C84A53B5','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','RISE','','2018-09-16 17:27:56','3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB'),
('98e07338-87ed-4d54-ade4-0807602d121f','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PANORAMA TOUR','','2018-09-16 17:27:56','3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB'),
('bcb167d6-c6b3-4712-b43f-f1c6b873a7c7','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SURYA FALAS','','2018-09-16 17:27:56','3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB'),
('E837DC82-CE67-462D-93A9-82A04AD4B779','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','HANNIEN TOUR','021-29239499/29','2018-09-16 17:27:56','3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB'),
('AF613920-1AAA-4BFC-A035-634BA2DF811A','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','XXI','02153125569','2018-09-16 17:27:56','CFE00739-91DE-495D-933E-F13D3BD6FB30'),
('0511F5C9-89D6-4414-8B78-249C8C98C791','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SOLARIA','(021)2923-9444','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('05AA55E4-E33C-4772-810C-FEA7AF75B21F','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','JCO','(021) 5312-8557','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('0A951A5B-B2DB-4041-9811-37FF49503B0E','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','STICKY','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('0d830657-1684-410a-b2bb-fdf86ebeb540','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','Living Kitchen','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('0EBF25BF-9775-408B-864C-1C118405B47E','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FREEZE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('132a4074-a4c5-40ae-9e8a-4427a475ed58','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','THE DUCK KING','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('1336E7AD-AEE7-43FF-B0FE-30710695B00D','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BANGKOK JAM','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('15616a6c-584c-4f18-a88d-4854b36b5a88','2018-09-13 15:48:56','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DOUGH DARLINGS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('1B34C3B2-77F9-494A-AF84-41641E8B55B9','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','HACHIMAKI','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('1BD9C836-1784-4B7B-837E-CCD36B7B761D','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','OLDTOWN WHITE COFFEE','(021)5312-5502','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('1C9E5094-636C-4BD5-9C43-310518E5A729','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','JUN NJAN','(021)2923-9511','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('1cbb0907-5aa1-4f4a-b88f-afbd669b48f2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DIN TAI FUNG','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('20DE251D-B38E-43AB-AD2B-B3D8502DAAEB','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WAROENG KITA 2','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('2126fe5e-d67a-4953-bd42-8aa6cbfc246f','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','NUTS N ROLL','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('24C67F49-5FDA-4AF4-BF6B-719D7CD6A8B2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MOTTO MOTTO','(021) 2923-9450','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('2f0564bc-d33b-4800-8477-4652b678a89a','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CUPBOB','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('2F420A5F-B6C1-465F-BBDD-32EA95655CE2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TAKIGAWA','(021) 2923-9457','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('315f8058-89eb-4f31-96ce-3ba54a5d19d2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KOKORO','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('35910917-7B73-454B-9D30-40A9727BC5EE','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','GOLDEN EGG TART','(021)2923-9461','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('36C48120-814D-4C7B-87F7-0D83077EAE9B','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BASKIN ROBBINS','021-83688262','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('3c5fe797-c12a-4e25-9ff1-52ea467891d4','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SAIGON DELIGHT','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('3D4B9469-C377-42EE-98F1-E526B6FC27B2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MARUTAMA RA-MEN','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('3F38473F-A668-4202-9B22-660D336547E5','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BREADTALK','(021)5312-8548','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('3f44f8b1-cacd-4f88-a148-9fb00e62961b','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','JITTLADA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('400e1ec7-4d76-464e-96e9-e6d1354534ec','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SOUR SALLY','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('4266D0AA-C607-4815-BA93-4BF6D8F9609C','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DONBURI ICHIYA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('43C00755-08C5-41BA-8C6B-BD0BC1A01C15','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MALAY VILLAGE','(021)2923-9494','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('4492C085-ACC1-4734-9232-878D828FBE42','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ASTON','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('47609771-fc11-4e99-b3ad-c336cce7fa4f','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','NANNY\'S PAVILION','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('47A50F31-0D17-41AC-A9D6-85D24726D235','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BENE RISTORANTE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('492af18f-ca36-4ad7-a0d2-9b43a9e4befe','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','RAA CHA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('4B98A842-2174-44F2-8AD2-61787312F3F2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TA WAN','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('4BE55ABA-AE2C-4690-9833-D5AD373C5E1E','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ROPPAN','(021) 2923-9445','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('4CCB6E33-2DF9-43AB-988D-D439D67EA401','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BEBEK TEPI SAWAH','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('4E78582A-6F32-4921-8462-EDC416D3CAB8','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FOOD TOWN','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('51D361F1-0EC7-4E72-B207-A464AE2F707F','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ITASUKI','(021)2923-9459','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('527340f3-328e-4f59-bae1-7ecd76edeb9c','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SOP IKAN BATAM','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('52B0DB87-B733-49E7-B2D0-3C72916199E3','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FREEZE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('543BE1FC-CF84-4FCA-B809-6375F5884461','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CHATIME','02129239419','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('5481421e-720c-40a0-92e9-d6ae88ccc649','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','COOL KIDS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('56975034-524C-4F60-90C1-B321A38A9B2F','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','A GANTEA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('56FBCCF3-CA49-4162-9A45-67CF3D575D81','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CANTON BAY','(021) 2923-9448','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('58B05260-60C2-4C72-90FD-0A81AE2C259D','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SPINELLI COFFEE','(021)2923-9475','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('59b2cfc7-8825-436c-9c2c-fa3b4517f56d','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PEZZO','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('5ADD2426-2331-4B10-917C-A533838680E1','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SHIHLIN','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('5b954aa1-69d0-49ca-8f78-89f10b756cc8','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PIZZA EXPRESS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('5EE9C84C-CDFD-4D38-9944-37BBB7683E66','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KAFE BETAWI','(021)2923-9501','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('635bce7d-0a01-464d-91dc-060ea5da08bc','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TAMANI','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('6A8018C8-F6FD-4320-8F7C-87BDA6B35703','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BREADLIFE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('6ACF797A-CFB1-463D-B213-9413BD63B60B','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','STEAK 21','02153125594','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('6B0143D3-DCA8-4E1F-A019-80FC4724E687','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BONCHON','(021)2923-9545','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('6F79CDD1-FFA4-42B7-92B1-777F960F28AD','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','EATON. AH MEI & SENJU','(021)2923-9480','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('6f832b90-0cad-43fc-8b5f-8f29b1b347ca','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','RAA CHA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('72FFC423-D53A-41CB-9D8C-9B8F927E29AA','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ICHIBAN SUSHI','(021)2923-9478','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('73D9EF37-CFE7-4C58-B271-0233DBD45F5A','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BAKSO AFUNG','(021)2923-9429','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('75707CA6-EBCF-437A-AA2D-2B121AA7E534','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','COFFEE TOFFEE','021-53125480','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('7b5ae7ae-7340-495d-ab3e-ce467e315fc7','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','GYU-KAKU','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('7D657151-E629-4E94-B4BE-8E0889F8ABB9','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','LITTLE PENANG','(021)2923-9489','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('7f00235e-a367-4efc-9706-782e10ef882e','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','NAMA SUSHI','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('7F70A9C3-4A67-42E4-8675-5DDA60E3789F','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ZOODLE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('80D46C01-2767-43ED-918D-450E73B1B00C','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BUNTAMA RAMEN','(021)2923-9439','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('812142c6-22d1-4ea9-95e4-bd1a311351a2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','Thai Street','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('823c47e3-3be2-473a-beb1-0905525842b2','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FOUNDER BAK KUTH','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('83150333-D1FC-40A1-932B-BAB565344B2E','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BLACKBALL','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('87F7243C-77CA-4401-A1A0-195C8D2FEC0F','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BLACK CANYON COFFEE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('88512246-5D80-47A7-BE13-ADFF1BB515AC','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DONBURI ICHIYA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('88F2E582-B20F-4112-B667-00EBFF3B80EE','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MAQUIS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('89f754d3-1ae0-49ee-a8d9-89fc7c8c3439','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DJOURNAL COFFEE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('8a230804-2631-4045-a840-9a0acb23cc4d','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PEOPLE\'S CAFE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('8a904418-f44a-40a9-a946-48aa3b3543ed','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FIREPOT','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('8defde0b-e459-4769-af8c-4d43d238717d','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ALMONDTREE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('90cce0d0-5502-4a4b-8b2f-aac43df406ac','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','HUROM JUICE','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('911b2e65-0cea-48de-a4fd-0b5ae3c9c172','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','AUNTIE ANNES','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('91BA74DC-6A4A-47CF-BCA3-94905E15CFDD','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','DAPOER PENYET','(021) 2923-9449','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('91f1dc71-8c43-4635-bb63-22ec2afcba15','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WENDYS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('93358857-CD8C-4DF6-B9CC-A15051CF3D3E','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SAMUDRA SUKI','02129239505','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('943e4fde-71b1-41bb-8b13-9a9a004698da','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','PAPER LUNCH','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('957126DE-F9F0-459C-A643-E72760D2B037','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SATE KHAS SENAYAN','(021)5312-5494','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('A78B65D6-9731-4645-8745-7E67BDBDE1C3','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','ZENBU','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('A8013C4D-0505-4979-A679-C2E6799F07F7','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','101 JUNCTION','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('A9595AA9-6C6B-4B62-8C4B-9382CCE102EB','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SUSHIMISE','02153125778','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('A98F4794-0DFC-4D86-8F1E-37F473A9B1E8','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KOPITIAM','(021)2923-9427','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('ad25e5b5-88aa-431d-9a88-c1aaaadd5c73','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','Starbucks Coffee','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('af53bc3f-6cbd-47f7-a365-3b5f2e3427dc','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CHOP BUNTUT','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('B378AEEB-F8DF-41FF-9FCE-9787B1E2F4D8','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','AMADEUS THE CAFE','(021)2923-9464','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('b499347f-882e-438c-a679-40f3fc47a3ae','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','LIVING KITCHEN','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('b55950a8-688b-4690-a68b-9a89365109cd','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','Shaburi','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('bb5f952a-7b8d-43e7-871e-85475e54f824','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BURGER KING','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('C1C65F0C-33F2-4481-B0CE-2D986DF7AA7C','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WAROENG KITA 1','(021) 5312-8646','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('c33550a0-a8d0-48cd-8074-763da0dde773','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','HEI KOPI','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('c6c32844-79ee-4335-8965-2b04912278f9','2018-09-13 15:48:57','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','Imperial Kithchen','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('c80a72ff-fb5c-4532-998b-b8f2511cc501','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TOUS LES JOURS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('c96a4c06-61e2-4543-b549-dd68571292fd','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CHICK N ROLL','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('c97efcf7-e5b7-426e-a702-c66d86f5c15e','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TOUS LES JOURS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('C9A6067F-A501-40E7-B63A-53ECFCBF7086','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MR PANCAKE','(021)2923-9518','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('CC04D17F-AC7C-4699-AF9F-6F0D249E1AAE','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','RAFFEL\'S','(021)2923-9488','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('CDED90BB-4A5B-4841-8DCE-1787EAD45AB7','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MY KOPI O','(021)2923-9417','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('d3bd3907-0d63-40f9-92aa-1f24561baf44','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','CREMERIA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('D78E8E56-0A7E-4EFA-ADCB-38C645E212F3','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','HANGANG & WHITE HUNTER','(021)5312-5496','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('DA7A5C67-B984-465C-9CE3-DD69004D8D17','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BAKMI GM','(021)2923-9462','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('dc70bc5c-509d-4288-b623-8dde51165c48','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','HOKKAIDO CHEESE TART','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('dd82597a-11e9-4322-a737-abd2a691d716','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KYO CHON','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('DFE2ACD2-9AB7-44F5-BBB7-52134221AA6C','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','WOKHEI','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('E032D1EA-98E1-459A-BC81-75E246F21144','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','THE COFFEE BEAN TEA LEAF','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('E1E2060C-8811-4B6D-B847-B9D2005562A1','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','LAWTON COFFEE','(021)5312-5478','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('e382571c-a16d-461b-b277-4352a889297f','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MONAMI BAKERY','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('e40c31f2-b383-48d1-bcbf-bf7561243c00','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FUNKY MONKEY','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('e7685980-320b-453f-aa7a-d07d67043bae','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','SUNNY SIDE UP','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('E7DA6F0E-5037-4689-8097-46A41F1C5C6B','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','REMPAH-REMPAH','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('EACB9B28-33AD-47FB-850B-391DB4EB13CE','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KENNY ROGERS','(021)2923-9514','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('EC1093C9-CC55-4BFD-BE49-B476C829071F','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','FISH & CO','(021)2923-9452','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('f20ac859-fb1f-4d21-861a-dc03c1b3f1c2','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','MARUGAME UDON','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('F2F297F0-1004-4A10-BC9C-3724EDCBD4C4','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BEATRICE QUARTERS','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('f5f88837-6f6a-4cf7-ac70-fe9c27b137e9','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','TIGA WONTON','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('f6784916-f867-41e4-8f06-dd82d182883c','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','QUIK SILVER','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('f75d990b-281a-4f10-93b1-13f26e58c36c','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KEPITING CAK GUNDUL','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('f77b6759-9b67-4f2f-aec7-2eb9141386ca','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KOBA','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('f917a5f8-f436-4b0e-8ccd-b96b3c9dd739','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','KINTAN','','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4'),
('FE4F7B52-B101-4960-B65D-F30982435A95','2018-09-13 15:48:58','','/img/seller_galery/no_image.jpg','/img/seller_galery/no_image.jpg','BENGAWAN SOLO','(021)5312-8569','2018-09-16 17:27:56','0B4EB552-8D1C-424E-9310-F2D375E858D4');

/*Table structure for table `merchant_category` */

DROP TABLE IF EXISTS `merchant_category`;

CREATE TABLE `merchant_category` (
  `merchant_category_id` varchar(255) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `merchant_category_name` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`merchant_category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `merchant_category` */

insert  into `merchant_category`(`merchant_category_id`,`create_at`,`merchant_category_name`,`update_at`) values 
('0CC71E09-D7ED-4A27-8086-530567CD542A','2018-09-13 16:00:38','BEAUTY','2018-09-16 17:27:54'),
('43B454G7-7157-50CD-B9F7-42146DB7740D','2018-09-13 16:00:38','ENTERTAINMENT','2018-09-16 17:27:54'),
('9E87CE21-CED0-4A99-B56C-5AE324C6C03A','2018-09-13 16:00:38','FASHION','2018-09-16 17:27:54'),
('0B4EB552-8D1C-424E-9310-F2D375E858D4','2018-09-13 16:00:38','FOOD AND BEVERAGE','2018-09-16 17:27:54'),
('3A02DF26-5183-4C84-9A8B-AA3FDAD2B9ED','2018-09-13 16:00:38','GADGET','2018-09-16 17:27:54'),
('D4889955-622E-4B7B-B9A9-D9B344488FB3','2018-09-13 16:00:38','HEALTH','2018-09-16 17:27:54'),
('32A323F6-6046-49BC-A8E6-31035CA6639C','2018-09-13 16:00:38','HOBBY','2018-09-16 17:27:54'),
('8CE1F8A9-8C7E-41D9-A18D-241EF12F377A','2018-09-13 16:00:38','HOME LIVING','2018-09-16 17:27:54'),
('CFE00739-91DE-495D-933E-F13D3BD6FB30','2018-09-13 16:00:38','MOVIE','2018-09-16 17:27:54'),
('62C63B8D-85D2-40D1-9F64-B54C5A52CC82','2018-09-13 16:00:38','OPTIC','2018-09-16 17:27:54'),
('21A05FCD-75C7-4832-B09C-1738E9B2E086','2018-09-13 16:00:38','REDEEM POINT','2018-09-16 17:27:54'),
('3C6FEE6C-C1A3-4CBD-BF41-DB7B1BA761FB','2018-09-13 16:00:38','SERVICE','2018-09-16 17:27:54'),
('C7DDBA6A-2762-487D-A69B-B710974B9EED','2018-09-13 16:00:38','STATIONERY','2018-09-16 17:27:54'),
('61D4B5CA-40FB-4173-9BB8-51463CAD13A2','2018-09-13 16:00:38','SUPERMARKET','2018-09-16 17:27:54'),
('DE165CC7-FBC6-4606-96CD-614504629719','2018-09-13 16:00:38','TOYS','2018-09-16 17:27:54');

/*Table structure for table `merchant_office_hour` */

DROP TABLE IF EXISTS `merchant_office_hour`;

CREATE TABLE `merchant_office_hour` (
  `merchant_working_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `day` int(11) NOT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`merchant_working_id`),
  KEY `FK6fsewkr26t84dyrjf8j7214af` (`merchant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `merchant_office_hour` */

insert  into `merchant_office_hour`(`merchant_working_id`,`create_at`,`day`,`end_time`,`start_time`,`update_at`,`merchant_id`) values 
(1,'2018-01-27 18:00:51',0,'18:00','07:00','2018-01-27 18:00:51',1),
(2,'2018-01-27 18:00:51',1,'07:00','07:00','2018-01-27 18:00:51',1),
(3,'2018-01-27 18:00:51',2,'07:00','07:00','2018-01-27 18:00:51',1),
(4,'2018-01-27 18:00:51',3,'07:00','07:00','2018-01-27 18:00:51',1),
(5,'2018-01-27 18:00:51',4,'07:00','07:00','2018-01-27 18:00:51',1),
(6,'2018-01-27 18:00:51',5,'07:00','07:00','2018-01-27 18:00:51',1),
(7,'2018-01-27 18:00:51',6,'07:00','07:00','2018-01-27 18:00:51',1);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` timestamp NULL DEFAULT current_timestamp(),
  `message` varchar(255) DEFAULT NULL,
  `status` int(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `receiver_id` varchar(255) DEFAULT NULL,
  `sender_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`message_id`,`title`),
  KEY `FK86f0kc2mt26ifwupnivu6v8oa` (`receiver_id`),
  KEY `FKcnj2qaf5yc36v2f90jw2ipl9b` (`sender_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `message` */

/*Table structure for table `nationality` */

DROP TABLE IF EXISTS `nationality`;

CREATE TABLE `nationality` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `nationality` */

insert  into `nationality`(`id`,`name`) values 
('WNI','Indonesian'),
('WNA','Others');

/*Table structure for table `parameter` */

DROP TABLE IF EXISTS `parameter`;

CREATE TABLE `parameter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `shown` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `parameter` */

insert  into `parameter`(`id`,`name`,`value`,`shown`) values 
(1,'EXPIRED_REDEEM_HOURS','48','Y');

/*Table structure for table `redeem` */

DROP TABLE IF EXISTS `redeem`;

CREATE TABLE `redeem` (
  `redeem_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `member_id` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  PRIMARY KEY (`redeem_id`),
  KEY `FKgkr987stqouivl8sxul9gdgho` (`member_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `redeem` */

/*Table structure for table `redeemed_reward` */

DROP TABLE IF EXISTS `redeemed_reward`;

CREATE TABLE `redeemed_reward` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `redeem_id` bigint(20) DEFAULT NULL,
  `reward_id` bigint(20) DEFAULT NULL,
  `approved_status` int(1) DEFAULT NULL,
  `approved_date` datetime DEFAULT NULL,
  `approved_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5l9yp3b5nm0xkpc8cjw0s1q7` (`redeem_id`),
  KEY `FK8s1394oigwo06duyw5huooyr` (`reward_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `redeemed_reward` */

/*Table structure for table `religion` */

DROP TABLE IF EXISTS `religion`;

CREATE TABLE `religion` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `religion` */

insert  into `religion`(`id`,`name`) values 
('431DD247-B8E3-4DB6-BE40-09A797BCA3C0','KONG HUCU'),
('C8B985F9-EBBF-44EC-A1E1-1107859DD8AB','KRISTEN KATOLIK'),
('37A1CF89-191F-46FE-A61A-6F7F76693C2F','KRISTEN PROTESTAN'),
('564227D2-1282-4CA6-8C04-82D813A92202','ISLAM'),
('76B7D05D-E4C4-4D18-8300-8A44F3F3A934','BUDHA'),
('295CC50F-813D-4873-939A-A83D8BA4EEE2','HINDU');

/*Table structure for table `reward` */

DROP TABLE IF EXISTS `reward`;

CREATE TABLE `reward` (
  `reward_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reward_description` varchar(255) DEFAULT NULL,
  `reward_image` varchar(255) DEFAULT NULL,
  `reward_name` varchar(255) DEFAULT NULL,
  `reward_point` int(11) DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  `event_id` bigint(20) DEFAULT NULL,
  `merchant_id` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `active` char(1) DEFAULT NULL,
  PRIMARY KEY (`reward_id`),
  KEY `FKcdbh34aynvu7bcbd9n7b0aar9` (`event_id`),
  KEY `FKj1v5s7itvprh4bvqocck45790` (`merchant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `reward` */

insert  into `reward`(`reward_id`,`reward_description`,`reward_image`,`reward_name`,`reward_point`,`expired_date`,`event_id`,`merchant_id`,`create_at`,`update_at`,`active`) values 
(1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc elementum nulla ut nisi vestibulum dapibus ut a elit. Donec semper odio et dignissim sodales. Aliquam erat volutpat.','toa-heftiba-296438.png','Bakmi Ayam Special',50000,'2018-09-18 00:00:00',1,'0DA7E526-4175-4C55-B30C-87AF6A48E19D',NULL,'2018-09-15 19:40:47','Y'),
(2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc elementum nulla ut nisi vestibulum dapibus ut a elit. Donec semper odio et dignissim sodales. Aliquam erat volutpat.','chris-barbalis-135753.png','Voucher Hotel',10000,NULL,1,'0DA7E526-4175-4C55-B30C-87AF6A48E19D',NULL,'2018-09-15 19:40:48','Y'),
(3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc elementum nulla ut nisi vestibulum dapibus ut a elit. Donec semper odio et dignissim sodales. Aliquam erat volutpat.','hanny-naibaho-353172.png','Nasi Special',50000,NULL,1,'0DA7E526-4175-4C55-B30C-87AF6A48E19D',NULL,'2018-09-15 19:40:49','Y'),
(4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc elementum nulla ut nisi vestibulum dapibus ut a elit. Donec semper odio et dignissim sodales. Aliquam erat volutpat.','ben-white-165651.png','A Cup of Coffee',20000,NULL,1,'0DA7E526-4175-4C55-B30C-87AF6A48E19D',NULL,'2018-09-15 21:27:34','Y');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`id`,`name`) values 
(1,'USER'),
(2,'MERCHANT');

/*Table structure for table `sof` */

DROP TABLE IF EXISTS `sof`;

CREATE TABLE `sof` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `sof` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `ecash_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo_profile_url` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `merchant_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_fueait3ruvr048vm626fr6dio` (`card_number`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`card_number`,`create_at`,`date_of_birth`,`ecash_id`,`email`,`full_name`,`mobile_number`,`password`,`photo_profile_url`,`status`,`update_at`,`role_id`,`merchant_id`) values 
('33ccfef4-7049-428d-a3f3-c42a3c0ed71f','lwb002875',NULL,'2018-09-10 00:00:00',NULL,'i.miranti@gmail.com','INDAH KALALO','628118030182','e10adc3949ba59abbe56e057f20f883e',NULL,0,'2018-09-16 17:21:59',NULL,NULL),
('admin',NULL,NULL,NULL,NULL,'admin@livingworld.id','administrator','081234567890','e10adc3949ba59abbe56e057f20f883e',NULL,1,NULL,1,NULL),
('093b57f1-7996-4b51-928e-83648377b877','LWB002999',NULL,'1986-12-06 00:00:00',NULL,'mettanimitha@gmail.com','DHAMMA MIMITHA','628170838678','e10adc3949ba59abbe56e057f20f883e',NULL,0,'2018-09-15 22:30:15',NULL,NULL),
('fa0082e5-32b5-47c3-9c88-be06d312523b','LWG008982','2018-09-15 23:51:50','2018-09-03 00:00:00',NULL,'as@s.com','tsst','1234567890','e10adc3949ba59abbe56e057f20f883e',NULL,0,'2018-09-16 17:08:11',NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values 
('33ccfef4-7049-428d-a3f3-c42a3c0ed71f',1),
('093b57f1-7996-4b51-928e-83648377b877',1),
('fa0082e5-32b5-47c3-9c88-be06d312523b',1);

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `vehicle_id` varchar(255) NOT NULL,
  `vehicle_color` varchar(255) DEFAULT NULL,
  `vehicle_number` varchar(255) DEFAULT NULL,
  `vehicle_type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `UK_d5rc6b0dnypuf0pxvr25mgfvt` (`vehicle_number`),
  KEY `FKrmyxjc1r0nxymg692mq9emy56` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `vehicle` */

insert  into `vehicle`(`vehicle_id`,`vehicle_color`,`vehicle_number`,`vehicle_type`,`user_id`) values 
('3d3bb860-870d-4d4f-bd7c-e6ce17c78f39','BLUE','1231','BMW','c3b6828a-3fc3-4a30-ac21-6061629b0cd6'),
('48bebb26-7339-4a8d-9f0e-0947a5c1993d','zhzh',NULL,'bzh','c3b6828a-3fc3-4a30-ac21-6061629b0cd6');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

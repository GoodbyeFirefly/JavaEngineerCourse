/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.27 : Database - bill-manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bill-manager` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bill-manager`;

/*Table structure for table `bill_type_` */

DROP TABLE IF EXISTS `bill_type_`;

CREATE TABLE `bill_type_` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `bill_type_` */

insert  into `bill_type_`(`id_`,`name_`) values 
(1,'收入'),
(2,'支出'),
(3,'借入'),
(4,'借出');

/*Table structure for table `bill_` */

DROP TABLE IF EXISTS `bill_`;

CREATE TABLE `bill_` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `title_` varchar(100) DEFAULT NULL,
  `bill_time_` date DEFAULT NULL,
  `type_id_` bigint(20) DEFAULT NULL,
  `price_` double(10,2) DEFAULT NULL,
  `explain_` varchar(100) DEFAULT NULL,
  `file_path_` varchar(500) DEFAULT NULL,
  `file_name_` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  KEY `fk_type_bill` (`type_id_`),
  CONSTRAINT `fk_type_bill` FOREIGN KEY (`type_id_`) REFERENCES `bill_type_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `bill_` */

insert  into `bill_`(`id_`,`title_`,`bill_time_`,`type_id_`,`price_`,`explain_`,`file_path_`,`file_name_`) values 
(1,'交通费','2017-06-26',1,1.23,'a',NULL,NULL),
(2,'饭补','2017-06-27',1,2.23,'b',NULL,NULL),
(3,'出差费','2017-06-28',2,33.00,'c',NULL,NULL),
(4,'奖金','2017-06-29',2,44.00,'d',NULL,NULL),
(20,'1','2018-10-18',2,1.00,NULL,NULL,NULL),
(21,'采购办公用品','2019-05-15',2,1234.00,'1234','C:\\Users\\Administrator\\AppData\\Local\\Temp\\tomcat-docbase.6814089322458474891.8080\\account-20190506121712451-3.png','account.png');



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.28-log : Database - fund
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fund` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fund`;

/*Table structure for table `t_expect_fund` */

DROP TABLE IF EXISTS `t_expect_fund`;

CREATE TABLE `t_expect_fund` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fund_code` varchar(200) NOT NULL,
  `fund_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_expect_fund` */

insert  into `t_expect_fund`(`id`,`fund_code`,`fund_name`) values (1,'001618','天弘中证电子指数C'),(2,'001630','天弘中证计算机指数C'),(3,'001632','天弘中证食品饮料指数C'),(4,'100032','富国中证红利指数增强'),(5,'165312','建信央视50');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

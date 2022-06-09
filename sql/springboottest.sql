/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 8.0.23 : Database - springboottest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboottest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `springboottest`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
                              `department_id` int NOT NULL AUTO_INCREMENT COMMENT '科室/部门_id',
                              `department_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科室/部门名称',
                              `department_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '备注',
                              `parent_id` int NOT NULL COMMENT '上级id',
                              PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `department` */

insert  into `department`(`department_id`,`department_name`,`department_remark`,`parent_id`) values
(1,'医院','医院全部',0),
(2,'内科','内科',1),
(3,'外科','外科',1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `user_name` varchar(25) DEFAULT NULL COMMENT '用户名',
                        `user_sex` tinyint NOT NULL DEFAULT '0' COMMENT '性别（0-男性、1-女性）',
                        `ID_Card` varchar(18) NOT NULL COMMENT '身份证号',
                        `user_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
                        `user_phone` varchar(11) NOT NULL COMMENT '手机号',
                        `user_image` varchar(255) DEFAULT NULL COMMENT '头像图片',
                        `user_password` varchar(60) NOT NULL COMMENT '用户密码',
                        `user_group_id` int NOT NULL COMMENT '用户组id',
                        `department_id` int NOT NULL COMMENT '部门id',
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */


/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
                              `user_group_id` int NOT NULL AUTO_INCREMENT COMMENT '用户组ID',
                              `user_group_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户组名称',
                              `remark` varchar(50) DEFAULT NULL COMMENT '说明',
                              PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_group` */

insert  into `user_group`(`user_group_id`,`user_group_name`,`remark`) values
(1,'admin','拥有所有权限'),
(2,'科室负责人','拥有本科室的管理权限'),
(3,'普通医生','查看自己');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

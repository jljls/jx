-- --------------------------------------------------------
-- 主机:                           
-- 服务器版本:                        10.2.11-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 db_jx 的数据库结构
CREATE DATABASE IF NOT EXISTS `db_jx` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_jx`;

-- 导出  表 db_jx.emp_log 结构
DROP TABLE IF EXISTS `emp_log`;
CREATE TABLE IF NOT EXISTS `emp_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `type` varchar(20) NOT NULL COMMENT '操作类型',
  `log_content` varchar(2000) DEFAULT NULL COMMENT '操作内容',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- 正在导出表  db_jx.emp_log 的数据：~94 rows (大约)
/*!40000 ALTER TABLE `emp_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp_log` ENABLE KEYS */;

-- 导出  表 db_jx.schedule_task 结构
DROP TABLE IF EXISTS `schedule_task`;
CREATE TABLE IF NOT EXISTS `schedule_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` int(11) NOT NULL DEFAULT 0,
  `create_by` varchar(50) DEFAULT '0',
  `create_time` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- 正在导出表  db_jx.schedule_task 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `schedule_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_task` ENABLE KEYS */;

-- 导出  表 db_jx.t_employee 结构
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE IF NOT EXISTS `t_employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `group_id` varchar(20) DEFAULT NULL COMMENT '分组id',
  `user_id` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `id_px` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- 正在导出表  db_jx.t_employee 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `t_employee` DISABLE KEYS */;
INSERT INTO `t_employee` (`id`, `group_id`, `user_id`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(34, '14', 'admin', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `t_employee` ENABLE KEYS */;

-- 导出  表 db_jx.t_veinfeat 结构
DROP TABLE IF EXISTS `t_veinfeat`;
CREATE TABLE IF NOT EXISTS `t_veinfeat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '静脉id',
  `user_id` varchar(50) NOT NULL,
  `vein_feat` text NOT NULL COMMENT '用户静脉',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8 COMMENT='用户的静脉表';

-- 正在导出表  db_jx.t_veinfeat 的数据：~24 rows (大约)
/*!40000 ALTER TABLE `t_veinfeat` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_veinfeat` ENABLE KEYS */;

-- 导出  表 db_jx.user_info 结构
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 正在导出表  db_jx.user_info 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`id`, `user_id`, `name`, `password`) VALUES
	(1, 'admin', 'admin', 'jx123456');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

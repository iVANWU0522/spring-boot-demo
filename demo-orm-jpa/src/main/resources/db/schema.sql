DROP TABLE IF EXISTS `orm_user`;
CREATE TABLE `orm_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key',
  `name` VARCHAR(32) NOT NULL UNIQUE COMMENT 'user name',
  `password` VARCHAR(32) NOT NULL COMMENT 'encrypted password',
  `salt` VARCHAR(32) NOT NULL COMMENT 'salt',
  `email` VARCHAR(32) NOT NULL UNIQUE COMMENT 'email address',
  `phone_number` VARCHAR(15) NOT NULL UNIQUE COMMENT 'phone number',
  `status` INT(2) NOT NULL DEFAULT 1 COMMENT 'status，-1：deleted，0：disable，1：enable',
  `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT 'create time',
  `last_login_time` DATETIME DEFAULT NULL COMMENT 'last login time',
  `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT 'last update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo Orm Demo Table';

DROP TABLE IF EXISTS `orm_department`;
CREATE TABLE `orm_department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key',
  `name` VARCHAR(32) NOT NULL COMMENT 'department name',
  `superior` INT(11)  COMMENT 'superior id',
  `levels` INT(11) NOT NULL COMMENT 'levels',
  `order_no` INT(11) NOT NULL DEFAULT 0 COMMENT 'order number',
  `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT 'create time',
  `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT 'last update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo Orm Demo Table';

DROP TABLE IF EXISTS `orm_user_dept`;
CREATE TABLE `orm_user_dept` (
  `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'primary key',
  `user_id` INT(11) NOT NULL COMMENT 'user id',
  `dept_id` INT(11) NOT NULL COMMENT 'department id',
  `create_time` DATETIME NOT NULL DEFAULT NOW() COMMENT 'create time',
  `last_update_time` DATETIME NOT NULL DEFAULT NOW() COMMENT 'last update time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo Orm Demo Table';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Spring Boot Demo Orm JdbcTemplate User';
-- 班级信息
-- -----------------------------------------------------
-- Table `DRIP_CLASS` 班级信息
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_CLASS`;

CREATE  TABLE IF NOT EXISTS `DRIP_CLASS` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `CLASS_NAME` VARCHAR(10) NOT NULL COMMENT '班级名称',
  `CLASS_DESC` TEXT NULL COMMENT '班级描述',
  `CRT_TM` DATETIME NULL COMMENT '创建时间' ,
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人',
  `UPT_TM` DATETIME NULL COMMENT '最近一次修改时间',
  `UPT_USER_ID` BIGINT NULL COMMENT '最近一次修改人标识，存储全局用户标识',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '班级信息';
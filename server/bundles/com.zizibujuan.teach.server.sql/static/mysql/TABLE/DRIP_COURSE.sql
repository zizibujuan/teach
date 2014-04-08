-- 课程
-- -----------------------------------------------------
-- Table `DRIP_COURSE` 课程
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_COURSE`;

CREATE  TABLE IF NOT EXISTS `DRIP_COURSE` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `COURSE_NAME` VARCHAR(64) NOT NULL COMMENT '课程名称',
  `COURSE_DESC` TEXT NULL COMMENT '课程描述',
  `CRT_TM` DATETIME NULL COMMENT '创建时间' ,
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人/贡献者标识',
  `UPT_TM` DATETIME NULL COMMENT '最近一次修改时间',
  `UPT_USER_ID` BIGINT NULL COMMENT '最近一次修改人标识，存储全局用户标识',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '课程';
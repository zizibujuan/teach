-- 课程表基本信息
-- -----------------------------------------------------
-- Table `DRIP_CURRICULUM` 课程表基本信息
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_CURRICULUM`;

CREATE  TABLE IF NOT EXISTS `DRIP_CURRICULUM` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `COURSE_ID` BIGINT NOT NULL COMMENT '课程标识',
  `CLASS_ID` BIGINT NOT NULL COMMENT '班级标识',
  `CRT_TM` DATETIME NULL COMMENT '创建时间' ,
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人',
  `UPT_TM` DATETIME NULL COMMENT '最近一次修改时间',
  `UPT_USER_ID` BIGINT NULL COMMENT '最近一次修改人标识，存储全局用户标识',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '课程表基本信息';
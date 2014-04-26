-- 课时
-- -----------------------------------------------------
-- Table `DRIP_LESSON` 课时
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_LESSON`;

CREATE  TABLE IF NOT EXISTS `DRIP_LESSON` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `COURSE_ID` BIGINT NOT NULL COMMENT '课程标识',
  `TITLE` VARCHAR(64) NOT NULL COMMENT '标题',
  `CONTENT_PATH` VARCHAR(64) NULL COMMENT '课程内容路径，使用git管理内容信息',
  `CONTENT_TYPE` CHAR(1) NULL COMMENT '内容类型，如ppt',
  `CRT_TM` DATETIME NULL COMMENT '创建时间' ,
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人/贡献者标识',
  `UPT_TM` DATETIME NULL COMMENT '最近一次修改时间',
  `UPT_USER_ID` BIGINT NULL COMMENT '最近一次修改人标识，存储全局用户标识',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '课时';
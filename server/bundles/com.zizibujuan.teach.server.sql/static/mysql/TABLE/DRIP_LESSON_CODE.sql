-- 课时中使用到的源代码
-- -----------------------------------------------------
-- Table `DRIP_LESSON_CODE` 课时中使用到的源代码
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_LESSON_CODE`;

CREATE  TABLE IF NOT EXISTS `DRIP_LESSON_CODE` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `LESSON_ID` BIGINT NOT NULL COMMENT '课程标识',
  
  `TITLE` VARCHAR(64) NOT NULL COMMENT '标题，如使用章节编号描述等',
  `LANG` CHAR(3) NULL COMMENT '代码使用的语言',
  `CONTENT` TEXT NULL COMMENT '代码',
  `CRT_TM` DATETIME NULL COMMENT '创建时间' ,
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人/贡献者标识',
  `UPT_TM` DATETIME NULL COMMENT '最近一次修改时间',
  `UPT_USER_ID` BIGINT NULL COMMENT '最近一次修改人标识，存储全局用户标识',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '课时中使用到的源代码';
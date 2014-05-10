-- 课程表设计，这张表中存储的是以周为频率的信息
-- -----------------------------------------------------
-- Table `DRIP_CURRICULUM_WEEKLY` 课程表周频率计划
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_CURRICULUM_WEEKLY`;

CREATE  TABLE IF NOT EXISTS `DRIP_CURRICULUM_WEEKLY` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `START_DATE` DATE NOT NULL COMMENT '开始日期',
  `START_TIME` TIME NOT NULL COMMENT '开始时间',
  `END_TIME` TIME NOT NULL COMMENT '结束时间',
  `COURSE_ID` BIGINT NOT NULL COMMENT '课程标识',
  `CLASS_ID` BIGINT NOT NULL COMMENT '班级标识',
  `END_DATE` DATE NULL COMMENT '结束日期',
  `REPEAT_WEEK_COUNT` INT NULL COMMENT '课程重复周次',
  `REPEAT_DAYS` VARCHAR(64) NULL COMMENT '每周重复的日期，每天用数字表示，多个用逗号隔开',
  `CRT_TM` DATETIME NULL COMMENT '创建时间',
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人',
  `UPT_TM` DATETIME NULL COMMENT '最近一次修改时间',
  `UPT_USER_ID` BIGINT NULL COMMENT '最近一次修改人标识，存储全局用户标识',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '课程表周频率计划';
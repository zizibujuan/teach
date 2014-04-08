-- 每个课时所有参与人的活动记录，包括学生和老师
-- -----------------------------------------------------
-- Table `DRIP_LESSON_LOG` 每个课时所有参与人的活动记录
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_LESSON_LOG`;

CREATE  TABLE IF NOT EXISTS `DRIP_LESSON_LOG` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `LESSON_ID` BIGINT NOT NULL COMMENT '课程标识',
  `ACTIVITY_TYPE` CHAR(2) NULL COMMENT '活动编码',
  `ACTIVITY_TM` DATETIME NULL COMMENT '活动开始时间' ,
  `ACTIVITY_USER_ID` BIGINT NOT NULL COMMENT '活动人标识',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '每个课时所有参与人的活动记录';
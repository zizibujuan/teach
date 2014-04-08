-- 每个课时学生签到情况
-- -----------------------------------------------------
-- Table `DRIP_LESSON_SIGNIN` 每个课时学生签到情况
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_LESSON_SIGNIN`;

CREATE  TABLE IF NOT EXISTS `DRIP_LESSON_SIGNIN` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `LESSON_ID` BIGINT NULL COMMENT '课时标识',
  `SIGNIN_TM` DATETIME NULL COMMENT '签到时间' ,
  `STUDENT_ID` BIGINT NOT NULL COMMENT '学生标识',
  `UPT_TM` DATETIME NULL COMMENT '最近一次修改时间',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '每个课时学生签到情况';
-- 订阅课程的学生
-- -----------------------------------------------------
-- Table `DRIP_COURSE_STUDENT` 订阅课程的学生
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_COURSE_STUDENT`;

CREATE  TABLE IF NOT EXISTS `DRIP_COURSE_STUDENT` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `STUDENT_ID` BIGINT NOT NULL COMMENT '学生标识',
  `REGISTER_TM` DATETIME NULL COMMENT '订阅时间' ,
  `ALLOW` TINYINT NOT NULL DEFAULT 0 COMMENT '是否有权学习课程',
  `ALLOW_TM` DATETIME NULL COMMENT '允许学习课程的时间' ,
  `ALLOW_USER` BIGINT NULL COMMENT '允许学习课程的老师标识' ,
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '订阅课程的学生';
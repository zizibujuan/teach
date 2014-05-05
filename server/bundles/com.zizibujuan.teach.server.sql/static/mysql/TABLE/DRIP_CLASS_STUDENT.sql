-- 班级中的学生
-- -----------------------------------------------------
-- Table `DRIP_CLASS_STUDENT` 班级中的学生
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_CLASS_STUDENT`;

CREATE  TABLE IF NOT EXISTS `DRIP_CLASS_STUDENT` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `CLASS_ID` BIGINT NOT NULL COMMENT '班级标识',
  `STUDENT_ID` BIGINT NOT NULL COMMENT '学生标识',
  `CRT_TM` DATETIME NULL COMMENT '创建时间' ,
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '班级中的学生';
-- 班级中的成员
-- -----------------------------------------------------
-- Table `DRIP_CLASS_MEMBER` 班级中的成员
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_CLASS_MEMBER`;

CREATE  TABLE IF NOT EXISTS `DRIP_CLASS_MEMBER` (
  `DBID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `CLASS_ID` BIGINT NOT NULL COMMENT '班级标识',
  `MEMBER_TYPE` CHAR(2) NOT NULL COMMENT '成员类型，01为老师，02为学生',
  `USER_ID` BIGINT NOT NULL COMMENT '用户标识',
  `CRT_TM` DATETIME NULL COMMENT '创建时间' ,
  `CRT_USER_ID` BIGINT NOT NULL COMMENT '创建人',
  PRIMARY KEY (`DBID`))
ENGINE = InnoDB
COMMENT = '班级中的成员';
-- 课程表时间安排
-- -----------------------------------------------------
-- Table `DRIP_CURRICULUM_SCHEDULE` 课程表时间安排
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DRIP_CURRICULUM_SCHEDULE`;

CREATE  TABLE IF NOT EXISTS `DRIP_CURRICULUM_SCHEDULE` (
  `CURRICULUM_ID` BIGINT NOT NULL COMMENT '课程表标识',
  `LESSON_ID` BIGINT NULL COMMENT '课时标识',
  `START_TIME` DATETIME NOT NULL COMMENT '开始时间',
  `END_TIME` DATETIME NULL COMMENT '结束时间')
ENGINE = InnoDB
COMMENT = '课程表时间安排';
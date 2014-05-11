package com.zizibujuan.teach.server.dao;

import com.zizibujuan.teach.server.model.WeeklyRepeatEvent;

/**
 * 课程表管理数据访问接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface CurriculumDao {

	/**
	 * 添加课程表
	 * 
	 * @param userId 创建人标识
	 * @param repeats 以周为频率的排课计划
	 * @return 课程表标识
	 */
	Long add(Long userId, WeeklyRepeatEvent repeats);
}

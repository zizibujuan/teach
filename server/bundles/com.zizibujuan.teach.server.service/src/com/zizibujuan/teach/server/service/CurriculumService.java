package com.zizibujuan.teach.server.service;

import com.zizibujuan.teach.server.model.Curriculum;
import com.zizibujuan.teach.server.model.WeeklyRepeatEvent;

/**
 * 课程表管理服务接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface CurriculumService {

	/**
	 * 添加课程表
	 * 
	 * @param userId 创建人标识
	 * @param repeats 以周为频率的排课计划
	 * @return 课程表标识
	 */
	Long add(Long userId, WeeklyRepeatEvent repeats);

	/**
	 * 获取课程表详情
	 * 
	 * @param curriculumId 课程表标识
	 * @return 课程表详情
	 */
	Curriculum get(Long curriculumId);

}

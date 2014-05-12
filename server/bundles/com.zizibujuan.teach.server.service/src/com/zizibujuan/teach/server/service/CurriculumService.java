package com.zizibujuan.teach.server.service;

import com.zizibujuan.teach.server.model.Curriculum;

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
	 * @param curriculum 课程排课安排
	 * @return 课程排课标识
	 */
	Long add(Long userId, Curriculum curriculum);

	/**
	 * 获取课程表详情
	 * 
	 * @param curriculumId 课程表标识
	 * @return 课程表详情
	 */
	Curriculum get(Long curriculumId);

}

package com.zizibujuan.teach.server.dao;

import com.zizibujuan.teach.server.model.Curriculum;

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
	 * @param curriculum 课程排课安排
	 * @return 课程排课标识
	 */
	Long add(Long userId, Curriculum curriculum);
}

package com.zizibujuan.teach.server.dao;

import com.zizibujuan.teach.server.model.Lesson;

/**
 * 课时管理数据访问接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface LessonDao {

	/**
	 * 添加课时
	 * 
	 * @param userId 添加人标识
	 * @param lesson 课时基本信息
	 * @return 课时标识
	 */
	Long add(Long userId, Lesson lesson);

}

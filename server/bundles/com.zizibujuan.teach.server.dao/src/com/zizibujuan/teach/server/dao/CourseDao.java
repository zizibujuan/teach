package com.zizibujuan.teach.server.dao;

import com.zizibujuan.teach.server.model.Course;

/**
 * 课程管理数据访问接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface CourseDao {

	/**
	 * 用户添加课程
	 * @param userId 用户标识
	 * @param course 课程信息
	 * @return 课程标识
	 */
	Long add(Long userId, Course course);

}

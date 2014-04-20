package com.zizibujuan.teach.server.service;

import com.zizibujuan.teach.server.model.Course;

/**
 * 课程管理服务接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface CourseService {

	/**
	 * 用户添加课程
	 * @param userId 用户标识
	 * @param course 课程信息
	 * @return 课程标识
	 */
	Long add(Long userId, Course course);

}

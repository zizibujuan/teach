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

	/**
	 * 判断用户是否已使用这个课程名称
	 * 
	 * @param userId 用户标识
	 * @param courseName 课程名称
	 * @return 如果已使用则返回<code>true</code>;否则返回<code>false</code>
	 */
	boolean nameIsUsed(Long userId, String courseName);
	
	// 如果是更新，则把更新前的信息也传递过来

}

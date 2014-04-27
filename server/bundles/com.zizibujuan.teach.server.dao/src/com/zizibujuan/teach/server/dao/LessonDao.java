package com.zizibujuan.teach.server.dao;

import java.util.List;

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

	/**
	 * 判断课程下的课时名称是否已被使用
	 * 
	 * @param courseId 课程标识
	 * @param lessonName 课程名称
	 * @return 如果已使用则返回<code>true</code>;否则返回<code>false</code>
	 */
	boolean nameIsUsed(Long courseId, String lessonName);
	
	/**
	 * 查询课程下的所有课时
	 * 
	 * @param courseId 课程标识
	 * @return 课时列表，如果没有则返回空列表。
	 */
	List<Lesson> get(Long courseId);
}

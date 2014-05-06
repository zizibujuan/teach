package com.zizibujuan.teach.server.dao;

import com.zizibujuan.teach.server.model.ClassInfo;

/**
 * 班级管理数据访问接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface ClassDao {

	/**
	 * 添加班级，任何人都可以创建自己的班级
	 * 
	 * @param userId 添加人标识
	 * @param classInfo 班级信息
	 * @return 班级标识
	 */
	Long add(Long userId, ClassInfo classInfo);
	
	/**
	 * 往班级中添加学生
	 * 
	 * @param createUserId 创建人标识
	 * @param classId 班级标识
	 * @param studentId 学生标识
	 */
	void addStudent(Long createUserId, Long classId, Long studentId);
}

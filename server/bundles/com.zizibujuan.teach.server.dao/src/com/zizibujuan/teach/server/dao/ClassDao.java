package com.zizibujuan.teach.server.dao;

import com.zizibujuan.teach.server.model.ClassInfo;
import com.zizibujuan.teach.server.model.ClassMemberType;

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
	 * 往班级中添加成员
	 * 
	 * @param createUserId 创建人标识
	 * @param classId 班级标识
	 * @param userId 用户标识
	 * @param userType 用户类型， 参考{@link ClassMemberType}
	 */
	void addMember(Long createUserId, Long classId, Long userId, String userType);
}

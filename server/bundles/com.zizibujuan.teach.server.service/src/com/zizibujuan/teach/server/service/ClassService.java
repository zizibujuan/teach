package com.zizibujuan.teach.server.service;

import com.zizibujuan.teach.server.model.ClassInfo;

/**
 * 班级管理服务接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface ClassService {

	/**
	 * 添加班级，任何人都可以创建自己的班级
	 * 
	 * @param userId 添加人标识
	 * @param classInfo 班级信息
	 * @return 班级标识
	 */
	Long add(Long userId, ClassInfo classInfo);

}

package com.zizibujuan.teach.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.teach.server.dao.ClassDao;
import com.zizibujuan.teach.server.service.ClassService;

/**
 * 班级管理服务实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class ClassServiceImpl implements ClassService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);
	
	private ClassDao classDao;
	
	public void setClassDao(ClassDao classDao) {
		logger.info("注入ClassDao");
		this.classDao = classDao;
	}

	public void unsetClassDao(ClassDao classDao) {
		if (this.classDao == classDao) {
			logger.info("注销ClassDao");
			this.classDao = null;
		}
	}
}

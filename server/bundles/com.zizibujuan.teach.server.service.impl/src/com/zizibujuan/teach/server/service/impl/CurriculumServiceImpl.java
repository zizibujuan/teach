package com.zizibujuan.teach.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.teach.server.dao.CurriculumDao;
import com.zizibujuan.teach.server.service.CurriculumService;

/**
 * 课程表管理服务实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CurriculumServiceImpl implements CurriculumService {
	private static final Logger logger = LoggerFactory.getLogger(CurriculumServiceImpl.class);
	
	private CurriculumDao curriculumDao;
	
	public void setCurriculumDao(CurriculumDao curriculumDao) {
		logger.info("注入CurriculumDao");
		this.curriculumDao = curriculumDao;
	}

	public void unsetCurriculumDao(CurriculumDao curriculumDao) {
		if (this.curriculumDao == curriculumDao) {
			logger.info("注销CurriculumDao");
			this.curriculumDao = null;
		}
	}
}

package com.zizibujuan.teach.server.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.cm.server.service.ApplicationPropertyService;
import com.zizibujuan.teach.server.dao.CurriculumDao;
import com.zizibujuan.teach.server.model.Curriculum;
import com.zizibujuan.teach.server.service.CurriculumService;

/**
 * 课程表管理服务实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CurriculumServiceImpl implements CurriculumService {
	private static final Logger logger = LoggerFactory.getLogger(CurriculumServiceImpl.class);
	private static final String ALERT_LESSON_BEFORE_MINITES = "alert.minite.before.lesson";
	
	private CurriculumDao curriculumDao;
	private ApplicationPropertyService applicationPropertyService;
	
	@Override
	public Long add(Long userId, Curriculum curriculum) {
		return curriculumDao.add(userId, curriculum);
	}
	
	@Override
	public List<Map<String, Object>> getIncomingEvents(Long userId) {
		int beforeMinites = applicationPropertyService.getForInt(ALERT_LESSON_BEFORE_MINITES);
		return curriculumDao.getIncomingEvents(userId, beforeMinites);
	}

	@Override
	public Curriculum get(Long curriculumId) {
		// TODO Auto-generated method stub
		return null;
	}
	
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
	
	public void setApplicationPropertyService(ApplicationPropertyService applicationPropertyService) {
		logger.info("注入ApplicationPropertyService");
		this.applicationPropertyService = applicationPropertyService;
	}

	public void unsetApplicationPropertyService(ApplicationPropertyService applicationPropertyService) {
		if (this.applicationPropertyService == applicationPropertyService) {
			logger.info("注销ApplicationPropertyService");
			this.applicationPropertyService = null;
		}
	}

}

// 注意，课程表只是课程表，不能约束实际的上课安排，只能起到指导作用。
// 由一个班级和一个课程共同决定一门课的课程表
// 先判断课程是否已创建
//	如果课程没有创建
//	 则保存周频率的安排
//  并生成一个详细的课程表，保存
// 如果课程已创建
//  如果时间与之前的时间不冲突，则追加
//  如果要修改之前的课程安排，则只能修改未来的安排，不允许修改过去的安排。
// 如果不是按照周频率排课的，则不要weekly表中插入数据。
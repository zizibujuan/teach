package com.zizibujuan.teach.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.teach.server.dao.CurriculumDao;
import com.zizibujuan.teach.server.model.Curriculum;
import com.zizibujuan.teach.server.model.TimePeriod;
import com.zizibujuan.teach.server.model.WeeklyRepeatEvent;
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
	
	// 注意，课程表只是课程表，不能约束实际的上课安排，只能起到指导作用。
	@Override
	public Long add(Long userId, WeeklyRepeatEvent repeats) {
		// 由一个班级和一个课程共同决定一门课的课程表
		// 先判断课程是否已创建
		//	如果课程没有创建
		//	 则保存周频率的安排
		//  并生成一个详细的课程表，保存
		// 如果课程已创建
		//  如果时间与之前的时间不冲突，则追加
		//  如果要修改之前的课程安排，则只能修改未来的安排，不允许修改过去的安排。
		
		// 如果不是按照周频率排课的，则不要weekly表中插入数据。
		curriculumDao.add(userId, repeats);
		
		return null;
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
}

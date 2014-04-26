package com.zizibujuan.teach.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.teach.server.dao.LessonDao;
import com.zizibujuan.teach.server.model.Lesson;
import com.zizibujuan.teach.server.service.LessonService;

/**
 * 课时信息管理服务实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class LessonServiceImpl implements LessonService {
	private static final Logger logger = LoggerFactory.getLogger(LessonServiceImpl.class);
	private LessonDao lessonDao;
	
	@Override
	public Long add(Long userId, Lesson lesson) {
		return lessonDao.add(userId, lesson);
	}

	public void setLessonDao(LessonDao lessonDao) {
		logger.info("注入LessonDao");
		this.lessonDao = lessonDao;
	}

	public void unsetLessonDao(LessonDao lessonDao) {
		if (this.lessonDao == lessonDao) {
			logger.info("注销LessonDao");
			this.lessonDao = null;
		}
	}
}

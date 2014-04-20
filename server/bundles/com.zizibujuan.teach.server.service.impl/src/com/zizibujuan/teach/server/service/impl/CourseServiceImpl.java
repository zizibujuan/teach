package com.zizibujuan.teach.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.teach.server.dao.CourseDao;
import com.zizibujuan.teach.server.model.Course;
import com.zizibujuan.teach.server.service.CourseService;

/**
 * 课程管理服务实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CourseServiceImpl implements CourseService{
	
	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	private CourseDao courseDao;
	
	@Override
	public Long add(Long userId, Course course) {
		return courseDao.add(userId, course);
	}
	
	
	public void setCourseDao(CourseDao courseDao) {
		logger.info("注入CourseDao");
		this.courseDao = courseDao;
	}

	public void unsetCourseDao(CourseDao courseDao) {
		if (this.courseDao == courseDao) {
			logger.info("注销CourseDao");
			this.courseDao = null;
		}
	}

}

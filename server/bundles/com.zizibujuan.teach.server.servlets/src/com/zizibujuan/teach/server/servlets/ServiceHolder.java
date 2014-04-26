package com.zizibujuan.teach.server.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.teach.server.service.CourseService;
import com.zizibujuan.teach.server.service.LessonService;


/**
 * 服务对象容器
 * 
 * @author jzw
 * @since 0.0.1
 */
public class ServiceHolder {
	private static final Logger logger = LoggerFactory
			.getLogger(ServiceHolder.class);

	private static ServiceHolder singleton;

	public static ServiceHolder getDefault() {
		return singleton;
	}

	public void activate() {
		singleton = this;
	}

	public void deactivate() {
		singleton = null;
	}
	
	private CourseService courseService;

	public void setCourseService(CourseService courseService) {
		logger.info("注入CourseService");
		this.courseService = courseService;
	}

	public void unsetCourseService(CourseService courseService) {
		logger.info("注销CourseService");
		if (this.courseService == courseService) {
			this.courseService = null;
		}
	}
	public CourseService getCourseService() {
		return this.courseService;
	}

	private LessonService lessonService;

	public void setLessonService(LessonService lessonService) {
		logger.info("注入LessonService");
		this.lessonService = lessonService;
	}

	public void unsetLessonService(LessonService lessonService) {
		logger.info("注销LessonService");
		if (this.lessonService == lessonService) {
			this.lessonService = null;
		}
	}
	
	public LessonService getLessonService() {
		return lessonService;
	}
}

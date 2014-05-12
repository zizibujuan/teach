package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.HttpURLConnection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.server.test.servlet.AuthorizedUserServlet;
import com.zizibujuan.teach.server.servlets.RestResource;

/**
 * 课程表测试用例
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CurriculumServletTest extends AuthorizedUserServlet{

	@Before
	public void setUp(){
		super.setUp();
		loginTestUser();
	}
	
	@After
	public void tearDown(){
		// 清空测试环境
		clearTables(
			Tables.LESSON,
			Tables.COURSE, 
			Tables.CLASS,
			Tables.CURRICULUM
		);
		// 课程每周的时间安排
		// 排除的时间
		// 添加的时间
		
		logoutTestUser();
	}
	
	@Test
	public void testAddCurriculum(){
		// 添加课程
		formData.clear();
		formData.put("name", "course1");
		xhr.post("courses", formData);
		Map<String, Object> courseInfo = xhr.getContentAsJsonObject();
		Long courseId = Long.valueOf(courseInfo.get("id").toString());
		
		// 添加课时
		formData.clear();
		formData.put("name", "lesson1");
		xhr.post(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON, formData);
		Map<String, Object> lessonInfo = xhr.getContentAsJsonObject();
		Long lessonId = Long.valueOf(lessonInfo.get("id").toString());
		
		// 添加一个班级
		formData.clear();
		formData.put("name", "class1");
		xhr.post(RestResource.CLASS, formData);
		Map<String, Object> classInfo = xhr.getContentAsJsonObject();
		Long classId = Long.valueOf(classInfo.get("id").toString());
		
		formData.clear();
		formData.put("courseId", courseId);
		formData.put("lessonId", lessonId);
		formData.put("classId", classId);
		
		// 设置课程开始时间
		formData.put("startTime", "2014-01-01 10:00");
		formData.put("endTime", "2014-01-01 11:00");
		
		xhr.post(RestResource.CURRICULUM, formData);
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		assertNotNull(returnContent.get("id"));
	}
}

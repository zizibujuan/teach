package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.server.test.servlet.AuthorizedUserServlet;

/**
 * 课时管理测试用例
 * 
 * @author jzw
 * @since 0.0.1
 */
public class LessonServletTest extends AuthorizedUserServlet{

	private static final String URI = "lessons";
	private Long courseId;
	
	@Before
	public void setUp(){
		super.setUp();
		loginTestUser();
		// 创建课时
		createCourse();
	}
	
	@After
	public void tearDown(){
		// 清空测试环境
		clearTables(Tables.LESSON, Tables.COURSE);
		logoutTestUser();
	}
	
	public void createCourse(){
		formData.put("name", "课程1");
		formData.put("description", "课程描述1");
		
		xhr.post("courses", formData);
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		courseId = Long.valueOf(returnContent.get("id").toString());
	}
	
	@Test
	public void testAddLesson(){
		formData.clear();
		formData.put("name", "lesson1");
		
		xhr.post(courseId + "/" + URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		assertNotNull(returnContent.get("id"));
	}
}

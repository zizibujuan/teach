package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.drip.server.util.servlet.validate.ErrorMessage;
import com.zizibujuan.drip.server.util.servlet.validate.FieldError;
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
		
		formData.clear();
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
		formData.put("name", "lesson1");
		xhr.post("courses/" + courseId + "/" + URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		assertNotNull(returnContent.get("id"));
	}
	
	@Test
	public void testAddLessonNameCanNotBlank(){
		formData.put("name", "");
		xhr.post("courses/" + courseId + "/" + URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_PRECON_FAILED, xhr.getResponseCode());
		FieldError returnContent = xhr.getContentAsJsonObject(FieldError.class);
		assertEquals("field", returnContent.getType());
		ErrorMessage error = returnContent.getErrors().get(0);
		assertEquals("name", error.getField());
		assertEquals("名称不能为空", error.getMessage());
	}
	
	@Test
	public void testAddLessonNameMaxLength32(){
		formData.put("name", StringUtils.repeat("a", 33));
		xhr.post("courses/" + courseId + "/" + URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_PRECON_FAILED, xhr.getResponseCode());
		FieldError returnContent = xhr.getContentAsJsonObject(FieldError.class);
		assertEquals("field", returnContent.getType());
		ErrorMessage error = returnContent.getErrors().get(0);
		assertEquals("name", error.getField());
		assertEquals("名称最多32个字", error.getMessage());
	}
	
	@Test
	public void testAddLessonDuplicateName(){
		formData.put("name", "lesson1");
		xhr.post("courses/" + courseId + "/" + URI, formData);
		
		formData.clear();
		formData.put("name", "lesson1");
		xhr.post("courses/" + courseId + "/" + URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_PRECON_FAILED, xhr.getResponseCode());
		FieldError returnContent = xhr.getContentAsJsonObject(FieldError.class);
		assertEquals("field", returnContent.getType());
		ErrorMessage error = returnContent.getErrors().get(0);
		assertEquals("name", error.getField());
		assertEquals("名称已被使用", error.getMessage());
	}
	
	@Test
	public void testCheckLessonNameUnique(){
		formData.put("value", "lesson1");
		xhr.post("courses/" + courseId + "/" + URI + "/check-name", formData);
		assertEquals(HttpURLConnection.HTTP_OK, xhr.getResponseCode());
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		assertEquals("lesson1", returnContent.get("name"));
		
		formData.clear();
		formData.put("name", "lesson1");
		xhr.post("courses/" + courseId + "/" + URI, formData);
		
		formData.clear();
		formData.put("value", "lesson1");
		xhr.post("courses/" + courseId + "/" + URI + "/check-name", formData);
		assertEquals(HttpURLConnection.HTTP_FORBIDDEN, xhr.getResponseCode());
		returnContent = xhr.getContentAsJsonObject();
		assertEquals("名称已被使用", returnContent.get("message"));
	}
}

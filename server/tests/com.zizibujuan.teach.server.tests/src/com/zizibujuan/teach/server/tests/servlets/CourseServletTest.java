package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.HttpURLConnection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.drip.server.util.servlet.validate.ErrorMessage;
import com.zizibujuan.drip.server.util.servlet.validate.FieldError;
import com.zizibujuan.server.test.servlet.AbstractServletTest;

/**
 * 课程管理
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CourseServletTest extends AbstractServletTest{

	private static final String URI = "courses";
	
	@Before
	public void setUp(){
		super.setUp();
		loginTestUser();
	}
	
	@After
	public void tearDown(){
		// 清空测试环境
		clearTables(Tables.COURSE);
		logoutTestUser();
	}
	
	// TODO: 把通用的字段校验提取到单独的方法中，只测试一次
	@Test
	public void testAddCourse(){
		formData.put("name", "课程1");
		formData.put("description", "课程描述1");
		
		xhr.post(URI, formData);
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		assertNotNull(returnContent.get("id"));
	}
	
	@Test
	public void testAddCourseNameCanNotBlank(){
		formData.put("name", "");
		formData.put("description", "课程描述1");
		xhr.post(URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_PRECON_FAILED, xhr.getResponseCode());
		FieldError returnContent = xhr.getContentAsJsonObject(FieldError.class);
		assertEquals("field", returnContent.getType());
		ErrorMessage error = returnContent.getErrors().get(0);
		assertEquals("name", error.getField());
		assertEquals("课程名不能为空", error.getMessage());
	}
	
	@Test
	public void testAddCourseNameMaxLength32(){
		formData.put("name", StringUtils.repeat("a", 33));
		formData.put("description", "课程描述1");
		xhr.post(URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_PRECON_FAILED, xhr.getResponseCode());
		FieldError returnContent = xhr.getContentAsJsonObject(FieldError.class);
		assertEquals("field", returnContent.getType());
		ErrorMessage error = returnContent.getErrors().get(0);
		assertEquals("name", error.getField());
		assertEquals("课程名称最多32个字", error.getMessage());
	}
	
	@Test
	public void testAddCourseDuplicateName(){
		formData.put("name", "课程1");
		formData.put("description", "课程描述1");
		xhr.post(URI, formData);
		
		formData.clear();
		formData.put("name", "课程1");
		formData.put("description", "课程描述2");
		xhr.post(URI, formData);
		
		assertEquals(HttpURLConnection.HTTP_PRECON_FAILED, xhr.getResponseCode());
		FieldError returnContent = xhr.getContentAsJsonObject(FieldError.class);
		assertEquals("field", returnContent.getType());
		ErrorMessage error = returnContent.getErrors().get(0);
		assertEquals("name", error.getField());
		assertEquals("课程名已被使用", error.getMessage());
	}

	@Test
	public void testCheckNameUnique(){
		formData.put("value", "课程1");
		xhr.post("courses/check-name?owner=" + testUserId, formData);
		assertEquals(HttpURLConnection.HTTP_OK, xhr.getResponseCode());
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		assertEquals("课程1", returnContent.get("name"));
		
		formData.clear();
		formData.put("name", "课程1");
		formData.put("description", "课程描述1");
		xhr.post(URI, formData);
		
		formData.clear();
		formData.put("value", "课程1");
		xhr.post("courses/check-name?owner=" + testUserId, formData);
		assertEquals(HttpURLConnection.HTTP_FORBIDDEN, xhr.getResponseCode());
		returnContent = xhr.getContentAsJsonObject();
		assertEquals("课程名已被使用", returnContent.get("message"));
	}
}

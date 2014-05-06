package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.HttpURLConnection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.server.test.servlet.AuthorizedUserServlet;

/**
 * 班级管理测试用例
 * 
 * @author jzw
 * @since 0.0.1
 */
public class ClassServletTest extends AuthorizedUserServlet{

	@Before
	public void setUp(){
		super.setUp();
		loginTestUser();
	}
	
	@After
	public void tearDown(){
		// 清空测试环境
		clearTables(Tables.CLASS);
		logoutTestUser();
	}
	
	@Test
	public void testAddClass(){
		formData.put("name", "class1");
		formData.put("description", "班级1");
		
		xhr.post("classes", formData);
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		assertNotNull(returnContent.get("id"));
	}
	
	@Test
	public void testAddStudentForClass(){
		Long classId = null;
		Long userId1 = null;
		try{
			userId1 = super.createUser("a@a.com", "p_a", "n_a");
			
			formData.put("name", "class1");
			formData.put("description", "班级1");
			
			xhr.post("classes", formData);
			Map<String, Object> classInfo = xhr.getContentAsJsonObject();
			classId = Long.valueOf(classInfo.get("id").toString());
			
			formData.clear();
			formData.put("userId", userId1);
			xhr.post("classes/" + classId + "/users/", formData);
			assertEquals(HttpURLConnection.HTTP_NO_CONTENT, xhr.getResponseCode());
		}finally{
			if(userId1 != null){
				clearTables(Tables.CLASS_STUDENT);
				removeUser(userId1);
			}
		}
		
		
	}
}

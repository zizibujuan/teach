package com.zizibujuan.teach.server.tests.servlets;

import java.net.HttpURLConnection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.zizibujuan.server.test.servlet.AbstractServletTest;

/**
 * 课程管理
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CourseServletTest extends AbstractServletTest{

	private static final String URI = "courses";
	
	@Test
	public void testAddCourse(){
		
		try{
			
			loginTestUser();
			
			formData.put("name", "课程1");
			formData.put("description", "课程描述1");
			
			xhr.post(URI, formData);
			Assert.assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
			Map<String, Object> returnContent = xhr.getContentAsJsonObject();
			Assert.assertNotNull(returnContent.get("id"));
		}finally{
			// 清空测试环境
			clearTables(Tables.COURSE);
			logoutTestUser();
		}
		
		
	}

	
}

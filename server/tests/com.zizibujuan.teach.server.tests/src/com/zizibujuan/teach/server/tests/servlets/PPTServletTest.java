package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.cm.server.service.ApplicationPropertyService;
import com.zizibujuan.cm.server.servlets.CMServiceHolder;
import com.zizibujuan.server.git.GitUtils;
import com.zizibujuan.server.test.servlet.AuthorizedUserServlet;
import com.zizibujuan.teach.server.service.PPTService;
import com.zizibujuan.teach.server.servlets.RestResource;

/**
 * 演示文档管理测试用例
 * 
 * @author jzw
 * @since 0.0.1
 */
public class PPTServletTest extends AuthorizedUserServlet{

	private ApplicationPropertyService applicationPropertyService = CMServiceHolder.getDefault().getApplicationPropertyService();
	private Long courseId;
	private Long lessonId;
	private String gitRootPath;
	
	@Before
	public void setUp(){
		super.setUp();
		loginTestUser();
		
		// 创建课时
		createCourse();
		
		createLesson();
		
		formData.clear();
	}
	
	public void createCourse(){
		formData.put("name", "课程1");
		formData.put("description", "课程描述1");
		
		xhr.post(RestResource.COURSE, formData);
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		courseId = Long.valueOf(returnContent.get("id").toString());
	}
	
	public void createLesson(){
		formData.clear();
		formData.put("name", "lesson1");
		xhr.post(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON, formData);
		Map<String, Object> returnContent = xhr.getContentAsJsonObject();
		lessonId = Long.valueOf(returnContent.get("id").toString());
		gitRootPath = applicationPropertyService.getForString(PPTService.KEY_GIT_ROOT_COURSE);
	}
	
	@After
	public void tearDown(){
		// 清空测试环境
		clearTables(Tables.LESSON, Tables.COURSE);
		logoutTestUser();
		
		String gitRepoPath = gitRootPath + courseId;
		try {
			GitUtils.delete(gitRepoPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPPT(){
		formData.put("commitMessage", "commit1");
		formData.put("content", "content1");
		xhr.put(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON + "/" + lessonId + "/" + RestResource.PPT, formData);
		
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
		assertTrue(GitUtils.isGitRepo(gitRootPath + courseId));
	}
	
	@Test
	public void testEditPPT(){
		formData.put("commitMessage", "commit1");
		formData.put("content", "content1");
		xhr.put(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON +"/" + lessonId + "/" + RestResource.PPT, formData);
		
		formData.clear();
		formData.put("commitMessage", "commit2");
		formData.put("content", "content2");
		xhr.put(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON+ "/" + lessonId + "/" + RestResource.PPT, formData);
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
	}
	
	@Test
	public void testGetPPT(){
		formData.put("commitMessage", "commit1");
		formData.put("content", "content1");
		xhr.put(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON + "/" + lessonId + "/" + RestResource.PPT, formData);
		
		xhr.get(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON+ "/" + lessonId + "/" + RestResource.PPT);
		assertEquals(HttpURLConnection.HTTP_OK, xhr.getResponseCode());
		Map<String, Object> map = xhr.getContentAsJsonObject();
		assertEquals("content1", map.get("content").toString());
	}
}

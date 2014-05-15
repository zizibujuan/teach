package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.server.test.servlet.AuthorizedUserServlet;
import com.zizibujuan.teach.server.model.ClassMemberType;
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

	@Test
	public void testGetIncomingEvent(){
		// 添加三个事件，一个已经过去，一个即将发生，一个至少15分钟后发生。
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
		Map<String, Object> lessonInfo1 = xhr.getContentAsJsonObject();
		Long lessonId1 = Long.valueOf(lessonInfo1.get("id").toString());
		
		formData.clear();
		formData.put("name", "lesson2");
		xhr.post(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON, formData);
		Map<String, Object> lessonInfo2 = xhr.getContentAsJsonObject();
		Long lessonId2 = Long.valueOf(lessonInfo2.get("id").toString());
		
		formData.clear();
		formData.put("name", "lesson3");
		xhr.post(RestResource.COURSE + "/" + courseId + "/" + RestResource.LESSON, formData);
		Map<String, Object> lessonInfo3 = xhr.getContentAsJsonObject();
		Long lessonId3 = Long.valueOf(lessonInfo3.get("id").toString());
		
		// 添加一个班级
		formData.clear();
		formData.put("name", "class1");
		xhr.post(RestResource.CLASS, formData);
		Map<String, Object> classInfo = xhr.getContentAsJsonObject();
		Long classId = Long.valueOf(classInfo.get("id").toString());
		
		// 往班级中添加一个学生，当前登录人
		formData.clear();
		formData.put("userId", testUserId);
		xhr.post(RestResource.CLASS + "/" + classId + "/" + RestResource.STUDENT +"/", formData);
		assertEquals(HttpURLConnection.HTTP_NO_CONTENT, xhr.getResponseCode());
		
		// 设置课程开始时间
		Date startTime = DateUtils.addMinutes(new Date(), 10); // 还有10分钟开始
		Date endTime = DateUtils.addHours(new Date(), 1);
		
		formData.clear();
		formData.put("courseId", courseId);
		formData.put("lessonId", lessonId1);
		formData.put("classId", classId);
		formData.put("startTime", DateFormatUtils.format(DateUtils.addDays(startTime, -1), "yyyy-MM-dd HH:mm"));
		formData.put("endTime", DateFormatUtils.format(DateUtils.addDays(endTime, -1), "yyyy-MM-dd HH:mm"));
		xhr.post(RestResource.CURRICULUM, formData);
		
		formData.clear();
		formData.put("courseId", courseId);
		formData.put("lessonId", lessonId2);
		formData.put("classId", classId);
		formData.put("startTime", DateFormatUtils.format(startTime, "yyyy-MM-dd HH:mm"));
		formData.put("endTime", DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm"));
		xhr.post(RestResource.CURRICULUM, formData);
		
		formData.clear();
		formData.put("courseId", courseId);
		formData.put("lessonId", lessonId3);
		formData.put("classId", classId);
		formData.put("startTime", DateFormatUtils.format(DateUtils.addDays(startTime, 1), "yyyy-MM-dd HH:mm"));
		formData.put("endTime", DateFormatUtils.format(DateUtils.addDays(endTime, 1), "yyyy-MM-dd HH:mm"));
		xhr.post(RestResource.CURRICULUM, formData);
		
		formData.clear();
		formData.put("type", "incoming");
		xhr.get(RestResource.CURRICULUM, formData);
		assertEquals(HttpURLConnection.HTTP_OK, xhr.getResponseCode());
		List<Map<String, Object>> returnContent = xhr.getContentAsJsonArray();
		assertEquals(1, returnContent.size());
		assertEquals("lesson2", returnContent.get(0).get("lessonName"));
		assertEquals(ClassMemberType.STUDENT, returnContent.get(0).get("classMemberType"));
	}
	
	// 有两个人都有即将发生的事件，确保只查出登录用户的事件
}

package com.zizibujuan.teach.server.tests.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.server.test.servlet.AuthorizedUserServlet;
import com.zizibujuan.teach.server.model.Curriculum;
import com.zizibujuan.teach.server.model.TimePeriod;

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
			Tables.COURSE, 
			Tables.CLASS, 
			Tables.CURRICULUM_WEEKLY, 
			Tables.CURRICULUM_SCHEDULE, 
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
		
		// 添加一个班级
		formData.clear();
		formData.put("name", "class1");
		xhr.post("classes", formData);
		Map<String, Object> classInfo = xhr.getContentAsJsonObject();
		Long classId = Long.valueOf(classInfo.get("id").toString());
		
		formData.clear();
		formData.put("courseId", courseId);
		formData.put("classId", classId);
		// 设置课程开始时间
		formData.put("startTime", "2014-01-01 10:00");
		formData.put("endTime", "2014-01-01 11:00");
		formData.put("weeklyRepeat", true); // 是否按照每周重复的方式设置
		// 设置重复周次
		formData.put("repeatWeekCount", 3);
		// 设置课程结束时间,注意课程结束时间和重复周次只能设置一个
		formData.put("repeatEndDate", null);
		// 设置每周的开课时间安排
		formData.put("repeatDays", "0,1"); // 星期天和星期一
		
		xhr.post("curricula", formData);
		assertEquals(HttpURLConnection.HTTP_CREATED, xhr.getResponseCode());
		Curriculum returnContent = xhr.getContentAsJsonObject(Curriculum.class);
		assertNotNull(returnContent.getId());
		assertEquals("course1", returnContent.getCourseName());
		assertEquals("class1", returnContent.getClassName());
		assertEquals(1, returnContent.getSchedule().size());
		TimePeriod time1 = returnContent.getSchedule().get(0);
		try {
			assertTrue(DateUtils.isSameDay(DateUtils.parseDate("2014-01-01 10:00", "YYYY-MM-dd mm:hh"), time1.getStartTime()));
			assertTrue(DateUtils.isSameDay(DateUtils.parseDate("2014-01-01 11:00", "YYYY-MM-dd mm:hh"), time1.getEndTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

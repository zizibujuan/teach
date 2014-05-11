package com.zizibujuan.teach.server.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.zizibujuan.teach.server.tests.servlets.ClassServletTest;
import com.zizibujuan.teach.server.tests.servlets.CourseServletTest;
import com.zizibujuan.teach.server.tests.servlets.CurriculumServletTest;
import com.zizibujuan.teach.server.tests.servlets.LessonServletTest;
import com.zizibujuan.teach.server.tests.servlets.PPTServletTest;

@RunWith(Suite.class)
@SuiteClasses({
//	CourseServletTest.class,
//	LessonServletTest.class,
//	PPTServletTest.class,
//	ClassServletTest.class,
	CurriculumServletTest.class
})
public class AllServerTests {

}

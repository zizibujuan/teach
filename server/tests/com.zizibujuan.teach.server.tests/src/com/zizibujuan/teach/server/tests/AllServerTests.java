package com.zizibujuan.teach.server.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.zizibujuan.teach.server.tests.servlets.CourseServletTest;
import com.zizibujuan.teach.server.tests.servlets.LessonServletTest;

@RunWith(Suite.class)
@SuiteClasses({
	CourseServletTest.class,
	LessonServletTest.class
})
public class AllServerTests {

}

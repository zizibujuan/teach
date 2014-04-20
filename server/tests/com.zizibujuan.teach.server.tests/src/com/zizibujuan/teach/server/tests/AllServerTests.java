package com.zizibujuan.teach.server.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.zizibujuan.teach.server.tests.servlets.CourseServletTest;

@RunWith(Suite.class)
@SuiteClasses({
	CourseServletTest.class
})
public class AllServerTests {

}

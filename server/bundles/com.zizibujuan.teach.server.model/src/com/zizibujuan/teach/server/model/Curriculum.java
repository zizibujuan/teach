package com.zizibujuan.teach.server.model;

import java.util.List;

/**
 * 课程表
 * 
 * @author jzw
 * @since 0.0.1
 */
public class Curriculum {

	private Long id;
	private Long courseId;
	private Long classId;
	private String courseName;
	private String className;
	private List<TimePeriod> schedule;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<TimePeriod> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<TimePeriod> schedule) {
		this.schedule = schedule;
	}
}

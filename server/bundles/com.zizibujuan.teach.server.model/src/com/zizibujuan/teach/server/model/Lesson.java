package com.zizibujuan.teach.server.model;

/**
 * 课时信息
 * 
 * @author jzw
 * @since 0.0.1
 */
public class Lesson{

	private Long courseId;
	private String name;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name == null ? "" : name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

package com.zizibujuan.teach.server.model;


/**
 * 课程
 * 
 * @author jzw
 * @since 0.0.1
 */
public class Course{

	private Long id;
	private String name;
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取课程名称
	 * 
	 * @return 如果name为null，则返回空字符串，如果name前后有空字符串，则去除
	 */
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

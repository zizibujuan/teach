package com.zizibujuan.teach.server.model;

import java.util.Date;

/**
 * 每周重复事件
 * 
 * @author jzw
 * @since 0.0.1
 */
// TODO: 类名有待重构
public class WeeklyRepeatEvent {

	private Date startDate;
	private String startTime;
	private String endTime;
	private Long courseId;
	private Long classId;
	private boolean isWeeklyRepeat;
	private int repeatWeekCount;
	private Date endDate;
	private String repeatDays;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public boolean isWeeklyRepeat() {
		return isWeeklyRepeat;
	}
	public void setWeeklyRepeat(boolean isWeeklyRepeat) {
		this.isWeeklyRepeat = isWeeklyRepeat;
	}
	public int getRepeatWeekCount() {
		return repeatWeekCount;
	}
	public void setRepeatWeekCount(int repeatWeekCount) {
		this.repeatWeekCount = repeatWeekCount;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRepeatDays() {
		return repeatDays;
	}
	public void setRepeatDays(String repeatDays) {
		this.repeatDays = repeatDays;
	}
}

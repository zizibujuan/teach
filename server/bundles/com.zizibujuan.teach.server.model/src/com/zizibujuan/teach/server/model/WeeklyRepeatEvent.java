package com.zizibujuan.teach.server.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 每周重复事件
 * 
 * @author jzw
 * @since 0.0.1
 */
// TODO: 类名有待重构
public class WeeklyRepeatEvent {

	private Date startTime;
	private Date endTime;
	private Long courseId;
	private Long classId;
	private boolean isWeeklyRepeat;
	private int repeatWeekCount;
	private Date repeatEndDate; //值为日期+" 24:00"
	private String repeatDays;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
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
	public Date getRepeatEndDate() {
		return repeatEndDate;
	}
	public void setRepeatEndDate(Date repeatEndDate) {
		this.repeatEndDate = repeatEndDate;
	}
	public String getRepeatDays() {
		return repeatDays;
	}
	public void setRepeatDays(String repeatDays) {
		this.repeatDays = repeatDays;
	}
	
	public List<TimePeriod> calSchedule(){
		List<TimePeriod> result = new ArrayList<TimePeriod>();
		
		Calendar cStartTime = Calendar.getInstance();
		cStartTime.setTime(startTime);
		int startDayOfWeek = cStartTime.get(Calendar.DAY_OF_WEEK);
		int startHour = cStartTime.get(Calendar.HOUR_OF_DAY);
		int startMinite = cStartTime.get(Calendar.MINUTE);
		int startSecond = cStartTime.get(Calendar.SECOND);
		
		Calendar cEndTime = Calendar.getInstance();
		cEndTime.setTime(endTime);
		int endHour = cEndTime.get(Calendar.HOUR_OF_DAY);
		int endMinite = cEndTime.get(Calendar.MINUTE);
		int endSecond = cEndTime.get(Calendar.SECOND);
		
		String[] days = getRepeatDays().split(",");
		int day1 = Integer.valueOf(days[0]);
		Calendar s = Calendar.getInstance();
		s.setTime(cStartTime.getTime());
		int span = day1 - startDayOfWeek;
		s.add(Calendar.DAY_OF_YEAR, span);
		
		Calendar e = Calendar.getInstance();
		e.setTime(s.getTime());
		e.set(Calendar.HOUR_OF_DAY, endHour);
		e.set(Calendar.MINUTE, endMinite);
		e.set(Calendar.SECOND, endSecond);
		
		int weekCount = this.getRepeatWeekCount();
		for(int i = 0; i < weekCount; i++){
			TimePeriod t = new TimePeriod();
			t.setStartTime(s.getTime());
			t.setEndTime(e.getTime());
			result.add(t);
			
			s.add(Calendar.WEEK_OF_YEAR, 1);
			e.add(Calendar.WEEK_OF_YEAR, 1);
		}
		
		return result;
	}
}

package com.zizibujuan.teach.server.model;

import java.util.Date;

/**
 * 时间段
 * 
 * @author jzw
 * @since 0.0.1
 */
public class TimePeriod {

	private Date startTime;
	private Date endTime;
	
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
}

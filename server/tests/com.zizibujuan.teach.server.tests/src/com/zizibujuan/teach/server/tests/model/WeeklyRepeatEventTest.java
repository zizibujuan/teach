package com.zizibujuan.teach.server.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import com.zizibujuan.teach.server.model.TimePeriod;
import com.zizibujuan.teach.server.model.WeeklyRepeatEvent;

/**
 * 计算以周为频率的课程表安排
 * 
 * @author jzw
 * @since 0.0.1
 */
public class WeeklyRepeatEventTest {
	
	private WeeklyRepeatEvent repeats;
	
	@Before
	public void setup(){
		repeats = new WeeklyRepeatEvent();
	}

	@Test
	public void testRepeatTwoWeekOneDayOfWeek_before(){
		Calendar startTime = Calendar.getInstance();
		// 2014年1月1日是星期三
		startTime.set(2014, 0, 1, 9, 0, 0);
		
		Calendar endTime = Calendar.getInstance();
		endTime.set(2014, 0, 1, 10, 0, 0);
		
		repeats.setStartTime(startTime.getTime());
		repeats.setEndTime(endTime.getTime());
		repeats.setWeeklyRepeat(true);
		repeats.setRepeatWeekCount(2);
		repeats.setRepeatDays("" + Calendar.THURSDAY);
		
		List<TimePeriod> schedule = repeats.calSchedule();
		assertEquals(2, schedule.size());
		
		TimePeriod t1 = schedule.get(0);
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 0, 2, 9, 0, 0);
		assertTrue(DateUtils.isSameDay(calendar.getTime(), t1.getStartTime()));
	}
	
}

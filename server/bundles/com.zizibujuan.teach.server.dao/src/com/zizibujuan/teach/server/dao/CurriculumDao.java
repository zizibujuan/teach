package com.zizibujuan.teach.server.dao;

import java.util.List;
import java.util.Map;

import com.zizibujuan.teach.server.model.ClassMemberType;
import com.zizibujuan.teach.server.model.Curriculum;

/**
 * 课程表管理数据访问接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface CurriculumDao {

	/**
	 * 添加课程表
	 * 
	 * @param userId 创建人标识
	 * @param curriculum 课程排课安排
	 * @return 课程排课标识
	 */
	Long add(Long userId, Curriculum curriculum);

	/**
	 * 获取指定用户即将参与的课程，即老师即将开讲的课程和学生即将收听的课程。
	 * 
	 * @param userId 用户标识
	 * @param beforeMinites 提前多长时间通知，单位为分
	 * @return 将要做的事情列表
	 * <pre>
	 * map结构为：
	 * 	lessonId: 课时标识
	 * 	courseName: 课程名称
	 *  lessonName: 课时名称
	 *  classMemberType: 课堂成员类型，参考{@link ClassMemberType}
	 *  lessonStartTime: 某节课开始时间，取的是数据库时间
	 *  lessonEndTime: 某节课结束时间，取的是数据库时间
	 *  currentTime: 当前时间，取得是数据库时间
	 * </pre>
	 */
	List<Map<String, Object>> getIncomingEvents(Long userId, int beforeMinites);
}

package com.zizibujuan.teach.server.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.zizibujuan.drip.server.util.dao.AbstractDao;
import com.zizibujuan.drip.server.util.dao.DatabaseUtil;
import com.zizibujuan.drip.server.util.dao.PreparedStatementSetter;
import com.zizibujuan.teach.server.dao.CurriculumDao;
import com.zizibujuan.teach.server.model.Curriculum;

/**
 * 课程表管理数据访问实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CurriculumDaoImpl extends AbstractDao implements CurriculumDao {


	private static final String SQL_INSERT_CURRICULUM = "INSERT INTO "
			+ "DRIP_CURRICULUM "
			+ "(COURSE_ID,"
			+ "LESSON_ID, "
			+ "CLASS_ID,"
			+ "START_TIME,"
			+ "END_TIME,"
			+ "CRT_USER_ID,"
			+ "CRT_TM) "
			+ "VALUES "
			+ "(?,?,?,?,?,?,now())";
	@Override
	public Long add(Long userId, Curriculum curriculum) {
		return DatabaseUtil.insert(getDataSource(), SQL_INSERT_CURRICULUM, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, curriculum.getCourseId());
				ps.setLong(2, curriculum.getLessonId());
				ps.setLong(3, curriculum.getClassId());
				ps.setTimestamp(4, new Timestamp(curriculum.getStartTime().getTime()));
				ps.setTimestamp(5, new Timestamp(curriculum.getEndTime().getTime()));
				ps.setLong(6, userId);
			}
		});
	}
	
	private static final String SQL_LIST_INCOMING_EVENT = "SELECT "
			+ "CM.CLASS_ID, "
			+ "CM.MEMBER_TYPE \"classMemberType\", "
			+ "C.START_TIME \"lessonStartTime\","
			+ "C.END_TIME \"lessonEndTime\","
			+ "now() \"currentTime\","
			+ "C.LESSON_ID \"lessonId\","
			+ "L.TITLE \"lessonName\","
			+ "CA.COURSE_NAME \"courseName\" "
			+ "FROM "
			+ "DRIP_CLASS_MEMBER CM,"
			+ "DRIP_CURRICULUM C,"
			+ "DRIP_LESSON L,"
			+ "DRIP_COURSE CA "
			+ "WHERE "
			+ "CM.USER_ID = ? AND "
			+ "CM.CLASS_ID = C.CLASS_ID AND "
			+ "(DATE_ADD(C.START_TIME, INTERVAL -? MINUTE) <= now() AND now() <= C.END_TIME) AND "
			+ "C.LESSON_ID = L.DBID AND "
			+ "L.COURSE_ID = CA.DBID";
	@Override
	public List<Map<String, Object>> getIncomingEvents(Long userId,
			int beforeMinites) {
		return DatabaseUtil.queryForList(
			getDataSource(), 
			SQL_LIST_INCOMING_EVENT, 
			userId, 
			beforeMinites
		);
	}
}

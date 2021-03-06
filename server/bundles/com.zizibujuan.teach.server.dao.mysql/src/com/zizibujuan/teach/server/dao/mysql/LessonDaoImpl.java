package com.zizibujuan.teach.server.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zizibujuan.drip.server.util.PageInfo;
import com.zizibujuan.drip.server.util.dao.AbstractDao;
import com.zizibujuan.drip.server.util.dao.DatabaseUtil;
import com.zizibujuan.drip.server.util.dao.PreparedStatementSetter;
import com.zizibujuan.drip.server.util.dao.RowMapper;
import com.zizibujuan.teach.server.dao.LessonDao;
import com.zizibujuan.teach.server.model.Lesson;

public class LessonDaoImpl extends AbstractDao implements LessonDao {

	private static final String SQL_INSERT_LESSON = "INSERT INTO "
			+ "DRIP_LESSON "
			+ "(COURSE_ID, "
			+ "TITLE,"
			+ "CRT_TM,"
			+ "CRT_USER_ID) "
			+ "VALUES "
			+ "(?, ?, now(), ?)";
	@Override
	public Long add(Long userId, Lesson lesson) {
		return DatabaseUtil.insert(getDataSource(), SQL_INSERT_LESSON, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, lesson.getCourseId());
				ps.setString(2, lesson.getName());
				ps.setLong(3, userId);
			}
			
		});
	}
	
	private static final String SQL_CHECK_LESSON_NAME = "SELECT "
			+ "1 "
			+ "FROM "
			+ "DRIP_LESSON "
			+ "WHERE "
			+ "COURSE_ID=? AND "
			+ "TITLE=? "
			+ "LIMIT 1";
	@Override
	public boolean nameIsUsed(Long courseId, String lessonName) {
		int result = DatabaseUtil.queryForInt(getDataSource(), SQL_CHECK_LESSON_NAME, courseId, lessonName);
		return result == 1;
	}
	
	private static final String SQL_LIST_LESSON = "SELECT "
			+ "DBID, "
			+ "TITLE "
			+ "FROM "
			+ "DRIP_LESSON "
			+ "WHERE "
			+ "COURSE_ID=? "
			+ "ORDER BY SEQ";
	@Override
	public List<Lesson> get(Long courseId, PageInfo pageInfo) {
		return DatabaseUtil.query(getDataSource(), SQL_LIST_LESSON, new RowMapper<Lesson>() {
			@Override
			public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
				Lesson lesson = new Lesson();
				lesson.setId(rs.getLong(1));
				lesson.setName(rs.getString(2));
				return lesson;
			}
		}, pageInfo, courseId);
	}

}

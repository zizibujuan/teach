package com.zizibujuan.teach.server.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zizibujuan.drip.server.util.dao.AbstractDao;
import com.zizibujuan.drip.server.util.dao.DatabaseUtil;
import com.zizibujuan.drip.server.util.dao.PreparedStatementSetter;
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

}

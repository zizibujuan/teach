package com.zizibujuan.teach.server.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zizibujuan.drip.server.util.dao.AbstractDao;
import com.zizibujuan.drip.server.util.dao.DatabaseUtil;
import com.zizibujuan.drip.server.util.dao.PreparedStatementSetter;
import com.zizibujuan.teach.server.dao.CourseDao;
import com.zizibujuan.teach.server.model.Course;

/**
 * 课程管理数据访问实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CourseDaoImpl extends AbstractDao implements CourseDao{

	private static final String SQL_INSERT_COURSE = "INSERT INTO "
			+ "DRIP_COURSE "
			+ "(COURSE_NAME, "
			+ "COURSE_DESC, "
			+ "CRT_TM, "
			+ "CRT_USER_ID) "
			+ "VALUES "
			+ "(?, ?, now(), ?)";
	@Override
	public Long add(final Long userId, final Course course) {
		return DatabaseUtil.insert(getDataSource(), SQL_INSERT_COURSE, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, course.getName());
				ps.setString(2, course.getDescription());
				ps.setLong(3, userId);
			}
			
		});
	}

}

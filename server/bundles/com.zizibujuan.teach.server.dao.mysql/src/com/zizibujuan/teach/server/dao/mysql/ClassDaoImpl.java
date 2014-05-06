package com.zizibujuan.teach.server.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zizibujuan.drip.server.util.dao.AbstractDao;
import com.zizibujuan.drip.server.util.dao.DatabaseUtil;
import com.zizibujuan.drip.server.util.dao.PreparedStatementSetter;
import com.zizibujuan.teach.server.dao.ClassDao;
import com.zizibujuan.teach.server.model.ClassInfo;

/**
 * 班级管理数据访问实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class ClassDaoImpl extends AbstractDao implements ClassDao {

	private static final String SQL_INSERT_CLASS = "INSERT INTO "
			+ "DRIP_CLASS "
			+ "(CLASS_NAME, "
			+ "CLASS_DESC, "
			+ "CRT_TM, "
			+ "CRT_USER_ID) "
			+ "VALUES "
			+ "(?, ?, now(), ?)";
	@Override
	public Long add(Long userId, ClassInfo classInfo) {
		return DatabaseUtil.insert(getDataSource(), SQL_INSERT_CLASS, new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, classInfo.getName());
				ps.setString(2, classInfo.getDescription());
				ps.setLong(3, userId);
			}
			
		});
	}
	
	private static final String SQL_INSERT_STUDENT = "INSERT INTO "
			+ "DRIP_CLASS_STUDENT "
			+ "(CLASS_ID,"
			+ "STUDENT_ID,"
			+ "CRT_USER_ID,"
			+ "CRT_TM) "
			+ "VALUES "
			+ "(?, ?, ?, now())";
	@Override
	public void addStudent(Long createUserId, Long classId, Long studentId) {
		DatabaseUtil.insert(getDataSource(), SQL_INSERT_STUDENT, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, classId);
				ps.setLong(2, studentId);
				ps.setLong(3, createUserId);
			}
		});
	}

}

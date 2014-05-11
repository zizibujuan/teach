package com.zizibujuan.teach.server.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zizibujuan.drip.server.util.dao.AbstractDao;
import com.zizibujuan.drip.server.util.dao.BatchPreparedStatementSetter;
import com.zizibujuan.drip.server.util.dao.DatabaseUtil;
import com.zizibujuan.drip.server.util.dao.PreparedStatementSetter;
import com.zizibujuan.teach.server.dao.CurriculumDao;
import com.zizibujuan.teach.server.model.TimePeriod;
import com.zizibujuan.teach.server.model.WeeklyRepeatEvent;

/**
 * 课程表管理数据访问实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CurriculumDaoImpl extends AbstractDao implements CurriculumDao {

	@Override
	public Long add(Long userId, WeeklyRepeatEvent repeats) {
		Connection con = null;
		Long curriculumId = null;
		try {
			con = getDataSource().getConnection();
			con.setAutoCommit(false);
			
			insertWeeklyRepeatEvent(con, userId, repeats);
			curriculumId = insertCurriculum(con, userId, repeats);
			batchInsertTimePlan(con, curriculumId, repeats);
			
			con.commit();
		} catch (SQLException e) {
			DatabaseUtil.safeRollback(con);
			e.printStackTrace();
		} finally{
			DatabaseUtil.closeConnection(con);
		}
		return curriculumId;
	}
	
	private static final String SQL_INSERT_CURRICULUM_SCHEDULE = "INSERT INTO "
			+ "DRIP_CURRICULUM_SCHEDULE "
			+ "(CURRICULUM_ID,"
			+ "START_TIME,"
			+ "END_TIME) "
			+ "VALUES "
			+ "(?, ?, ?)";
	private void batchInsertTimePlan(Connection con, Long curriculumId, WeeklyRepeatEvent repeats) throws SQLException {
		List<TimePeriod> list = new ArrayList<TimePeriod>();
		String[] repeatDays = repeats.getRepeatDays().split(",");
		if(repeats.getRepeatEndDate() != null){
			
		}else{
			int weekCount = repeats.getRepeatWeekCount();
			Date startTime = repeats.getStartTime();
			
			for(int i = 0; i < weekCount; i++){
				TimePeriod t = new TimePeriod();
				
				list.add(t);
			}
		}
		
		DatabaseUtil.batchUpdate(con, SQL_INSERT_CURRICULUM_SCHEDULE, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				TimePeriod t = list.get(index);
				ps.setLong(1, curriculumId);
				ps.setTimestamp(2, new Timestamp(t.getStartTime().getTime()));
				ps.setTimestamp(3, new Timestamp(t.getEndTime().getTime()));
			}
			
			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
		
	}

	private static final String SQL_INSERT_CURRICULUM = "INSERT INTO "
			+ "DRIP_CURRICULUM "
			+ "(COURSE_ID, "
			+ "CLASS_ID,"
			+ "CRT_USER_ID,"
			+ "CRT_TM) "
			+ "VALUES "
			+ "(?,?,?,now())";
	private Long insertCurriculum(Connection con, Long userId, WeeklyRepeatEvent repeats) throws SQLException {
		return DatabaseUtil.insert(con, SQL_INSERT_CURRICULUM, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, repeats.getCourseId());
				ps.setLong(2, repeats.getClassId());
				ps.setLong(3, userId);
			}
		});
	}
	
	private static final String SQL_INSERT_CURRICULUM_WEEKLY = "INSERT INTO "
			+ "DRIP_CURRICULUM_WEEKLY "
			+ "(START_TIME,"
			+ "END_TIME,"
			+ "COURSE_ID,"
			+ "CLASS_ID,"
			+ "REPEAT_END_DATE,"
			+ "REPEAT_WEEK_COUNT,"
			+ "REPEAT_DAYS,"
			+ "CRT_USER_ID,"
			+ "CRT_TM) "
			+ "VALUES "
			+ "(?,?,?,?,?,?,?,?,now())";
	private void insertWeeklyRepeatEvent(Connection con, Long userId,
			WeeklyRepeatEvent repeats) throws SQLException {
		DatabaseUtil.insert(con, SQL_INSERT_CURRICULUM_WEEKLY, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setTimestamp(1, new Timestamp(repeats.getStartTime().getTime()));
				ps.setTimestamp(2, new Timestamp(repeats.getEndTime().getTime()));
				ps.setLong(3, repeats.getCourseId());
				ps.setLong(4, repeats.getClassId());
				if(repeats.getRepeatEndDate() == null){
					ps.setNull(5, Types.TIMESTAMP);
				}else{
					ps.setTimestamp(5, new Timestamp(repeats.getRepeatEndDate().getTime()));
				}
				ps.setInt(6, repeats.getRepeatWeekCount()); // 默认为0
				ps.setString(7, repeats.getRepeatDays());
				ps.setLong(8, userId);
			}
		});
	}

}

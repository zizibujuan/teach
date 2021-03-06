package com.zizibujuan.teach.server.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IPath;

import com.zizibujuan.drip.server.util.servlet.BaseServlet;
import com.zizibujuan.drip.server.util.servlet.RequestUtil;
import com.zizibujuan.drip.server.util.servlet.ResponseUtil;
import com.zizibujuan.drip.server.util.servlet.UserSession;
import com.zizibujuan.teach.server.model.ClassInfo;
import com.zizibujuan.teach.server.service.ClassService;
import com.zizibujuan.useradmin.server.model.UserInfo;

/**
 * 班级管理
 * 
 * @author jzw
 * @since 0.0.1
 */
public class ClassServlet extends BaseServlet{

	private static final long serialVersionUID = 3160811637103016539L;
	
	private ClassService classService;
	
	public ClassServlet() {
		this.classService = ServiceHolder.getDefault().getClassService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		traceRequest(req);
		IPath path = getPath(req);
		if(path.segmentCount() == 0){
			Long userId = ((UserInfo)UserSession.getUser(req)).getId();
			ClassInfo classInfo = RequestUtil.fromJsonObject(req, ClassInfo.class);
			Long classId = classService.add(userId, classInfo);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", classId);
			ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
			return;
		}
		
		if(path.segmentCount() == 2){
			String resName = path.segment(1);
			// TODO: 把资源名提取到常量类中
			if(resName.equals(RestResource.STUDENT)){
				Long createUserId = ((UserInfo)UserSession.getUser(req)).getId();
				Long classId = Long.valueOf(path.segment(0));
				Map<String, Object> student = RequestUtil.fromJsonObject(req);
				Long studentId = Long.valueOf(student.get("userId").toString());
				classService.addStudent(createUserId, classId, studentId);
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return;
			}
			
			if(resName.equals(RestResource.TEACHER)){
				Long createUserId = ((UserInfo)UserSession.getUser(req)).getId();
				Long classId = Long.valueOf(path.segment(0));
				Map<String, Object> teacher = RequestUtil.fromJsonObject(req);
				Long teacherId = Long.valueOf(teacher.get("userId").toString());
				classService.addTeacher(createUserId, classId, teacherId);
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return;
			}
			
		}
		
		super.doPost(req, resp);
	}

	
}




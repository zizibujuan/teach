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
			
			
			Long classId = 0l;
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", classId);
			ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
			return;
		}
		
		super.doPost(req, resp);
	}

	
}




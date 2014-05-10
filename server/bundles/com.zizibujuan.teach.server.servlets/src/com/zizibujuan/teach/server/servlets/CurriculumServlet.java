package com.zizibujuan.teach.server.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IPath;

import com.zizibujuan.drip.server.util.servlet.BaseServlet;
import com.zizibujuan.drip.server.util.servlet.RequestUtil;
import com.zizibujuan.drip.server.util.servlet.UserSession;
import com.zizibujuan.teach.server.model.WeeklyRepeatEvent;
import com.zizibujuan.teach.server.service.CurriculumService;
import com.zizibujuan.useradmin.server.model.UserInfo;

/**
 * 课程表管理
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CurriculumServlet extends BaseServlet{

	private static final long serialVersionUID = -5162142111436319681L;
	
	private CurriculumService curriculumService;
	
	public CurriculumServlet() {
		curriculumService = ServiceHolder.getDefault().getCurriculumService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		traceRequest(req);
		IPath path = getPath(req);
		if(path.segmentCount() == 0){
			Long userId = ((UserInfo)UserSession.getUser(req)).getId();
			WeeklyRepeatEvent repeats = RequestUtil.fromJsonObject(req, WeeklyRepeatEvent.class);
			
			return;
		}
		
		super.doPost(req, resp);
	}

	
}

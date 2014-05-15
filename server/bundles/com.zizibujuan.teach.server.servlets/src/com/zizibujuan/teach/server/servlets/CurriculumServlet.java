package com.zizibujuan.teach.server.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IPath;

import com.zizibujuan.drip.server.util.servlet.BaseServlet;
import com.zizibujuan.drip.server.util.servlet.RequestUtil;
import com.zizibujuan.drip.server.util.servlet.ResponseUtil;
import com.zizibujuan.drip.server.util.servlet.UserSession;
import com.zizibujuan.teach.server.model.Curriculum;
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
			Curriculum curriculum = RequestUtil.fromJsonObject(req, Curriculum.class);
			Long curriculumId = curriculumService.add(userId, curriculum);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", curriculumId);
			ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
			return;
		}
		
		super.doPost(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		traceRequest(req);
		IPath path = getPath(req);
		if(path.segmentCount() == 0){
			String type = req.getParameter("type");
			if(type != null && type.equals("incoming")){
				Long userId = ((UserInfo)UserSession.getUser(req)).getId();
				List<Map<String, Object>> result = curriculumService.getIncomingEvents(userId);
				ResponseUtil.toJSON(req, resp, result);
				return;
			}
		}
		super.doGet(req, resp);
	}
	
}

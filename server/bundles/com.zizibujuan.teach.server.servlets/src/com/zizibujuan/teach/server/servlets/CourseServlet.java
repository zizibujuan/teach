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
import com.zizibujuan.teach.server.model.Course;
import com.zizibujuan.teach.server.service.CourseService;
import com.zizibujuan.useradmin.server.model.UserInfo;

/**
 * 课程管理servlet
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CourseServlet extends BaseServlet{

	private static final long serialVersionUID = 5459471541414091792L;

	private CourseService courseService;
	
	public CourseServlet(){
		courseService = ServiceHolder.getDefault().getCourseService();
	}
	
	/**
	 * 一个人创建的课程名称不能重复
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		traceRequest(req);
		IPath path = getPath(req);
		if(path.segmentCount() == 0){
			Course course = RequestUtil.fromJsonObject(req, Course.class);
			Long userId = ((UserInfo)UserSession.getUser(req)).getId();
			Long courseId = courseService.add(userId, course);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", courseId);
			ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
			return;
		}
		super.doPost(req, resp);
	}

	
}

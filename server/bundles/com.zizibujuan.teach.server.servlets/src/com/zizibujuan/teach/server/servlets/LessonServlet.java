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
import com.zizibujuan.teach.server.model.Lesson;
import com.zizibujuan.teach.server.service.LessonService;
import com.zizibujuan.useradmin.server.model.UserInfo;

/**
 * 课时管理
 * 
 * @author jzw
 * @since 0.0.1
 */
public class LessonServlet extends BaseServlet {

	private static final long serialVersionUID = 5390084435254043742L;

	private LessonService lessonService;
	public LessonServlet(){
		lessonService = ServiceHolder.getDefault().getLessonService();
	}
	
	/**
	 * lessons/{courseId}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		traceRequest(req);
		IPath path = getPath(req);
		if(path.segmentCount() == 1){
			Long courseId = Long.valueOf(path.segment(0));
			Long userId = ((UserInfo)UserSession.getUser(req)).getId();
			Lesson lesson = RequestUtil.fromJsonObject(req, Lesson.class);
			lesson.setCourseId(courseId);
			Long lessonId = lessonService.add(userId, lesson);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", lessonId);
			ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
			return;
		}
		super.doPost(req, resp);
	}

	
}

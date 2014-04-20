package com.zizibujuan.teach.server.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IPath;

import com.zizibujuan.drip.server.util.servlet.BaseServlet;
import com.zizibujuan.drip.server.util.servlet.ResponseUtil;

/**
 * 课程管理servlet
 * 
 * @author jzw
 * @since 0.0.1
 */
public class CourseServlet extends BaseServlet{

	private static final long serialVersionUID = 5459471541414091792L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		traceRequest(req);
		IPath path = getPath(req);
		if(path.segmentCount() == 0){
			Map<String, Object> result = new HashMap<>();
			result.put("id", 1);
			
			ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
			return;
		}
		super.doPost(req, resp);
	}

	
}

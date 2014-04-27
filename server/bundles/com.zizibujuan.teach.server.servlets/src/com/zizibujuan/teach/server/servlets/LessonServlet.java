package com.zizibujuan.teach.server.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IPath;

import com.zizibujuan.drip.server.util.servlet.BaseServlet;

/**
 * 课时管理， 课时作为课程的嵌套资源，将处理逻辑移到CourseServlet中完成。
 * 
 * @author jzw
 * @since 0.0.1
 */
public class LessonServlet extends BaseServlet {

	private static final long serialVersionUID = 5390084435254043742L;

	
	public LessonServlet(){
		
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
			
		}else if(path.segmentCount() == 2){
			
		}
		super.doPost(req, resp);
	}

	
}

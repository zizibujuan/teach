package com.zizibujuan.teach.server.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IPath;

import com.zizibujuan.drip.server.util.PageInfo;
import com.zizibujuan.drip.server.util.servlet.BaseServlet;
import com.zizibujuan.drip.server.util.servlet.RequestUtil;
import com.zizibujuan.drip.server.util.servlet.ResponseUtil;
import com.zizibujuan.drip.server.util.servlet.UserSession;
import com.zizibujuan.drip.server.util.servlet.validate.Validator;
import com.zizibujuan.teach.server.model.Course;
import com.zizibujuan.teach.server.model.Lesson;
import com.zizibujuan.teach.server.service.CourseService;
import com.zizibujuan.teach.server.service.LessonService;
import com.zizibujuan.teach.server.service.PPTService;
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
	private LessonService lessonService;
	private PPTService pptService;
	
	public CourseServlet(){
		courseService = ServiceHolder.getDefault().getCourseService();
		lessonService = ServiceHolder.getDefault().getLessonService();
		pptService = ServiceHolder.getDefault().getPPTService();
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
			
			// 校验用户名是否被使用
			Validator validator = new Validator();
			String name = course.getName();
			if(name.isEmpty()){
				validator.addFieldError("name", "课程名不能为空"); // TODO: 国际化
			}else if(name.length() > 32){
				validator.addFieldError("name", "课程名称最多32个字");
			}else if(courseService.nameIsUsed(userId, name)){
				validator.addFieldError("name", "课程名已被使用");
			}
			if(validator.hasErrors()){
				ResponseUtil.toJSON(req, resp, validator.getFieldErrors(), HttpServletResponse.SC_PRECONDITION_FAILED);
				return;
			}
			
			Long courseId = courseService.add(userId, course);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", courseId);
			ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
			return;
		}else if(path.segmentCount() == 1){
			String verb = path.segment(0);
			if(verb.equals("check-name")){
				Long userId = ((UserInfo)UserSession.getUser(req)).getId();
				Map<String, Object> map = RequestUtil.fromJsonObject(req);
				String name = map.get("value").toString();
				if(courseService.nameIsUsed(userId, name)){
					Map<String, String> result = new HashMap<>();
					result.put("message", "课程名已被使用");
					ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_FORBIDDEN);
				}else{
					Map<String, String> result = new HashMap<>();
					result.put("name", name);
					ResponseUtil.toJSON(req, resp, result);
				}
				return;
			}
		}else if(path.segmentCount() == 2){
			String res = path.segment(1);
			if(res.equals("lessons")){
				Long courseId = Long.valueOf(path.segment(0));
				Long userId = ((UserInfo)UserSession.getUser(req)).getId();
				Lesson lesson = RequestUtil.fromJsonObject(req, Lesson.class);
				
				Validator validator = new Validator();
				String name = lesson.getName();
				if(name.isEmpty()){
					validator.addFieldError("name", "名称不能为空");
				}else if(name.length() > 32){
					validator.addFieldError("name", "名称最多32个字");
				}else if(lessonService.nameIsUsed(courseId, name)){
					validator.addFieldError("name", "名称已被使用");
				}if(validator.hasErrors()){
					ResponseUtil.toJSON(req, resp, validator.getFieldErrors(), HttpServletResponse.SC_PRECONDITION_FAILED);
					return;
				}
				
				lesson.setCourseId(courseId);
				Long lessonId = lessonService.add(userId, lesson);
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("id", lessonId);
				ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_CREATED);
				return;
			}
		}else if(path.segmentCount() == 3){
			Long courseId = Long.valueOf(path.segment(0));
			String res = path.segment(1);
			String verb = path.segment(2);
			if(res.equals("lessons")){
				if(verb.equals("check-name")){
					Map<String, Object> map = RequestUtil.fromJsonObject(req);
					String name = map.get("value").toString();
					if(lessonService.nameIsUsed(courseId, name)){
						Map<String, String> result = new HashMap<>();
						result.put("message", "名称已被使用");
						ResponseUtil.toJSON(req, resp, result, HttpServletResponse.SC_FORBIDDEN);
					}else{
						Map<String, String> result = new HashMap<>();
						result.put("name", name);
						ResponseUtil.toJSON(req, resp, result);
					}
					return;
				}
			}
			
		}else if(path.segmentCount() == 4){
			Long userId = ((UserInfo)UserSession.getUser(req)).getId();
			Long courseId = Long.valueOf(path.segment(0));
			Long lessonId = Long.valueOf(path.segment(2));
			String resLesson = path.segment(1);
			String resPPT = path.segment(3);
			if(resLesson.equals("lessons") && resPPT.equals("ppt")){
				Map<String, Object> pptMap = RequestUtil.fromJsonObject(req);
				String content = pptMap.get("content").toString();
				String commitMessage = pptMap.get("commitMessage").toString();
				pptService.add(userId, courseId, lessonId, content, commitMessage);
				ResponseUtil.toJSON(req, resp, null, HttpServletResponse.SC_CREATED);
				return;
			}
			
		}
		super.doPost(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		traceRequest(req);
		IPath path = getPath(req);
		int segmentCount = path.segmentCount();
		if(segmentCount == 2){
			Long courseId = Long.valueOf(path.segment(0));
			String res = path.segment(1);
			if(res.equals("lessons")){
				PageInfo pageInfo = getPageInfo(req);
				List<Lesson> result = lessonService.get(courseId, pageInfo);
				ResponseUtil.toJSON(req, resp, pageInfo, result);
				return;
			}
		}
		
		if(segmentCount == 4){
			Long courseId = Long.valueOf(path.segment(0));
			Long lessonId = Long.valueOf(path.segment(2));
			String resLesson = path.segment(1);
			String resPPT = path.segment(3);
			if(resLesson.equals("lessons") && resPPT.equals("ppt")){
				String content = pptService.getContent(courseId, lessonId);
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("content", content);
				ResponseUtil.toJSON(req, resp, result);
				return;
			}
		}
		
		super.doGet(req, resp);
	}
	
	

	
}

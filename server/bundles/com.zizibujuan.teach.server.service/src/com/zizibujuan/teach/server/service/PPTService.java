package com.zizibujuan.teach.server.service;

/**
 * ppt内容管理服务接口
 * 
 * @author jzw
 * @since 0.0.1
 */
public interface PPTService {
	
	static final String KEY_GIT_ROOT_COURSE = "git.repo.root.course";
	static final String PPT_FILE_NAME = "ppt.md";

	/**
	 * 在git仓库中添加ppt内容。如果git仓库还没有创建，则先创建git仓库，再添加ppt内容。
	 * 
	 * 注意，一个课时只对应一个ppt文件，使用markdown格式。
	 * 
	 * git仓库的目录结构为：
	 * <pre>
	 *  courseId
     *   	res
     *     		images
     *     		video
     *     		audio
     *   	lessonId
     *     		ppt
     *       		ppt1.md
	 * </pre>
	 * 
	 * @param userId 用户标识
	 * @param courseId 课程标识
	 * @param lessonId 课时标识
	 * @param pptContent ppt内容
	 * @param commitMessage 提交内容说明
	 */
	void add(Long userId, Long courseId, Long lessonId, String pptContent, String commitMessage);

}

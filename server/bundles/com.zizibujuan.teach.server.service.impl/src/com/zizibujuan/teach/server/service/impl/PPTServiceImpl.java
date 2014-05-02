package com.zizibujuan.teach.server.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zizibujuan.cm.server.dao.ApplicationPropertyDao;
import com.zizibujuan.server.git.GitUtils;
import com.zizibujuan.teach.server.service.PPTService;
import com.zizibujuan.useradmin.server.dao.UserDao;
import com.zizibujuan.useradmin.server.model.UserInfo;

/**
 * ppt内容管理服务实现类
 * 
 * @author jzw
 * @since 0.0.1
 */
public class PPTServiceImpl implements PPTService {

	private Logger logger = LoggerFactory.getLogger(PPTServiceImpl.class);
	
	private ApplicationPropertyDao applicationPropertyDao;
	private UserDao userDao;
	
	@Override
	public void add(Long userId, Long courseId, Long lessonId, String pptContent, String commitMessage) {
		String gitRootPath = applicationPropertyDao.getForString(KEY_GIT_ROOT_COURSE);
		String gitRepoPath = gitRootPath + courseId;
		UserInfo userInfo = userDao.getById(userId);
		if(!GitUtils.isGitRepo(gitRepoPath)){
			// 如果不存在，则先创建git仓库
			GitUtils.init(gitRepoPath, userInfo.getLoginName(), userInfo.getEmail());
		}
		
		String relativePath = "/" + lessonId + "/";
		String fileName = PPT_FILE_NAME;
		GitUtils.commit(gitRootPath + courseId, relativePath, fileName, pptContent, userInfo.getLoginName(), userInfo.getEmail(), commitMessage);
	}

	@Override
	public String getContent(Long courseId, Long lessonId) {
		String gitRootPath = applicationPropertyDao.getForString(KEY_GIT_ROOT_COURSE);
		String filePath = gitRootPath + courseId + "/" + lessonId + "/" + PPT_FILE_NAME;
		StringWriter sw = null;
		InputStream in = null;
		try{
			sw = new StringWriter();
			in = new FileInputStream(filePath);
			IOUtils.copy(in, sw);
			return sw.toString();
		} catch (FileNotFoundException e) {
			logger.error("没有找到文件", e);
			return "";
		} catch (IOException e) {
			logger.error("获取文件内容出错", e);
			return "";
		}finally{
			IOUtils.closeQuietly(sw);
			IOUtils.closeQuietly(in);
		}
	}
	
	public void setApplicationPropertyDao(ApplicationPropertyDao applicationPropertyDao) {
		logger.info("注入applicationPropertyDao");
		this.applicationPropertyDao = applicationPropertyDao;
	}

	public void unsetApplicationPropertyDao(ApplicationPropertyDao applicationPropertyDao) {
		if (this.applicationPropertyDao == applicationPropertyDao) {
			logger.info("注销applicationPropertyDao");
			this.applicationPropertyDao = null;
		}
	}
	
	public void setUserDao(UserDao userDao) {
		logger.info("注入userDao");
		this.userDao = userDao;
	}

	public void unsetUserDao(UserDao userDao) {
		if (this.userDao == userDao) {
			logger.info("注销userDao");
			this.userDao = null;
		}
	}
}

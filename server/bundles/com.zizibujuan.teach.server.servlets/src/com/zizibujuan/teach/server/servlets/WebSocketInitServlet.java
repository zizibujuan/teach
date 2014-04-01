package com.zizibujuan.teach.server.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class WebSocketInitServlet extends HttpServlet{

	private static final long serialVersionUID = -3169009992878082202L;

	private static List<TeachingSocket> socketList;  
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		socketList = new ArrayList<TeachingSocket>(); 
		super.init(config);
		System.out.println("Server start============");  
	}

	public static synchronized List<TeachingSocket> getSocketList() {  
        return socketList;  
    }
}

package com.zizibujuan.teach.server.servlets;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class TeachingServlet extends WebSocketServlet{

	private static final long serialVersionUID = -3474203042078996620L;

	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		return new TeachingSocket();
	}






}

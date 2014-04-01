package com.zizibujuan.teach.server.servlets;

import java.io.IOException;
import java.util.List;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

/**
 * 教学终端
 * 
 * @author jzw
 * @since 0.0.1
 */
public class TeachingSocket implements OnTextMessage {

	private Connection conn;

	@Override
	public void onClose(int arg0, String arg1) {
		WebSocketInitServlet.getSocketList().remove(this);
		System.out.println("onClose==========================");
	}

	@Override
	public void onOpen(Connection conn) {
		System.out.println("onOpen=========================="
				+ conn.getMaxIdleTime());
		WebSocketInitServlet.getSocketList().add(this);
		this.conn = conn;
	}

	@Override
	public void onMessage(String data) {
		List<TeachingSocket> socketList = WebSocketInitServlet.getSocketList();
		for (TeachingSocket socket : socketList) {
			try {
				socket.getConn().sendMessage(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}

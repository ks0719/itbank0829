package spring.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler{
	private Set<WebSocketSession> set = new HashSet<>();
	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		set.add(session);
		log.info("클라이언트 접속 : "+session.getRemoteAddress());
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		set.remove(session);
		log.info("클라이언트 종료 : "+session.getRemoteAddress());
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//interceptor를 통해 주입된 HttpSession의 정보를 확인
		Map<String, Object> attr = session.getAttributes();
		log.info(attr.toString());
		String id=(String) attr.get("id");
		
		Iterator<WebSocketSession> iter = set.iterator();
		String host = session.getRemoteAddress().getHostName();
		TextMessage newMessage = new TextMessage("["+id+"] "+message.getPayload());
		while(iter.hasNext()) {
			WebSocketSession ws = iter.next();
			ws.sendMessage(newMessage);
		}
	}
}

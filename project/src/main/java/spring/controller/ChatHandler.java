package spring.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import spring.db.member.MemberDao;
@Controller
public class ChatHandler extends TextWebSocketHandler{
	@Autowired
	MemberDao mdao;
	private Set<WebSocketSession> set = new HashSet<>();
	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		set.add(session);
		log.info("클라이언트 접속 : "+session.getRemoteAddress());
		Map<String,Object> attr=session.getAttributes();
		//log.debug("별걸 다하네 : "+attr.get("mynick"));
		//log.debug("이건또 뭐야?"+session.getAttributes());
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
		log.info("이건가?:"+attr.toString());
		String id=(String) attr.get("id");
		
		Iterator<WebSocketSession> iter = set.iterator();
		String host = session.getRemoteAddress().getHostName();
		TextMessage newMessage = new TextMessage("["+id+"] "+message.getPayload());
		while(iter.hasNext()) {
			WebSocketSession ws = iter.next();
			ws.sendMessage(newMessage);
		}
	}
	@RequestMapping(value="/ChatHandler/myfriendlist",method=RequestMethod.POST,produces = "application/json")
	@ResponseBody
	public	FriendVo myfriendlist(String mynick){
		log.debug("들어옴?");
		List<String> list=new ArrayList<String>();
		list=mdao.myfriendlist(mynick);
		
		FriendVo vo=new FriendVo();
		vo.setId("친구목록");
		vo.setList(list);
		return vo;
	}
}

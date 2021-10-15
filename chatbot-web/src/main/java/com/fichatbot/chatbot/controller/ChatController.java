package com.fichatbot.chatbot.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fichatbot.chatbot.service.ChatService;


@RestController
public class ChatController {
	
	@Autowired ChatService chatService;	
		
	@CrossOrigin("*")
	@PostMapping(value = "/chat/open")
	public Map<String, Object> open(@RequestBody Map<String, Object> data) {
		
		String store = (String) data.get("store");
//		Map<String, Object> answer = new HashMap<String, Object>();
//		String message = "";
//		
//		if (store.equals("SKT")) {
//			message = "등급을 모를경우 여기서 확인해주세요:\nhttps://tmembership.tworld.co.kr:8000/web/html/membership/useguide/card_basic_info08.jsp\n";
//		}
//		else if(store.equals("KT")) {
//			message = "등급을 모를경우 여기서 확인해주세요:\nhttps://membership.kt.com/guide/system/SystemInfo.do\n";
//		}
//		else if(store.equals("LG")){
//			message = "등급을 모를경우 여기서 확인해주세요:\nhttps://www.uplus.co.kr/evt/mbrs/cdap/RetrieveUbMbNewMemInfo.hpi?mid=13123\n";
//		}
//		
//		answer.put("position", "left");
//		answer.put("type", "text");
//		answer.put("text", message);
//		answer.put("date", new Date());
		
		//return answer;
		return chatService.open(store);
	}
	
	@CrossOrigin("*")
	@PostMapping(value = "/chat/message")
	public Map message(@RequestBody Map<String, Object> data, HttpServletRequest req) throws IOException {	// 사용자의 채팅을 입력받음
		return chatService.message(data, req);
	}
}
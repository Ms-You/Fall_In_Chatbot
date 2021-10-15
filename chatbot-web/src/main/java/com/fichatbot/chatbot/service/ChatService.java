package com.fichatbot.chatbot.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fichatbot.chatbot.dao.ChatDao;


@Service
public class ChatService {
	String store;
	
	public void setStore(String store) {this.store = store;}
	public String getStore() {return store;}
	
	private ChatDao chatDao = new ChatDao();
	
    public Map<String, Object> open(String store) {
    	String json = chatDao.open();
    	setStore(store);
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Map<String, Object> res = new HashMap<String, Object>();
    	try {
			Map<String, Object> map = mapper.readValue(json, Map.class);
			Map<String, Object> return_object = (Map<String, Object>)map.get("return_object");
			Map<String, Object> result = (Map<String, Object>) return_object.get("result");
			Map<String, Object> submap = new HashMap<String, Object>();
			
			String uuid = (String) return_object.get("uuid");
			
			String text = (String) result.get("system_text");
			
			
			
			submap.put("id", "user");
			
			res.put("id", "chatbot");
			res.put("uuid", uuid);
			res.put("text", text);
			res.put("createdAt", new Date());
			res.put("user", submap);
			
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return res;
    }

    @SuppressWarnings("unchecked")
	public Map<String, Object> message(Map<String, Object> data, HttpServletRequest servletReq) {
    	
		Map<String, Object> question = (Map<String, Object>)data.get("question");
		
		Map<String, String> req = new HashMap<>();
		req.put("text", (String)question.get("text"));
		req.put("uuid", (String)data.get("uuid"));
		
		String folderName = servletReq.getSession().getServletContext().getRealPath("/") + "resources" + File.separator + "tts";
		
		String json = chatDao.message(req);
    	ObjectMapper mapper = new ObjectMapper();
    	
		Map<String, Object> res = new HashMap<String, Object>();
    	try {
			Map<String, Object> map = mapper.readValue(json, Map.class);
			Map<String, Object> return_object = (Map<String, Object>)map.get("return_object");
			Map<String, Object> result = (Map<String, Object>) return_object.get("result");
			Map<String, Object> submap = new HashMap<String, Object>();
			
			String uuid = (String) return_object.get("uuid");
			String text = (String) result.get("system_text");
			
			String store = getStore();
			if (store.equals("SKT")) {
				if(text.contains("꼭 입력해주세요")) 
					text = text.replace("꼭 입력해주세요", "(등급을 모를경우 여기서 확인해주세요:\nhttps://tmembership.tworld.co.kr:8000/web/html/membership/useguide/card_basic_info08.jsp)\n");
			}
			else if(store.equals("KT")) {
				if(text.contains("꼭 입력해주세요"))
					text = text.replace("꼭 입력해주세요", "(등급을 모를경우 여기서 확인해주세요:\nhttps://membership.kt.com/guide/system/SystemInfo.do)\n");
			}
			else if(store.equals("LG")){
				if(text.contains("꼭 입력해주세요"))
					text = text.replace("꼭 입력해주세요", "(등급을 모를경우 여기서 확인해주세요:\nhttps://www.uplus.co.kr/evt/mbrs/cdap/RetrieveUbMbNewMemInfo.hpi?mid=13123)\n");
			}
			
			if (text.contains("Telecom.type")) {
				text = "일치하는 혜택 결과가 없습니다.";
			}
			submap.put("id", "user");
			
			res.put("id", "chatbot");
			res.put("uuid", uuid);
			res.put("text", text);
			res.put("createdAt", new Date());
			res.put("user", submap);
			res.put("ttsUrl", chatDao.tts(folderName, text));
			
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}

}
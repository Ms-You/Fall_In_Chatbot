package com.fichatbot.chatbot.controller;

import java.util.Map;

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
   public Map open() {
	   return chatService.open();
   }
   
   
   @CrossOrigin("*")
   @PostMapping(value = "/chat/message")
   public Map message(@RequestBody Map<String, Object> data) {
	   
	   return chatService.message(data);
	   
   }
   

   
}
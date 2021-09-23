package com.fichatbot.chatbot;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ChatController {
   
   private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
   
   @CrossOrigin("*")
   @PostMapping(value = "/chat")
   public Map message(Locale locale, Model model) {
      logger.info("Welcome home! The client locale is {}.", locale);
      
      Map map = new HashMap();
      Map submap = new HashMap();
      
      map.put("Id","chatbot");
      map.put("text", "¾È³ç ³ª´Â Ãªº¿ÀÌ¾ß");
      map.put("createAt", new Date());
      
      submap.put("id", "user");
      map.put("user", submap);
      
      return map;
   }
   
}
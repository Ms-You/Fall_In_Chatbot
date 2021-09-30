package com.fichatbot.chatbot.dao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


public class ChatDao {
	private static final String OPEN_API_URL = "http://aiopen.etri.re.kr:8000/Dialog";
	private static final String ACCESS_KEY = "cdc949b2-648d-4c69-977c-b6be16a2b8de";

	public String open() {
		String openApiURL = OPEN_API_URL;
        String accessKey = ACCESS_KEY;
        String domain = "Benefit";
        String access_method = "internal_data";
        String method = "open_dialog";
        Gson gson = new Gson();
 
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
 
        ////////////////////////// OPEN DIALOG //////////////////////////
 
        argument.put("name", domain);
        argument.put("access_method", access_method);
        argument.put("method", method);
 
        request.put("access_key", accessKey);
        request.put("argument", argument);
 
 
        URL url;
        Integer responseCode = null;
        String responBody = null;
        try {
                url = new URL(openApiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
 
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(gson.toJson(request).getBytes("UTF-8"));
                wr.flush();
                wr.close();
 
                responseCode = con.getResponseCode();
                InputStream is = con.getInputStream();
                byte[] buffer = new byte[is.available()];
                int byteRead = is.read(buffer);
                responBody = new String(buffer);
 
                System.out.println("[responseCode] " + responseCode);
                System.out.println("[responBody]");
                System.out.println(responBody);
 
        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        return responBody;
	}
	
	public String message(Map map) {
		String openApiURL = OPEN_API_URL;
        String accessKey = ACCESS_KEY;
        String uuid = (String)map.get("uuid");  // Open Dialog로 부터 생성된 UUID
        String method = "dialog";           // method 호출 방식
        String text = (String)map.get("text");          // method 호출 방식
        Gson gson = new Gson();
 
        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();
 
        ////////////////////////// OPEN DIALOG //////////////////////////
 
        argument.put("uuid", uuid);
        argument.put("method", method);
        argument.put("text", text);
 
        request.put("access_key", accessKey);
        request.put("argument", argument);
 
 
        URL url;
        Integer responseCode = null;
        String responBody = null;
        try {
                url = new URL(openApiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
 
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.write(gson.toJson(request).getBytes("UTF-8"));
                wr.flush();
                wr.close();
 
                responseCode = con.getResponseCode();
                InputStream is = con.getInputStream();
                byte[] buffer = new byte[is.available()];
                int byteRead = is.read(buffer);
                responBody = new String(buffer);
 
                System.out.println("[responseCode] " + responseCode);
                System.out.println("[responBody]");
                System.out.println(responBody);
 
        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
		return responBody;
	}
	
	

}

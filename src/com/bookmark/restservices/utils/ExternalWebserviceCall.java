package com.bookmark.restservices.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ExternalWebserviceCall {
	
	//private static Logger logger = Logger.getLogger(ExternalWebserviceCall.class);
	private String targetURI;
	
	public ExternalWebserviceCall(String targetURI) {
		this.targetURI = targetURI;
	}

	public JSONObject httpPostMethod(Object object2,String methodName)
			throws MalformedURLException, IOException {
		String url = targetURI+"/"+methodName;
		JSONObject jsonObj=null; 
		//logger.debug("ExternalWebserviceCall::httpPostMethod:: Post URL : "+url);
		System.out.println("url:"+url);
		URL object = new URL(url);
		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(object2.toString());
		wr.flush();
		System.out.println("JSON Request: " + object2.toString());

		StringBuilder jsonData = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK || HttpResult == HttpURLConnection.HTTP_ACCEPTED ) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				jsonData.append(line + "\n");
			}
			br.close();
			try{
				if(jsonData != null && !jsonData.toString().isEmpty()) {
					jsonObj= (JSONObject)new JSONParser().parse(jsonData.toString());
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			//logger.error("ExternalWebserviceCall::httpPostMethod:: Web Service is down. Response Code: " + HttpResult);
		}
		
		return jsonObj;
	}// httpPostMethod

	public static String convertPojoToJson(Object pojoObject)throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(pojoObject);
		return jsonString;
	}

	public JSONObject httpGetMethod(String methodName) throws IOException {
		String url = targetURI+"/"+methodName;
		JSONObject jsonObj=null; 
		//logger.debug("ExternalWebserviceCall::httpPostMethod:: Post URL : "+url);
		System.out.println("url:"+url);
		URL object = new URL(url);
		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");
		//con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("GET");
		StringBuilder jsonData = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK || HttpResult == HttpURLConnection.HTTP_ACCEPTED ) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				jsonData.append(line + "\n");
			}
			br.close();
			try{
				if(jsonData != null && !jsonData.toString().isEmpty()) {
					jsonObj= (JSONObject) new JSONParser().parse(jsonData.toString());
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			//logger.error("ExternalWebserviceCall::httpPostMethod:: Web Service is down. Response Code: " + HttpResult);
		}
		return jsonObj;
	}
	
}

package com.bookmark.restservices.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bookmark.restservices.model.BookMark;
import com.bookmark.restservices.utils.ExternalWebserviceCall;

public class CheckOutClient
{
  public static void main(String[] args) throws MalformedURLException, IOException{
	
	 
	  JSONObject jsonObject = getAllTest();
	  JSONArray data= (JSONArray)jsonObject.get("dataList");
	  Iterator jsonitr = data.iterator();
	  HashMap<String, BookMark> hmbk = new HashMap<String, BookMark>();
	  List<String> tree = new ArrayList<String>();
	  while (jsonitr.hasNext()) {
	    JSONObject innerObj = (JSONObject)jsonitr.next();
	    BookMark bm= new BookMark(Integer.parseInt(innerObj.get("id").toString()),innerObj.get("name").toString(),innerObj.get("type").toString(),innerObj.get("path").toString(),"","",innerObj.get("parent").toString());
	    hmbk.put(innerObj.get("path").toString(), bm);
	    tree.add(bm.getPath());
	  }
	  Collections.sort(tree);
	  
	  StringBuffer htmlcont = new StringBuffer();
	  htmlcont.append("<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>Structure</title></head><body><h1>My BookMarks</h1><ol class='tree'>");
	  Iterator<String> iterator = tree.iterator();
	  String prev="";
	  String curr="";
		while (iterator.hasNext()) {
			//System.out.println(iterator.next());
			BookMark bm= hmbk.get(iterator.next());
			if(!bm.getParent().equals("BOOKMARKS")){
				htmlcont.append("<ol>");
			}
			if(bm.getType().equals("FOLDER")){
				if(bm.getPath().startsWith(prev)){
					if(bm.getPath().equals(prev)){
					htmlcont.append("<li><label for='"+bm.getName()+"'>"+bm.getName()+"</label> <input type='checkbox' checked disabled id='"+bm.getName()+"' /> </li>");
					}else{
						htmlcont.append("<li><label for='"+bm.getName()+"'>"+bm.getName()+"</label> <input type='checkbox' checked disabled id='"+bm.getName()+"' /> </li>");
					}
						
					prev=bm.getParent();
				}else{
					htmlcont.append("<li><label for='"+bm.getName()+"'>"+bm.getName()+"</label> <input type='checkbox' checked disabled id='"+bm.getName()+"' /> </li>");
				}
			}else{
				htmlcont.append("<li class='file'><a href=''>"+bm.getName()+"</a></li>");
			}
			if(!bm.getParent().equals("BOOKMARKS")){
				htmlcont.append("</ol>");
			}
		}
      htmlcont.append("</ol></body></html>");
      System.out.println(htmlcont);

  }
  
  public static JSONObject getAllTest() throws IOException{
	  ExternalWebserviceCall extserv = new ExternalWebserviceCall("http://localhost:8080/Bookmark");
	 return extserv.httpGetMethod("rest/getall");
	 
  
  }
  public static void newTest() throws MalformedURLException, IOException{
	  ExternalWebserviceCall extserv = new ExternalWebserviceCall("http://localhost:8080/Bookmark");
	  JSONObject jsonobj =new JSONObject();
	  jsonobj.put("name", "helxvlo");
	  jsonobj.put("type", "FOLDER");
	  jsonobj.put("newpath", "helxvlo");
	  extserv.httpPostMethod(jsonobj, "rest/new");
  }
}

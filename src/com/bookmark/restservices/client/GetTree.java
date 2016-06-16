package com.bookmark.restservices.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import com.bookmark.restservices.model.BookMark;
import com.bookmark.restservices.utils.JdbcUtil;

public class GetTree {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		HashMap<String,BookMark> collectionbookmark =getAllBookmarks();
		
		  MXMTree tree = new MXMTree(new MXMNode("root", "root"));
		
		
		HashMap<String,HashSet<BookMark>> treestructure =new HashMap<String,HashSet<BookMark>>();
		
		//treestructure.put("BOOKMARK", null);
		for (Entry<String, BookMark> entry : collectionbookmark.entrySet()) {
		   /* String key = entry.getKey();
		    BookMark value = entry.getValue();
		    System.out.println(key+" :: "+value.getPath()+" : "+value.getParent()+" : "+value.getParentid());
		    
		    if(!key.equals("BOOKMARK")){
			    if(treestructure.containsKey(key)){
			    	HashSet<BookMark> g = treestructure.get(key);
					   g.add(value);
					   treestructure.put(value.getParent(),g);
			    }else{
			    	HashSet<BookMark> x = new HashSet<BookMark>();
			    	 treestructure.put(key,x );
			    }
			   
			 
		    } */
		    
		tree.addElement(entry.getKey());
		    
		  
		
		   
		}
		tree.printTree();
		
	/*	for (Entry<String, HashSet<BookMark>> entry : treestructure.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue().size());
			  Iterator<BookMark> iterator = entry.getValue().iterator();
			  while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					BookMark bm= iterator.next();
					System.out.println("           "+bm.getPath());
			  }
		}*/
	}

	public static HashMap<String, BookMark> getAllBookmarks() throws SQLException {
		// TODO Auto-generated method stub
		HashMap<String,BookMark> collectionbookmark = new HashMap<String,BookMark>();
		
		Connection conn=JdbcUtil.getInstance().getMySqlConnection();
		String sql ="Select id,name,type,path,url,description,parentid from "+JdbcUtil.getInstance().getProperty("mysql.favourite.tablename");
		PreparedStatement prest = conn.prepareStatement(sql); 
		ResultSet rs = prest.executeQuery(sql);
		 while(rs.next()){
			 BookMark bookmark = new BookMark();
			 bookmark.setId(rs.getInt(1));
			 bookmark.setName(rs.getString(2));
			 bookmark.setType(rs.getString(3));
			 String path=rs.getString(4);
			 bookmark.setPath(path);
			 bookmark.setUrl(rs.getString(5));
			 bookmark.setDescription(rs.getString(6));
			 StringBuilder parent=new StringBuilder(path);
			 System.out.println(parent.toString() +"--->"+parent.lastIndexOf("/"+rs.getString(2)));
			 if(!path.equals("BOOKMARK") ){
				 parent.replace(parent.lastIndexOf("/"+rs.getString(2)), parent.length(), "");
				 bookmark.setParent(parent.toString());
			 }else{
				 bookmark.setParent("$$$"); 
			 }
			 bookmark.setParentid(rs.getInt(7));
			 collectionbookmark.put(bookmark.getPath(),bookmark);	 
		 }
		 return collectionbookmark;
	}

}

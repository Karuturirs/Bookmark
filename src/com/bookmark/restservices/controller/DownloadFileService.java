package com.bookmark.restservices.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.bookmark.restservices.utils.JdbcUtil;
 
@Path("/download")
public class DownloadFileService 
{
    @GET
    @Path("/file")
    public Response downloaCSVFile()
    {
        StreamingOutput fileStream =  new StreamingOutput() 
        {
            @Override
            public void write(java.io.OutputStream output) throws IOException 
            {
                	String filename = "D:/out/sampleout.csv";
                	exportData(filename);
                    java.nio.file.Path path = Paths.get(filename);
                    byte[] data = Files.readAllBytes(path);
                    output.write(data);
                    output.flush();

                
            }
        };
        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = myfav.csv")
                .build();
    }
    
    public void exportData(String filename) {
        Statement stmt;
        String query;
        try {
            //stmt = JdbcUtil.getInstance().getMySqlConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
             
            //For comma separated file
         //   query = "SELECT * into OUTFILE  '"+filename+ "' FIELDS TERMINATED BY ',' FROM "+JdbcUtil.getInstance().getProperty("mysql.favourite.tablename");
            //stmt.executeQuery(query);
        	 FileWriter fw = new FileWriter(filename);
     	    Connection conn=JdbcUtil.getInstance().getMySqlConnection();
     		String sql =JdbcUtil.getInstance().getProperty("bookmark_fav.download.query")+JdbcUtil.getInstance().getProperty("mysql.favourite.tablename");
     		PreparedStatement prest = conn.prepareStatement(sql); 
     		ResultSet rs = prest.executeQuery(sql);
     		 fw.append(JdbcUtil.getInstance().getProperty("bookmark_fav.select.columns"));
             fw.append('\n');
     		 while(rs.next()){
     			 System.out.println(rs.getInt(1));
                  fw.append(String.valueOf(rs.getInt(1)));
                  fw.append(',');
                  fw.append(rs.getString(2));
                  fw.append(',');
                  fw.append(rs.getString(3));
                  fw.append(',');
                  fw.append(rs.getString(4));
                  fw.append(',');
                  fw.append(rs.getString(5));
                  fw.append(',');
                  fw.append(rs.getString(6));
                  fw.append(',');
                  fw.append(rs.getString(7));
                  fw.append('\n');
     		 }
     		 fw.flush();
              fw.close();
        } catch(Exception e) {
            e.printStackTrace();
            stmt = null;
        }
    }
}
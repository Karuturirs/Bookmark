package com.bookmark.restservices.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bookmark.restservices.model.Output;



public class LoadCSVtoDB {
 

    private Connection connection;
    private String seprator;
 
    /**
     * Public constructor to build CSVLoader object with
     * Connection details. The connection is closed on success
     * or failure.
     * @param connection
     */
    public LoadCSVtoDB(Connection connection, String seprator) {
        this.connection = connection;
        //Set default separator
        this.seprator = ",";
    }
     
    /**
     * Parse CSV file using OpenCSV library and load in 
     * given database table. 
     * @param csvFile Input CSV file
     * @param truncateBeforeLoad Truncate the table before inserting 
     *          new records.
     * @throws Exception
     */
    public void CSVtoDB(String csvFile,
            boolean truncateBeforeLoad,Output output) throws Exception {
 
    	 BufferedReader fileReader = null;
		try{
		        if(null == this.connection) {
		            throw new Exception("Not a valid connection.");
		        }
		        String line = "";
		       //Create the file reader
		       fileReader = new BufferedReader(new FileReader(csvFile));
		       //Read the CSV file header to skip it
		       fileReader.readLine();
		       //Read the file line by line starting from the second line
		       final int batchSize = 1000;
	           int count = 0;
	           PreparedStatement prest =null;
	           if(truncateBeforeLoad){
	        	 //delete data from table before loading csv
	                connection.createStatement().execute("DELETE FROM " + JdbcUtil.getInstance().getProperty("mysql.favourite.tablename"));
	           }
	           //connection.setAutoCommit(false);
		       while ((line = fileReader.readLine()) != null) {
			       //Get all tokens available in line
		    	   String[] tokens=line.split(seprator);
		    	   if (null != line) {
			    	   if(count>0){
			    		   String sql="Insert into "+JdbcUtil.getInstance().getProperty("mysql.favourite.tablename")+" ("+JdbcUtil.getInstance().getProperty("bookmark_fav.insert.columns")+") VALUES (?,?,?,?,?)";
			    		   prest = (PreparedStatement) connection.prepareStatement(sql);  
			    		   prest.setString(1, tokens[0]); 
			    		   prest.setString(2, tokens[1]); 
			    		   prest.setString(3, tokens[2]); 
			    		   prest.setString(4, tokens[3]); 
			    		   prest.setString(5, tokens[4]); 
			    		   prest.execute();
			    		  // prest.addBatch();
			    	   }
		    	   }
		    	   if (++count % batchSize == 0) {
		    		   //prest.executeBatch();
	                }
		       }
		       output.setStatus(200);
				output.setMessage("OK");
				output.setDescription("Successfully uploaded records to DB.");
		      // prest.executeBatch();
		      // connection.commit();
        }catch (Exception e) {
        	output.setStatus(800);
			output.setMessage("Unable to process the CSVtoDB");
			output.setDescription(e.getMessage());
        	System.out.println("Error in CSVtoDB !!!");
        	e.printStackTrace();
        } finally {
        	try {
                  fileReader.close();
        	} catch (IOException e) {
        			System.out.println("Error while closing CSVtoDB !!!");
        			e.printStackTrace();
        	}
        }
    }
 
    public String getSeprator() {
        return seprator;
    }
 
    public void setSeprator(String seprator) {
        this.seprator = seprator;
    }
 
}
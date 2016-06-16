package com.bookmark.restservices.utils;


/****************************************************************************************************
 *  Loads all the property from the jdbc.properties
*******************************************************************************************************/
public  class PropertyFileReader {
	public PropertyFileReader() {
	}
	 public static String getProperty(String key)
     {
        String keyValue=JdbcUtil.getInstance().getproperties().getProperty(key);
        return keyValue;
     }
	     
}
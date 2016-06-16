package com.bookmark.restservices.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class JdbcUtil {
	private static Connection con;
	private static volatile JdbcUtil instanceObjectValidator = null;
	private static Properties prop;
	private JdbcUtil() {
		try {
			 prop = new Properties();
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
			prop.load(inputStream);
			String connectionUrl = prop.getProperty("mysql.jdbc.url")+ prop.getProperty("mysql.database");
			String dbUser = prop.getProperty("mysql.jdbc.username");
			String dbPwd = prop.getProperty("mysql.jdbc.password");
			Class.forName(prop.getProperty("mysql.jdbc.driverClassName"));
			if (con == null) {
				con = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static JdbcUtil getInstance() {
		if (instanceObjectValidator == null) {
			synchronized (JdbcUtil.class) {
				if (instanceObjectValidator == null) {
					instanceObjectValidator = new JdbcUtil();
				}
			}
		}
		return instanceObjectValidator;
	}

	public  Connection getMySqlConnection() {
		return con;
	}
	public  Properties getproperties(){
		return prop;
	}
	 public String getProperty(String key)
     {
        String keyValue=prop.getProperty(key);
        return keyValue;
     }
	 public JSONObject pojo2Json(Object obj){
			JSONObject object = new JSONObject();
			try{
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonString = objectMapper.writeValueAsString(obj);
				JSONParser jsonParser = new JSONParser();
				object = (JSONObject) jsonParser.parse(jsonString);
			}catch(Exception e){
				e.printStackTrace();
			}
			return object;
		}
}

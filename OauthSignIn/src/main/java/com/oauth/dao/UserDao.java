//$Id$
package com.oauth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.oauth.model.User;

public class UserDao {
	

		
		public static Connection toconnect()
		{
			String url = "jdbc:postgresql://localhost:5432/oauth";
			Connection conn = null;
			
			try {
				Class.forName("org.postgresql.Driver");
	
				conn = DriverManager.getConnection(url,"postgres", "123");
			    } 
			catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
			    }
			return conn;
			
		}
		
		public static int insert(User user) throws SQLException
		{
		   Connection connection = UserDao.toconnect();
		   String INSERT_QUERY;
			int result =0;
			try {
				INSERT_QUERY= QueryGenerator.insertQuery(user.returnDetails(), "User","");
				System.out.println(INSERT_QUERY);
						PreparedStatement preparedstat = connection.prepareStatement(INSERT_QUERY);
						for(int i=1;i<user.getDetails().length;i++) {
						preparedstat.setObject(i,user.getDetails()[i-1]);
						}
						result = preparedstat.executeUpdate();
						System.out.println(result);
				
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName()+": "+e.getMessage());
		        System.exit(0);
			}
			finally {
			connection.close();
			}
			return result;
		}
		
//		public JSONArray select(User user) throws SQLException
//		{
//			Connection connection =toconnect();
//			String SELECT_QUERY;
//			try
//			{
//				PreparedStatement preparedstat;
//				if(user.getEmail_id()!= null) {
//				SELECT_QUERY = QueryGenerator.selectQuery("customer", " * "," where email_id =? and passwrd=? ");
//				preparedstat = connection.prepareStatement(SELECT_QUERY);
//				preparedstat.setString(1, user.getEmail_id());
//				preparedstat.setString(2, user.getPasswrd());
//				}
//				else {
//					SELECT_QUERY = QueryGenerator.selectQuery("customer", " * ","");
//					preparedstat = connection.prepareStatement(SELECT_QUERY);	
//				}
//				
//				ResultSet rs =preparedstat.executeQuery(); 
//				JSONArray jsonArray = new JSONArray();
//				while (rs.next()) {
//
//					int columns = rs.getMetaData().getColumnCount();
//					JSONObject obj = new JSONObject();
//
//					for (int i = 0; i < columns; i++)
//						obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
//					jsonArray.put(obj);
//				}
//				return jsonArray;
//			}
//			catch (SQLException | JSONException e) {
//				e.printStackTrace();
//				System.err.println(e.getClass().getName()+": "+e.getMessage());
//		        System.exit(0);
//			}
//			finally {
//			connection.close();
//			}
//			return null;
//			
//			
//			
//		}
		

	}



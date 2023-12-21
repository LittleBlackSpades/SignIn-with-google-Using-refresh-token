//$Id$
package com.oauth.dao;

public class QueryGenerator {
	
	public static String insertQuery(String[] colms,String tableName ,String condn)
	{
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO "+tableName+" (");
		StringBuilder val = new StringBuilder(" VALUES (");
		int len = colms.length-1;
		for(int i=1;i<len;i++)
		{
			query.append(colms[i]+",");
			val.append("?,");
		}
		query.append(colms[len]+") ");
		val.append("?)"+condn+";");
		query.append(val);
		System.out.println(query.toString());
		return query.toString();		
	}
	
	
	public static String updateQuery(String[] colms,String tableName,String condn)
	{
		StringBuilder query = new StringBuilder();
		query.append("UPDATE "+tableName+" SET ");
		int len = colms.length-1;
		for(int i=1;i<len;i++)
		{
			query.append(colms[i]+"=?, ");
					
		}
		query.append(colms[len]+" =?  WHERE "+condn+"?;");
		return query.toString();		
		
	}
	
	public static String deleteQuery(String tableName,String condn)
	{
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM "+ tableName +" WHERE "+condn+";");
		return query.toString();
	}
	
	
	public static String selectQuery(String tableName, String field,String condn)
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT "+ field+ " FROM "+ tableName+ condn+";");
		return query.toString();
	}
	
	public static String joinQuery(String condn)
	{
		StringBuilder query = new StringBuilder();
		query.append("SELECT "+condn+";");
		return query.toString();
		
	}	
	
}

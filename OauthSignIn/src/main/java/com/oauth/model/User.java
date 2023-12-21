//$Id$
package com.oauth.model;

import java.lang.reflect.*;
import java.util.Arrays;
//import org.json.*;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
		
		private int user_id;
		private String name;
		private String email_id;
		private int age;
		
		public int getUser_id() {
			return user_id;
		}
		
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}


		public User()
		{
		}
		public User(String email_id,String name,int age)
		{
				this.setEmail_id(email_id);
				this.setName(name);
				this.setAge(age);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail_id() {
			return email_id;
		}

		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
		
		public Object[] getDetails()
		{
			Object[] details = {this.getName(),this.getEmail_id(),this.getAge()};
			return details;
		}
		
		public static String[] returnDetails()
		{
			return Arrays.stream(User.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
			
		}
		
}

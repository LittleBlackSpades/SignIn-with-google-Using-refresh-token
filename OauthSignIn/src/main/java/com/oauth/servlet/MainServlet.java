//$Id$
package com.oauth.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.stream.Collectors;



public class MainServlet  extends HttpServlet {


		protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
		{	
			String token = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			try {
				GoogleApiServlet.getUserDetails(token);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

}

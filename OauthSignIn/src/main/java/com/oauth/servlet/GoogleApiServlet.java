
package com.oauth.servlet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.oauth.model.User;

public class GoogleApiServlet {
	
	
    private static final String USER_INFO_ENDPOINT = "https://www.googleapis.com/oauth2/v1/userinfo";

	
	static void getUserDetails(String token) throws IOException, JSONException {
		JSONObject json = new JSONObject(token);
		String accessToken = json.getString("access_token");
		String refreshToken = json.getString("refresh_token");
		boolean expiresIn = json.getBoolean("expires_in");
		HttpClient httpClient = HttpClient.newBuilder().build();
		if(expiresIn) {
			 Map<String, String> data = new HashMap<>();
		        data.put("grant_type", "refresh_token");
		        data.put("client_id", "986853611746-2f92eqbkr7p35o9nr8uk885gq69b1dvk.apps.googleusercontent.com");
		        data.put("client_secret", "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");
		        data.put("refresh_token", refreshToken);
			  HttpRequest request = HttpRequest.newBuilder()
		                .uri(URI.create("https://accounts.google.com/o/oauth2/token"))
		                .header("Content-Type", "application/x-www-form-urlencoded")
		                .POST(BodyPublishers.ofString( buildFormDataFromMap(data)))
		                .build();
			  try {
		            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		            if (response.statusCode() == 200) {
		            	JSONObject jsonResponse = new JSONObject(response.body());
		            	accessToken = jsonResponse.getString("access_token");
		                System.out.println("Access Token Response:");
		                System.out.println(response.body());
		            } else {
		                System.err.println("Failed to obtain access token. Status code: " + response.statusCode());
		                System.err.println("Response body: " + response.body());
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		}
        HttpURLConnection connection = (HttpURLConnection) new URL(USER_INFO_ENDPOINT).openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String jsonResponse = response.toString();
          //  User user = new user();
           System.out.println(jsonResponse);
        }
	}
	private static String buildFormDataFromMap(Map<String, String> data) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return builder.toString();
    }
	
	
}

package impl;

import java.io.*;
import java.util.*;

import service.FirebaseService;

public class User {
	public static String username;
	public static final String GROUP_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODQ3MTEyNDQsInYiOjAsImlhdCI6MTU1NDM3MDM3MSwiZCI6eyJ1aWQiOiJkYTBhYTZkMy02M2JkLTQxM2EtOGQyYS04YWYxOTA3N2YzNTYifX0.4Qw3u05K-sdKsR4ERGOHiobOlClrKAq00Ntg0sllMYU";
	public static final String GROUP_NAME = "Donald Knuth";

	public static String getUsername() {
		return username;
	}
	
	public int checkCredentials(String usernameToCheck, String passwordToCheck)  {
		List<List<String>> records = new ArrayList<>();
		String filePath = System.getProperty("user.dir") + "\\src\\resources\\wachtwoorden.csv";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    System.out.println(usernameToCheck);
		    System.out.println(passwordToCheck);
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		        records.add(Arrays.asList(values));
		        System.out.println(values[1].trim() + ":" + values[2].trim());
		        
		        if (usernameToCheck.equals(values[1].trim()) && passwordToCheck.equals(values[2].trim())) {
		        	this.username = usernameToCheck;
		        	System.out.println("succes");
		        	return 0; //success
		        }
		    }
		   
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return 1; //user not found
	}
	
	
	public static void setScore(int score, String username) throws Exception {
		FirebaseService firebaseService = new FirebaseService();
		firebaseService.saveHighscore(GROUP_NAME, GROUP_TOKEN, score, username);
	}
}
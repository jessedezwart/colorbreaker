package impl;

import java.io.*;
import java.util.*;

public class User {
	public String username;
	public int highscore;

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
}
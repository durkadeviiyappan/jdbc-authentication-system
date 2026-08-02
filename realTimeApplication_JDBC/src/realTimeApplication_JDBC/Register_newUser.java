package realTimeApplication_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import exceptionCollections.AccountAlreadyExistsException;
import exceptionCollections.DuplicateMailIdException;
import exceptionCollections.DuplicatePhoneNumberException;
import java.util.Scanner;

public class Register_newUser {
	
	private String userName;
	private String email;
	private String phone_no;
	private String password;
	
   public Register_newUser(String userName , String email , String phone_no , String password) {
	   this.userName = userName;
	   this.email = email;
	   this.phone_no = phone_no;
	   this.password = password;
	   
	   Scanner sc = new Scanner(System.in);
	   
	   
	   Connection con = DBConnection.getConnection() ;
	     
	    try {
	    	 //step1 : check if the email or phone number already exists in the dataBase
	        String query1 = "select * from user where email = ? or phone_no = ? ";
	        
	        PreparedStatement p1 = con.prepareStatement(query1);
	        p1.setString(1, email);
	        p1.setString(2, phone_no);
	        
	           ResultSet rs = p1.executeQuery();
	           
	           if(!rs.next()) {
	        	   int OTP = OTPGenerationAndVerificationProcess.OTPGeneration();
	        	   System.out.println("OTP : "+OTP);
	        	   
	        	  System.out.print("Enter OTP : ");
	        	  
	        	  if(sc.nextInt() == OTP) {
	        		  String insert = "insert into user values(?,?,?,?) ";
	        		  PreparedStatement p2 = con.prepareStatement(insert);
	        		  p2.setString(1, userName);
	        		  p2.setString(2, email);
	        		  p2.setString(3, phone_no);
	        		  p2.setString(4, password);
	        		  
	        		  int row = p2.executeUpdate();
	        		  
	        		  if(row >= 1) {
	        			  System.out.println("Successfully Registered !!");
	        			  System.out.println("Welcome "+userName);
	        		  }
	        		  else {
	        			  System.out.println("Registeration Failed");
	        			  System.out.println("Try Again!");
	        		  }
	        		  
	        		  //close p2 resource
	        		  p2.close();
	        	  }
	           }
	           else {
	        	if(rs.getString("email").equals(email) && rs.getString("phone_no").equals(phone_no)) {
	        		 throw new AccountAlreadyExistsException("Account already exists");
	        		 }
	        	else if(rs.getString("email").equals(email)) {
	        		throw new DuplicateMailIdException("Duplicate mail_id exists");
	        	}
	        	else {
	        		throw new DuplicatePhoneNumberException("Duplicate phone_no exists");
	        	}
	           }
	           
	           //close connection and p1 resource
	           rs.close();
	           p1.close();
	           con.close();
	       } 
	    
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
	     catch(AccountAlreadyExistsException e) {
			e.printStackTrace();
		}
	    catch(DuplicateMailIdException e) {
	    	e.printStackTrace();
	    }
	    catch(DuplicatePhoneNumberException e) {
	    	e.printStackTrace();
	    }
	   
   }
   
   
}

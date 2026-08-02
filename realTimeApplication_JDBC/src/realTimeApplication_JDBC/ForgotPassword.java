package realTimeApplication_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import exceptionCollections.InvalidCredentialsException;
import exceptionCollections.InvalidOTPException;
import exceptionCollections.PasswordMismatchException;
import exceptionCollections.PasswordResetFaildException;
import java.util.Scanner;

public class ForgotPassword {
	
	 private String userInput ;
	 
	public ForgotPassword(String userInput){
		this.userInput = userInput;
		
		Scanner sc = new Scanner(System.in);
		
		Connection con = DBConnection.getConnection();
		
		try {
		//step => check the given userInput presnt in DB or not
		String query1 = "select * from user where email = ? or phone_no = ? ";
		PreparedStatement p1 = con.prepareStatement(query1);
		
		p1.setString(1,userInput);
		p1.setString(2, userInput);
		
		ResultSet rs = p1.executeQuery();
		
		if(!rs.next()) {
			throw new InvalidCredentialsException("Invalid credentials");
		}
		else {
			int OTP = OTPGenerationAndVerificationProcess.OTPGeneration();
			System.out.println("OTP : "+OTP);
			System.out.print("Enter OTP : ");
			
			if(OTP == sc.nextInt()) {
			
				String query2 = "update user set password = ? where email = ? or phone_no = ?";
				PreparedStatement p2 = con.prepareStatement(query2);
			   System.out.print("Enter password :  ");
			   String password = sc.next();
			   
			   System.out.println("confirm password : ");
			   String confirmPassword = sc.next();
			   
			   if(password.equals(confirmPassword)) {
				   p2.setString(1, confirmPassword);
				   p2.setString(2, userInput);
				   p2.setString(3, userInput);
				   
				   int row = p2.executeUpdate();
				   if(row == 1) {
				   System.out.println("Reset Password successfully done");
				   }
				   else {
					   throw new PasswordResetFaildException("Password reset was unsuccessfull . Please try again! ");
				   }
			   }
			   else {
				   throw new PasswordMismatchException("Password and Confirm password do not match");
			   }
			   
			   //close p2 resource
			   p2.close();
			}
			else {
				throw new InvalidOTPException("Invalid OTP");
			}
		}
		
		//close p1 and connection resource
	    p1.close();
	    con.close();
		
		}
	
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(InvalidCredentialsException e) {
			e.printStackTrace();
		}
		catch(InvalidOTPException e) {
			e.printStackTrace();
		}
		catch(PasswordMismatchException e) {
			e.printStackTrace();
		}
		catch(PasswordResetFaildException e) {
			e.printStackTrace();
		}
		
		
	}

}

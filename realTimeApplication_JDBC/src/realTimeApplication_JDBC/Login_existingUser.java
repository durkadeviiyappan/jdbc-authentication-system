package realTimeApplication_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import exceptionCollections.UserNotFoundException;
import exceptionCollections.InvalidPasswordException;

public class Login_existingUser {
	
	private String userInput;
	private String password;
	
	public Login_existingUser(String userInput , String password) {
		this.userInput = userInput ;
		this.password = password;
		
		Connection con = DBConnection.getConnection();
		
		try {
		//step1 : check whether email/phone exists in the database
		String query1 = "select * from user where email = ? or phone_no = ? ";
		PreparedStatement p1 = con.prepareStatement(query1);
		p1.setString(1,userInput);
		p1.setString(2,userInput);
		
		ResultSet rs = p1.executeQuery();
		if(!rs.next()) {
			throw new UserNotFoundException("your credentials not found");
		}
		//otherwise the record present into the DB , so now check password is correct or not
		else {
			if(rs.getString("password").equals(password)) {
				System.out.println("Successfully Login");
				System.out.println("Welcome back  "+rs.getString("userName")+"  🤗🤗🤗🤗🤗🤗🤗🤗🤗");
			}
			else {
				throw new InvalidPasswordException("Incorrect password");
			}
		}
		//close connection resource and p1 resource
		        rs.close();
		        p1.close();
				con.close();
				
	}
		
		
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(UserNotFoundException e) {
			e.printStackTrace();
		}
		catch(InvalidPasswordException e) {
			e.printStackTrace();
		}
	}

}

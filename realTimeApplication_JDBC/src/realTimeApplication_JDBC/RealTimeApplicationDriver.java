package realTimeApplication_JDBC;

import java.util.Scanner;

import exceptionCollections.InvalidOperationException;


public class RealTimeApplicationDriver {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String userName;
		String email;
		String phone_no;
		String password;
		
		
		while(true) {
			
			System.out.println(" *Register -> press(1) \n *Login -> press(2) \n *Forgot Password -> press(3) \n *Exit -> press(4)");
			int operation = sc.nextInt();
			
			
		switch(operation) {
		case 1 :
			System.out.print("Enter userName : ");
			userName = sc.next();
			
			System.out.print("Enter email : ");
			email = sc.next();
			
			System.out.print("Enter phone_no : ");
			phone_no = sc.next();
			
			System.out.print("Enter password : ");
			password = sc.next();
			
			Register_newUser r = new Register_newUser(userName , email ,phone_no , password);
			
			break;
		
		case 2 :
			System.out.print("Enter email or phone_no  : ");
			email = sc.next();
			
			System.out.print("Enter password : ");
			password = sc.next();
			
			Login_existingUser l = new Login_existingUser(email,password);
			
			break;
			
		case 3 :
			System.out.print("Enter email or phone_no : ");
			String userInput = sc.next();
			
			ForgotPassword f = new ForgotPassword(userInput);
			
			break;
			
		case 4 : 
			System.out.println("Thanking you 🤗🤗🤗🤗🤗🤗🤗🤗🤗");
			return;
			
		default : 
			try{
				throw new InvalidOperationException("Invalid Operation Exception");
			}
			catch(InvalidOperationException i) {
		         System.out.println(i.getMessage());
	         }
	      }
		
		System.out.println("------------------------------------------------------------------------------------");
		}
	}

}

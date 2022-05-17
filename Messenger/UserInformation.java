package com.messager;

import java.util.*;

public class UserInformation{
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private static Boolean loginDetails ;
	
	protected static final String userNameValidation(final String userName) {
		String userNamePattern = "^[a-zA-Z0-9\\s]{8,20}$";
		
		loginDetails = userName.matches(userNamePattern);
		
		if (loginDetails) {
			System.out.println(" ");
		} else {
			System.out.println("Username Invalid Format");
			LoginPage.assignUserName();
		}
		return userName;
	}
	
	protected static final String passwordValidation(final String password) {
		String passwordPattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		
		loginDetails = password.matches(passwordPattern);
		
		if (loginDetails) {
			System.out.println(" ");
		} else {
			System.out.println("Password In Invalid Format");
			LoginPage.assignPassword();
		}
		return password;
	}
	
	protected static final String emailValidation(final String email) {
		String emailPattern = "^[a-z0-9._]+@[a-z.]+$";
		
		loginDetails = email.matches(emailPattern);
		
		if (loginDetails) {
			System.out.println("\n");
		} else {
			System.out.println("Email ID is Invalid Format So kindly Retry");
			ContactPage.assignEmail();
		}
		return email;
	}
	
	protected static final String mobilenumberValidation(final String mobilenumber) {
		String mobileNumberPattern = "[6-9][0-9]{9}";
		
		Boolean loginDetails = mobilenumber.matches(mobileNumberPattern);
		
		if (loginDetails) {
			System.out.println("\n");
		} else {
			System.out.println("-----MobileNumber Must contain 10 characters:---------------------------------------------------");
			ContactPage.assignPhoneNumber();
		}
		return mobilenumber;
	}
}
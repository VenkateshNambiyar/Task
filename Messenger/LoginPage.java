package com.messager;

import java.util.*;
import java.io.*;

public class LoginPage {
	private static final Properties ACCOUNTDETAILS = new Properties();
	
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private static String userName;
	private static String password;
	private static String userLogin;
	private static String userPassword;
	
	protected static final void loadFile() {
		try {
			FileInputStream loginDetails = new FileInputStream("Login.txt");
			
			ACCOUNTDETAILS.load(loginDetails);
		} catch (Exception e ) {
			System.out.println("------FILE::NOT::FOUND-----");
		}
	}
	
	protected static final void storeFile() {
		try {
			FileOutputStream storeUserDetails = new FileOutputStream("Login.txt");
			
			ACCOUNTDETAILS.store(storeUserDetails,"New user Added Succesfully");
		} catch (Exception fileNotFound ) {
			System.out.println("------FILE::NOT::FOUND-----");
		}
	}
	
	protected static String assignUserName() {				
		System.out.print("Enter your username : ");
		userName = SCANNER.nextLine();
		
		UserInformation.userNameValidation(userName);
		return userName;
	}
	
	protected static String assignPassword() {		
		System.out.print("Enter your Password : ");
		password = SCANNER.nextLine();
	
		UserInformation.passwordValidation(password);
		return password;
	}	
	
	protected static String userLoginDetails() {
		assignUserName();
		userLogin = String.valueOf(UserInformation.userNameValidation(userName));
		return userName;
	}
	
	protected static void userPasswordDetails() {
		assignPassword();		
		userPassword = String.valueOf(UserInformation.passwordValidation(password));
	}
	
	protected static final void signup() {
		try {
			signupDetails();
		} catch (Exception userNotFound) {
			System.out.println("----I--N--V--A--L--I--D----");
		}
	}
	
	private static void signupDetails() {
		loadFile();
		userLoginDetails();
		userPasswordDetails();
		boolean userID = ACCOUNTDETAILS.containsKey(userLogin);
		
		if (userID) {
			System.out.println("UserName Exists");
			signin();
		} else {
			ACCOUNTDETAILS.setProperty(userLogin,userPassword);
			LoginPage.storeFile();
			System.out.println("Succesfully Account Created");
			MainClass.contactPage();
		}
	}
	
	protected static final void signin() {
		try {
			signinDetails();
		} catch (Exception userNotFound) {
			System.out.println("----I--N--V--A--L--I--D----");
		}
	}
	
	protected static final void signinDetails() {
		loadFile();
		userLoginDetails();
		userPasswordDetails();
		String userInformation = ACCOUNTDETAILS.getProperty(userLogin);
		
		if (userInformation.equals(userPassword)) {
			System.out.println("Successful Login...." + userLogin);
			MainClass.contactPage();
		} else {
			System.out.println("----I--N--V--A--L--I--D----");
			signin();
		}
	}
	
	protected static final void forgotPassword() {
		try {
			passwordDetails();
		} catch (Exception userNotFound) {
			System.out.println("----I--N--V--A--L--I--D----");
		}
	}
	
	protected static final void passwordDetails() {
		loadFile();
		userLoginDetails();		
		userPasswordDetails();
		boolean userid = ACCOUNTDETAILS.containsKey(userLogin);
		
		if (userid) {		
			ACCOUNTDETAILS.put(userLogin,userPassword);
			storeFile();
			System.out.println("Succesfully Password Updated");
			MainClass.homePage();
		} else {
			System.out.println("Invalid UserID");
			forgotPassword();
		}
	}
}	
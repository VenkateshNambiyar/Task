package com.messager;

import java.util.*;

public class MainClass {
	private static final Scanner SCANNER = new Scanner(System.in);
	
	public static void homePage() {		
		System.out.println(" Welcome to Messager ");
		System.out.println(" 1)Login\t   2)New Account\t  3)Forgot Password  ");
		System.out.print("Select AnyOne : \t");
		String selectOperation = SCANNER.nextLine();
		
		String LoginSelection = "[123]";
		
		Boolean loginDetails = selectOperation.matches(LoginSelection);
		
		if(loginDetails) {
			
			switch (selectOperation) {
			case "1":
				LoginPage.signin();
				break;
			case "2":
				LoginPage.signup();
				break;	
			case "3":
				LoginPage.forgotPassword();
				break;
			default:
				System.out.print("Welcome to Messager");
				break;
			}
		} else {
			System.out.println("Invalid Select");
			homePage();
		}
	}
	
	public static void contactPage() {		
		System.out.println(" 1)Show Contact\t   2)Add New Contact\t  3)ChatBox");
		System.out.print("Select AnyOne : \t");
		String contactOperation = SCANNER.nextLine();
		
		String contactSelection = "[123]";
		
		Boolean contactDetails = contactOperation.matches(contactSelection);
		
		if(contactDetails) {
			
			switch (contactOperation) {
			case "1":
				ContactPage.displayContact();
				break;
			case "2":
				ContactPage.addNewContact();
				break;	
			case "3":
				ChatBox.messageBox();
				break;
			default:
				System.out.print("Invalid Selection. Retry");
				break;
			}
		} else {
			System.out.println("Invalid Select");
			contactPage();
		}
	}
	
	public static void main(String[] args) {
		MainClass chatapplication = new MainClass();
		
		chatapplication.homePage();
	}
}
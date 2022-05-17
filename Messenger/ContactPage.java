package com.messager;

import java.util.*;
import java.io.*;

public class ContactPage {
	private static final Properties CONTACT_DETAILS = new Properties();
	
	private static final HashMap<String,String> NEW_CONTACT = new HashMap<String, String>();
	
	private static final Map<String,Map<String,String>> OLD_CONTACT = new HashMap<String,Map<String,String>>();
	
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private static String phoneNumber;
	private static String email;
	private static String userMobileNumber;
	private static String userEmail;
	
	protected static final void contactLoadFile() {
		try {
			FileInputStream userContact = new FileInputStream("Contact.txt");
			
			CONTACT_DETAILS.load(userContact);
		} catch (Exception fileNotFound) {
			System.out.println("------FILE::NOT::FOUND-----");
		}
	}
	
	protected static final void contactStoreFile() {
		try {
			FileOutputStream storeContactDetails = new FileOutputStream("Contact.txt");
			
			CONTACT_DETAILS.store(storeContactDetails,"New user Added Succesfully");
		} catch (Exception fileNotFound ) {
			System.out.println("------FILE::NOT::FOUND-----");
		}
	}
	
	protected static String assignPhoneNumber() {				
		System.out.print("Enter your phone Number : ");
		phoneNumber = SCANNER.nextLine();
		
		UserInformation.mobilenumberValidation(phoneNumber);
		return phoneNumber;
	}
	
	protected static String assignEmail() {		
		System.out.print("Enter your Email : ");
		email = SCANNER.nextLine();
	
		UserInformation.emailValidation(email);
		return email;
	}	
	
	protected static void userContactDetails() {
		assignPhoneNumber();
		userMobileNumber = String.valueOf(UserInformation.mobilenumberValidation(phoneNumber));
	}
	
	protected static void userEmailDetails() {
		assignEmail();
		userEmail = String.valueOf(UserInformation.emailValidation(email));
	}
	
	protected static final void displayContact() {
		try {
			contactList();
			MainClass.contactPage();
		} catch (Exception userNotFound) {
			System.out.println("userNotFound");
			displayContact();
		}
	}
	
	private static final void contactList() {
		contactLoadFile();
		userEmailDetails();
		boolean email_Information = CONTACT_DETAILS.containsKey(userEmail);
		
		if (email_Information) {
			String[] persondetails = CONTACT_DETAILS.getProperty(userEmail).split(",");
			
			for (String displayContactDetails : persondetails) {		
				System.out.println(displayContactDetails);
			}
		} else {
			System.out.println("User Not Found");
			displayContact();
		}
	}
	
	protected static final void addUserContactDetails(){
		contactLoadFile();
		userContactDetails();
		String userLogin = String.valueOf(LoginPage.userLoginDetails());
		
		NEW_CONTACT.put(phoneNumber,userLogin);
		OLD_CONTACT.put(CONTACT_DETAILS.getProperty(userEmail),NEW_CONTACT);
		String userContactProfile = OLD_CONTACT.toString();

		CONTACT_DETAILS.setProperty(userEmail,userContactProfile);
		contactStoreFile();
		MainClass.contactPage();
	}
	
	protected static final void addNewContact() {
		try {
			contactLoadFile();
			userEmailDetails();			
			boolean email_Id = CONTACT_DETAILS.containsKey(userEmail);
		
			if (email_Id) {
				addUserContactDetails();
				MainClass.homePage();
			} else {
				addNewContact();
			}
		} catch (Exception userNotFound) {
			System.out.println("userNotFound");
			addNewContact();
		}
	}
}
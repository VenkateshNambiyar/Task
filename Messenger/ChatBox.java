package com.messager;

import java.sql.Timestamp;
import java.util.*;

public class ChatBox {
	private static final Scanner SCANNER = new Scanner(System.in);
	
	private static final Map<String,Map<String,Set<String>>> CLOCK_TIME = new HashMap<String,Map<String,Set<String>>>();
	
	protected static String displayTimeStamp() {
		Date date = new Date();
		
		long time = date.getTime();
		
		Timestamp dateTime = new Timestamp(time);
		
		String displayTime = dateTime.toString(); 
		
		return displayTime; 
	}
	
	protected static final void messageBox() {
		try {
			chatapplication();
		} catch (Exception chatbox) {
			System.out.println("Retry");
			messageBox();
		}
	}
	
	private static void chatapplication() {
		Set<String> message = new TreeSet<String>();
		System.out.print("Enter Your Message :\t");
		message.add(SCANNER.nextLine());
		
		Map<String,Set<String>> receiver_Name = new HashMap<String,Set<String>>();
		System.out.print("Enter your Reciever Name:\t");
		receiver_Name.put(SCANNER.nextLine(),message);
		
		String displayDateTime = String.valueOf(displayTimeStamp());
	 
		CLOCK_TIME.put(displayDateTime,receiver_Name);
		chatList();
	}
	
    private static void chatList() {
		Scanner userChat = new Scanner(System.in);
		final String yes = "y";
		
		System.out.println("You want to continue to message : YES(y) OR NO(n)"); 
		System.out.print("Enter YOur Option:" );
		String newUser = userChat.nextLine();
		
		if(newUser.equals(yes)) {
			messageBox();
		} else {
			System.out.println("Thank You");
			System.out.println(CLOCK_TIME);
			MainClass.homePage();
		}
	}
}
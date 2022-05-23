package messager.view;

import messager.service.ChatBox;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.sql.Timestamp;

public class LoginPage {
    private static final Properties ACCOUNT_DETAILS = new Properties();

    private static final Properties CONTACT_DETAILS = new Properties();

    private static final Map<String,String> NEW_CONTACT = new HashMap<>();

    private static final Map<String, Map<String,String>> OLD_CONTACT = new HashMap<>();

    private static final Map<String,Map<String,Set<String>>>  MESSAGE_TIMING_DETAILS = new HashMap<>();
    private static void loadFile() {
        try {
            FileInputStream loginDetails = new FileInputStream("Login.txt");

            ACCOUNT_DETAILS.load(loginDetails);
        } catch (Exception e ) {
            System.out.println("------FILE::NOT::FOUND-----");
        }
    }

    private static void storeFile() {
        try {
            FileOutputStream storeUserDetails = new FileOutputStream("Login.txt");

            ACCOUNT_DETAILS.store(storeUserDetails,"New user Added Successfully");
        } catch (Exception exception) {
            System.out.println("------FILE::NOT::FOUND-----");
        }
    }

    private static void contactLoadFile() {
        try {
            FileInputStream userContact = new FileInputStream("Contact.txt");

            CONTACT_DETAILS.load(userContact);
        } catch (Exception fileNotFound) {
            System.out.println("------FILE::NOT::FOUND-----");
        }
    }

    private static void contactStoreFile() {
        try {
            FileOutputStream storeContactDetails = new FileOutputStream("Contact.txt");

            CONTACT_DETAILS.store(storeContactDetails,"New user Added Successfully");
        } catch (Exception fileNotFound ) {
            System.out.println("------FILE::NOT::FOUND-----");
        }
    }

    public void newUser(final String userId,final String userPassword) {
        try {
            loadFile();
            boolean userID = ACCOUNT_DETAILS.containsKey(userId);

            if (userID) {
                System.out.println("UserName Exists");
                ChatBox chatBox = new ChatBox();

                chatBox.signUp();
            } else {
                ACCOUNT_DETAILS.setProperty(userId, userPassword);
                storeFile();
                System.out.println("Successfully Account Created");
                ChatBox chatBox = new ChatBox();

                chatBox.contactOperation();
            }
        } catch (Exception exception) {
            System.out.println("Retry");
        }
    }

    public void oldUser(final String userId,final String userPassword) {
        try {
            loadFile();
            String userInformation = ACCOUNT_DETAILS.getProperty(userId);

            if (userInformation.equals(userPassword)) {
                System.out.println("Successful Login...." + userId);
                ChatBox chatBox = new ChatBox();

                chatBox.contactOperation();
            } else {
                System.out.println("----I--N--V--A--L--I--D----");
                ChatBox chatBox = new ChatBox();

                chatBox.signIn();
            }
        } catch (Exception e) {
            System.out.println("------::INVALID::-----");
            ChatBox chatBox = new ChatBox();

            chatBox.loginOperation();
        }
    }

    public void forgotPassword(final String userId,final String userPassword) {
        loadFile();
        boolean userid = ACCOUNT_DETAILS.containsKey(userId);

        if (userid) {
            ACCOUNT_DETAILS.put(userId,userPassword);
            storeFile();
            System.out.println("Successfully Password Updated");
            ChatBox chatBox = new ChatBox();

            chatBox.loginOperation();
        } else {
            System.out.println("Invalid UserID");
            ChatBox chatBox = new ChatBox();

            chatBox.forgotPassword();
        }
    }

    public void displayUserContactDetails(final String email) {
        contactLoadFile();
        boolean email_Information = CONTACT_DETAILS.containsKey(email);

        if (email_Information) {
            String[] userDetails = CONTACT_DETAILS.getProperty(email).split(",");

            for (String displayContactDetails : userDetails) {
                System.out.println(displayContactDetails);
            }
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        } else {
            System.out.println("User Not Found");
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        }
    }

    public void addNewContact(final String mobileNumber, final String personName,final String email){
        contactLoadFile();
        boolean email_Id = CONTACT_DETAILS.containsKey(email);

        if (email_Id) {
            NEW_CONTACT.put(mobileNumber,personName);
            OLD_CONTACT.put(CONTACT_DETAILS.getProperty(email),NEW_CONTACT);
            String userContactProfile = OLD_CONTACT.toString();

            CONTACT_DETAILS.setProperty(email,userContactProfile);
            contactStoreFile();
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        } else {
            NEW_CONTACT.put(mobileNumber,personName);
            String userContactProfile = NEW_CONTACT.toString();

            CONTACT_DETAILS.setProperty(email,userContactProfile);
            contactStoreFile();
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        }
    }

    private static String displayTimeStamp() {
        String displayTime;
        Date date = new Date();

        long time = date.getTime();

        Timestamp dateTime = new Timestamp(time);

        displayTime = dateTime.toString();

        return displayTime;
    }

    public void messageBox(final String textMessage, final String userName,final String email) {
        boolean email_Information = CONTACT_DETAILS.containsKey(email);

        if (email_Information) {
            Set<String> set = new TreeSet<>();

            set.add(textMessage);
            Map<String,Set<String>> receiver_Name = new HashMap<>();

            receiver_Name.put(userName,set);
            String displayDateTime = String.valueOf(displayTimeStamp());

            MESSAGE_TIMING_DETAILS.put(displayDateTime,receiver_Name);
            chatList();
        } else {
            System.out.println("User Not Found");
            ChatBox chatBox = new ChatBox();

            chatBox.messageBox();
        }
    }

    private static void chatList() {
        Scanner userChat = new Scanner(System.in);

        final String yes = "y";

        System.out.println("You want to continue to message : YES(y) OR NO(n)");
        System.out.print("Enter YOur Option:" );
        String newUser = userChat.nextLine();

        if(newUser.equals(yes)) {
            ChatBox chatBox = new ChatBox();

            chatBox.messageBox();
        } else {
            System.out.println("Thank You");
            System.out.println(MESSAGE_TIMING_DETAILS);
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        }
    }
}
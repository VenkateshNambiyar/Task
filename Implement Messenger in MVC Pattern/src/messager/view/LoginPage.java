package messager.view;

import messager.service.ChatBox;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class LoginPage {
    private static final Properties ACCOUNT_DETAILS = new Properties();

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
}
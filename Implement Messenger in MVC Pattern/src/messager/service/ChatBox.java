package messager.service;

import messager.controller.Messenger;
import messager.model.ContactInformation;
import messager.model.LoginInformation;
import messager.model.MessageInformation;
import messager.view.ContactPage;
import messager.view.LoginPage;
import messager.view.MessagePage;

import java.util.Scanner;

public class ChatBox implements ChatApplication {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final LoginInformation LOGIN_INFORMATION = new LoginInformation();

    private static final LoginPage LOGIN_PAGE = new LoginPage();
    private static final ContactInformation CONTACT_INFORMATION = new ContactInformation();
    private static final ContactPage CONTACT_PAGE = new ContactPage();
    private static final MessageInformation MESSAGE_INFORMATION = new MessageInformation();
    private static final MessagePage MESSAGE_PAGE = new MessagePage();

    private static final Messenger MESSENGER = new Messenger(LOGIN_INFORMATION,LOGIN_PAGE,CONTACT_INFORMATION,CONTACT_PAGE,MESSAGE_PAGE,MESSAGE_INFORMATION);

    public static void main(String[] args) {
        try {
            ChatBox chatBox = new ChatBox();
            chatBox.loginOperation();
        } catch (Exception exception) {
            System.out.println("Exception :"+exception);
        }
    }

    public void userDetails() {
        assignUserName();
        assignPassword();
    }

    public void signUp() {
        userDetails();
        MESSENGER.accountSignUp();
    }

    public void signIn() {
        userDetails();
        MESSENGER.accountSignIn();
    }

    public void forgotPassword() {
        userDetails();
        MESSENGER.accountForgotPassword();
    }

    public void displayContact() {
        assignEmail();
        MESSENGER.userContact();
    }

    public void addNewContact() {
        assignEmail();
        assignMobileNumber();
        assignUserName();
        MESSENGER.addUserContact();
    }

    public void messageBox() {
        assignTextMessage();
        assignProfileId();
        MESSENGER.messenger();
    }

    public void oldUserMessage() {
        assignTextMessage();
        MESSENGER.messageExistingUser();
    }

    public void loginOperation() {
        System.out.println("Select Anyone Operation : 1)SignIn \t 2)SignUp \t 3)ForgotPassword\t");
        System.out.print("Select Option : \t");
        String selectOperation = SCANNER.nextLine();

        String loginSelection = "[123]";

        boolean loginDetails = selectOperation.matches(loginSelection);

        if (loginDetails) {
            switch (selectOperation) {
            case "1" -> signIn();
            case "2" -> signUp();
            case "3" -> forgotPassword();
            default -> System.out.println("----I--N--V--A--L--I--D----");
            }
        } else {
            System.out.println("Invalid Selection");
            loginOperation();
        }
    }

    public void contactOperation() {
        System.out.println("Select Anyone Operations : 1)Show Contacts \t 2)Add new Contact \t 3)ChatBox\t 4)Logout");
        System.out.print("Select Option : \t");
        String selectOperation = SCANNER.nextLine();

        String contactSelection = "[1-4]";

        boolean contactDetails = selectOperation.matches(contactSelection);

        if (contactDetails) {
            switch (selectOperation) {
            case "1" -> displayContact();
            case "2" -> addNewContact();
            case "3" -> messageBox();
            case "4" -> loginOperation();
            default -> System.out.println("----I--N--V--A--L--I--D----");
            }
        } else {
            System.out.println("Invalid Selection");
            contactOperation();
        }
    }

    public void assignUserName() {
        System.out.print("Enter Your UserName : \t");
        String username = SCANNER.nextLine();

        String userNamePattern = "^[a-zA-Z\\d\\s]{8,20}$";

        boolean loginDetails = username.matches(userNamePattern);

        if (loginDetails) {
            LOGIN_INFORMATION.setUserName(username);
        } else {
            System.out.println("-----UserName must contain 8 characters:--------");
            assignUserName();
        }
    }

    public void assignPassword() {
        System.out.print("Enter Your Password : \t");
        String password = SCANNER.nextLine();

        String passwordPattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

        boolean loginDetails = password.matches(passwordPattern);

        if (loginDetails) {
            LOGIN_INFORMATION.setPassword(password);
        } else {
            System.out.println("Password In Invalid Format");
            assignPassword();
        }
    }

    public void assignEmail() {
        System.out.print("Enter Your Email : \t");
        String email = SCANNER.nextLine();

        String emailPattern = "^[a-z\\d._]+@[a-z.]+$";

        boolean contactDetails = email.matches(emailPattern);

        if (contactDetails) {
            CONTACT_INFORMATION.setEmail(email);
        } else {
            System.out.println("Email ID is Invalid Format So kindly Retry");
            assignEmail();
        }
    }

    public void assignMobileNumber() {
        System.out.print("Enter Your MobileNumber : \t");
        String mobileNumber = SCANNER.nextLine();

        String mobileNumberPattern = "[6-9][0-9]{9}";

        boolean contactDetails = mobileNumber.matches(mobileNumberPattern);

        if (contactDetails) {
            CONTACT_INFORMATION.setMobileNumber(mobileNumber);
        } else {
            System.out.println("-----MobileNumber Must contain 10 characters:--------");
            assignMobileNumber();
        }
    }

    public void assignTextMessage() {
        System.out.print("Enter Your textMessage : \t");
        MESSAGE_INFORMATION.setTextMessage(SCANNER.nextLine());
    }

    public void assignProfileId() {
        System.out.print("Enter userProfileId : \t");
        String contactProfile = SCANNER.nextLine();

        String contactProfilePattern = "(?:[0-9]|[0-9]{2}|[0-9]{3})";

        boolean contactDetails = contactProfile.matches(contactProfilePattern);

        if (contactDetails) {
            MESSAGE_INFORMATION.setUserProfileId(contactProfile);
        } else {
            System.out.println("-----ProfileId only contain 1-3 characters:--------");
            assignProfileId();
        }
    }
}
package messager.service;

import messager.controller.LoginCredentials;
import messager.model.UserInformation;
import messager.view.LoginPage;

import java.util.Scanner;

public class ChatBox implements ChatApplication {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final UserInformation MODEL = new UserInformation();

    private static final LoginPage VIEW = new LoginPage();

    private static final LoginCredentials LOGIN_CREDENTIALS = new LoginCredentials(MODEL, VIEW);

    public static void main(String[] args) {
        try {
            ChatBox chatBox = new ChatBox();
            chatBox.loginOperation();
        } catch (Exception exception) {
            System.out.println();
        }
    }

    public void userDetails() {
        assignUserName();
        assignPassword();
    }

    public void signUp() {
        userDetails();
        LOGIN_CREDENTIALS.accountSignUp();
    }

    public void signIn() {
        userDetails();
        LOGIN_CREDENTIALS.accountSignIn();
    }

    public void forgotPassword() {
        userDetails();
        LOGIN_CREDENTIALS.accountForgotPassword();
    }

    public void displayContact() {
        assignEmail();
        LOGIN_CREDENTIALS.userContact();
    }

    public void addNewContact() {
        assignEmail();
        assignMobileNumber();
        assignUserName();
        LOGIN_CREDENTIALS.addUserContact();
    }

    public void messageBox() {
        assignTextMessage();
        assignUserName();
        assignEmail();
        LOGIN_CREDENTIALS.messenger();
    }

    public void loginOperation() {
        System.out.println("Select Anyone Operations : 1)SignIn \t 2)SignUp \t 3)ForgotPassword\t");
        System.out.print("Select Option : \t");
        String selectOperation = SCANNER.nextLine();

        String loginSelection = "[123]";

        boolean loginDetails = selectOperation.matches(loginSelection);

        if (loginDetails) {
            switch (selectOperation) {
            case "1":
                signIn();
                break;
            case "2":
                signUp();
                break;
            case "3":
                forgotPassword();
                break;
            default:
                System.out.println("----I--N--V--A--L--I--D----");
            }
        } else {
            System.out.println("Invalid Selection");
            loginOperation();
        }
    }

    public void contactOperation() {
        System.out.println("Select Anyone Operations : 1)Show Contacts \t 2)Add new Contact \t 3)ChatBox\t");
        System.out.print("Select Option : \t");
        String selectOperation = SCANNER.nextLine();

        String contactSelection = "[123]";

        boolean contactDetails = selectOperation.matches(contactSelection);

        if (contactDetails) {
            switch (selectOperation) {
            case "1":
                displayContact();
                break;
            case "2":
                addNewContact();
                break;
            case "3":
                messageBox();
                forgotPassword();
                break;
            default:
                System.out.println("----I--N--V--A--L--I--D----");
            }
        } else {
            System.out.println("Invalid Selection");
            contactOperation();
        }
    }

    public void assignUserName() {
        System.out.print("Enter Your UserName : \t");
        String username = SCANNER.nextLine();

        String userNamePattern = "^[a-zA-Z0-9\\s]{8,20}$";

        boolean loginDetails = username.matches(userNamePattern);

        if (loginDetails) {
            MODEL.setUserName(username);
        } else {
            System.out.println("Username Invalid Format");
            assignUserName();
        }
    }

    public void assignPassword() {
        System.out.print("Enter Your Password : \t");
        String password = SCANNER.nextLine();

        String passwordPattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

        boolean loginDetails = password.matches(passwordPattern);

        if (loginDetails) {
            MODEL.setPassword(password);
        } else {
            System.out.println("Password In Invalid Format");
            assignPassword();
        }
    }

    public void assignEmail() {
        System.out.print("Enter Your Email : \t");
        String email = SCANNER.nextLine();

        String emailPattern = "^[a-z0-9._]+@[a-z.]+$";

        boolean contactDetails = email.matches(emailPattern);

        if (contactDetails) {
            MODEL.setEmail(email);
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
            MODEL.setMobileNumber(mobileNumber);
        } else {
            System.out.println("-----MobileNumber Must contain 10 characters:--------");
            assignMobileNumber();
        }
    }

    public void assignTextMessage() {
        System.out.print("Enter Your textMessage : \t");
        MODEL.setTextMessage(SCANNER.nextLine());
    }
}
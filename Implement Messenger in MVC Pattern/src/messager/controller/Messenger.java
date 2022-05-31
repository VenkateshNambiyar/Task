package messager.controller;

import messager.model.ContactInformation;
import messager.model.LoginInformation;
import messager.model.MessageInformation;
import messager.view.ContactPage;
import messager.view.LoginPage;
import messager.view.MessagePage;

public class Messenger {
    private final LoginInformation loginModel;
    private final LoginPage loginView;
    private final ContactPage contactView;
    private final ContactInformation contactModel;
    private final MessageInformation messageBox;
    private final MessagePage messagePage;

    public Messenger(LoginInformation loginModel, LoginPage loginView,ContactInformation contactModel, ContactPage contactView,MessagePage messagePage,MessageInformation messageBox) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.contactModel = contactModel;
        this.contactView= contactView;
        this.messagePage = messagePage;
        this.messageBox = messageBox;
    }

   public void accountSignUp() {
        loginView.newUser(loginModel.getUserName(), loginModel.getPassword());
    }

   public void accountSignIn() {
        loginView.oldUser(loginModel.getUserName(), loginModel.getPassword());
   }

   public void accountForgotPassword() {
        loginView.forgotPassword(loginModel.getUserName(),loginModel.getPassword());
   }

    public void userContact() {
        contactView.displayUserContactDetails(contactModel.getEmail());
    }

    public void addUserContact() {
        contactView.addNewContact(contactModel.getMobileNumber(), loginModel.getUserName(),contactModel.getEmail());
    }

    public void messenger() {
        messagePage.messageBox(messageBox.getTextMessage(), messageBox.getUserProfileId(),contactModel.getEmail());
    }

    public void messageExistingUser() {
        messagePage.messageOldUser(messageBox.getTextMessage(), messageBox.getUserProfileId());
    }
}
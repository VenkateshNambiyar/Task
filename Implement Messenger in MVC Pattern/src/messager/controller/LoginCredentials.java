package messager.controller;

import messager.model.UserInformation;
import messager.view.LoginPage;

public class LoginCredentials {
    private final UserInformation model;
    private final LoginPage view;

    public LoginCredentials(UserInformation model,LoginPage view) {
        this.model = model;
        this.view = view;
    }

   public void accountSignUp() {
        view.newUser(model.getUserName(), model.getPassword());
    }

   public void accountSignIn() {
        view.oldUser(model.getUserName(), model.getPassword());
   }

   public void accountForgotPassword() {
        view.forgotPassword(model.getUserName(),model.getPassword());
   }

   public void userContact() {
        view.displayUserContactDetails(model.getEmail());
   }

   public void addUserContact() {
        view.addNewContact(model.getMobileNumber(), model.getUserName(),model.getEmail());
   }

    public void messenger() {
        view.messageBox(model.getTextMessage(), model.getUserName(),model.getEmail());
    }
}
package messager.model;

public class UserInformation {
    private String userName;
    private String password;
    private String mobileNumber;
    private String email;
    private String textMessage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }
}
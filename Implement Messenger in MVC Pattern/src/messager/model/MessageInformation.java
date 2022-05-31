package messager.model;

public class MessageInformation {
    private String textMessage;
    private String userProfileId;

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(final String textMessage) {
        this.textMessage = textMessage;
    }

    public String getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(final String userProfileId) {
        this.userProfileId = userProfileId;
    }
}
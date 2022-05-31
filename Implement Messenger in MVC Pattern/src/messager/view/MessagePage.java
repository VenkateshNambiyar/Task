package messager.view;

import messager.service.ChatBox;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.*;

public class MessagePage {
    private static final Map<String,Map<String,Set<String>>>  MESSAGE_TIMING_DETAILS = new HashMap<>();

    private static final Properties CONTACT_DETAILS = new Properties();

    private static String displayTimeStamp() {
        String displayTime;
        Date date = new Date();

        long time = date.getTime();

        Timestamp dateTime = new Timestamp(time);

        displayTime = dateTime.toString();

        return displayTime;
    }

    public void messageBox(final String textMessage, final String userProfileId,final String email) {
        try {
            final FileInputStream userContact = new FileInputStream(email);

            CONTACT_DETAILS.load(userContact);

            boolean person_Id = CONTACT_DETAILS.containsKey(userProfileId);

            if (person_Id) {
                Set<String> set = new HashSet<>();

                set.add(textMessage);
                Map<String,Set<String>> receiver_Name = new HashMap<>();

                receiver_Name.put(userProfileId,set);
                String displayDateTime = String.valueOf(displayTimeStamp());

                MESSAGE_TIMING_DETAILS.put(displayDateTime,receiver_Name);
                MessagePage.chatList();
            } else {
                System.out.println("User Not Found");
                ChatBox chatBox = new ChatBox();

                chatBox.messageBox();
            }
        } catch (Exception exception) {
            System.out.println("exception");
        }
    }

    public void messageOldUser(final String textMessage,final String userProfileId) {
        Set<String> set = new HashSet<>();

        set.add(textMessage);
        Map<String,Set<String>> receiver_Name = new HashMap<>();

        receiver_Name.put(userProfileId,set);
        String displayDateTime = String.valueOf(displayTimeStamp());

        MESSAGE_TIMING_DETAILS.put(displayDateTime,receiver_Name);
        MessagePage.chatList();
    }
   private static void chatList() {
        Scanner userChat = new Scanner(System.in);

        final String oldUser = "1";
        final String newUserProfile = "2";

        System.out.println("You want to continue with same user: press(1) OR new user press :(2) OR logout press:(Any Key)");
        System.out.print("Enter YOur Option:" );
        String newUser = userChat.nextLine();

        if(newUser.equals(oldUser)) {
            final ChatBox chatBox = new ChatBox();

            chatBox.oldUserMessage();
        } else if (newUser.equals(newUserProfile)){
            final ChatBox chatBox = new ChatBox();

            chatBox.messageBox();
        } else {
            System.out.println("Thank You");
            System.out.println(MESSAGE_TIMING_DETAILS);
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        }
    }
}

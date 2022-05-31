package messager.view;

import messager.service.ChatBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class ContactPage {
    private static final Properties CONTACT_DETAILS = new Properties();

    private static final Map<String,String> NEW_CONTACT = new HashMap<>();

    private static void contactLoadFile(final String email) throws Exception {
        final FileInputStream userContact = new FileInputStream(email);

        CONTACT_DETAILS.load(userContact);
    }

    private static void contactStoreFile(final String email) throws Exception {
        final FileOutputStream storeContactDetails = new FileOutputStream(email);

        CONTACT_DETAILS.store(storeContactDetails,"New user Added Successfully");
    }

    public void displayUserContactDetails(final String email) {
        try {
            contactLoadFile(email);
            CONTACT_DETAILS.forEach((key, value) -> System.out.println(key + " : " + value));
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        } catch (Exception exception) {
            System.out.println("UserNotFound");
            ChatBox chatBox = new ChatBox();

            chatBox.contactOperation();
        }
    }

    public void addNewContact(final String mobileNumber, final String personName,final String email) {
        try {
            int randomId = 256;

            final Random random = new Random();

            int personId = random.nextInt(randomId);

            String userProfileId = Integer.toString(personId);

            boolean userContact = CONTACT_DETAILS.containsKey(userProfileId);

            if (userContact) {
                System.out.println("Already userId exists. So kindly Try again");
                ChatBox chatBox = new ChatBox();

                chatBox.contactOperation();
            } else {
                NEW_CONTACT.put(mobileNumber,personName);
                String userContactProfile = NEW_CONTACT.toString();

                CONTACT_DETAILS.setProperty(userProfileId,userContactProfile);
                contactStoreFile(email);
                ChatBox chatBox = new ChatBox();

                chatBox.contactOperation();
            }
        } catch (Exception exception) {
            System.out.println("User Not Found");
        }
    }
}
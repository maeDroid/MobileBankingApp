package mae.comp3717.bcit.ca.bankingapp.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample title for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Messages {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Message> ITEMS = new ArrayList<Message>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Message> ITEM_MAP = new HashMap<String, Message>();

        static String content1 = "Thank you for installing Sea to Sky Bank's mobile app. " +
                "Please contact a representative at your local branch if you have questions " +
                "or need any assistance with your banking and investment needs.";


        static String content2 = "Going paperless helps reduce your paper clutter and" +
                "saves you from spending valuable time sorting, filing, and shredding" +
                "monthly statements. \n\n" +
                "Paperless recordkeeping is secure, private, and best of all, it's free!" +
                "\n\nSimplify your monthly paperwork and go green today.";

        static String content3 = "Daylight Savings ends on Sunday, November 1, 2015, " +
                "at 2:00:00 AM.  Remember to turn your clocks backward 1 hour to " +
                "1:00:00 AM local standard time.";


    static {
        // Add 3 sample items.
        addItem(new Message("1", "January 1st, 2015", "Welcome!", content1));
        addItem(new Message("2", "July 1st, 2015", "Go Paperless", content2));
        addItem(new Message("3", "October 13th, 2015", "Daylight Savings", content3));
    }

    private static void addItem(Message item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of title.
     */
    public static class Message {
        public String id;
        public String date; 
        public String title;
        public String body;

        public Message(String id, String date, String title, String body) {
            this.id = id;
            this.date = date;
            this.title = title;
            this.body = body;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}

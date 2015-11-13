/**
 * Created by Aditya Dhingra & Abid Kaisani on 10/30/15.
 */
import java.util.*;
public class CircularBuffer {
    private static int size;
    private static int messagecount;
    private static ArrayList<String> chatroom = new ArrayList<>();
    private static ArrayList<Long> chatcount = new ArrayList<>();

    public CircularBuffer(int size) {
        this.size = size;
        this.messagecount = 0;
    }

    public static void put(String message) {

        String FourCode = String.format("%04d", messagecount);
        message = FourCode + ") " + message;
        System.out.println(message);
        if (chatroom.size() <= size) {
            chatroom.add(message);
            chatroom.add(messagecount);
            messagecount ++;
        } else {
            for (int i = 0; i < chatroom.size() ; i++) {
                
            }
        }
    }

    //ADDING GET NEWEST METHOD
    public String[] getNewest(int numMessages) {
        if (numMessages < 0) {
            return null;
        }
        if (numMessages == 0) {
        }
        return null; // remove this later on.
    }

    public static void main(String[] args) {
        put("HI MY NAME");
    }

}

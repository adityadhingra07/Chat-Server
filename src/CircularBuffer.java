/**
 * Created by Aditya Dhingra & Abid Kaisani on 10/30/15.
 */
import java.util.*;
public class CircularBuffer {
    private int size;
    private int messagecount;
    public static ArrayList<String> chatroom = new ArrayList<>();
    private static ArrayList<Integer> chatcount = new ArrayList<>();

    public CircularBuffer(int size) {
        this.size = size;
        this.messagecount = 0;
    }

    public void put(String message) {

        String FourCode = String.format("%04d", messagecount);
        message = FourCode + ") " + message;
        System.out.println(message);
        if (chatroom.size() < size) {
            chatroom.add(message);
            chatcount.add(messagecount);
            messagecount++;
        } else {
            for (int i = 0; i < chatroom.size() - 1 ; i++) {
                if (chatcount.get(i) > chatcount.get(i+1)) {
                    chatroom.set(i+1 , message);
                    chatcount.set(i+1, messagecount);
                    messagecount++;
                }
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
        CircularBuffer cb = new CircularBuffer(4);
        cb.put("Hi");
        cb.put("1");
        cb.put("2");
        cb.put("3");
        cb.put("4");
        cb.put("5");
        cb.put("6");
        System.out.println(cb.chatroom.toString());

    }

}

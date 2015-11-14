/**
 * Created by Aditya Dhingra & Abid Kaisani on 10/30/15.
 *//**
 * <b> CS 180 - Project 4 - Chat Server Skeleton </b>
 * <p>
 * <p>
 * This is the skeleton code for the ChatServer Class. This is a private chat
 * server for you and your friends to communicate.
 *
 * @author (Aditya Dhingra & Abid Kaisani) <(adhingr@purdue.edu ,akaisani@purdue.edu)>
 * @version (Today's Date)
 * @lab (Your Lab Section)
 */

import java.lang.reflect.Array;
import java.util.*;
public class CircularBuffer {
    private int size;
    private int messagecount;
    private static ArrayList<String> chatroom = new ArrayList<>();
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
            for (int i = 0; i < chatcount.size(); i++) {
                if (chatcount.get(i) > chatcount.get(i+1)) {
                    chatroom.set(i+1 , message);
                    chatcount.set(i+1, messagecount);
                    messagecount++;
                    break;
                } else if(messagecount - chatcount.get(size - 1) == 1) {
                    chatroom.set(i , message);
                    chatcount.set(i, messagecount);
                    messagecount++;
                    break;
                }
            }
        }
    }

    public String[] getNewest(int numMessages) {
        // Invalid Number
        if (numMessages < 0) {
            return null;
        }
        // No messages
        if (numMessages == 0) {
            String[] s = {};
            return s;
        }
        //ELSE DO THIS
        String[] tempChatroom = new String[chatroom.size()];
        for (int i = 0; i < chatroom.size(); i++) {
            tempChatroom[i] = chatroom.get(i);
        }
        int[] tempChatcount = new int[chatcount.size()];
        for (int i = 0; i < chatcount.size(); i++) {
            tempChatcount[i] = chatcount.get(i);
        }
        String tempMsg = "";
        int temp = 0;
        int numAvailable;
        if (messagecount < chatroom.size()) {
            numAvailable = messagecount;
        } else {
            numAvailable = size;
        }
        if (numAvailable < numMessages) {
            for (int i = 0; i < tempChatcount.length; i++) {
                for (int j = 0; j < tempChatcount.length; j++) {
                    if(tempChatcount[i] < tempChatcount[j]) {
                        temp = tempChatcount[i];
                        tempMsg = tempChatroom[i];
                        tempChatcount[i] = tempChatcount[j];
                        tempChatroom[i] = tempChatroom[j];
                        tempChatcount[j] = temp;
                        tempChatroom[j] = tempMsg;
                    }
                }
            }
            return tempChatroom;
        }
        // IF NOTHING ABOVE WORKS, THEN DO THIS
        for (int i = 0; i < tempChatcount.length; i++) {
            for (int j = 0; j < tempChatcount.length; j++) {
                if(tempChatcount[i] < tempChatcount[j]) {
                    temp = tempChatcount[i];
                    tempMsg = tempChatroom[i];
                    tempChatcount[i] = tempChatcount[j];
                    tempChatroom[i] = tempChatroom[j];
                    tempChatcount[j] = temp;
                    tempChatroom[j] = tempMsg;
                }
            }
        }
        String[] FinalArray = new String[numMessages];
        int ind = 0;
        for (int j = tempChatroom.length - numMessages; j < tempChatroom.length; j++) {
            FinalArray[ind] = tempChatroom[j];
            ind++;
        }

        return FinalArray;
    }

    public static void main(String[] args) {
        CircularBuffer cb = new CircularBuffer(4);
        cb.put("0");
        cb.put("1");
        cb.put("2");
        cb.put("3");
        cb.put("4");
        cb.put("5");
        cb.put("6");
        cb.put("7");
        cb.put("8");
        cb.put("9");
        System.out.println(cb.chatcount.toString());
        System.out.println(cb.chatroom.toString());
        System.out.println(Arrays.toString(cb.getNewest(3)));

    }

}

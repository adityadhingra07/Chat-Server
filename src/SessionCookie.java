/**
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

import java.util.*;
public class SessionCookie {

    private long cookieID;
    public static int timeoutLength = 300;
    long oldTime;
    long updatedTime;
    public SessionCookie(long cookieID) {
        this.cookieID = cookieID;
        oldTime = System.currentTimeMillis();
    }

    public boolean hasTimedOut() {
        long timeElapsed = updatedTime * 1000;
        if (timeElapsed == timeoutLength) {
            return true;
        }

        return false;
    }
    /** before edit
    public long getUpdatedTimeOfActivity() {

        long newTime = System.currentTimeMillis();
        updatedTime = newTime - oldTime;
        return updatedTime;
    }
     */
    public void updateTimeOfActivity() {
        long newTime = System.currentTimeMillis();
        updatedTime = newTime - oldTime;
    }

    public long getID() {
//        Random randomifier = new Random();
//        int num1 = randomifier.nextInt(9);
//        int num2 = randomifier.nextInt(9);
//        int num3 = randomifier.nextInt(9);
//        int num4 = randomifier.nextInt(9);
//        String FourCode = "" + num1 + num2 + num3 + num4;
//        int ID = Integer.parseInt(FourCode);
//        cookieID =ID;
        return cookieID;
    }

}

/**
 * Created by Aditya Dhingra & Abid Kaisani on 10/30/15.
 */
import java.util.*;
public class SessionCookie {

    private long cookieID;
    public static int timeoutLength = 300;

    public SessionCookie(long cookieID) {
        this.cookieID = cookieID;
    }

    public boolean hasTimedOut() {
        return false;
    }

    public void updateTimeOfActivity() {
        long oldTime = System.currentTimeMillis();
        long newTime = System.currentTimeMillis();
        long updatedTime = newTime - oldTime;
    }

    public long getID() {
        Random randomifier = new Random();
        int num1 = randomifier.nextInt(9);
        int num2 = randomifier.nextInt(9);
        int num3 = randomifier.nextInt(9);
        int num4 = randomifier.nextInt(9);
        String FourCode = "" + num1 + num2 + num3 + num4;
        int ID = Integer.parseInt(FourCode);
        cookieID =ID;
        return cookieID;
    }

}

/**
 * Created by Aditya on 10/30/15.
 */
public class SessionCookie {

    private long cookieID;
    public static int timeoutLength = 300;

    public SessionCookie(long cookieID) {
        this.cookieID = cookieID;
    }

    public boolean hasTimedOut() {

    }

    public void updateTimeOfActivity() {

    }

    public long getID() {
        return cookieID;
    }

}

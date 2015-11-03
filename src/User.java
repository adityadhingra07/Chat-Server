/**
 * Aditya Dhingra & Abid Kaisani
 */
import java.util.ArrayList;

public class User {

    //PROTOCOLS FOR USER.CLASS

    private String username;
    private String password;
    private SessionCookie cookie;
    ArrayList<User> userArrayList;


    public User(String username, String password, SessionCookie cookie) {
        this.username = username;
        this.password = password;
        this.cookie = cookie;
        userArrayList = new ArrayList<User>();
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return username;
    }

    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public SessionCookie getCookie() {
        return cookie;
    }

    public void setCookie(SessionCookie cookie) {
        this.cookie = cookie;
    }
    public void ADD_USER(int cookieID, String username, String password) {
        //  User userNew = new User(password, username, cookieID);
    }

    public void USER_LOGIN(String username, String password) {
        for (int i = 0; i < userArrayList.size(); i++) {
            if (userArrayList.get(i).getName().equals(username) && userArrayList.get(i).getPassword().equals(password)) {

            }
        }
    }

    public void POST_MESSAGE(int cookieID, String message) {

    }

    public void GET_MESSAGES(int CookieID, int numMessages) {

    }

}

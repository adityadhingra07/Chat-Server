/**
 * Aditya Dhingra & Abid Kaisani
 */
import java.util.ArrayList;

public class User {

    //PROTOCOLS FOR USER.CLASS

    private String username;
    private String password;
    private SessionCookie cookie;


    public User(String username, String password, SessionCookie cookie) {
        this.username = username;
        this.password = password;
        this.cookie = cookie;

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

}

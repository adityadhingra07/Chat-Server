import com.sun.deploy.util.StringUtils;

import java.util.*;

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
public class ChatServer {
    private User[] users;
    private int maxMessages;
    int count = 0;
    int cookieID;
    String command;
    String uname;
    String upass;
    String cookit;

    public ChatServer(User[] users, int maxMessages) {
        // TODO Complete the constructor
        this.users = users;
        Random randomifier = new Random();
        int num1 = randomifier.nextInt(9);
        int num2 = randomifier.nextInt(9);
        int num3 = randomifier.nextInt(9);
        int num4 = randomifier.nextInt(9);
        String FourCode = "" + num1 + num2 + num3 + num4;
        int ID = Integer.parseInt(FourCode);
        cookieID = ID;
        users[0] = new User("root", "cs180", new SessionCookie(cookieID));
    }

    /**
     * This method begins server execution.
     */
    public void run() {
        boolean verbose = false;
        System.out.printf("The VERBOSE option is off.\n\n");
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.printf("Input Server Request: ");
            String command = in.nextLine();

            // this allows students to manually place "\r\n" at end of command
            // in prompt
            command = replaceEscapeChars(command);

            if (command.startsWith("kill"))
                break;

            if (command.startsWith("verbose")) {
                verbose = !verbose;
                System.out.printf("VERBOSE has been turned %s.\n\n", verbose ? "on" : "off");
                continue;
            }

            String response = null;
            try {
                response = parseRequest(command);
            } catch (Exception ex) {
                response = MessageFactory.makeErrorMessage(MessageFactory.UNKNOWN_ERROR,
                        String.format("An exception of %s occurred.", ex.getMessage()));
            }

            // change the formatting of the server response so it prints well on
            // the terminal (for testing purposes only)
            if (response.startsWith("SUCCESS\t"))
                response = response.replace("\t", "\n");

            // print the server response
            if (verbose)
                System.out.printf("response:\n");
            System.out.printf("\"%s\"\n\n", response);
        }

        in.close();
    }

    /**
     * Replaces "poorly formatted" escape characters with their proper values.
     * For some terminals, when escaped characters are entered, the terminal
     * includes the "\" as a character instead of entering the escape character.
     * This function replaces the incorrectly inputed characters with their
     * proper escaped characters.
     *
     * @param str - the string to be edited
     * @return the properly escaped string
     */
    private static String replaceEscapeChars(String str) {
        str = str.replace("\\r", "\r");
        str = str.replace("\\n", "\n");
        str = str.replace("\\t", "\t");

        return str;
    }

    /**
     * Determines which client command the request is using and calls the
     * function associated with that command.
     *
     * @param request - the full line of the client request (CRLF included)
     * @return the server response
     */
    public String parseRequest(String request) {
        // TODO: Replace the following code with the actual code

        char[] strChar = request.toCharArray();
        strChar = Arrays.copyOf(strChar, strChar.length - 2);
        request = new String(strChar);
        String[] stringTemp = request.split("\t");
        String compiledString = "";
        for (int i = 0; i < stringTemp.length; i++) {
            compiledString = compiledString + stringTemp[i] + " ";
        }
        request = compiledString;
        String[] checkerarg = compiledString.split(" ");

        if (checkerarg.length != 3 && checkerarg.length == 4) {

        } else if (checkerarg.length !=3){
            return "failure:error message for incorrect number of arguments";
        }
        command = checkerarg[0];

        if (!(command.equals("ADD-USER")) || !(command.equals("USER-LOGIN")) || !(command.equals("POST-MESSAGE"))
                || !(command.equals("GET-MESSAGES")))
            return "failure:incorrect command or parameters error";
        if (!(command.toUpperCase().equals("ADD-USER"))) {
            for (int i = 0; i < users.length; i++) {
                if (command.toUpperCase().equals(users[i].getName())) {
                    if (users[i].getCookie() == null) {
                        return "failure:LUser not autheticated message";
                    } else if (users[i].getCookie().hasTimedOut()) {
                        users[i].setCookie(null);
                        return "daliuere:Cookie timeout message";
                    } else {
                        continue;
                    }
                }
            }
        } else {
            if (command.equals("ADD-USER")) {
                return addUser(checkerarg);
            }
            if (command.equals("USER-LOGIN"))
                return userLogin(checkerarg);
            if (command.equals("POST-MESSAGE"))
                return postMessage(checkerarg,checkerarg[2]);
            if (command.equals("GET-MESSAGES"))
                return getMessages(checkerarg);


        }


        return "unknown";
    }

    //Protocol methods
    public String postMessage(String[] args, String name) {
        for (int i = 0; i < args.length; i++) {
            args[i] = StringUtils.trimWhitespace(args[i]);
            if (args[i].length() <= 0) {
                return "SOME ERROR MESSAGE I HAVE TO LOOK UP FOR STRING WITHOUT ONE CHARACTER";
            }

            return String.format("%s:\t%s", name, args[i]);
        }

        return null;
    }

    public String addUser(String[] args) {
        User userNew = new User(args[2],args[3],new SessionCookie(Long.parseLong(args[1])));
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null)
                users[i] = userNew;
            return "SUCCESS\r\n";
        }
        return "Failure: no add user";
    }

    public String userLogin(String[] args) {
        return  null;
    }


    public String getMessages(String[] args) {
        return null;
    }


//    public void AD(int cookieID, String username, String password) {
//        User userNew = new User(username, password, new SessionCookie((long) cookieID));
//        //ASK GREGGORY ABOUT THIS METHOD
//        for (int i = 0; i < users.length; i++) {
//            if (users[i] == null)
//                users[i] = userNew;
//            else {
//                continue;
//            }
//        }
//    }

    public static void main(String[] args) {

        //System.out.println(parseRequest("User-login\troot\tcs180\r\n").length());
    }


    //Protocol methods end


}

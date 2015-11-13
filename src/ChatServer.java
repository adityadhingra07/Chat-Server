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
    public ChatServer(User[] users, int maxMessages) {
        // TODO Complete the constructor
        this.users = users;

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
    public static String parseRequest(String request) {
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
        if (checkerarg.length != 3 ) {
            return "error message for incorrect number of arguments";
        }


        return request;
    }

    //Protocol methods
    public String postMessage(String[] args, String name) {
        for (int i = 0; i < args.length ; i++) {
            args[i] = StringUtils.trimWhitespace(args[i]);
            if (args[i].length() <= 0) {
                return "SOME ERROR MESSAGE I HAVE TO LOOK UP FOR STRING WITHOUT ONE CHARACTER";
            }
            return String.format("%s:\t%s",name,args[i]);
        }
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

    public static void main (String[] args) {

        System.out.println(parseRequest("User-login\troot\tcs180\r\n").length());
    }







    //Protocol methods end


}

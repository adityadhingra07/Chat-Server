import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collector;

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
        int num1 = randomifier.nextInt(10000);
        //                    int num2 = randomifier.nextInt(9);
        //                    int num3 = randomifier.nextInt(9);
        //                    int num4 = randomifier.nextInt(9);
        String FourCode = String.format("%04d", num1);
        int ID = Integer.parseInt(FourCode);
        cookieID = ID;
        System.out.println(cookieID);
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

        } else if (checkerarg.length != 3) {
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
            if (command.equals("POST-MESSAGE")) {
                int foodId = Integer.parseInt(checkerarg[1]);
                String name = "";
                for (int i = 0; i < users.length; i++) {
                    if (users[i].getCookie().getID() == foodId)
                        name = users[i].getName();

                }
                return postMessage(checkerarg, name);
            }
            if (command.equals("GET-MESSAGES"))
                return getMessages(checkerarg);


        }


        return "unknown:parse request";
    }

    //Protocol methods
    public String addUser(String[] args) {
        boolean checkAlphabet = true;
        boolean checkNumba = true;
        String viableCheckString = "" + args[2] + " " + args[3];
        String[] viableArray = viableCheckString.split(" ");
        char[] uname = viableArray[0].toCharArray();
        char[] pass = viableArray[1].toCharArray();
        if ((uname.length < 1 && uname.length > 20))
            return "Failure: incorrect length of username";
        if ((pass.length < 4 && pass.length > 40))
            return "Failure: incorrect length of password";

        while (checkAlphabet == true) {
            for (int j = 0; j < uname.length; j++) {
                if (!(Character.isLetter(uname[j]))) {
                    checkAlphabet = false;

                }
            }

            for (int j = 0; j < pass.length; j++) {
                if (!(Character.isLetter(pass[j]))) {
                    checkAlphabet = false;
                }
            }
        }
        while (checkNumba == true) {
            for (int j = 0; j < uname.length; j++) {
                if ((!(Character.isLetter(uname[j]))) && !(Character.isLetter(uname[j]))) {
                    checkNumba = false;

                }
            }

            for (int j = 0; j < pass.length; j++) {
                if ((!(Character.isLetter(pass[j]))) && !(Character.isLetter(pass[j]))) {
                    checkNumba = false;
                }
            }
        }
        if (checkNumba == false || checkAlphabet == false)
            return "Failure: Username can ony contain the following [A-Za-z0-9]";


        User userNew = new User(args[2], args[3], new SessionCookie(Long.parseLong(args[1])));
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null)
                users[i] = userNew;
            return "SUCCESS\r\n";
        }
        return "Failure: no add user";
    }

    public String userLogin(String[] args) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().equals(args[1])) {
                if (users[i].getCookie() == null) {
                    if (users[i].getPassword().equals(args[2])) {
                        Random randomifier = new Random();
                        int num1 = randomifier.nextInt(10000);
//                    int num2 = randomifier.nextInt(9);
//                    int num3 = randomifier.nextInt(9);
//                    int num4 = randomifier.nextInt(9);
                        String FourCode = String.format("%04d", num1);
                        int ID = Integer.parseInt(FourCode);
                        cookieID = ID;
                        boolean cookieUnique = false;
                        while (cookieUnique != true) {
                            for (int j = 0; j < users.length; j++) {
                                if (users[j].getCookie().getID() == cookieID)
                                    cookieUnique = false;
                                else
                                    cookieUnique = true;
                            }
                        }
                        if (cookieUnique == true) {
                            users[i].setCookie(new SessionCookie((long) cookieID));
                            return String.format("SUCCESS\t%04D\r\n", cookieID);
                        }

                        return "failure: incorrect password";
                    }
                    return "Faliure: User already signed in";
                }
                return "Failure: user dies not exsist";

            }

        }
        return "unknown login";
    }

    public String postMessage(String[] args, String name) {
//        for (int i = 0; i < args.length; i++) {
//            args[i] = StringUtils.trimWhitespace(args[i]);
//            if (args[i].length() <= 0) {
//                return "SOME ERROR MESSAGE I HAVE TO LOOK UP FOR STRING WITHOUT ONE CHARACTER";
//            }
//
//            return String.format("%s:\t%s", name, args[i]);
//        }
        String message = args[2];
        String noSpaceMessage = StringUtils.trimWhitespace(message);
        if (noSpaceMessage.length() < 1)
            return "SOME ERROR MESSAGE I HAVE TO LOOK UP FOR STRING WITHOUT ONE CHARACTER";


        return String.format("%s: %s",name,message);
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

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
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
    SessionCookie cookie;
    int cookieID;
    String command;
    CircularBuffer cb;
    int count;


    public ChatServer(User[] users, int maxMessages) {
        this.maxMessages = maxMessages;
        cb = new CircularBuffer(maxMessages);
        this.users = users;
        Random randomifier = new Random();
        int num1 = randomifier.nextInt(10000);
        //                    int num2 = randomifier.nextInt(9);
        //                  return "SOME ERROR MESSAGE I HAVE TO LOOK UP FOR STRING WITHOUT ONE CHARACTER";  int num3 = randomifier.nextInt(9);
        //                    int num4 = randomifier.nextInt(9);
        String FourCode = String.format("%04d", num1);
        int ID = Integer.parseInt(FourCode);
        cookieID = ID;
        //System.out.println(cookieID);
        //System.out.println(FourCode);
        count++;
        users = Arrays.copyOf(users, count);
        //users[0] = new User("root", "cs180", new SessionCookie(cookieID));
        users[0] = new User("root", "cs180", null);
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

        if (request == null)
            return "FAILURE\t11\tUnknown Command Error: Command unknown.\r\n";
//        char[] strChar = request.toCharArray();
//        strChar = Arrays.copyOf(strChar, strChar.length - 2);
//        request = new String(strChar);
        String[] checkerarg;
        int position = request.indexOf("\r\n");
        if (position == -1) {
            checkerarg = request.split("\t");
        } else {
            request = request.substring(0, position);
            checkerarg = request.split("\t");
        }

//        for (int i = 0; i < checkerarg.length; i++) {
//            System.out.print(checkerarg[i] + " ");
//            System.out.println(checkerarg[i].length());
//        }
//        String compiledString = "";
//        for (int i = 0; i < stringTemp.length; i++) {
//            compiledString = compiledString + stringTemp[i] + " ";
//        }
////        request = compiledString;
//        String[] checkerarg = compiledString.split(" ");

//        if (checkerarg.length != 3 && checkerarg.length == 4) {
//
//        } else if (checkerarg.length != 3) {
//            //return "failure:error message for incorrect number of arguments";
//            //return "Failure: ERROR MESSAGE #10";
//            return "FAILURE\t10\tFormat Command Error: Your Format is incorrect.\r\n";
//        }
//        if (checkerarg.length != 3) {
//            if (checkerarg.length != 4) {
//                //return "failure:error message for incorrect number of arguments";
//                //return "Failure: ERROR MESSAGE #10";
//                return "FAILURE\t10\tFormat Command Error: Your Format is incorrect.\r\n";
//            }
//        }
        command = checkerarg[0];

        if ((!(command.equals("ADD-USER"))) & (!(command.equals("USER-LOGIN"))) & (!(command.equals("POST-MESSAGE")))
                & (!(command.equals("GET-MESSAGES")))) {
            //return "failure:incorrect command or parameters error";
            //return "Failure: ERROR MESSAGE #10";
            return "FAILURE\t11\tUnknown Command Error: Command unknown.\r\n";
        }
//        if (!(command.toUpperCase().equals("ADD-USER"))) {
//            for (int i = 0; i < users.length; i++) {
//                if (command.toUpperCase().equals(users[i].getName())) {
//                    if (users[i].getCookie() == null) {
//                        //user not logged in message
//                        //return "Failure: ERROR MESSAGE #23";
//
//                    } else if (users[i].getCookie().hasTimedOut()) {
//                        users[i].setCookie(null);
////                        return "Failure:Cookie timeout message";
//                        //return "Failure: ERROR MESSAGE #05";
//                        return "FAILURE\t05\tCookie Timeout Error: Your login cookie has timed out.\r\n";
//                    } else {
//                        continue;
//                    }
//                }
//            }
//        } else {
        if (checkerarg.length > 2 && checkerarg.length < 5) {
            if (checkerarg.length == 4) {
                if (command.equals("ADD-USER")) {
                    System.out.println("imma add you baby");
                    return addUser(checkerarg);
                }
            } else if (checkerarg.length == 3) {
                if (command.equals("USER-LOGIN")) {
                    System.out.println("imma log you in baby");
                    return userLogin(checkerarg);
                }
                if (command.equals("POST-MESSAGE")) {
                    System.out.println("say something im giving up on you ");
                    long idOfUser = Long.parseLong(checkerarg[1]);
                    String name = "";
                    for (int i = 0; i < users.length; i++) {
                        if (users[i].getCookie().getID() == idOfUser)
                            name = users[i].getName();

                    }
                    return postMessage(checkerarg, name);
                }
                if (command.equals("GET-MESSAGES")) {
                    System.out.println("going over the past aiyyy");
                    return getMessages(checkerarg);
                }
            }
        }
        return MessageFactory.makeErrorMessage(10);
    }


    //}


//        return "unknown:parse request";
//        return "Failure: ERROR MESSAGE #11";



    //PROTOCOL METHODS : START HERE

    public String addUser(String[] args) {
        String cookie = args[1];
        String uname = args[2];
        String pass = args[3];

        //CHECK FOR VALIDITY OF UNAME AND PASS
        // 1. Length Check
        boolean check = true;
        if (!(uname.length() >= 1 && uname.length() <= 20)) {
            check = false;
//            return "Failure: ERROR MESSAGE #24";
            return "FAILURE\t24\tInvalid Value Error: Incorrect value entered.\r\n";
        }
        if (!(pass.length() >= 4 && pass.length() <= 40)) {
            check = false;
            //            return "Failure: ERROR MESSAGE #24";
            return "FAILURE\t24\tInvalid Value Error: Incorrect value entered.\r\n";
        }
        // 2. AlphaNumeric Check
        for (int i = 0; i < uname.length(); i++) {
            if (!(Character.isLetterOrDigit(uname.charAt(i)))) {
                check = false;
                //            return "Failure: ERROR MESSAGE #24";
                return "FAILURE\t24\tInvalid Value Error: Incorrect value entered.\r\n";
            }
        }
        for (int i = 0; i < pass.length(); i++) {
            if (!(Character.isLetterOrDigit(pass.charAt(i)))) {
                check = false;
                //            return "Failure: ERROR MESSAGE #24";
                return "FAILURE\t24\tInvalid Value Error: Incorrect value entered.\r\n";
            }
        }
        //CHECKS COMPLETE : Errors have been reported.

//        boolean checkAlphabet = true;
//        boolean checkNumba = true;
//        String viableCheckString = "" + args[2] + " " + args[3];
//        String[] viableArray = viableCheckString.split(" ");
//        char[] uname = viableArray[0].toCharArray();
//        char[] pass = viableArray[1].toCharArray();
//        if ((uname.length < 1 && uname.length > 20))
//            return "Failure: incorrect length of username";
//        if ((pass.length < 4 && pass.length > 40))
//            return "Failure: incorrect length of password";
//        while (checkAlphabet == false) {
//            for (int j = 0; j < uname.length; j++) {
//                if (!(Character.isLetterOrDigit(uname[j]))) {
//                    checkAlphabet = false;
//
//                }
//            }
//
//            for (int j = 0; j < pass.length; j++) {
//                if (!(Character.isLetterOrDigit(pass[j]))) {
//                    checkAlphabet = false;
//                }
//            }
//        }


//        while (checkAlphabet == true) {
//            for (int j = 0; j < uname.length; j++) {
//                if (!(Character.isLetter(uname[j]))) {
//                    checkAlphabet = false;
//
//                }
//            }
//
//            for (int j = 0; j < pass.length; j++) {
//                if (!(Character.isLetter(pass[j]))) {
//                    checkAlphabet = false;
//                }
//            }
//        }
//        while (checkNumba == true) {
//            for (int j = 0; j < uname.length; j++) {
//                if ((!(Character.isLetter(uname[j]))) && !(Character.isLetter(uname[j]))) {
//                    checkNumba = false;
//
//                }
//            }
//
//            for (int j = 0; j < pass.length; j++) {
//                if ((!(Character.isLetter(pass[j]))) && !(Character.isLetter(pass[j]))) {
//                    checkNumba = false;
//                }
//            }
//        }
//        if (checkAlphabet == false)
//            return "Failure: Username can ony contain the following [A-Za-z0-9]";
        //IDK ABOUT THIS
        //cookie = cookie.substring(0, cookie.length() - 2);

        User userNew = new User(args[1], args[2], new SessionCookie(Long.parseLong(cookie)));
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().equals(userNew.getName())) {
//                return "Failure: ERROR MESSAGE #22";
                return "FAILURE\t22\tUser Error: User already exists.\r\n";
            }
        }
        count++;
        users = Arrays.copyOf(users, count);
        users[count - 1] = userNew;
        return "SUCCESS\r\n";


        //return "Failure: no add user unknown";
        // return "Failure: ERROR MESSAGE #00";
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
                        //wrong password error
//                        return "Failure: ERROR MESSAGE #21";
                        return "FAILURE\t21\tAuthentication Error: User already exists.\r\n";
                    }
//                    return "Failure: User already signed in";
//                    return "Failure: ERROR MESSAGE #25";
                    return "FAILURE\t25\tUser Connected Error: User already logged in.\r\n";
                }
                // "Failure: user dies not exist"
//                return "Failure: ERROR MESSAGE #20";
                return "FAILURE\t20\tUser Username Lookup Error: The specified user does not exist.\t\n\r\n";

            }

        }
        //return "unknown login";
//        return "Failure: ERROR MESSAGE #00";
        return "FAILURE\t00\tUnknown Error: An unknown error occurred. This was likely caused by an uncaught exception.\r\n";
    }

    public String postMessage(String[] args, String name) {
        String message = args[1];
//        String noSpaceMessage = StringUtils.trimWhitespace(message);
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().equals(name)) {
                if (users[i].getCookie() == null) {
                    // user not logged in error
//                    return "Failure: ERROR MESSAGE #23";
                    return "FAILURE\t23\tLogin Error: The specified user has not logged in to the server.\r\n";
                } else {
                    continue;
                }
            }
        }
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().equals(name)) {
                if (users[i].getCookie().hasTimedOut() == true) {
                    // cookie timed out error
                    //return "Failure: ERROR MESSAGE #05";
                    return "FAILURE\t05\tCookie Timeout Error: Your login cookie has timed out.\r\n";
                } else
                    continue;
            }
        }
        // CHECK IF THE FOLLOWING STATEMENTS NEED TO BE IN THE LOOP WITH THE IF STATEMENTS OR NOT

        String noSpaceMessage = message.trim();
        if (noSpaceMessage.length() < 1) {
//            return "SOME ERROR MESSAGE I HAVE TO LOOK UP FOR STRING WITHOUT ONE CHARACTER";
//            //return "Failure: ERROR MESSAGE #10";
            return "FAILURE\t10\tFormat Command Error: Your Format is incorrect.\r\n";
        }

        //UPDATING THE USERS SESSION COOKIE
        for (int i = 0; i < users.length; i++) {
            if (users[i].getName().equals(name)) {
                users[i].getCookie().updateTimeOfActivity();
            }
        }
        String msg = String.format("%s: %s", name, message);
        cb.put(msg);
        return msg;
    }

    public String getMessages(String[] args) {
        int numMessages = Integer.parseInt(args[1]);
        if (numMessages < 0) {
//                      return "Failure: ERROR MESSAGE #24";
            return "FAILURE\t24\tInvalid Value Error: Incorrect value entered.\r\n";
        }
        String[] msg = cb.getNewest(numMessages);
        String finalmsg = "SUCCESS\\t";
        for (int i = 0; i < msg.length; i++) {
            if (i == msg.length - 1) {
                finalmsg += msg[i] + "\r\n";
            } else {
                finalmsg += msg[i] + "\t";
            }
        }
        return finalmsg;
    }

    //PROTOCOL METHODS: END HERE

//        msg = msg.substring(1,msg.length() -1);
//        String[] messages = msg.split(",");
//        for (int i = 0; i < messages.length; i++) {
//            String start = messages[i].substring(0,messages[i].length() -2);
//            String mid = "";
//            String end = messages[i].substring(messages[i].length() -1,messages.length);
//            for (int j = 0; j < users.length; j++) {
//                if (users[i].getCookie().getID()== Integer.parseInt(start.substring(0,messages[i].length() -1)));
//                    mid = users[i].getName();
//
//            }
//            messages[i] = "" + start + mid + end;
//        }
//        String temp = Arrays.toString(messages);
//        String output = temp.replaceAll(",","\t");
//        return String.format("SUCCESS\t%s",output);


    public static void main(String[] args) {
        ChatServer c = new ChatServer(new User[0], 200);
        System.out.println(c.parseRequest("User-login\troot\tcs180\r\n").length());

    }

}

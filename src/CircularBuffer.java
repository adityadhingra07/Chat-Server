/**
 * Created by Aditya Dhingra & Abid Kaisani on 10/30/15.
 */
import java.util.*;
public class CircularBuffer {
    private int size;
    public CircularBuffer(int size) {
        this.size = size;
    }

    public void put(String message) {
        Random randomifier = new Random();

        int num1 = randomifier.nextInt(9);
        int num2 = randomifier.nextInt(9);
        int num3 = randomifier.nextInt(9);
        int num4 = randomifier.nextInt(9);
        String FourCode = "" + num1 + num2 + num3 + num4;
        message = FourCode + ") " + message;
    }

    //GET NEWEST METHOD LEFT


}

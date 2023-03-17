package seedu.badMaths.matrix;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ui {
    Logger logger = Logger.getLogger("matrix");

    public void printBeginning(){
        System.out.println("========= MATRIX CALCULATOR =========");
        System.out.println("You enter into the matrix calculator!");
        System.out.println("You can type 'info' to learn how to use this calculator.\n");
    }
    public void printEnding(){
        System.out.println("Exit from the calculator!");
    }
    public void printInfo(){
        System.out.println("");
    }
    public void printShapeMismatchExceptionLog(){
        String logMessage = "";
        logMessage += "Shape mismatch between t1 and t2 occur : cannot execute matrix multiplication.\n";
        logMessage += "Click ENTER to resume your calculation.";
        logger.log(Level.WARNING, logMessage);
    }

    public static String readCommand(){
        Scanner in = new Scanner(System.in);
        System.out.print(">> ");
        return in.nextLine();
    }
}

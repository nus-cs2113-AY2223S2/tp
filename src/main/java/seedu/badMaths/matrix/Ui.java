package seedu.badMaths.matrix;

import java.util.Scanner;

public class Ui {
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

    public static String readCommand(){
        Scanner in = new Scanner(System.in);
        System.out.print(">> ");
        return in.nextLine();
    }
}

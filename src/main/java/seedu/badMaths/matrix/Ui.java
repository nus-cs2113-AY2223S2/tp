package seedu.badMaths.matrix;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ui {
    public void printBeginning() {
        System.out.println("Enter the matrix expression you want to calculate.");
    }

    public void printInfo(){
        System.out.println("");
    }

    public void printResult(Tensor2D tensor){
        System.out.println("Result.");
        System.out.println(tensor);
    }


    public String readCommand(){
        Scanner in = new Scanner(System.in);
        System.out.print(">>> ");
        return in.nextLine();
    }
}

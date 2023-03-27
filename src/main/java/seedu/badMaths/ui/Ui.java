package seedu.badMaths.ui;

import java.util.ArrayList;

public class Ui {
    public static void printIncorrectFormatEntered(){
        System.out.println("Please enter the format as required.");
    }
    public static void printNegativeAmplitudeEntered(){
        System.out.println("Amplitude cannot be negative!");
    }

    public static void printAmplitude(double amplitude){
        System.out.println("This is the amplitude: "+ amplitude);
    }

    public static void printPhase(double phase){
        System.out.println("This is the phase: "+ phase);
    }
    public static void printVerticalShift(double verticalShift){
        System.out.println("This is the vertical shift: "+ verticalShift);
    }
    public static void printFrequency(Double freq){
        System.out.println("This is the freq (Hz): "+ freq);
    }

    public static void printNotes(ArrayList<String> notes) {
        if (notes.size() == 0) {
            System.out.println("You have no notes yet. :(");
        }
        else {
            System.out.println("Here are the notes you have stored:");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
            System.out.println("Now you have " + notes.size() + " tasks in the list.");
        }
    }
}

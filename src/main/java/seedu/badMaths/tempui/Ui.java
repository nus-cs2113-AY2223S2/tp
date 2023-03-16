package seedu.badMaths.tempui;

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
}

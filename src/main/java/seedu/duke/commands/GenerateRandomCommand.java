package seedu.duke.commands;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.ui.Ui;
import seedu.duke.exceptions.ErrorMessages;
import java.util.ArrayList;

public class GenerateRandomCommand extends Command {
    private int count;

    public GenerateRandomCommand(String userInput){
        if(isNumeric(userInput) && Integer.parseInt(userInput) > 0 ){
            this.count = Integer.parseInt(userInput);
        } else{
            ErrorMessages.invalidInputMessage();
        }

    }

    // Check if 2nd part of input is a positive integer
    public static boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public void executeCommand(Ui ui, GenerateExercise exerciseGenerator){
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateRandomSet(count);
        ui.printExerciseFromList(exercises);
    }
}

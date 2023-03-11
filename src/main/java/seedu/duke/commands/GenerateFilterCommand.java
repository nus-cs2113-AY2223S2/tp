
package seedu.duke.commands;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class GenerateFilterCommand extends Command {
    private final int count;
    private final String[] userCommands;

    public GenerateFilterCommand(String[] userCommands){
        this.count = userCommands.length;
        this.userCommands = userCommands;
    }
    
    public void executeCommand(Ui ui, GenerateExercise exerciseGenerator){
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateSetAll();
        int numberOfExercisesToGenerate = 0;
        for(int i=1; i<count; i++) {
            switch(userCommands[i]){
            case "gym":
                exercises = exerciseGenerator.generateFilteredGymSetFrom(exercises);
                break;
            case "static":
                exercises = exerciseGenerator.generateFilteredBodySetFrom(exercises);
                break;
            //add more filters here!
            /* 
            case "arms":
                exercises = filterByArms(exercises);
                break;
            case "legs":
                exercises = filterByLegs(exercises);
                break;
            case "core":
                exercises = filterByCore(exercises);
                break;
            case "easy":
                exercises = filterByEasy(exercises);
                break;
            case "medium":
                exercises = filterByMedium(exercises);
                break;
            case "hard":
                exercises = filterByHard(exercises);
                break;
            */
            default:
                try {
                    numberOfExercisesToGenerate = Integer.parseInt(userCommands[i]);
                } catch(NumberFormatException e) {
                    System.out.println("enter a valid number of exercises!");
                }
            }
        }
        exercises = exerciseGenerator.generateRandomSetFrom(exercises, numberOfExercisesToGenerate);
        ui.printExerciseFromList(exercises);
    }
}

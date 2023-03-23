package seedu.duke.ui;

import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userplan.UserPlan;

import java.util.ArrayList;
import java.util.HashMap;

public class Ui {
    private static final String SEPARATOR = "________________________________________";

    public void printExerciseFromList(ArrayList<ExerciseData> exerciseData) {
        PrintExercises.printExercise(exerciseData);
    }
    public void greetUser() {
        Greet.greet();
    }

    public void byeUser() {
        Bye.bye();
    }
    public void printFilters() {
        PrintHelpMessage.printFiltersAvailable();
    }
    public void unknownCommand() {
        PrintHelpMessage.unknownCommandMessage();
    }
    public void showPlan(UserPlan planner) {
        PrintPlanner.printPlanner( planner );
    }
    public void printHelp() {
        PrintHelpMessage.showAvailableCommands();
    }
    public void printPlannerHelp() {
        PrintHelpMessage.showAvailablePlannerCommands();
    }
    public void splitLine() {
        System.out.println(SEPARATOR);
    }

    public void printUserExerciseHistory(HashMap<String, Integer> userExerciseHistory) {
        PrintUserExerciseData.printUserExerciseHistory(userExerciseHistory);
    }

}

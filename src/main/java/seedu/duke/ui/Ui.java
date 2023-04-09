package seedu.duke.ui;

import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.userplan.UserPlan;


import java.util.ArrayList;
import java.util.HashMap;

public class Ui {
    private static final String SEPARATOR = "________________________________________";

    public void printExerciseFromList (ArrayList<ExerciseData> exerciseData) {
        PrintExercises.printExercise(exerciseData);
    }

    public void greetUser () {
        Greet.greet();
    }

    public void byeUser () {
        Bye.bye();
    }

    public void printFilters () {
        PrintHelpMessage.printFiltersAvailable();
    }

    public void unknownCommand () {
        PrintHelpMessage.unknownCommandMessage();
    }

    public void showPlan (UserPlan planner) {
        PrintPlanner.printPlanner(planner);
    }

    public void printHelp () {
        PrintHelpMessage.showAvailableCommands();
    }

    public void printPlannerHelp () {
        PrintHelpMessage.showAvailablePlannerCommands();
    }

    public void splitLine () {
        System.out.println(SEPARATOR);
    }

    public void plannerMode () {
        System.out.println("\n===>Planner Mode<===");
    }

    public void workoutMode () {
        System.out.println("\n===>Workout Mode<===");
    }

    //public void printUserExerciseHistory (HashMap<String, Integer> userExerciseHistory) {
    public void printUserExerciseHistory (HashMap<String, Integer> userExerciseHistory,
                                          int overallCount, int uniqueCount) {
        PrintUserExerciseData.printUserExerciseHistory(userExerciseHistory,
                overallCount,uniqueCount);
    }

    public void printPlannerGreeting () {
        PrintPlanner.printPlannerGreeting();
        this.plannerMode();
    }

    public void printExerciseSessionHelp () {
        PrintHelpMessage.showAvailableExerciseSessionCommands();
    }

}


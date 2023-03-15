package seedu.duke.ui;

import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

public class Ui {
    public void printExerciseFromList(ArrayList<ExerciseData>exerciseData){
        PrintExercises.printExercise(exerciseData);
    }

    public void greetUser(){
        Greet.greet();
    }

    public void byeUser(){
        Bye.bye();
    }

    public void printFilters() { Bye.printFiltersAvailable(); }

}

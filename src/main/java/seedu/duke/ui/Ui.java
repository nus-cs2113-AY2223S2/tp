package seedu.duke.ui;

import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;

public class Ui {
    private static final String SEPARATOR = "________________________________________";
    public void printExerciseFromList(ArrayList<ExerciseData>exerciseData){
        PrintExercises.printExercise(exerciseData);
    }

    public void greetUser(){
        Greet.greet();
    }

    public void byeUser(){
        Bye.bye();
    }

    public void splitLine(){
        System.out.println(SEPARATOR);
    }

}

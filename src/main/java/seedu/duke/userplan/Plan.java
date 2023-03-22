package seedu.duke.userplan;

import java.util.ArrayList;

//@@author Khulon
public class Plan {
    private ArrayList<String> exercisePlans;
    private String status;
    private String planName;
    private int noOfExercise;


    public Plan (ArrayList<String> exercisePlan, String planName) {
        this.exercisePlans = exercisePlan;
        this.status = " ";
        this.planName = planName;
        this.noOfExercise = 0;
    }
    public String getPlanName () {
        return planName;
    }
    public String getStatus () {
        return this.status;
    }
    public ArrayList<String> getExercisePlans () {
        return this.exercisePlans;
    }
    public ArrayList<String> getPlan() {
        return this.exercisePlans;
    }
    public void markComplete () {
        this.status = "X";
    }
}

package seedu.commands;


import seedu.calorietracker.CalorieTracker;
import seedu.commands.workoutcommands.StartDayCommand;
import seedu.commands.workoutcommands.StartWorkoutCommand;
import seedu.commands.workoutcommands.ViewWorkoutCommand;
import seedu.workout.Day;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Command {
    //protected static ArrayList<Workout> workoutArrayList;

    protected  WorkoutList workoutList = new WorkoutList();
    //protected HashMap<String, Workout> singleWorkout;
    protected CalorieTracker calorieTracker;
    protected Day singleWorkout;
    //protected HashMap<String, Workout> workouts = WorkoutList.getWorkoutsByDate();
    protected HashMap<Date, Day> workouts;
    //protected HashMap<String, Workout> workout;
    protected Day day = new Day();
    protected static Date date;
    protected Workout workoutForOneDay = new Workout();

    protected static boolean isWorkoutEntered;
    protected static boolean isDayEntered;

    public Command() {

       // this.day = workoutList.getDay();
        //workout = day.getWorkout();
        //workouts = workoutList.getWorkouts();
    }



    public Day getStartDay() {
        return day;
    }


/*  public void setStartDay(Day startDay) {
        this.startDay = startDay;
    }*/
    /* public static WorkoutList getWorkoutList() {
        return workoutList;
    }*/


    public static void setIsDayEntered(boolean isDayEnter) {
        isDayEntered = isDayEnter;
    }
    public static void setIsWorkoutEntered(boolean isWorkoutEnter) {
        isWorkoutEntered = isWorkoutEnter;
    }

    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker) throws IllegalArgumentException{
        if (workoutList == null || calorieTracker == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }
        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;


    }
    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}

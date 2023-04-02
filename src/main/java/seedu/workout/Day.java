package seedu.workout;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Day {
    private Date date;
    private HashMap<String, Workout> workoutsByDate;
    private Workout workout = new Workout();

    public Day(Date date) {
        this.date = date;
        this.workoutsByDate = new HashMap<>();
        //workout = new Workout();
    }

    public Day() {
        this.workoutsByDate = new HashMap<>();
    }

   /* public Date getDate() {
        return date;
    }*/

    public void addWorkout(String workoutName, Workout workout) {
        workoutsByDate.put(workoutName, workout);
        setWorkout(workout);
        setWorkoutsByDate(workoutsByDate);
    }


    public Workout getWorkout() {
        return this.workout;
    }


    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    /*public Workout getWorkouts(String name) {
                return workouts.get(name);
            }*/
    public HashMap<String, Workout> getWorkoutsByDate() {
        return this.workoutsByDate;
    }

    public void setWorkoutsByDate(HashMap<String, Workout> workoutsByDate) {
        this.workoutsByDate = workoutsByDate;
    }

}
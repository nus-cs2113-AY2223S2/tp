package seedu.workout;

import java.util.Date;
import java.util.HashMap;

//@@ author ZIZI-czh
public class Day {
    private Date date;
    private HashMap<String, Workout> workoutsByDate;
    private Workout workout = new Workout();
    private String workoutName;



    public Day(Date date) {
        this.date = date;
        this.workoutsByDate = new HashMap<>();
        //workout = new Workout();
    }

    //@@ author ZIZI-czh
    public Day() {
        this.workoutsByDate = new HashMap<>();
    }

    //@@ author ZIZI-czh
    public void addWorkout(String workoutName, Workout workout) {
        workoutsByDate.put(workoutName, workout);
        setWorkout(workout);
        setWorkoutsByDate(workoutsByDate);
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    //@@ author ZIZI-czh
    public Workout getWorkout() {
        return this.workout;
    }


    //@@ author ZIZI-czh
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    //@@ author ZIZI-czh
    public HashMap<String, Workout> getWorkoutsByDate() {
        return this.workoutsByDate;
    }

    //@@ author ZIZI-czh
    public void setWorkoutsByDate(HashMap<String, Workout> workoutsByDate) {
        this.workoutsByDate = workoutsByDate;
    }
}

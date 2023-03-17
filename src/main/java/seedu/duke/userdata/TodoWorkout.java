package seedu.duke.userdata;

import java.time.LocalDateTime;
import seedu.duke.exersisedata.ExerciseData;

public class TodoWorkout {
    private final LocalDateTime dateAdded;
    private final ExerciseData exerciseData;
    private LocalDateTime dateComplete;
    private String status;

    /**
     * Constructs a new workout that the user has added to their list. Initialises the status of the workout and sets
     * the datetime when workout was added
     *
     * @param exerciseTodo the exercise which the user plans to do
     */
    public TodoWorkout(ExerciseData exerciseTodo) {
        this.exerciseData = exerciseTodo;
        this.dateAdded = LocalDateTime.now();
        this.status = "Incomplete";
    }

    /**
     * Gets the current status of the TodoWorkout
     *
     * @return status of current workout whether it is complete or incomplete
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Gets the datetime in which the TodoWorkout was last added
     *
     * @return datetime object of when TodoWorkout was added
     */
    public LocalDateTime getDateAdded() {
        return this.dateAdded;
    }

    /**
     * Gets the exercise object in which user had added to their list
     *
     * @return exercise data of the exercise added to their TodoWorkout
     */
    public ExerciseData getExerciseData() {
        return this.exerciseData;
    }
    /**
     * Marks the TodoWorkout as completed
     */
    public void markComplete() {
        this.status = "Complete";
        this.dateComplete = LocalDateTime.now();
    }

}

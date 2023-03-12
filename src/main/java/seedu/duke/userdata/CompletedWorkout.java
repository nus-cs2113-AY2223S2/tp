package seedu.duke.userdata;

import java.time.LocalDateTime;
import seedu.duke.exersisedata.ExerciseData;

public class CompletedWorkout {

    private final LocalDateTime dateComplete;
    private final ExerciseData completedExercise;
    public CompletedWorkout(ExerciseData completedExercise){
        this.completedExercise = completedExercise;
        this.dateComplete = LocalDateTime.now();
    }

    public LocalDateTime getDateComplete() {
        return dateComplete;
    }

    public ExerciseData getCompletedExercise() {
        return completedExercise;
    }
}

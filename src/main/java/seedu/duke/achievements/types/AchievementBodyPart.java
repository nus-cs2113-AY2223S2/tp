package seedu.duke.achievements.types;

import seedu.duke.achievements.Achievement;
import seedu.duke.achievements.AchievementDifficulty;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

public class AchievementBodyPart extends Achievement {
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";
    private final String bodyPartType;
    public AchievementBodyPart (String name, String requirement, boolean completed,
                             AchievementDifficulty difficulty, String achievementBodyPart,
                             int countCurrent, int countToComplete) {
        super (name, requirement, completed, difficulty, achievementBodyPart, countCurrent, countToComplete);
        bodyPartType = achievementBodyPart;
    }

    public boolean updateIndex(ExerciseData exercise) {
        String workoutTypeRaw = exercise.getWorkoutType().toString();
        int start = workoutTypeRaw.indexOf(OPEN_BRACE);
        int end = workoutTypeRaw.indexOf(CLOSE_BRACE);
        String workoutTypeFinal = workoutTypeRaw.substring(start + 1, end);
        if (workoutTypeFinal.equals(bodyPartType)) {
            countCurrent += 1;
        }
        if (!this.getCompleted() && countCurrent == countToComplete) {
            this.complete();
            return true;
        }
        return false;
    }
}

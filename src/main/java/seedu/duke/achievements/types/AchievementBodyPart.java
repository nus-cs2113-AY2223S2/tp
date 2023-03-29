package seedu.duke.achievements.types;

import seedu.duke.achievements.Achievement;
import seedu.duke.achievements.AchievementDifficulty;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

public class AchievementBodyPart extends Achievement {
    private final String bodyPartType;
    public AchievementBodyPart (String name, String requirement, boolean completed,
                             AchievementDifficulty difficulty, String achievementBodyPart,
                             int countCurrent, int countToComplete) {
        super (name, requirement, completed, difficulty, achievementBodyPart, countCurrent, countToComplete);
        bodyPartType = achievementBodyPart;
    }

    public boolean updateIndex(ExerciseData exercise) {
        if (exercise.getWorkoutType().equals(bodyPartType)) {
            countCurrent += 1;
        }
        if (!this.isCompleted() && countCurrent == countToComplete) {
            this.complete();
            return true;
        }
        return false;
    }
}

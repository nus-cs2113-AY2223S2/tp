package seedu.duke.achievements.types;

import seedu.duke.achievements.Achievement;
import seedu.duke.achievements.AchievementDifficulty;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

public class AchievementLevel extends Achievement {
    private final String levelType;
    public AchievementLevel (String name, String requirement, boolean completed,
                             AchievementDifficulty difficulty, String achievementLevel,
                             int countCurrent, int countToComplete) {
        super (name, requirement, completed, difficulty, achievementLevel, countCurrent, countToComplete);
        levelType = achievementLevel;
    }

    public boolean updateIndex(ExerciseData exercise) {
        String level;
        if (levelType.toString().equals("easy")) {
            level = "beginner";
        } else if (levelType.toString().equals("medium")) {
            level = "intermediate";
        } else {
            level = "expert";
        }
        if (exercise.getLevel().equals(level)) {
            countCurrent += 1;
        }

        if (!this.getCompleted() && countCurrent == countToComplete) {
            this.complete();
            return true;
        }
        return false;
    }

}

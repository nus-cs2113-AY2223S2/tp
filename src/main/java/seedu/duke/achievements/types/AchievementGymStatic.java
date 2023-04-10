package seedu.duke.achievements.types;

import seedu.duke.achievements.Achievement;
import seedu.duke.achievements.AchievementDifficulty;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

public class AchievementGymStatic extends Achievement {
    private static final String NULL = "null";
    private static final String OUTPUT_BODY = "body only";
    private final String staticOrGym;
    public AchievementGymStatic (String name, String requirement, boolean completed,
                                 AchievementDifficulty difficulty, String achievementBodyPart,
                                 int countCurrent, int countToComplete) {
        super (name, requirement, completed, difficulty, achievementBodyPart, countCurrent, countToComplete);
        staticOrGym = achievementBodyPart;
    }


    public boolean updateIndex(ExerciseData exercise) {
        if (staticOrGym.equals("static")) {
            if (!exercise.getEquipment().equals(NULL) &&
                    exercise.getEquipment().equals(OUTPUT_BODY)) {
                countCurrent += 1;
            }
        } else {
            assert staticOrGym.equals("gym");
            if (!exercise.getEquipment().equals(NULL) &&
                    !exercise.getEquipment().equals(OUTPUT_BODY)) {
                countCurrent += 1;
            }
        }
        if (!this.getCompleted() && countCurrent == countToComplete) {
            this.complete();
            return true;
        }
        return false;
    }

}



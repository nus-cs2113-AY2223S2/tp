package seedu.duke.achievements;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.Session;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.data.userdata.userplan.UserPlan;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.storage.Storage;

import java.util.ArrayList;

//import static org.junit.jupiter.api.Assertions.;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadAchievement {
    private static final int EASY_START_INDEX = 0;
    private static final int MEDIUM_START_INDEX = 3;
    private static final int HARD_START_INDEX = 6;
    private static final int CORE_START_INDEX = 9;
    private static final int LEG_START_INDEX = 12;
    private static final int UPPER_START_INDEX = 15;
    private static final int GYM_START_INDEX = 18;
    private static final int STATIC_START_INDEX = 21;
    private static final int E_ACHIEVEMENT_REQUIREMENT = 1;
    private static final int M_ACHIEVEMENT_REQUIREMENT = 3;
    private static final int H_ACHIEVEMENT_REQUIREMENT = 5;
    private static final int M_ACHIEVEMENT_INDEX_INCREMENT = 1;
    private static final int H_ACHIEVEMENT_INDEX_INCREMENT = 2;

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";
    private static final String UPPER = "upper";
    private static final String CORE = "core";
    private static final String LEGS = "legs";
    private static final String NULL = "null";
    private static final String OUTPUT_BODY = "body only";

    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String EXPERT = "expert";
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";
    AchievementListHandler achievementListTestHandler = new AchievementListHandler();
    private static final int NUMBER_OF_PRELOADED_ACHIEVEMENTS = 24;

    //Do note that running this test causes your achievement data to be erased

    /**
     * Ensures that when the base achievements are loaded initially, they have 0 count, and are not completed.
     */
    @Test
    void testAchievementLoadFromFileCorrectly() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        for (int i = 0; i < achievementList.size(); i++) {
            assertEquals(achievementList.get(i).getCompleted(), false);
            assertEquals(achievementList.get(i).countCurrent, 0);
        }
    }
}

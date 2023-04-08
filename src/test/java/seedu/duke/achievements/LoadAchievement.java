package seedu.duke.achievements;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadAchievement {
    private static final int NUMBER_OF_PRELOADED_ACHIEVEMENTS = 24;
    AchievementListHandler achievementListTestHandler = new AchievementListHandler();

    //Do note that running achievement tests cause your the achievement data to be erased (i.e. achievements count
    //set to 0

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

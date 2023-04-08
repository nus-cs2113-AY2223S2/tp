package seedu.duke.achievements;

import org.junit.jupiter.api.Test;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.Session;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.data.userdata.userplan.UserPlan;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.storage.Storage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These tests test static and gym exercises.
 */
public class AchieveGymStaticAchievements {
    private static final int GYM_START_INDEX = 18;
    private static final int STATIC_START_INDEX = 21;
    private static final int E_ACHIEVEMENT_REQUIREMENT = 1;
    private static final int H_ACHIEVEMENT_REQUIREMENT = 5;
    private static final int M_ACHIEVEMENT_INDEX_INCREMENT = 1;
    private static final int H_ACHIEVEMENT_INDEX_INCREMENT = 2;
    private static final int NUMBER_OF_PRELOADED_ACHIEVEMENTS = 24;
    AchievementListHandler achievementListTestHandler = new AchievementListHandler();

    Storage dummyStorage = new Storage() {
        @Override
        public void writeToJson(UserCareerData userCareerData){}
        @Override
        public void writeToJson(UserPlan userPlan){}
        @Override
        public UserCareerData loadUserData() {
            return null;
        }
        @Override
        public UserPlan loadUserPlans() {
            return null;
        }
    };

    @Test
    void testEGymAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();
        for (int i = 0; i < E_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }
        Session session = new Session(actualExerciseData);

        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(GYM_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(GYM_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(GYM_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testHGymAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        exerciseData = generateExercise.generateFilteredGymSetFrom(exerciseData);
        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();
        for (int i = 0; i < H_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }
        Session session = new Session(actualExerciseData);

        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(GYM_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(GYM_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(GYM_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
    }

    @Test
    void testEStaticAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();
        for (int i = 0; i < E_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }
        Session session = new Session(actualExerciseData);

        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(STATIC_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(STATIC_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(STATIC_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testHStaticAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        exerciseData = generateExercise.generateFilteredStaticSetFrom(exerciseData);
        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();
        for (int i = 0; i < H_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }
        Session session = new Session(actualExerciseData);

        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(STATIC_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(STATIC_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(STATIC_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
    }

}

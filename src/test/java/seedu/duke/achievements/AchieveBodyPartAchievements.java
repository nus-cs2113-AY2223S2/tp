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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AchieveBodyPartAchievements {
    private static final int E_ACHIEVEMENT_REQUIREMENT = 1;
    private static final int M_ACHIEVEMENT_REQUIREMENT = 3;
    private static final int H_ACHIEVEMENT_REQUIREMENT = 5;
    private static final int M_ACHIEVEMENT_INDEX_INCREMENT = 1;
    private static final int H_ACHIEVEMENT_INDEX_INCREMENT = 2;
    private static final int CORE_START_INDEX = 9;
    private static final int LEG_START_INDEX = 12;
    private static final int UPPER_START_INDEX = 15;
    private static final String UPPER = "upper";
    private static final String CORE = "core";
    private static final String LEGS = "legs";
    private static final int NUMBER_OF_PRELOADED_ACHIEVEMENTS = 24;
    AchievementListHandler achievementListTestHandler = new AchievementListHandler();


    @Test
    void testECoreAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }
        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();
        for (int i = 0; i < E_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }
        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(CORE_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(CORE_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(CORE_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testMCoreAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }
        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < M_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }


        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(CORE_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(CORE_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(CORE_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testHCoreAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, CORE);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }
        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < H_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(CORE_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(CORE_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(CORE_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
    }

    @Test
    void testELegsAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }


        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < E_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(LEG_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(LEG_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(LEG_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testMLegsAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }


        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < M_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(LEG_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(LEG_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(LEG_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testHLegsAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, LEGS);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }


        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < H_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(LEG_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(LEG_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(LEG_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
    }

    @Test
    void testEUpperBodyAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }


        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < E_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(UPPER_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(UPPER_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(UPPER_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testMUpperBodyAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }


        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < M_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(UPPER_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(UPPER_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(UPPER_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testHUpperBodyAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try {
            exerciseData = generateExercise.generateFilteredWorkoutTypeFrom(exerciseData, UPPER);
        } catch (DukeError e) {
            System.out.println("Error occured when using the generate filtered difficulty set");
        }


        ArrayList<ExerciseData> actualExerciseData = new ArrayList<>();

        for (int i = 0; i < H_ACHIEVEMENT_REQUIREMENT; i++) {
            actualExerciseData.add(exerciseData.get(i));
        }

        Session session = new Session(actualExerciseData);
        Storage dummyStorage = new Storage() {
            @Override
            public void writeToJson(UserCareerData userCareerData) throws DukeError {
            }

            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {
            }

            @Override
            public UserCareerData loadUserData() {
                return null;
            }

            @Override
            public UserPlan loadUserPlans() {
                return null;
            }
        };
        ExerciseStateHandler exerciseStateHandler = new ExerciseStateHandler(dummyStorage);
        exerciseStateHandler.updateWorkoutAchievements(session, achievementListTestHandler);
        ArrayList<Achievement> achievementList = achievementListTestHandler.getAchievementList();
        assertEquals(achievementList.get(UPPER_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(UPPER_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(UPPER_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
    }
}

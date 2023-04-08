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

public class AchieveDifficultyAchievements {
    private static final int EASY_START_INDEX = 0;
    private static final int MEDIUM_START_INDEX = 3;
    private static final int HARD_START_INDEX = 6;

    private static final int E_ACHIEVEMENT_REQUIREMENT = 1;
    private static final int M_ACHIEVEMENT_REQUIREMENT = 3;
    private static final int H_ACHIEVEMENT_REQUIREMENT = 5;
    private static final int M_ACHIEVEMENT_INDEX_INCREMENT = 1;
    private static final int H_ACHIEVEMENT_INDEX_INCREMENT = 2;

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";
    private static final int NUMBER_OF_PRELOADED_ACHIEVEMENTS = 24;
    AchievementListHandler achievementListTestHandler = new AchievementListHandler();

    @Test
    void testEEasyAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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


        assertEquals(achievementList.get(EASY_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(EASY_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(EASY_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testMEasyAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}
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

        assertEquals(achievementList.get(EASY_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(EASY_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(EASY_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);

    }

    @Test
    void testHEasyAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, EASY);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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

        assertEquals(achievementList.get(EASY_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(EASY_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(EASY_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);

    }

    @Test
    void testEMediumAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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
        assertEquals(achievementList.get(MEDIUM_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(MEDIUM_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(MEDIUM_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testMMediumAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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
        assertEquals(achievementList.get(MEDIUM_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(MEDIUM_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(MEDIUM_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testHMediumAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, MEDIUM);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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
        assertEquals(achievementList.get(MEDIUM_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(MEDIUM_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(MEDIUM_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
    }

    @Test
    void testEHardAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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
        assertEquals(achievementList.get(HARD_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(HARD_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
        assertEquals(achievementList.get(HARD_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testMHardAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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
        assertEquals(achievementList.get(HARD_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(HARD_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(HARD_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), false);
    }

    @Test
    void testHHardAchievementUpdateSession() {
        achievementListTestHandler.clearAchievementsData();
        assertEquals(achievementListTestHandler.getAchievementList().size(), NUMBER_OF_PRELOADED_ACHIEVEMENTS);
        ArrayList<ExerciseData> exerciseData;
        GenerateExercise generateExercise = new GenerateExercise();
        exerciseData = generateExercise.generateSetAll();
        try{
            exerciseData = generateExercise.generateFilteredDifficultySetFrom(exerciseData, HARD);
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
            public void writeToJson(UserCareerData userCareerData) throws DukeError {}
            @Override
            public void writeToJson(UserPlan userPlan) throws DukeError {}

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
        assertEquals(achievementList.get(HARD_START_INDEX).getCompleted(), true);
        assertEquals(achievementList.get(HARD_START_INDEX + M_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
        assertEquals(achievementList.get(HARD_START_INDEX + H_ACHIEVEMENT_INDEX_INCREMENT).getCompleted(), true);
    }
}

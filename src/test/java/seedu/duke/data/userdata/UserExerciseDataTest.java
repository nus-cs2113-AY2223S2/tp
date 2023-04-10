package seedu.duke.data.userdata;

import org.junit.jupiter.api.Test;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserExerciseDataTest {

    @Test
    void testAddUserExerciseHistory() {
        // create a UserCareerData object with some session data
        UserCareerData userCareerData = new UserCareerData();
        ArrayList<ExerciseData> exerciseDataArrayList = new ArrayList<>();
        ExerciseData exerciseData = new ExerciseData();
        List<String> workoutType = new ArrayList<>();
        workoutType.add("upper");
        List<String> instructions = new ArrayList<>();
        instructions.add("Arms move down, then move up.");
        exerciseData.setName("push up");
        exerciseData.setLevel("easy");
        exerciseData.setInstructions(instructions);
        exerciseData.setWorkoutType(workoutType);

        exerciseDataArrayList.add(exerciseData);
        Session session = new Session(exerciseDataArrayList);
        userCareerData.addWorkoutSession(session);
        int size = UserExerciseData.addUserExerciseHistory(userCareerData).size();

        assertEquals(1,size);
    }

    @Test
    void testTotalSessionSize() {
        UserCareerData userCareerData = new UserCareerData();
        ArrayList<ExerciseData> exerciseDataArrayList = new ArrayList<>();
        ExerciseData exerciseData = new ExerciseData();
        List<String> workoutType = new ArrayList<>();
        workoutType.add("upper");
        List<String> instructions = new ArrayList<>();
        instructions.add("Arms move down, then move up.");
        exerciseData.setName("push up");
        exerciseData.setLevel("easy");
        exerciseData.setInstructions(instructions);
        exerciseData.setWorkoutType(workoutType);

        exerciseDataArrayList.add(exerciseData);
        Session session = new Session(exerciseDataArrayList);
        userCareerData.addWorkoutSession(session);
        userCareerData.addWorkoutSession(session);
        int size = UserExerciseData.totalExerciseSessionSize(userCareerData);
        assertEquals(2, size);
    }

    @Test
    void testTotalUniqueSessionSize() {
        UserCareerData userCareerData = new UserCareerData();
        ArrayList<ExerciseData> exerciseDataArrayList = new ArrayList<>();
        ExerciseData exerciseData = new ExerciseData();
        List<String> workoutType = new ArrayList<>();
        workoutType.add("upper");
        List<String> instructions = new ArrayList<>();
        instructions.add("Arms move down, then move up.");
        exerciseData.setName("push up");
        exerciseData.setLevel("easy");
        exerciseData.setInstructions(instructions);
        exerciseData.setWorkoutType(workoutType);
        exerciseData.setName("push up");
        exerciseData.setLevel("easy");
        exerciseData.setInstructions(instructions);
        exerciseData.setWorkoutType(workoutType);

        exerciseDataArrayList.add(exerciseData);
        Session session = new Session(exerciseDataArrayList);
        userCareerData.addWorkoutSession(session);
        HashMap<String, Integer> newMap = UserExerciseData.addUserExerciseHistory(userCareerData);
        int size = UserExerciseData.totalUniqueSessionSize(newMap);
        assertEquals(1, size);
    }
}

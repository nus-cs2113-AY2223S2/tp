package seedu.duke.data.userdata;

import org.junit.jupiter.api.Test;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserCareerDataTest {
    @Test
    void testPrintAllFinishedSessions() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        UserCareerData userCareerData = new UserCareerData();
        userCareerData.printAllFinishedWorkoutSessions();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "You have not completed any exercises :(\r\n" +
                    "Add on to this list by completing a workout session!\r\n";
        } else {
            expectedOutput = "You have not completed any exercises :(\n" +
                    "Add on to this list by completing a workout session!\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void testAddSession() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        ArrayList<ExerciseData> exerciseList = new ArrayList<>();
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> exerciseData = generateExercise.generateSetAll();
        exerciseList.add(exerciseData.get(773));
        UserCareerData userCareerData = new UserCareerData();
        Session session = new Session(exerciseList);
        userCareerData.addWorkoutSession(session);
        userCareerData.printAllFinishedWorkoutSessions();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Session 1\r\n" +
                    "On this date: 2023-04-08\r\n" +
                    "Exercise ID: 773. \r\n" +
                    "Name: Standing One-Arm Cable Curl\r\n" +
                    "Difficulty Level: intermediate\r\n" +
                    "Workout Type: upper body\r\n" +
                    "Start out by grabbing single handle next to the low pulley machine. " +
                    "Make sure you are far enough from the machine so that your arm is " +
                    "supporting the weight. Make sure that your upper arm is stationary " +
                    "perpendicular to the floor with elbows in and palms facing forward. " +
                    "Your non lifting arm should be grabbing your waist. This will allow " +
                    "you to keep your balance. Slowly begin to curl the single handle " +
                    "upwards while keeping the upper arm stationary until your forearm " +
                    "touches your bicep while exhaling. Tip: Only the forearm should move. " +
                    "Hold the contraction position as you squeeze the bicep and then lower " +
                    "the single handle back down to the starting position as you inhale. " +
                    "Repeat for the recommended amount of repetitions. Switch upper body" +
                    " while performing this exercise.\r\n" +
                    "\r\n";
        } else {
            expectedOutput = "Session 1\n" +
                    "On this date: 2023-04-08\n" +
                    "Exercise ID: 773. \n" +
                    "Name: Standing One-Arm Cable Curl\n" +
                    "Difficulty Level: intermediate\n" +
                    "Workout Type: upper body\n" +
                    "Start out by grabbing single handle next to the low pulley machine. " +
                    "Make sure you are far enough from the machine so that your arm is " +
                    "supporting the weight. Make sure that your upper arm is stationary " +
                    "perpendicular to the floor with elbows in and palms facing forward. " +
                    "Your non lifting arm should be grabbing your waist. This will allow " +
                    "you to keep your balance. Slowly begin to curl the single handle " +
                    "upwards while keeping the upper arm stationary until your forearm " +
                    "touches your bicep while exhaling. Tip: Only the forearm should move. " +
                    "Hold the contraction position as you squeeze the bicep and then lower " +
                    "the single handle back down to the starting position as you inhale. " +
                    "Repeat for the recommended amount of repetitions. Switch upper body" +
                    " while performing this exercise.\n" +
                    "\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }
    @Test
    void testDeleteSession() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        ArrayList<ExerciseData> exerciseList = new ArrayList<>();
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> exerciseData = generateExercise.generateSetAll();
        exerciseList.add(exerciseData.get(773));
        UserCareerData userCareerData = new UserCareerData();
        Session session = new Session(exerciseList);
        userCareerData.addWorkoutSession(session);
        userCareerData.deleteWorkoutSession(1);
        userCareerData.printAllFinishedWorkoutSessions();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "You have not completed any exercises :(\r\n" +
                    "Add on to this list by completing a workout session!\r\n";
        } else {
            expectedOutput = "You have not completed any exercises :(\n" +
                    "Add on to this list by completing a workout session!\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }
}
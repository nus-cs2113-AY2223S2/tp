package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.userdata.Session;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.userplan.UserPlan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class TestUi {
    /**
     * Checks if the ui.splitLine() method prints the correct output.
     */
    @Test
    void testSplitLine() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.splitLine();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "________________________________________\r\n";
        } else {
            expectedOutput = "________________________________________\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    /**
     * Checks if the ui.printFilters() method prints the correct output.
     */
    @Test
    void testPrintFiltersAvailable() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printFilters();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "By place:\r\n" +
                    "\t[gym] exercises that can be done with gym equipment\r\n" +
                    "\t[static]: exercises that only require your body\r\n" +
                    "By difficulty:\r\n" +
                    "\t[easy] exercises of low intensity\r\n" +
                    "\t[medium] exercises of medium intensity\r\n" +
                    "\t[hard] exercises of hard intensity\r\n" +
                    "By Body part:\r\n" +
                    "\t[upper] exercises that train your upper body\r\n" +
                    "\t[core] exercises that train your core\r\n" +
                    "\t[legs] exercises that train your legs\r\n";
        } else {
            expectedOutput = "By place:\n" +
                    "\t[gym] exercises that can be done with gym equipment\n" +
                    "\t[static]: exercises that only require your body\n" +
                    "By difficulty:\n" +
                    "\t[easy] exercises of low intensity\n" +
                    "\t[medium] exercises of medium intensity\n" +
                    "\t[hard] exercises of hard intensity\n" +
                    "By Body part:\n" +
                    "\t[upper] exercises that train your upper body\n" +
                    "\t[core] exercises that train your core\n" +
                    "\t[legs] exercises that train your legs\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    /**
     * Checks if the ui.unknownCommand() method prints the correct output.
     */
    @Test
    void testUnknownCommand() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.unknownCommand();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Unknown command! Type [help] to see what we can do!\r\n";
        } else {
            expectedOutput = "Unknown command! Type [help] to see what we can do!\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    /**
     * Checks if the ui.printHelp() method prints the correct output.
     */
    @Test
    void testPrintHelp() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printHelp();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "These are some commands available:\r\n" +
                    "[generate]\r\n" +
                    "\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x\r\n" +
                    "\tFILTER stands for a specific requirement you want to include in your exercise\r\n" +
                    "[filters]\r\n" +
                    "\tView all available filters\r\n" +
                    "[plans]\r\n" +
                    "\tShow all plans\r\n" +
                    "[planner]\r\n" +
                    "\tEnter workout plan editor\r\n" +
                    "[find]\r\n" +
                    "\tfinds all relevant exercises based on the keyword : find [keyword]\r\n" +
                    "[bye]\r\n" +
                    "\tEnd the program\r\n";
        } else {
            expectedOutput = "These are some commands available:\n" +
                    "[generate]\n" +
                    "\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x\n" +
                    "\tFILTER stands for a specific requirement you want to include in your exercise\n" +
                    "[filters]\n" +
                    "\tView all available filters\n" +
                    "[plans]\n" +
                    "\tShow all plans\n" +
                    "[planner]\n" +
                    "\tEnter workout plan editor\n" +
                    "[find]\n" +
                    "\tfinds all relevant exercises based on the keyword : find [keyword]\n" +
                    "[bye]\n" +
                    "\tEnd the program\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    /**
     * Checks if the ui.greetUser() method prints the correct output.
     */
    @Test
    void testGreetUser() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.greetUser();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Hello from\r\n"
                    + "    _______ __                          ____        __      \r\n" +
                    "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \r\n" +
                    "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\r\n" +
                    " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\r\n" +
                    "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ \r\n" +
                    "Start your fitness journey! Type [help] to see the things you can do!\r\n";
        } else {
            expectedOutput = "Hello from\n"
                    + "    _______ __                          ____        __      \n" +
                    "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \n" +
                    "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\n" +
                    " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\n" +
                    "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ \n" +
                    "Start your fitness journey! Type [help] to see the things you can do!\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }


    /**
     * Checks if the ui.byeUser() method prints the correct output.
     */
    @Test
    void testByeUser() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.byeUser();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "    ____             __\r\n" +
                    "   / __ )__  _____  / /\r\n" +
                    "  / __  / / / / _ \\/ / \r\n" +
                    " / /_/ / /_/ /  __/_/  \r\n" +
                    "/_____/\\__, /\\___(_)   \r\n" +
                    "      /____/    \r\n" +
                    "Thanks for using Fitness Duke!\r\n" +
                    "\r\n" +
                    "Hope to see you again!\r\n" +
                    "\r\n";
        } else {
            expectedOutput = "    ____             __\n" +
                    "   / __ )__  _____  / /\n" +
                    "  / __  / / / / _ \\/ / \n" +
                    " / /_/ / /_/ /  __/_/  \n" +
                    "/_____/\\__, /\\___(_)   \n" +
                    "      /____/    \n" +
                    "Thanks for using Fitness Duke!\n" +
                    "\n" +
                    "Hope to see you again!\n" +
                    "\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    /**
     * Checks if the ui.printPlannerHelp() method prints the correct output.
     */
    @Test
    void testPrintPlannerHelp() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printPlannerHelp();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "These are some commands available: \r\n" +
                    "[add]\r\n" +
                    "\tCreate a new plan on a day of the week: add monday plan_name FILTER1 FILTER2 ... x\r\n" +
                    "\tFILTER stands for a specific requirement you want to include in your exercise\r\n" +
                    "[delete]\r\n" +
                    "\tdelete a plan on a day of the week: delete monday plan_name\r\n" +
                    "[plans]\r\n" +
                    "\tShow all plans\r\n" +
                    "[filters]\r\n" +
                    "\tView all available filters\r\n" +
                    "[exit]\r\n" +
                    "\tExit workout plan editor\r\n";
        } else {
            expectedOutput = "These are some commands available: \n" +
                    "[add]\n" +
                    "\tCreate a new plan on a day of the week: add monday plan_name FILTER1 FILTER2 ... x\n" +
                    "\tFILTER stands for a specific requirement you want to include in your exercise\n" +
                    "[delete]\n" +
                    "\tdelete a plan on a day of the week: delete monday plan_name\n" +
                    "[plans]\n" +
                    "\tShow all plans\n" +
                    "[filters]\n" +
                    "\tView all available filters\n" +
                    "[exit]\n" +
                    "\tExit workout plan editor\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    /**
     * Checks if printUserExerciseHistory() method prints the correct output.
     */
    @Test
    void testPrintUserExerciseHistory() {
        //add one value then compare.
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        HashMap<String,Integer> userExerciseDataMap = new HashMap<>();
        String exerciseName = "3/4 Sit-Up";
        String exerciseNameTwo = "Ab Crunch Machine";

        if (userExerciseDataMap.containsKey(exerciseName)) {
            int count = userExerciseDataMap.get(exerciseName);
            userExerciseDataMap.put(exerciseName, count + 1);
        } else {
            userExerciseDataMap.put(exerciseName, 1);
        }

        Ui ui = new Ui();
        ui.printUserExerciseHistory(userExerciseDataMap);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Here is a list of all the exercises you have completed:\r\n" +
                    "\r\n" +
                    "Exercise: 3/4 Sit-Up" + "\tFrequency of Completion: 1\r\n";
        } else {
            expectedOutput = "Exercise: 3/4 Sit-Up" + "\tFrequency of Completion: 1\n";
        }
        assertEquals(expectedOutput,actualOutput.toString());
    }

    //test for show plan.
    /*@Test
    void testShowPlan() {
        UserPlan planner = new UserPlan();

    }*/

    //To be completed
    /*@Test
    void testPrintExerciseFromList() {
        String OPEN_BRACE = "[";
        String CLOSE_BRACE = "]";
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        ArrayList<ExerciseData> exerciseData;
        List<String>myList = new ArrayList<>();
        myList.add("core");

        ExerciseData dummy1 = new ExerciseData();
        dummy1.setName("3/4 Sit-Up");
        dummy1.setWorkoutType(myList);
        dummy1.setLevel("beginner");
        dummy1.getId() = "1";

        Ui ui = new Ui();
        ui.printExerciseFromList(exerciseData);
        if (os.contains("Windows")) {
            for (ExerciseData exercise : exerciseData) {
                String getWorkoutType = exercise.getWorkoutType().toString();
                String getInstructions = exercise.getInstructions().toString();
                int start = getWorkoutType.indexOf(OPEN_BRACE);
                int end = getWorkoutType.indexOf(CLOSE_BRACE);
                int startInstructions = getInstructions.indexOf(OPEN_BRACE);
                int endInstructions = getInstructions.indexOf(CLOSE_BRACE);
                String getWorkoutTypeFinal = getWorkoutType.substring(start + 1, end);
                String getInstructionsFinal = getInstructions.substring(startInstructions + 1, endInstructions);
                expectedOutput = "Exercise ID: " + exercise.getId() + ". \r\n" +
                        "Name: " + exercise.getName() + "\r\n" +
                        "Difficulty Level: " + exercise.getLevel() + "\r\n" +
                        "Workout Type: " + getWorkoutTypeFinal + "\r\n" +
                        getInstructionsFinal + "\r\n";
                assertEquals(expectedOutput, actualOutput.toString());
            }
        } else {
            for (ExerciseData exercise : exerciseData) {
                String getWorkoutType = exercise.getWorkoutType().toString();
                String getInstructions = exercise.getInstructions().toString();
                int start = getWorkoutType.indexOf(OPEN_BRACE);
                int end = getWorkoutType.indexOf(CLOSE_BRACE);
                int startInstructions = getInstructions.indexOf(OPEN_BRACE);
                int endInstructions = getInstructions.indexOf(CLOSE_BRACE);
                String getWorkoutTypeFinal = getWorkoutType.substring(start + 1, end);
                String getInstructionsFinal = getInstructions.substring(startInstructions + 1, endInstructions);
                expectedOutput = "Exercise ID: " + exercise.getId() + ". \n" +
                        "Name: " + exercise.getName() + "\n" +
                        "Difficulty Level: " + exercise.getLevel() + "\n" +
                        "Workout Type: " + getWorkoutTypeFinal + "\n" +
                        getInstructionsFinal + "\n";
                assertEquals(expectedOutput, actualOutput.toString());
            }
        }
    }*/
}

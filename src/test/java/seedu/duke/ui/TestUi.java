package seedu.duke.ui;

import org.junit.jupiter.api.Test;

import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.userplan.UserPlan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestUi {
    //@@author L-K-Chng

    /**
     * Checks if the ui.splitLine() method prints the correct output.
     */
    @Test
    void testSplitLine () {
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

    //@@author L-K-Chng

    /**
     * Checks if the ui.printFilters() method prints the correct output.
     */
    @Test
    void testPrintFiltersAvailable () {
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

    //@@author L-K-Chng

    /**
     * Checks if the ui.unknownCommand() method prints the correct output.
     */
    @Test
    void testUnknownCommand () {
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

    //@@author L-K-Chng

    /**
     * Checks if the ui.printHelp() method prints the correct output.
     */
    @Test
    void testPrintHelp () {
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
                "[ippt] [AGE] [RUNTIME] [PUSHUPs] [SITUPs]\r\n" +
                "\tStarts an IPPT exercise session with input repetitions for the 3 sets of exercises.\r\n" +
                "[start]\r\n" +
                "\tStart a workout session\r\n" +
                "[history]\r\n" +
                "\tView the information on all workout sessions you have completed\r\n" +
                "[data]\r\n" +
                "\tView your completed exercises as well as the number of times" +
                " you have completed each exercise\r\n" +
                "[delete]\r\n" +
                "\tDelete a workout session you have within your workout history: delete NUMBER\r\n" +
                "\tNUMBER refers to the session number of the workout session you wish to delete\r\n" +
                "[plans]\r\n" +
                "\tShow all plans\r\n" +
                "[planner]\r\n" +
                "\tEnter workout plan editor\r\n" +
                "[quick]\r\n" +
                "\tGenerate a planned exercise: quick PLAN_NAME x\r\n" +
                "\tPLAN_NAME needs has to be in your planner, and x is the number of exercises\r\n" +
                "[find]\r\n" +
                "\tfinds all relevant exercises based on the keyword : find [keyword]\r\n" +
                "[achievements]\r\n" +
                "\tShows all the available achievements, their requirements and whether they have been achieved " +
                "or not\r\n" +
                "[clear_achievements]\r\n" +
                "\tClears all the data of finished exercises for the achievements database, resetting " +
                "counters for all achievements.  \n\tDo note that this command does not clear the counters for " +
                "each specific exercise, hence the number of each exercise completed from the data command will " +
                "NOT be cleared.\r\n" +
                "[exit]\r\n" +
                "\tEnd the program\r\n";
        } else {
            expectedOutput = "These are some commands available:\n" +
                "[generate]\n" +
                "\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x\n" +
                "\tFILTER stands for a specific requirement you want to include in your exercise\n" +
                "[filters]\n" +
                "\tView all available filters\n" +
                "[ippt] [AGE] [RUNTIME] [PUSHUPs] [SITUPs]\n" +
                "\tStarts an IPPT exercise session with input repetitions for the 3 sets of exercises.\n" +
                "[start]\n" +
                "\tStart a workout session\n" +
                "[history]\n" +
                "\tView the information on all workout sessions you have completed\n" +
                "[data]\n" +
                "\tView your completed exercises as well as the number of times" +
                " you have completed each exercise\n" +
                "[delete]\n" +
                "\tDelete a workout session you have within your workout history: delete NUMBER\n" +
                "\tNUMBER refers to the session number of the workout session you wish to delete\n" +
                "[plans]\n" +
                "\tShow all plans\n" +
                "[planner]\n" +
                "\tEnter workout plan editor\n" +
                "[quick]\n" +
                "\tGenerate a planned exercise: quick PLAN_NAME x\n" +
                "\tPLAN_NAME needs has to be in your planner, and x is the number of exercises\n" +
                "[find]\n" +
                "\tfinds all relevant exercises based on the keyword : find [keyword]\n" +
                "[achievements]\n" +
                "\tShows all the available achievements, their requirements and whether they have been achieved " +
                "or not" +
                "\n" +
                "[clear_achievements]\n" +
                "\tClears all the data of finished exercises for the achievements database, resetting " +
                "counters for all achievements.  " +
                "\n\tDo note that this command does not clear the counters for each specific exercise," +
                " hence the number of each exercise completed from the data command will NOT be cleared.\n" +
                "[exit]\n" +
                "\tEnd the program\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    //@@author L-K-Chng

    /**
     * Checks if the ui.greetUser() method prints the correct output.
     */
    @Test
    void testGreetUser () {
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

    //@@author L-K-Chng

    /**
     * Checks if the ui.byeUser() method prints the correct output.
     */
    @Test
    void testByeUser () {
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

    //@@author L-K-Chng

    /**
     * Checks if the ui.printPlannerHelp() method prints the correct output.
     */
    @Test
    void testPrintPlannerHelp () {
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

    //@@author L-K-Chng

    /**
     * Checks if the ui.printExerciseSessionHelp() method prints the correct output.
     */
    @Test
    void testPrintExerciseSessionHelp () {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.printExerciseSessionHelp();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "These are some commands available: \r\n" +
                "[current]\r\n" +
                "\tShows you the list of exercises that you have in your current workout session.\r\n" +
                "[finish]\r\n" +
                "\tComplete your current workout session!\r\n" +
                "[cancel]\r\n" +
                "\tTerminate your current workout session.\r\n";
        } else {
            expectedOutput = "These are some commands available: \n" +
                "[current]\n" +
                "\tShows you the list of exercises that you have in your current workout session.\n" +
                "[finish]\n" +
                "\tComplete your current workout session!\n" +
                "[cancel]\n" +
                "\tTerminate your current workout session.\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    //@@author L-K-Chng

    /**
     * Checks if printUserExerciseHistory() method prints the correct output.
     */
    @Test
    void testPrintUserExerciseHistory () {
        //add one value then compare.
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        HashMap<String, Integer> userExerciseDataMap = new HashMap<>();
        int overallCount = 1;
        int uniqueCount = 1;

        String exerciseDescription = "Exercise Name: 3/4 Sit-Up" + System.lineSeparator() +
            "Difficulty Level: beginner" + System.lineSeparator() + "Workout type: core" +
            System.lineSeparator() + "Lie down on the floor and secure your feet. Your legs should be bent" +
            " at the knees., " +
            "Place your hands behind or to the side of your head. You will begin " +
            "with your back on the ground. This will be your starting position., " +
            "Flex your hips and spine to raise your torso toward your knees., " +
            "At the top of the contraction your torso should be perpendicular to " +
            "the ground. Reverse the motion, going only Â¾ of the way down., " +
            "Repeat for the recommended amount of repetitions.";

        userExerciseDataMap.put(exerciseDescription, 1);

        Ui ui = new Ui();
        ui.printUserExerciseHistory(userExerciseDataMap,overallCount,uniqueCount);
        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Here is a list of all the exercises you have completed:\r\n" +
                "\r\n" +
                "Exercise Name: 3/4 Sit-Up\r\n" +
                "Difficulty Level: beginner\r\n" +
                "Workout type: core\r\n" +
                "Lie down on the floor and secure your feet. Your legs should be bent" +
                " at the knees., " +
                "Place your hands behind or to the side of your head. You will begin " +
                "with your back on the ground. This will be your starting position., " +
                "Flex your hips and spine to raise your torso toward your knees., " +
                "At the top of the contraction your torso should be perpendicular to " +
                "the ground. Reverse the motion, going only Â¾ of the way down., " +
                "Repeat for the recommended amount of repetitions.\r\n" +
                "Times Completed: 1\r\n" +
                "\r\n" +
                "You have completed a total of 1 non-unique exercise(s), " +
                    "of which 1 of them are unique! Keep it up!:)\r\n";
        } else {
            expectedOutput = "Here is a list of all the exercises you have completed:\n" +
                "\n" +
                "Exercise Name: 3/4 Sit-Up\n" +
                "Difficulty Level: beginner\n" +
                "Workout type: core\n" +
                "Lie down on the floor and secure your feet. Your legs should be bent" +
                " at the knees., " +
                "Place your hands behind or to the side of your head. You will begin " +
                "with your back on the ground. This will be your starting position., " +
                "Flex your hips and spine to raise your torso toward your knees., " +
                "At the top of the contraction your torso should be perpendicular to " +
                "the ground. Reverse the motion, going only Â¾ of the way down., " +
                "Repeat for the recommended amount of repetitions.\n" +
                "Times Completed: 1\n" +
                "\n" +
                "You have completed a total of 1 non-unique exercise(s), " +
                    "of which 1 of them are unique! Keep it up!:)\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    //@@author Khulon
    @Test
    void testPrintPlans () {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        UserPlan planner = new UserPlan();
        Ui ui = new Ui();
        ui.showPlan(planner);

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "YOUR WORKOUT PLAN:\r\n" +
                "_________\r\n" +
                "MONDAY\r\n" +
                "_________\r\n" +
                "TUESDAY\r\n" +
                "_________\r\n" +
                "WEDNESDAY\r\n" +
                "_________\r\n" +
                "THURSDAY\r\n" +
                "_________\r\n" +
                "FRIDAY\r\n" +
                "_________\r\n" +
                "SATURDAY\r\n" +
                "_________\r\n" +
                "SUNDAY\r\n";
        } else {
            expectedOutput = "YOUR WORKOUT PLAN:\n" +
                "_________\n" +
                "MONDAY\n" +
                "_________\n" +
                "TUESDAY\n" +
                "_________\n" +
                "WEDNESDAY\n" +
                "_________\n" +
                "THURSDAY\n" +
                "_________\n" +
                "FRIDAY\n" +
                "_________\n" +
                "SATURDAY\n" +
                "_________\n" +
                "SUNDAY\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    //@@author L-K-Chng

    /**
     * Checks if printExerciseFromList() method prints the correct output.
     */
    @Test
    void testPrintExerciseFromList () {

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String os = System.getProperty("os.name");
        String expectedOutput = "";
        ExerciseData exerciseData = new ExerciseData();
        exerciseData.setId("0");
        exerciseData.setName("3/4 Sit-Up");
        exerciseData.setLevel("beginner");
        List<String> workoutType = new ArrayList<String>();
        workoutType.add("core");
        exerciseData.setWorkoutType(workoutType);
        List<String> instructions = new ArrayList<String>();
        instructions.add("Lie down on the floor and secure your feet. Your legs should be bent" +
                             " at the knees.");
        instructions.add("Place your hands behind or to the side of your head. You will begin " +
                             "with your back on the ground. This will be your starting position.");
        instructions.add("Flex your hips and spine to raise your torso toward your knees.");
        instructions.add("At the top of the contraction your torso should be perpendicular to " +
                             "the ground. Reverse the motion, going only Â¾ of the way down.");
        instructions.add("Repeat for the recommended amount of repetitions.");
        exerciseData.setInstructions(instructions);

        ArrayList<ExerciseData> exercises = new ArrayList<>();
        exercises.add(exerciseData);

        Ui uiManager = new Ui();
        uiManager.printExerciseFromList(exercises);

        if (os.contains("Windows")) {
            expectedOutput = "Exercise ID: 0. \r\n" +
                "Name: 3/4 Sit-Up\r\n" +
                "Difficulty Level: beginner\r\n" +
                "Workout Type: core\r\n" +
                "Lie down on the floor and secure your feet. Your legs should be bent" +
                " at the knees. " +
                "Place your hands behind or to the side of your head. You will begin " +
                "with your back on the ground. This will be your starting position. " +
                "Flex your hips and spine to raise your torso toward your knees. " +
                "At the top of the contraction your torso should be perpendicular to " +
                "the ground. Reverse the motion going only Â¾ of the way down. " +
                "Repeat for the recommended amount of repetitions.\r\n" +
                "\r\n";
        } else {
            expectedOutput = "Exercise ID: 0. \n" +
                "Name: 3/4 Sit-Up\n" +
                "Difficulty Level: beginner\n" +
                "Workout Type: core\n" +
                "Lie down on the floor and secure your feet. Your legs should be bent" +
                " at the knees. " +
                "Place your hands behind or to the side of your head. You will begin " +
                "with your back on the ground. This will be your starting position. " +
                "Flex your hips and spine to raise your torso toward your knees. " +
                "At the top of the contraction your torso should be perpendicular to " +
                "the ground. Reverse the motion going only Â¾ of the way down. " +
                "Repeat for the recommended amount of repetitions.\n" +
                "\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

}

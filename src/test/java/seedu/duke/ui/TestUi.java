package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import seedu.duke.ui.Ui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TestUi {
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
                    "\t[arms] exercises that train your arms\r\n" +
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
                    "\t[arms] exercises that train your arms\n" +
                    "\t[core] exercises that train your core\n" +
                    "\t[legs] exercises that train your legs\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

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
                    "[find]\r\n" +
                    "\tfinds all relevant exercises based on the keyword : find [keyword]\r\n" +
                    "[bye]\r\n" +
                    "\tEnd the program\r\n";
        } else {
            expectedOutput = "These are some commands available:\n" +
                    "[generate]\r\n" +
                    "\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x\n" +
                    "\tFILTER stands for a specific requirement you want to include in your exercise\n" +
                    "[filters]\n" +
                    "\tView all available filters\n" +
                    "[find]\n" +
                    "\tfinds all relevant exercises based on the keyword : find [keyword]\n" +
                    "[bye]\n" +
                    "\tEnd the program\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

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
}

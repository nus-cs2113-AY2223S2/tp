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
                    "\t[legs] exercises that train your legs\r\n" +
                    "\r\n";
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
                    "\t[legs] exercises that train your legs\n" +
                    "\n";
        }
        String actual = actualOutput.toString();
        assertEquals(expectedOutput,
                actual.replaceAll("\n","\r\n").replaceAll("\r","\r\n"));
    }

    /*@Test
    void testGreetUser() {
        String LOGO = "    _______ __                          ____        __      \n" +
                "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \n" +
                "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\n" +
                " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\n" +
                "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ ";
        String expectedGreeting = "Hello from" + System.lineSeparator()
                + LOGO + System.lineSeparator()
                + "Start your fitness journey! Type [help] to see the things you can do!";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui ui = new Ui();
        ui.greetUser();

        String os = System.getProperty("os.name");
        String expectedOutput = "";

        if (os.contains("Windows")) {
            expectedOutput = "Hello from\n"
                    + "    _______ __                          ____        __      \n" +
                    "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \n" +
                    "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\n" +
                    " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\n" +
                    "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ \n"
                    + "Start your fitness journey! Type [help] to see the things you can do!\n";
            *//*expectedOutput = "======================================================\r\n" +
                    "Hello! Welcome to CLIAlgo Notes!\r\n" +
                    "======================================================\r\n";*//*
        } else {
            *//*expectedOutput = "======================================================\n" +
                    "Hello! Welcome to CLIAlgo Notes!\n" +
                    "======================================================\n";*//*
            expectedOutput = "Hello from\n"
                    + "    _______ __                          ____        __      \n" +
                    "   / ____(_) /_____  ___  __________   / __ \\__  __/ /_____ \n" +
                    "  / /_  / / __/ __ \\/ _ \\/ ___/ ___/  / / / / / / / //_/ _ \\\n" +
                    " / __/ / / /_/ / / /  __(__  |__  )  / /_/ / /_/ / ,< /  __/\n" +
                    "/_/   /_/\\__/_/ /_/\\___/____/____/  /_____/\\__,_/_/|_|\\___/ "
                    + "Start your fitness journey! Type [help] to see the things you can do!\n";
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }*/

}

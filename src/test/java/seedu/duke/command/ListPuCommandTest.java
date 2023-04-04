package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListPuCommandTest {
    private static final String LINE = "____________________________________________________________";
    //@@author dfa-reused
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStream() {
        System.setOut(originalOut);
    }
    //@@ author dfa

    //Solution below adapted from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    @Test
    void execute_correctLines_success() {
        ListPuCommand listPuCommand = new ListPuCommand();
        listPuCommand.execute();
        assertEquals("This is the list of PUs:" + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        "   Partner University Name                           PU Abb    " + System.lineSeparator() +
                        "1. KOREA UNIVERSITY                                  KU" + System.lineSeparator() +
                        "2. KOREA ADVANCED INSTITUTE OF SCIENCE & TECHNOLOGY  KAIST" + System.lineSeparator() +
                        "3. POHANG UNIVERSITY OF SCIENCE & TECHNOLOGY         POSTECH" + System.lineSeparator() +
                        "4. SEOUL NATIONAL UNIVERSITY                         SNU" + System.lineSeparator() +
                        "5. YONSEI UNIVERSITY                                 YU" + System.lineSeparator() +
                        "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }
}

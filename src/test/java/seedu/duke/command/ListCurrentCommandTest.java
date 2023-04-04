package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import seedu.duke.Module;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListCurrentCommandTest {
    private static final String LIST_CURRENT_MESSAGE = "List of Added modules:";
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
    //@@author dfa

    //Solution below adapted from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    @Test
    void execute_correctLines_success() {
        ArrayList<Module> modules = new ArrayList<>();
        Module module1 = new Module(1, "AE320", "Aerodynamics II", 3,
                "ME4231", "Aerodynamics", 4);
        Module module2 = new Module(4, "M2794.0073", "Finite Element Analysis"
                , 3,
                "ME4291", "Finite Element Analysis", 4);
        modules.add(module1);
        modules.add(module2);
        ListCurrentCommand listCurrentCommand = new ListCurrentCommand(modules);
        listCurrentCommand.execute();
        assertEquals("List of Added Modules for: KOREA UNIVERSITY" + System.lineSeparator() +
                        "[KOREA UNIVERSITY Module] maps to ----> [NUS Module]" + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        "1.[AE320][Aerodynamics II][3]   maps to ----> [ME4231][Aerodynamics][4]"
                        + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        System.lineSeparator() +
                        "The current module list is empty for: KOREA ADVANCED INSTITUTE OF SCIENCE & TECHNOLOGY"
                        + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        System.lineSeparator() +
                        "The current module list is empty for: POHANG UNIVERSITY OF SCIENCE & TECHNOLOGY"
                        + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        System.lineSeparator() +
                        "List of Added Modules for: SEOUL NATIONAL UNIVERSITY" + System.lineSeparator() +
                        "[SEOUL NATIONAL UNIVERSITY Module] maps to ----> [NUS Module]" + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        "1.[M2794.0073][Finite Element Analysis][3]   maps to ----> " +
                        "[ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        System.lineSeparator() +
                        "The current module list is empty for: YONSEI UNIVERSITY" + System.lineSeparator() +
                        "____________________________________________________________" + System.lineSeparator() +
                        "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }
}

package seedu.duke;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintTest {

    @Test
    public void printAddedModuleTest() {
        ModuleList moduleList = new ModuleList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.addModule("CS2113T", "4", "CORE");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've added this module:" + System.lineSeparator()
                + "       [C] CS2113T 4 MCs" + System.lineSeparator() + "     Now you have 1 modules in the list."
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printFoundModuleTest() {
        ModuleList moduleList = new ModuleList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE");
        System.setOut(new PrintStream(outContent));
        moduleList.findModule("CS2113T");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Here are the matching modules in your list:" + System.lineSeparator()
                + "       [C] CS2113T 4 MCs" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printNotFoundModuleTest() {
        ModuleList moduleList = new ModuleList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE");
        System.setOut(new PrintStream(outContent));
        moduleList.findModule("CS2101");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     There are no matching modules in your list:" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printDeletedModuleTest() {
        ModuleList moduleList = new ModuleList();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE");
        System.setOut(new PrintStream(outContent));
        moduleList.deleteModule("CS2113T");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Noted. I've removed this module:" + System.lineSeparator()
                + "       [C] CS2113T 4 MCs" + System.lineSeparator() + "     Now you have 0 modules in the list."
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printModuleListTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "4", "CORE");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.viewModuleList();
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Here are the modules in your list:" + System.lineSeparator()
                + "     1.[C] CS2113T 4 MCs" + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printManPageTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Manpage.printManPage();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() +

                "--------------------USER GUIDE--------------------" +
                System.lineSeparator() + System.lineSeparator() +
                "Modganiser is effortless module planning, at your fingertips via the " +
                "Command Line Interface (CLI)." + System.lineSeparator() +
                "It helps Information security students graduate in time by " +
                "giving you a platform to plan all 4 years" + System.lineSeparator() +
                "of your modules to ensure that you meet graduation requirements (UE/GEs)." +
                System.lineSeparator() + "If you can type fast," +
                "Modganiser can get your schedule up  faster than traditional GUI apps." +
                System.lineSeparator() + System.lineSeparator() +

                "----------COMMAND SUMMARY----------" + System.lineSeparator() + System.lineSeparator()
                + " " +
                "Man" + System.lineSeparator() + " " +
                "Command: man" + System.lineSeparator() + System.lineSeparator() + " " +

                "Add" + System.lineSeparator() + " " +
                "Command: add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE"
                + System.lineSeparator() + " " +
                "To find out more, use the command: man /add"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "List" + System.lineSeparator() + " " +
                "Command: list all or list /(year)" + System.lineSeparator()
                + " " +
                "To find out more, use the command: man /list"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "Find" + System.lineSeparator() + " " +
                "Command: find /MODULE_CODE" + System.lineSeparator()
                + " " +
                "To find out more, use the command: man /find"
                + System.lineSeparator() + System.lineSeparator() + " " +


                "Delete" + System.lineSeparator() + " " +
                "Command: delete /MODULE_CODE" + System.lineSeparator()
                + " " + "To find out more, use the command: man /delete"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "Exit" + System.lineSeparator() + " " +
                "Command: exit" + System.lineSeparator() + " "+
                "To find out more, use the command: man /exit"
                + System.lineSeparator() + System.lineSeparator() + " ";

        assertEquals(expectedOutput.replaceAll("\\s+",""),
                outContent.toString().replaceAll("\\s+",""));

    }
    @Test
    public void printManPageAddTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Manpage.printManPageAdd();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() +

                "Adding a module: add" + System.lineSeparator() + "Adds a module to the list of modules."
                + System.lineSeparator() + " " +
                "Format: add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE" + System.lineSeparator() + " " +
                "Example: add /CS2113T /4 /CORE" + System.lineSeparator() + System.lineSeparator() + " " +
                "--Module Types--" + System.lineSeparator() + " " +
                "CORE" + System.lineSeparator() + " " +
                "GE (General modules)" + System.lineSeparator() + " " +
                "UE (Unrestricted Electives)" + System.lineSeparator() + System.lineSeparator()
                + " ";

        assertEquals(expectedOutput.replaceAll("\\s+",""),
                outContent.toString().replaceAll("\\s+",""));

    }

    @Test
    public void printManPageListTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Manpage.printManPageList();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() +
                "List all modules: list all " + System.lineSeparator() + " " +
                "Shows a list of all modules in the module planner along with the module type." +
                System.lineSeparator() + " " +
                "Example: list all" + System.lineSeparator() + System.lineSeparator() + " " +

                "List all modules: list /(Year) "+System.lineSeparator()+" " +
                "Shows a list of all modules in that year along with the module type."
                + System.lineSeparator() + " " +
                "Example: list 1" + System.lineSeparator() + System.lineSeparator() + " ";

        assertEquals(expectedOutput.replaceAll("\\s+",""),
                outContent.toString().replaceAll("\\s+",""));
    }


    @Test
    public void printManPageFindTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Manpage.printManPageFind();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() +

                "Find module: find /(Module Code)" + System.lineSeparator() + " " +
                "Find which year and semester I plan to take the module." + System.lineSeparator() + " " +
                "Example: find /CS2113T" + System.lineSeparator() + System.lineSeparator() + " ";

        assertEquals(expectedOutput.replaceAll("\\s+",""),
                outContent.toString().replaceAll("\\s+",""));
    }

    @Test
    public void printManPageDeleteTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Manpage.printManPageDelete();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() +

                "Delete module: delete /(Module Code)" + System.lineSeparator() + " " +
                "Shows a list of all modules in the module planner." + System.lineSeparator() + " " +
                "Example: delete /CS2113T" + System.lineSeparator() + System.lineSeparator() + " ";

        assertEquals(expectedOutput.replaceAll("\\s+",""),
                outContent.toString().replaceAll("\\s+",""));
    }
    @Test
    public void printManPageExitTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Manpage.printManPageExit();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() +

                "Exit program: exit" + System.lineSeparator() + " " +
                "Exit module planner." + System.lineSeparator() + " " +
                "Format: exit" + System.lineSeparator() + System.lineSeparator();

        assertEquals(expectedOutput.replaceAll("\\s+",""),
                outContent.toString().replaceAll("\\s+",""));
    }

}

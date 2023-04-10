package seedu.main;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintTest {
    private static String line = "-----------------------------------------------------"
            + System.lineSeparator() + " ";
    private static String printingLine = "    ____________________________________________________________"
            + System.lineSeparator();
    @Test
    public void printAddedModuleTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /CS2113T /4 /CORE /1 /1", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've added this module:" + System.lineSeparator()
                + "       [C][\" \"] CS2113T 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "     Now you have 1 modules in your module plan."
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printFoundModuleByCodeTest() {
        ModuleList moduleList = new ModuleList();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("find/code/CS", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Here are the matching modules in your module plan:"
                + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator()
                + "     1. [C][\" \"] CS2113T 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printMultipleFoundModuleByCodeTest() {
        ModuleList moduleList = new ModuleList();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        moduleList.addModule("CS2101", "4", "CORE", "1", "1");
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("find/code/CS", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Here are the matching modules in your module plan:"
                + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator()
                + "     1. [C][\" \"] CS2113T 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "     2. [C][\" \"] CS2101 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printNoFoundModuleByCodeTest() {
        ModuleList moduleList = new ModuleList();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("find/code/IS", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     There are no modules that match the keyword: IS in your module plan"
                + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printFoundModuleByTypeTest() {
        ModuleList moduleList = new ModuleList();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("find/type/Core", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Here are the matching modules in your module plan:"
                + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator()
                + "     1. [C][\" \"] CS2113T 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printMultipleFoundModuleByTypeTest() {
        ModuleList moduleList = new ModuleList();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        moduleList.addModule("CS2101", "4", "CORE", "1", "1");
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("find/type/Core", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Here are the matching modules in your module plan:"
                + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator()
                + "     1. [C][\" \"] CS2113T 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "     2. [C][\" \"] CS2101 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printNotFoundModuleByTypeTest() {
        ModuleList moduleList = new ModuleList();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("find/type/GE", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator()
                + "     There are no GE modules in your module plan" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printDeletedModuleTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("delete /CS2113T", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Noted. I've removed this module:" + System.lineSeparator()
                + "       [C][\" \"] CS2113T 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "     Now you have 0 modules in your module plan."
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printModuleListTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "4", "CORE", "2", "2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.listModules();
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Here are the modules in your module plan:" + System.lineSeparator()
                + "     1.[C][\" \"] CS2113T 4 MCs (Year: 2, Sem: 2)" + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void editModuleTypeTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "4", "GE", "2", "2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.editModuleType("CS2113T", "CORE");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've edited the information for this module:"
                + System.lineSeparator()
                + "       [C][\" \"] CS2113T 4 MCs (Year: 2, Sem: 2)" + System.lineSeparator()
                + "     Now you have 1 modules in your module plan."
                + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void editModularCreditsTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "2", "CORE", "2", "2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.editModularCredits("CS2113T", "4");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've edited the information for this module:"
                + System.lineSeparator()
                + "       [C][\" \"] CS2113T 4 MCs (Year: 2, Sem: 2)" + System.lineSeparator()
                + "     Now you have 1 modules in your module plan."
                + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void editModuleYearTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "4", "CORE", "3", "2");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.editYear("CS2113T", "2");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've edited the information for this module:"
                + System.lineSeparator()
                + "       [C][\" \"] CS2113T 4 MCs (Year: 2, Sem: 2)" + System.lineSeparator()
                + "     Now you have 1 modules in your module plan."
                + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void editModuleSemesterTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "4", "CORE", "2", "1");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.editSemester("CS2113T", "2");
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've edited the information for this module:"
                + System.lineSeparator()
                + "       [C][\" \"] CS2113T 4 MCs (Year: 2, Sem: 2)" + System.lineSeparator()
                + "     Now you have 1 modules in your module plan."
                + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printManPageTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPage();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() +

                "--------------------USER GUIDE--------------------" +
                System.lineSeparator() + System.lineSeparator() +
                "Modganiser is effortless module planning, at your fingertips via the " +
                "Command Line Interface (CLI)." + System.lineSeparator() +
                "It helps Information security students graduate in time by " +
                "giving you a platform to plan all 4 years" + System.lineSeparator() +
                "of your modules to ensure that you meet graduation requirements (UE/GEs)." +
                System.lineSeparator() + "If you can type fast, " +
                "Modganiser can get your schedule up  faster than traditional GUI apps." +
                System.lineSeparator() + System.lineSeparator() +

                "----------COMMAND SUMMARY----------" + System.lineSeparator() + System.lineSeparator()
                + " " +
                "1. Man" + System.lineSeparator() + " " +
                "Command: man" + System.lineSeparator() + System.lineSeparator() + " " +

                "2. Add" + System.lineSeparator() + " " +
                "Command: add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE /YEAR /SEM"
                + System.lineSeparator() + " " +
                "Example for year 1 semester 1 : add /CS2113T /4 /CORE /1 /1" +
                System.lineSeparator() + " " +
                "Example for year 3 semester 2 : add /ATAP /12 /INTERNSHIP /3 /2" +
                System.lineSeparator() + " " +
                "To find out more, use the command: man /add"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "3. List" + System.lineSeparator() + " " +
                "Command: list /all or list /YEAR" + System.lineSeparator()
                + " " +
                "To find out more, use the command: man /list"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "4. Edit" + System.lineSeparator() + " " +
                "Command: edit /MODULE_CODE /FIELD_TO_EDIT /NEW_INFO" +
                System.lineSeparator() + " " +
                "To find out more, use the command: man /edit"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "5. Find" + System.lineSeparator() + " " +
                "Command: find /CODE /KEYWORD or find /TYPE /KEYWORD" +
                System.lineSeparator() + " " +
                "To find out more, use the command: man /find"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "6. Track"+ System.lineSeparator() + " " +
                "Command: track /all or track /MODULE_TYPE" + System.lineSeparator() + " " +
                "To find out more, use the command: man /track"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "7. Delete" + System.lineSeparator() + " " +
                "Command: delete /MODULE_CODE" + System.lineSeparator()
                + " " + "To find out more, use the command: man /delete"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "8. Grade Calculator" + System.lineSeparator() + " " +
                "Command: calculatecap" + System.lineSeparator()
                + " " + "To find out more, use the command: man /calculator"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "9. Input Grades" + System.lineSeparator() + " " +
                "Command: grade /MODULE_CODE /GRADE" + System.lineSeparator()
                + " " + "To find out more, use the command: man /grade"
                + System.lineSeparator() + System.lineSeparator() + " " +

                "10. Exit" + System.lineSeparator() + " " +
                "Command: bye" + System.lineSeparator() + " " +
                "To find out more, use the command: man /bye"
                + System.lineSeparator() + System.lineSeparator() + " "
                + line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

    }

    @Test
    public void printManPageAddTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageAdd();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +

                "Adding a module: add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE /YEAR /SEM"
                + System.lineSeparator() + " " +
                "Adds a module to the list of modules."
                + System.lineSeparator() + " " +
                "Year includes {1, 2, 3, 4}"
                + System.lineSeparator() + " " +
                "Semester includes  {1, 1.5 (Special Term 1), 2, 2.5 (Special Term 2)}" +
                System.lineSeparator() + " " +
                "Multiple modules could be added at the same time, like: " +
                "add /CS2113T IS4231 CS1010 /4 /CORE /1 /1 for year 1 semester 1"
                + System.lineSeparator() + " " +
                "Example for year 1 semester 1: add /CS2113T /4 /CORE /1 /1" + System.lineSeparator() + " " +
                "Example for year 3 semester 2 : add /ATAP /12 /INTERNSHIP /3 /2" +
                System.lineSeparator() + System.lineSeparator() + " " +

                "--Module Types--" + System.lineSeparator() + " " +
                "CORE" + System.lineSeparator() + " " +
                "GE (General modules)" + System.lineSeparator() + " " +
                "UE (Unrestricted Electives)" + System.lineSeparator() + " " +
                "INTERNSHIP (Module Code : ATAP, SIP1, SIP2, IIP, NOC, CVWO1, CVWO2)" + System.lineSeparator()
                + System.lineSeparator()
                + " " + line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

    }

    @Test
    public void printManPageListTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageList();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +
                "List all modules: list /all " + System.lineSeparator() + " " +
                "Shows a list of all modules in the module planner along with the module type." +
                System.lineSeparator() + " " +
                "Example: list /all" + System.lineSeparator() + System.lineSeparator() + " " +

                "List all modules: list /YEAR " + System.lineSeparator() + " " +
                "Shows a list of all modules in that year along with the module type."
                + System.lineSeparator() + " " +
                "Example: list /1" + System.lineSeparator() + System.lineSeparator() + " " +
                line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void printManPageEditTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageEdit();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +
                "Edit modules: edit /MODULE_CODE /FIELD_TO_EDIT /NEW_INFO" + System.lineSeparator() + " " +
                "Shows a list of all modules in that year along with the module type." + System.lineSeparator() + " " +
                "Example: To change CS1010 modular credits field from 6 to 4, the command" +
                "will be : edit /CS1010 /MC /4" + System.lineSeparator() + System.lineSeparator() + " " +

                "FIELD_TO_EDIT includes:" + System.lineSeparator() + " " +
                "1. MODULE_CODE will be /CODE" + System.lineSeparator() + " " +
                "2. modular credits will be /MC" + System.lineSeparator() + " " +
                "3. MODULE_TYPE will be /TYPE" + System.lineSeparator() + " " +
                "4. Year will be /YEAR" + System.lineSeparator() + " " +
                "5. Semester will be /SEMESTER" + System.lineSeparator() + " " +
                "6. Grade will be /GRADE" + System.lineSeparator() + System.lineSeparator() +  " " +
                line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void printManPageFindTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageFind();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +
                "Find module: find /CODE /KEYWORD or find /TYPE /KEYWORD" + System.lineSeparator() + " " +
                "Find out which year and semester you plan to take the module." +
                System.lineSeparator() + System.lineSeparator() + " " +
                "Example: In a list with CS2113T and CS2101 as both core modules." + System.lineSeparator() + " " +
                "1.  find /CODE /CS2113 will show the CS2113T module only" + System.lineSeparator() + " " +
                "2.  find /TYPE /CORE  will show both CS2113T and CS2101 modules" +
                System.lineSeparator() + System.lineSeparator() + " "
                + line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }
    @Test
    public void printManPageTrackTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageTrack();
        String expectedOutput = System.lineSeparator() + System.lineSeparator() + line +

                 "To track module type: track /MODULE_TYPE"+ System.lineSeparator() + " " +
                 "or to track all modules progresses: track /all"  +
                System.lineSeparator() +System.lineSeparator() + " " +
                 "Tracks how many modular credits of a module type you have finished"+ System.lineSeparator() + " " +
                 "and Modganiser will list out all the modules that you have taken"+ System.lineSeparator() + " " +
                 "under that module type and how many more modular credits you need"+ System.lineSeparator() + " " +
                 "before you complete the requirements for that module type."+
                 System.lineSeparator() +System.lineSeparator()+ " " +
                 "Example: track /CORE" +System.lineSeparator()+ " " +
                 "Tracks the completion status of your core modules" +
                 System.lineSeparator() +System.lineSeparator()+ " " +

                 "--Module Types--" +System.lineSeparator()+ " " +
                 "CORE" +System.lineSeparator()+ " " +
                 "GE (General modules)"+System.lineSeparator()+ " " +
                 "UE (Unrestricted Electives)"+System.lineSeparator()+ " " +
                 "INTERNSHIP (Module Code : ATAP, SIP1, SIP2, IIP, NOC, CVWO1, CVWO2)"+
                  System.lineSeparator()+System.lineSeparator()+ " " + line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }
    @Test
    public void printManPageDeleteTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageDelete();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +

                "Delete module: delete /MODULE_CODE" + System.lineSeparator() + " " +
                "Shows a list of all modules in the module planner." + System.lineSeparator() + " " +
                "Example: delete /CS2113T" + System.lineSeparator() + System.lineSeparator() + " "
                + line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void printManPageCalculatorTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageCalculator();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +
                "Grade calculator: calculatecap" + System.lineSeparator() + " " +
                "Calculates your cap" + System.lineSeparator() + System.lineSeparator() + " " +
                line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void printManPageInputGradeTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageInputGrade();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +
                "Input Grades: grade /MODULE_CODE /GRADE" + System.lineSeparator() + " " +
                "Example: grade /CS2101 /A+"
                + System.lineSeparator() + System.lineSeparator() + " " +
                "Grades:" + System.lineSeparator() + " " +
                "A+ [5.0]" + System.lineSeparator() + " " +
                "A  [5.0]" + System.lineSeparator() + " " +
                "A- [4.5]" + System.lineSeparator() + " " +
                "B+ [4.0]" + System.lineSeparator() + " " +
                "B  [3.5]" + System.lineSeparator() + " " +
                "B- [3.0]" + System.lineSeparator() + " " +
                "C+ [2.5]" + System.lineSeparator() + " " +
                "C  [2.0]" + System.lineSeparator() + " " +
                "D+ [1.5]" + System.lineSeparator() + " " +
                "D  [1.0]" + System.lineSeparator() + " " +
                "F  [0]" + System.lineSeparator() + " " +
                "S  [N.A.]" + System.lineSeparator() + " " +
                "U  [N.A.]" + System.lineSeparator() + " " +
                "CS [N.A.]" + System.lineSeparator() + " " +
                "CU [N.A.]" + System.lineSeparator() + System.lineSeparator() + " " +
                line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void printManPageExitTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ManPage.printManPageExit();
        String expectedOutput = System.lineSeparator() + System.lineSeparator()
                + line +

                "Exit program: bye" + System.lineSeparator() + " " +
                "Exit module planner." + System.lineSeparator() + " " +
                "Format: bye" + System.lineSeparator() + System.lineSeparator()
                + line;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }


    @Test
    public void updateModuleGradeTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        try {
            parser.checkUserInput("grade /CS2113T /A", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've updated the grade for this module:"
                + System.lineSeparator()
                + "       [C][\"A\"] CS2113T 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void calculateCAPTest() {
        ModuleList moduleList = new ModuleList();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        moduleList.addModule("CS2113T", "4", "CORE", "1", "1");
        moduleList.addModule("CS2101", "4", "CORE", "1", "1");
        try {
            moduleList.updateModuleGrade("CS2113T", "A", "grade");
            moduleList.updateModuleGrade("CS2101", "B", "grade");
            parser.checkUserInput("calculateCAP", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     I have calculated your CAP across your graded modules!"
                + System.lineSeparator()
                + "     Your CAP is currently: 4.25 :)" + System.lineSeparator()
                + "    ____________________________________________________________" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printInvalidModuleTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("grade /CS2113T /A", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Unable to update the grade for CS2113T as it's not in " +
                "your module plan!" + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printInvalidModularCreditTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /CS2113T /-1 /CORE /1 /1", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator()
                + "     Error: Make sure Modular Credits is an integer with no decimal place from 0-41"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printEmptyModuleCodeTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add / /4 /CORE /1 /1", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Error: User input cannot be empty for any parameter"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator() + "     Error: Module Code cannot be empty"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator() + "     Attempting to add modules (if any) with the correct module code!"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printLessThanSixCharacterModuleCodeTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS42 /4 /CORE /1 /1", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator()
                + "     Error: Module Code cannot be less than 6 characters!"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator()
                + "     Attempting to add modules (if any) with the correct module code!"
                + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printIncorrectNumberOfAddFieldTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS4231 /CORE /1", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Error: Missing fields"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printIncorrectModuleTypeTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS4231 /4 /UEGE /1 /1", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Error: Incorrect Module Type, Accepted Module Types are: "
                + "(CORE, UE ,GE ,INTERNSHIP)"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printDuplicateModuleTypeTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS4231 /4 /CORE /1 /1", moduleList);
            parser.checkUserInput("add /IS4231 /4 /CORE /2 /2", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Got it. I've added this module:" + System.lineSeparator()
                + "       [C][\" \"] IS4231 4 MCs (Year: 1, Sem: 1)" + System.lineSeparator()
                + "     Now you have 1 modules in your module plan."
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator()
                + "    ____________________________________________________________"
                + System.lineSeparator() + "     Error: IS4231 is already in your module plan"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printIncorrectYearTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS4231 /4 /CORE /5 /1", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator()
                + "     Error: Make sure Year of Study is an integer with no decimal place from 1-4"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printIncorrectSemesterTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS4231 /4 /CORE /1 /3", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Error: Make sure Semester is 1, 1.5 (Special Term 1), 2 or " +
                "2.5 (Special Term 2), with no additional decimal places\n"
                + "     Example: 1.0, 1.50, 2.0, 2.50 is not accepted as a valid input!"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printYearNotIntegerTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS4231 /4 /CORE /a /3", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator()
                + "     Error: Make sure Year of Study is an integer with no decimal place from 1-4\n"
                + "     Example: 1.0 is not accepted as a valid input!"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void printSemesterNotAcceptedTest() {
        ModuleList moduleList = new ModuleList();
        new Main();
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            parser.checkUserInput("add /IS4231 /4 /CORE /1 /1.6", moduleList);
        } catch (MainException e) {
            return;
        }
        String expectedOutput = "    ____________________________________________________________"
                + System.lineSeparator() + "     Error: Make sure Semester is 1, 1.5 (Special Term 1), 2 or " +
                "2.5 (Special Term 2), with no additional decimal places\n"
                + "     Example: 1.0, 1.50, 2.0, 2.50 is not accepted as a valid input!"
                + System.lineSeparator() + "    ____________________________________________________________"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    public void printWrongManPageField() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Print.printWrongManPageField();

        String expectedOutput = printingLine +
                "     Incorrect field for the ManPage Command, " + System.lineSeparator() +
                "     Check our command summary again by typing `man`" + System.lineSeparator() +
               printingLine;
        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }
}


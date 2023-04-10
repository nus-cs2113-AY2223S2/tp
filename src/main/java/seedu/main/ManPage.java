
package seedu.main;
public class ManPage {

    private static final String LINE= "-----------------------------------------------------\n ";
    public ManPage() {

    }

    /**
     * Prints the full command summary.
     */
    public static void printManPage() {
        System.out.println("\n\n" +

                "--------------------USER GUIDE--------------------\n\n" +
                "Modganiser is effortless module planning, at your fingertips via the " +
                "Command Line Interface (CLI).\n" +
                "It helps Information security students graduate in time by " +
                "giving you a platform to plan all 4 years\n" +
                "of your modules to ensure that you meet graduation requirements (UE/GEs).\nIf you can type fast, " +
                "Modganiser can get your schedule up  faster than traditional GUI apps.\n\n" +

                "----------COMMAND SUMMARY----------\n\n " +
                "1. Man\n " +
                "Command: man\n\n " +

                "2. Add\n " +
                "Command: add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE /YEAR /SEM\n " +
                "Example for year 1 semester 1 : add /CS2113T /4 /CORE /1 /1\n "+
                "Example for year 3 semester 2 : add /ATAP /12 /INTERNSHIP /3 /2\n "+
                "To find out more, use the command: man /add\n\n " +

                "3. List\n " +
                "Command: list /all or list /YEAR\n " +
                "To find out more, use the command: man /list\n\n " +

                "4. Edit\n " +
                "Command: edit /MODULE_CODE /FIELD_TO_EDIT /NEW_INFO\n " +
                "To find out more, use the command: man /edit\n\n " +

                "5. Find\n " +
                "Command: find /CODE /KEYWORD or find /TYPE /KEYWORD\n " +
                "To find out more, use the command: man /find\n\n " +

                "6. Track\n " +
                "Command: track /all or track /MODULE_TYPE\n " +
                "To find out more, use the command: man /track\n\n " +

                "7. Delete\n " +
                "Command: delete /MODULE_CODE\n " +
                "To find out more, use the command: man /delete\n\n " +

                "8. Grade Calculator\n " +
                "Command: calculatecap\n " +
                "To find out more, use the command: man /calculator\n\n " +

                "9. Input Grades\n " +
                "Command: grade /MODULE_CODE /GRADE\n " +
                "To find out more, use the command: man /grade\n\n " +

                "10. Exit\n " +
                "Command: bye\n "+
                "To find out more, use the command: man /bye\n " +
                LINE);
    }

    /**
     * Prints a more detailed description on how to
     * use the add feature.
     */
    public static void printManPageAdd() {
        System.out.println("\n\n" +
                LINE+
                "Adding a module:  add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE /YEAR /SEM\n "+
                "Adds a module to the list of modules.\n " +
                "Year includes {1, 2, 3, 4}\n "+
                "Semester includes  {1, 1.5 (Special Term 1), 2, 2.5 (Special Term 2)}\n "+
                "Multiple modules could be added at the same time, like: "+
                "add /CS2113T IS4231 CS1010 /4 /CORE /1 /1 for year 1 semester 1\n "+
                "Example for year 1 semester 1: add /CS2113T /4 /CORE /1 /1\n " +
                "Example for year 3 semester 2 : add /ATAP /12 /INTERNSHIP /3 /2\n\n "+

                "--Module Types--\n " +
                "CORE\n " +
                "GE (General modules)\n " +
                "UE (Unrestricted Electives)\n " +
                "INTERNSHIP (Module Code : ATAP, SIP1, SIP2, IIP, NOC, CVWO1, CVWO2)\n\n " +
                LINE);
    }

    /**
     * Prints a more detailed description on how to
     * use the list feature.
     */
    public static void printManPageList() {
        System.out.println("\n\n" + LINE +

                "List all modules: list /all \n " +
                "Shows a list of all modules in the module planner along with the module type.\n " +
                "Example: list /all\n\n " +

                "List all modules: list /YEAR \n " +
                "Shows a list of all modules in that year along with the module type.\n " +
                "Example: list /1\n\n" +
                LINE);
    }

    /**
     * Prints a more detailed description on how to
     * use the edit feature.
     */
    public static void printManPageEdit() {
        System.out.println("\n\n" + LINE +

                "Edit modules: edit /MODULE_CODE /FIELD_TO_EDIT /NEW_INFO  \n " +
                "Shows a list of all modules in that year along with the module type.\n " +
                "Example: To change CS1010 modular credits field from 6 to 4, the command" +
                "will be : edit /CS1010 /MC /4\n\n " +

                "FIELD_TO_EDIT includes:\n "+
                "1. MODULE_CODE will be /CODE\n " +
                "2. modular credits will be /MC\n "+
                "3. MODULE_TYPE will be /TYPE\n " +
                "4. Year will be /YEAR\n "+
                "5. Semester will be /SEMESTER\n " +
                "6. Grade will be /GRADE\n\n " +
                LINE);
    }

    /**
     * Prints a more detailed description on how to
     * use the find feature.
     */
    public static void printManPageFind() {
        System.out.println("\n\n" + LINE +

                "Find module: find /CODE /KEYWORD or find /TYPE /KEYWORD\n " +
                "Find out which year and semester you plan to take the module.\n\n " +
                "Example: In a list with CS2113T and CS2101 as both core modules.\n "+
                "1.  find /CODE /CS2113 will show the CS2113T module only\n "+
                "2.  find /TYPE /CORE  will show both CS2113T and CS2101 modules\n\n " +
                LINE);
    }

    /**
     * Prints a more detailed description on how to
     * use the track feature.
     */
    public static void printManPageTrack() {
        System.out.println("\n\n" + LINE +

                "To track module type: track /MODULE_TYPE\n " +
                "or to track all modules progresses: track /all\n\n "+
                "Tracks how many modular credits of a module type you have finished\n " +
                "and Modganiser will list out all the modules that you have taken\n " +
                "under that module type and how many more modular credits you need\n " +
                "before you complete the requirements for that module type.\n\n " +
                "Example: track /CORE\n " +
                "Tracks the completion status of your core modules\n\n " +

                "--Module Types--\n " +
                "CORE\n " +
                "GE (General modules)\n " +
                "UE (Unrestricted Electives)\n " +
                "INTERNSHIP (Module Code : ATAP, SIP1, SIP2, IIP, NOC, CVWO1, CVWO2)\n\n " +
                LINE);
    }


    /**
     * Prints a more detailed description on how to
     * use the delete feature.
     */
    public static void printManPageDelete() {
        System.out.println("\n\n" +
                LINE+
                "Delete module: delete /MODULE_CODE\n " +
                "Shows a list of all modules in the module planner.\n " +
                "Example: delete /CS2113T\n\n " + LINE );
    }

    /**
     * Prints a more detailed description on how to
     * use the Grade calculator.
     */
    public static void printManPageCalculator() {
        System.out.println("\n\n" + LINE +

                "Grade calculator: calculatecap \n " +
                "Calculates your cap\n\n " +
                LINE);
    }

    /**
     * Prints a more detailed description on how to
     * input grades.
     */
    public static void printManPageInputGrade() {
        System.out.println("\n\n" +
                LINE+

                "Input Grades: grade /MODULE_CODE /GRADE\n " +
                "Example: grade /CS2101 /A+\n\n "+
                "Grades: \n " +
                        "A+ [5.0]\n " +
                        "A  [5.0]\n " +
                        "A- [4.5]\n " +
                        "B+ [4.0]\n " +
                        "B  [3.5]\n " +
                        "B- [3.0]\n " +
                        "C+ [2.5]\n " +
                        "C  [2.0]\n " +
                        "D+ [1.5]\n " +
                        "D  [1.0]\n " +
                        "F  [0]\n " +
                        "S  [N.A.]\n " +
                        "U  [N.A.]\n " +
                        "CS [N.A.]\n " +
                        "CU [N.A.]\n\n " +
                LINE);
    }

    /**
     * Prints a more detailed description on how to
     * use the exit feature.
     */
    public static void printManPageExit() {
        System.out.println("\n\n" +

                LINE+
                "Exit program: bye\n " +
                "Exit module planner.\n " +
                "Format: bye\n\n"
                +LINE);
    }

}

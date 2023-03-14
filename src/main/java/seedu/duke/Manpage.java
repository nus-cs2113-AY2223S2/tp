
package seedu.duke;
public class Manpage {
    public Manpage() {

    }
    public static void printManPage() {
        System.out.println("\n\n" +

                "--------------------USER GUIDE--------------------\n\n" +
                "Modganiser is effortless module planning, at your fingertips via the " +
                "Command Line Interface (CLI).\n" +
                "It helps Information security students graduate in time by " +
                "giving you a platform to plan all 4 years\n" +
                "of your modules to ensure that you meet graduation requirements (UE/GEs).\nIf you can type fast," +
                "Modganiser can get your schedule up  faster than traditional GUI apps.\n\n" +

                "----------COMMAND SUMMARY----------\n\n " +
                "Man\n " +
                "Command: man\n\n " +
                "Add\n " +
                "Command: add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE\n " +
                "To find out more, use the command: man /add\n\n " +
                "List\n " +
                "Command: list all or list /(year)\n " +
                "To find out more, use the command: man /list\n\n " +
                "Find\n " +
                "Command: find /MODULE_CODE\n " +
                "To find out more, use the command: man /find\n\n " +
                "Delete\n " +
                "Command: delete /MODULE_CODE\n " +
                "To find out more, use the command: man /delete\n\n " +
                "Exit\n " +
                "Command: exit\n "+
                "To find out more, use the command: man /exit\n\n " );
    }

    public static void printManPageAdd() {
        System.out.println("\n\n" +

                "Adding a module: add\n Adds a module to the list of modules.\n " +
                "Format: add /MODULE_CODE /MODULAR_CREDITS /MODULE_TYPE\n " +
                "Example: add /CS2113T /4 /CORE\n\n " +

                "--Module Types--\n " +
                "CORE\n " +
                "GE (General modules)\n " +
                "UE (Unrestricted Electives)\n\n ");
    }
    public static void printManPageList() {
        System.out.println("\n\n" +

                "List all modules: list all \n " +
                "Shows a list of all modules in the module planner along with the module type.\n " +
                "Example: list all\n\n " +

                "List all modules: list /(Year) \n " +
                "Shows a list of all modules in that year along with the module type.\n " +
                "Example: list 1\n\n ");
    }

    public static void printManPageFind() {
        System.out.println("\n\n" +

                "Find module: find /(Module Code)\n " +
                "Find which year and semester I plan to take the module.\n " +
                "Example: find /CS2113T\n\n ");
    }

    public static void printManPageDelete() {
        System.out.println("\n\n" +

                "Delete module: delete /(Module Code)\n " +
                "Shows a list of all modules in the module planner.\n " +
                "Example: delete /CS2113T\n\n ");
    }

    public static void printManPageExit() {
        System.out.println("\n\n" +

                "Exit program: exit\n " +
                "Exit module planner.\n " +
                "Format: exit\n\n");
    }

}

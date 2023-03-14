package seedu.duke;
import java.util.ArrayList;

public class Print {

    public static void printHelloMessage(String name) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello " + name + "! I'm your personal Modganiser.");
        System.out.println("     What would you like to plan today?");
        System.out.println("     To find out more, type `man` and press enter :)");
        System.out.println("    ____________________________________________________________");
    }

    public static void printLogo() {
        String logo = "  __  __               _                           _\n" +
                " |  \\/  |   ___     __| |   __ _    __ _   _ __   (_)  ___    ___   _ __\n" +
                " | |\\/| |  / _ \\   / _` |  / _` |  / _` | | '_ \\  | | / __|  / _ \\ | '__|\n" +
                " | |  | | | (_) | | (_| | | (_| | | (_| | | | | | | | \\__ \\ |  __/ | |\n" +
                " |_|  |_|  \\___/   \\__,_|  \\__, |  \\__,_| |_| |_| |_| |___/  \\___| |_|\n" +
                "                           |___/";

        System.out.println("Hello from\n" + logo);
    }

    public static void printFoundModule(Module foundModule) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching modules in your list:");
        System.out.println("       " + foundModule);
        System.out.println("    ____________________________________________________________");
    }

    public static void printNoModuleFound() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     There are no matching modules in your list:");
        System.out.println("    ____________________________________________________________");
    }

    public static void printDeletedModule(Module deletedModule, int numOfModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this module:");
        System.out.println("       " + deletedModule);
        System.out.println("     Now you have " + numOfModules + " modules in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void printAddedModule(Module addedModule, int numOfModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this module:");
        System.out.println("       " + addedModule);
        System.out.println("     Now you have " + numOfModules + " modules in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void printModuleList(ArrayList<Module> listOfModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the modules in your list:");
        for (int i = 0; i < listOfModules.size(); i++) {
            System.out.println("     " + (i + 1) + "." + listOfModules.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printFarewellMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye! Hope you enjoyed using Modganiser!");
        System.out.println("    ____________________________________________________________");
    }

    public static void printErrorMessage(DukeException e) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Error: " + e.getDescription());
        System.out.println("    ____________________________________________________________");
    }

}

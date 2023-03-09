package seedu.duke;
import java.util.ArrayList;

public class Print {

    public static void printHelloMessage() {
        String logo = "Modganiser";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
    }

    public static void printFoundModule(Module foundModule) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching modules in your list:");
        System.out.println("     " + foundModule);
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

}

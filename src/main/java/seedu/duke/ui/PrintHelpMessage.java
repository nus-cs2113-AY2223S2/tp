package seedu.duke.ui;

//@@author ChubbsBunns
//@@author Khulon
public class PrintHelpMessage {
    public static void showAvailableCommands() {
        System.out.println("These are some commands available:");
        System.out.println("[generate]");
        System.out.println("\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x");
        System.out.println("\tFILTER stands for a specific requirement you want to include in your exercise");
        System.out.println("[filters]");
        System.out.println("\tView all available filters");
        System.out.println("[plans]");
        System.out.println("\tShow all plans");
        System.out.println("[planner]");
        System.out.println("\tEnter workout plan editor");
        System.out.println("[find]");
        System.out.println("\tfinds all relevant exercises based on the keyword : find [keyword]");
        System.out.println("[bye]");
        System.out.println("\tEnd the program");
    }

    public static void printFiltersAvailable() {
        String newline = System.lineSeparator();
        System.out.println("By place:" + newline +
                "\t[gym] exercises that can be done with gym equipment" + newline +
                "\t[static]: exercises that only require your body" + newline +
                "By difficulty:" + newline +
                "\t[easy] exercises of low intensity" + newline +
                "\t[medium] exercises of medium intensity" + newline +
                "\t[hard] exercises of hard intensity" + newline +
                "By Body part:" + newline +
                "\t[upper] exercises that train your upper body" + newline +
                "\t[core] exercises that train your core" + newline +
                "\t[legs] exercises that train your legs");
    }

    public static void showAvailablePlannerCommands() {
        System.out.println("These are some commands available: ");
        System.out.println("[add]");
        System.out.println("\tCreate a new plan on a day of the week: add monday plan_name FILTER1 FILTER2 ... x");
        System.out.println("\tFILTER stands for a specific requirement you want to include in your exercise");
        System.out.println("[delete]");
        System.out.println("\tdelete a plan on a day of the week: delete monday plan_name");
        System.out.println("[plans]");
        System.out.println("\tShow all plans");
        System.out.println("[filters]");
        System.out.println("\tView all available filters");
        System.out.println("[exit]");
        System.out.println("\tExit workout plan editor");
        //System.out.println(ErrorMessages.ERROR_GLOBAL_INVALID_COMMAND.toString());
    }

    public static void unknownCommandMessage() {
        System.out.println("Unknown command! Type [help] to see what we can do!");
    }

}

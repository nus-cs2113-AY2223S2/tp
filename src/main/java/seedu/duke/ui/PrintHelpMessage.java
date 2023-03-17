package seedu.duke.ui;

public class PrintHelpMessage {
    public static void showAvailableCommands() {
        System.out.println("These are some commands available: ");
        System.out.println("[generate]");
        System.out.println("\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x");
        System.out.println("\tFILTER stands for a specific requirement you want to include in your exercise");
        System.out.println("[filters]");
        System.out.println("\tView all available filters");
        System.out.println("[bye]");
        System.out.println("\tEnd the program");
        //System.out.println(ErrorMessages.ERROR_GLOBAL_INVALID_COMMAND.toString());
    }
    public static void printFiltersAvailable() {
        System.out.println("By place:\n" +
                "\t[gym] exercises that can be done with gym equipment\n" +
                "\t[static]: exercises that only require your body\n" +
                "By difficulty:\n" +
                "\t[easy] exercises of low intensity\n" +
                "\t[medium] exercises of medium intensity\n" +
                "\t[hard] exercises of hard intensity\n" +
                "By Body part:\n" +
                "\t[arms] exercises that trains your arms\n" +
                "\t[core] exercises that trains your core\n" +
                "\t[legs] exercises that trains your legs\n");
    }
    public static void unknownCommandMessage() {
        System.out.println("Unknown command! Type [help] to see what we can do!");
    }

}

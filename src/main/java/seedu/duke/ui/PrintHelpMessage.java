package seedu.duke.ui;

public class PrintHelpMessage {
    public static void showAvailableCommands() {
        System.out.println("These are some commands available:");
        System.out.println("[generate]");
        System.out.println("\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x");
        System.out.println("\tFILTER stands for a specific requirement you want to include in your exercise");
        System.out.println("[filters]");
        System.out.println("\tView all available filters");
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
                "\t[arms] exercises that train your arms" + newline +
                "\t[core] exercises that train your core" + newline +
                "\t[legs] exercises that train your legs");
    }
    public static void unknownCommandMessage() {
        System.out.println("Unknown command! Type [help] to see what we can do!");
    }

}

package seedu.duke.ui;

//@@author ChubbsBunns
//@@author Khulon
//@@author ghzr0
public class PrintHelpMessage {
    public static void showAvailableCommands() {
        System.out.println("These are some commands available:");
        System.out.println("[generate]");
        System.out.println("\tGenerate a specific list of exercises: generate FILTER1 FILTER2 ... x");
        System.out.println("\tFILTER stands for a specific requirement you want to include in your exercise");
        System.out.println("[filters]");
        System.out.println("\tView all available filters");
        System.out.println("ippt [AGE] [RUNTIME] [PUSHUP SCORE] [SITUP SCORE]");
        System.out.println("\tStarts an IPPT exercise session with input scores for the 3 sets of exercises.");
        System.out.println("[start]");
        System.out.println("\tStart a workout session");
        System.out.println("[history]");
        System.out.println("\tView the information on all workout sessions you have completed");
        System.out.println("[data]");
        System.out.println("\tView your completed exercises as well as the number of times " +
                "you have completed each exercise");
        System.out.println("[delete]");
        System.out.println("\tDelete a workout session you have within your " +
                "workout history: delete NUMBER");
        System.out.println("\tNUMBER refers to the session number of the " +
                "workout session you wish to delete");
        System.out.println("[plans]");
        System.out.println("\tShow all plans");
        System.out.println("[planner]");
        System.out.println("\tEnter workout plan editor");
        System.out.println("[quick]");
        System.out.println("\tGenerate a planned exercise: quick PLAN_NAME x");
        System.out.println("\tPLAN_NAME needs has to be in your planner, and x is the number of exercises");
        System.out.println("[find]");
        System.out.println("\tfinds all relevant exercises based on the keyword : find [keyword]");
        System.out.println("[achievements]");
        System.out.println("\tShows all the available achievements, their requirements " +
                             "and whether they have been achieved or not");
        System.out.println("[clear_achievements]");
        System.out.println("\tClears all the data of finished exercises for the achievements database, resetting " +
                           "counters for all achievements. " +
                           " \n\tDo note that this command does not clear the counters " +
                           "for each specific exercise, hence the number of each exercise completed from the " +
                           "data command will NOT be cleared.");
        System.out.println("[exit]");
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
    }

    public static void showAvailableExerciseSessionCommands() {
        System.out.println("These are some commands available: ");
        System.out.println("[current]");
        System.out.println("\tShows you the list of exercises that you have in your current workout session.");
        System.out.println("[finish]");
        System.out.println("\tComplete your current workout session!");
        System.out.println("[cancel]");
        System.out.println("\tTerminate your current workout session.");
    }

    public static void unknownCommandMessage() {
        System.out.println("Unknown command! Type [help] to see what we can do!");
    }

}

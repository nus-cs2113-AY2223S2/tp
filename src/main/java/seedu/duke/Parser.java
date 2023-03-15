package seedu.duke;

import java.util.ArrayList;
import java.io.IOException;

public class Parser {
    private static ArrayList<University> universities = new DataReader().getUniversities();
    private static ArrayList<Module> puModules = new DataReader().getModules();
    private static final String LINE = "____________________________________________________________";
    private static final String COMMAND_INPUT_ERROR = "Please type in the correct command input";

    public static void printGreeting() {
        System.out.println("\n" +
                "  ____  _____ ____    _   _      _                 \n" +
                " / ___|| ____|  _ \\  | | | | ___| |_ __   ___ _ __ \n" +
                " \\___ \\|  _| | |_) | | |_| |/ _ \\ | '_ \\ / _ \\ '__|\n" +
                "  ___) | |___|  __/  |  _  |  __/ | |_) |  __/ |   \n" +
                " |____/|_____|_|     |_| |_|\\___|_| .__/ \\___|_|   \n" +
                "                                  |_|              \n");
    }

    // Todo: Throw Exception when commandWords.size() == 1
    public static ArrayList<String> parseCommand(String userInput) {
        String[] input = userInput.split((" "), 2);
        ArrayList<String> commandWords = new ArrayList<>();
        String commandInput = input[0];
        commandWords.add(commandInput);
        if (input.length > 1) {
            String commandSpecifics = input[1];
            commandWords.add(commandSpecifics);
        }
        return commandWords;
    }

    public static void printPUModules(int univID) {
        ArrayList<Module> puModulesToPrint = new ArrayList<>();
        for (Module module : puModules) {
            if (module.getUnivId() == univID) {
                puModulesToPrint.add(module);
            }
        }
        int puModulesIndex = 0;
        for (Module module : puModulesToPrint) {
            puModulesIndex++;
            String moduleCode = module.getModuleCode();
            String moduleName = module.getModuleName();
            int moduleMCs = module.getModuleMCs();
            String nusModuleCode = module.getNusModuleCode();
            String nusModuleName = module.getNusModuleName();
            int nusModuleMCs = module.getNusModuleMCs();
            System.out.print(puModulesIndex + ". ");
            System.out.println("[" + moduleCode + "]" + "[" + moduleName + "]" + "[" + moduleMCs + "]");
            System.out.print("   maps to ----> ");
            System.out.println("[" + nusModuleCode + "]" + "[" + nusModuleName + "]" + "[" + nusModuleMCs + "]");
        }
    }

    public static void printPUList() {
        System.out.println(LINE);
        for (University university : universities) {
            int uniId = university.getUnivId();
            String uniName = university.getUnivName();
            String uniAbbName = university.getUnivAbbName();
            System.out.println(uniId + ". " + uniName + " " + uniAbbName);
        }
        System.out.println(LINE);
    }

    public static void printCurrentModList(ArrayList<Module> modules) {
        int listIndex = 0;
        System.out.println(LINE);
        for (Module module : modules) {
            listIndex++;
            String moduleCode = module.getModuleCode();
            String moduleName = module.getModuleName();
            int moduleMCs = module.getModuleMCs();
            String nusModuleCode = module.getNusModuleCode();
            String nusModuleName = module.getNusModuleName();
            int nusModuleMCs = module.getNusModuleMCs();
            System.out.print(listIndex + ".");
            System.out.println("[" + moduleCode + "]" + "[" + moduleName + "]" + "[" + moduleMCs + "]");
            System.out.print("   map to ----> ");
            System.out.println("[" + nusModuleCode + "]" + "[" + nusModuleName + "]" + "[" + nusModuleMCs + "]");
        }
        System.out.println(LINE);
    }

    /**
     * Deletes the module corresponding to the uni specified by user. Module will the removed from user's
     * saved list of modules.
     *
     * @param indexToDelete Index of that module that is given in user input.
     * @param uniModuleList The corresponding ArrayList of that specified uni.
     * @param database      Database of the user's saved list of modules.
     */
    public static void deleteModule(int indexToDelete, ArrayList<Module> uniModuleList,
                                    Storage database) {
        int indexToZeroBased = indexToDelete - 1;
        try {
            uniModuleList.remove(indexToZeroBased);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        try {
            database.writeListToFile(uniModuleList);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
        }
    }
}


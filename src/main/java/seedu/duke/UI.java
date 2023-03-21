package seedu.duke;

import java.util.ArrayList;

public class UI {
    private static final String LIST_PU_MESSAGE = "This is the list of PUs:";
    private static final String LIST_CURRENT_MESSAGE = "List of Added modules:";
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_MOD_MESSAGE = "This module has been added to the current module list!";
    private static final String DELETE_MOD_MESSAGE = "This module has been deleted from the current module list!";

    private static final String ADD_MOD_FAILURE_MESSAGE = "Save Module Failed";
    private static final String COMMAND_INPUT_ERROR = "Please type in the correct command input";
    private static final String WELCOME_MESSAGE = "~Welcome to SEP Helper~";
    private static final String READ_COMMAND_INPUT = "What can I do for you?";
    private static final String HELP_MESSAGE = "\nType /help if you need help getting started :)";
    private static final String INPUT_NOT_INT_MESSAGE = "The input for the given command is not an integer";
    private static final String INVALID_PU_MESSAGE = "PU not found :( Please type in the correct PU name\n";
    private static final String INVALID_MODULE_MESSAGE = "Module not found :( Please type in the correct MODULE name\n";
    private static ArrayList<Module> puModules = new DataReader().getModules();
    private static ArrayList<University> universities = new DataReader().getUniversities();

    public UI() {
    }

    public void printPUListMessage() {
        System.out.println(LIST_PU_MESSAGE);
        System.out.println(LINE);
    }

    public void printCurrentListMessage() {
        System.out.println(LIST_CURRENT_MESSAGE);
    }

    public void printPUModListMessage(String univName) {
        System.out.println(univName + " Modules");
        System.out.println(LINE);
    }

    public void printAddModMessage() {
        System.out.println(ADD_MOD_MESSAGE);
        System.out.println(LINE);
    }

    public void printDeleteModMessage() {
        System.out.println(DELETE_MOD_MESSAGE);
        System.out.println(LINE);
    }

    public void printInputNotNumMessage() {
        System.out.println(INPUT_NOT_INT_MESSAGE);
        System.out.println(LINE);
    }

    public String getInvalidPuMessage() {
        return INVALID_PU_MESSAGE + LINE;
    }

    public String getInvalidModuleMessage() {
        return INVALID_MODULE_MESSAGE + LINE;
    }

    public void printGreetingMessage() {
        System.out.println("\n" +
                "  ____  _____ ____    _   _      _                 \n" +
                " / ___|| ____|  _ \\  | | | | ___| |_ __   ___ _ __ \n" +
                " \\___ \\|  _| | |_) | | |_| |/ _ \\ | '_ \\ / _ \\ '__|\n" +
                "  ___) | |___|  __/  |  _  |  __/ | |_) |  __/ |   \n" +
                " |____/|_____|_|     |_| |_|\\___|_| .__/ \\___|_|   \n" +
                "                                  |_|              \n");

        System.out.println(WELCOME_MESSAGE);
        System.out.println(READ_COMMAND_INPUT);
        System.out.println(HELP_MESSAGE);
        System.out.println(LINE);
    }

    public void printPUModules(int univID) {
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
        System.out.println(LINE);
    }

    public void printPUList() {
        System.out.println(LINE);
        for (University university : universities) {
            int uniId = university.getUnivId();
            String uniName = university.getUnivName();
            String uniAbbName = university.getUnivAbbName();
            System.out.println(uniId + ". " + uniName + " " + uniAbbName);
        }
        System.out.println(LINE);
    }

    public void printCurrentModList(ArrayList<Module> modules) {
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
            System.out.print("   maps to ----> ");
            System.out.println("[" + nusModuleCode + "]" + "[" + nusModuleName + "]" + "[" + nusModuleMCs + "]");
        }
        System.out.println(LINE);
    }

    public void printInvalidInputMessage() {
        System.out.println("Invalid Input");
    }

    public static void printAddModuleFailureMessage() {
        System.out.println(ADD_MOD_FAILURE_MESSAGE);
    }

    public static void printHelpCommandMessage() {
        System.out.println("Here are the list of commands: \n"
                + "LIST PU                     : Provides the list of Partner Universities available\n"
                + "LIST [PU ABBRV]             : Provides the list of all modules available " +
                "in the specified Partner University\n"
                + "LIST CURRENT                : Provides the list of modules that the user has added to his/her " +
                "list of interest\n"
                + "ADD [PU ABBRV]/[MODULE CODE]: Adds the specified module into user's current list of modules\n"
                + "REMOVE [INDEX]              : Removes the specified module by index from user's current list\n"
                + "EXIT                        : Exits the program\n\n");
        System.out.println(READ_COMMAND_INPUT);
        System.out.println(LINE);
    }

    public static void printExceptionErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printExitMessage() {
        System.out.println("Exiting program now");
    }
}

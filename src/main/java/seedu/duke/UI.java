package seedu.duke;

import java.util.ArrayList;

public class UI {
    private static final String LIST_PU_MESSAGE = "This is the list of PUs:";
    private static final String LIST_CURRENT_PU_MESSAGE = "List of Added Modules for: ";
    private static final String CURRENT_LIST_PU_EMPTY = "The current module list is empty for: ";
    private static final String LIST_CURRENT_MESSAGE = "List of Added modules:";
    private static final String LIST_DEADLINES_MESSAGE = "List of Deadlines:";
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_MOD_MESSAGE = "This module has been added to the current module list!";
    private static final String ADD_DEADLINE_MESSAGE = "This deadline has been added to the current deadlines";
    private static final String DELETE_MOD_MESSAGE = "This module has been deleted from the current module list!";

    private static final String ADD_MOD_FAILURE_MESSAGE = "Save Module Failed";
    private static final String ADD_DEADLINE_FAILURE_MESSAGE = "Save Deadline task Failed";
    private static final String COMMAND_INPUT_ERROR = "Please type in the correct command input";
    private static final String WELCOME_MESSAGE = "~Welcome to SEP Helper~";
    private static final String READ_COMMAND_INPUT = "What can I do for you?";
    private static final String HELP_MESSAGE = "\nType /help if you need help getting started :)";
    private static final String INPUT_NOT_INT_MESSAGE = "The input for the given command is not an integer";
    private static final String INVALID_PU_MESSAGE = "PU not found :( Please type in the correct PU name";
    private static final String INVALID_MODULE_MESSAGE = "Module not found :( Please type in the correct MODULE name";
    private static final String INVALID_SEARCH_MODULE_MESSAGE = "There is no matching module code found.\n"
                                                + "Please ensure that you have typed in the correct NUS Module Code";
    private static final String INVALID_BUDGET_MESSAGE = "Please type in the correct budget command";
    private static final String CURRENT_MOD_LIST_EMPTY = "The current module list is empty";
    private static final String CURRENT_DEADLINES_LIST_EMPTY = "The current deadlines list is empty";
    private static final String FOUND_LIST_MESSAGE = "Here is/are the list/s of modules that can map "
                                                        + "this NUS module code: ";
    private static ArrayList<Module> puModules = new DataReader().getModules();
    private static ArrayList<University> universities = new DataReader().getUniversities();

    public UI() {
    }

    public void printPUListMessage() {
        System.out.println(LIST_PU_MESSAGE);
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
        return INVALID_PU_MESSAGE;
    }

    public String getInvalidSearchModuleMessage() {
        return INVALID_SEARCH_MODULE_MESSAGE;
    }

    public String getInvalidModuleMessage() {
        return INVALID_MODULE_MESSAGE;
    }

    public String getCommandInputError() {
        return COMMAND_INPUT_ERROR;
    }

    public String getInvalidBudgetMessage() {
        return INVALID_BUDGET_MESSAGE;
    }

    public String getLine() {
        return LINE;
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

    public void printFoundNusModules(ArrayList<Module> foundNusModList, String nusModCode,
                                     ArrayList<University> universities) {
        System.out.println(FOUND_LIST_MESSAGE + nusModCode);
        System.out.println(LINE);
        int foundModIndex = 0;
        int printIndex = 1;
        int prevModulePuId = 0;
        for (Module modToPrint : foundNusModList) {
            String moduleCode = modToPrint.getModuleCode();
            String moduleName = modToPrint.getModuleName();
            int moduleMCs = modToPrint.getModuleMCs();
            int currModulePuId = modToPrint.getUnivId();
            int puIndex= currModulePuId - 1;
            String currPuAbbr = universities.get(puIndex).getUnivAbbName();
            if (foundModIndex >= 1) {
                prevModulePuId = foundNusModList.get(foundModIndex - 1).getUnivId();
            }
            if (currModulePuId == prevModulePuId) {
                printIndex++;
                System.out.println(printIndex + ". [" + moduleCode + "]" + "[" + moduleName + "]"
                                        + "[" + moduleMCs + "]");
            } else {
                printIndex = 1;
                if (printIndex == 1) {
                    System.out.println(LINE);
                    System.out.println(currPuAbbr);
                    System.out.println(LINE);
                }
                System.out.println(printIndex + ". [" + moduleCode + "]"
                                        + "[" + moduleName + "]" + "[" + moduleMCs + "]");
            }
            foundModIndex++;
        }
    }

    public void printPUModules(int univID) {
        ArrayList<Module> puModulesToPrint = new ArrayList<>();
        for (Module puModule : puModules) {
            if (puModule.getUnivId() == univID) {
                puModulesToPrint.add(puModule);
            }
        }
        int puModulesIndex = 0;
        for (Module puModuleToPrint : puModulesToPrint) {
            puModulesIndex++;
            String moduleCode = puModuleToPrint.getModuleCode();
            String moduleName = puModuleToPrint.getModuleName();
            int moduleMCs = puModuleToPrint.getModuleMCs();
            String nusModuleCode = puModuleToPrint.getNusModuleCode();
            String nusModuleName = puModuleToPrint.getNusModuleName();
            int nusModuleMCs = puModuleToPrint.getNusModuleMCs();
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

    public void printAllCurrentModList(ArrayList<Module> modules) {
        for (int i = 0; i < universities.size(); i++) {
            University currentUniversity = universities.get(i);
            int uniID = currentUniversity.getUnivId();
            printCurrentPuModList(modules, uniID);
            System.out.println();
        }
    }

    public void printDeadlinesList(ArrayList<Deadline> deadlines) {
        int listIndex = 0;
        if (deadlines.size() < 1) {
            System.out.println(CURRENT_DEADLINES_LIST_EMPTY);
        } else {
            System.out.println(LIST_DEADLINES_MESSAGE);
            System.out.println(LINE);
            for (Deadline deadline : deadlines) {
                listIndex++;
                String deadlineTask = deadline.getTask();
                String deadlineDueDate = deadline.getDueDate();
                System.out.println(listIndex + ". " + deadlineTask + " [Due by: " + deadlineDueDate + "]");
            }
        }
        System.out.println(LINE);
    }

    public void printAddDeadlineMessage() {
        System.out.println(ADD_DEADLINE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printReminderMessage(Deadline deadline, int counter) {
        String deadlineTask = deadline.getTask();
        String deadlineDueDate = deadline.getDueDate();
        System.out.println("REMINDER! The following task(s) is/are due soon: ");
        System.out.println(counter + ". " + deadlineTask + " [Due by: " + deadlineDueDate + "]");
        System.out.println(LINE);
    }

    public void printInvalidInputMessage() {
        System.out.println("Invalid Input");
    }

    public static void printAddModuleFailureMessage() {
        System.out.println(ADD_MOD_FAILURE_MESSAGE);
    }

    public static void printAddDeadlineFailureMessage() {
        System.out.println(ADD_DEADLINE_FAILURE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printHelpCommandMessage() {
        System.out.println("Here are the list of commands:\n"
                + "LIST PU                     : Provides the list of Partner Universities available\n"
                + "LIST [PU ABBRV]             : Provides the list of all modules available " +
                "in the specified Partner University\n"
                + "LIST [PU INDEX]             : Provides the list of all modules available " +
                "in the specified Partner University\n"
                + "                              by index of LIST PU\n"
                + "LIST CURRENT                : Provides the list of modules that the user has added to his/her " +
                "list of interest\n"
                + "LIST CURRENT [PU ABBRV]     : Provides the list of modules that user has added to his list of " +
                "list of interest for the specified PU\n"
                + "ADD [PU ABBRV]/[MODULE CODE]: Adds the specified module into user's current list of modules\n"
                + "REMOVE [INDEX]              : Removes the specified module by index from user's current list\n"
                + "EXIT                        : Exits the program\n\n");
        System.out.println(READ_COMMAND_INPUT);
        System.out.println(LINE);
    }

    /**
     * Prints out user added modules of a specified partner university using uniID as identity.
     * The function first picks out all modules specified to the university selected, and prints out module
     * information to the user afterwards.
     *
     * @param modules ArrayList of all modules that user has selected.
     * @param uniID Unique partner university ID
     */
    public void printCurrentPuModList(ArrayList<Module> modules, int uniID) {
        ArrayList<Module> puModulesToPrint = new ArrayList<>();
        for (int i = 0; i < modules.size(); i++) {
            Module currentModule = modules.get(i);
            int currentModuleUnivId = currentModule.getUnivId();
            if (currentModuleUnivId == uniID) {
                puModulesToPrint.add(currentModule);
            }
        }
        String universityName = "";
        for (int i = 0; i < universities.size(); i++) {
            University currentUniversity = universities.get(i);
            if (currentUniversity.getUnivId() == uniID) {
                universityName = currentUniversity.getUnivName();
            }
        }
        int listIndex = 0;
        if (puModulesToPrint.size() < 1) {
            System.out.println(CURRENT_LIST_PU_EMPTY + universityName);
            System.out.println(LINE);
            System.out.println(LINE);
        } else {
            System.out.println(LIST_CURRENT_PU_MESSAGE + universityName);
            System.out.println(LINE);
            for (Module module : puModulesToPrint) {
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
    }
    public static void printExceptionErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printExitMessage() {
        System.out.println("Exiting program now");
    }
}

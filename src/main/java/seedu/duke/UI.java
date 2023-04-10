package seedu.duke;

import seedu.duke.budget.Accommodation;
import seedu.duke.budget.AirplaneTicket;
import seedu.duke.budget.BudgetPlanner;
import seedu.duke.budget.Entertainment;
import seedu.duke.budget.Food;
import seedu.duke.budget.GoodsAndServices;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class UI {
    public static final String INVALID_BUDGET_AMOUNT_MESSAGE = "Please input a valid amount which is positive whole " +
            "number more than equals to 0 and less than equal to " + BudgetPlanner.MAX_BUDGET;
    private static final int LIST_PU_LENGTH_FOR_PU_ABB = 52;
    private static final String LIST_PU_HEADER_MESSAGE = "   Partner University Name                  " +
            "         PU Abb    ";
    private static final String LIST_PU_MESSAGE = "This is the list of PUs:";
    private static final String LIST_CURRENT_PU_MESSAGE = "List of Added Modules for: ";
    private static final String CURRENT_LIST_PU_EMPTY = "The current module list is empty for: ";
    private static final String LIST_CURRENT_MESSAGE = "List of Added modules:";
    private static final String LIST_DEADLINES_MESSAGE = "List of Deadlines:";
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_MOD_MESSAGE = "This module has been added to the current module list!";
    private static final String ADD_DEADLINE_MESSAGE = "This deadline has been added to the current deadlines";
    private static final String DELETE_MOD_MESSAGE = "This module has been deleted from the current module list!";
    private static final String DELETE_DEADLINE_MESSAGE = "This deadline has been deleted from the current deadline " +
            "list!";
    private static final String DELETE_NUM_ERROR = "Deletion failed :( Please check the index to be removed again";

    private static final String ADD_MOD_FAILURE_MESSAGE = "Save Module Failed";
    private static final String ADD_DEADLINE_FAILURE_MESSAGE = "Save Deadline task Failed";
    private static final String COMMAND_INPUT_ERROR = "Please type in the correct command input";
    private static final String WRITE_TO_DATABASE_ERROR = "Unable to write to database :(";
    private static final String WELCOME_MESSAGE = "~Welcome to SEP Helper~";
    private static final String READ_COMMAND_INPUT = "What can I do for you?";
    private static final String HELP_MESSAGE = "\nType /help if you need help getting started :)";
    private static final String INPUT_NOT_INT_MESSAGE = "The input for the given command is not an integer";
    private static final String INVALID_PU_MESSAGE = "PU not found :( Please type in the correct PU Abbreviation";
    private static final String INVALID_MODULE_MESSAGE = "Module not found :( Please type in the correct MODULE name";
    private static final String INVALID_MODULE_INDEX_MESSAGE = "Module not found :( Please type in a correct index";
    private static final String INVALID_SEARCH_MODULE_MESSAGE = "There is no matching module code found.\n"
            + "Please ensure that you have typed in the correct NUS Module Code";
    private static final String INVALID_BUDGET_MESSAGE = "Please type in the correct budget command";
    private static final String CURRENT_MOD_LIST_EMPTY = "The current module list is empty";
    private static final String CURRENT_DEADLINES_LIST_EMPTY = "The current deadlines list is empty";
    private static final String FOUND_LIST_MESSAGE = "Here is/are the list/s of modules that can map "
            + "this NUS module code: ";
    private static final String MODULE_ALREADY_EXIST_MESSAGE = "This module already exists in your list";
    private static final String PU_UNI_NAME_MAPS_TO_NUS_MESSAGE = " Module] maps to ----> [NUS Module]";
    private static final String MAPPABLE_NUS_MOD_CODE_MESSAGE = "This is the list of mappable NUS module codes";
    private static final String NOT_INTEGER_MESSAGE = "Please enter an integer between 0 and 2,147,483,647";
    private static ArrayList<Module> puModules = DataReader.getDataReaderOneInstance().getModules();
    private static ArrayList<University> universities = DataReader.getDataReaderOneInstance().getUniversities();

    /**
     * UI has a Singleton Design Pattern
     */
    private static UI uiOneInstance = null;

    private UI() {
    }

    /**
     * Returns the one instance of the UI class, OR if there is no such instance yet, instantiates an instance of UI.
     *
     * @return The Singleton Instance of UI class.
     */
    public static UI getUiOneInstance() {
        if (uiOneInstance == null) {
            uiOneInstance = new UI();
        }
        return uiOneInstance;
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public void printPUListMessage() {
        System.out.println(LIST_PU_MESSAGE);
    }

    public void printPUModListMessage(String univName) {
        System.out.println(univName + " Modules");
        System.out.println("[" + univName + PU_UNI_NAME_MAPS_TO_NUS_MESSAGE);
        System.out.println(LINE);
    }

    public void printAddModMessage(Module moduleToAdd) {
        printModule(moduleToAdd);
        System.out.println(ADD_MOD_MESSAGE);
        System.out.println(LINE);
    }

    public void printDeleteModMessage() {
        System.out.println(DELETE_MOD_MESSAGE);
        System.out.println(LINE);
    }

    public void printModuleDeleted(Module moduleDeleted) {
        if (moduleDeleted == null) {
            return;
        }
        printModule(moduleDeleted);
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

    /**
     * Getter function for the Invalid Module Index Message
     * String: "Module not found :( Please type in a correct index"
     *
     * @return String of INVALID_MODULE_INDEX_MESSAGE
     */
    public String getInvalidModuleMessage() {
        return INVALID_MODULE_INDEX_MESSAGE;
    }

    public String getCommandInputError() {
        return COMMAND_INPUT_ERROR;
    }

    public static void printDeleteNumError() {
        System.out.println(DELETE_NUM_ERROR);
        System.out.println(LINE);
    }

    public String getInvalidBudgetMessage() {
        return INVALID_BUDGET_MESSAGE;
    }

    public String getLine() {
        return LINE;
    }

    public void printGreetingMessage() {
        System.out.println("\n" + "  ____  _____ ____    _   _      _                 \n"
                + " / ___|| ____|  _ \\  | | | | ___| |_ __   ___ _ __ \n"
                + " \\___ \\|  _| | |_) | | |_| |/ _ \\ | '_ \\ / _ \\ '__|\n"
                + "  ___) | |___|  __/  |  _  |  __/ | |_) |  __/ |   \n"
                + " |____/|_____|_|     |_| |_|\\___|_| .__/ \\___|_|   \n"
                + "                                  |_|              \n");

        System.out.println(WELCOME_MESSAGE);
        System.out.println(READ_COMMAND_INPUT);
        System.out.println(HELP_MESSAGE);
        System.out.println(LINE);
    }

    /**
     * Retrieve a HashSet of  Nus modules available by looping through the all the Partner Universities modules
     * @param allModules ArrayList of Module containing all the Partner Universities modules
     * @return nusModulesCodeList HashSet of String containing available Nus modules
     */
    public HashSet<String> removeDupeNusMods(ArrayList<Module> allModules) {
        HashSet<String> nusModulesCodeList = new HashSet<>();
        for (Module m : allModules) {
            String nusModuleCode = m.getNusModuleCode();
            nusModulesCodeList.add(nusModuleCode);
        }
        return nusModulesCodeList;
    }

    /**
     * Prints out the list of available Nus Module details that can be used to search for
     * mappable Partner Universities modules
     * @param allModules ArrayList of Module containing all the Partner Universities modules
     */
    public void printNusMods(ArrayList<Module> allModules) {
        HashSet<String> nusModuleCodeList = removeDupeNusMods(allModules);
        System.out.println(LINE);
        System.out.println(MAPPABLE_NUS_MOD_CODE_MESSAGE);
        System.out.println(LINE);
        int listIndex = 0;
        for (String nusModCode : nusModuleCodeList) {
            listIndex++;
            for (Module m : allModules) {
                String nusModuleCode = m.getNusModuleCode();
                String nusModuleName = m.getNusModuleName();
                int nusModuleMc = m.getNusModuleMCs();
                if (nusModuleCode.equalsIgnoreCase(nusModCode)) {
                    System.out.println(listIndex + ". [" + nusModuleCode + "] " + nusModuleName + ", "
                            + nusModuleMc + " MCs");
                    break;
                }
            }
        }
        System.out.println(LINE);
    }

    /**
     * Prints out a list of mappable Partner Universities modules from the specific nusModCode inputted by the user. It
     * will loop through foundNusModList arraylist and print out the Partner University module if it can be mapped from
     * the specific nusModCode. The lists of mappable modules will be print out accordingly under their
     * respective Partner University.
     * @param foundNusModList Arraylist of Module containing all the mappable Partner Universities modules.
     * @param nusModCode String containing the specific Nus Module Code to search by inputted by user.
     * @param universities ArrayList of University containing all the Partner Universities details.
     */
    public void printFoundNusModules(ArrayList<Module> foundNusModList, String nusModCode,
                                     ArrayList<University> universities) {
        foundNusModList.sort(Comparator.comparingInt(Module::getUnivId));
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
            int puIndex = currModulePuId - 1; //zero indexing
            String currPuAbbr = universities.get(puIndex).getUnivAbbName();
            if (foundModIndex >= 1) {
                prevModulePuId = foundNusModList.get(foundModIndex - 1).getUnivId();
            }
            if (currModulePuId == prevModulePuId) {
                printIndex++;
                System.out.println(printIndex + ". [" + moduleCode + "]"
                        + "[" + moduleName + "]" + "[" + moduleMCs + "]");
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
        System.out.println(LINE);
    }

    /**
     * Filters out mappable modules according to corresponding PU by the univID and filter given, by looping
     * through all mappable modules and looking at their univID and checking if it aligns with filter given.
     * After filtering, prints out module details to users.
     *
     * @param univID Unique integer index corresponding to Partner University.
     * @param filter String containing filter input from user, filter is yet to be distinguished between
     *               MC Filter or Name Filter
     */
    public void printPUModules(int univID, String filter) {
        ArrayList<Module> puModulesToPrint = new ArrayList<>();
        for (Module puModule : puModules) {
            if ((puModule.getUnivId() == univID) && puModule.isInFilter(filter)) {
                puModulesToPrint.add(puModule);
            }
        }
        int puModulesIndex = 0;
        for (Module puModuleToPrint : puModulesToPrint) {
            puModulesIndex++;
            System.out.print(puModulesIndex + ". ");
            printModule(puModuleToPrint);
        }
        System.out.println(LINE);
    }


    //@@author MuxPotato

    /**
     * Prints out mapping details, from both NUS and Korea PU, associated with the Module object passed.
     *
     * @param puModuleToPrint Module object whom information is to be printed out.
     */
    private void printModule(Module puModuleToPrint) {
        String moduleCode = puModuleToPrint.getModuleCode();
        String moduleName = puModuleToPrint.getModuleName();
        int moduleMCs = puModuleToPrint.getModuleMCs();
        String nusModuleCode = puModuleToPrint.getNusModuleCode();
        String nusModuleName = puModuleToPrint.getNusModuleName();
        int nusModuleMCs = puModuleToPrint.getNusModuleMCs();
        System.out.print("[" + moduleCode + "]" + "[" + moduleName + "]" + "[" + moduleMCs + "]");
        System.out.print("   maps to ----> ");
        System.out.println("[" + nusModuleCode + "]" + "[" + nusModuleName + "]" + "[" + nusModuleMCs + "]");
    }
    //@@author

    /**
     * Prints out the list of Korea Partner Universities, formatted to be aligned.
     * Uses a while loop to print whitespaces till a certain length is obtained for PU Abbrev to be printed in a column.
     */
    public void printPUList() {
        System.out.println(LINE);
        System.out.println(LIST_PU_HEADER_MESSAGE);
        for (University university : universities) {
            int uniId = university.getUnivId();
            String uniName = university.getUnivName();
            String uniAbbName = university.getUnivAbbName();
            String uniIDAndName = uniId + ". " + uniName;
            System.out.print(uniIDAndName);
            int uniIDAndNameLength = uniIDAndName.length();
            int counter = LIST_PU_LENGTH_FOR_PU_ABB - uniIDAndNameLength;
            while (counter >= 0) {
                System.out.print(" ");
                counter--;
            }
            System.out.println(uniAbbName);
        }
        System.out.println(LINE);
    }

    /**
     * Prints out all current modules users added for all Partner Universities.
     *
     * @param modules ArrayList that holds all the modules users has added to his/her list.
     */
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

    public void printDeleteDeadlineMessage() {
        System.out.println(DELETE_DEADLINE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printStorageCorruptedMessage() {
        System.out.println("Storage is corrupted, corrupted modules have been deleted");
    }

    public static void printModuleCorruptDeleteMessage() {
        System.out.println("One Module Corrupted, will be deleted");
    }

    public static void printReminderMessage() {
        System.out.println("REMINDER! The following task(s) is/are due soon: ");
    }

    public static void printReminderDeadline(Deadline deadline, int counter) {
        String deadlineTask = deadline.getTask();
        String deadlineDueDate = deadline.getDueDate();
        System.out.println(counter + ". " + deadlineTask + " [Due by: " + deadlineDueDate + "]");
    }

    /**
     * Prints out Invalid Input Message.
     */
    public void printInvalidInputMessage() {
        System.out.println("Invalid Input");
    }

    public static void printAddModuleFailureMessage() {
        System.out.println(ADD_MOD_FAILURE_MESSAGE);
    }

    public static void printModAlreadyExistMessage() {
        printLine();
        System.out.println(MODULE_ALREADY_EXIST_MESSAGE);
        printLine();
    }

    public static void printAddDeadlineFailureMessage() {
        System.out.println(ADD_DEADLINE_FAILURE_MESSAGE);
        System.out.println(LINE);
    }

    public static void printWriteToDatabaseFailureMessage() {
        System.out.println(WRITE_TO_DATABASE_ERROR);
        System.out.println(LINE);
    }

    /**
     * Prints out the list of commands available in the program and explain how and what each commands can do.
     */
    public static void printHelpCommandMessage() {
        System.out.println("Here are the list of commands:\n"
                + "/LIST PU                                              : Provides the list of Partner Universities "
                + "available\n"
                + "/LIST [PU ABBRV]                                      : Provides the list of all modules available "
                + "in the specified\n"
                + "                                                        Partner University\n"
                + "/LIST [PU INDEX]                                      : Provides the list of all modules available "
                + "in the specified\n"
                + "                                                        Partner University by index of LIST PU\n"
                + "/LIST [PU ABBREVIATION/PU INDEX] /FILTER /MC [MC]     : Provides the list of filtered modules "
                + "according to the Partner\n"
                + "                                                        University modular credits\n"
                + "/LIST [PU ABBREVIATION/PU INDEX] /FILTER /NAME [NAME] : Provides the list of filtered modules "
                + "according to the Partner\n"
                + "                                                        University module name\n"
                + "/LIST CURRENT                                         : Provides the list of modules that the user "
                + "has added to his/her\n"
                + "                                                        list of interest\n"
                + "/LIST CURRENT [PU ABBRV]                              : Provides the list of modules that user has "
                + "added to his/her list\n"
                + "                                                        of interest for the specified PU\n"
                + "/ADD [PU ABBRV]/[INDEX]                               : Adds the specified module into user's "
                + "current list of modules\n"
                + "/REMOVE [PU ABBRV]/[INDEX]                            : Removes the specified module by index from "
                + "user's current list\n"
                + "/SEARCH [NUS MOD CODE]                                : Search for PU modules that can map the "
                + "user's targeted module\n"
                + "/SEARCH /MODS                                         : Shows a list of available Nus Modules to "
                + "search for mappable\n"
                + "                                                        PU's modules\n"
                + "/budget /budget [AMOUNT]                              : Allows the user to input/edit the total "
                + "amount of budget for\n"
                + "                                                        his/her SEP trip\n"
                + "/budget /accommodation [AMOUNT]                       : Allows the user to input/edit the total "
                + "amount of accommodation\n"
                + "                                                        cost for his/her SEP trip\n"
                + "/budget /airplane [AMOUNT]                            : Allows the user to input/edit the total "
                + "amount of airplane\n"
                + "                                                        ticket cost for his/her SEP trip\n"
                + "/budget /food [AMOUNT]                                : Allows the user to input/edit the total "
                + "amount of food cost for\n"
                + "                                                        his/her SEP trip\n"
                + "/budget /entertainment [AMOUNT]                       : Allows the user to input/edit the total "
                + "amount of entertainment\n"
                + "                                                        cost for his/her SEP trip\n"
                + "/budget /view                                         : Provides an overview of the user's planned "
                + "budget\n"
                + "/deadline/list                                        : Provides the list of deadlines the user has "
                + "added\n"
                + "/deadline/add [DEADLINE DESCRIPTION] /by [DD-MM-YYYY] : Allows the user to add in his/her own "
                + "personalized deadlines of\n"
                + "                                                        the key dates for certain SEP requirements\n"
                + "/deadline/remove [DEADLINE INDEX]                     : Allows the user to remove the specific "
                + "deadline from the list\n"
                + "/EXIT                                                 : Exits the program\n\n");
        System.out.println(READ_COMMAND_INPUT);
        System.out.println(LINE);
    }

    public static void printEditBudgetMessage(int amount) {
        System.out.println(LINE);
        System.out.println("Budget has been changed to: " + amount);
        System.out.println(LINE);
    }

    public static void printEditCostMessage(int amount, GoodsAndServices goods) {
        String goodsAndServicesType;
        if (goods instanceof Accommodation) {
            goodsAndServicesType = "Accommodation";
        } else if (goods instanceof AirplaneTicket) {
            goodsAndServicesType = "Airplane Ticket";
        } else if (goods instanceof Entertainment) {
            goodsAndServicesType = "Entertainment";
        } else if (goods instanceof Food) {
            goodsAndServicesType = "Food";
        } else {
            return;
        }
        System.out.println(LINE);
        System.out.println(goodsAndServicesType + " cost has been changed to: " + amount);
        System.out.println(LINE);
    }

    public static void printBudgetNoChangeMessage() {
        System.out.println(LINE);
        System.out.println("Budget remains unchanged, please check input amount");
        System.out.println(LINE);
    }

    public static void printCostNoChangeMessage() {
        System.out.println(LINE);
        System.out.println("Cost remains unchanged, please check input amount");
        System.out.println(LINE);
    }

    public static void printViewBudget(BudgetPlanner budgetPlanner) {
        System.out.println(LINE);
        System.out.println("Total budget: " + budgetPlanner.getBudget());
        System.out.println("Accommodation cost: " + budgetPlanner.getAccommodationTotalCost());
        System.out.println("Airplane Ticket cost: " + budgetPlanner.getAirplaneTicketTotalCost());
        System.out.println("Food cost: " + budgetPlanner.getFoodTotalCost());
        System.out.println("Entertainment cost: " + budgetPlanner.getEntertainmentTotalCost());
        System.out.println("Surplus/Deficit: " + budgetPlanner.getSurplus());
        System.out.println(LINE);
    }

    public static void printBudgetStorageCorruptedMessage() {
        System.out.println("Budget Storage is corrupted, resetting budget");
    }

    public static void printDeadlineStorageCorruptedMessage() {
        System.out.println("Deadline Storage is corrupted, deleting corrupted deadlines");
    }

    public String notIntegerError() {
        return NOT_INTEGER_MESSAGE;
    }

    public static void printInvalidBudgetAmountMessage() {
        System.out.println(INVALID_BUDGET_AMOUNT_MESSAGE);
    }

    /**
     * Prints out user added modules of a specified partner university using uniID
     * as identity. The function first picks out all modules specified to the
     * university selected, and prints out module information to the user
     * afterwards.
     *
     * @param modules ArrayList of all modules that user has selected.
     * @param uniID   Unique partner university ID
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
            assert puModulesToPrint.size() < 1 : "size of puModulesToPrint array should be < 1";
            System.out.println(CURRENT_LIST_PU_EMPTY + universityName);
            System.out.println(LINE);
            System.out.println(LINE);
        } else {
            System.out.println(LIST_CURRENT_PU_MESSAGE + universityName);
            System.out.println("[" + universityName + PU_UNI_NAME_MAPS_TO_NUS_MESSAGE);
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
                System.out.print("[" + moduleCode + "]" + "[" + moduleName + "]" + "[" + moduleMCs + "]");
                System.out.print("   maps to ----> ");
                System.out.println("[" + nusModuleCode + "]" + "[" + nusModuleName + "]" + "[" + nusModuleMCs + "]");
            }
            System.out.println(LINE);
        }
    }

    public static void printExceptionErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints out Exit Message.
     */
    public void printExitMessage() {
        System.out.println("Exiting program now");
    }
}

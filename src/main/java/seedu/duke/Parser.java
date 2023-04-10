package seedu.duke;

import seedu.duke.budget.BudgetPlanner;
import seedu.duke.command.AddDeadlineCommand;
import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.BudgetCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteDeadlineCommand;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.EditAccommodationCommand;
import seedu.duke.command.EditAirplaneTicketCommand;
import seedu.duke.command.EditBudgetCommand;
import seedu.duke.command.EditEntertainmentCommand;
import seedu.duke.command.EditFoodCommand;
import seedu.duke.command.ExceptionHandleCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCurrentCommand;
import seedu.duke.command.ListDeadlinesCommand;
import seedu.duke.command.ListPuCommand;
import seedu.duke.command.ListPuModulesCommand;
import seedu.duke.command.ListFoundNusModsCommand;
import seedu.duke.command.ListMappableNusModsCommand;
import seedu.duke.command.ViewBudgetCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidPuException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.command.ListCurrentPuCommand;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Parser has a Singleton Design Pattern
 */
public class Parser {
    private static UI ui = UI.getUiOneInstance();
    private static Parser instance = null;

    private Parser() {
    }

    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    /**
     * Parses user input and returns the appropriate Command object based on the user input.
     *
     * @param userInput The command input provided by the user in the form of a string.
     * @param universities An ArrayList of University containing all the Partner Universities.
     * @param modules An ArrayList of Module containing the saved current list of modules.
     * @param puModules An ArrayList of Module containing all the modules of the Partner Universities.
     * @param moduleStorage A ModuleStorage object to store and manage current module list-related information
     * @param deadlineStorage A DeadlineStorage object to store and manage deadline-related information.
     * @param budgetPlanner  A BudgetPlanner object to store and manage budget-related information.
     * @param deadlines An ArrayList of Deadline containing the added/saved deadlines.
     * @return
     */
    public Command parseUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                    ArrayList<Module> puModules, ModuleStorage moduleStorage,
                                    DeadlineStorage deadlineStorage, BudgetPlanner budgetPlanner,
                                    ArrayList<Deadline> deadlines) {
        ArrayList<String> userInputWords = parseCommand(userInput.trim());
        String userCommandFirstKeyword = userInputWords.get(0);
        String userCommandSecondKeyword = "";
        if (userInputWords.size() > 1) {
            userCommandSecondKeyword = userInputWords.get(1);
        }
        String inputIgnoringCase = userCommandFirstKeyword.toLowerCase();
        try {
            switch (inputIgnoringCase) {
            case "/list":
                return prepareListCommands(userInputWords, universities, modules);
            case "/search":
                userInput3WordsException(userInputWords);
                assert userInputWords.size() > 1 : "No Nus Module Code Read";
                return prepareSearchByNusModCode(userCommandSecondKeyword, puModules, universities);
            case "/exit":
                userInput2WordsException(userInputWords);
                return new ExitCommand();
            case "/add":
                userInput3WordsException(userInputWords);
                return prepareAddModuleCommand(moduleStorage, userCommandSecondKeyword, puModules, universities);
            case "/remove":
                userInput3WordsException(userInputWords);
                return prepareRemoveModuleCommand(moduleStorage, userCommandSecondKeyword, universities);
            case "/help":
                userInput2WordsException(userInputWords);
                return new HelpCommand();
            case "/budget":
                return prepareBudgetCommand(userInput, budgetPlanner);
            case "/deadline/list":
                userInput2WordsException(userInputWords);
                return new ListDeadlinesCommand(deadlines);
            case "/deadline/add":
                return prepareAddDeadlineCommand(deadlineStorage, userInputWords);
            case "/deadline/remove":
                userInput3WordsException(userInputWords);
                int indexDeadlineToRemove = stringToInt(userCommandSecondKeyword);
                return new DeleteDeadlineCommand(deadlineStorage, indexDeadlineToRemove, deadlines);
            default:
                throw new InvalidCommandException(ui.getCommandInputError());
            }
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    /**
     * Throws an InvalidCommandException when the user input is more than 3 words
     * @param userInputWords ArrayList of string containing the words of user input
     * @throws InvalidCommandException If the user input does not match any valid list command.
     */
    private void userInput3WordsException(ArrayList<String> userInputWords) throws InvalidCommandException {
        if (userInputWords.size() >= 3) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
    }

    /**
     * Throws an InvalidCommandException when the user input is more than 2 words
     * @param userInputWords ArrayList of string containing the words of user input
     * @throws InvalidCommandException If the user input does not match any valid list command.
     */
    private void userInput2WordsException(ArrayList<String> userInputWords) throws InvalidCommandException {
        if (userInputWords.size() >= 2) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
    }

    /**
     * Replace all the extra white spaces with a single white space and tokenizes the user input into ArrayList of
     * strings with a max size of 3.
     * @param userInput The String of user input
     * @return commandWords The ArrayList of String containing the command words inputted by the user
     */
    private static ArrayList<String> parseCommand(String userInput) {
        ArrayList<String> commandWords = new ArrayList<String>();
        String commandInput = userInput.replaceAll("\\s+", " ");
        String[] input = commandInput.split(" ", 3);
        for (String s : input) {
            if (!s.matches("\\s+")) {
                commandWords.add(s);
            }
        }
        return commandWords;
    }

    /**
     * Prepare to call the handleListCommands() if size of userInputWords is more than 1, otherwise it will throw
     * InvalidCommandException.
     * @param userInputWords ArrayList of string containing the command words of user input
     * @param universities An ArrayList of University containing all the Partner Universities.
     * @param modules An ArrayList of Module containing the saved current list of modules.
     * @return handleListCommands()
     * @throws InvalidCommandException If the user input does not match any valid list command.
     */
    private Command prepareListCommands(ArrayList<String> userInputWords, ArrayList<University> universities,
                                        ArrayList<Module> modules) {
        try {
            if (userInputWords.size() == 1) {
                throw new InvalidCommandException(ui.getCommandInputError());
            } else {
                String userCommandSecondKeyword = userInputWords.get(1);
                return handleListCommands(userInputWords, userCommandSecondKeyword, universities, modules);
            }
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    /**
     * Reads in the second command keyword and instantiates its respective Commands: ListPuCommand() or
     * ListCurrentCommand() or prepareListPuModulesCommand() using the switch-case statements, otherwise it will
     * throw and catch InvalidCommandException.
     * @param userInputWords ArrayList of string containing the command words of user input
     * @param userCommandSecondKeyword The string containing the second keyword
     * @param universities An ArrayList of University containing all the Partner Universities.
     * @param modules An ArrayList of Module containing the saved current list of modules.
     * @return ListPuCommand() or ListCurrentCommand() or prepareListPuModulesCommand()
     * @throws InvalidCommandException If the user input does not match any valid list command.
     */
    private Command handleListCommands(ArrayList<String> userInputWords, String userCommandSecondKeyword,
                                       ArrayList<University> universities, ArrayList<Module> modules) {
        String userCommandIgnoreCase = userCommandSecondKeyword.toLowerCase();
        try {
            switch (userCommandIgnoreCase) {
            case "pu":
                if (userInputWords.size() >= 3) {
                    throw new InvalidCommandException(ui.getCommandInputError());
                }
                return new ListPuCommand();
            case "current":
                if (userInputWords.size() == 3) {
                    String userCommandThirdKeyword = userInputWords.get(2);
                    return prepareListCurrentPUModulesCommand(userCommandThirdKeyword, universities, modules);
                }
                return new ListCurrentCommand(modules);
            default:
                return prepareListPuModulesCommand(userInputWords, userCommandSecondKeyword, universities);
            }
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    /**
     * Prepares the search command with creating a new ArrayList of Module foundModulesToPrint and a string
     * searchModCode before calling handleSearchByNusModCode().
     * @param nusModCode String containing the specific Nus Module Code to search by inputted by user.
     * @param allModules An ArrayList of Module containing all the modules of the Partner Universities.
     * @param universities ArrayList of University containing all the Partner Universities details.
     * @return handleSearchByNusModCode()
     */
    private Command prepareSearchByNusModCode(String nusModCode, ArrayList<Module> allModules,
                                              ArrayList<University> universities) {
        String searchModCode = nusModCode;
        ArrayList<Module> foundModulesToPrint = new ArrayList<>();
        try {
            return handleSearchByNusModCode(foundModulesToPrint, searchModCode, allModules, universities);
        } catch (InvalidModuleException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    /**
     * Reads in the String searchModCode and instantiates its respective Commands objects: ListMappableNusModsCommand()
     * or ListFoundNusModsCommand().
     * @param foundModulesToPrint Arraylist of Module containing all the mappable Partner Universities modules.
     * @param searchModCode String containing the specific Nus Module Code to search by inputted by user.
     * @param allModules An ArrayList of Module containing all the modules of the Partner Universities.
     * @param universities ArrayList of University containing all the Partner Universities details.
     * @return ListMappableNusModsCommand() or ListFoundNusModsCommand()
     * @throws InvalidModuleException
     */
    private Command handleSearchByNusModCode(ArrayList<Module> foundModulesToPrint, String searchModCode,
                                             ArrayList<Module> allModules, ArrayList<University> universities)
            throws InvalidModuleException {
        if (searchModCode.equalsIgnoreCase("/mods")) {
            return new ListMappableNusModsCommand(allModules);
        } else {
            for (Module module : allModules) {
                String nusModuleCode = module.getNusModuleCode();
                if (nusModuleCode.equalsIgnoreCase(searchModCode)) {
                    foundModulesToPrint.add(module);
                }
            }
            int numOfFoundModules = foundModulesToPrint.size();
            if (numOfFoundModules == 0) {
                throw new InvalidModuleException(ui.getInvalidSearchModuleMessage());
            } else {
                return new ListFoundNusModsCommand(searchModCode, foundModulesToPrint, universities);
            }
        }
    }

    private Command prepareListPuModulesCommand(ArrayList<String> userInputWords, String univAbbNameOrIndex,
                                                ArrayList<University> universities) {
        String filter = "";
        try {
            filter = prepareFilterString(userInputWords, filter);
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
        String universityAbbName = "";
        int univIndex = 0;
        boolean isUnivAbbr = false;
        try {
            univIndex = Integer.parseInt(univAbbNameOrIndex) - 1;
        } catch (NumberFormatException nfe) {
            universityAbbName = univAbbNameOrIndex;
            isUnivAbbr = true;
        }
        try {
            return handleListPuModulesCommand(universities, universityAbbName, univIndex, isUnivAbbr, filter);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    //@@author Briantjs00
    private String prepareFilterString(ArrayList<String> userInputWords, String filter) throws InvalidCommandException {
        if (userInputWords.size() == 3) {
            String lowerCaseUserCommandThirdKeyword = userInputWords.get(2).toLowerCase();
            String[] filterWords = lowerCaseUserCommandThirdKeyword.split(" ", 3);
            if (lowerCaseUserCommandThirdKeyword.startsWith("/filter") && filterWords.length == 3) {
                if (filterWords[1].equalsIgnoreCase("/mc")) {
                    String credits = filterWords[2];
                    checkIsValidModuleCredit(credits);
                    filter = lowerCaseUserCommandThirdKeyword.replaceFirst("/filter", "").trim();
                } else if (filterWords[1].equalsIgnoreCase("/name")) {
                    filter = lowerCaseUserCommandThirdKeyword.replaceFirst("/filter", "").trim();
                } else {
                    throw new InvalidCommandException(ui.getCommandInputError());
                }
            } else {
                throw new InvalidCommandException(ui.getCommandInputError());
            }
        }
        return filter;
    }

    //@@author
    private void checkIsValidModuleCredit(String credits) throws InvalidCommandException {
        if (credits.startsWith("-")) {
            throw new InvalidCommandException(ui.notIntegerError());
        }
        try {
            Integer.parseInt(credits);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(ui.notIntegerError());
        }
    }

    private Command handleListPuModulesCommand(ArrayList<University> universities, String universityAbbName,
                                               int univIndex, boolean isUnivAbbr, String filter)
            throws InvalidPuException {
        String universityName = "";
        int univID = 0;

        // list [Univ Abbr]
        if (isUnivAbbr) {
            for (University university : universities) {
                if (universityAbbName.equalsIgnoreCase(university.getUnivAbbName())) {
                    univID = university.getUnivId();
                    universityName = university.getUnivName();
                }
            }
            if (universityName.equals("")) {
                throw new InvalidPuException(ui.getInvalidPuMessage());
            }
        } else {
            if (univIndex >= universities.size() || univIndex < 0) {
                throw new InvalidPuException(ui.getInvalidPuMessage());
            } else { // list [UnivID]
                univID = universities.get(univIndex).getUnivId();
                universityName = universities.get(univIndex).getUnivName();
            }
        }
        if (filter.equals("")) {
            return new ListPuModulesCommand(univID, universityName);
        } else {
            return new ListPuModulesCommand(univID, universityName, filter);
        }
    }

    /**
     * Returns handleAddModuleCommand. Should an error occur, returns Command object for exceptions instead.
     *
     * @param moduleStorage        moduleStorage Class that holds user added modules.
     * @param abbreviationAndIndex String containing university abbreviation and Index of module to add relative
     *                             to the specific PU module list.
     * @param allModules           ArrayList of Module that contains all the modules for all PUs.
     * @param universities         ArrayList of University that contains all the PUs University object.
     * @return handleAddModuleCommand function. Returns ExceptionHandleCommand if error is caught.
     */
    private Command prepareAddModuleCommand(ModuleStorage moduleStorage, String abbreviationAndIndex,
                                            ArrayList<Module> allModules, ArrayList<University> universities) {
        try {
            return handleAddModuleCommand(moduleStorage, abbreviationAndIndex, allModules, universities);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        } catch (InvalidModuleException e) {
            return new ExceptionHandleCommand(e);
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    // The add comment currently works in the format of PartnerAbb/Index

    /**
     * Handles User input for Add function. Splits the string into two parts and matches PU Abbreviation to a specific
     * PU and finds the Module corresponding to the Index relative to the module list of the PU selected.
     *
     * @param moduleStorage        moduleStorage Class that holds user added modules.
     * @param abbreviationAndIndex String containing university abbreviation and Index of module to add relative
     *                             to the specific PU module list.
     * @param allModules           ArrayList of Module that contains all the modules for all PUs.
     * @param universities         ArrayList of University that contains all the PUs University object.
     * @return Returns AddModuleCommand(moduleToAdd, moduleStorage) to Duke to handle the addition of module into
     *         user selected modules in moduleStorage.
     * @throws InvalidCommandException Thrown when AbbreviationAndIndex does not split into two Strings.
     * @throws InvalidPuException      Thrown when Abbreviation given cannot be matched with any PUs.
     * @throws InvalidModuleException  Thrown when no Module can be found at the inputted Index.
     */
    private Command handleAddModuleCommand(ModuleStorage moduleStorage, String abbreviationAndIndex,
                                           ArrayList<Module> allModules, ArrayList<University> universities)
            throws InvalidCommandException, InvalidPuException, InvalidModuleException {
        String[] stringSplit = abbreviationAndIndex.split("/");
        if (stringSplit.length != 2) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String abbreviation = stringSplit[0];
        String moduleIndexString = stringSplit[1];
        int univID = -1;
        for (int i = 0; i < universities.size(); ++i) {
            if (universities.get(i).getUnivAbbName().equalsIgnoreCase(abbreviation)) {
                univID = universities.get(i).getUnivId();
                break;
            }
        }
        if (univID == -1) {
            throw new InvalidPuException(ui.getInvalidPuMessage());
        }
        int moduleIndex = stringToInt(moduleIndexString);
        int moduleIndexToAddZeroBased = moduleIndex - 1;
        Module moduleToAdd = null;
        int counterUpToIndexToAdd = 0;
        for (int i = 0; i < allModules.size(); i++) {
            Module currentModule = allModules.get(i);
            if (currentModule.getUnivId() == univID) {
                if (counterUpToIndexToAdd == moduleIndexToAddZeroBased) {
                    moduleToAdd = currentModule;
                }
                counterUpToIndexToAdd++;
            }
        }
        if (moduleToAdd == null) {
            throw new InvalidModuleException(ui.getInvalidModuleMessage());
        }
        return new AddModuleCommand(moduleToAdd, moduleStorage);
    }

    /**
     * Returns handleAddDeadlineCommand. Should an error occur, return Command object for exceptions instead.
     *
     * @param deadlineStorage deadlineStorage Class that holds user added deadlines.
     * @param userInputWords  ArrayList of String of user's input.
     * @return handleAddDeadlineCommand function. Returns ExceptionHandleCommand if error is caught.
     */
    private Command prepareAddDeadlineCommand(DeadlineStorage deadlineStorage, ArrayList<String> userInputWords) {
        try {
            return handleAddDeadlineCommand(deadlineStorage, userInputWords);
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    /**
     * Checks if user input is in the correct format and returns Command object for adding a new deadline.
     *
     * @param deadlineStorage deadlineStorage Class that holds user added deadlines.
     * @param userInputWords  ArrayList of String of user's input.
     * @return AddDeadlineCommand object if user input format is correct.
     * @throws InvalidCommandException when user input format is incorrect.
     */
    private Command handleAddDeadlineCommand(DeadlineStorage deadlineStorage, ArrayList<String> userInputWords)
            throws InvalidCommandException {
        // Format of user input is: task /by dueDate
        // Format of dueDate is: dd-MM-yyyy
        if (userInputWords.size() < 2) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String deadlineToAdd = userInputWords.get(1) + " " + userInputWords.get(2);
        String[] stringSplit = deadlineToAdd.split(" /(?i)by");
        if (stringSplit.length != 2 || stringSplit[0].isBlank()) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String task = stringSplit[0].trim();
        String dueDate = stringSplit[1].trim();
        String[] dueDateFormat = dueDate.split("-");
        if (dueDateFormat.length != 3) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String dueDateDate = dueDateFormat[0];
        String dueDateMonth = dueDateFormat[1];
        String dueDateYear = dueDateFormat[2];
        checkIsValidDate(dueDateDate, dueDateMonth, dueDateYear);
        Deadline deadlineTypeToAdd = new Deadline(task, dueDate);
        return new AddDeadlineCommand(deadlineTypeToAdd, deadlineStorage);
    }

    /**
     * Checks if the due date given is a valid date in terms of Date, Month and Year. Exception is thrown
     * should an error occur.
     *
     * @param dueDateDate  Date to be checked for validity.
     * @param dueDateMonth Month to be checked for validity.
     * @param dueDateYear  Year to be checked for validity.
     * @throws InvalidCommandException when either the date, month or year is invalid.
     */
    private void checkIsValidDate(String dueDateDate, String dueDateMonth, String dueDateYear) throws
            InvalidCommandException {
        if (dueDateDate.length() != 2 || dueDateMonth.length() != 2 || dueDateYear.length() != 4) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        if (stringToInt(dueDateMonth) > 12) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        try {
            LocalDate.of(stringToInt(dueDateYear), stringToInt(dueDateMonth), stringToInt(dueDateDate));
        } catch (DateTimeException e) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
    }

    //@@author
    private Command prepareBudgetCommand(String userInput, BudgetPlanner budgetPlanner) throws InvalidCommandException {
        userInput = removeFirstCommandWord(userInput);
        if (userInput.trim().equalsIgnoreCase("/view")) {
            return new ViewBudgetCommand(budgetPlanner);
        }
        if (!userInput.startsWith("/")) {
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
        userInput = userInput.replaceFirst("/", "");
        String[] commandWords = userInput.split((" "), 2);
        trimStringArray(commandWords);
        if (commandWords.length != 2) {
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
        String budgetCommand = commandWords[0].toLowerCase();
        String stringAmount = commandWords[1];
        checkBudgetKeyword(budgetCommand);
        checkBudgetValidAmount(stringAmount);
        return budgetCommandType(budgetPlanner, budgetCommand, stringAmount);
    }

    private String removeFirstCommandWord(String userInput) throws InvalidCommandException {
        if (userInput.startsWith("/BUDGET")) {
            userInput = userInput.replaceFirst("/BUDGET", "").trim();
        } else if (userInput.startsWith("/budget")) {
            userInput = userInput.replaceFirst("/budget", "").trim();
        } else {
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
        return userInput;
    }

    private void checkBudgetKeyword(String budgetCommand) throws InvalidCommandException {
        if (!budgetCommand.equals("budget") && !budgetCommand.equals("accommodation") &&
                !budgetCommand.equals("airplane") && !budgetCommand.equals("food") &&
                !budgetCommand.equals("entertainment")) {
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
    }

    private void checkBudgetValidAmount(String stringAmount) throws InvalidCommandException {
        if (stringAmount.startsWith("-") || stringAmount.length() > 10) {
            throw new InvalidCommandException(UI.INVALID_BUDGET_AMOUNT_MESSAGE);
        }
        try {
            Long longAmount = Long.parseLong(stringAmount);
            if (longAmount > BudgetPlanner.MAX_BUDGET) {
                throw new InvalidCommandException(UI.INVALID_BUDGET_AMOUNT_MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(UI.INVALID_BUDGET_AMOUNT_MESSAGE);
        }
    }

    private BudgetCommand budgetCommandType(BudgetPlanner budgetPlanner, String budgetCommand, String stringAmount)
            throws InvalidCommandException {
        int amount = stringToInt(stringAmount);
        switch (budgetCommand) {
        case "budget":
            return new EditBudgetCommand(amount, budgetPlanner);
        case "accommodation":
            return new EditAccommodationCommand(amount, budgetPlanner);
        case "airplane":
            return new EditAirplaneTicketCommand(amount, budgetPlanner);
        case "food":
            return new EditFoodCommand(amount, budgetPlanner);
        case "entertainment":
            return new EditEntertainmentCommand(amount, budgetPlanner);
        default:
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
    }

    private void trimStringArray(String[] commandWords) {
        for (int i = 0; i < commandWords.length; ++i) {
            commandWords[i] = commandWords[i].trim();
        }
    }

    /**
     * Prepares to handle the List Current Pu Modules Commands.
     * Serves to catch Invalid Pu Exception.
     *
     * @param univAbbName String input by user of Partner University Name in Abbreviation.
     * @param universities ArrayList of all the Partner Universities as University objects.
     * @param modules ArrayList of all modules user has added to his/her list.
     * @return An object of command class by calling handleListCurrentPuModulesCommand.
     */
    private Command prepareListCurrentPUModulesCommand(String univAbbName, ArrayList<University> universities,
                                                       ArrayList<Module> modules) {
        try {
            return handleListCurrentPuModulesCommand(universities, univAbbName, modules);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    /**
     * Checks if universityAbbName inputted by user is a valid Abbreviation. If yes, returns object of class
     * ListCurrentPuCommand, otherwise throw an Invalid Pu Exception.
     *
     * @param universities ArrayList of all the Partner Universities as University objects.
     * @param universityAbbName String input by user of Partner University Name in Abbreviation.
     * @param modules ArrayList of all modules user has added to his/her list.
     * @return An object of command class, ListCurrentPuCommand class.
     * @throws InvalidPuException Thrown if universityAbbName is unable to be met to any of the Partner Universities.
     */
    private Command handleListCurrentPuModulesCommand(ArrayList<University> universities, String universityAbbName,
                                                      ArrayList<Module> modules)
            throws InvalidPuException {
        int univID = 0;
        for (University university : universities) {
            if (universityAbbName.equalsIgnoreCase(university.getUnivAbbName())) {
                univID = university.getUnivId();
            }
        }
        if (univID == 0) {
            throw new InvalidPuException(ui.getInvalidPuMessage());
        }
        return new ListCurrentPuCommand(modules, univID);
    }

    /**
     * Handles User input for Remove function.
     * Splits the string into two parts and matches PU Abbreviation to a specific PU and finds the Module corresponding
     * to the Index inputted, relative to the user's module list of the PU selected, to delete.
     *
     * @param moduleStorage  moduleStorage Class that holds user added modules.
     * @param abbreviationAndIndex String containing university abbreviation and Index of module to remove relative
     *                             to the specific PU module list.
     * @param universities  ArrayList of University that contains all the PUs University object.
     * @return Returns DeleteModuleCommand to Duke to handle the removal of module from user selected modules
     *         in moduleStorage.
     * @throws InvalidCommandException Thrown when AbbreviationAndIndex does not split into two Strings.
     * @throws InvalidPuException Thrown when Abbreviation given cannot be matched with any PUs.
     */
    private Command handleRemoveModuleCommand(ModuleStorage moduleStorage, String abbreviationAndIndex,
                                              ArrayList<University> universities)
            throws InvalidCommandException, InvalidPuException {
        String[] stringSplit = abbreviationAndIndex.split("/");
        if (stringSplit.length != 2) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String abbreviation = stringSplit[0];
        String indexToDeleteString = stringSplit[1];
        int indexToDelete = stringToInt(indexToDeleteString);

        int univID = -1;
        for (int i = 0; i < universities.size(); ++i) {
            if (universities.get(i).getUnivAbbName().equalsIgnoreCase(abbreviation)) {
                univID = universities.get(i).getUnivId();
                break;
            }
        }
        if (univID == -1) {
            throw new InvalidPuException(ui.getInvalidPuMessage());
        }
        return new DeleteModuleCommand(moduleStorage, indexToDelete, univID);
    }

    /**
     *  Prepares to handle the List Current Pu Modules Commands.
     *  Serves to catch Invalid Pu Exception, and Invalid Command Exception.
     *
     * @param moduleStorage moduleStorage Class that holds user added modules.
     * @param abbreviationAndIndex String containing university abbreviation and Index of module to remove relative
     *                             to the specific PU module list.
     * @param universities ArrayList of University that contains all the PUs University object.
     * @return An object of command class by calling handleRemoveModuleCommand
     */
    private Command prepareRemoveModuleCommand(ModuleStorage moduleStorage, String abbreviationAndIndex,
                                               ArrayList<University> universities) {
        try {
            return handleRemoveModuleCommand(moduleStorage, abbreviationAndIndex, universities);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    /**
     * Converts given string to int type.
     *
     * @param stringToConvert String to be converted
     * @return The number in int type
     */
    private int stringToInt(String stringToConvert) {
        int intConverted = -1;
        try {
            intConverted = Integer.parseInt(stringToConvert);
        } catch (NumberFormatException e) {
            ui.printInputNotNumMessage();
        }
        return intConverted;
    }
}

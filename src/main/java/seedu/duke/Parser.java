package seedu.duke;

import seedu.duke.budget.BudgetPlanner;
import seedu.duke.command.AddDeadlineCommand;
import seedu.duke.command.AddModuleCommand;
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
import seedu.duke.command.ViewBudgetCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidPuException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.command.ListCurrentPuCommand;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {
    private static UI ui = new UI();

    public Command parseUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                    ArrayList<Module> puModules, Storage storage, DeadlineStorage deadlineStorage,
                                    BudgetPlanner budgetPlanner, ArrayList<Deadline> deadlines) {

        ArrayList<String> userInputWords = parseCommand(userInput.trim());
        String userCommandFirstKeyword = userInputWords.get(0);
        String userCommandSecondKeyword = "";
        String userCommandThirdKeyword = "";
        if (userInputWords.size() > 1) {
            userCommandSecondKeyword = userInputWords.get(1);
        }
        if (userInputWords.size() > 2) {
            userCommandThirdKeyword = userInputWords.get(2);
        }
        String inputIgnoringCase = userCommandFirstKeyword.toLowerCase();
        try {
            switch (inputIgnoringCase) {
            case "list":
                return prepareListCommands(userInputWords, universities, modules);
            case "search":
                assert userInputWords.size() > 1 : "No Nus Module Code Read";
                return prepareSearchByNusModCode(userCommandSecondKeyword, puModules, universities);
            case "exit":
                return new ExitCommand();
            case "add":
                return prepareAddModuleCommand(storage, userCommandSecondKeyword, puModules, universities);
            case "remove":
                return prepareRemoveModuleCommand(storage, userCommandSecondKeyword, universities);
            case "/help":
                return new HelpCommand();
            case "/budget":
                return prepareBudgetCommand(userInput, budgetPlanner);
            case "/deadline/list":
                return new ListDeadlinesCommand(deadlines);
            case "/deadline/add":
                return prepareAddDeadlineCommand(deadlineStorage, userInputWords);
            case "/deadline/remove":
                int indexDeadlineToRemove = stringToInt(userCommandSecondKeyword);
                return new DeleteDeadlineCommand(deadlineStorage, indexDeadlineToRemove, deadlines);
            default:
                throw new InvalidCommandException(ui.getCommandInputError());
            }
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    public static ArrayList<String> parseCommand(String userInput) {
        String[] input = userInput.split((" "), 3);
        ArrayList<String> commandWords = new ArrayList<>();
        String commandInput = input[0];
        commandWords.add(commandInput);
        if (input.length == 2) {
            String commandSpecificFirstWord = input[1];
            commandWords.add(commandSpecificFirstWord);
        } else if (input.length == 3) {
            String commandSpecificFirstWord = input[1];
            commandWords.add(commandSpecificFirstWord);
            String commandSpecificsSecondWord = input[2];
            commandWords.add(commandSpecificsSecondWord);
        }
        return commandWords;
    }

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

    private Command handleListCommands(ArrayList<String> userInputWords, String userCommandSecondKeyword,
                                       ArrayList<University> universities, ArrayList<Module> modules) {
        String userCommandIgnoreCase = userCommandSecondKeyword.toLowerCase();
        switch (userCommandIgnoreCase) {
        case "pu":
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
    }

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

    private Command handleSearchByNusModCode(ArrayList<Module> foundModulesToPrint, String searchModCode,
                                             ArrayList<Module> allModules, ArrayList<University> universities)
            throws InvalidModuleException {
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

    private Command prepareListPuModulesCommand(ArrayList<String> userInputWords, String univAbbNameOrIndex,
                                                ArrayList<University> universities) {
        String filter = "";
        if (userInputWords.size() == 3) {
            String userCommandThirdKeyword = userInputWords.get(2);
            if (userCommandThirdKeyword.startsWith("/filter") && userCommandThirdKeyword.length() > 8) {
                filter = userCommandThirdKeyword.substring(8);
            }
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

    private Command handleListPuModulesCommand(ArrayList<University> universities, String universityAbbName,
                                               int univIndex, boolean isUnivAbbr, String filter)
            throws InvalidPuException {
        String universityName = "";
        int univID = 0;
        if (isUnivAbbr) { // list [Univ Abbr]
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

    private Command prepareAddModuleCommand(Storage storage, String abbreviationAndCode, ArrayList<Module> allModules,
                                            ArrayList<University> universities) {
        try {
            return handleAddModuleCommand(storage, abbreviationAndCode, allModules, universities);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        } catch (InvalidModuleException e) {
            return new ExceptionHandleCommand(e);
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    // The add comment currently works in the format of PartnerAbb/ModuleCode
    private Command handleAddModuleCommand(Storage storage, String abbreviationAndCode, ArrayList<Module> allModules,
                                           ArrayList<University> universities)
            throws InvalidCommandException, InvalidPuException, InvalidModuleException {
        String[] stringSplit = abbreviationAndCode.split("/");
        if (stringSplit.length != 2) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String abbreviation = stringSplit[0];
        String moduleCode = stringSplit[1];
        Module moduleToAdd = null;
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
        for (int i = 0; i < allModules.size(); i++) {
            Module currentModule = allModules.get(i);
            if (currentModule.getUnivId() == univID && currentModule.getModuleCode().equalsIgnoreCase(moduleCode)) {
                moduleToAdd = currentModule;
            }
        }
        if (moduleToAdd == null) {
            throw new InvalidModuleException(ui.getInvalidModuleMessage());
        }
        return new AddModuleCommand(moduleToAdd, storage);
    }

    private Command prepareAddDeadlineCommand(DeadlineStorage deadlineStorage, ArrayList<String> userInputWords) {
        try {
            return handleAddDeadlineCommand(deadlineStorage, userInputWords);
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    // Format of user input is: task /by dueDate
    // Format of dueDate is: dd-MM-yyyy
    private Command handleAddDeadlineCommand(DeadlineStorage deadlineStorage, ArrayList<String> userInputWords)
            throws InvalidCommandException {
        if (userInputWords.size() < 2) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String deadlineToAdd = userInputWords.get(1) + " " + userInputWords.get(2);
        String[] stringSplit = deadlineToAdd.split(" /by");
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
        Deadline deadlineTypeToAdd = new Deadline(task, dueDate);
        return new AddDeadlineCommand(deadlineTypeToAdd, deadlineStorage);
    }

    //2147483647
    private Command prepareBudgetCommand(String userInput, BudgetPlanner budgetPlanner) throws InvalidCommandException {
        userInput = userInput.replaceFirst("/budget", "").trim();
        String[] commandWords = userInput.split(("/"), 3);
        trimStringArray(commandWords);
        if (userInput.trim().equalsIgnoreCase("/view")) {
            return new ViewBudgetCommand(budgetPlanner);
        }
        if (commandWords.length != 3) {
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
        String budgetCommand = commandWords[1].toLowerCase();
        String stringAmount = commandWords[2];
        if (!budgetCommand.equals("budget") && !budgetCommand.equals("accommodation") &&
                !budgetCommand.equals("airplane") && !budgetCommand.equals("food") &&
                !budgetCommand.equals("entertainment")) {
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
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

    private Command prepareListCurrentPUModulesCommand(String univAbbNameOrIndex, ArrayList<University> universities,
                                                       ArrayList<Module> modules) {
        char digitChecker = univAbbNameOrIndex.charAt(0);
        String universityAbbName = "";
        int univIndex = -1;
        // remember handle exception for numberformatexception use stringtoint instead?
        if (Character.isDigit(digitChecker)) {
            univIndex = Integer.parseInt(univAbbNameOrIndex) - 1;
        } else {
            universityAbbName = univAbbNameOrIndex;
        }
        try {
            return handleListCurrentPuModulesCommand(universities, universityAbbName, univIndex, modules);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    private Command handleListCurrentPuModulesCommand(ArrayList<University> universities, String universityAbbName,
                                                      int univIndex, ArrayList<Module> modules)
            throws InvalidPuException {
        int univID = 0;
        if (univIndex == -1) {
            for (University university : universities) {
                if (universityAbbName.equalsIgnoreCase(university.getUnivAbbName())) {
                    univID = university.getUnivId();
                }
            }
        }
        if (univID == 0) {
            throw new InvalidPuException(ui.getInvalidPuMessage());
        }
        return new ListCurrentPuCommand(modules, univID);
    }

    private Command handleRemoveModuleCommand(Storage storage, String abbreviationAndIndex,
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
        return new DeleteModuleCommand(storage, indexToDelete, univID);
    }

    private Command prepareRemoveModuleCommand(Storage storage, String abbreviationAndIndex,
                                               ArrayList<University> universities) {
        try {
            return handleRemoveModuleCommand(storage, abbreviationAndIndex, universities);
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

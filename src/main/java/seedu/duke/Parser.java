package seedu.duke;

import seedu.duke.budget.BudgetPlanner;
import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
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
import seedu.duke.command.ListPuCommand;
import seedu.duke.command.ListPuModulesCommand;
import seedu.duke.command.ViewBudgetCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidPuException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.command.ListCurrentPuCommand;

import java.util.ArrayList;

public class Parser {
    private static UI ui = new UI();
    private static final int SIZE_OF_ONE = 1;
    private static final int SIZE_OF_TWO = 2;
    private static final int SIZE_OF_THREE = 3;
    private static final int INDEX_ZERO = 0;

    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    public Command parseUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                    ArrayList<Module> puModules, Storage storage, BudgetPlanner budgetPlanner) {

        ArrayList<String> userInputWords = parseCommand(userInput);
        String userCommandFirstKeyword = userInputWords.get(INDEX_ZERO);
        String userCommandSecondKeyword = "";
        String userCommandThirdKeyword = "";
        if (userInputWords.size() > SIZE_OF_ONE) {
            userCommandSecondKeyword = userInputWords.get(INDEX_ONE);
        }
        String inputIgnoringCase = userCommandFirstKeyword.toLowerCase();
        try {
            switch (inputIgnoringCase) {
            case "list":
                if (userInputWords.size() == 1) {
                    throw new InvalidCommandException(ui.getCommandInputError());
                }
                switch (userCommandSecondKeyword.toLowerCase()) {
                case "pu":
                    return new ListPuCommand();
                case "current":
                    if (userInputWords.size() == SIZE_OF_TWO) {
                        return new ListCurrentCommand(modules);
                    }
                    else if (userInputWords.size() == SIZE_OF_THREE) {
                        userCommandThirdKeyword = userInputWords.get(INDEX_TWO);
                        return prepareListCurrentPUModulesCommand(userCommandThirdKeyword, universities, modules);
                    }
                }
                if (userCommandSecondKeyword.equalsIgnoreCase("pu")) {
                    return new ListPuCommand();
                } else if (userCommandSecondKeyword.equalsIgnoreCase("current")) {
                    return new ListCurrentCommand(modules);
                } else {  // list PU name case
                    return prepareListPuModulesCommand(userCommandSecondKeyword, universities);
                }
            case "exit":
                return new ExitCommand();
            case "add":
                return prepareAddModuleCommand(storage, userCommandSecondKeyword, puModules, universities);
            case "remove":
                int indexToRemove = stringToInt(userCommandSecondKeyword);
                return new DeleteModuleCommand(storage, indexToRemove, modules);
            case "/help":
                return new HelpCommand();
            case "/budget":
                return prepareBudgetCommand(userCommandSecondKeyword,budgetPlanner);
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

    private Command prepareListPuModulesCommand(String univAbbNameOrIndex, ArrayList<University> universities) {
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
            return handleListPuModulesCommand(universities, universityAbbName, univIndex);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    private Command handleListPuModulesCommand(ArrayList<University> universities,
                                               String universityAbbName, int univIndex) throws InvalidPuException {
        String universityName = "";
        int univID = 0;
        if (univIndex == -1) {
            for (University university : universities) {
                if (universityAbbName.equalsIgnoreCase(university.getUnivAbbName())) {
                    univID = university.getUnivId();
                    universityName = university.getUnivName();
                }
            }
        } else {
            univID = universities.get(univIndex).getUnivId();
            universityName = universities.get(univIndex).getUnivName();
        }
        if (univID == 0 && universityName.equals("")) {
            throw new InvalidPuException(ui.getInvalidPuMessage());
        }
        return new ListPuModulesCommand(univID, universityName);
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

    // /budget accommodation 200
    // /budget budget 5000
    private Command prepareBudgetCommand(String userInput, BudgetPlanner budgetPlanner) throws InvalidCommandException {
        String[] commandWords = userInput.split((" "), 2);
        if (userInput.trim().equalsIgnoreCase("view")) {
            return new ViewBudgetCommand(budgetPlanner);
        }
        if (commandWords.length != 2) {
            throw new InvalidCommandException(ui.getInvalidBudgetMessage());
        }
        String budgetCommand = commandWords[0].toLowerCase();
        int amount = stringToInt(commandWords[1]);
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
}

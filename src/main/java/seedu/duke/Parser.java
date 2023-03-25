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
import seedu.duke.command.ViewBudgetCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidPuException;
import seedu.duke.exceptions.InvalidModuleException;

import java.util.ArrayList;

public class Parser {
    private static UI ui = new UI();

    public Command parseUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                    ArrayList<Module> puModules, Storage storage, BudgetPlanner budgetPlanner,
                                    ArrayList<Deadline> deadlines) {

        ArrayList<String> userInputWords = parseCommand(userInput);
        String userCommandFirstKeyword = userInputWords.get(0);
        String userCommandSecondKeyword = "";
        if (userInputWords.size() > 1) {
            userCommandSecondKeyword = userInputWords.get(1);
        }
        String inputIgnoringCase = userCommandFirstKeyword.toLowerCase();
        try {
            switch (inputIgnoringCase) {
            case "list":
                if (userInputWords.size() == 1) {
                    throw new InvalidCommandException(ui.getCommandInputError());
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
                int indexModToRemove = stringToInt(userCommandSecondKeyword);
                return new DeleteModuleCommand(storage, indexModToRemove, modules);
            case "/help":
                return new HelpCommand();
            case "/budget":
                return prepareBudgetCommand(userCommandSecondKeyword,budgetPlanner);
            case "/deadline/list":
                return new ListDeadlinesCommand(deadlines);
            case "/deadline/add":
                return prepareAddDeadlineCommand(storage, userCommandSecondKeyword);
            case "/deadline/remove":
                int indexDeadlineToRemove = stringToInt(userCommandSecondKeyword);
                return new DeleteDeadlineCommand(storage, indexDeadlineToRemove, deadlines);
            default:
                throw new InvalidCommandException(ui.getCommandInputError());
            }
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

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

    private Command prepareAddDeadlineCommand(Storage storage, String deadlineToAdd) {
        try {
            return handleAddDeadlineCommand(storage, deadlineToAdd);
        } catch (InvalidCommandException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    // Format of user input is: task /by dueDate
    // Format of dueDate is: dd-MM-yyyy
    private Command handleAddDeadlineCommand(Storage storage, String deadlineToAdd)
            throws InvalidCommandException {
        String[] stringSplit = deadlineToAdd.split(" /by");
        if (stringSplit.length != 2) {
            throw new InvalidCommandException(ui.getCommandInputError());
        }
        String task = stringSplit[0];
        String dueDate = stringSplit[1];
        Deadline deadlineTypeToAdd = new Deadline(task, dueDate);
        return new AddDeadlineCommand(deadlineTypeToAdd, storage);
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
}

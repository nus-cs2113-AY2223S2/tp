package seedu.duke;

import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.ExceptionHandleCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.ListCurrentCommand;
import seedu.duke.command.ListPuCommand;
import seedu.duke.command.ListPuModulesCommand;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidPuException;
import seedu.duke.exceptions.InvalidModuleException;

import java.util.ArrayList;

public class Parser {
    private static UI ui = new UI();

    public Command parseUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                    ArrayList<Module> puModules, Storage storage) {
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
                int indexToRemove = stringToInt(userCommandSecondKeyword);
                return new DeleteModuleCommand(storage, indexToRemove, modules);
            case "/help":
                return new HelpCommand();
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

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

import seedu.duke.exceptions.InvalidPuException;
import seedu.duke.exceptions.InvalidModuleException;

import java.util.ArrayList;

public class Parser {
    private static UI ui = new UI();

    public Command handleUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                     ArrayList<Module> puModules, Storage storage) {
        ArrayList<String> userInputWords = parseCommand(userInput);
        String userCommandFirstKeyword = userInputWords.get(0);
        String userCommandSecondKeyword = "";
        if (userInputWords.size() > 1) {
            userCommandSecondKeyword = userInputWords.get(1);
        }
        switch (userCommandFirstKeyword) {
        case "list":
            if (userCommandSecondKeyword.equalsIgnoreCase("pu")) {
                return new ListPuCommand();
            } else if (userCommandSecondKeyword.equalsIgnoreCase("current")) {
                return new ListCurrentCommand(modules);
            } else {  // list PU name case
                return prepareListPuModulesCommand(userInput, universities);
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
            return new InvalidCommand();
        }
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

    private Command prepareListPuModulesCommand(String userInput, ArrayList<University> universities) {
        int indexOfFirstSpace = userInput.indexOf(' ');
        int indexOfFirstLetterOfPu = indexOfFirstSpace + 1;
        String universityAbbName = userInput.substring(indexOfFirstLetterOfPu);
        try {
            return handleListPuModulesCommand(universities, universityAbbName);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    // Todo: Right now, it uses university Name only but since university object has 3 attributes:
    // todo: handle exceptions such that the universityname inputted is incorrect
    // 1. univId; 2. univName; 3. univAbbName; we can use this next time
    // Note that this function, takes in the arrayList of modules of ALL MODULES
    // THIS IS NOT THE FUNCTION THAT RETURNS USER SELECTED MODULES SPECIFIED TO A PU.
    private Command handleListPuModulesCommand(ArrayList<University> universities,
                                               String universityAbbName) throws InvalidPuException {
        int univId = -1;
        String universityName = "";
        for (int i = 0; i < universities.size(); i++) {
            University currentUniversity = universities.get(i);
            String currentUniversityAbbName = currentUniversity.getUnivAbbName();
            if (universityAbbName.equals(currentUniversityAbbName)) {
                univId = currentUniversity.getUnivId(); //Todo: change magic literal
                universityName = currentUniversity.getUnivName(); // Todo: might be empty string
            }
        }
        if (univId == -1) {
            throw new InvalidPuException(ui.getInvalidPuMessage());
        }
        return new ListPuModulesCommand(univId, universityName);
    }

    private Command prepareAddModuleCommand(Storage storage, String abbreviationAndCode, ArrayList<Module> allModules,
                                            ArrayList<University> universities) {
        try {
            return handleAddModuleCommand(storage, abbreviationAndCode, allModules, universities);
        } catch (InvalidPuException e) {
            return new ExceptionHandleCommand(e);
        } catch (InvalidModuleException e) {
            return new ExceptionHandleCommand(e);
        }
    }

    // The add comment currently works in the format of PartnerAbb/ModuleCode
    private Command handleAddModuleCommand(Storage storage, String abbreviationAndCode, ArrayList<Module> allModules,
                                           ArrayList<University> universities)
            throws InvalidPuException, InvalidModuleException {
        String[] stringSplit = abbreviationAndCode.split("/");
        if (stringSplit.length != 2) {
            return new InvalidCommand();
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

package seedu.duke;

import seedu.duke.command.AddModuleCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteModuleCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.ListCurrentCommand;
import seedu.duke.command.ListPuCommand;
import seedu.duke.command.ListPuModulesCommand;
import seedu.duke.command.HelpCommand;

import java.util.ArrayList;
import java.io.IOException;

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
                // Change the below code index stuff to be in the handleListPuModules, use SLAP
                int indexOfFirstSpace = userInput.indexOf(' ');
                String universityAbbName = userInput.substring(indexOfFirstSpace + 1);
                return handleListPuModulesCommand(universities, universityAbbName);
            }
        case "exit":
            return new ExitCommand();
        case "add":
            return handleAddModuleCommand(storage, userCommandSecondKeyword, puModules, universities);
        case "remove":
            // Handle exception for parseInt, use try catch, shift it to another Function thanks
            int indexToRemove = Integer.parseInt(userCommandSecondKeyword);
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

    /**
     * Deletes the module corresponding to the uni specified by user. Module will the removed from user's
     * saved list of modules.
     *
     * @param indexToDelete Index of that module that is given in user input.
     * @param uniModuleList The corresponding ArrayList of that specified uni.
     * @param database      Database of the user's saved list of modules.
     */
    public static boolean handleDeleteModule(int indexToDelete, ArrayList<Module> uniModuleList,
                                       Storage database) {
        int indexToZeroBased = indexToDelete - 1;
        try {
            uniModuleList.remove(indexToZeroBased);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
            return false;
        }
        try {
            database.writeListToFile(uniModuleList);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
            return false;
        }
        return true;
    }

    // Todo: Right now, it uses university Name only but since university object has 3 attributes:
    // todo: handle exceptions such that the universityname inputted is incorrect
    // 1. univId; 2. univName; 3. univAbbName; we can use this next time
    // Note that this function, takes in the arrayList of modules of ALL MODULES
    // THIS IS NOT THE FUNCTION THAT RETURNS USER SELECTED MODULES SPECIFIED TO A PU.
    private Command handleListPuModulesCommand(ArrayList<University> universities,
                                               String universityAbbName) {
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
        return new ListPuModulesCommand(univId, universityName);
    }

    // The add comment currently works in the format of PartnerAbb/ModuleCode
    private Command handleAddModuleCommand(Storage storage, String abbreviationAndCode, ArrayList<Module> allModules,
                                           ArrayList<University> universities) {
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
            return new InvalidCommand();
        }
        for (int i = 0; i < allModules.size(); i++) {
            Module currentModule = allModules.get(i);
            if (currentModule.getUnivId() == univID && currentModule.getModuleCode().equalsIgnoreCase(moduleCode)) {
                moduleToAdd = currentModule;
            }
        }
        if (moduleToAdd == null) {
            return new InvalidCommand();
        }
        return new AddModuleCommand(moduleToAdd, storage);
    }
}


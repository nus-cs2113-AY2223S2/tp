package seedu.duke;

import java.util.ArrayList;
import java.io.IOException;

public class Parser {
    private static UI ui = new UI();

    public boolean executeUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                      ArrayList<Module> puModules, Storage storage) {
        ArrayList<String> userInputWords = Parser.parseCommand(userInput);
        String userCommandFirstKeyword = userInputWords.get(0);
        String userCommandSecondKeyword = "";
        if (userInputWords.size() > 1) {
            userCommandSecondKeyword = userInputWords.get(1);
        }
        boolean toContinue = true;
        switch (userCommandFirstKeyword) {
        case "list":
            if (userCommandSecondKeyword.equalsIgnoreCase("pu")) {
                executeListAllPuUniversitiesCommand();
            } else if (userCommandSecondKeyword.equalsIgnoreCase("current")) {
                executeListCurrentModulesCommand(modules);
            } else {  // list PU name case
                int indexOfFirstSpace = userInput.indexOf(' ');
                String universityName = userInput.substring(indexOfFirstSpace + 1);
                executeListPuModulesCommand(puModules, universities, universityName);
            }
            break;
        case "exit":
            toContinue = false;
            executeExitCommand();
            break;
        case "add":
            executeAddModuleCommand(storage, userCommandSecondKeyword, puModules, universities);
            ui.printAddModMessage();
            break;
        case "remove":
            int indexToRemove = Integer.parseInt(userCommandSecondKeyword);
            executeDeleteModuleCommand(storage, indexToRemove, modules);
            ui.printDeleteModMessage();
            break;
        default:
            ui.printInvalidInputMessage();
            break;
        }
        return toContinue;
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
    public static void deleteModule(int indexToDelete, ArrayList<Module> uniModuleList,
                                    Storage database) {
        int indexToZeroBased = indexToDelete - 1;
        try {
            uniModuleList.remove(indexToZeroBased);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        try {
            database.writeListToFile(uniModuleList);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
        }
    }

    private void executeListCurrentModulesCommand(ArrayList<Module> modules) {
        ui.printCurrentModList(modules);
        ui.printCurrentListMessage();
    }

    // Todo: Right now, it uses university Name only but since university object has 3 attributes:
    // todo: handle exceptions such that the universityname inputted is incorrect
    // 1. univId; 2. univName; 3. univAbbName; we can use this next time
    // Note that this function, takes in the arrayList of modules of ALL MODULES
    // THIS IS NOT THE FUNCTION THAT RETURNS USER SELECTED MODULES SPECIFIED TO A PU.
    private void executeListPuModulesCommand(ArrayList<Module> modules, ArrayList<University> universities,
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
        ui.printPUModules(univId);
        ui.printPUModListMessage(universityName);  //Todo: exception this is not found, it is a empty string
    }

    private void executeExitCommand() {
        ui.printExitMessage();
    }

    private void executeListAllPuUniversitiesCommand() {
        ui.printPUList();
        ui.printPUListMessage();
    }

    // The add comment currently searches only the module code
    private void executeAddModuleCommand(Storage storage, String abbreviationAndCode, ArrayList<Module> allModules,
                                         ArrayList<University> universities) {
        String[] stringSplit = abbreviationAndCode.split("/");
        String abbreviation = stringSplit[0];
        String moduleCode = stringSplit[1];
        int univID = 0;
        for (int i = 0; i < universities.size(); ++i) {
            if (universities.get(i).getUnivAbbName().equalsIgnoreCase(abbreviation)) {
                univID = universities.get(i).getUnivId();
                break;
            }
        }
        for (int i = 0; i < allModules.size(); i++) {
            Module currentModule = allModules.get(i);
            if (currentModule.getUnivId() == univID && currentModule.getModuleCode().equalsIgnoreCase(moduleCode)) {
                storage.addModuleToModuleList(currentModule);
            }
        }
    }

    private void executeDeleteModuleCommand(Storage storage, int indexToDelete, ArrayList<Module> modules) {
        Parser.deleteModule(indexToDelete, modules, storage);
    }
}


package seedu.duke;

import java.util.ArrayList;

public class UI {
    private static final String LIST_PU_MESSAGE = "This is the list of PUs:";
    private static final String LIST_CURRENT_MESSAGE = "List of Added modules:";
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_MOD_MESSAGE = "This module has been added to the current list!";
    private static final String DELETE_MOD_MESSAGE = "This module has been deleted from the current list!";
    public UI() {
    }


    // Todo: The userInput now is the whole string, and I am breaking it down into an Array of Strings here
    // Todo: This will be changed when the parser is added.
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
                printPUList();
                executeListAllPuUniversitiesCommand(universities);
            } else if (userCommandSecondKeyword.equalsIgnoreCase("current")) {
                printCurrentList();
                executeListCurrentModulesCommand(modules);
            } else {  // list PU name case
                int indexOfFirstSpace = userInput.indexOf(' ');
                String universityName = userInput.substring(indexOfFirstSpace + 1);
                printPUModList(universityName);
                executeListPuModulesCommand(puModules, universities, universityName);
            }
            break;
        case "exit":
            toContinue = false;
            executeExitCommand();
            break;
        case "add":
            executeAddModuleCommand(storage, userCommandSecondKeyword, puModules, universities);
            printAddMod();
            break;
        case "remove":
            int indexToRemove = Integer.parseInt(userCommandSecondKeyword);
            executeDeleteModuleCommand(storage, indexToRemove, modules);
            printDeleteMod();
            break;
        default:
            System.out.println("Invalid Input");
            break;
        }
        return toContinue;
    }

    private void printPUList() {
        System.out.println(LIST_PU_MESSAGE);
        System.out.println(LINE);
    }

    private void printCurrentList() {
        System.out.println(LIST_CURRENT_MESSAGE);
    }

    private void printPUModList(String univName) {
        System.out.println(univName + " Modules");
        System.out.println(LINE);
    }

    private void printAddMod() {
        System.out.println(ADD_MOD_MESSAGE);
        System.out.println(LINE);
    }

    private void printDeleteMod() {
        System.out.println(DELETE_MOD_MESSAGE);
        System.out.println(LINE);
    }

    private void executeListCurrentModulesCommand(ArrayList<Module> modules) {
        Parser.printCurrentModList(modules);
    }

    // Todo: Right now, it uses university Name only but since university object has 3 attributes:
    // todo: handle exceptions such that the universityname inputted is incorrect
    // 1. univId; 2. univName; 3. univAbbName; we can use this next time
    // Note that this function, takes in the arrayList of modules of ALL MODULES
    // THIS IS NOT THE FUNCTION THAT RETURNS USER SELECTED MODULES SPECIFIED TO A PU.
    private void executeListPuModulesCommand(ArrayList<Module> modules, ArrayList<University> universities,
                                             String universityAbbName) {
        int univId = -1;
        for (int i = 0; i < universities.size(); i++) {
            University currentUniversity = universities.get(i);
            String currentUniversityAbbName = currentUniversity.getUnivAbbName();
            if (universityAbbName.equals(currentUniversityAbbName)) {
                univId = currentUniversity.getUnivId(); //Todo: change magic literal
            }
        }
        Parser.printPUModules(univId);
    }

    private void executeExitCommand() {
        System.out.println("Exiting program now");
    }

    private void executeListAllPuUniversitiesCommand(ArrayList<University> universities) {
        Parser.printPUList();
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

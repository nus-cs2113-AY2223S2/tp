package seedu.duke;
import java.util.ArrayList;

public class UI {
    public UI() {};

    // Todo: The userInput now is the whole string, and I am breaking it down into an Array of Strings here
    // Todo: This will be changed when the parser is added.
    public boolean executeUserCommand(String userInput, ArrayList<University> universities, ArrayList<Module> modules,
                                      ArrayList<Module> allModules, Storage storage) {
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
                executeListAllPuUniversitiesCommand(universities);
            } else if (userCommandSecondKeyword.equalsIgnoreCase("current")) {
                executeListCurrentModulesCommand(modules);
            } else {  // list PU name case
                int indexOfFirstSpace = userInput.indexOf(' ');
                String universityName = userInput.substring(indexOfFirstSpace + 1);
                executeListPuModulesCommand(allModules, universities, universityName);
            }
            break;
        case "exit":
            toContinue = false;
            executeExitCommand();
            break;
        case "add":
            executeAddModuleCommand(storage, userCommandSecondKeyword, allModules);
            break;
        case "remove":
            break; // Todo: Add remove function after darren PR
        default:
            System.out.println("Invalid Input");
            break;
        }
        return toContinue;
    }
    private void executeListCurrentModulesCommand(ArrayList<Module> modules) {
        Parser.printCurrentModList();
    }

    // Todo: Right now, it uses university Name only but since university object has 3 attributes:
    // todo: handle exceptions such that the univeristyname inputted is incorrect
    // 1. univId; 2. univName; 3. univAbbName; we can use this next time
    // Note that this function, takes in the arrayList of modules of ALL MODULES
    // THIS IS NOT THE FUNCITON THAT RETURNS USER SELECTED MODULES SPECIFIED TO A PU.
    private void executeListPuModulesCommand(ArrayList<Module> modules, ArrayList<University> universities,
                                             String universityName) {
        int univId = -1;
        for (int i = 0; i < universities.size(); i++) {
            University currentUniversity = universities.get(i);
            String currentUniversityName = currentUniversity.getUnivName();
            if (universityName.equals(currentUniversityName)) {
                univId = currentUniversity.getUnivId();
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
    private void executeAddModuleCommand(Storage storage, String moduleCode, ArrayList<Module> allModules) {
        for (int i = 0; i < allModules.size(); i++) {
            Module currentModule = allModules.get(i);
            if (currentModule.getModuleCode().equals(moduleCode)) {
                storage.addModuleToModuleList(currentModule);
            }
        }
    }
}

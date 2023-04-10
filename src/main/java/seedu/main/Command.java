package seedu.main;

import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.main.Main.LOGGER;

public class Command {
    private static final int REQUIRED_MC_CORE = 96;
    private static final int REQUIRED_MC_GE = 20;
    private static final int REQUIRED_MC_UE = 32;
    private static final int REQUIRED_MC_INTERNSHIP = 12;
    private InputChecker checkInput = new InputChecker();
    public Command () {

    }

    //@@author anthea-pr0g
    /**
     * Checks for valid manpage command in the user input
     *
     * @param userCommands The command that the user inputs to the ChatBot
     */

    public void manPageCommand(String[] userCommands, String userInput) {
        LOGGER.log(Level.INFO, "Starting manpage process");

        if(userInput.equalsIgnoreCase("man")) {
            ManPage.printManPage();
        } else {
            try {
                int numberOfFields = 2;
                checkInput.checkNumberOfFields(numberOfFields, userCommands);
                checkInput.checkManPageInput(userCommands);
            } catch (MainException e) {
                Print.printErrorMessage(e);
                LOGGER.log(Level.WARNING, "User Input is invalid");
            }
            assert userCommands.length == 2;
        }
        LOGGER.log(Level.INFO, "End of manpage process");
    }

    //@@author geraldkoh4
    public void addCommand(String[] userCommands, ModuleList moduleList) {
        try {
            LOGGER.log(Level.INFO, "Starting addModule process");
            checkInput.checkAddInput(moduleList, userCommands);
        } catch (MainException e) {
            LOGGER.log(Level.WARNING, "addModule Check failed: " + e.getDescription());
            Print.printErrorMessage(e);
        }
        LOGGER.log(Level.INFO, "End of addModule process");
    }

    //@@author Thiolk
    /**
     * Finds module by 2 ways, via their module code or via their module type
     * Finding module by their module code will return every module that contains the keyword inputted by the user
     * Finding module by type will return every module that corresponds to the module type inputted by the user
     *
     * @param userCommands the command that the user inputs into the ChatBot
     * @param moduleList the list of modules in the module plan
     */
    public void findCommand(String[] userCommands, ModuleList moduleList) {
        try {
            int numberOfFields = 3;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }
        assert userCommands.length == 3;

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }
        ArrayList<Module> foundModules = new ArrayList<>();

        try {
            if (userCommands[1].trim().equals("CODE")) {
                checkInput.checkAlphanumeric(userCommands[2].trim());
                foundModules = moduleList.findModuleByCode(userCommands[2].trim());
            } else if (userCommands[1].trim().equals("TYPE")) {
                foundModules = moduleList.findModuleByType(userCommands[2].trim());
            } else {
                throw new MainException("Please specify type of search with /Code or /Type");
            }

            if (foundModules.isEmpty()) {
                Print.printNoModuleFound(userCommands[1].trim(), userCommands[2].trim());
                LOGGER.log(Level.INFO, "Finished findModule process with no matching module found");

            } else {
                LOGGER.log(Level.INFO, "Finished findModule process with matching module found");
                Print.printFoundModule(foundModules);
            }
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }
    }

    //@@author chokyao
    public void deleteCommand(String[] userCommands, ModuleList moduleList) {
        try {
            int numberOfFields = 2;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }
        assert userCommands.length == 2;

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }

        try {
            checkInput.checkAlphanumeric(userCommands[1].trim());
            Module deletedModule = moduleList.deleteModule(userCommands[1].trim());
            if (deletedModule == null) { // When no module matches the given module code
                Print.printNoDeletedModuleFound(userCommands[1].trim());
            } else { // When a matching module is found and successfully deleted
                Print.printDeletedModule(deletedModule, moduleList.getModuleListSize());
            }
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }
    }

    //@@author saintzaw
    /**
     * Checks the user input to ensure its validity before running the listModules method
     *
     * @param userCommands the command that the user inputs into the ChatBot
     * @param moduleList the list of modules in the module plan
     */
    public void listCommand(String[] userCommands, ModuleList moduleList) throws MainException {
        try {
            int numberOfFields = 2;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }
        assert userCommands.length == 2;

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }

        listModules(moduleList, userCommands[1].trim());
    }

    //@@author saintzaw
    /**
     * Lists the modules in the year specified by the user.
     *
     * @param listOfModules the list of modules in the module plan
     * @param year the year in which the modules are taken or to be taken in
     * @throws MainException if the user input for the year is invalid
     */
    private void listModules(ModuleList listOfModules, String year) throws MainException {
        switch(year) {
        case "ALL":
            listOfModules.listModulesByYear("1");
            listOfModules.listModulesByYear("2");
            listOfModules.listModulesByYear("3");
            listOfModules.listModulesByYear("4");
            break;
        case "1":
            listOfModules.listModulesByYear("1");
            break;
        case "2":
            listOfModules.listModulesByYear("2");
            break;
        case "3":
            listOfModules.listModulesByYear("3");
            break;
        case "4":
            listOfModules.listModulesByYear("4");
            break;
        default:
            throw new MainException("Make sure Year of Study is an integer with no decimal place from 1-4 or \"all\" \n"
                    + "     Example: 1.0 is not accepted as a valid input!");
        }
    }

    //@@author saintzaw
    /**
     * Checks the user input to ensure its validity before running the editModuleField method
     *
     * @param userCommands the command that the user inputs into the ChatBot
     * @param moduleList the list of modules in the module plan
     */
    public void editCommand(String[] userCommands, ModuleList moduleList) {
        try {
            int numberOfFields = 4;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }
        assert userCommands.length == 4;

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }

        try {
            editModuleField(moduleList, userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }
    }

    //@@author saintzaw
    /**
     * Edits the existing information in a specific field of the module description with the
     * new information provided by the user.
     *
     * @param listOfModules the list of modules in the module plan
     * @param userCommands the command that the user inputs into the ChatBot
     * @throws MainException if the user input for the field to edit is invalid
     */
    private void editModuleField(ModuleList listOfModules, String[] userCommands) throws MainException {
        //format: edit /moduleCode /field /update
        String moduleCode = userCommands[1].trim();
        //the field that the user wants to update
        String moduleField = userCommands[2].trim();
        //the new information to replace in the indicated field
        String update = userCommands[3].trim();

        // Checks if the specified module exists
        ArrayList<Module> modules = listOfModules.getModuleList();
        boolean moduleIsFound = false;
        for (Module module : modules) {
            if (module.getModuleCode().equals(moduleCode)) {
                moduleIsFound = true;
                break;
            }
        }
        if (!moduleIsFound) {
            throw new MainException("The module you are trying to edit does not exist in the module plan. " +
                    System.lineSeparator() +
                    "\t Please add the module using the add command if you wish to take the module in the future.");
        }

        switch (moduleField) {
        case "MC":
            checkInput.checkEditInputCorrectModularCreditField(update);
            listOfModules.editModularCredits(moduleCode, update);
            break;
        case "TYPE":
            checkInput.checkEditInputCorrectTypeOfModule(update);
            listOfModules.editModuleType(moduleCode, update);
            break;
        case "CODE":
            checkInput.checkAddInputNoDuplicates(update, listOfModules.getModuleList());
            checkInput.checkEditInputCorrectModuleCode(update);
            listOfModules.editModuleCode(moduleCode, update);
            break;
        case "YEAR":
            checkInput.checkEditInputYear(update);
            listOfModules.editYear(moduleCode, update);
            break;
        case "SEMESTER":
            checkInput.checkEditInputSemester(update);
            listOfModules.editSemester(moduleCode, update);
            break;
        case "GRADE":
            checkInput.checkGradeInput(update);
            listOfModules.editModuleGrade(moduleCode, update);
            break;
        default:
            throw new MainException("Make sure the field to edit is MC, type, code, " +
                    "year, semester or grade");
        }
    }

    //@@author denzelcjy
    /**
     * Updates the module grade of the given module in the module list.
     *
     * @param userCommands the command that the user inputs into the ChatBot
     * @param moduleList the list of modules in the module plan
     */
    public void gradeCommand(String[] userCommands, ModuleList moduleList) {
        try {
            LOGGER.log(Level.INFO, "Starting Grade process");
            int numberOfFields = 3;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            LOGGER.log(Level.WARNING, "grade input length check failed: " + e.getDescription());
            Print.printErrorMessage(e);
            return;
        }
        assert userCommands.length == 3;

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }

        try {
            checkInput.checkAlphanumeric(userCommands[1].trim());
            checkInput.checkGradeInput(userCommands[2].trim());
            Module moduleGradeUpdated =
                    moduleList.updateModuleGrade(userCommands[1].trim(), userCommands[2].trim(), "grade");
            if (moduleGradeUpdated != null) {
                LOGGER.log(Level.INFO, "Update grade process success!");
                Print.printUpdatedModuleGrade(moduleGradeUpdated);
            } else {
                Print.printInvalidModule(userCommands[1].trim());
            }
        } catch (MainException e) {
            LOGGER.log(Level.WARNING, "valid grade input check failed: " + e.getDescription());
            Print.printErrorMessage(e);
        }
    }

    /**
     * Allows calculation of user's CAP based on grades with valid grade values.
     *
     * @param userCommands the command that the user inputs into the ChatBot
     * @param moduleList the list of modules in the module plan
     */
    public void calculateCAPCommand(String[] userCommands, ModuleList moduleList) {
        try {
            LOGGER.log(Level.INFO, "Starting process to calculate CAP");
            int numberOfFields = 1;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            LOGGER.log(Level.WARNING, "calculatecap input length check failed: " + e.getDescription());
            Print.printErrorMessage(e);
            return;
        }

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }

        assert userCommands.length == 1;
        moduleList.calculateCAP();
        LOGGER.log(Level.INFO, "End of calculate CAP process");
    }

    //@@author
    public void byeCommand(String[] userCommands, ModuleList moduleList) {
        try {
            int numberOfFields = 1;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }

        assert userCommands.length == 1;
        Storage.saveModules(moduleList.getModuleList());
        Print.printFarewellMessage();
        LOGGER.log(Level.INFO, "Exiting Modganiser.");
    }

    //@@author Thiolk
    /**
     * Checks the user input to ensure its validity before running the trackGraduationRequirement method
     *
     * @param userCommands the command that the user inputs into the ChatBot
     * @param moduleList the list of modules in the module plan
     */
    public void trackCommand(String[] userCommands, ModuleList moduleList) {
        try {
            int numberOfFields = 2;
            checkInput.checkNumberOfFields(numberOfFields, userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }
        assert userCommands.length == 2;

        try {
            checkInput.checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            return;
        }

        try {
            trackGraduationRequirements(moduleList, userCommands[1].trim());
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }
    }

    /**
     * Check how far the user is from meeting the requirement for each module type
     *
     * @param listOfModules list of modules that the user has
     * @param moduleType module type that the user wants to check
     * @throws MainException if the moduleType the user input is not valid
     */
    private void trackGraduationRequirements(ModuleList listOfModules, String moduleType) throws MainException {
        ArrayList<Module> foundModules = new ArrayList<>();
        String[] moduleTypes = {"CORE", "GE" , "UE", "INTERNSHIP"};
        int[] requiredMCs = {REQUIRED_MC_CORE, REQUIRED_MC_GE, REQUIRED_MC_UE, REQUIRED_MC_INTERNSHIP};
        int requiredMC = 0;
        boolean isTrackAll = false;
        switch(moduleType) {
        case "ALL":
            for (int i = 0; i < moduleTypes.length; i++) {
                foundModules = listOfModules.findModuleByType(moduleTypes[i]);
                listOfModules.trackModules(foundModules, moduleTypes[i], requiredMCs[i]);
            }
            isTrackAll = true;
            break;
        case "CORE":
            requiredMC = requiredMCs[0];
            break;
        case "GE":
            requiredMC = requiredMCs[1];
            break;
        case "UE":
            requiredMC = requiredMCs[2];
            break;
        case "INTERNSHIP":
            requiredMC = requiredMCs[3];
            break;
        default:
            throw new MainException("Make sure you're trying to track Core, GE, UE, Internship or All.");
        }
        if (!isTrackAll) {
            foundModules = listOfModules.findModuleByType(moduleType);
            listOfModules.trackModules(foundModules, moduleType, requiredMC);
        }
    }
}

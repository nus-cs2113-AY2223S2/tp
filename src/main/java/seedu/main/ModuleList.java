package seedu.main;

import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.main.Main.LOGGER;

public class ModuleList {
    private static final int MODULE_TYPE_INDEX = 0;
    private static final int MODULE_GRADE_INDEX = 1;
    private static final int MODULE_CODE_INDEX = 2;
    private static final int MODULAR_CREDITS_INDEX = 3;
    private static final int MODULE_YEAR_INDEX = 4;
    private static final int MODULE_SEMESTER_INDEX = 5;
    private static final String SEMESTER_ONE = "1";
    private static final String SPECIAL_TERM_ONE = "1.5";
    private static final String SEMESTER_TWO = "2";
    private static final String SPECIAL_TERM_TWO = "2.5";
    private ArrayList<Module> listOfModules;

    /**
     * Initialises listOfModules to an empty ArrayList of modules
     */
    public ModuleList() {
        this.listOfModules = new ArrayList<>();
    }

    /**
     * Initialise listOfModules to an ArrayList containing all the previously saved modules
     * @param savedModules An ArrayList containing string representations of saved modules
     */
    public ModuleList(ArrayList<String> savedModules) throws MainException {
        this.listOfModules = new ArrayList<>();
        LOGGER.log(Level.INFO, "Starting process to load previously saved modules");
        for (String line : savedModules) {
            addExistingModule(line);
        }
        LOGGER.log(Level.INFO, "Finished loading previously saved modules");
    }

    public ArrayList<Module> getModuleList() {
        return listOfModules;
    }

    public int getModuleListSize() {
        return listOfModules.size();
    }

    /**
     * Adds a module to the moduleList.
     *
     * @param moduleCode The unique identifier of modules.
     * @param modularCredits The amount of credits prescribed to the module.
     * @param moduleType The type of module to be added. [Core, GeneralElective, UnrestrictedElective, Internship]
     * @param year The year that the module is taken or to be taken in. [1-6]
     * @param semester The semester that the module is taken or to be taken in. [1, 1.5, 2, 2.5]
     * @return
     */
    public Module addModule(String moduleCode, String modularCredits,
                            String moduleType, String year, String semester) {
        int oldSizeOfList = listOfModules.size();
        LOGGER.log(Level.INFO, "Starting addModule process");
        switch (moduleType.toUpperCase()) {
        case "CORE":
            Core newCore = new Core(moduleCode, modularCredits, year, semester);
            listOfModules.add(newCore);
            return newCore;
        case "GE":
            GeneralElective newGeneralElective = new GeneralElective(moduleCode, modularCredits, year, semester);
            listOfModules.add(newGeneralElective);
            return newGeneralElective;
        case "UE":
            UnrestrictedElective newUnrestrictedElective =
                    new UnrestrictedElective(moduleCode, modularCredits, year, semester);
            listOfModules.add(newUnrestrictedElective);
            return newUnrestrictedElective;
        case "INTERNSHIP":
            Internship newInternship = new Internship(moduleCode, modularCredits, year, semester);
            listOfModules.add(newInternship);
            return newInternship;
        default:
            break;
        }
        assert listOfModules.size() == oldSizeOfList + 1 : "Module not added correctly";
        LOGGER.log(Level.INFO, "Finished addModule process");
        return null;
    }

    /**
     * List all the modules that contains the keyword in their module code
     *
     * @param keyword The word that the user would like to search for
     * @throws MainException if the list of modules is currently empty
     */
    public ArrayList<Module> findModuleByCode(String keyword) throws MainException {
        if (listOfModules.size() == 0) {
            throw new MainException("There are currently no modules in your module plan.");
        }
        assert listOfModules.size() > 0 : "no items in list";
        LOGGER.log(Level.INFO, "Starting findModuleByName process");
        ArrayList<Module> foundModules = new ArrayList<>();
        for (Module module : listOfModules) {
            if (module.getModuleCode().contains(keyword)) {
                foundModules.add(module);
            }
        }
        return foundModules;
    }

    /**
     * List all the modules that are of the corresponding module type
     *
     * @param type The module type that the user wants to search for
     * @throws MainException if the module type the user input is invalid or if the list of modules is currently empty
     */
    public ArrayList<Module> findModuleByType(String type) throws MainException {
        if (listOfModules.size() == 0) {
            throw new MainException("There are currently no modules in your module plan.");
        }
        assert listOfModules.size() > 0 : "no items in list";
        LOGGER.log(Level.INFO, "Starting findModuleByType process");
        ArrayList<Module> foundModules = new ArrayList<>();
        Class searchForType = null;
        switch (type) {
        case "CORE":
            searchForType = Core.class;
            break;
        case "GE":
            searchForType = GeneralElective.class;
            break;
        case "UE":
            searchForType = UnrestrictedElective.class;
            break;
        case "INTERNSHIP":
            searchForType = Internship.class;
            break;
        default:
            throw new MainException("Make sure your module type input is CORE, GE, UE or Internship");
        }
        for (Module module : listOfModules) {
            if(module.getClass().equals(searchForType)) {
                foundModules.add(module);
            }
        }
        return foundModules;
    }

    /**
     * Deletes a module from the moduleList.
     *
     * @param moduleCode The unique identifier of modules.
     * @return the Module object that was deleted from the moduleList, returns null if no module was deleted
     */
    public Module deleteModule(String moduleCode) throws MainException {
        if (listOfModules.size() == 0) {
            throw new MainException("There are currently no modules in your list");
        }
        assert listOfModules.size() > 0 : "no items in list";
        LOGGER.log(Level.INFO, "Starting deleteModule process");
        Module deletedModule = null;
        int oldSizeOfList = listOfModules.size();
        for (int i = 0; i < listOfModules.size(); i++) {
            if (listOfModules.get(i).getModuleCode().equals(moduleCode)) {
                deletedModule = listOfModules.get(i);
                listOfModules.remove(i);
                assert listOfModules.size() == oldSizeOfList - 1 : "Module not deleted correctly";
                LOGGER.log(Level.INFO, "Finished deleteModule process with module successfully deleted");
                return deletedModule;
            }
        }
        assert listOfModules.size() == oldSizeOfList : "Wrong module was deleted";
        LOGGER.log(Level.INFO, "Finished deleteModule process with no module deleted");
        return deletedModule;
    }

    /**
     * Lists all the modules in the moduleList.
     */
    public void listModules() {
        LOGGER.log(Level.INFO, "Starting listModules process");
        if (getModuleListSize() > 0) {
            Print.printModuleList(listOfModules);
        } else {
            String year = "0";
            Print.printEmptyModuleList(year);
        }
        LOGGER.log(Level.INFO, "Finished listModules process");
    }

    /**
     * Creates a list of modules for Semester 1, Special Term 1, Semester 2 and Special Term 2 of a given year.
     *
     * @param year The year that the modules are taken or to be taken in. [1-4]
     * @param moduleListByYear The list of modules in that year
     * @param moduleListSemOne The list of modules in Semester 1
     * @param moduleListSpecialTermOne  The list of modules in Special Term 1
     * @param moduleListSemTwo  The list of modules in Semester 2
     * @param moduleListSpecialTermTwo The list of modules in Special Term 2
     */
    public void addModulesToLists(String year, ArrayList<Module> moduleListByYear, ArrayList<String> moduleListSemOne,
                                 ArrayList<String> moduleListSpecialTermOne, ArrayList<String> moduleListSemTwo,
                                 ArrayList<String> moduleListSpecialTermTwo ) {

        for (Module module : listOfModules) {
            //the format for displaying the module in the module plan is
            // [TYPE][GRADE] MODULE_CODE MODULAR_CREDITS 'MCs'
            String mod = "[" + module.getModuleType() + "]" + "[\"" + module.getGrade() + "\"] "
                    + module.getModuleCode() + " " + module.getModularCredits() + " MCs";

            if (module.getYear().equals(year)) {
                switch (module.getSemester()) {
                case SEMESTER_ONE:
                    moduleListSemOne.add(mod);
                    moduleListByYear.add(module);
                    break;
                case SPECIAL_TERM_ONE:
                    moduleListSpecialTermOne.add(mod);
                    moduleListByYear.add(module);
                    break;
                case SEMESTER_TWO:
                    moduleListSemTwo.add(mod);
                    moduleListByYear.add(module);
                    break;
                case SPECIAL_TERM_TWO:
                    moduleListSpecialTermTwo.add(mod);
                    moduleListByYear.add(module);
                    break;
                default:
                    moduleListByYear.add(module);
                }
            }
        }
    }

    /**
     * Lists all the modules in the moduleList that have the year attribute specified by the user input.
     *
     * @param year The year that the module is taken or to be taken in. [1-4]
     */
    public void listModulesByYear(String year) {
        LOGGER.log(Level.INFO, "Starting listModulesByYear process");
        ArrayList<Module> moduleListByYear = new ArrayList<>();
        ArrayList<String> moduleListSemOne = new ArrayList<>();
        ArrayList<String> moduleListSpecialTermOne = new ArrayList<>();
        ArrayList<String> moduleListSemTwo = new ArrayList<>();
        ArrayList<String> moduleListSpecialTermTwo = new ArrayList<>();

        if (getModuleListSize() > 0) {
            addModulesToLists(year, moduleListByYear, moduleListSemOne, moduleListSpecialTermOne,
                    moduleListSemTwo, moduleListSpecialTermTwo);
            if (moduleListByYear.size() > 0 ) {
                Print.printModuleListByYear(moduleListSemOne, moduleListSpecialTermOne,
                        moduleListSemTwo, moduleListSpecialTermTwo, year);
            } else {
                Print.printEmptyModuleList(year);
            }
        } else {
            Print.printEmptyModuleList(year);
        }
        LOGGER.log(Level.INFO, "Finished listModulesByYear process");
    }

    /**
     * Edits the Modular Credits attribute of a specified module.
     *
     * @param moduleCode The unique identifier of the module.
     * @param newModularCredits The updated amount of credits that are to be prescribed to the module.
     */
    public void editModularCredits(String moduleCode, String newModularCredits) {
        LOGGER.log(Level.INFO, "Starting editModularCredits process");
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                module.setModularCredits(newModularCredits);
                Print.printEditedModule(module, listOfModules.size());
            }
        }
        LOGGER.log(Level.INFO, "Finished editModularCredits process");
    }

    /**
     * Edits the Year attribute of a specified module.
     *
     * @param moduleCode The unique identifier of the module.
     * @param newYear The updated year that the module is taken or to be taken in. [1-6]
     */
    public void editYear(String moduleCode, String newYear) {
        LOGGER.log(Level.INFO, "Starting editYear process");
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                module.setYear(newYear);
                Print.printEditedModule(module, listOfModules.size());
            }
        }
        LOGGER.log(Level.INFO, "Finished editModularCredits process");
    }

    /**
     * Edits the Semester attribute of a specified module.
     *
     * @param moduleCode The unique identifier of the module.
     * @param newSemester The updated semester that the module is taken or to be taken in. [1, 1.5, 2, 2.5]
     */
    public void editSemester(String moduleCode, String newSemester) {
        LOGGER.log(Level.INFO, "Starting editSemester process");
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                module.setSemester(newSemester);
                Print.printEditedModule(module, listOfModules.size());
            }
        }
        LOGGER.log(Level.INFO, "Finishing editSemester process");
    }

    /**
     * Edits the Grade attribute of a specified module.
     *
     * @param moduleCode The unique identifier of the module.
     * @param newGrade The updated grade that is to be prescribed to the module.
     * @throws MainException if command is edit and grade has not been added yet.
     */
    public void editModuleGrade(String moduleCode, String newGrade) throws MainException {
        LOGGER.log(Level.INFO, "Starting editModuleGrade process");
        Module updatedModule = updateModuleGrade(moduleCode, newGrade, "edit");
        if (updatedModule != null) {
            Print.printEditedModule(updatedModule, listOfModules.size());
        }
        LOGGER.log(Level.INFO, "Finishing editModuleGrade process");
    }

    /**
     * Edits the Module Type attribute of a specified module.
     *
     * @param moduleCode The unique identifier of the module.
     * @param moduleType The updated type of module. [Core, GeneralElective, UnrestrictedElective, Internship]
     */
    public void editModuleType(String moduleCode, String moduleType) {
        LOGGER.log(Level.INFO, "Starting editModuleType process");
        try {
            // Delete module with old moduleType
            Module oldModule = deleteModule(moduleCode);
            String modularCredits = oldModule.getModularCredits();
            String year = oldModule.getYear();
            String semester = oldModule.getSemester();
            String grade = oldModule.getGrade();

            // Add new module
            Module moduleEdited = addModule(moduleCode, modularCredits, moduleType, year, semester);
            updateModuleGrade(moduleCode, grade, "grade");
            Print.printEditedModule(moduleEdited, listOfModules.size());
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }
        LOGGER.log(Level.INFO, "Finishing editModuleType process");
    }

    /**
     * Edits the Module Code attribute of a specified module.
     *
     * @param oldModuleCode The original unique identifier of the module.
     * @param newModuleCode The updated unique identifier of the module.
     */
    public void editModuleCode(String oldModuleCode, String newModuleCode) {
        LOGGER.log(Level.INFO, "Starting editModuleCode process");
        try {
            // Delete module with old moduleType
            Module oldModule = deleteModule(oldModuleCode);
            String moduleType = oldModule.getModuleType();
            String modularCredits = oldModule.getModularCredits();
            String year = oldModule.getYear();
            String semester = oldModule.getSemester();
            String grade = oldModule.getGrade();

            // Add new module
            Module moduleEdited;
            if (moduleType == "C") {
                moduleEdited = addModule(newModuleCode, modularCredits, "CORE", year, semester);
            } else if (moduleType == "I") {
                moduleEdited = addModule(newModuleCode, modularCredits, "INTERNSHIP", year, semester);
            } else {
                moduleEdited = addModule(newModuleCode, modularCredits, moduleType, year, semester);
            }
            updateModuleGrade(newModuleCode, grade, "grade");
            Print.printEditedModule(moduleEdited, listOfModules.size());
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }
        LOGGER.log(Level.INFO, "Finishing editModuleCode process");
    }

    /**
     * Based on an existing saved module, add this module to listOfModules
     * @param line A string representation of a module, containing all its relevant information
     * @throws MainException If any of the parameters of the module is not valid
     */
    public void addExistingModule(String line) throws MainException {
        String[] moduleData = line.split("\\|");

        String moduleGrade = moduleData[MODULE_GRADE_INDEX];
        String moduleCode = moduleData[MODULE_CODE_INDEX];
        String modularCredits = moduleData[MODULAR_CREDITS_INDEX];
        String moduleYear = moduleData[MODULE_YEAR_INDEX];
        String moduleSemester = moduleData[MODULE_SEMESTER_INDEX];

        checkIfTampered(moduleGrade, moduleCode, modularCredits, moduleYear, moduleSemester);

        switch (moduleData[MODULE_TYPE_INDEX]) {
        case "C":
            Core coreModule = new Core(moduleCode, modularCredits, moduleYear, moduleSemester);
            coreModule.setGrade(moduleGrade);
            listOfModules.add(coreModule);
            break;
        case "GE":
            GeneralElective generalElectiveModule = new GeneralElective(moduleCode,
                    modularCredits, moduleYear, moduleSemester);
            generalElectiveModule.setGrade(moduleGrade);
            listOfModules.add(generalElectiveModule);
            break;
        case "UE":
            UnrestrictedElective unrestrictedElectiveModule =
                    new UnrestrictedElective(moduleCode, modularCredits, moduleYear, moduleSemester);
            unrestrictedElectiveModule.setGrade(moduleGrade);
            listOfModules.add(unrestrictedElectiveModule);
            break;
        case "I":
            Internship internshipModule = new Internship(moduleCode, modularCredits, moduleYear, moduleSemester);
            internshipModule.setGrade(moduleGrade);
            listOfModules.add(internshipModule);
            break;
        default:
            throw new MainException("Invalid module type");
        }
    }

    /**
     * Given all the parameters of a saved module, check if the parameters are valid or tampered with
     * @param moduleGrade A string representation of a module's grade
     * @param moduleCode A string representation of a module's code
     * @param modularCredits A string representation of a module's modular credits
     * @param moduleYear A string representation of a module's year
     * @param moduleSemester A string representation of a module's semester
     * @throws MainException If any of the parameters provided is not valid
     */
    public void checkIfTampered(String moduleGrade, String moduleCode, String modularCredits,
            String moduleYear, String moduleSemester) throws MainException {
        InputChecker checkInput = new InputChecker();;
        if (!moduleGrade.equals(" ")) {
            checkInput.checkGradeInput(moduleGrade);
        }
        checkInput.checkEditInputCorrectModuleCode(moduleCode);
        checkInput.checkEditInputCorrectModularCreditField(modularCredits);
        checkInput.checkEditInputYear(moduleYear);
        checkInput.checkEditInputSemester(moduleSemester);
    }

    /**
     * Updates the grade attribute for the module code specified by the user.
     *
     * @param moduleCode the string containing the name of the module code.
     * @param moduleGrade the string containing the grade input by user.
     * @return the module object with the updated grade attribute.
     * @throws MainException if command is "grade" and grade has already been added, or if command is edit and grade has
     *     not been added yet.
     */
    public Module updateModuleGrade(String moduleCode, String moduleGrade, String command) throws MainException {
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                validateSetModuleGrade(module, command);
                module.setGrade(moduleGrade);
                return module;
            }
        }
        return null;
    }

    /**
     * Validates whether the grade can be updated for the specific command given.
     *
     * @param module the module object that matches the module to be updated by the user.
     * @param command the string containing the command that the method is called from.
     * @throws MainException if command is "grade" and grade has already been added, or if command is edit and grade has
     *     not been added yet.
     */
    private void validateSetModuleGrade(Module module, String command) throws MainException {
        if (command.equals("grade") && !module.getGrade().equals(" ")) {
            throw new MainException("Grade for module has already been added, you may edit your grade" +
                    " using the edit command instead!");
        }
        if (command.equals("edit") && module.getGrade().equals(" ")) {
            throw new MainException("Grade for module has not been added yet, you may add your grade" +
                    " using the grade command instead!");
        }
    }

    /**
     * Calculates the CAP for the user based on existing modules in the list.
     */
    public void calculateCAP() {
        double calculatedCAP;
        double sumOfWeightage = 0;
        int totalModularCredits = 0;
        boolean hasGradedModules = false;
        if (listOfModules.size() == 0) {
            Print.printNoModulesToCalculateCAP();
            return;
        }
        for (Module module : listOfModules) {
            if(shouldCountModuleGrade(module.getGrade())) {
                sumOfWeightage += getGradeValue(module.getGrade()) * Integer.parseInt(module.getModularCredits());
                totalModularCredits += Integer.parseInt(module.getModularCredits());
                hasGradedModules = true;
            }
        }
        if (hasGradedModules) {
            calculatedCAP = sumOfWeightage / (double) totalModularCredits;
            double roundedOffCAP = Math.round(calculatedCAP * 100.0) / 100.0;
            LOGGER.log(Level.INFO, "CAP has been calculated, proceeding to print.");
            Print.printCalculatedCAP(roundedOffCAP);
        } else {
            Print.printNoGradedModulesToCalculateCAP();
        }
    }

    /**
     * Checks if a module's grade should be counted in the CAP for the user.
     *
     * @param moduleGrade the string containing the grade input by user.
     * @return a boolean that is set to true if the module grade should be counted, else set to false.
     */
    public boolean shouldCountModuleGrade(String moduleGrade) {
        boolean shouldCount;
        switch(moduleGrade) {
        case InputChecker.GRADE_A_PLUS:
            // Fallthrough
        case InputChecker.GRADE_A:
            // Fallthrough
        case InputChecker.GRADE_A_MINUS:
            // Fallthrough
        case InputChecker.GRADE_B_PLUS:
            // Fallthrough
        case InputChecker.GRADE_B:
            // Fallthrough
        case InputChecker.GRADE_B_MINUS:
            // Fallthrough
        case InputChecker.GRADE_C_PLUS:
            // Fallthrough
        case InputChecker.GRADE_C:
            // Fallthrough
        case InputChecker.GRADE_D_PLUS:
            // Fallthrough
        case InputChecker.GRADE_D:
            // Fallthrough
        case InputChecker.GRADE_F:
            shouldCount = true;
            break;
        default:
            shouldCount = false;
            break;
        }
        return shouldCount;
    }

    /**
     * Gets the grade value corresponding to the module grade string given by the user.
     *
     * @param moduleGrade the string containing the grade input by user.
     * @return the double value corresponding to the module grade given.
     */
    public double getGradeValue(String moduleGrade) {
        double gradeValue;
        switch(moduleGrade) {
        case InputChecker.GRADE_A_PLUS:
            // Fallthrough
        case InputChecker.GRADE_A:
            gradeValue = 5.0;
            break;
        case InputChecker.GRADE_A_MINUS:
            gradeValue = 4.5;
            break;
        case InputChecker.GRADE_B_PLUS:
            gradeValue = 4.0;
            break;
        case InputChecker.GRADE_B:
            gradeValue = 3.5;
            break;
        case InputChecker.GRADE_B_MINUS:
            gradeValue = 3.0;
            break;
        case InputChecker.GRADE_C_PLUS:
            gradeValue = 2.5;
            break;
        case InputChecker.GRADE_C:
            gradeValue = 2.0;
            break;
        case InputChecker.GRADE_D_PLUS:
            gradeValue = 1.5;
            break;
        case InputChecker.GRADE_D:
            gradeValue = 1.0;
            break;
        case InputChecker.GRADE_F:
            gradeValue = 0.0;
            break;
        default:
            gradeValue = 0;
            break;
        }
        return gradeValue;
    }

    /**
     * Based on a user-specified type of module, track the number of modules of this type that have
     * been completed
     *
     * @param moduleList list of modules of the same type that are in the list
     * @param moduleType String variable that holds the type of module
     * @param requiredMCs Integer representing the total required MCs for the type of module in moduleType
     */
    public void trackModules(ArrayList<Module> moduleList, String moduleType, int requiredMCs) {
        int plannedMCs = 0;
        int remainingMCs = 0;

        for (Module module : moduleList) {
            plannedMCs += Integer.parseInt(module.getModularCredits());
        }

        remainingMCs = requiredMCs - plannedMCs;
        Print.printModuleTypeRequirements(moduleList, plannedMCs, remainingMCs, requiredMCs, moduleType);
    }
}

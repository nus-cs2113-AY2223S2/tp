package seedu.duke;
import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.duke.Duke.LOGGER;

public class ModuleList {
    private static final int MODULE_TYPE_INDEX = 0;
    private static final int MODULE_GRADE_INDEX = 1;
    private static final int MODULE_CODE_INDEX = 2;
    private static final int MODULAR_CREDITS_INDEX = 3;
    private static final int MODULE_YEAR_INDEX = 4;
    private static final int MODULE_SEMESTER_INDEX = 5;

    private ArrayList<Module> listOfModules;

    public ModuleList() {
        this.listOfModules = new ArrayList<>();
    }

    public ModuleList(ArrayList<String> savedModules) {
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
     * @throws DukeException if the list of modules is currently empty
     */
    public ArrayList<Module> findModuleByName(String keyword) throws DukeException {
        if (listOfModules.size() == 0) {
            throw new DukeException("There are currently no modules in your list");
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
     * @throws DukeException if the module type the user input is invalid or if the list of modules is currently empty
     */
    public ArrayList<Module> findModuleByType(String type) throws DukeException {
        if (listOfModules.size() == 0) {
            throw new DukeException("There are currently no modules in your list");
        }
        assert listOfModules.size() > 0 : "no items in list";
        LOGGER.log(Level.INFO, "Starting findModuleByType process");
        ArrayList<Module> foundModules = new ArrayList<>();
        switch (type) {
        case "CORE":
            for (Module module : listOfModules) {
                if (module instanceof Core) {
                    foundModules.add(module);
                }
            }
            break;
        case "GE":
            for (Module module : listOfModules) {
                if (module instanceof GeneralElective) {
                    foundModules.add(module);
                }
            }
            break;
        case "UE":
            for (Module module : listOfModules) {
                if (module instanceof UnrestrictedElective) {
                    foundModules.add(module);
                }
            }
            break;
        case "INTERNSHIP":
            for (Module module : listOfModules) {
                if (module instanceof Internship) {
                    foundModules.add(module);
                }
            }
            break;
        default:
            throw new DukeException("Make sure your types are CORE, GE, UE or Internship");
        }
        return foundModules;
    }

    public Module deleteModule(String moduleCode) throws DukeException {
        if (listOfModules.size() == 0) {
            throw new DukeException("There are currently no modules in your list");
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

    public void listModulesByYear(String year) {
        LOGGER.log(Level.INFO, "Starting listModules process");
        ArrayList<Module> moduleListByYear = new ArrayList<>();
        ArrayList<String> moduleListSemOne = new ArrayList<>();
        ArrayList<String> moduleListSpecialTermOne = new ArrayList<>();
        ArrayList<String> moduleListSemTwo = new ArrayList<>();
        ArrayList<String> moduleListSpecialTermTwo = new ArrayList<>();

        if (getModuleListSize() > 0) {
            for (Module module : listOfModules) {
                String mod = "[\"" + module.getModuleType() + "\"]" + "[\"" + module.getGrade() + "\"] "
                        + module.getModuleCode() + " " + module.getModularCredits() + " MCs";
                if (module.getYear().equals(year)) {
                    switch (module.getSemester()) {
                    case "1":
                        moduleListSemOne.add(mod);
                        moduleListByYear.add(module);
                        break;
                    case "1.5":
                        moduleListSpecialTermOne.add(mod);
                        moduleListByYear.add(module);
                        break;
                    case "2":
                        moduleListSemTwo.add(mod);
                        moduleListByYear.add(module);
                        break;
                    case "2.5":
                        moduleListSpecialTermTwo.add(mod);
                        moduleListByYear.add(module);
                        break;
                    default:
                        moduleListByYear.add(module);
                    }
                }
            }
            if (moduleListByYear.size() != 0 ) {
                Print.printModuleListByYear(moduleListSemOne, moduleListSpecialTermOne,
                        moduleListSemTwo, moduleListSpecialTermTwo, year);
            } else {
                Print.printEmptyModuleList(year);
            }
        } else {
            Print.printEmptyModuleList(year);
        }
        LOGGER.log(Level.INFO, "Finished listModules process");
    }

    public void editModularCredits(String moduleCode, String newModularCredits) {
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                module.setModularCredits(newModularCredits);
                Print.printEditedModule(module, listOfModules.size());
            }
        }
    }

    public void editYear(String moduleCode, String newYear) {
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                module.setYear(newYear);
                Print.printEditedModule(module, listOfModules.size());
            }
        }
    }

    public void editSemester(String moduleCode, String newSemester) {
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                module.setSemester(newSemester);
                Print.printEditedModule(module, listOfModules.size());
            }
        }
    }

    public void editModuleType(String moduleCode, String modularCredits,
                               String moduleType, String year, String semester, String grade) {
        //delete module with old moduleType
        try {
            deleteModule(moduleCode);
        } catch (DukeException e) {
            Print.printErrorMessage(e);
        }
        //add module with new moduleType
        Module moduleEdited = addModule(moduleCode, modularCredits, moduleType, year, semester);
        updateModuleGrade(moduleCode, grade);
        Print.printEditedModule(moduleEdited, listOfModules.size());
    }

    /**
     * Based on the existing saved modules, add these modules to listOfModules
     * @param line A string representation of a module, containing all its relevant information
     */
    public void addExistingModule(String line) {
        String[] moduleData = line.split("\\|");

        String moduleGrade = moduleData[MODULE_GRADE_INDEX];
        String moduleCode = moduleData[MODULE_CODE_INDEX];
        String modularCredits = moduleData[MODULAR_CREDITS_INDEX];
        String moduleYear = moduleData[MODULE_YEAR_INDEX];
        String moduleSemester = moduleData[MODULE_SEMESTER_INDEX];

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
            break;
        }

    }

    /**
     * Updates the grade attribute for the module code specified by the user.
     *
     * @param moduleCode the string containing the name of the module code.
     * @param moduleGrade the string containing the grade input by user.
     * @return the module object with the updated grade attribute.
     */
    public Module updateModuleGrade(String moduleCode, String moduleGrade) {
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                module.setGrade(moduleGrade);
                return module;
            }
        }
        return null;
    }

    /**
     * Calculates the CAP for the user based on existing modules in the list.
     */
    public void calculateCAP() {
        double calculatedCAP;
        double sumOfWeightage = 0;
        int totalModularCredits = 0;
        for (Module module : listOfModules) {
            if(shouldCountModuleGrade(module.getGrade())) {
                sumOfWeightage += getGradeValue(module.getGrade()) * Integer.parseInt(module.getModularCredits());
                totalModularCredits += Integer.parseInt(module.getModularCredits());
            }
        }
        calculatedCAP = sumOfWeightage/(double)totalModularCredits;
        double roundedOffCAP = Math.round(calculatedCAP*100.0)/100.0;
        LOGGER.log(Level.INFO, "CAP has been calculated, proceeding to print.");
        Print.printCalculatedCAP(roundedOffCAP);
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
        case "A+":
            // Fallthrough
        case "A":
            // Fallthrough
        case "A-":
            // Fallthrough
        case "B+":
            // Fallthrough
        case "B":
            // Fallthrough
        case "B-":
            // Fallthrough
        case "C+":
            // Fallthrough
        case "C":
            // Fallthrough
        case "D+":
            // Fallthrough
        case "D":
            // Fallthrough
        case "F":
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
        case "A+":
            // Fallthrough
        case "A":
            gradeValue = 5.0;
            break;
        case "A-":
            gradeValue = 4.5;
            break;
        case "B+":
            gradeValue = 4.0;
            break;
        case "B":
            gradeValue = 3.5;
            break;
        case "B-":
            gradeValue = 3.0;
            break;
        case "C+":
            gradeValue = 2.5;
            break;
        case "C":
            gradeValue = 2.0;
            break;
        case "D+":
            gradeValue = 1.5;
            break;
        case "D":
            gradeValue = 1.0;
            break;
        case "F":
            gradeValue = 0.0;
            break;
        default:
            gradeValue = 0;
            break;
        }
        return gradeValue;
    }

    /**
     * Track the number of GE Modules completed
     *
     * @param listOfGeneralElectives list of Core Modules that are in the list
     * @param moduleType String variable that holds the moduleType, "GE"
     */
    public void trackGeneralElectives(ArrayList<Module> listOfGeneralElectives, String moduleType) {
        int completedMCs = 0;
        int requiredMCs = 20;
        int remainingMCs = 0;
        for (Module module : listOfGeneralElectives) {
            if (!(module.getGrade().equals(" "))) {
                completedMCs += Integer.parseInt(module.getModularCredits());
            } else {
                listOfGeneralElectives.remove(module);
            }
        }
        remainingMCs = requiredMCs - completedMCs;
        Print.printModuleTypeRequirements(listOfGeneralElectives,
                completedMCs, remainingMCs, requiredMCs, moduleType);
    }

    /**
     * Track the number of UE Modules completed
     *
     * @param listOfUnRestrictedElectives list of UE Modules that are in the list
     * @param moduleType String variable that holds the moduleType, "UE"
     */
    public void trackUnrestrictedElectives(ArrayList<Module> listOfUnRestrictedElectives, String moduleType) {
        int completedMCs = 0;
        int requiredMCs = 32;
        int remainingMCs = 0;
        for (Module module : listOfUnRestrictedElectives) {
            if (!(module.getGrade().equals(" "))) {
                completedMCs += Integer.parseInt(module.getModularCredits());
            } else {
                listOfUnRestrictedElectives.remove(module);
            }
        }
        remainingMCs = requiredMCs - completedMCs;
        Print.printModuleTypeRequirements(listOfUnRestrictedElectives,
                completedMCs, remainingMCs, requiredMCs, moduleType);
    }

    /**
     * Track the number of Internship Modules completed
     *
     * @param listOfInternship list of Internship Modules that are in the list
     * @param moduleType String variable that holds the moduleType, "Internship"
     */
    public void trackInternship(ArrayList<Module> listOfInternship, String moduleType) {
        int completedMCs = 0;
        int requiredMCs = 12;
        int remainingMCs = 0;
        for (Module module : listOfInternship) {
            if (!(module.getGrade().equals(" "))) {
                completedMCs += Integer.parseInt(module.getModularCredits());
            } else {
                listOfInternship.remove(module);
            }
        }
        remainingMCs = requiredMCs - completedMCs;
        Print.printModuleTypeRequirements(listOfInternship,
                completedMCs, remainingMCs, requiredMCs, moduleType);
    }

    /**
     * Track the number of Core Modules completed
     *
     * @param listOfCoreModules list of Core Modules that are in the list
     * @param moduleType String variable that holds the moduleType, "CORE"
     */
    public void trackCoreModules(ArrayList<Module> listOfCoreModules, String moduleType) {
        int completedMCs = 0;
        int requiredMCs = 96;
        int remainingMCs = 0;
        for (Module module : listOfCoreModules) {
            if (!(module.getGrade().equals(" "))) {
                completedMCs += Integer.parseInt(module.getModularCredits());
            } else {
                listOfCoreModules.remove(module);
            }
        }
        remainingMCs = requiredMCs - completedMCs;
        Print.printModuleTypeRequirements(listOfCoreModules, completedMCs, remainingMCs, requiredMCs, moduleType);
    }
}

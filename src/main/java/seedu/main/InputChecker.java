package seedu.main;

import java.util.ArrayList;

public class InputChecker {

    public static final String GRADE_A_PLUS = "A+";
    public static final String GRADE_A = "A";
    public static final String GRADE_A_MINUS = "A-";
    public static final String GRADE_B_PLUS = "B+";
    public static final String GRADE_B = "B";
    public static final String GRADE_B_MINUS = "B-";
    public static final String GRADE_C_PLUS = "C+";
    public static final String GRADE_C = "C";
    public static final String GRADE_D_PLUS = "D+";
    public static final String GRADE_D =  "D";
    public static final String GRADE_F = "F";
    public static final String GRADE_SATISFACTORY = "S";
    public static final String GRADE_UNSATISFACTORY = "U";
    public static final String GRADE_COMPLETED_SATISFACTORILY = "CS";
    public static final String GRADE_COMPLETED_UNSATISFACTORILY = "CU";
    private static final String SEMESTER_ONE = "1";
    private static final String SPECIAL_TERM_ONE = "1.5";
    private static final String SEMESTER_TWO = "2";
    private static final String SPECIAL_TERM_TWO = "2.5";
    private static final int YEAR_LOWER_BOUND = 1;
    private static final int YEAR_UPPER_BOUND = 4;
    private static final int MODULE_CODE_LOWER_BOUND = 6;
    private static final int MODULE_CODE_UPPER_BOUND = 10;
    private static final String EMPTY_STRING = "";
    private static final int MODULE_CREDIT_LOWER_BOUND = 0;
    private static final int MODULE_CREDIT_UPPER_BOUND = 41;
    private static final String INVALID_MODULE_CODE = "fail";
    private static final String ERROR_MESSAGE_FORMATTING_SPACES = "     ";

    public InputChecker () {

    }

    //@@author saintzaw
    /**
     * Checks all the fields of the user command, to ensure there are no empty fields.
     *
     * @param userCommands The input that the user keyed in.
     * @throws MainException if the user input is invalid.
     */
    public void checkBlankUserInput(String[] userCommands) throws MainException {
        for (String userCommand : userCommands) {
            if (userCommand.isBlank()) {
                throw new MainException("User input cannot be empty for any parameter");
            }
        }
    }

    /**
     * It will display the description of the feature as requested by the
     * user's input.
     *
     * @param userCommands the feature the user wants to find out more about
     */
    //@@author anthea-pr0g
    public void checkManPageInput(String[] userCommands) {
        assert userCommands.length == 2;

        switch (userCommands[1]) {
        case "ADD":
            ManPage.printManPageAdd();
            break;
        case "LIST":
            ManPage.printManPageList();
            break;
        case "EDIT":
            ManPage.printManPageEdit();
            break;
        case "FIND":
            ManPage.printManPageFind();
            break;
        case "TRACK":
            ManPage.printManPageTrack();
            break;
        case "DELETE":
            ManPage.printManPageDelete();
            break;
        case "CALCULATOR":
            ManPage.printManPageCalculator();
            break;
        case "GRADE":
            ManPage.printManPageInputGrade();
            break;
        case "BYE":
            ManPage.printManPageExit();
            break;
        default:
            Print.printWrongManPageField();
            break;
        }
    }

    //@@author geraldkoh4
    /**
     * Checks all the fields of addInput, which is the input captured when add command is called by user.
     *
     * @param listOfModules The currently stored list of modules. Can be loaded or during the session.
     * @param userCommands The input that the user keyed in.
     * @throws MainException if the user input is invalid.
     */
    public void checkAddInput(ModuleList listOfModules, String[] userCommands) throws MainException {
        //check for correct number of fields
        int numberOfFields = 6;
        checkNumberOfFields(numberOfFields, userCommands);
        assert userCommands.length == 6 : "Expected 6 fields";

        try {
            checkBlankUserInput(userCommands);
        } catch (MainException e) {
            Print.printErrorMessage(e);
        }

        //check for non-empty and correct number of characters in the moduleCode field
        String[] moduleList = userCommands[1].trim().split(" ");
        try {
            checkAddInputCorrectModuleCode(moduleList);
        } catch (MainException e) {
            Print.printErrorMessage(e);
            Print.printAddingCorrectModuleCode();
            // returns sanitised list
            moduleList = lazyDeleteInvalidInputsForModuleList(moduleList);
        }
        //check for correct field in MC
        checkAddInputCorrectModularCreditField(userCommands);

        //check for correct field in type of module
        checkAddInputCorrectTypeOfModule(userCommands);

        //check for correct year and semester
        checkAddInputYearAndSemester(userCommands);

        // add all the modules and checks for duplicates
        addModuleWithChecks(moduleList,listOfModules,userCommands);
    }

    //@@author geraldkoh4
    /**
     * Checks the moduleCode section of addInput command.
     * Currently checks for empty strings.
     * Currently checks for the correct number of parameters. [6-10]
     * Currently checks for the entire string to be alphanumeric.
     *
     * @param moduleList the moduleCode field split into the respective fields and stored in an array
     * @throws MainException if user command is invalid
     */
    private void checkAddInputCorrectModuleCode(String[] moduleList) throws MainException {
        for (int i = 0; i < moduleList.length; i++) {
            if (moduleList[i].equals(EMPTY_STRING)) {
                throw new MainException("Module Code cannot be empty");
            }
            if (moduleList[i].trim().length() < MODULE_CODE_LOWER_BOUND) {
                throw new MainException("Module Code cannot be less than 6 characters!");
            }
            if (moduleList[i].length() > MODULE_CODE_UPPER_BOUND) {
                throw new MainException("Module Code cannot be more than 10 characters!");
            }
            checkAlphanumeric(moduleList[i]);
        }
    }

    //@@author geraldkoh4
    /**
     * Checks the modularCredit section of addInput command.
     * Currently checks for the correct modular credit of [0-41]
     * Currently checks for the modular credit to be an integer.
     *
     * @param userCommands the user input split into the respective fields and stored in an array
     * @throws MainException if user command is invalid
     */
    private void checkAddInputCorrectModularCreditField(String[] userCommands) throws MainException {
        try {
            int moduleCredits = Integer.parseInt(userCommands[2].trim());
            if (moduleCredits < MODULE_CREDIT_LOWER_BOUND || moduleCredits > MODULE_CREDIT_UPPER_BOUND) {
                throw new MainException("Make sure Modular Credits is an integer with no decimal place from 0-41");
            }
        } catch (NumberFormatException e) {
            throw new MainException("Make sure Modular Credits is an integer with no decimal place from 0-41\n"
                    + ERROR_MESSAGE_FORMATTING_SPACES
                    + "Example: 4.0 is not accepted as a valid input!");
        }
    }

    //@@author geraldkoh4
    /**
     * Checks the moduleType section of addInput command.
     * Currently checks for whether they belong to [Core, UE, GE, Internship]
     *
     * @param userCommands the user input split into the respective fields and stored in an array
     * @throws MainException if user command is invalid
     */
    private void checkAddInputCorrectTypeOfModule(String[] userCommands) throws MainException {
        String typeOfModule = userCommands[3].trim();
        boolean isCorrectModuleType = typeOfModule.equals("CORE")
                || typeOfModule.equals("UE")
                || typeOfModule.equals("GE")
                || typeOfModule.equals("INTERNSHIP") ;
        if (!isCorrectModuleType) {
            throw new MainException("Incorrect Module Type, Accepted Module Types are: (CORE, UE ,GE ,INTERNSHIP)");
        }
    }

    //@@author geraldkoh4
    /**
     * Checks the listOfModules to ensure that there are no duplicates.
     *
     * @param moduleCode The unique identifier of modules.
     * @param listOfModules The currently stored list of modules.
     * @throws MainException if the user input is invalid.
     */
    public void checkAddInputNoDuplicates(String moduleCode, ArrayList<Module> listOfModules) throws MainException {
        for (Module module : listOfModules) {
            if (module.getModuleCode().equals(moduleCode)) {
                throw new MainException(moduleCode + " is already in your module plan");
            }
        }
    }

    //@@author geraldkoh4
    /**
     * Checks the year and semester section of addInput command.
     * Currently checks for year being [1-4].
     * Currently checks for semester being [1, 1.5, 2, 2.5].
     * Currently checks for year being an integer.
     *
     * @param userCommands the user input split into the respective fields and stored in an array
     * @throws MainException if user command is invalid
     */
    private void checkAddInputYearAndSemester(String[] userCommands) throws MainException {
        try {
            int year = Integer.parseInt(userCommands[4].trim());
            if (year < YEAR_LOWER_BOUND || year > YEAR_UPPER_BOUND) {
                throw new MainException("Make sure Year of Study is an integer with no decimal place from 1-4");
            }
            String semester = userCommands[5].trim();
            boolean isCorrectSemester = semester.equals(SEMESTER_ONE)
                    || semester.equals(SPECIAL_TERM_ONE)
                    || semester.equals(SEMESTER_TWO)
                    || semester.equals(SPECIAL_TERM_TWO);
            if (!isCorrectSemester) {
                throw new MainException("Make sure Semester is 1, 1.5 (Special Term 1), 2 or 2.5 (Special Term 2)"
                        + ", with no additional decimal places\n"
                        + ERROR_MESSAGE_FORMATTING_SPACES
                        + "Example: 1.0, 1.50, 2.0, 2.50 is not accepted as a valid input!");
            }
        } catch (NumberFormatException e) {
            throw new MainException("Make sure Year of Study is an integer with no decimal place from 1-4\n"
                    + ERROR_MESSAGE_FORMATTING_SPACES
                    + "Example: 1.0 is not accepted as a valid input!");
        }
    }

    //@@author geraldkoh4
    /**
     * Checks for the correct number of fields for the respective command
     *
     * @param numberOfFields The number of fields that the user should input for the command
     * @param userCommands the user input split into the respective fields and stored in an array
     * @throws MainException if user command is invalid
     */
    public void checkNumberOfFields(int numberOfFields, String[] userCommands) throws MainException {
        if (userCommands.length > numberOfFields) {
            throw new MainException("Too many fields, use the '/' character only for fields/flags!");
        } else if (userCommands.length < numberOfFields) {
            throw new MainException("Missing fields");
        }
    }

    //@@author geraldkoh4
    /**
     * Checks for correct inputs for module code, discards invalid inputs.
     * The invalid inputs are lazy deleted with "fail".
     * Returns the list of corrected inputs.
     *
     * @param moduleList The broken down array of module codes
     * @return sanitisedModuleList The broken down array of module codes, but with only the correct inputs remaining.
     */
    private String[] lazyDeleteInvalidInputsForModuleList(String[] moduleList) {
        String[] sanitisedModuleList = moduleList;
        for (int i = 0; i < sanitisedModuleList.length; i++) {
            boolean unacceptedInput = sanitisedModuleList[i].equals(EMPTY_STRING)
                    || sanitisedModuleList[i].trim().length() < MODULE_CODE_LOWER_BOUND
                    || sanitisedModuleList[i].trim().length() > MODULE_CODE_UPPER_BOUND
                    || !sanitisedModuleList[i].trim().matches("^[A-Z0-9]*$");

            if (unacceptedInput) {
                sanitisedModuleList[i] = INVALID_MODULE_CODE;
            }
        }
        return sanitisedModuleList;
    }

    //@@author geraldkoh4
    /**
     * Checks for duplicate before adding each module.
     *
     * @param moduleList This is the list of module codes.
     * @param listOfModules This is the list of our currently stored modules.
     * @param userCommands This the user command.
     */
    private void addModuleWithChecks(String[] moduleList, ModuleList listOfModules, String[] userCommands) {
        for (String moduleCode: moduleList) {
            if (moduleCode.equals(INVALID_MODULE_CODE)) {
                continue;
            }
            try {
                checkAddInputNoDuplicates(moduleCode.trim(), listOfModules.getModuleList());
            } catch (MainException e) {
                Print.printErrorMessage(e);
                continue;
            }
            Module addedModule = listOfModules.addModule(moduleCode.trim(), userCommands[2].trim(),
                    userCommands[3].trim(), userCommands[4].trim(), userCommands[5].trim());
            Print.printAddedModule(addedModule, listOfModules.getModuleListSize());
        }
    }

    //@@author saintzaw
    /**
     * Checks the Module Code field of an Edit Module Code command.
     * Currently checks for empty strings.
     * Currently checks for the correct number of characters. [6-10]
     * Currently checks for the entire string to be alphanumeric.
     *
     * @param moduleCode the new unique identifier of the module
     * @throws MainException if user command is invalid
     */
    public void checkEditInputCorrectModuleCode(String moduleCode) throws MainException {
        if (moduleCode.equals(EMPTY_STRING)) {
            throw new MainException("Module Code cannot be empty!");
        }
        if (moduleCode.trim().length() < MODULE_CODE_LOWER_BOUND) {
            throw new MainException("Module Code cannot be less than 6 characters!");
        }
        if (moduleCode.trim().length() > MODULE_CODE_UPPER_BOUND) {
            throw new MainException("Module Code cannot be more than 10 characters!");
        }
        checkAlphanumeric(moduleCode.trim());
    }

    //@@author saintzaw
    /**
     * Checks the Modular Credits field of an Edit Modular Credits command.
     * Currently checks for the correct modular credit of [0-41]
     * Currently checks for the modular credit to be an integer.
     *
     * @param modularCredits the new number of Modular Credits to be prescribed to the module
     * @throws MainException if user command is invalid
     */
    public void checkEditInputCorrectModularCreditField(String modularCredits) throws MainException {
        try {
            int moduleCredits = Integer.parseInt(modularCredits);
            if (moduleCredits < MODULE_CREDIT_LOWER_BOUND || moduleCredits > MODULE_CREDIT_UPPER_BOUND) {
                throw new MainException("Make sure Modular Credits is an integer with no decimal place from 0-41");
            }
        } catch (NumberFormatException e) {
            throw new MainException("Make sure Modular Credits is an integer with no decimal place from 0-41\n"
                    + ERROR_MESSAGE_FORMATTING_SPACES
                    + "Example: 4.0 is not accepted as a valid input!");
        }
    }

    //@@author saintzaw
    /**
     * Checks the Module Type field of an Edit Module Type command.
     * Currently checks for whether they belong to [Core, UE, GE, Internship]
     *
     * @param typeOfModule the user input split into the respective fields and stored in an array
     * @throws MainException if user command is invalid
     */
    public void checkEditInputCorrectTypeOfModule(String typeOfModule) throws MainException {
        boolean isCorrectModuleType = typeOfModule.equals("CORE")
                || typeOfModule.equals("UE")
                || typeOfModule.equals("GE")
                || typeOfModule.equals("INTERNSHIP") ;
        if (!isCorrectModuleType) {
            throw new MainException("Incorrect Module Type, Accepted Module Types are: (CORE, UE ,GE ,INTERNSHIP)");
        }
    }

    //@@author saintzaw
    /**
     * Checks the year field of an Edit Year command.
     * Currently checks for year being [1-4].
     * Currently checks for year being an integer.
     *
     * @param year the updated year that the module is taken or to be taken in
     * @throws MainException if user command is invalid
     */
    public void checkEditInputYear(String year) throws MainException {
        try {
            int newYear = Integer.parseInt(year);
            if (newYear < YEAR_LOWER_BOUND || newYear > YEAR_UPPER_BOUND) {
                throw new MainException("Make sure Year of Study is an integer with no decimal place from 1-4");
            }
        } catch (NumberFormatException e) {
            throw new MainException("Make sure Year of Study is an integer with no decimal place from 1-4\n"
                    + ERROR_MESSAGE_FORMATTING_SPACES
                    + "Example: 1.0 is not accepted as a valid input!");
        }
    }

    //@@author saintzaw
    /**
     * Checks the semester field of an Edit Semester command.
     * Currently checks for semester being [1, 1.5, 2, 2.5].
     *
     * @param semester the updated semester that the module is taken or to be taken in
     * @throws MainException if user command is invalid
     */
    public void checkEditInputSemester(String semester) throws MainException {
        boolean isCorrectSemester = semester.equals(SEMESTER_ONE)
                || semester.equals(SPECIAL_TERM_ONE)
                || semester.equals(SEMESTER_TWO)
                || semester.equals(SPECIAL_TERM_TWO);
        if (!isCorrectSemester) {
            throw new MainException("Make sure Semester is 1, 1.5 (Special Term 1), 2 or 2.5 (Special Term 2)"
                    + ", with no additional decimal places\n"
                    + ERROR_MESSAGE_FORMATTING_SPACES
                    + "Example: 1.0, 1.50, 2.0, 2.50 is not accepted as a valid input!");
        }
    }

    //@@author denzelcjy
    /**
     * Checks if the grade input by the user is a valid grade string.
     *
     * @param inputGrade the string containing grade input by user.
     * @throws MainException when the input grade is invalid / in an invalid command format
     */
    public void checkGradeInput(String inputGrade) throws MainException {
        switch (inputGrade) {
        case GRADE_A_PLUS:
            //Fallthrough
        case GRADE_A:
            //Fallthrough
        case GRADE_A_MINUS:
            //Fallthrough
        case GRADE_B_PLUS:
            //Fallthrough
        case GRADE_B:
            //Fallthrough
        case GRADE_B_MINUS:
            //Fallthrough
        case GRADE_C_PLUS:
            //Fallthrough
        case GRADE_C:
            //Fallthrough
        case GRADE_D_PLUS:
            //Fallthrough
        case GRADE_D:
            //Fallthrough
        case GRADE_F:
            //Fallthrough
        case GRADE_SATISFACTORY:
            //Fallthrough
        case GRADE_UNSATISFACTORY:
            //Fallthrough
        case GRADE_COMPLETED_SATISFACTORILY:
            //Fallthrough
        case GRADE_COMPLETED_UNSATISFACTORILY:
            break;
        default:
            throw new MainException("Invalid command format / invalid grade input! Please follow the format " +
                    "`grade /<module code> /<valid grade>` as per the user guide.");
        }
    }

    /**
     * Checks if the given module code by the user is alphanumeric.
     *
     * @param moduleCode the string containing grade input by user.
     * @throws MainException when the module code input is not alphanumeric.
     */
    public void checkAlphanumeric(String moduleCode) throws MainException {
        boolean isAlphaNumeric = moduleCode.trim().matches("^[A-Z0-9]*$");
        if (!isAlphaNumeric) {
            throw new MainException("Module Code is not alphanumeric!");
        }
    }
}

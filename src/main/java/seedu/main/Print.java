package seedu.main;
import java.util.ArrayList;

public class Print {

    private static final String LINE= "    ____________________________________________________________\n";

    /**
     * Prints a greeting message to the user.
     */
    public static void printHelloMessage(String name) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello " + name + "!");
        System.out.println("     I'm your personal Modganiser.");
        System.out.println("     What would you like to plan today?");
        System.out.println("     To find out more, type `man` and press enter :)");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the Modganiser logo as part of the greeting message to the user.
     */
    public static void printLogo() {
        String logo = "  __  __               _                           _\n" +
                " |  \\/  |   ___     __| |   __ _    __ _   _ __   (_)  ___    ___   _ __\n" +
                " | |\\/| |  / _ \\   / _` |  / _` |  / _` | | '_ \\  | | / __|  / _ \\ | '__|\n" +
                " | |  | | | (_) | | (_| | | (_| | | (_| | | | | | | | \\__ \\ |  __/ | |\n" +
                " |_|  |_|  \\___/   \\__,_|  \\__, |  \\__,_| |_| |_| |_| |___/  \\___| |_|\n" +
                "                           |___/";

        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a message prompting the user for their name.
     */
    public static void promptForName() {
        System.out.println("What is your name?");
    }

    /**
     * Prints a message to user that the given name is invalid.
     */
    public static void printInvalidNameMessage() {
        System.out.println("Please key in a valid name:");
    }

    /**
     * Prints a message to user that finding modules by keyword was successful.
     */
    public static void printFoundModule(ArrayList<Module> foundModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching modules in your module plan:");
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < foundModules.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + foundModules.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that finding modules by keyword was unsuccessful.
     */
    public static void printNoModuleFound(String searchType, String keyword) {
        System.out.println("    ____________________________________________________________");
        if (searchType.equals("CODE")) {
            System.out.println("     There are no modules that match the keyword: " + keyword + " in your module plan");
        } else if (searchType.equals("TYPE")) {
            System.out.println("     There are no "+ keyword + " modules in your module plan");
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that deleting modules was successful.
     */
    public static void printDeletedModule(Module deletedModule, int numOfModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this module:");
        System.out.println("       " + deletedModule);
        System.out.println("     Now you have " + numOfModules + " modules in your module plan.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that deleting modules was unsuccessful.
     */
    public static void printNoDeletedModuleFound(String moduleCode) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     There is no module that matches the module code: " + moduleCode +
                " in your module plan");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that adding module was successful.
     */
    public static void printAddedModule(Module addedModule, int numOfModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this module:");
        System.out.println("       " + addedModule);
        System.out.println("     Now you have " + numOfModules + " modules in your module plan.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that editing module was successful.
     */
    public static void printEditedModule(Module editedModule, int numOfModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've edited the information for this module:");
        System.out.println("       " + editedModule);
        System.out.println("     Now you have " + numOfModules + " modules in your module plan.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the list of modules.
     */
    public static void printModuleList(ArrayList<Module> listOfModules) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the modules in your module plan:");
        for (int i = 0; i < listOfModules.size(); i++) {
            System.out.println("     " + (i + 1) + "." + listOfModules.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the list of modules in the specified year.
     *
     * @param semOneModuleList The list of modules in Semester 1.
     * @param specialTermOneModuleList The list of modules in Special Term 1.
     * @param semTwoModuleList The list of modules in Semester 2.
     * @param specialTermTwoModuleList The list of modules in Special Term 2.
     * @param year The year that the modules are taken or to be taken in. [1-4]
     */
    public static void printModuleListByYear(ArrayList<String> semOneModuleList,
                                             ArrayList<String> specialTermOneModuleList,
                                             ArrayList<String> semTwoModuleList,
                                             ArrayList<String> specialTermTwoModuleList ,String year) {
        //printing the title for the Year
        System.out.println("    __________");
        System.out.println("   | Year " + year + "   |");
        System.out.println("   |__________|");

        //format specifier for each semester column
        String semesterFormat = "       %-28s %-10s %-28s %-10s %-28s %-10s %-28s\n";

        //printing the titles for the semesters
        String semesterUnderline = "===========";
        String specialTermUnderline = "===============";
        String whitespace = " ";
        String columnSeparator = "|";
        System.out.printf(semesterFormat, "Semester 1", whitespace,
                "Special Term 1", whitespace, "Semester 2", whitespace, "Special Term 2");
        System.out.printf(semesterFormat, semesterUnderline, whitespace,
                specialTermUnderline, whitespace, semesterUnderline, whitespace, specialTermUnderline);

        //maxNumOfModules is the largest number of modules in a semester in the given year
        //it is used for the formatting of the length of the table of modules displayed to the user
        int maxNumOfModules = Math.max(Math.max(semOneModuleList.size(), specialTermOneModuleList.size()),
                Math.max(semTwoModuleList.size(), specialTermTwoModuleList.size()));

        for (int i = 0; i < maxNumOfModules; i++) {
            String semOne;
            String specialTermOne;
            String semTwo;
            String specialTermTwo;

            if (i < semOneModuleList.size()) {
                semOne = semOneModuleList.get(i);
            } else {
                //print an empty string if there are no more modules in the list for that semester
                semOne = " ";
            }

            if (i < specialTermOneModuleList.size()) {
                specialTermOne = specialTermOneModuleList.get(i);
            } else {
                //print an empty string if there are no more modules in the list for that semester
                specialTermOne = " ";
            }

            if (i < semTwoModuleList.size()) {
                semTwo = semTwoModuleList.get(i);
            } else {
                //print an empty string if there are no more modules in the list for that semester
                semTwo = " ";
            }

            if (i < specialTermTwoModuleList.size()) {
                specialTermTwo = specialTermTwoModuleList.get(i);
            } else {
                //print an empty string if there are no more modules in the list for that semester
                specialTermTwo = " ";
            }

            //printing the modules in each semester of the specified year
            System.out.printf(semesterFormat, semOne, columnSeparator, specialTermOne, columnSeparator,
                    semTwo, columnSeparator, specialTermTwo);
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that their list of modules is empty for the specified year.
     *
     * @param year The year that the modules are taken or to be taken in. [1-4]
     */
    public static void printEmptyModuleList(String year) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     There are currently no modules in your module plan for Year " + year);
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Prints a farewell message to the user.
     */
    public static void printFarewellMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye! Hope you enjoyed using Modganiser!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints an error message to the user.
     */
    public static void printErrorMessage(MainException e) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Error: " + e.getDescription());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that there was an error in creating the name file.
     */
    public static void printCreateNameFileError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    An error occurred when creating the name file");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that there was an error in creating the modules file.
     */
    public static void printCreateModulesFileError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    An error occurred when creating the modules file");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that there was an error in creating the modules file.
     */
    public static void printLoadingSaveFileError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    An error occurred when loading modules from the save file   ");
        System.out.println("    as it is corrupted.                                         ");
        System.out.println("    Your saved modules could not be retrieved :(                ");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that there was an error in saving the modules.
     */
    public static void printModulesSavingError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Modules were not saved to file");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that there was an error in saving the name.
     */
    public static void printNameSavingError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Name was not saved to file");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that there was an error in loading the modules file.
     */
    public static void printMissingModulesFileError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Modules file was not found");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that there was an error in loading the name file.
     */
    public static void printNameFileLoadingError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Name file was not found");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that updating module grade was successful.
     */
    public static void printUpdatedModuleGrade(Module updatedModule) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've updated the grade for this module:");
        System.out.println("       " + updatedModule);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that updating module grade was unsuccessful.
     */
    public static void printInvalidModule(String moduleCode) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Unable to update the grade for " + moduleCode + " as it's not in your module plan!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that calculating CAP was successful.
     */
    public static void printCalculatedCAP(double calculatedCAP) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     I have calculated your CAP across your graded modules!");
        System.out.println("     Your CAP is currently: " + calculatedCAP + " :)");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that about their graduation requirements.
     *
     * @param plannedModules The list of planned modules.
     * @param plannedMCs The number of Modular Credits planned for the specified Module Type.
     * @param remainingMCs The number of Modular Credits left to be completed for the specified Module Type.
     * @param requiredMCs The total number of Modular to be Credits completed for the specified Module Type.
     * @param moduleType The type of module. [Core, GeneralElective, UnrestrictedElective, Internship]
     */
    public static void printModuleTypeRequirements(ArrayList<Module> plannedModules, int plannedMCs,
                                                        int remainingMCs, int requiredMCs, String moduleType) {
        System.out.println("    ____________________________________________________________");
        if (plannedModules.size() == 0) {
            System.out.println("     You have not planned any " + moduleType + " modules yet.");
            System.out.println("     You need to plan " + remainingMCs + " MCs of " + moduleType + " modules to " +
                    "meet the graduation requirements.");
            System.out.println("    ____________________________________________________________");
        } else {
            System.out.println("     Here are the " + moduleType + " modules that you have planned for so far:");
            for (int i = 0; i < plannedModules.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + plannedModules.get(i));
            }

            if (remainingMCs <= 0) {
                System.out.println("     Congratulations! Your plan meets all the graduation requirements for this " +
                        "module type :)");
            } else {
                System.out.println("     Congratulations! You have planned for " + plannedMCs + " of the "
                        + requiredMCs + " MCs required :)");

                System.out.println("     You need to plan " + remainingMCs + " MCs more to meet the " +
                        "graduation requirements.");
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    /**
     * Prints a message to user that calculating CAP was unsuccessful.
     */
    public static void printNoModulesToCalculateCAP() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Unable to calculate your CAP as there are no modules in your module plan :(");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that calculating CAP was unsuccessful.
     */
    public static void printNoGradedModulesToCalculateCAP() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Unable to calculate your CAP as there are no graded modules that should be counted " +
                "towards your CAP :(");
        System.out.println("     (Note: Modules with grade S, U, CS or CU will not be counted in towards your CAP!)");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that Modganiser is adding other modules with the correct module code
     */
    public static void printAddingCorrectModuleCode() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Attempting to add modules (if any) with the correct module code!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a message to user that they have inputted the wrong inputs for manpage
     */
    public static void printWrongManPageField() {

        System.out.println(LINE + "     Incorrect field for the ManPage Command, \n" +
                "     Check our command summary again by typing `man`\n" + LINE);
    }

}

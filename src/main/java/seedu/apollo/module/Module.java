package seedu.apollo.module;

/**
 * Class representing a Module.
 * Contains the Module's code and name.
 */
public class Module {
    private static String moduleCode;
    private static String moduleName;


    /**
     * Initialises the Module with its corresponding code and name.
     *
     * @param moduleCode    The code of the module e.g. CS2113
     * @param moduleName    The name of the module e.g. Software Engineering and Object-Oriented Programming
     */
    public Module(String moduleCode, String moduleName) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }


    /**
     * Retrieves a String with the module's code.
     *
     * @return String of the module code
     */
    public static String getModuleCode() {
        return moduleCode;
    }

    /**
     * Retrieves a String with the module's name.
     *
     * @return String of the module name
     */
    public static String getModuleName() {
        return moduleName;
    }

    /**
     * Prints out the Module in desired format.
     */
    @Override
    public String toString() {
        return moduleCode + ": " + moduleName;
    }
}

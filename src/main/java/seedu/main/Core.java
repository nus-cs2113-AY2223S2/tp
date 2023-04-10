package seedu.main;

/**
 * Core is a subclass of Modules.
 * It has a custom moduleType and toString method.
 */

public class Core extends Module {

    /**
     * Constructs a Core object that inherits from Module object.
     *
     * @param moduleCode     the string containing the name of the module code.
     * @param modularCredits the string containing the number of modular credits given upon completion of the module.
     * @param year           the string containing the year that the user plans to take this module.
     * @param semester       the string containing the semester that the user plans to take this module.
     */
    public Core(String moduleCode, String modularCredits, String year, String semester) {
        super(moduleCode, modularCredits, year, semester);
    }

    /**
     * Returns the letter that signifies the moduleType of Core.
     * Overrides the parent implementation.
     *
     * @return The letter that signifies a Core module.
     */
    @Override
    public String getModuleType() {
        return "C";
    }

    /**
     * Formats the proper string to be printed when the Core Module object is referenced.
     * Overrides the parent implementation.
     *
     * @return The correctly formatted string of the Core Module object.
     */
    @Override
    public String toString() {
        return "[C]" + super.toString();
    }
}

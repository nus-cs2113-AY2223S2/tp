package seedu.main;

/**
 * GeneralElective is a subclass of Modules.
 * It has a custom moduleType and toString method.
 */
public class GeneralElective extends Module {

    /**
     * Constructs a GeneralElective object that inherits from Module object.
     *
     * @param moduleCode     the string containing the name of the module code.
     * @param modularCredits the string containing the number of modular credits given upon completion of the module.
     * @param year           the string containing the year that the user plans to take this module.
     * @param semester       the string containing the semester that the user plans to take this module.
     */
    public GeneralElective(String moduleCode, String modularCredits, String year, String semester) {
        super(moduleCode, modularCredits, year, semester);
    }

    /**
     * Returns the letter that signifies the moduleType of GeneralElective.
     * Overrides the parent implementation.
     *
     * @return The letter that signifies a GeneralElective module.
     */
    @Override
    public String getModuleType() {
        return "GE";
    }

    /**
     * Formats the proper string to be printed when the GeneralElective Module object is referenced.
     * Overrides the parent implementation.
     *
     * @return The correctly formatted string of the GeneralElective Module object.
     */
    @Override
    public String toString() {
        return "[GE]" + super.toString();
    }
}

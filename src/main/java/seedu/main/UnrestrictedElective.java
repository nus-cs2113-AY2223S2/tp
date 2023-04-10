package seedu.main;

/**
 * UnrestrictedElective is a subclass of Modules.
 * It has a custom moduleType and toString method.
 */
public class UnrestrictedElective extends Module {

    /**
     * Constructs a UnrestrictedElective object that inherits from Module object.
     *
     * @param moduleCode the string containing the name of the module code.
     * @param modularCredits the string containing the number of modular credits given upon completion of the module.
     * @param year the string containing the year that the user plans to take this module.
     * @param semester the string containing the semester that the user plans to take this module.
     */
    public UnrestrictedElective(String moduleCode, String modularCredits, String year, String semester) {
        super(moduleCode, modularCredits, year, semester);
    }

    /**
     * Returns the letter that signifies the moduleType of UnrestrictedElective.
     * Overrides the parent implementation.
     *
     * @return The letter that signifies an UnrestrictedElective module.
     */
    @Override
    public String getModuleType() {
        return "UE";
    }

    /**
     * Formats the proper string to be printed when the UnrestrictedElective Module object is referenced.
     * Overrides the parent implementation.
     *
     * @return The correctly formatted string of the UnrestrictedElective Module object.
     */
    @Override
    public String toString() {
        return "[UE]" + super.toString();
    }
}

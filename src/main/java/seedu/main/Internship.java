package seedu.main;

/**
 * Internship is a subclass of Modules.
 * It has a custom moduleType and toString method.
 */

//@@author anthea-pr0g
public class Internship extends Module {

    /**
     * A module type called Internship
     *
     * @param moduleCode     The module code
     * @param modularCredits The module credits of the module stated
     * @param year           The year the user wants the module to be placed in
     * @param semester       The semester the user wants the module to be placed in
     */
    public Internship(String moduleCode, String modularCredits, String year, String semester) {
        super(moduleCode, modularCredits, year, semester);
    }

    /**
     * Returns the letter that signifies the moduleType of Internship.
     * Overrides the parent implementation.
     *
     * @return The letter that signifies an Internship module.
     */
    @Override
    public String getModuleType() {
        return "I";
    }

    /**
     * Formats the proper string to be printed when the Internship Module object is referenced.
     * Overrides the parent implementation.
     *
     * @return The correctly formatted string of the Internship Module object.
     */
    @Override
    public String toString() {
        return "[I]" + super.toString();
    }
}

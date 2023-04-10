package seedu.main;

/**
 * Superclass of ModuleTypes: Core, GeneralElective, UnrestrictedElective, Internship
 */
public class Module {
    private String moduleCode;
    private String modularCredits;
    private String year;
    private String semester;
    private String grade;

    /**
     * Constructor for the subclasses: Core, GeneralELective, UnrestrictedElective, Internship
     *
     * @param moduleCode     The identifier for modules. Must be unique. Uppercase.
     * @param modularCredits The amount of credits prescribed to the module.
     * @param year           The year the module is taken or to be taken in.[year 1-6]
     * @param semester       The semester the module is taken or to be taken in.[Semesters 1, 1.5, 2, 2.5]
     */
    public Module(String moduleCode, String modularCredits, String year, String semester) {
        this.moduleCode = moduleCode;
        this.modularCredits = modularCredits;
        this.year = year;
        this.semester = semester;
        this.grade = " ";
    }

    /**
     * Gets the module code.
     *
     * @return The module code of the fetched module.
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Gets the modular credits.
     *
     * @return The amount of credits given to the module.
     */
    public String getModularCredits() {
        return modularCredits;
    }

    /**
     * Gets the year of the module.
     *
     * @return The year of the module.
     */
    public String getYear() {
        return year;
    }

    /**
     * Gets the semester of the module.
     *
     * @return The semester of the module.
     */
    public String getSemester() {
        return semester;
    }

    /**
     * To be used by subclasses. Returns the type of the module.
     *
     * @return Only to be used by subclasses. Returns empty string if used by module.
     */
    public String getModuleType() {
        return "";
    }

    /**
     * Sets modular credits of a module.
     *
     * @param newModularCredits The new updated modularCredits which will replace the old modularCredit.
     */
    public void setModularCredits(String newModularCredits) {
        this.modularCredits = newModularCredits;
    }

    /**
     * Sets semester of a module.
     *
     * @param newSemester The new updated semester which will replace the old semester.
     */
    public void setSemester(String newSemester) {
        this.semester = newSemester;
    }

    /**
     * Sets year of a module.
     *
     * @param newYear The new updated year which will replace the old year.
     */
    public void setYear(String newYear) {
        this.year = newYear;
    }

    /**
     * Gets the grade of a module.
     *
     * @return The grade that the module currently has. Will be empty if not graded yet.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the grade of a module.
     *
     * @param grade The new updated grade of the module which will replace the old grade.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Prints the relevant information of the module. To be used by subclasses only.
     *
     * @return The relevant string describing the module's stored information. It will be printed for the user.
     */
    @Override
    public String toString() {
        return "[\"" + this.grade + "\"] " + this.moduleCode + " " + this.modularCredits + " MCs"
                + " (Year: " + this.year + ", Sem: " + this.semester + ")";
    }
}

package seedu.duke;

public class Module {
    private String moduleCode;
    private String modularCredits;

    public Module(String moduleCode, String modularCredits) {
        this.moduleCode = moduleCode;
        this.modularCredits = modularCredits;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    @Override
    public String toString() {
        return this.moduleCode + " " + this.modularCredits + " MCs";
    }
}

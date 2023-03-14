package seedu.duke;

public class UnrestrictedElective extends Module {

    public UnrestrictedElective (String moduleCode, String modularCredits) {
        super(moduleCode, modularCredits);
    }

    @Override
    public String toString() {
        return "[UE] " + super.toString();
    }
}

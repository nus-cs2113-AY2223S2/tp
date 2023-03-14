package seedu.duke;

public class GeneralElective extends Module {

    public GeneralElective (String moduleCode, String modularCredits) {
        super(moduleCode, modularCredits);
    }

    @Override
    public String toString() {
        return "[GE] " + super.toString();
    }
}

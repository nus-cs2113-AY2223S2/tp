package seedu.duke;

public class Core extends Module{

    public Core (String moduleCode, String modularCredits) {
        super(moduleCode, modularCredits);
    }

    @Override
    public String toString() {
        return "[C] " + super.toString();
    }
}

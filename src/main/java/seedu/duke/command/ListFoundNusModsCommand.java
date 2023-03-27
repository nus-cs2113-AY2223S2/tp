package seedu.duke.command;

import seedu.duke.Module;
import seedu.duke.University;

import java.util.ArrayList;

public class ListFoundNusModsCommand extends Command {
    private String nusModCode;
    private ArrayList<Module> foundNusModList;
    private ArrayList<University> universities;

    public ListFoundNusModsCommand(String nusModCode, ArrayList<Module> foundNusModList,
                                   ArrayList<University> universities) {
        this.foundNusModList = foundNusModList;
        this.nusModCode = nusModCode;
        this.universities = universities;
    }
    @Override
    public void execute() {
        ui.printFoundNusModules(foundNusModList, nusModCode, universities);
        ui.getLine();
    }
}

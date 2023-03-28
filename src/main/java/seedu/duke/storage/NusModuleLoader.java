package seedu.duke.storage;

import seedu.duke.NusModule;

import java.util.HashMap;

public interface NusModuleLoader {
    HashMap<String, NusModule> loadModules();
}

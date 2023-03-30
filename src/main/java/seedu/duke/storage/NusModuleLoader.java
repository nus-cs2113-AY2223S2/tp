package seedu.duke.storage;

import seedu.duke.NusModule;

import java.util.HashMap;

/**
 * Represents Storage for nus modules.
 */
public interface NusModuleLoader {
    HashMap<String, NusModule> loadModules();
}

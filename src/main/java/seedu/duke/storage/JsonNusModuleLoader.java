package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.NusModule;
import seedu.duke.Ui;


import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Objects;

/**
 * A class to access NusMods data stored on a json file.
 */
public class JsonNusModuleLoader implements NusModuleLoader{

    private static final String nusModFile = "/nusmods.json";
    GsonBuilder builder = new GsonBuilder().registerTypeAdapter(HashMap.class, new ModuleAdapter())
            .setPrettyPrinting();

    Gson gson = builder.create();

    /**
     * Loads and deserializes information from nusMods.json into a HashMap for quick reference by other components.
     * @return HashMap with String key and NusModule objects as value.
     */
    public HashMap<String, NusModule> loadModules(){
        HashMap<String, NusModule> nusModuleHashMap = new HashMap<>();
        try{
            Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/nusmods.json")));
            nusModuleHashMap = gson.fromJson(reader, HashMap.class);
        } catch (Exception e){
            Ui.printErrorMsg("FileNotFound");
        }

        return nusModuleHashMap;
    }
}

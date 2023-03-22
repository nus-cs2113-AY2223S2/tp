package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.NusModule;
import seedu.duke.Ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class NusmodConverter {

    private static final String nusModFile = System.getProperty("user.dir")+"/src/main/data/nusmods.json";
    GsonBuilder builder = new GsonBuilder().registerTypeAdapter(HashMap.class, new ModuleAdapter())
            .setPrettyPrinting();

    Gson gson = builder.create();

    public HashMap<String, NusModule> loadModules(){
        File loadFile = new File(nusModFile);
        HashMap<String, NusModule> nusModuleHashMap = new HashMap<>();
        if (!loadFile.exists()){
            return nusModuleHashMap;
        }
        InputStreamReader fileReader;
        try{
            fileReader = new InputStreamReader(new FileInputStream(nusModFile), StandardCharsets.UTF_8);
            nusModuleHashMap = gson.fromJson(fileReader, HashMap.class);
        } catch (FileNotFoundException e){
            Ui.printErrorMsg("FileNotFound");
        }
        return nusModuleHashMap;
    }
}

package seedu.duke.storage;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import seedu.duke.NPExceptions;
import seedu.duke.NusModule;

class NusModuleStorageTest {
    private final JsonNusModuleLoader converter = new JsonNusModuleLoader();

    @Test
    public void loadMods() throws NPExceptions {
        HashMap<String, NusModule> nusmods = converter.loadModules();
        System.out.println("CS2113 Details:");
        NusModule cs2113 = nusmods.get("CS2113");
        System.out.println(cs2113.getDescription());
        System.out.println(cs2113.getFaculty());
        System.out.println(cs2113.getLesson(2, "LEC", "1"));
        System.out.println(nusmods.get("CS2113").getLesson(2, "TUT", "13").toString());
        System.out.println("========================================================");
        System.out.println("CG2023 Details:");
        NusModule cg2023 = nusmods.get("CG2023");
        System.out.println(cg2023.getDescription());
        System.out.println(cg2023.getFaculty());
        System.out.println(cg2023.getLesson(2,"LEC", "02"));
        System.out.println("================================================================");
        NusModule cs3244 = nusmods.get("CS3243"); //test special sem modules.
        System.out.println(cs3244.getLesson(3, "LEC", "1"));
        System.out.println(nusmods.get("CS3243").getLesson(3, "TUT", "01").toString());
    }



}

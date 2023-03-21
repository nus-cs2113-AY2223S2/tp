package seedu.duke.storage;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import seedu.duke.NusModule;

class NusmodConverterTest {
    private final NusmodConverter converter = new NusmodConverter();

    @Test
    public void loadMods(){
        HashMap<String, NusModule> nusmods = converter.loadModules();
        System.out.println("CS2113 Details:");
        System.out.println(nusmods.get("CS2113").getDescription());
        System.out.println(nusmods.get("CS2113").getFaculty());
        System.out.println("========================================================");
        System.out.println("CG2023 Details:");
        System.out.println(nusmods.get("CG2023").getTitle());
    }

}

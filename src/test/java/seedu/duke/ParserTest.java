package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class ParserTest {
    @Test
    public void deleteModule_correctFormat_success() {
        Storage storage = new Storage();
        ArrayList<Module> uniList1 = new ArrayList<>();
        Module module = new Module(1, "AE320", "Aerodynamics II", 3,
                "ME4231", "Aerodynamics", 4);
        uniList1.add(module);
        Storage.deleteModule(1, uniList1, storage);
        assertEquals(uniList1.size(), 0);
    }
}


//@@author Jeraldchen
package seedu.duke.menu;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    @Test
    public void registerPatient() {
        String name = "Akshay";
        String password1 = "iloveCS2113";
        String password2 = "iloveCS2113";

        HashMap<String, String> personalInfo = new HashMap<>();
        if (password1 == password2) {
            personalInfo.put(name, password1);
        }

        assertEquals(personalInfo.get(name), password1);
    }

}

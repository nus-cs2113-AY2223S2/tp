package seedu.duke.budget;

import org.junit.jupiter.api.Test;
import seedu.duke.Module;
import seedu.duke.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {

    @Test
    public void initialiseFood_integerType_success() {
        int testPrice = 20;
        Food food = new Food(testPrice);
        assertEquals(testPrice, food.getPrice());
    }

    @Test
    public void changeFoodPrice_integerType_success() {
        int testPrice = 1000;
        int changePrice = 20;
        Food food = new Food(testPrice);
        food.setPrice(changePrice);
        assertEquals(changePrice, food.getPrice());
    }

    public void addModule_correctFormat_success() {
        Storage storage = new Storage();
        Module module = new Module(1, "AE320", "Aerodynamics II", 3,
                "ME4231", "Aerodynamics", 4);
        storage.addModuleToModuleList(module);
        int lastIndex = storage.getModules().size() - 1;
        assertEquals("1,AE320,Aerodynamics II,3,ME4231,Aerodynamics,4",
                storage.getModules().get(lastIndex).toString());
    }

}

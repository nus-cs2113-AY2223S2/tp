package seedu.mealcompanion.recipe;

import org.junit.jupiter.api.Test;

class IngredientDatabaseTest {
    @Test
    void createIngredientDatabase() {
        IngredientDatabase.getDbInstance();
    }
}

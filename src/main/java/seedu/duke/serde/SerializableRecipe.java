package seedu.duke.serde;

import seedu.duke.DukeException;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.recipe.IngredientDatabase;
import seedu.duke.recipe.Instruction;
import seedu.duke.recipe.InstructionList;
import seedu.duke.recipe.Recipe;

import java.util.List;

public class SerializableRecipe {
    private String name;
    private List<SerializableIngredient> ingredients;
    private List<String> instructions;

    public Recipe toRecipe() throws DukeException {
        IngredientList ingredientList = new IngredientList();
        for(SerializableIngredient ingredient : ingredients) {
            IngredientDatabase db = IngredientDatabase.getDbInstance();
            if (!db.getKnownIngredients().containsKey(ingredient.getName())) {
                throw new DukeException("Unknown ingredient in recipe book: " + ingredient.getName());
            }
            ingredientList.add(new Ingredient(ingredient.getName(), ingredient.getAmount()));
        }

        InstructionList instructionList = new InstructionList();
        for (String instruction : instructions) {
            instructionList.add(new Instruction(instruction));
        }
        return new Recipe(this.name, ingredientList, instructionList);
    }
}

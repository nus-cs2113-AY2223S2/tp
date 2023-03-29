package seedu.duke.recipe;

import seedu.duke.ui.UI;

import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> list;

    protected int currIngredientNumber;

    UI ui = new UI();
    /**
     * Class constructor without arguments.
     */
    public IngredientList() {
        list = new ArrayList<>();
        currIngredientNumber = 0;
    }

    /**
     * Class constructor with argument. An existing array list of ingredients
     * is used as the argument
     *
     * @param ingredientList - list of all ingredients in the recipe.
     */
    public IngredientList(ArrayList<Ingredient> ingredientList) {
        list = ingredientList;
        currIngredientNumber = ingredientList.size();
    }

    public int getCurrIngredientNumber() {
        return currIngredientNumber;
    }

    /**
     * Adds a new ingredient to the list.
     *
     * @param item - the ingredient to be added to the list.
     */
    private void addIngredient(Ingredient item) {
        list.add(item);
        currIngredientNumber++;
    }

    /**
     * Removes a new ingredient to the list.
     *
     * @param index - the index of the ingredient to be removed from the list.
     */
    private void removeIngredient(int index) {
        list.remove(index-1);
        currIngredientNumber--;
    }
    public void showList() {
        System.out.println("Here are " + currIngredientNumber + " ingredients in the list:");
        for (int i = 0; i < currIngredientNumber; i++) {
            System.out.println((i + 1) + ". " + list.get(i).getName());
        }
    }
    public ArrayList<Ingredient> getList() {
        return list;
    }

    public void editIngredient(int ingredientIndex) {
        System.out.println("Enter the description of the ingredient:");
        String description = ui.readCommand();
        Ingredient newIngredient = new Ingredient(description);
        list.set(ingredientIndex, newIngredient);
        System.out.println("Ingredient has been edited:");
        System.out.print((ingredientIndex + 1) + ". ");
        System.out.println(list.get(ingredientIndex).getName());
    }
}

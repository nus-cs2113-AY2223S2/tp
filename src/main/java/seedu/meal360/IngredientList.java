package seedu.meal360;

import java.util.HashMap;
import java.time.LocalDate;
import seedu.meal360.exceptions.IngredientNotFoundException;

public class IngredientList extends HashMap<String, Ingredient> {

    private static final Parser parser = new Parser();

    // public method to find count of ingredient
    public Integer findIngredientCount(String ingredientName) throws IngredientNotFoundException {
        if (this.containsKey(ingredientName)) {
            return this.get(ingredientName).ingredientCount;
        }
        throw new IngredientNotFoundException("Ingredient not found");
    }

    // public method to find expiry date of ingredient
    public LocalDate findExpiryDate(String ingredientName) throws IngredientNotFoundException {
        if (this.containsKey(ingredientName)) {
            return this.get(ingredientName).expiryDate;
        }
        throw new IngredientNotFoundException("Ingredient not found");
    }

    /**
     * Adds an ingredient to the ingredient list. If the ingredient already exists, the count of the
     * ingredient is updated, and the expiry date is also updated.
     *
     * @author jaredoong
     * @param ingredient Ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient) {
        if (this.containsKey(ingredient.ingredientName)) {
            int currentCount = this.get(ingredient.ingredientName).ingredientCount;
            int newCount = currentCount + ingredient.ingredientCount;
            this.put(ingredient.ingredientName,
                    new Ingredient(ingredient.ingredientName, newCount, ingredient.expiryDate));
        } else {
            this.put(ingredient.ingredientName, ingredient);
        }
    }

    public void editIngredient(Ingredient ingredient, Integer ingredientCount, String expiryDate)
            throws IngredientNotFoundException {
        if (this.containsKey(ingredient.ingredientName)) {
            ingredient.ingredientCount = ingredientCount;
            ingredient.expiryDate = parser.parseDate(expiryDate);
            ingredient.isExpired = ingredient.expiryDate.isBefore(LocalDate.now());
            super.put(ingredient.ingredientName, ingredient);
        }
        throw new IngredientNotFoundException("Ingredient not found");
    }

    /**
     * Deletes an ingredient from the ingredient list. If the ingredient count is greater than the count to be
     * deleted, the count of the ingredient is updated. If the ingredient count is equal or less than to the
     * count to be deleted, the ingredient is removed from the list.
     *
     * @author jaredoong
     * @param ingredient Ingredient to be deleted.
     * @throws IngredientNotFoundException If the ingredient is not found in the list.
     */
    public void deleteIngredient(Ingredient ingredient) throws IngredientNotFoundException {
        String ingredientName = ingredient.ingredientName;
        int ingredientCount = ingredient.ingredientCount;

        if (this.containsKey(ingredientName)) {
            int currentCount = this.get(ingredientName).ingredientCount;
            int newCount = currentCount - ingredientCount;
            if (newCount > 0) {
                this.put(ingredientName,
                        new Ingredient(ingredientName, newCount, this.get(ingredientName).expiryDate));
            } else {
                this.remove(ingredientName);
            }
        } else {
            throw new IngredientNotFoundException("Ingredient not found");
        }
    }

    // private indexed list of ingredients, count and expiry date
    private String listIngredients() {
        String ingredientList = "";
        int index = 1;
        for (Ingredient ingredient : this.values()) {
            ingredientList += String.format("%d. %s (count: %d, expires on %s)", index,
                    ingredient.ingredientName, ingredient.ingredientCount, ingredient.expiryDate);
        }
        return ingredientList;
    }

    // public method to print ingredients in list with indexing
    public void printIngredients() {
        System.out.println(listIngredients());
    }

    // public method to print expired ingredients and expiry date
    // if no expired ingredients, print "No expired ingredients"
    public void printExpiredIngredients() {
        String expiredIngredients = "";
        int index = 1;
        for (Ingredient ingredient : this.values()) {
            if (ingredient.isExpired) {
                expiredIngredients += String.format("%d. %s (expires on %s)", index,
                        ingredient.ingredientName, ingredient.expiryDate);
            }
        }
        if (expiredIngredients.equals("")) {
            System.out.println("No expired ingredients.");
        } else {
            System.out.println(expiredIngredients);
        }
    }

    // public method to clear all ingredients
    public void clearIngredients() {
        this.clear();
    }

    // public method to clear expired ingredients
    public void clearExpiredIngredients() {
        for (Ingredient ingredient : this.values()) {
            if (ingredient.isExpired) {
                this.remove(ingredient.ingredientName);
            }
        }
    }
}

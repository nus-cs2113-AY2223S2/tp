package seedu.meal360;

import java.util.ArrayList;
import java.time.LocalDate;

public class IngredientList extends ArrayList<Ingredient> {
    private static final Parser parser = new Parser();

    // private exception for ingredient not found
    private class IngredientNotFoundException extends Exception {
        public IngredientNotFoundException() {
            System.out.println("Ingredient not found");
        }
    }

    // public method to find count of ingredient
    public Integer findCount(String ingredientName) {
        try {
            for (Ingredient ingredient : this) {
                if (ingredient.ingredientName.equalsIgnoreCase(ingredientName)) {
                    return ingredient.ingredientCount;
                }
            }
            throw new IngredientNotFoundException();
        } catch (IngredientNotFoundException e) {
            return null;
        }
    }

    // public method to find expiry date of ingredient
    public LocalDate findExpiryDate(String ingredientName) {
        try {
            for (Ingredient ingredient : this) {
                if (ingredient.ingredientName.equalsIgnoreCase(ingredientName)) {
                    return ingredient.expiryDate;
                }
            }
            throw new IngredientNotFoundException();
        } catch (IngredientNotFoundException e) {
            return null;
        }
    }

    public void addIngredient(Ingredient ingredient) {
        super.add(ingredient);
    }

    public void editIngredient(Ingredient ingredient, Integer ingredientCount, String expiryDate) {
        ingredient.ingredientCount = ingredientCount;
        ingredient.expiryDate = parser.parseDate(expiryDate);
    }

    public void deleteIngredient(int ingredientNum) {
        Ingredient ingredientToDelete = super.get(ingredientNum);
        super.remove(ingredientToDelete);
    }

    // private indexed list of ingredients, count and expiry date
    private String listIngredients() {
        String ingredientList = "";
        for (int i = 0; i < this.size(); i++) {
            ingredientList += (i + 1) + ". " + this.get(i).ingredientName + " ("
                    + this.get(i).ingredientCount + " left, expires on "
                    + this.get(i).expiryDate + ")\n";
        }
        return ingredientList;
    }

    // public method to print ingredients in list
    public void printIngredients() {
        System.out.println(listIngredients());
    }

    // public method to print expired ingredients and expiry date
    // if no expired ingredients, print "No expired ingredients"
    public void printExpiredIngredients() {
        String expiredIngredients = "";
        for (Ingredient ingredient : this) {
            if (ingredient.expired) {
                expiredIngredients += ingredient.ingredientName + " (expires on "
                        + ingredient.expiryDate + ")\n";
            }
        }
        if (expiredIngredients.equals("")) {
            System.out.println("No expired ingredients");
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
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).expired) {
                this.remove(i);
            }
        }
    }

}

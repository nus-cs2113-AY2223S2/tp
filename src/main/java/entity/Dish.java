package entity;

import java.util.ArrayList;

public class Dish {
    private String dishName;
    private Integer dishPrice;
    private ArrayList<String> ingredientsList;

    public Dish(String dishName, int dishPrice, ArrayList<String> ingredientsList) {
        setDishName(dishName);
        setDishPrice(dishPrice);
        setIngredientsList(ingredientsList);
    }

    public String getDishName() {
        return dishName;
    }

    public int getPriceOfDishInCents() {
        return dishPrice;
    }

    public double getPriceOfDishInDollars() {
        return ((double) this.dishPrice) / 100.0;
    }

    public ArrayList<String> getIngredientsList() {
        return ingredientsList;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }

    public void setIngredientsList(ArrayList<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public String savableString() {
        //String ingredients = ingredientsList.toString().substring(1, ingredientsList.toString().length() - 1);
        String ingredients = "";
        for (String ingredient : ingredientsList) {
            ingredients += (ingredient + ";");
        }
        if (ingredients.endsWith(";")) {
            ingredients = ingredients.substring(0, ingredients.length() - 1);
        }
        return this.dishName + "~|~" + this.dishPrice + "~|~" + ingredients;
    }


}

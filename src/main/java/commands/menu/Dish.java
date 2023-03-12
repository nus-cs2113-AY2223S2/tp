package commands.menu;

import java.util.ArrayList;

public class Dish {
    String dishName;
    Integer dishPrice;
    ArrayList<String> ingredientsList;

    public Dish(String dishName, int dishPrice, ArrayList<String> ingredientsList) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.ingredientsList = ingredientsList;
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


}

package seedu.definitions;

public enum FoodTypes {
    INGREDIENT("Ingredient"),
    DISH("Dish"),
    SIDE("Side");

    private String foodType;

    private FoodTypes(String foodType) {
        this.foodType = foodType;
    } 

    @Override
    public String toString() {
        return foodType;
    }
}

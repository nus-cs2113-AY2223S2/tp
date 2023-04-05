package seedu.commands.caloriecommands;


import seedu.calorietracker.Calories;

public class AddCalorieCommand extends CaloriesCommand {


    public static final int CALORIES_NOT_GIVEN = -1;
    private static final String FOOD_CALORIES_IS_EMPTY_FIRST = "No calories record for '";
    private static final String FOOD_CALORIES_IS_EMPTY_SECOND = " Please add calories for it.";
    private String foodName;
    private int currentFoodCalories;

    private Calories calories = new Calories();
    private StringBuilder string = new StringBuilder();


    public AddCalorieCommand(String foodName, int currentFoodCalories) {
        this.foodName = foodName;
        this.currentFoodCalories = currentFoodCalories;
    }
    public void setCalories(Calories calories) {
        this.calories = calories;
    }


    public void checkCalorieMap() {
        if (calories.checkFood(foodName)) {
            calories.addToCurrentFoodCalories(foodName, foodList.getFoodCalories().get(foodName));
            string.append("Updated total calories for '").append(foodName)
                    .append("' in daily calories to ")
                    .append(calories.getSingleFoodCalories().get(foodName))
                    .append(" kcal!");
            ;
        } else {
            calories.addFoodCalories(foodName, foodList.getFoodCalories().get(foodName));
            string.append("Added '").append(foodName).append(": ")
                    .append(foodList.getFoodCalories().get(foodName))
                    .append(" kcal' to daily calories consumption!");
        }
    }

    public void checksFoodList() {

        // if the user enter the same calories values
        if (foodList.getFoodCalories().get(foodName) == currentFoodCalories) {
            string.append("'").append(foodName).append(": ")
                    .append(currentFoodCalories).append(" kcal'")
                    .append(" found in the food list!")
                    .append(System.lineSeparator());
            checkCalorieMap();
        } else { // the user had entered a new calories for the food
            foodList.addFood(foodName, currentFoodCalories);
            string.append("'").append(foodName).append("'")
                    .append(" found in food list and updated calories to ")
                    .append(currentFoodCalories)
                    .append(" kcal!")
                    .append(System.lineSeparator());
            checkCalorieMap();
        }

    }

    @Override
    public String execute() {
        /*calorieTracker.setFoodList(foodList);
        return calorieTracker.addCalories(date, food, calories);*/
        if (isDateEntered) {
            if (currentFoodCalories == CALORIES_NOT_GIVEN) {
                //check if the food had been stored in food list before
                if (foodList.contains(foodName)) {
                    // if the info find in hte food list
                    // and calories does not been entered by the user
                    string.append("'").append(foodName).append(": ")
                            .append(foodList.getFoodCalories().get(foodName)).append(" kcal'")
                            .append(" found in the food list!")
                            .append(System.lineSeparator());
                    checkCalorieMap();
                } else {
                    // if failed to find the food calories in the list
                    string.append(FOOD_CALORIES_IS_EMPTY_FIRST)
                            .append(foodName).append("' in food list")
                            .append(System.lineSeparator())
                            .append(FOOD_CALORIES_IS_EMPTY_SECOND);
                }

                //if the user had entered the calories for food before
            } else {
                if (foodList.contains(foodName)) {
                    checksFoodList();

                } else {
                    // both food list and daily calories consumption does not contain the food
                    foodList.addFood(foodName, currentFoodCalories);
                    string.append("Added '").append(foodName).append(": ")
                            .append(currentFoodCalories)
                            .append(" kcal' to food list!")
                            .append(System.lineSeparator());
                    calories.addFoodCalories(foodName, currentFoodCalories);
                    string.append("Added '").append(foodName).append(": ")
                            .append(currentFoodCalories)
                            .append(" kcal' to daily calories consumption!");
                }
            }
        } else {
            string.append("please use '/cdate' to start a date first!");
        }
        return string.toString();
    }
}

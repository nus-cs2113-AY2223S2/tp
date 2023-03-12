package seedu.duke.food;

import java.util.ArrayList;

public class FoodList {
    private ArrayList<Food> foodList = new ArrayList<>();
    private int numberOfFood = 0;

    public FoodList() {
    }

    public void addFood(Food food) {
        numberOfFood++;
        foodList.add(food);
    }

    public void removeFood(int index) {
        numberOfFood--;
        foodList.remove(index);
    }

    public int getNumberOfFood(){
        return numberOfFood;
    }

    public ArrayList<Food> getFoodList(){
        return foodList;
    }

    public Food getFood(int i){
        return foodList.get(i);
    }
}

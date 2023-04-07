package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;

public class WeeklyPlan extends HashMap<String, Integer> {

    public void addPlans(WeeklyPlan recipeMap) {
        final boolean[] validNumber = {true};
        recipeMap.forEach((recipe, count) -> {
            if (validNumber[0] && this.containsKey(recipe)) {
                int newCount = this.get(recipe) + count;
                validNumber[0] = (newCount > 1000) ? false : true;
            }
        });

        if (validNumber[0]) {
            recipeMap.forEach((recipe, count) -> {
                if (this.containsKey(recipe)) {
                    this.put(recipe, this.get(recipe) + count);
                } else {
                    this.put(recipe, count);
                }
            });
        } else {
            throw new IllegalArgumentException("Total quantity cannot exceed 1000!");
        }
    }

    public void deletePlans(WeeklyPlan recipeMap) {
        recipeMap.forEach((recipe, count) -> {
            if (this.containsKey(recipe)) {
                int currentCount = this.get(recipe);
                int newCount = currentCount - count;
                this.remove(recipe);
                if (newCount > 0) {
                    this.put(recipe, newCount);
                }
            } else {
                throw new IllegalArgumentException("Recipe does not exist in weekly plan!");
            }
        });
    }

    public void clearPlan() {
        this.clear();
    }

    public boolean checkValidity(RecipeList recipeList) {
        ArrayList<String> toRemove = new ArrayList<>();
        this.forEach((recipe, count) -> {
            if (recipeList.findByName(recipe) == null) {
                toRemove.add(recipe);
            }
        });

        if (toRemove.size() == 0) {
            return true;
        }
        toRemove.forEach(recipe -> this.remove(recipe));
        return false;
    }
}

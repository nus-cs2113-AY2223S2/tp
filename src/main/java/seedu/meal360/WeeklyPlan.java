package seedu.meal360;

import java.util.HashMap;

public class WeeklyPlan extends HashMap<String, Integer> {

    public void addPlans(WeeklyPlan recipeMap) {
        recipeMap.forEach((recipe, count) -> {
            if (this.containsKey(recipe)) {
                this.put(recipe, this.get(recipe) + count);
            } else {
                this.put(recipe, count);
            }
        });
    }

    public void deletePlans(WeeklyPlan recipeMap) {
        recipeMap.forEach((recipe, count) -> {
            if (this.containsKey(recipe)) {
                this.remove(recipe);
            } else {
                throw new IllegalArgumentException("Recipe does not exist in weekly plan!");
            }
        });
    }
}

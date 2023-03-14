package seedu.meal360;

import java.util.HashMap;

public class WeeklyPlan extends HashMap<String, Integer> {

    public void addPlan(WeeklyPlan recipeMap) {
        recipeMap.forEach((recipe, count) -> {
            this.put(recipe, count);
        });
    }

    public void deletePlan(WeeklyPlan recipeMap) {
        recipeMap.forEach((recipe, count) -> {
            this.remove(recipe);
        });
    }
}

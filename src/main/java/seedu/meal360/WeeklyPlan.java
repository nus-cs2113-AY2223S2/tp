package seedu.meal360;

import java.util.HashMap;

public class WeeklyPlan extends HashMap<String, Integer> {

    public void addPlan(HashMap<String, Integer> recipeMap) {
        recipeMap.forEach((recipe, count) -> {
            this.put(recipe, count);
        });
    }

    public void deletePlan(HashMap<String, Integer> recipeMap) {
        recipeMap.forEach((recipe, count) -> {
            this.remove(recipe);
        });
    }
}

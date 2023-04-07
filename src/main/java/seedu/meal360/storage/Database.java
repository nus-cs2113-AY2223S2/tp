package seedu.meal360.storage;

import java.io.IOException;
import java.util.HashMap;
import seedu.meal360.IngredientList;
import seedu.meal360.Recipe;
import seedu.meal360.RecipeList;
import seedu.meal360.WeeklyPlan;

public class Database {

    private static final String recipesDatabaseFilepath = "." + System.getProperty("file.separator") + "database"
            + System.getProperty("file.separator")
            + "recipesDatabase.json";

    private static final String weeklyPlanDatabaseFilepath = "." + System.getProperty("file.separator") + "database"
            + System.getProperty("file.separator")
            + "weeklyPlanDatabase.json";

    private static final String userIngredientsDatabaseFilepath = "." + System.getProperty("file.separator")
            + "database" + System.getProperty(
                    "file.separator")
            + "userIngredientsDatabase.json";

    JsonDatabaseHelper<RecipeList> recipeListJsonDatabaseHelper = new JsonDatabaseHelper<>(
            recipesDatabaseFilepath, defaultRecipeList(), RecipeList.class);

    JsonDatabaseHelper<WeeklyPlan> weeklyPlanJsonDatabaseHelper = new JsonDatabaseHelper<>(
            weeklyPlanDatabaseFilepath, new WeeklyPlan(), WeeklyPlan.class);

    JsonDatabaseHelper<IngredientList> userIngredientsJsonDatabaseHelper = new JsonDatabaseHelper<>(
            userIngredientsDatabaseFilepath, new IngredientList(), IngredientList.class);

    public RecipeList loadRecipesDatabase() throws IOException {
        return recipeListJsonDatabaseHelper.loadDatabase();
    }

    public void saveRecipesDatabase(RecipeList recipeList) throws IOException {
        recipeListJsonDatabaseHelper.saveDatabase(recipeList);
    }

    public WeeklyPlan loadWeeklyPlanDatabase() throws IOException {
        return weeklyPlanJsonDatabaseHelper.loadDatabase();
    }

    public void saveWeeklyPlanDatabase(WeeklyPlan weeklyPlan) throws IOException {
        weeklyPlanJsonDatabaseHelper.saveDatabase(weeklyPlan);
    }

    public IngredientList loadUserIngredientsDatabase() throws IOException {
        return userIngredientsJsonDatabaseHelper.loadDatabase();
    }

    public void saveIngredientsDatabase(IngredientList userIngredients) throws IOException {
        userIngredientsJsonDatabaseHelper.saveDatabase(userIngredients);
    }

    public RecipeList defaultRecipeList() {
        RecipeList defaultRecipeList = new RecipeList();
        defaultRecipeList.add(new Recipe("chicken rice", new HashMap<>() {
            {
                put("chicken", 1);
                put("rice", 1);
                put("salt", 1);
                put("pepper", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("tomato fritters", new HashMap<>() {
            {
                put("ripe mixed-colour cherry tomatoes ", 250);
                put("flat-leaf parsley", 15);
                put("garlic cloves", 2);
                put("plain flour", 2);
                put("eggs", 2);
                put("breadcrumbs", 100);
                put("vegetable oil", 2);
                put("feta cheese", 30);
            }
        }));

        defaultRecipeList.add(new Recipe("chicken and mushroom pie", new HashMap<>() {
            {
                put("chicken breast fillets", 2);
                put("mushrooms", 250);
                put("onion", 1);
                put("plain flour", 2);
                put("butter", 2);
                put("milk", 2);
                put("puff pastry", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("seafood paella", new HashMap<>() {
            {
                put("prawns", 500);
                put("mussels", 500);
                put("chorizo", 100);
                put("onion", 1);
                put("garlic cloves", 2);
                put("rice", 1);
                put("cherry tomatoes", 250);
                put("vegetable oil", 2);
                put("chicken stock", 1);
                put("saffron", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("avocado toast", new HashMap<>() {
            {
                put("avocado", 1);
                put("bread", 2);
                put("lemon", 1);
                put("salt", 1);
                put("pepper", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("italian sausage orzo soup", new HashMap<>() {
            {
                put("italian sausage", 1);
                put("onion", 1);
                put("garlic cloves", 2);
                put("carrot", 1);
                put("celery", 1);
                put("dried oregano", 1);
                put("dried basil", 1);
                put("dried thyme", 1);
                put("dried parsley", 1);
                put("dried rosemary", 1);
                put("chicken stock", 1);
                put("orzo", 1);
                put("parmesan cheese", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("chicken and mushroom pasta", new HashMap<>() {
            {
                put("chicken breast fillets", 2);
                put("mushrooms", 250);
                put("onion", 1);
                put("garlic cloves", 2);
                put("plain flour", 2);
                put("butter", 2);
                put("milk", 2);
                put("pasta", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("creamy hummus pasta", new HashMap<>() {
            {
                put("pasta", 1);
                put("hummus", 1);
                put("garlic cloves", 2);
                put("lemon", 1);
                put("salt", 1);
                put("pepper", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("ground beef and potato casserole", new HashMap<>() {
            {
                put("ground beef", 1);
                put("potato", 1);
                put("onion", 1);
                put("garlic cloves", 2);
                put("tomato paste", 1);
                put("tomato sauce", 1);
                put("beef stock", 1);
                put("cheddar cheese", 1);
            }
        }));

        defaultRecipeList.add(new Recipe("buffalo chicken mac 'n' cheese", new HashMap<>() {
            {
                put("chicken breast fillets", 2);
                put("macaroni", 1);
                put("butter", 2);
                put("flour", 2);
                put("milk", 2);
                put("cheddar cheese", 1);
                put("clue cheese", 1);
                put("hot sauce", 1);
            }
        }));

        return defaultRecipeList;
    }
}

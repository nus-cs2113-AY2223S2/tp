package seedu.meal360;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.meal360.exceptions.InvalidRecipeNameException;
import seedu.meal360.exceptions.InvalidValueException;
import seedu.meal360.storage.Database;

class Meal360Test {

    private static RecipeList recipes = new RecipeList();
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();

    private static final Database database = new Database();

    private static final IngredientList userIngredients = new IngredientList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    public void setUpRecipes() {
        // Adding of recipes
        HashMap<String, Integer> burgerIngredients = new HashMap<>();
        burgerIngredients.put("buns", 2);
        burgerIngredients.put("meat patty", 1);
        burgerIngredients.put("lettuce", 3);
        Recipe burger = new Recipe("burger", burgerIngredients);

        HashMap<String, Integer> pizzaIngredients = new HashMap<>();
        pizzaIngredients.put("dough", 1);
        pizzaIngredients.put("tomato sauce", 1);
        pizzaIngredients.put("cheese", 1);
        pizzaIngredients.put("pepperoni", 1);
        Recipe pizza = new Recipe("pizza", pizzaIngredients);

        HashMap<String, Integer> saladIngredients = new HashMap<>();
        saladIngredients.put("lettuce", 1);
        saladIngredients.put("tomato", 1);
        saladIngredients.put("cucumber", 1);
        Recipe salad = new Recipe("salad", saladIngredients);

        recipes.addRecipe(burger);
        recipes.addRecipe(pizza);
        recipes.addRecipe(salad);
    }

    @AfterEach
    public void tearDownRecipes() {
        recipes = new RecipeList();
    }

    @Test
    public void testViewRecipe() {

        // Testing positive case
        try {
            String userInput = "view 1";
            String[] command = parser.cleanUserInput(userInput);
            Recipe recipe = parser.parseViewRecipe(command, recipes);
            assertEquals("burger", recipe.getName());
            assertEquals(2, (int) recipe.getIngredients().get("buns"));
            assertEquals(1, (int) recipe.getIngredients().get("meat patty"));
            assertEquals(3, (int) recipe.getIngredients().get("lettuce"));
        } catch (Exception e) {
            assert false;
        }

        // Testing negative case (invalid index)
        try {
            String userInput = "view 5";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseViewRecipe(command, recipes);
            assert false;
        } catch (Exception e) {
            assertEquals("Please enter a valid recipe number. You entered 5, which is out of bounds.",
                    e.getMessage());
        }

        // Testing negative case (invalid index)
        try {
            String userInput = "view 0";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseViewRecipe(command, recipes);
            assert false;
        } catch (Exception e) {
            assertEquals("Please enter a valid recipe number. You entered 0, which is out of bounds.",
                    e.getMessage());
        }

        // Testing negative case (missing index)
        try {
            String userInput = "view";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseViewRecipe(command, recipes);
            assert false;
        } catch (Exception e) {
            assertEquals("Please enter a valid recipe number. You did not enter a recipe number.",
                    e.getMessage());
        }

        // Testing negative case (index is not a number)
        try {
            String userInput = "view a";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseViewRecipe(command, recipes);
            assert false;
        } catch (Exception e) {
            assertEquals("Please enter a valid recipe number. You entered a, which is not a valid number.",
                    e.getMessage());
        }

        // Testing negative case (index is negative number)
        try {
            String userInput = "view -1";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseViewRecipe(command, recipes);
            assert false;
        } catch (Exception e) {
            assertEquals("Please enter a valid recipe number. You entered -1, which is out of bounds.",
                    e.getMessage());
        }
    }

    @Test
    public void testDeleteRecipe() {
        HashMap<String, Integer> testIngredients = new HashMap<>();
        testIngredients.put("test ingredient", 100);
        // create a fake recipe
        Recipe testR = new Recipe("test recipe name", testIngredients);
        recipes.addRecipe(testR);

        int oldRecipeListSize = recipes.size();
        // delete recipe
        recipes.deleteRecipe(recipes.indexOf(testR));
        int newRecipeListSize = recipes.size();
        // check if recipe list size is 1 less than before
        assertEquals((oldRecipeListSize - 1), newRecipeListSize);
    }

    @Test
    public void testAddWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();

        // Testing positive case
        try {
            String userInput = "weekly /add burger 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assertTrue(weeklyPlan.containsKey("burger"));
            assertEquals(1, (int) weeklyPlan.get("burger"));
        } catch (Exception e) {
            assert false;
        }

        // Testing case-insensitive
        try {
            String userInput = "weekly /add BURGER 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assertTrue(weeklyPlan.containsKey("burger"));
            assertEquals(2, (int) weeklyPlan.get("burger"));
        } catch (Exception e) {
            assert false;
        }

        // Testing negative case (recipe does not exist in recipe list)
        try {
            String userInput = "weekly /add chicken 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(InvalidRecipeNameException.class, e.getClass());
            assertEquals("Please indicate a valid recipe name.", e.getMessage());
        }

        // Testing negative case (quantity is 0)
        try {
            String userInput = "weekly /add burger 0";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity is negative)
        try {
            String userInput = "weekly /add burger -1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity is greater than 1000)
        try {
            String userInput = "weekly /add burger 1001";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity is not a number)
        try {
            String userInput = "weekly /add burger a";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (recipe name is not specified)
        try {
            String userInput = "weekly /add";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (quantity is not specified)
        try {
            String userInput = "weekly /add burger";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (quantity specified is not an integer)
        try {
            String userInput = "weekly /add burger 1.5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity specified causes integer overflow)
        try {
            String userInput = "weekly /add burger 21474836480000000000";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }
    }

    @Test
    public void testAddMultiWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();

        // Testing positive case
        try {
            String userInput = "weekly /multiadd /r burger /q 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assertTrue(weeklyPlan.containsKey("burger"));
            assertEquals(1, (int) weeklyPlan.get("burger"));
        } catch (Exception e) {
            assert false;
        }

        // Testing positive case (adding multiple recipes)
        try {
            String userInput = "weekly /multiadd /r burger /q 1 /r pizza /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assertTrue(weeklyPlan.containsKey("burger"));
            assertEquals(2, (int) weeklyPlan.get("burger"));
            assertTrue(weeklyPlan.containsKey("pizza"));
            assertEquals(5, (int) weeklyPlan.get("pizza"));
        } catch (Exception e) {
            assert false;
        }

        // Testing case where same recipe name is specified twice
        try {
            String userInput = "weekly /multiadd /r burger /q 1 /r burger /q 20";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assertTrue(weeklyPlan.containsKey("burger"));
            assertEquals(22, (int) weeklyPlan.get("burger"));
        } catch (Exception e) {
            assert false;
        }

        // Testing negative case (quantity is negative)
        try {
            String userInput = "weekly /multiadd /r burger /q -1 /r pizza /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (quantity is greater than 1000)
        try {
            String userInput = "weekly /multiadd /r burger /q 1001 /r pizza /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (quantity is not a number)
        try {
            String userInput = "weekly /multiadd /r burger /q a /r pizza /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (recipe name is not in the recipe list)
        try {
            String userInput = "weekly /multiadd /r randomname /q 1 /r burger /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(InvalidRecipeNameException.class, e.getClass());
            assertEquals("Please indicate a valid recipe name.", e.getMessage());
        }

        // Testing negative case (recipe name is not specified)
        try {
            String userInput = "weekly /multiadd /q 1 /r burger /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (quantity is not specified)
        try {
            String userInput = "weekly /multiadd /r burger /r pizza /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (quantity given results in integer overflow)
        try {
            String userInput = "weekly /multiadd /r burger /q 10000000000000000000000 /r pizza /q 5";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }
    }

    @Test
    public void testDeleteWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("salad", 10);
        weeklyPlan.put("pizza", 30);

        // Testing positive cases
        try {
            String userInput = "weekly /delete salad 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assertEquals(9, weeklyPlan.get("salad"));
        } catch (Exception e) {
            assert false; // Not supposed to throw any exception here
        }

        // Testing case-insensitive
        try {
            String userInput = "weekly /delete Pizza 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assertEquals(29, weeklyPlan.get("pizza"));
        } catch (InvalidRecipeNameException | InvalidValueException e) {
            assert false; // Not supposed to throw any exception here
        }

        // Testing negative case (recipe exist in recipe list but not in weekly plan)
        try {
            String userInput = "weekly /delete burger 20";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Recipe does not exist in weekly plan!", e.getMessage());
        }

        // Testing negative case (recipe does not exist in recipe list)
        try {
            String userInput = "weekly /delete unknown 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidRecipeNameException.class, e.getClass());
            assertEquals("Please indicate a valid recipe name.", e.getMessage());
        }

        // Testing negative case (quantity is 0)
        try {
            String userInput = "weekly /delete salad 0";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity is greater than 1000)
        try {
            String userInput = "weekly /delete salad 1001";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity is negative)
        try {
            String userInput = "weekly /delete salad -1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity is not a number)
        try {
            String userInput = "weekly /delete salad one";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }

        // Testing negative case (quantity is not specified)
        try {
            String userInput = "weekly /delete salad";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (recipe name is not specified)
        try {
            String userInput = "weekly /delete";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (quantity results in integer overflow)
        try {
            String userInput = "weekly /delete salad 1000000000000000000000000000000000";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a number between 1 to 1000 for the quantity.", e.getMessage());
        }
    }

    @Test
    public void testDeleteMultiWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("salad", 10);
        weeklyPlan.put("pizza", 30);
        weeklyPlan.put("burger", 100);

        // Testing positive case
        try {
            String userInput = "weekly /multidelete /r salad /q 1 /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assertEquals(9, weeklyPlan.get("salad"));
            assertEquals(28, weeklyPlan.get("pizza"));
        } catch (InvalidRecipeNameException | InvalidValueException e) {
            assert false; // Not supposed to throw any exception here
        }

        // Testing positive case (case-insensitive)
        try {
            String userInput = "weekly /multidelete /r SALAD /q 1 /r pIzZa /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assertEquals(8, weeklyPlan.get("salad"));
            assertEquals(26, weeklyPlan.get("pizza"));
        } catch (InvalidRecipeNameException | InvalidValueException e) {
            assert false; // Not supposed to throw any exception here
        }

        // Testing positive case (quantity given exceeds the current quantity)
        try {
            String userInput = "weekly /multidelete /r salad /q 100 /r pizza /q 100";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assertNull(weeklyPlan.get("salad"));
            assertNull(weeklyPlan.get("pizza"));
        } catch (InvalidRecipeNameException | InvalidValueException e) {
            assert false; // Not supposed to throw any exception here
        }

        // Testing negative case (recipe name is not specified)
        try {
            String userInput = "weekly /multidelete /q 1 /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (quantity is not specified)
        try {
            String userInput = "weekly /multidelete /r salad /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter the command in the correct format.", e.getMessage());
        }

        // Testing negative case (quantity is not a number)
        try {
            String userInput = "weekly /multidelete /r salad /q one /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (quantity results in integer overflow)
        try {
            String userInput = "weekly /multidelete /r salad /q 100000000000000000000000000 /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (quantity is negative)
        try {
            String userInput = "weekly /multidelete /r salad /q -1 /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (quantity is 0)
        try {
            String userInput = "weekly /multidelete /r salad /q 0 /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (quantity is greater than 1000)
        try {
            String userInput = "weekly /multidelete /r salad /q 1001 /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Testing negative case (recipe name is not in the weekly plan)
        try {
            String userInput = "weekly /multidelete /r randomname /q 1 /r pizza /q 2";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach here
        } catch (Exception e) {
            assertEquals(InvalidRecipeNameException.class, e.getClass());
            assertEquals("Please indicate a valid recipe name.", e.getMessage());
        }
    }

    @Test
    public void testClearWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("salad", 1);
        weeklyPlan.put("pizza", 3);

        // Testing clearing weekly plan
        weeklyPlan.clearPlan();
        assertEquals(weeklyPlan.size(), 0);
    }

    @Test
    public void testViewIngredients() {

        HashMap<String, Integer> pastaIngredients = new HashMap<>();
        pastaIngredients.put("penne", 1);
        Recipe pasta = new Recipe("pasta", pastaIngredients);
        recipes.add(pasta);

        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("pasta", 1);

        ui.printWeeklyIngredients(weeklyPlan, recipes);
        assertEquals(ui.formatMessage("Here are your weekly ingredients:") + System.lineSeparator()
                + ui.formatMessage("penne (1)"), outContent.toString().trim());
    }

    @Test
    public void testViewEmptyWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        ui.printWeeklyPlan(weeklyPlan);
        assertEquals(ui.formatMessage("Your weekly plan is empty!"), outContent.toString().trim());
    }

    @Test
    public void testViewNonEmptyWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("salad", 1);
        ui.printWeeklyPlan(weeklyPlan);
        assertEquals(
                ui.formatMessage("Here is your weekly plan:") + System.lineSeparator() + ui.formatMessage(
                        "salad x1"), outContent.toString().trim());
    }

    @Test
    public void testListRecipe() {
        RecipeList recipeListToPrint;
        String[] inputs;

        inputs = new String[]{"list"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(3, recipeListToPrint.size());
        assertEquals(3, recipeListToPrint.get(0).getNumOfIngredients());
        assertEquals(4, recipeListToPrint.get(1).getNumOfIngredients());
        assertEquals(3, recipeListToPrint.get(2).getNumOfIngredients());

        inputs = new String[]{"list", "tomato", "sauce"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(1, recipeListToPrint.size());
        assertEquals(4, recipeListToPrint.get(0).getNumOfIngredients());
        assertEquals("pizza", recipeListToPrint.get(0).getName());

        inputs = new String[]{"list", "salad", "&&", "tomato"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(1, recipeListToPrint.size());
        assertEquals(3, recipeListToPrint.get(0).getNumOfIngredients());
        assertEquals("salad", recipeListToPrint.get(0).getName());

        inputs = new String[]{"list", "salad", "&&", "pizza"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(0, recipeListToPrint.size());
    }

    @Test
    public void testListTagAndAddTag() {
        RecipeList recipeListToPrint;
        String[] inputs;
        // add tag for testing
        String[] addTagInputs = new String[]{"tag", "breakfast", "<<", "salad"};
        parser.parseTagRecipe(addTagInputs, recipes);

        inputs = new String[]{"list", "/t", "breakfast"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(recipeListToPrint.size(), 1);
        assertEquals(recipeListToPrint.get(0).getName(), "salad");

        // add tag for testing
        addTagInputs = new String[]{"tag", "breakfast", "<<", "pizza"};
        parser.parseTagRecipe(addTagInputs, recipes);
        addTagInputs = new String[]{"tag", "western", "<<", "burger"};
        parser.parseTagRecipe(addTagInputs, recipes);

        inputs = new String[]{"list", "/t", "breakfast"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(recipeListToPrint.size(), 2);

        inputs = new String[]{"list", "/t", "breakfast", "&&", "western"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(recipeListToPrint.size(), 0);

        // add tag for testing
        addTagInputs = new String[]{"tag", "western", "<<", "pizza"};
        parser.parseTagRecipe(addTagInputs, recipes);
        inputs = new String[]{"list", "/t", "breakfast", "&&", "western"};
        recipeListToPrint = parser.parseListRecipe(inputs, recipes);
        assertEquals(recipeListToPrint.size(), 1);
        assertEquals(recipeListToPrint.get(0).getName(), "pizza");
    }

    @Test
    public void testListTagException() {
        String[] inputs;

        //check exception
        String[] noArgInputs = new String[]{"list", "/t"};
        assertThrows(IllegalArgumentException.class, () -> parser.parseListRecipe(noArgInputs, recipes));

        String[] notFoundTagInputs1 = new String[]{"list", "/t", "/t"};
        assertThrows(NullPointerException.class, () -> parser.parseListRecipe(notFoundTagInputs1, recipes));

        String[] notFoundTagInputs2 = new String[]{"list", "/t", "phone", "&&", "computer"};
        assertThrows(NullPointerException.class, () -> parser.parseListRecipe(notFoundTagInputs2, recipes));

        String[] beforeAddTagInputs = new String[]{"list", "/t", "breakfast"};
        assertThrows(NullPointerException.class, () -> parser.parseListRecipe(beforeAddTagInputs, recipes));

        // add tag for testing
        inputs = new String[]{"tag", "breakfast", "<<", "salad"};
        parser.parseTagRecipe(inputs, recipes);
        inputs = new String[]{"tag", "breakfast", ">>", "salad"};
        parser.parseTagRecipe(inputs, recipes);

        String[] nothingInTagInputs = new String[]{"list", "/t", "breakfast"};
        ui.listRecipe(parser.parseListRecipe(nothingInTagInputs, recipes));
        assertEquals(ui.formatMessage("There is nothing to list."), outContent.toString().trim());
    }

    @Test
    public void testRemoveTagAndAddTag() {
        String[] inputs;
        Recipe invalidRecipe = new Recipe("invalid Recipe", new HashMap<>(1, 1));

        inputs = new String[]{"tag", "western", "<<", "burger"};
        parser.parseTagRecipe(inputs, recipes);
        inputs = new String[]{"tag", "western", "<<", "pizza"};
        parser.parseTagRecipe(inputs, recipes);

        //exception
        assertThrows(IndexOutOfBoundsException.class,
                () -> recipes.removeRecipeFromTag("western", invalidRecipe));
        String[] invalidTagInputs = new String[]{"tag", "random", "tag", ">>", "burger"};
        assertThrows(IndexOutOfBoundsException.class, () -> parser.parseTagRecipe(invalidTagInputs, recipes));

        String[] noRecipeInTagInputs = new String[]{"tag", "breakfast", ">>", "pizza"};
        assertThrows(IndexOutOfBoundsException.class,
                () -> parser.parseTagRecipe(noRecipeInTagInputs, recipes));

        inputs = new String[]{"tag", "western", ">>", "pizza"};
        parser.parseTagRecipe(inputs, recipes);
        assertEquals(recipes.tags.get("western").size(), 1);
    }

    @Test
    public void testRandomRecipe() {
        // no recipe in the lists
        RecipeList blankRecipeList = new RecipeList();
        assertThrows(NullPointerException.class, () -> parser.parseRandomRecipe(blankRecipeList));

        // general case
        Recipe randomRecipe1 = parser.parseRandomRecipe(recipes);
        assertNotNull(randomRecipe1);
        assertNotNull(recipes.randomRecipe());
    }

    @Test
    public void testLoadDatabase() {
        assertDoesNotThrow(database::loadRecipesDatabase);
    }
}

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
import seedu.meal360.exceptions.IngredientNotFoundException;
import seedu.meal360.exceptions.InvalidRecipeNameException;
import seedu.meal360.exceptions.InvalidValueException;
import seedu.meal360.exceptions.NoRecipeException;
import seedu.meal360.exceptions.RecipeNotFoundInTagException;
import seedu.meal360.exceptions.TagNotFoundException;
import seedu.meal360.storage.Database;

class Meal360Test {

    private static RecipeList recipes = new RecipeList();
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();

    private static final Database database = new Database();

    private static IngredientList userIngredients = new IngredientList();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    //@@author jaredoong
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

        HashMap<String, Integer> chickenRiceIngredients = new HashMap<>();
        chickenRiceIngredients.put("rice", 1);
        chickenRiceIngredients.put("chicken", 1);
        Recipe chickenRice = new Recipe("chicken rice", chickenRiceIngredients);

        recipes.addRecipe(burger);
        recipes.addRecipe(pizza);
        recipes.addRecipe(salad);
        recipes.addRecipe(chickenRice);

        // Adding of ingredients
        Ingredient buns = new Ingredient("buns", 10, "10/10/2024");
        Ingredient meatPatty = new Ingredient("meat patty", 10, "10/10/2024");
        Ingredient lettuce = new Ingredient("lettuce", 10, "10/10/2024");
        Ingredient dough = new Ingredient("dough", 10, "10/10/2024");
        Ingredient tomatoSauce = new Ingredient("tomato sauce", 10, "10/10/2024");
        Ingredient cheese = new Ingredient("cheese", 10, "10/10/2024");
        Ingredient pepperoni = new Ingredient("pepperoni", 10, "10/10/2024");
        Ingredient tomato = new Ingredient("tomato", 10, "10/10/2024");
        Ingredient cucumber = new Ingredient("cucumber", 10, "10/10/2024");
        Ingredient rice = new Ingredient("rice", 10, "10/10/2024");
        Ingredient chicken = new Ingredient("chicken", 10, "10/10/2024");
        Ingredient peasandcorn = new Ingredient("peas and corn", 10, "10/10/2024");

        userIngredients.addIngredient(buns);
        userIngredients.addIngredient(meatPatty);
        userIngredients.addIngredient(lettuce);
        userIngredients.addIngredient(dough);
        userIngredients.addIngredient(tomatoSauce);
        userIngredients.addIngredient(cheese);
        userIngredients.addIngredient(pepperoni);
        userIngredients.addIngredient(tomato);
        userIngredients.addIngredient(cucumber);
        userIngredients.addIngredient(rice);
        userIngredients.addIngredient(chicken);
        userIngredients.addIngredient(peasandcorn);
    }

    @AfterEach
    public void tearDownRecipes() {
        recipes = new RecipeList();
        userIngredients = new IngredientList();
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

    //@@author gurmankalkat
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

        // No recipe inputted
        try {
            String userInput = "delete";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range in the correct format.",
                    e.getMessage());
        }

        // Recipe in incorrect format
        try {
            String userInput = "delete pizza";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range in the correct format.",
                    e.getMessage());
        }

        // Index out of bounds
        try {
            String userInput = "delete 100000";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range.", e.getMessage());
        }

        // Index out of bounds
        try {
            String userInput = "delete -5";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range.", e.getMessage());
        }

        // Range invalid
        try {
            String userInput = "delete 0-2000000";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range.", e.getMessage());
        }

        // Range invalid
        try {
            String userInput = "delete 1---------";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range in the correct format.",
                    e.getMessage());
        }

        // Range invalid
        HashMap<String, Integer> testIngredients1 = new HashMap<>();
        testIngredients1.put("test ingredient", 100);
        // create a fake recipe
        Recipe testR1 = new Recipe("test recipe name", testIngredients);
        recipes.addRecipe(testR1);

        HashMap<String, Integer> testIngredients2 = new HashMap<>();
        testIngredients2.put("test ingredient", 100);
        // create a fake recipe
        Recipe testR2 = new Recipe("test recipe name", testIngredients);
        recipes.addRecipe(testR2);
        try {
            String userInput = "delete 2-1";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range.", e.getMessage());
        }

        // Delete all
        try {
            String userInput = "delete /r all";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assertEquals(0, recipes.size());
        } catch (Exception e) {
            assert false;
        }

        // Recipe does not exist in recipe list)
        try {
            String userInput = "delete /r pizza";
            String[] command = parser.cleanUserInput(userInput);
            parser.parseDeleteRecipe(command, recipes);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
            assertEquals("Please enter a valid recipe number, name, or range in the correct format.",
                    e.getMessage());
        }
    }

    //@@author jaredoong
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

        // Testing positive case (case-insensitive)
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

        // Testing positive case (name with space)
        try {
            String userInput = "weekly /add chicken rice 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assertTrue(weeklyPlan.containsKey("chicken rice"));
            assertEquals(1, (int) weeklyPlan.get("chicken rice"));
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
            assertEquals("Please ensure that you entered a valid quantity as the last argument.",
                    e.getMessage());
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
            assertEquals("Please enter the command in the correct format with all parameters provided.",
                    e.getMessage());
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
            assertEquals("Please enter the command in the correct format with all parameters provided.",
                    e.getMessage());
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
            assertEquals("Please ensure that you entered a valid quantity as the last argument.",
                    e.getMessage());
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
            assertEquals("Please ensure that you entered a valid quantity as the last argument.",
                    e.getMessage());
        }

        // Testing negative case (missing all arguments)
        try {
            String userInput = "weekly";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please indicate if you would want to add to, delete from, clear weekly plan, or "
                    + "mark as done.", e.getMessage());
        }

        // Testing negative case (arguments are not in the correct order)
        try {
            String userInput = "weekly /add 1 burger";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please ensure that you entered a valid quantity as the last argument.",
                    e.getMessage());
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

        // Testing case where recipe name has space
        try {
            String userInput = "weekly /multiadd /r burger /q 1 /r chicken rice /q 20";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assertTrue(weeklyPlan.containsKey("burger"));
            assertEquals(23, (int) weeklyPlan.get("burger"));
            assertTrue(weeklyPlan.containsKey("chicken rice"));
            assertEquals(20, (int) weeklyPlan.get("chicken rice"));
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
            assertEquals("Please ensure the number of /r and /q are the same.", e.getMessage());
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
            assertEquals("Please ensure the number of /r and /q are the same.", e.getMessage());
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

        // Testing negative case (arguments are not in the correct order)
        try {
            String userInput = "weekly /multiadd /q 1 /r burger";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.addPlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please ensure that the /r and /q flags are entered in the correct order.",
                    e.getMessage());
        }
    }

    @Test
    public void testDeleteWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("salad", 10);
        weeklyPlan.put("pizza", 30);
        weeklyPlan.put("chicken rice", 10);

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

        // Testing case where recipe name has space
        try {
            String userInput = "weekly /delete chicken rice 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assertEquals(9, weeklyPlan.get("chicken rice"));
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
            assertEquals("Please ensure that you entered a valid quantity as the last argument.",
                    e.getMessage());
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
            assertEquals("Please enter the command in the correct format with all parameters provided.",
                    e.getMessage());
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
            assertEquals("Please enter the command in the correct format with all parameters provided.",
                    e.getMessage());
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
            assertEquals("Please ensure that you entered a valid quantity as the last argument.",
                    e.getMessage());
        }

        // Testing negative case (arguments are not in the correct order)
        try {
            String userInput = "weekly /delete 1 burger";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please ensure that you entered a valid quantity as the last argument.",
                    e.getMessage());
        }
    }

    @Test
    public void testDeleteMultiWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("salad", 10);
        weeklyPlan.put("pizza", 30);
        weeklyPlan.put("burger", 100);
        weeklyPlan.put("chicken rice", 50);

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

        // Testing positive case (recipe name has space)
        try {
            String userInput = "weekly /multidelete /r chicken rice /q 1";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assertEquals(49, weeklyPlan.get("chicken rice"));
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
            assertEquals("Please ensure the number of /r and /q are the same.", e.getMessage());
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
            assertEquals("Please ensure the number of /r and /q are the same.", e.getMessage());
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

        // Testing negative case (arguments are not in the correct order)
        try {
            String userInput = "weekly /multidelete /q 1 /r burger";
            String[] command = parser.cleanUserInput(userInput);
            WeeklyPlan recipeMap = parser.parseWeeklyPlan(command, recipes);
            weeklyPlan.deletePlans(recipeMap);
            assert false; // Not supposed to reach this line
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please ensure that the /r and /q flags are entered in the correct order.",
                    e.getMessage());
        }
    }

    //@@author gurmankalkat
    @Test
    public void testClearWeeklyPlan() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("salad", 1);
        weeklyPlan.put("pizza", 3);
        // Testing clearing weekly plan
        weeklyPlan.clearPlan();
        assertEquals(weeklyPlan.size(), 0);

        // weekly plan of size 0
        weeklyPlan = new WeeklyPlan();
        weeklyPlan.clearPlan();
        assertEquals(weeklyPlan.size(), 0);
    }

    @Test
    public void testViewWeeklyIngredients() {
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

    //@@author jaredoong
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

    //@@author junenita
    @Test
    public void testListRecipe() {
        RecipeList recipeListToPrint;
        String[] inputs;

        try {
            inputs = new String[]{"list"};
            recipeListToPrint = parser.parseListRecipe(inputs, recipes);
            assertEquals(4, recipeListToPrint.size());
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
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testListTagAndAddTag() {
        try {
            RecipeList recipeListToPrint;
            String[] inputs;
            // check add tag (1)
            String[] addTagInputs = new String[]{"tag", "breakfast", "<<", "salad"};
            parser.parseTagRecipe(addTagInputs, recipes);

            // check list tag (1)
            inputs = new String[]{"list", "/t", "breakfast"};
            recipeListToPrint = parser.parseListRecipe(inputs, recipes);
            assertEquals(recipeListToPrint.size(), 1);
            assertEquals(recipeListToPrint.get(0).getName(), "salad");

            // add tag (2)
            addTagInputs = new String[]{"tag", "breakfast", "<<", "pizza"};
            parser.parseTagRecipe(addTagInputs, recipes);

            addTagInputs = new String[]{"tag", "western", "<<", "burger"};
            parser.parseTagRecipe(addTagInputs, recipes);

            // list tag (2)
            inputs = new String[]{"list", "/t", "breakfast"};
            recipeListToPrint = parser.parseListRecipe(inputs, recipes);
            assertEquals(recipeListToPrint.size(), 2);

            inputs = new String[]{"list", "/t", "breakfast", "&&", "western"};
            recipeListToPrint = parser.parseListRecipe(inputs, recipes);
            assertEquals(recipeListToPrint.size(), 0);

            // add tag (3)
            addTagInputs = new String[]{"tag", "western", "<<", "pizza"};
            parser.parseTagRecipe(addTagInputs, recipes);

            // list tag (3)
            inputs = new String[]{"list", "/t", "breakfast", "&&", "western"};
            recipeListToPrint = parser.parseListRecipe(inputs, recipes);
            assertEquals(recipeListToPrint.size(), 1);
            assertEquals(recipeListToPrint.get(0).getName(), "pizza");
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testPrintAddTagMessage() {
        String[] addTagInputs = new String[]{"tag", "breakfast", "<<", "salad"};
        try {
            String addTagReturnMessage = parser.parseTagRecipe(addTagInputs, recipes);
            ui.printTagMessage(addTagReturnMessage);
            assertEquals(ui.formatMessage("You have successfully added the recipe(s) to \"breakfast\" tag."),
                    outContent.toString().trim());
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testPrintRemoveTagMessage() {
        String[] addTagInputs = new String[]{"tag", "breakfast", "<<", "salad"};
        try {
            parser.parseTagRecipe(addTagInputs, recipes);
            String[] removeTagInputs = new String[]{"tag", "breakfast", ">>", "salad"};
            String removeTagReturnMessage = parser.parseTagRecipe(removeTagInputs, recipes);
            ui.printTagMessage(removeTagReturnMessage);
            assertEquals(
                    ui.formatMessage("You have successfully removed the recipe(s) from \"breakfast\" tag."),
                    outContent.toString().trim());
        } catch (Exception e) {
            assert false;
        }

    }

    @Test
    public void testListTagException() {
        String[] inputs;

        //check exception
        String[] noArgInputs = new String[]{"list", "/t"};
        assertThrows(TagNotFoundException.class, () -> parser.parseListRecipe(noArgInputs, recipes));

        String[] notFoundTagInputs1 = new String[]{"list", "/t", "/t"};
        assertThrows(TagNotFoundException.class, () -> parser.parseListRecipe(notFoundTagInputs1, recipes));

        String[] notFoundTagInputs2 = new String[]{"list", "/t", "phone", "&&", "computer"};
        assertThrows(TagNotFoundException.class, () -> parser.parseListRecipe(notFoundTagInputs2, recipes));

        String[] beforeAddTagInputs = new String[]{"list", "/t", "breakfast"};
        assertThrows(TagNotFoundException.class, () -> parser.parseListRecipe(beforeAddTagInputs, recipes));

        // add tag for testing
        try {
            inputs = new String[]{"tag", "breakfast", "<<", "salad"};
            parser.parseTagRecipe(inputs, recipes);
            inputs = new String[]{"tag", "breakfast", ">>", "salad"};
            parser.parseTagRecipe(inputs, recipes);

            String[] nothingInTagInputs = new String[]{"list", "/t", "breakfast"};

            ui.listRecipe(parser.parseListRecipe(nothingInTagInputs, recipes));
            assertEquals(ui.formatMessage("There is nothing to list."), outContent.toString().trim());
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testRemoveTagAndAddTag() {
        String[] inputs;
        Recipe invalidRecipe = new Recipe("invalid Recipe", new HashMap<>(1, 1));

        try {
            inputs = new String[]{"tag", "western", "<<", "burger"};
            parser.parseTagRecipe(inputs, recipes);
            inputs = new String[]{"tag", "western", "<<", "pizza"};
            parser.parseTagRecipe(inputs, recipes);
        } catch (Exception e) {
            assert false;
        }

        //exception
        assertThrows(RecipeNotFoundInTagException.class,
                () -> recipes.removeRecipeFromTag("western", invalidRecipe));
        String[] invalidTagInputs = new String[]{"tag", "random", "tag", ">>", "burger"};
        assertThrows(TagNotFoundException.class, () -> parser.parseTagRecipe(invalidTagInputs, recipes));

        String[] noRecipeInTagInputs = new String[]{"tag", "western", ">>", "random"};
        assertThrows(NoRecipeException.class, () -> parser.parseTagRecipe(noRecipeInTagInputs, recipes));

        try {
            inputs = new String[]{"tag", "western", ">>", "pizza"};
            parser.parseTagRecipe(inputs, recipes);
            assertEquals(recipes.tags.get("western").size(), 1);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testRandomRecipe() {
        // no recipe in the lists
        RecipeList blankRecipeList = new RecipeList();
        assertThrows(NoRecipeException.class, () -> parser.parseRandomRecipe(blankRecipeList));

        // general case
        try {
            Recipe randomRecipe1 = parser.parseRandomRecipe(recipes);
            assertNotNull(randomRecipe1);
            assertNotNull(recipes.randomRecipe());
        } catch (Exception e) {
            assert false;
        }
    }

    //@@author jaredoong
    @Test
    public void testWeeklyDone() {
        WeeklyPlan weeklyPlan = new WeeklyPlan();
        weeklyPlan.put("pizza", 100);
        weeklyPlan.put("burger", 100);
        weeklyPlan.put("chicken rice", 100);

        // Testing positive case
        try {
            String userInput = "weekly /done burger";
            String[] command = parser.cleanUserInput(userInput);
            String recipeToDelete = parser.parseMarkDone(command, userIngredients, weeklyPlan, recipes);
            weeklyPlan.markDone(recipeToDelete, recipes, userIngredients);
            assertEquals(8, userIngredients.findIngredientCount("buns"));
            assertEquals(9, userIngredients.findIngredientCount("meat patty"));
            assertEquals(7, userIngredients.findIngredientCount("lettuce"));
            assertEquals(99, weeklyPlan.get("burger"));
        } catch (Exception e) {
            assert false;
        }

        // Testing positive case (case-insensitive)
        try {
            String userInput = "weekly /done Pizza";
            String[] command = parser.cleanUserInput(userInput);
            String recipeToDelete = parser.parseMarkDone(command, userIngredients, weeklyPlan, recipes);
            weeklyPlan.markDone(recipeToDelete, recipes, userIngredients);
            assertEquals(9, userIngredients.findIngredientCount("dough"));
            assertEquals(9, userIngredients.findIngredientCount("tomato sauce"));
            assertEquals(9, userIngredients.findIngredientCount("cheese"));
            assertEquals(9, userIngredients.findIngredientCount("pepperoni"));
            assertEquals(99, weeklyPlan.get("pizza"));
        } catch (Exception e) {
            assert false;
        }

        // Testing positive case (entire command case-insensitive)
        try {
            String userInput = "WEEKLY /DONE pizza";
            String[] command = parser.cleanUserInput(userInput);
            String recipeToDelete = parser.parseMarkDone(command, userIngredients, weeklyPlan, recipes);
            weeklyPlan.markDone(recipeToDelete, recipes, userIngredients);
            assertEquals(8, userIngredients.findIngredientCount("dough"));
            assertEquals(8, userIngredients.findIngredientCount("tomato sauce"));
            assertEquals(8, userIngredients.findIngredientCount("cheese"));
            assertEquals(8, userIngredients.findIngredientCount("pepperoni"));
            assertEquals(98, weeklyPlan.get("pizza"));
        } catch (Exception e) {
            assert false;
        }

        // Testing positive case (recipe name with space)
        try {
            String userInput = "weekly /done chicken rice";
            String[] command = parser.cleanUserInput(userInput);
            String recipeToDelete = parser.parseMarkDone(command, userIngredients, weeklyPlan, recipes);
            weeklyPlan.markDone(recipeToDelete, recipes, userIngredients);
            assertEquals(9, userIngredients.findIngredientCount("chicken"));
            assertEquals(9, userIngredients.findIngredientCount("rice"));
            assertEquals(99, weeklyPlan.get("chicken rice"));
        } catch (Exception e) {
            assert false;
        }

        // Testing negative case (recipe not found)
        try {
            String userInput = "weekly /done pizzaandburgers";
            String[] command = parser.cleanUserInput(userInput);
            String recipeToDelete = parser.parseMarkDone(command, userIngredients, weeklyPlan, recipes);
            weeklyPlan.markDone(recipeToDelete, recipes, userIngredients);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(InvalidRecipeNameException.class, e.getClass());
            assertEquals("Please enter a valid recipe name.", e.getMessage());
        }

        // Testing negative case (recipe not in weekly plan)
        try {
            String userInput = "weekly /done salad";
            String[] command = parser.cleanUserInput(userInput);
            String recipeToDelete = parser.parseMarkDone(command, userIngredients, weeklyPlan, recipes);
            weeklyPlan.markDone(recipeToDelete, recipes, userIngredients);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Recipe does not exist in weekly plan.", e.getMessage());
        }

        // Testing negative case (not enough ingredients)
        try {
            String userInput = "weekly /done burger";
            String[] command = parser.cleanUserInput(userInput);
            for (int i = 0; i < 3; i++) {
                String recipeToDelete = parser.parseMarkDone(command, userIngredients, weeklyPlan, recipes);
                weeklyPlan.markDone(recipeToDelete, recipes, userIngredients);
            }
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(IngredientNotFoundException.class, e.getClass());
            assertEquals("You do not have enough ingredients to mark this recipe as done.", e.getMessage());
        }
    }

    @Test
    public void testAddUserIngredients() {

        // Test positive case (new ingredient)
        try {
            String userInput = "add_i /n fish /c 10 /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assertEquals(10, userIngredients.findIngredientCount("fish"));
        } catch (Exception e) {
            assert false;
        }

        // Test positive case (ingredient name already exists)
        try {
            String userInput = "add_i /n chicken /c 10 /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assertEquals(20, userIngredients.findIngredientCount("chicken"));
        } catch (Exception e) {
            assert false;
        }

        //  Test positive case (case-insensitive)
        try {
            String userInput = "add_i /n CHICKEN /c 10 /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assertEquals(30, userIngredients.findIngredientCount("chicken"));
        } catch (Exception e) {
            assert false;
        }

        // Test positive case (expiry date gets updated)
        try {
            String userInput = "add_i /n chicken /c 10 /d 21/10/2025";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assertEquals(40, userIngredients.findIngredientCount("chicken"));
            assertEquals(parser.parseDate("21/10/2025"), userIngredients.findExpiryDate("chicken"));
        } catch (Exception e) {
            assert false;
        }

        // Test positive case (ingredient name with spaces)
        try {
            String userInput = "add_i /n peas and corn and carrot /c 10 /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assertEquals(10, userIngredients.findIngredientCount("peas and corn and carrot"));
        } catch (Exception e) {
            assert false;
        }

        // Test negative case (quantity is 0)
        try {
            String userInput = "add_i /n chicken /c 0 /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (quantity is negative)
        try {
            String userInput = "add_i /n chicken /c -10 /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (quantity is more than 1000)
        try {
            String userInput = "add_i /n chicken /c 1001 /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (quantity is not a number)
        try {
            String userInput = "add_i /n chicken /c abc /d 10/10/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (expiry date is not in the correct format)
        try {
            String userInput = "add_i /n chicken /c 10 /d 10/10/202";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter a valid date in the format dd/mm/yyyy.", e.getMessage());
        }

        // Test negative case (expiry date is not a valid date)
        try {
            String userInput = "add_i /n chicken /c 10 /d 10/13/2024";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToAdd = parser.parseAddUserIngredients(command);
            userIngredients.addIngredient(ingredientToAdd);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("Please enter a valid date in the format dd/mm/yyyy.", e.getMessage());
        }
    }

    @Test
    public void testDeleteUserIngredients() {

        // Test positive case
        try {
            String userInput = "del_i /n chicken /c 5";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assertEquals(5, userIngredients.findIngredientCount("chicken"));
        } catch (Exception e) {
            assert false;
        }

        // Test positive case (case-insensitive)
        try {
            String userInput = "del_i /n CHEESE /c 5";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assertEquals(5, userIngredients.findIngredientCount("cheese"));
        } catch (Exception e) {
            assert false;
        }

        // Test positive case (ingredient name with spaces)
        try {
            String userInput = "del_i /n peas and corn /c 5";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assertEquals(5, userIngredients.findIngredientCount("peas and corn"));
        } catch (Exception e) {
            assert false;
        }

        // Test positive case (quantity is more than the current quantity)
        try {
            String userInput = "del_i /n chicken /c 100";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assertNull(userIngredients.get("chicken"));
        } catch (Exception e) {
            assert false;
        }

        // Test negative case (quantity is 0)
        try {
            String userInput = "del_i /n chicken /c 0";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (quantity is negative)
        try {
            String userInput = "del_i /n chicken /c -5";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (quantity is more than 1000)
        try {
            String userInput = "del_i /n chicken /c 1001";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(InvalidValueException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (quantity is not a number)
        try {
            String userInput = "del_i /n chicken /c abc";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(NumberFormatException.class, e.getClass());
            assertEquals("Please enter a positive number between 1 to 1000 for the quantity.",
                    e.getMessage());
        }

        // Test negative case (ingredient name is not found)
        try {
            String userInput = "del_i /n beef /c 5";
            String[] command = parser.cleanUserInput(userInput);
            Ingredient ingredientToDelete = parser.parseDeleteUserIngredients(command);
            userIngredients.deleteIngredient(ingredientToDelete);
            assert false; // should not reach here
        } catch (Exception e) {
            assertEquals(IngredientNotFoundException.class, e.getClass());
            assertEquals("Ingredient not found", e.getMessage());
        }
    }

    @Test
    public void testNonEmptyListUserIngredients() {
        IngredientList testIngredientList = new IngredientList();
        Ingredient buns = new Ingredient("buns", 10, "10/10/2024");
        testIngredientList.addIngredient(buns);

        // Test positive case
        ui.printUserIngredients(testIngredientList);
        assertEquals(
                ui.formatMessage("Here is your ingredient list:") + System.lineSeparator() + ui.formatMessage(
                        "buns " + "(10) " + "[by:10/10/2024]") + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testEmptyListUserIngredients() {
        IngredientList testIngredientList = new IngredientList();

        // Testing positive case (no ingredients)
        ui.printUserIngredients(testIngredientList);
        assertEquals(ui.formatMessage("Your ingredient list is empty!") + System.lineSeparator(),
                outContent.toString());
    }

    @Test
    public void testLoadDatabase() {
        assertDoesNotThrow(database::loadRecipesDatabase);
    }

    //@@author AbijithRam
    @Test
    public void testCombineWords() {
        String[] input1 = {"One", "Two", "Three"};
        assertEquals("One Two Three", parser.combineWords(input1, 0, 3));

        String[] input2 = {"Pizza  ", "Burger  ", "   Ice Cream"};
        assertEquals("Pizza Burger Ice Cream", parser.combineWords(input2, 0, 3));

        String[] input3 = {"1 1 1", "2  2  2", "3   3   3"};
        assertEquals("1 1 1 2  2  2 3   3   3", parser.combineWords(input3, 0, 3));

    }
}

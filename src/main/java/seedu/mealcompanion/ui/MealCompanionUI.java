package seedu.mealcompanion.ui;

import java.util.Scanner;

/**
 * Contains the UI helpers for the current MealCompanionSession session.
 */
public class MealCompanionUI {

    private static final String DIVIDER = "_______________________________________________________________________"
            + "__________________________________________";

    private Scanner scanner;

    public MealCompanionUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Returns a string representing the next command the user enters.
     * @return the next command string.
     */
    public String getNextCommandString() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        } else {
            return "help"; // return default command if there is no input
        }
    }


    /**
     * Prints the welcome message.
     */
    public void printIntroduction() {
        System.out.println("Welcome to Meal Companion!");
    }

    /**
     * Prints the provided message.
     * @param message the message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    //@@author TJW0911

    /**
     * Prints help messages for the "help" command.
     */

    public void printHelpMessages() {
        printHelpMessageHeader();
        printIngredientHelp();
        printRecipeHelp();
        printAllergenHelp();
        printByeCommandHelp();
    }

    private static void printAllergenHelp() {
        printAllergenAddHelp();
        printAllergenRemoveHelp();
        printAllergenListHelp();
    }

    private static void printAllergenListHelp() {
        System.out.println("Command: allergen list");
        System.out.println("List out all the allergens the user currently has");
        System.out.println(DIVIDER);
    }

    private static void printAllergenRemoveHelp() {
        System.out.println("Command: allergen remove <ingredient>");
        System.out.println("Removes the ingredient from the users list of allergens");
        System.out.println(DIVIDER);
    }

    private static void printAllergenAddHelp() {
        System.out.println("Command: allergen add <ingredient>");
        System.out.println("Adds the ingredient to the users list of allergens");
        System.out.println(DIVIDER);
    }

    private static void printIngredientHelp() {
        printAddCommandHelp();
        printRemoveCommandHelp();
        printMakeCommandHelp();
        printClearCommandHelp();
        printListIngredientsHelp();
        printSearchIngredientsHelp();
    }

    private static void printRecipeHelp() {
        printRecipePossibleHelp();
        printRecipeAllHelp();
        printRecipeRandomHelp();
        printRecipeDetailHelp();
        printRecipeNeedHelp();
        printRecipeAlmostHelp();
    }

    private static void printRecipeAlmostHelp() {
        System.out.println("Command: recipe almost");
        System.out.println("Lists all the recipes that cannot currently be made but has " +
                "less than 4 insufficient ingredients");
        System.out.println(DIVIDER);
    }

    private static void printRecipeNeedHelp() {
        System.out.println("Command: recipe need <recipe number>");
        System.out.println("Prints all the missing ingredients needed to make a specified recipe");
        System.out.println(DIVIDER);
    }

    private static void printRecipeDetailHelp() {
        System.out.println("Command: recipe <recipe number>");
        System.out.println("Prints all the details of the specified recipe");
        System.out.println(DIVIDER);
    }

    private static void printRecipeRandomHelp() {
        System.out.println("Command: recipe random");
        System.out.println("Prints a random recipe from the list of all recipes");
        System.out.println(DIVIDER);
    }

    private static void printMakeCommandHelp() {
        System.out.println("Command: make <recipe number>");
        System.out.println("Removes all the ingredients needed to make a specified recipe from inventory");
        System.out.println(DIVIDER);
    }

    private static void printClearCommandHelp() {
        System.out.println("Command: clear");
        System.out.println("Clears all the ingredient in the inventory");
        System.out.println(DIVIDER);
    }

    private static void printHelpMessageHeader() {
        System.out.println("COMMAND SUMMARY:");
        System.out.println(DIVIDER);
    }

    private static void printByeCommandHelp() {
        System.out.println("Command: bye");
        System.out.println("Exits the program");
        System.out.println(DIVIDER);
    }

    private static void printRecipeAllHelp() {
        System.out.println("Command: recipe all");
        System.out.println("Lists out all the recipes currently stored in the recipe inventory.");
        System.out.println(DIVIDER);
    }

    private static void printRecipePossibleHelp() {
        System.out.println("Command: recipe possible");
        System.out.println("Lists out all the recipes that can be made by the ingredients that are currently " +
                "in the ingredient inventory.");
        System.out.println(DIVIDER);
    }

    private static void printRemoveCommandHelp() {
        System.out.println("Command: remove <ingredient> /qty <quantity>");
        System.out.println("Remove an ingredient of specified quantity from your ingredient inventory.");
        System.out.println(DIVIDER);
    }

    private static void printAddCommandHelp() {
        System.out.println("Command: add <ingredient> /qty <quantity>");
        System.out.println("Add an ingredient of specified quantity to your ingredient inventory.");
        System.out.println(DIVIDER);
    }

    private static void printListIngredientsHelp() {
        System.out.println("Command: ingredients list");
        System.out.println("Lists out current ingredients and their respective quantities stored in the fridge.");
        System.out.println(DIVIDER);
    }

    //@@author EthanYidong
    private static void printSearchIngredientsHelp() {
        System.out.println("Command: ingredients search");
        System.out.println("Searches the ingredients database for the specified keyword.");
        System.out.println("If keyword is omitted, all ingredients will be printed.");
        System.out.println(DIVIDER);
    }

}

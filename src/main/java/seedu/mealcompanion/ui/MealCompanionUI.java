package seedu.mealcompanion.ui;

import java.util.Scanner;

/**
 * Contains the UI helpers for the current MealCompanionSession session.
 */
public class MealCompanionUI {

    private static final String DIVIDER = "\n_______________________________________________________________________"
            + "____________________________________\n";

    private Scanner scanner;

    public MealCompanionUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Returns a string representing the next command the user enters.
     * @return the next command string.
     */
    public String getNextCommandString() {
        return this.scanner.nextLine();
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

    /**
     * Prints help messages for the "help" command.
     */

    public void printHelpMessages() {
        printHelpMessageHeader();
        PrintIngredientHelp();
        PrintRecipeHelp();
        printByeCommandHelp();
    }

    private static void PrintIngredientHelp() {
        printAddCommandHelp();
        printRemoveCommandHelp();
        PrintMakeCommandHelp();
        PrintClearCommandHelp();
        printListIngredientsHelp();
        printSearchIngredientsHelp();
    }

    private static void PrintRecipeHelp() {
        printRecipePossibleHelp();
        printRecipeAllHelp();
        PrintRecipeRandomHelp();
        PrintRecipeDetailHelp();
        PrintRecipeNeedHelp();
    }

    private static void PrintRecipeNeedHelp() {
        System.out.println("Command: recipe detail <recipe name>");
        System.out.println("Prints all the missing ingredients needed to make a specified recipe");
        System.out.println(DIVIDER);
    }

    private static void PrintRecipeDetailHelp() {
        System.out.println("Command: recipe detail <recipe index/name>");
        System.out.println("Prints all the details of the specified recipe");
        System.out.println(DIVIDER);
    }

    private static void PrintRecipeRandomHelp() {
        System.out.println("Command: recipe random");
        System.out.println("Prints a random recipe from the list of all recipes");
        System.out.println(DIVIDER);
    }

    private static void PrintMakeCommandHelp() {
        System.out.println("Command: make <recipe name>");
        System.out.println("Removes all the ingredients needed to make a specified recipe from inventory");
        System.out.println(DIVIDER);
    }

    private static void PrintClearCommandHelp() {
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

    private static void printSearchIngredientsHelp() {
        System.out.println("Command: ingredients search");
        System.out.println("Searches the ingredients database for the specified keyword.");
        System.out.println("If keyword is omitted, all ingredients will be printed.");
        System.out.println(DIVIDER);
    }
}

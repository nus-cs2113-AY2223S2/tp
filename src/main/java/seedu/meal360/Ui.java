package seedu.meal360;

import java.util.Scanner;

public class Ui {

    private static final int BOXWIDTH = 100;
    private static final String SEPARATORCHAR = "-";

    public void printSeparator() {
        String separatorLine = SEPARATORCHAR.repeat(BOXWIDTH);
        System.out.println(separatorLine);
    }

    public void printWelcomeMessage() {
        Scanner takeName = new Scanner(System.in);
        printSeparator();
        String logo = " __  __          _ ____  __  __\n" +
                "|  \\/  |___ __ _| |__ / / / /  \\\n" +
                "| |\\/| / -_) _` | ||_ \\/ _ \\ () |\n" +
                "|_|  |_\\___\\__,_|_|___/\\___/\\__/\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        String userName = takeName.nextLine();
        printSeparator();
        System.out.println("Hello " + userName + "!" + " Welcome to Meal360, your ultimate Recipe Manager!");
        printSeparator();
    }

    public void printGoodbyeMessage() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public String formatMessage(String message) {
        return String.format("| %-97s|", message);
    }

    public void printMessage(String message) {
        String outputMessage = formatMessage(message);
        System.out.println(outputMessage);
    }

    public void printRecipe(Recipe recipe) {
        printSeparator();
        System.out.println(formatMessage("Name of recipe: " + recipe.getName()));
        for (String ingredient : recipe.getIngredients().keySet()) {
            String outputMessage = String.format("%s(%d)", ingredient, recipe.getIngredients().get(ingredient));
            System.out.println(formatMessage(outputMessage));
        }
        printSeparator();
    }
}

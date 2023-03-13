package seedu.meal360;

import java.util.HashMap;

public class Ui {

    private static final int BOXWIDTH = 100;
    private static final String SEPARATORCHAR = "-";

    public void printSeparator() {
        String separatorLine = SEPARATORCHAR.repeat(BOXWIDTH);
        System.out.println(separatorLine);
    }

    public void printWelcomeMessage() {
        printSeparator();
        String logo = " __  __          _ ____  __  __\n" +
                "|  \\/  |___ __ _| |__ / / / /  \\\n" +
                "| |\\/| / -_) _` | ||_ \\/ _ \\ () |\n" +
                "|_|  |_\\___\\__,_|_|___/\\___/\\__/\n";
        System.out.println("Welcome to Meal360, your ultimate Recipe Manager!\n" + logo);
        printSeparator();
    }

    public void printGoodbyeMessage() {
        printSeparator();
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
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
        System.out.println(formatMessage("Name of recipe: " + recipe.getName()));
        for (String ingredient : recipe.getIngredients().keySet()) {
            String outputMessage = String.format("%s(%d)", ingredient, recipe.getIngredients().get(ingredient));
            System.out.println(formatMessage(outputMessage));
        }
    }

    public void printWeeklyPlan(WeeklyPlan weeklyPlan) {
        if (weeklyPlan.isEmpty()) {
            printMessage("Your weekly plan is empty!");
        } else {
            printMessage("Here is your weekly plan:");
            weeklyPlan.forEach((recipe, count) -> {
                String outputMessage = String.format("%s: %d times", recipe, count);
                printMessage(outputMessage);
            });
        }

    }
}

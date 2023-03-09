package seedu.meal360;

import java.util.Scanner;

public class Meal360 {

    /**
     * Main entry-point for the java.duke.Meal360 application.
     */

    private static Boolean canExit = false;
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static final Database database = new Database();
    private static final RecipeList recipeList = new RecipeList();

    public static void startApp() {
        ui.printWelcomeMessage();
    }

    public static void receiveInput(String input) {
        ui.printMessage(input);
        if (input.equalsIgnoreCase("bye")) {
            canExit = true;
        }
    }

    public static void exitApp() {
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        startApp();

        String line;
        Scanner userInput = new Scanner(System.in);

        do {
            line = userInput.nextLine();
            receiveInput(line);
        } while (!canExit);

        exitApp();
    }
}

package seedu.meal360;

public class Ui {

    private static final int BOXWIDTH = 100;
    private static final String SEPARATORCHAR = "-";

    public void printSeparator() {
        String separatorLine = SEPARATORCHAR.repeat(BOXWIDTH);
        System.out.println(separatorLine);
    }

    public void printWelcomeMessage() {
        printSeparator();
        String logo = " __  __          _ ____  __  __\n" + "|  \\/  |___ __ _| |__ / / / /  \\\n"
                + "| |\\/| / -_) _` | ||_ \\/ _ \\ () |\n" + "|_|  |_\\___\\__,_|_|___/\\___/\\__/\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
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
        printSeparator();
        System.out.println(outputMessage);
        printSeparator();
    }

    public void printRecipe(Recipe recipe) {
        printMessage(recipe.getName());
    }
}

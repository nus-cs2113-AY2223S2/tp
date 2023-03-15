package seedu.moneymind.command;

import seedu.moneymind.Ui;

public class HelpCommand implements Command {
    @Override
    public void execute(Ui ui) {
        System.out.println("Here are the commands you can use:");
        System.out.println("1. help - show instructions on how to use the app");
        System.out.println("Format: help");
        System.out.println("Example: help\n");
        System.out.println("2. category - add a category to your list");
        System.out.println("Format: category <category name>");
        System.out.println("Example: category food\n");
        System.out.println("3. event - add an event to a category");
        System.out.println("Format: event <event name> b/<budget number> e/<expense number>");
        System.out.println("Example: event lunch b/10 e/5\n");
        System.out.println("4. view - view all the events in a category or all the categories");
        System.out.println("Format: view <category name>");
        System.out.println("If you want to view all the categories, just type view");
        System.out.println("Example: view food\n");
        System.out.println("5. delete - delete an event from a category or a whole category");
        System.out.println("Format: delete c/<category name> e/<event name>");
        System.out.println("If you want to delete a whole category, you can remove e/<event name>");
        System.out.println("Example: delete c/food e/lunch");
        System.out.println("Example: delete c/food\n");
        System.out.println("6. bye - exit the app");
        System.out.println("Format: bye");
        System.out.println("Example: bye\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

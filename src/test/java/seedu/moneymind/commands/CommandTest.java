package seedu.moneymind.commands;

import seedu.moneymind.category.Category;
import seedu.moneymind.category.CategoryList;
import seedu.moneymind.command.CategoryCommand;
import seedu.moneymind.parser.Parser;
import seedu.moneymind.event.Event;
import seedu.moneymind.exceptions.InvalidCommandException;
import seedu.moneymind.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandTest {
    Category food;
    Category book;
    Event salad;
    Event pizza;
    Event harryPotter;
    Event lordOfTheRings;

    public CommandTest() {
        food = new Category("food");
        book = new Category("book");
        salad = new Event("salad", 100);
        pizza = new Event("pizza", 200);
        harryPotter = new Event("Harry Potter", 70);
        lordOfTheRings = new Event("Lord of the Rings", 90);
    }

    public void setup() {
        food.addEvent(salad);
        food.addEvent(pizza);
        book.addEvent(harryPotter);
        book.addEvent(lordOfTheRings);
        CategoryList.categories.add(food);
        CategoryList.categories.add(book);
        CategoryCommand.categoryMap.put("food", 0);
        CategoryCommand.categoryMap.put("book", 1);
    }

    public ByteArrayOutputStream executeInput(String input) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        // Get help from chatGPT for the next 2 lines
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try {
            seedu.moneymind.command.Command command = parser.parseNextCommand(input);
            command.execute(ui);
        } catch (InvalidCommandException error) {
            error.showErrorMessage();
        } catch (Exception e) {
            // no code needed here
        }
        return outputStream;
    }

    /**
     * Clears all static variables
     */
    public static void clear() {
        CategoryList.categories.clear();
        CategoryCommand.categoryMap.clear();
    }

}

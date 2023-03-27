package seedu.duke.utils.parsers;

import seedu.duke.commands.CategoryCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class CategoryParser extends Parser {
    public CategoryParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    @Override
    public void run() {
        // upc/[UPC] c/[Category] to edit category
        // list to list all categories
        // [Category] to list all items in a specific category
        // System.out.println(rawInput);
        try {
            String[] categoryCommandType = rawInput.split(" ", 2);
            //assert categoryCommandType[0].contains("upc/") : "UPC Code is not present in user category command?";
            if (categoryCommandType[0].equals("list") || categoryCommandType[0].startsWith("upc/")) {
                Command categoryCommand = new CategoryCommand(inventory, categoryCommandType);
                categoryCommand.run();
            }
            if (!categoryCommandType[0].equals("list") &&
                    !categoryCommandType[0].startsWith("upc/") || categoryCommandType.length < 1) {
                throw new CategoryFormatException();
            }
        } catch (CategoryFormatException e) {
            //editawayRTE
            Ui.printInvalidCategoryCommand();
        }
    }
}

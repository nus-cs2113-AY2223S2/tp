package seedu.duke.utils.parsers;

import seedu.duke.commands.CategoryCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.CategoryFormatException;
import seedu.duke.objects.Inventory;

public class CategoryParser extends Parser {
    public CategoryParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    @Override
    public void run() {
        try {
            if (rawInput.equals("list") || rawInput.equals("table")) {
                Command categoryCommand = new CategoryCommand(inventory, rawInput);
                categoryCommand.run();
            } else {
                throw new CategoryFormatException();
            }
        } catch (CategoryFormatException e) {
            e.incorrectParameters();
        }
    }
}

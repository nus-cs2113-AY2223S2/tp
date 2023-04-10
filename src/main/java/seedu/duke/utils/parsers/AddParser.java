package seedu.duke.utils.parsers;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.OutOfRangeException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.types.Types;
import seedu.duke.utils.Ui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddParser extends Parser {

    public AddParser(final String rawInput, final Inventory inventory) {
        super(rawInput, inventory);
    }

    /**
     * Handles the "add" command by parsing the user's input into separate input parameters using regex.
     */
    @Override
    public void run() {
        try {
            if (rawInput.length() == EMPTY) {
                throw new MissingParametersException();
            }
            Matcher matcher = Pattern.compile(ADD_REGEX).matcher(rawInput);
            if (!matcher.matches()) {
                Ui.printInvalidAddCommand();
                return;
            }
            String name = matcher.group(NAME_INDEX);
            name = name.trim();
            String upc = matcher.group(UPC_INDEX);
            upc = upc.trim();
            BigInteger quantity = new BigInteger(matcher.group(QTY_INDEX));
            BigDecimal price = new BigDecimal(matcher.group(PRICE_INDEX));
            String category = matcher.group(CAT_INDEX);

            if (quantity.compareTo(new BigInteger(String.valueOf(Types.MAX_QTY))) > 0 ||
                    price.compareTo(BigDecimal.valueOf(Types.MAX_QTY)) > 0) {
                throw new OutOfRangeException();
            }

            Item newItem = new Item(name, upc, quantity.intValue(), price.doubleValue(), category);
            Command addCommand = new AddCommand(inventory, newItem);
            addCommand.run();
        } catch (MissingParametersException | NumberFormatException e) {
            Ui.printInvalidAddCommand();
        } catch (OutOfRangeException e) {
            e.printOutOfRange();
        }
    }
}

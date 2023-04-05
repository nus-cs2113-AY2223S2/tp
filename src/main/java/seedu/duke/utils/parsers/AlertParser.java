package seedu.duke.utils.parsers;

import seedu.duke.commands.AddAlertCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveAlertCommand;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.exceptions.OutOfRangeException;
import seedu.duke.objects.Alert;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertParser extends Parser {

    public AlertParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    /**
     * Parses the add alert command and prints an error message if wrong inputs from the user are detected.
     *
     * @param rawInput  The user input.
     * @param inventory The inventory to be modified.
     */
    private void parseAddAlert(String rawInput, Inventory inventory) {

        Pattern pattern = Pattern.compile(ALERT_ADD_REGEX);
        Matcher matcher = pattern.matcher(rawInput);

        try {
            if (matcher.matches()) {

                BigInteger stock = new BigInteger(matcher.group(STOCK_INDEX));

                if (stock.compareTo(new BigInteger("99999999")) > 0) {
                    throw new OutOfRangeException();
                }

                Alert newAlert = new Alert(matcher.group(ALERT_UPC_INDEX), matcher.group(ADD_MINMAX_INDEX),
                        stock.toString());

                Command addAlertCommand = new AddAlertCommand(inventory, newAlert);
                addAlertCommand.run();

            } else {
                System.out.println("NEGATIVE HERE");
                Ui.printInvalidAddAlertCommand();
            }
        } catch (OutOfRangeException e) {
            e.printOutOfRange();
        }
    }


    /**
     * Parses the remove alert command and prints an error message if wrong inputs from the user are detected.
     *
     * @param rawInput  The user input.
     * @param inventory The inventory to be modified.
     */
    private void parseRemoveAlert(String rawInput, Inventory inventory) {
        Pattern pattern = Pattern.compile(ALERT_REMOVE_REGEX);
        Matcher matcher = pattern.matcher(rawInput);

        if (matcher.matches()) {
            Command removeAlertCommand = new RemoveAlertCommand(inventory, matcher.group(ALERT_UPC_INDEX),
                    matcher.group(REMOVE_MINMAX_INDEX));
            removeAlertCommand.run();
        } else {
            Ui.printInvalidRemoveAlertCommand();
        }

    }

    /**
     * Processes the "alert" command by delegating it to either add or remove alert parsing functions and prints an
     * error message if wrong inputs from the user are detected.
     */

    @Override
    public void run() {
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }

            Pattern pattern = Pattern.compile(ALERT_REGEX);
            Matcher matcher = pattern.matcher(rawInput);

            if (matcher.matches()) {
                switch (matcher.group(ALERT_COMMAND_INDEX)) {
                case "add":
                    parseAddAlert(matcher.group(ALERT_DETAILS_INDEX), inventory);
                    break;
                case "remove":
                    parseRemoveAlert(matcher.group(ALERT_DETAILS_INDEX), inventory);
                    break;
                default:
                    Ui.printInvalidAlertKeyword();
                }
            } else {
                Ui.printInvalidAlertParameter();
            }
        } catch (MissingParametersException e) {
            e.missingAlertParameters();
        }

    }
}

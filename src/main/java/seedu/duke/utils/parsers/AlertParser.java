package seedu.duke.utils.parsers;

import seedu.duke.commands.AddAlertCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.RemoveAlertCommand;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertParser extends Parser {
    private AlertList alertList;

    public AlertParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
        this.alertList = inventory.getAlertList();
    }

    //TODO: trim trailing whitespace from input string?
    //whitespace at the end of the string currently results in successful alert removal
    private void parseAddAlert(String rawInput, Inventory inventory) {
        Pattern pattern = Pattern.compile(ALERT_ADD_REGEX);
        Matcher matcher = pattern.matcher(rawInput);

        if (matcher.matches()) {
            Alert newAlert = new Alert(matcher.group(ALERT_UPC_INDEX), matcher.group(ADD_MINMAX_INDEX),
                    matcher.group(STOCK_INDEX));

            Command addAlertCommand = new AddAlertCommand(inventory, newAlert);
            addAlertCommand.run();

        } else {
            Ui.printInvalidAddAlertCommand();
        }

    }

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
            }
        } catch (MissingParametersException e) {
            e.missingAddItemParameters();
        }

    }
}

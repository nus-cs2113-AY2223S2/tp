package seedu.duke.utils.parsers;

import seedu.duke.commands.AddAlertCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertParser extends Parser{
    private AlertList alertList;
    public AlertParser(String rawInput, Inventory inventory, AlertList alertList){
        super(rawInput, inventory);
        this.alertList = alertList;
    }
    @Override
    public void run(){
        try {
            if (rawInput == null) {
                throw new MissingParametersException();
            }

            Pattern pattern = Pattern.compile(ALERT_REGEX);
            Matcher matcher = pattern.matcher(rawInput);

            if (matcher.matches()) {
                Alert newAlert = new Alert(matcher.group(UPC_INDEX), matcher.group(MINMAX_INDEX),
                        matcher.group(STOCK_INDEX));

                Command addAlertCommand = new AddAlertCommand(inventory, newAlert, alertList);
                addAlertCommand.run();

            } else {
                Ui.printInvalidAddAlertCommand();
            }
        } catch (MissingParametersException e) {
            e.missingAddItemParameters();
        }

    }
}

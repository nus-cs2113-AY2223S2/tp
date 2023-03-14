package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.AddFinancialStatement;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.DeleteFinancialStatement;
import seedu.rainyDay.command.GenerateReport;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;
import seedu.rainyDay.modules.UI;

public class Parser {
    public static Command parseUserInput(String userInput) throws IllegalArgumentException, RainyDayException {
        String action = userInput.split("\\s+")[0];
        if (action.equalsIgnoreCase(Command.COMMAND_ADD)) {
            return addStatement(userInput);
        } else if (action.equalsIgnoreCase(Command.COMMAND_DELETE)) {
            return deleteStatement(userInput);
        } else if (action.equalsIgnoreCase(Command.COMMAND_VIEW)) {
            return generateReport(RainyDay.financialReport);
//      } else if (action.equalsIgnoreCase(Command.COMMAND_HELP)) {
//          UI.displayHelp();
        } else {
            throw new RainyDayException(ErrorMessage.UNRECOGNIZED_INPUT.toString());
        }
    }

    private static AddFinancialStatement addStatement(String userInput) {
        try {
            String[] tokens = userInput.split("-", 2);
            String[] inputs = tokens[1].split("\\s+", 2);
            String flowDirection = inputs[0];
            String[] data = inputs[1].split("\\$");
            String description = data[0].trim();
            String amount = data[1];
            return new AddFinancialStatement(description, flowDirection, Integer.parseInt(amount));
        } catch (Exception e) {
            throw new IllegalArgumentException(UI.WRONG_ADD_FORMAT);
        }
    }

    public static DeleteFinancialStatement deleteStatement(String userInput) throws IllegalArgumentException {
        String[] tokens = userInput.split("\\s+");
        if (tokens.length < 2) {
            throw new IllegalArgumentException(UI.NO_DELETE_INDEX);
        }
        try {
            int index = Integer.parseInt(tokens[1]);
            return new DeleteFinancialStatement(index);
        } catch (Exception e) {
            throw new IllegalArgumentException(UI.WRONG_DELETE_INDEX);
        }
    }

    public static GenerateReport generateReport(FinancialReport financialReport) {
        return new GenerateReport(financialReport);
    }
}

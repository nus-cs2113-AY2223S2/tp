package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

import java.time.format.DateTimeFormatter;

public interface FormatReport {
    static String formatFinancialStatement(int statementIndex, FinancialStatement currentStatement) {
        String statementOutput;
        String statementName = currentStatement.getDescription();
        double statementValue = currentStatement.getValue();
        String statementCategory = currentStatement.getCategory();
        String statementDirection = currentStatement.getFlowSymbol();
        String date;
        if (currentStatement.getDate() == null) {
            date = "no date   ";
        } else {
            date = currentStatement.getDate().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        }


        String index = String.format("00000%d", statementIndex);
        index = index.substring(index.length() - 5);
        String value = String.format(" %s$%.2f            ", statementDirection, statementValue);
        value = value.substring(0, 12);
        String name = String.format("%s                              ", statementName);
        name = name.substring(0, 30);
        String category = String.format("%s                ", statementCategory);
        category = category.substring(0, 16);
        statementOutput = "|" + index + "|" + name + "|" + value + "|" + category + "|" + date + "|"
                + System.lineSeparator();

        return statementOutput;
    }

    static String formatSummary(double inflow, double outflow) {
        assert (inflow != 0 || outflow != 0);
        String inflowInformation = String.format("|Inflow: $%.2f", inflow);
        String outflowInformation = String.format("|Outflow: $%.2f", outflow);

        String remainingValueInformation = String.format("|Remaining value: $%.2f", (inflow - outflow));
        String summary = String.join(System.lineSeparator(), inflowInformation, outflowInformation,
                remainingValueInformation);
        return summary;
    }
}

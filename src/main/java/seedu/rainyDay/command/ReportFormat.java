package seedu.rainyDay.command;

import seedu.rainyDay.data.FinancialStatement;

public interface ReportFormat {
    static String formatFinancialStatement(int statementIndex, FinancialStatement currentStatement) {
        String statementName = currentStatement.getDescription();
        double statementValue = currentStatement.getValue();
        String statementCategory = currentStatement.getCategory();
        String statementDirection = currentStatement.getFlowSymbol();

        String index = String.format("00000%d", statementIndex);
        index = index.substring(index.length() - 5);
        String value = String.format(" %s$%.2f            ", statementDirection, statementValue);
        value = value.substring(0, 12);
        String name = String.format("%s                              ", statementName);
        name = name.substring(0, 30);
        String category = String.format("%s                ", statementCategory);
        category = category.substring(0, 16);
        System.out.printf("|%s|%s|%s|%s|\n", index, name, value, category);
    }
}

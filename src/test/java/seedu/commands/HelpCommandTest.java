package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.expenditure.ExpenditureList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    ExpenditureList testExpenditures = new ExpenditureList();

    @Test
    public void test_helpCommand_execute() {
        HelpCommand testHelp = new HelpCommand();
        assertEquals("\nHere are the list of commands and their respective functions. "
                + "Please take note of the FORMAT that is stated below\n"
                + "========================================================================================================================"
                +
                "\n1. Add an expenditure to the record\n" +
                "Format: EXPENDITURE_TYPE d/DATE a/AMOUNT p/DESCRIPTION\n" +
                "There are 7 EXPENDITURE_TYPE: 'Academic', 'Accommodation', 'Entertainment', " +
                "'Food', 'Transport', 'Tuition', 'Other'\n" +
                "Example: academic d/2023-02-02 a/25.10 p/NUS\n" +
                "========================================================================================================================"
                +
                "\n2. Add a lend/borrow record\n" +
                "Format: TYPE d/DATE n/NAME a/AMOUNT b/DEADLINE p/DESCRIPTION\n" +
                "TYPE should be either 'lend' or 'borrow'\n" +
                "Example: lend d/2022-02-02 n/Akshay Narayan a/25.10 b/2024-07-14 p/CS2113\n" +
                "========================================================================================================================"
                +
                "\n3. Delete a specified expenditure from the record\n" +
                "Format: delete INDEX\n" +
                "Example: delete 1 \n" +
                "========================================================================================================================"
                +
                "\n4. Edit an expenditure\n"
                + "Format: edit INDEX d/DATE a/AMOUNT p/DESCRIPTION\n" +
                "Cannot change an expenditure type\n" +
                "Example: edit 2 d/2023-02-15 a/20.00 p/Eat Food\n" +
                "========================================================================================================================"
                +
                "\n5. Edit a Lend/Borrow record\n"
                + "Format: edit INDEX d/DATE n/NAME a/AMOUNT b/DEADLINE p/DESCRIPTION\n" +
                "Cannot change a lend record to a borrow record\n" +
                "Example: edit 17 d/2022-02-02 n/Akshay Narayan a/25.10 b/2024-07-14 p/CS2113\n" +
                "========================================================================================================================"
                +
                "\n6. List all expenditures in the record in the specified currency\n"
                + "Format: list CURRENCY \n" + "A currency has to be specified for list to display.\n"
                + "Example: list SGD \n" +
                "========================================================================================================================"
                +
                "\n7. Sort the expenditure list\n"
                + "Format: sort TYPE\n" +
                "Types:\n" +
                "ascend: sort by ascending amount || descend: sort by descending amount\n" +
                "latest: sort from latest date || earliest: sort from earliest date\n" +
                "Example: sort latest\n" +
                "========================================================================================================================"
                +
                "\n8. View all expenditures and their total amount in the record BY DATE\n"
                + "Format: viewdate DATE CURRENCY\n"
                + "Example: viewdate 2023-03-29 SGD\n" +
                "========================================================================================================================"
                +
                "\n9. View all expenditures and their total amount in the record BY CATEGORY\n"
                + "Format: viewtype CATEGORY CURRENCY\n"
                + "There are 9 CATEGORIES: 'academic', 'accommodation', 'entertainment'," +
                "'food', 'transport', 'tuition', 'other', 'borrow', 'lend'\n"
                + "Example: viewtype food SGD\n" +
                "========================================================================================================================"
                +
                "\n10. Set a fixed budget for money management\n"
                + "Format: set AMOUNT\n"
                + "Example: set 150\n" +
                "========================================================================================================================"
                +
                "\n11. Check expenditure with respect to a allocated budget, which accepts an optional filters\n"
                + "Format: check\n \n" +
                "List of optional filters: \n \n" +
                "By date(yyyy-mm-dd)\n" + "Format: check d/DATE\n" +
                "Example of checking budget by date: check d/2023-03-29\n \n" +
                "By year(yyyy)\n" + "Format: check y/YEAR\n"
                + "Example of checking budget by year: check y/2023\n \n" +
                "By expenditure category \n" + "Format: check t/CATEGORY\n" +
                "Example of checking budget by category: check t/academic\n" +
                "========================================================================================================================"
                +
                "\n12. Finding expenditure records by description keyword\n"
                + "Format: find keyword\n" +
                "========================================================================================================================"
                +
                "\n13. Duplicates an existing expenditure record from the expenditure list and append to the list\n"
                + "Format: duplicate INDEX\n" +
                "========================================================================================================================"
                +
                "\n14. Display the conversion rates used in MyLedger\n"
                + "Format: showrates\n" +
                "========================================================================================================================"
                +
                "\n15. Marking a lend or borrow expenditure record\n"
                + "Format: mark INDEX\n" +
                "========================================================================================================================"
                +
                "\n16. Unmarking a lend or borrow expenditure record\n"
                + "Format: unmark INDEX\n" +
                "========================================================================================================================",
                testHelp.execute(testExpenditures).getCommandResult());

    }
}

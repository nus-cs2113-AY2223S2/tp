package seedu.ui;

public class Ui {
    public static final String LOGO = " _  _  _  _  __    ____  ____   ___  ____  ____\n"
            + "( \\/ )( \\/ )(  )  (  __)(    \\ / __)(  __)(  _ \\\n" +
            "/ \\/ \\ )  / / (_/\\ ) _)  ) D (( (_ \\ ) _)  )   / \n" +
            "\\_)(_/(__/  \\____/(____)(____/ \\___/(____)(__\\_)\n";
    public static final String HORIZONTAL_LINE = "===================================================" + 
        "=====================================================================\n";
    public static final String WELCOME_MESSAGE = "Hello there, Welcome to\n";
    public static final String INSTRUCTIONS = "\nMyLedger is a University financial manager made for students!\n";
    public static final String HELP_MESSAGE = "If this is your first time here, "
            + "Type <help> to learn more about the commands\n";
    public static final String HELP_PAGE = "\nHere are the list of commands and their respective functions. "
            + "Please take note of the FORMAT that is stated below\n"
            + HORIZONTAL_LINE +
            "\n1. Add an expenditure to the record\n" +
            "Format: EXPENDITURE_TYPE d/DATE a/AMOUNT p/DESCRIPTION\n" + 
            "There are 7 EXPENDITURE_TYPE: 'Academic', 'Accommodation', 'Entertainment', " +
            "'Food', 'Transport', 'Tuition', 'Other'\n" +
            "Example: academic d/2023-02-02 a/25.10 p/NUS\n" +
            HORIZONTAL_LINE +
            "\n2. Add a lend/borrow record\n" +
            "Format: TYPE d/DATE n/NAME a/AMOUNT b/DEADLINE p/DESCRIPTION\n" +
            "TYPE should be either 'lend' or 'borrow'\n"+
            "Example: lend d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 p/CS2113\n" +
             HORIZONTAL_LINE +
            "\n3. Delete a specified expenditure from the record\n" +
            "Format: delete INDEX\n" +
            "Example: delete 1 \n" +
            HORIZONTAL_LINE +
            "\n4. Edit an expenditure\n"
            + "Format: edit INDEX d/DATE a/AMOUNT p/DESCRIPTION\n" +
            "Cannot change an expenditure type\n" +
            "Example: edit 2 d/2023-02-15 a/20.00 p/Eat Food\n" +
            HORIZONTAL_LINE +
            "\n5. Edit a Lend/Borrow record\n"
            + "Format: edit INDEX d/DATE n/NAME a/AMOUNT b/DEADLINE p/DESCRIPTION\n" +
            "Cannot change a lend record to a borrow record\n" +
            "Example: edit 17 d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 p/CS2040\n" +
            HORIZONTAL_LINE +
            "\n6. List all expenditures in the record in the specified currency\n"
            + "Format: list CURRENCY \n" + "A currency has to be specified for list to display.\n"
            + "Example: list SGD \n" +
            HORIZONTAL_LINE +
            "\n7. Sort the expenditure list\n"
            + "Format: sort TYPE\n" +
            "Types:\n" +
            "ascend: sort by ascending amount || descend: sort by descending amount\n" +
            "latest: sort from latest date || earliest: sort from earliest date\n" +
            "Example: sort latest\n" +
            HORIZONTAL_LINE +
            "\n8. List all expenditures and their total amount in the record BY DATE\n"
            + "Format: viewdate DATE\n"
            + "Example: viewdate 2023-03-29\n" +
            HORIZONTAL_LINE +
            "\n9. List all expenditures and their total amount in the record BY CATEGORY\n"
            + "Format: viewtype CATEGORY\n"
            + "There are 9 CATEGORIES: 'academic', 'accommodation', 'entertainment'," +
            "'food', 'transport', 'tuition', 'other', 'borrow', 'lend'\n" 
            + "Example: viewtype food\n" +
            HORIZONTAL_LINE +
            "\n10. Set a fixed budget for money management\n"
            + "Format: set AMOUNT\n"
            + "Example: set 150\n" +
            HORIZONTAL_LINE +
            "\n11. Check expenditure with respect to a allocated budget, which accepts an optional filters\n"
            + "Format: check\n \n" +
            "List of optional filters: \n \n" +
            "By date(yyyy-mm-dd)\n" + "Format: check d/DATE\n" +
            "Example of checking budget by date: check d/2023-03-29\n \n" +
            "By year(yyyy)\n" + "Format: check y/YEAR\n"
            + "Example of checking budget by year: check y/2023\n \n" +
            "By expenditure category \n" + "Format: check t/CATEGORY\n" +
            "Example of checking budget by category: check t/academic\n" +
            HORIZONTAL_LINE +
            "\n12. Display the conversion rates used in MyLedger\n"
            + "Format: showrates\n" +
            HORIZONTAL_LINE;

        

    public static void greetUser() {
        System.out.println(WELCOME_MESSAGE + LOGO + INSTRUCTIONS + HELP_MESSAGE);
    }

}

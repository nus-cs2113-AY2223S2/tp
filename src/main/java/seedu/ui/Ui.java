package seedu.ui;

public class Ui {
    public static final String LOGO = " _  _  _  _  __    ____  ____   ___  ____  ____\n"
            + "( \\/ )( \\/ )(  )  (  __)(    \\ / __)(  __)(  _ \\\n" +
            "/ \\/ \\ )  / / (_/\\ ) _)  ) D (( (_ \\ ) _)  )   / \n" +
            "\\_)(_/(__/  \\____/(____)(____/ \\___/(____)(__\\_)\n";
    public static final String HORIZONTAL_LINE = "------------------------------------------------------------------------------------------------------------------";

    public static final String WELCOME_MESSAGE = "Hello there, Welcome to\n";
    public static final String INSTRUCTIONS = "\nHere are the list possible commands: "
            + "add, delete, edit, list, borrow, lend, exit\n";
    public static final String HELP_MESSAGE = "If this is your first time here, "
            + "Type <help> to learn more about the commands\n";
    public static final String HELP_PAGE = "Here are the list of commands and their respective functions. "
            + "Please take note of the FORMAT that is stated below\n"
            + HORIZONTAL_LINE +
            "\n1.add: There are 2 ways to add an expenditure to the record\n" +
            "Format: EXPENDITURE_TYPE d/DATE a/AMOUNT s/DESCRIPTION\n" +
            "        LEND/BORROW d/DATE n/(LEND/BORROW)_NAME a/AMOUNT b/DEADLINE s/DESCRIPTION\n" +
            HORIZONTAL_LINE +
            "\n2.delete: Deletes the specified expenditure from the record\n" +
            "Format: delete INDEX\n" +
            HORIZONTAL_LINE +
            "\n3.edit: Edits an existing expenditure in the record, please note that editing cannot change the expenditure type\n"
            +
            "Format: edit INDEX d/DATE a/AMOUNT s/DESCRIPTION\n" +
            "        edit INDEX d/DATE n/(LEND/BORROW)_NAME a/AMOUNT b/DEADLINE s/DESCRIPTION\n" +
            HORIZONTAL_LINE +
            "\n4.list: Shows a list of expenditures and loans in the record based on existing categories\n"
            +
            "Format: list /TYPE\n" +
            HORIZONTAL_LINE +
            "\n5.borrow: Keep a record of an incoming loan\n" +
            "Format: borrow a/AMOUNT n/LENDER_NAME d/BORROWED_DATE\n" + HORIZONTAL_LINE +
            "\n6.lend: Add an expenditure to the record\n" +
            "Format: lend a/AMOUNT n/BORROWER_NAME d/LENT_DATE";

    public static void greetUser() {
        System.out.println(WELCOME_MESSAGE + LOGO + INSTRUCTIONS + HELP_MESSAGE);
    }

}

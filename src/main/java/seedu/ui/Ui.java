package seedu.ui;

public class Ui {
    public static final String LOGO = " _  _  _  _  __    ____  ____   ___  ____  ____\n"
            + "( \\/ )( \\/ )(  )  (  __)(    \\ / __)(  __)(  _ \\\n" +
            "/ \\/ \\ )  / / (_/\\ ) _)  ) D (( (_ \\ ) _)  )   / \n" +
            "\\_)(_/(__/  \\____/(____)(____/ \\___/(____)(__\\_)\n";

    public static final String WELCOME_MESSAGE = "Hello there, Welcome to\n";
    public static final String INSTRUCTIONS = "\nHere are the list possible commands: "
            + "add, delete, edit, list, borrow, lend, exit\n";
    public static final String HELP_MESSAGE = "If this is your first time here, "
            + "Type <help> to learn more about the commands\n";
    public static final String HELP_PAGE = "Here are the list of commands and their respective functions. "
            + "Please take note of the FORMAT that is stated below\n"
            +
            "1. add: Add an expenditure to the record\n" +
            "Format: CATEGORY d/DATE a/AMOUNT s/DESCRIPTION\n" +
            "2. delete: Deletes the specified expenditure from the record\n" +
            "Format: delete INDEX\n" +
            "3. edit: Edits an existing expenditure in the record\n" +
            "Format: edit INDEX d/DATE a/AMOUNT s/DESCRIPTION\n" +
            "Disclaimer: For v1.0 on Borrow or Lend Expenditures, " +
            "editing can only change its borrow/lender date, amount and description\n" +
            "4. list: Shows a list of expenditures and loans in the record based on existing categories\n" +
            "Format: list /TYPE\n" +
            "5. borrow: Keep a record of an incoming loan\n" +
            "Format: borrow a/AMOUNT n/LENDER_NAME d/BORROWED_DATE\n" +
            "6. lend: Add an expenditure to the record\n" +
            "Format: lend a/AMOUNT n/BORROWER_NAME d/LENT_DATE";

    public static void greetUser() {
        System.out.println(WELCOME_MESSAGE + LOGO + INSTRUCTIONS + HELP_MESSAGE);
    }

}

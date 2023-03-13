package seedu.ui;

public class Ui {
    public static final String LOGO = " _  _  _  _  __    ____  ____   ___  ____  ____\n"
            + "( \\/ )( \\/ )(  )  (  __)(    \\ / __)(  __)(  _ \\\n" +
            "/ \\/ \\ )  / / (_/\\ ) _)  ) D (( (_ \\ ) _)  )   / \n" +
            "\\_)(_/(__/  \\____/(____)(____/ \\___/(____)(__\\_)\n";

    public static final String WELCOME_MESSAGE = "Hello there, Welcome to\n";
    public static final String INSTRUCTIONS = "Here are the list possible commands: "
            + "add, delete, edit, list, borrow, lend, exit\n";
    public static final String HELP_MESSAGE = "If this is your first time here, "
            + "Type <help> to learn more about the commands\n";

    public static void greetUser() {
        System.out.println(WELCOME_MESSAGE + LOGO + INSTRUCTIONS + HELP_MESSAGE);
    }

}

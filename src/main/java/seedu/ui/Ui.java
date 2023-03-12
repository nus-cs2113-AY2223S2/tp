package seedu.ui;

public class Ui {
    public static final String logo = " _  _  _  _  __    ____  ____   ___  ____  ____\n"
            + "( \\/ )( \\/ )(  )  (  __)(    \\ / __)(  __)(  _ \\\n" +
            "/ \\/ \\ )  / / (_/\\ ) _)  ) D (( (_ \\ ) _)  )   / \n" +
            "\\_)(_/(__/  \\____/(____)(____/ \\___/(____)(__\\_)\n";

    public static final String WELCOMEMESSAGE = "Hello there, Welcome to\n";
    public static final String INSTRUCTIONS = "Here are the list possible commands: add, delete, edit, list, borrow, lend, exit\n"
            + "If this is your first time here, Type <help> to learn more about the commands";

    public static void greetUser() {
        System.out.println(WELCOMEMESSAGE + logo + INSTRUCTIONS);
    }

}

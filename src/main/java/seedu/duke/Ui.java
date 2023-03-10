package seedu.duke;

import static seedu.duke.ColorCode.*;

public class Ui {
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO =
            "    /|    //| |     // | |     //   ) )  //   / / //   ) )\n"
                    + "   //|   // | |    //__| |    //        //   / / ((        \n"
                    + "  // |  //  | |   / ___  |   //  ____  //   / /    \\\\\\\\      \n"
                    + " //  | //   | |  //    | |  //    / / //   / /       ) )   \n"
                    + "//   |//    | | //     | | ((____/ / ((___/ / ((___ / /    ";
    public static final String GREET_MESSAGE = "Welcome to MagusStock. How may I assist you today?";
    public static final String EXIT_MESSAGE = "Hope you had an enjoyable experience. See you next time!";
    public static final String UNKNOWN_COMMAND = "I don't understand that command, please refer to the user guide " +
            "for all available commands";
    public static final String INVALID_ADD = "Wrong/Incomplete Format! Please add new items in the following format: " +
            "add n/[name] upc/[UPC] qty/[quantity] p/[price]";
    public static final String DUPLICATE_ADD = "Duplicate item found! Please add another item with a different UPC";
    public static final String SUCCESS_ADD = "Successfully added the item(s) into the system!";
    public static final String INVALID_EDIT_FORMAT = "Wrong/Incomplete Format! Please edit items in the following " +
            "format: " + "edit upc/[UPC] {n/[Name] qty/[Quantity] p/[Price]}";
    public static final String ITEM_NOT_FOUND = "Edit failed! Reason: Item not found in database. Please add item " +
            "first!";
    public static final String SUCCESS_EDIT = "Successfully edited the following item:";
    public Ui() {
        greetUser();
    }

    public static void printExitMessage() {
        System.out.println(LINE);
        System.out.println(EXIT_MESSAGE);
        System.out.println(LINE);
    }

    public static void greetUser() {
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println(GREET_MESSAGE);
        System.out.println(LINE);
    }

    public static void printUnknownCommand() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + UNKNOWN_COMMAND + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printInvalidAddCommand() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + INVALID_ADD + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printDuplicateAdd() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + DUPLICATE_ADD + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printSuccessAdd(){
        System.out.println(LINE);
        System.out.println(ANSI_GREEN + SUCCESS_ADD + ANSI_RESET);
        System.out.println(LINE);
    }

    /**
     * Informs the user that his/her edit command is of the wrong format, by the printing a string to show the correct
     * format instead.
     */
    public static void printInvalidEditCommand() {
        System.out.println(LINE);
        System.out.println(INVALID_EDIT_FORMAT);
        System.out.println(LINE);
    }

    /**
     * Prints a string to inform the user that the item with the specified UPC code cannot be found inside the database.
     */
    public static void printItemNotFound() {
        System.out.println(LINE);
        System.out.println(ITEM_NOT_FOUND);
        System.out.println(LINE);
    }

    /**
     * Prints the updated version of the item in question in order to inform the user of the changes made by him or her.
     *
     * @param oldItem The item containing the old attributes.
     * @param updatedItem The same item but with new attributes as defined by the user.
     */
    public static void printEditDetails(Item oldItem, Item updatedItem) {
        System.out.println(LINE);
        System.out.println(ANSI_BLUE + SUCCESS_EDIT + ANSI_RESET + "\n");
        System.out.println(ANSI_RED + "Before Update: " + ANSI_RESET);
        System.out.println("Item Name: " + oldItem.getName() + "\n" + "UPC Code: " + oldItem.getUpc() + "\n" +
                "Quantity Available: " + oldItem.getQuantity() +  "\n" + "Item Price: " + oldItem.getPrice());
        System.out.println("\n" + ANSI_GREEN + "After Update: " + ANSI_RESET);
        System.out.println("Item Name: " + updatedItem.getName() + "\n" + "UPC Code: " + updatedItem.getUpc() + "\n" +
                "Quantity Available: " + updatedItem.getQuantity() +  "\n" + "Item Price: " + updatedItem.getPrice());
        System.out.println(LINE);
    }
}

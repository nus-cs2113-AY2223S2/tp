package seedu.mealcompanion.ui;

import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

/**
 * Contains the UI helpers for the current MealCompanionSession session.
 */
public class MealCompanionUI {

    private static final String DIVIDER = "_______________________________________________________________________"
            + "__________________________________________";

    private Scanner scanner;

    public MealCompanionUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Returns a string representing the next command the user enters.
     * @return the next command string.
     */
    public String getNextCommandString() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        } else {
            return "help"; // return default command if there is no input
        }
    }


    /**
     * Prints the Meal Companion logo
     *
     * @author aaronxujiachen
     */
    public void printLogo() {
        String logo = "                                                                                                   \n" +
                "                                                                                                    \n" +
                "                 .:                                                                                 \n" +
                "                  :~:            ......                                                             \n" +
                "               :^:. :^   .:^~!!!!!~~~~~~!~~^^.                                                      \n" +
                "             ..  .:   :~7!!^:..           .:^~!!~.                                                  \n" +
                "             :::^.  ^?7^.                      .^!7:                                   :^.          \n" +
                "                   !J:                  ..        :J:            .:^^~~~~~~~~^::.    :~^  ..        \n" +
                "                  :Y^    .:   ^        .~!~        ^J       .^~!!~~^^:::::::^^~!77!^::  :^:.        \n" +
                "                  7?.    ~7.:^J!~~~~~~~~!J~::~~     J^    :!!^:                  .:~77^ ...::^.     \n" +
                "      ..::^~~!!777YJ. .^!77~^^::..........:^^!!!~:  !7   :J:                         :?7 .:..       \n" +
                "   .77????JJJ77?JJ7Y7!?~.                       :~7:^J   J:       :^        .~7.      .Y^           \n" +
                "   .Y!J7!7???7!7??7?Y?7~^::............... ....::^?~.J. ~?    .7..7?^^~~~~~~^7?::.::   !J           \n" +
                "    ?7??77?J?777?J?75!^Y!~!?J7!!!!!!!!!!!!?J7~~J?:.  ^J ?^  :~!?!~^^::......::^^~!?7~: :Y.          \n" +
                "    ~J7?777?J?77???!YJ^Y:.:^::............::^..!J^^   Y:Y. ?!:                      :7?:J^          \n" +
                "    .Y!J777???77?J?7?Y~Y...:JY~.........:JY~...^Y^.  ^?^J .7~~^^^::::::::::::::::^^^~!?.7?          \n" +
                "     ?7??77?J?777?J?7YJ!....!?^.........:!?^....J!:^!7.J: .:~Y^!??!~~~~~~~~~~~7??~^??^: :Y:         \n" +
                "     ^J7?777JJ777???!YJ?..:::...::...:^....:::..J!^^: .Y  .^!Y.::::...........:.:..!J!~  J~         \n" +
                "      J!?777?J?77?J?7Y~?!.:::...^7~^~!^...::::.?!      !7. :??..7P5^.........!!?!..^Y^. ~J.         \n" +
                "      ~J77!7?J?777?JJJ. J~........:::.........7?        ^7~!?:..:!!:.:::::::.:.::...!?^!~           \n" +
                "       ~7?77???7!7YJ~   .77^^::..:....:..::^~7!            77.::::...?!^^^!?...::::.75?^~:          \n" +
                "         .^!7JJ?J?^       :?J5JYJ~????~JYJ5?J!.       :^~^^^!7!^.....:~~~~~:.....^!JY?5JJYJ!        \n" +
                "             :P?Y7       .7~:5?5J^:??:^75?5~ :!!~:  ~7!:^!:~?!^~!!!~^:::::::^^!!!7Y^Y77^?7Y?J.      \n" +
                "            77?YJ!!7~   ^?: ~5?5~7!^^!7^YJY?    :!!?? .: ....7?.~7J5YY7J?J75Y5?7~.?~.^!:.^!?^       \n" +
                "           :577Y??7:Y7~!7   7Y?JJJJJJJJJJ?J5!~:   .5!~!!~~!?!!YY^ 7YYY~!!!~JYY7 ^!757~^^~!7.        \n" +
                "           .Y?7?P5!^J. .   ^5J????????Y??JJ5..~?~ ^YY5Y77!!!!!Y! .YJJJ?????JJ?Y.  !J.:^Y!.          \n" +
                "            ~Y?J5J5J~:. .^775??????????????5^.~7::Y~J?5YJ~~~!?7 ~JP???????Y?JJ5J~ ~.  :J            \n" +
                "             ..7575::~~~!~.^5??????????????YY!:   !?^^:?Y5JY!^^7^~5???????J???5!^7~:.^J:            \n" +
                "               :5?Y~       !Y??????????????JY    :77!!!!JYJ?~!^  ?Y???????????Y?  :^~~.             \n" +
                "                YJJ?       JJ???????????????P7~:!7:   ..         Y?????????????Y                    \n" +
                "                !57Y.     .5J???????????????5~~5^               :5?????????????5:                   \n" +
                "                :5J5:     :J!!!!!!!!!!!!!!!~??!^                ~Y7777777777777Y~                   \n" +
                "                 .^:                                            .................                   \n" +
                "                                                                                                    \n";
        System.out.println(logo);
    }

    /**
     * Prints the welcome message.
     */
    public void printIntroduction() { System.out.println("Welcome to Meal Companion!"); }

    /**
     * Prints the farewell image to user
     *
     * @author aaronxujiachen
     */
    public void printFarewell() {
        System.out.println("Thank you for using MealCompanion. Have a great day!");
        String bye = "                                                                                                    \n" +
                "                                                                                                    \n" +
                "                                                                .JP?                                \n" +
                "                                                                .#@@7                               \n" +
                "                                                      :~7YPG^    !@@B                               \n" +
                "                                               .~^ .5#&@&BPY:     5@@7                              \n" +
                "                                         .!~   !@@! 7&@B.         :&@#.                             \n" +
                "                                 .~??!:  ~@@Y  ~@@?  ?@@Y5B&G.     ?@@?                             \n" +
                "                               :5&@&#@@5. !&@G:~@@!   B@@#57^       JG7                             \n" +
                "                               ~BP!. 7@@!  :G@&G@@^   !@@?    :^    :P#G^                           \n" +
                "                                .P#!^B@@7:   7#@@G     P@&7?P#@&^   :G#B^                           \n" +
                "                                .#@B5BGB@@5.   5@&~    .5#&#GY!:      .                             \n" +
                "                                 7@@7   7@@7   .B@&.     ..                                         \n" +
                "                                  P@&JJJB@&^    :?7                                                 \n" +
                "                                  .B@@BG5?:        ^!7!:                                            \n" +
                "                                   :7~          ^5&@##&@B7    ::.                                   \n" +
                "                                              .5@@Y^  .?@@~~5#&&&#Y^                                \n" +
                "                ^5Y                          ^#@G:     !@@#@&J~:~Y@@7                               \n" +
                "               !&@J                         ~&@Y      :#@@@Y.     G@G...                            \n" +
                "               P#!  ^?^       :^^:         ^&@Y      .B@@&!      ^&@@&&&G7.                         \n" +
                "             :J!   ~&@!    .Y#&&&&#5~     ^#@5      .B@@#^      :#@@P!^7#@G.                        \n" +
                "             B@Y  :&@7     P@#~::~Y&@P:  ^#@P      .B@@B:      ~#@G^    !@@~                        \n" +
                "            7@#.  P@5      P@B     .P@#:^#@P      .B@@G.      7@@Y      G@B                         \n" +
                "            G@J  ^@@^      ~@@7      G@##@5      .G@@P.     .5@@7     .P@&!                         \n" +
                "           :&@~  J@G        G@G      ~@@@?       P@@Y      ~#@B^     ^B@@&##P~                      \n" +
                "           ^@@^  P@5        ?@&:     .&@Y!?!^.  5@@?      J@@Y.     ?@@G7:.7@@^                     \n" +
                "           ^@@^  ?G!        7@@:     :&@?JGB##BB@&~     ^G@#!     :P@#7    !@&:                     \n" +
                "           .#@!  :5?        Y@#.     ?@&:   .^75B&#57: 7&@5.     ?&@5.    !@@!                      \n" +
                "            5@P  ^@@^      .#@5      Y@@7        :75#&#@@7     ^G@#!     J@#~                       \n" +
                "            ^@@^  ~!.      J@@^       7&@5          .~Y#@P!.  J@@Y.    ^B@5.                        \n" +
                "             !?:          ~@@?         ^&@J            .!P@#YB@B~    .Y@&!       ~~   ~~            \n" +
                "                          B@P           ?@&:              :J#@&^    7#@Y.       7@#. !@&.           \n" +
                "                         :@@7           ~@@~                .75~  ~G@G^        .#@?  :J!            \n" +
                "                         :&@J           ?@@^                    ^P@B!          G@5  ~G?             \n" +
                "                          P@B.          G@5                   .P@&7          .G@P  :&@?             \n" +
                "                          :&@5          .:                   .G@B:          ~#@Y  :#@J              \n" +
                "                           ~&@Y                             ^#@G.         :5@#!  !&@?               \n" +
                "                          .Y@@@B~                          ?@@Y          J@&J. .Y@#!                \n" +
                "                          Y@&^J&@J                       !B@B~       :75~^7.  7&@Y.                 \n" +
                "                          J@&~ .~.                    .7B@#?         P#P^  .?#@P^                   \n" +
                "                           J@@Y:                  :~?P&@G7.             .!P&&5^                     \n" +
                "                            ^P@@5~.        P#BGGB#&&#P?:                P&G7.                       \n" +
                "                              ^J#@&57:     :!?@@G!~:                    ..                          \n" +
                "                                 ^JG&@#G5J??YB@B^                                                   \n" +
                "                                    .^7YPGGGPY!.                                                    \n" +
                "                                                                                                    \n" +
                "                                                                                                   \n";
        System.out.println(bye);
    }

    /**
     * Prints the provided message.
     * @param message the message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    //@@author TJW0911

    /**
     * Prints help messages for the "help" command.
     */

    public void printHelpMessages() {
        printHelpMessageHeader();
        printIngredientHelp();
        printRecipeHelp();
        printAllergenHelp();
        printByeCommandHelp();
    }

    private static void printAllergenHelp() {
        printAllergenAddHelp();
        printAllergenRemoveHelp();
        printAllergenListHelp();
    }

    private static void printAllergenListHelp() {
        System.out.println("Command: allergen list");
        System.out.println("List out all the allergens the user currently has");
        System.out.println(DIVIDER);
    }

    private static void printAllergenRemoveHelp() {
        System.out.println("Command: allergen remove <ingredient>");
        System.out.println("Removes the ingredient from the users list of allergens");
        System.out.println(DIVIDER);
    }

    private static void printAllergenAddHelp() {
        System.out.println("Command: allergen add <ingredient>");
        System.out.println("Adds the ingredient to the users list of allergens");
        System.out.println(DIVIDER);
    }

    private static void printIngredientHelp() {
        printAddCommandHelp();
        printRemoveCommandHelp();
        printMakeCommandHelp();
        printClearCommandHelp();
        printListIngredientsHelp();
        printSearchIngredientsHelp();
    }

    private static void printRecipeHelp() {
        printRecipePossibleHelp();
        printRecipeAllHelp();
        printRecipeRandomHelp();
        printRecipeDetailHelp();
        printRecipeNeedHelp();
        printRecipeAlmostHelp();
    }

    private static void printRecipeAlmostHelp() {
        System.out.println("Command: recipe almost");
        System.out.println("Lists all the recipes that cannot currently be made but has " +
                "less than 4 insufficient ingredients");
        System.out.println(DIVIDER);
    }

    private static void printRecipeNeedHelp() {
        System.out.println("Command: recipe need <recipe number>");
        System.out.println("Prints all the missing ingredients needed to make a specified recipe");
        System.out.println(DIVIDER);
    }

    private static void printRecipeDetailHelp() {
        System.out.println("Command: recipe <recipe number>");
        System.out.println("Prints all the details of the specified recipe");
        System.out.println(DIVIDER);
    }

    private static void printRecipeRandomHelp() {
        System.out.println("Command: recipe random");
        System.out.println("Prints a random recipe from the list of all recipes");
        System.out.println(DIVIDER);
    }

    private static void printMakeCommandHelp() {
        System.out.println("Command: make <recipe number>");
        System.out.println("Removes all the ingredients needed to make a specified recipe from inventory");
        System.out.println(DIVIDER);
    }

    private static void printClearCommandHelp() {
        System.out.println("Command: clear");
        System.out.println("Clears all the ingredient in the inventory");
        System.out.println(DIVIDER);
    }

    private static void printHelpMessageHeader() {
        System.out.println("COMMAND SUMMARY:");
        System.out.println(DIVIDER);
    }

    private static void printByeCommandHelp() {
        System.out.println("Command: bye");
        System.out.println("Exits the program");
        System.out.println(DIVIDER);
    }

    private static void printRecipeAllHelp() {
        System.out.println("Command: recipe all");
        System.out.println("Lists out all the recipes currently stored in the recipe inventory.");
        System.out.println(DIVIDER);
    }

    private static void printRecipePossibleHelp() {
        System.out.println("Command: recipe possible");
        System.out.println("Lists out all the recipes that can be made by the ingredients that are currently " +
                "in the ingredient inventory.");
        System.out.println(DIVIDER);
    }

    private static void printRemoveCommandHelp() {
        System.out.println("Command: remove <ingredient> /qty <quantity>");
        System.out.println("Remove an ingredient of specified quantity from your ingredient inventory.");
        System.out.println(DIVIDER);
    }

    private static void printAddCommandHelp() {
        System.out.println("Command: add <ingredient> /qty <quantity>");
        System.out.println("Add an ingredient of specified quantity to your ingredient inventory.");
        System.out.println(DIVIDER);
    }

    private static void printListIngredientsHelp() {
        System.out.println("Command: ingredients list");
        System.out.println("Lists out current ingredients and their respective quantities stored in the fridge.");
        System.out.println(DIVIDER);
    }

    //@@author EthanYidong
    private static void printSearchIngredientsHelp() {
        System.out.println("Command: ingredients search");
        System.out.println("Searches the ingredients database for the specified keyword.");
        System.out.println("If keyword is omitted, all ingredients will be printed.");
        System.out.println(DIVIDER);
    }

}

package utils.userinterface;

import utils.Card;
import utils.cardlist.CardList;
import utils.enums.StringArt;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private static final int LINE_LENGTH = 100;
    private static final String INKA_ART =
            ".___        __            \n" +
                    "|   | ____ |  | _______   \n" +
                    "|   |/    \\|  |/ /\\__  \\  \n" +
                    "|   |   |  \\    <  / __ \\_\n" +
                    "|___|___|  /__|_ \\(____  /\n" +
                    "         \\/     \\/     \\/ ";

    private static final String BYE_ART =
            " ____  _  _  ____    _   \n" +
                    "(  _ \\( \\/ )(  __)  / \\  \n" +
                    " ) _ ( )  /  ) _)   \\_/  \n" +
                    "(____/(__/  (____)  (_) ";
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getCommand() {
        return scanner.nextLine();
    }

    public void printLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    public void printGreeting() {
        printLine();
        System.out.println(StringArt.INKA.art);
        System.out.println("Welcome to Inka ! Say no more to failing exams as Inka will be your lord and saviour!");
        printLine();
    }

    public void printBye() {
        printLine();
        System.out.println(StringArt.BYE.art);
        System.out.println("\n Bye! All the best for your exams man!!!");
        printLine();
    }

    public void printNumOfQuestions(CardList cardList) {
        System.out.println("You now have " + cardList.size() + " questions in the bank.");
    }

    public void printAddQuestionSuccess() {
        System.out.println("That's a good question for revision later!");
    }

    public void printDeleteSuccess() {
        System.out.println("Too easy ha? You won't see that question again!");
    }

    public void printWrongCommand() {
        System.out.println("Unresolvable command");
    }

    public void printCard(CardList cardlist, int id) {
        System.out.println(cardlist.get(id));
    }

    public void printList(CardList cardList) {
        if (cardList.isEmpty()) {
            System.out.println("Your current list is empty.");
        } else {
            System.out.println("Here is your current list of questions buddy:");
            for (int i = 0; i < cardList.size(); ++i) {
                System.out.println((i + 1) + "." + cardList.get(i));
            }
        }
    }
}

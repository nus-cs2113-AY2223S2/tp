package utils;

import java.util.Scanner;
import model.CardList;
import utils.enums.StringArt;

public class UserInterface {
    private static final int LINE_LENGTH = 100;
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void printDivider() {
        System.out.println("_".repeat(LINE_LENGTH));
    }

    public void printGreeting() {
        printDivider();
        System.out.println(StringArt.INKA.art);
        System.out.println("Welcome to Inka ! Say no more to failing exams as Inka will be your lord and saviour!");
        printDivider();
    }

    public void printBye() {
        printDivider();
        System.out.println(StringArt.BYE.art);
        System.out.println("\n Bye! All the best for your exams man!!!");
        printDivider();
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

    public void printExportSuccess() {
        System.out.println("Deck exported liao");
    }

    public void printWrongCommand() {
        System.out.println("PLease re-enter a valid command!");
    }

    public void printImportBad() {
        System.out.println("No savedata detected! make new one for uuuuuuuuuuuuuuu");
    }

    public void printCard(CardList cardlist, int id) {
        System.out.println(cardlist.get(id));
    }

    public void printDeleteDateFormatError() {
        System.out.println("Please ensure that your date is a number and not a string!");
    }

    public void printDeleteDateRangeError() {
        System.out.println("Please ensure that the index you wish to delete exists!");
    }

    public void printDeleteError() {
        System.out.println("Whoops, ensure that your delete command is valid!");
    }

    public void addError() {
        System.out.println("Please ensure use '-' to separate the question and answer to the add command!");
    }

    public void addMissingQuestionPrompt() {
        System.out.println("Please ensure that you supply a valid question to Inka!");
    }

    public void addMissingAnswerPrompt() {
        System.out.println("Please ensure that you supply a valid answer to Inka!");
    }

    public void addMissingQuestionAndAnswerPrompt() {
        System.out.println("Please ensure that you supply a valid question and answer to Inka!");
    }

    public void printList(CardList cardList) {
        if (cardList.isEmpty()) {
            System.out.println("Your current list is empty.");
        } else {
            System.out.println("Here is your current list of questions buddy:");
            for (int i = 0; i < cardList.size(); ++i) {
                System.out.println((i + 1) + "." + cardList.get(i)); // 1. question  answer
            }
        }
    }
}

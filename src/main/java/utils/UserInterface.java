package utils;

import java.util.Scanner;
import model.CardList;
import utils.enums.StringArt;
import utils.exceptions.InkaException;

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

    public void printWrongCommand() {
        System.out.println("Please re-enter a valid command!");
    }

    public void printNoSaveFile() {
        System.out.println("No savedata detected! make new one for uuuuuuuuuuuuuuu");
    }

    public void printSaveSuccess() {
        System.out.println("Deck exported liao");
    }

    public void printLoadSuccess() {
        System.out.println("Deck loaded!");
    }

    public void printLoadFailure() {
        System.out.println("Failed to load deck :(");
    }

    public void printSaveFailure() {
        System.out.println("Failed to save deck :(");
    }

    public void printCard(CardList cardlist, int id) {
        System.out.println(cardlist.get(id));
    }



    public void printException(InkaException e) {
        System.out.println(e.getUiMessage());
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

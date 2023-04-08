package com.clanki.ui;

import com.clanki.objects.Flashcard;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parts of the code below are copied and adapted from TextUi.java of
 * addressbook-level2, link of original code:
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java.
 */
public class Ui {
    public static final String AFTER_COMMAND_SPACING = "     ";
    public static final String BEFORE_ELABORATION_SPACING = "         ";
    private final Scanner in;
    // private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        // this.out = out;
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public void printSuccessfulAddMessage(String questionText, String answerText) {
        System.out.println("You have added the following card:");
        System.out.println("Q: " + questionText);
        System.out.println("A: " + answerText);
    }

    public void printByeMessage() {
        System.out.println("Bye. Don't forget to come back later to study!");
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to Clanki! Time to start studying!");
    }

    public void printInvalidInput() {
        System.out.println("The input is in an incorrect format.\n" +
                "You can view our user guide or type help to see the correct formats for commands.\n");
    }

    /**
     * Prints out a message to indicate a successful deletion of a flashcard
     *
     * @param index the index of the flashcard at the point of deletion
     */
    public void printSuccessfulDelete(int index) {
        System.out.println("Got it. Deleted the flashcard at index " + index);
    }

    /**
     * reused from Kong Dehao ip for general printing to console
     *
     * @param message multiple strings to be shown to user
     */
    public void printlnSeveralStrings(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public void printHelpMessage() {
        System.out.println(
                "The following are the commands you can use:\n" +
                        "add:" + AFTER_COMMAND_SPACING + "Adds a flashcard to the current list of flashcards.\n"
                        + BEFORE_ELABORATION_SPACING + "Parameters: add /q QUESTION /a ANSWER\n"
                        + BEFORE_ELABORATION_SPACING + "Example: add /q What is the worst fruit? /a Durian\n"
                        + "update:  Changes the content of flashcard's question, answer or date.\n"
                        + BEFORE_ELABORATION_SPACING + "Parameters: update /q QUERY\n"
                        + BEFORE_ELABORATION_SPACING + "Example: update fruit \n"
                        + BEFORE_ELABORATION_SPACING + BEFORE_ELABORATION_SPACING +
                        "Which flashcard do you want to update? 1 /q What is the best fruit?\n"
                        + "delete:  Removes a flashcard with specified string.\n"
                        + BEFORE_ELABORATION_SPACING + "Parameters: delete /q QUERY\n"
                        + BEFORE_ELABORATION_SPACING + "Example: delete fruit \n"
                        + "review:  Go through all flashcards that are due today.\n"
                        + "list:    lists out the questions and answers in the list of flashcards.\n"
                        + BEFORE_ELABORATION_SPACING + "Parameters: list all (lists all flashcards)\n"
                        + BEFORE_ELABORATION_SPACING + "Parameters: list DUE_DATE (list all flashcards with that " +
                        "specified due date)\n"
                        + BEFORE_ELABORATION_SPACING + "Example: list 2023-05-04\n"
                        + "clear:   Deletes all the flashcards in the list.\n"
                        + "bye:     Exit the program.\n"
        );
    }

    public void printSeparationLine() {
        System.out.println("==========================================================");
    }

    /**
     * Prints the question and answer of a single flashcard to the console.
     *
     * @Param flashcard the flashcard to be print
     */
    public void printFlashCard(Flashcard flashcard) {
        System.out.println("Q: " + flashcard.getQuestion());
        System.out.println("A: " + flashcard.getAnswer());
    }

    /**
     * Prints the questions and answers of a list of flashcards to the console.
     *
     * @param flashcards the list of flashcards to be printed
     */
    public void printFlashCards(ArrayList<Flashcard> flashcards) {
        for (int i = 0; i < flashcards.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            printFlashCard(flashcards.get(i));
        }
    }

    public void printSuccessfulUpdateMessage(Flashcard updatedFlashcard) {
        System.out.println("Understood. The card has been updated to");
        printFlashCard(updatedFlashcard);
    }

    public void printFlashCardsOnDate(ArrayList<Flashcard> flashcards, LocalDate date) {
        int countFlashcardsPrinted = 0;
        for (int i = 0; i < flashcards.size(); i++) {
            if (flashcards.get(i).getDueDate().compareTo(date) == 0) {
                countFlashcardsPrinted++;
                if (countFlashcardsPrinted == 1) {
                    System.out.println("Here is your list of flashcards with the specified due date:");
                }
                System.out.println("[" + (countFlashcardsPrinted) + "]");
                printFlashCard(flashcards.get(i));
            }
        }
        if (countFlashcardsPrinted == 0) {
            System.out.println("Your list of flashcards with the specified due date is empty.");
        }
    }
}

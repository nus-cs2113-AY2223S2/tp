package com.clanki.ui;

import com.clanki.objects.Flashcard;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parts of the code below are copied and adapted from TextUi.java of
 * addressbook-level2, link of original code:
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java.
 */
public class Ui {
    private final Scanner in;

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Display messages to inform user that a flashcard have been successfully
     * added.
     *
     * @param questionText The question of the new flashcard.
     * @param answerText   The answer of the new flashcard.
     */
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

    /**
     * Display message to inform user that their input is of a wrong format.
     */
    public void printInvalidInput() {
        System.out.println("The input is in an incorrect format.\n"
                + "You can view our user guide or type help to see the correct formats for commands.\n");
    }

    /**
     * Prints out a message to indicate a successful deletion of a flashcard
     *
     * @param index the index of the flashcard at the point of deletion
     */
    public void printSuccessfulDelete(int index) {
        System.out.println("Understood. The card has been deleted. " + index);
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

    /**
     * Display all the current valid commands the user can key in.
     */
    public void printHelpMessage() {
        System.out.println("The following are the commands you can use:\n"
                + "add     Adds a flashcard to the current list of flashcards.\n"
                + "        Parameters: add /q QUESTION /a ANSWER\n"
                + "        Example: add /q What is the worst fruit? /a Durian\n"
                + "update  Changes the content of flashcard's question or answer.\n"
                + "        Parameters: update QUERY\n" + "        Example: update fruit\n"
                + "        Which flashcard do you want to update? 1 /q What is the best fruit?\n"
                + "delete  Removes a flashcard with specified string.\n"
                + "        Parameters: delete QUERY\n" + "        Example: delete fruit\n"
                + "review  Go through all flashcards that are due today.\n"
                + "list    Lists out the questions and answers in the list of flashcards.\n"
                + "        Parameters: list all (lists all flashcards)\n"
                + "        Parameters: list DUE_DATE (list all flashcards with that specified due date)\n"
                + "        Example: list 2023-05-04\n"
                + "clear   Deletes all the flashcards in the list.\n"
                + "bye     Exit the program.");
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

    /**
     * Display that the contents of list have been successfully cleared.
     */
    public void printClearMessage() {
        System.out.println("All flashcards have been deleted.");
        System.out.println("Your list of flashcards is now empty.");
    }
}

package com.clanki.commands;

import com.clanki.exceptions.UpdatedContentIsEmptyException;
import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.parser.ParsedInput;
import com.clanki.ui.Ui;

import java.util.ArrayList;

/**
 * The UpdateCommand class represents a command that updates an existing
 * flashcard in the flashcard list. The command allows users to change the
 * question, answer or date of the flashcard.
 */
public class UpdateCommand extends Command {
    private static final String QUESTION_OPTION_IDENTIFIER = "q";
    private static final String ANSWER_OPTION_IDENTIFIER = "a";

    ArrayList<Flashcard> matchingFlashcards;
    String query;

    public UpdateCommand(String query) {
        this.matchingFlashcards = new ArrayList<>();
        this.query = query;
    }

    /**
     * Prompts the user for input and updates the specified flashcard based on the
     * user's input.
     *
     * @param flashcards the list of flashcards to update
     * @param display    the Ui object for displaying messages to the user
     */
    private void runUpdateFlashcard(Ui display) {
        boolean isDone = false;
        while (!isDone) {
            try {
                ParsedInput input = new ParsedInput(display.getUserCommand());
                int index = Integer.parseInt(input.getCommand());
                if (index < 1 || index > matchingFlashcards.size()) {
                    throw new Exception();
                }

                Flashcard currentFlashcard = matchingFlashcards.get(index - 1);

                String questionText = input.getOptionByName(QUESTION_OPTION_IDENTIFIER);
                String answerText = input.getOptionByName(ANSWER_OPTION_IDENTIFIER);

                if (questionText == null && answerText == null) {
                    throw new UpdatedContentIsEmptyException();
                }

                if (questionText != null) {
                    currentFlashcard.setQuestion(questionText);
                }

                if (answerText != null) {
                    currentFlashcard.setAnswer(answerText);
                }

                display.printSuccessfulUpdateMessage(currentFlashcard);
                isDone = true;
            } catch (UpdatedContentIsEmptyException e) {
                System.out.println("Please enter the changes to be modified.");
            } catch (Exception e) {
                System.out.println("Please enter a valid index value.");
            }
        }
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        matchingFlashcards = flashcardList.queryFlashcards(query);

        if (matchingFlashcards.size() == 0) {
            System.out.printf("Sorry! No flashcards matching \"%s\" was found. Please try again.",
                    query);
            return;
        }

        System.out.printf("Found %d card(s) with query \"%s\":\n", matchingFlashcards.size(),
                query);
        display.printFlashCards(matchingFlashcards);
        System.out.println("Which one do you want to update?");

        runUpdateFlashcard(display);
    }
}

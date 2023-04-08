package com.clanki.commands;

import com.clanki.exceptions.UpdatedContentIsEmptyException;
import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.parser.InvalidIdentifierException;
import com.clanki.parser.Parser;
import com.clanki.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The UpdateCommand class represents a command that updates an existing flashcard
 * in the flashcard list.
 * The command allows users to change the question, answer or date of the flashcard.
 */
public class UpdateCommand extends Command {
    private static final String INVALID_IDENTIFIER_ERROR = "You can only enter /q, /a or /d";
    private static final String EMPTY_CONTENT_ERROR = "Please enter the changes to be modified.";
    private static final String INCORRECT_FORMAT_ERROR =
            "Please enter the input in the correct format as shown in the user guide.";
    private static final String EMPTY_INPUT_ERROR = "Please enter the index of the flashcard you want to update.";
    private static final String INDEX_OUT_OF_LIST_ERROR = "You have selected an index out of the list.";
    private static final String INVALID_DATE_FORMAT_ERROR = "Please enter the date in the format: yyyy-mm-dd";
    String query;
    ArrayList<Flashcard> matchingFlashcards = new ArrayList<>();

    public UpdateCommand(String query) {
        super();
        this.query = query;
    }

    /**
     * Searches for flashcards that match the query and adds them to the matchingFlashcards list.
     *
     * @param flashcards the list of flashcards to search through
     * @param query the search query for finding matching flashcards
     */
    public void findFlashcard(ArrayList<Flashcard> flashcards, String query) {
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard currentFlashcard = flashcards.get(i);
            if (currentFlashcard.getQuestion().toLowerCase().contains(query.toLowerCase())) {
                matchingFlashcards.add(currentFlashcard);
            }
            if (currentFlashcard.getAnswer().toLowerCase().contains(query.toLowerCase())) {
                matchingFlashcards.add(currentFlashcard);
            }
            if (currentFlashcard.getDueDate().toString().equals(query)) {
                matchingFlashcards.add(currentFlashcard);
            }
        }
    }

    /**
     * Updates the content of a flashcard based on the specified identifier and updated content.
     *
     * @param flashcards the list of flashcards to update
     * @param indexInMatchList the index of the flashcard in the matchingFlashcards list
     * @param identifier the identifier for the field to update (/q for question, /a for answer, /d for due date)
     * @param updatedContent the updated content for the field
     * @return the index of the updated flashcard in the flashcards list
     */
    public int implementUpdate(ArrayList<Flashcard> flashcards, int indexInMatchList, String identifier,
                               String updatedContent) {
        Flashcard flashcardToChange = matchingFlashcards.get(indexInMatchList);
        int index = flashcards.indexOf(flashcardToChange);
        if (identifier.contains("q")) {
            flashcards.get(index).setQuestion(updatedContent.substring(0, 1).toUpperCase() +
                    updatedContent.substring(1));
        }
        if (identifier.contains("a")) {
            flashcards.get(index).setAnswer(updatedContent.substring(0, 1).toUpperCase() +
                    updatedContent.substring(1));
        }
        if (identifier.contains("d")) {
            LocalDate dateToChange = LocalDate.parse(updatedContent);
            flashcards.get(index).setDueDate(dateToChange);
        }
        return index;
    }

    /**
     * Prompts the user for input and updates the specified flashcard based on the user's input.
     *
     * @param flashcards the list of flashcards to update
     * @param display the Ui object for displaying messages to the user
     */
    private void updateFlashcard(ArrayList<Flashcard> flashcards, Ui display) {
        int checker = 0;
        while (checker == 0) {
            try {
                String userText = display.getUserCommand();
                int indexInMatchList = Parser.getIndexForUpdateCommand(userText);
                String identifier = Parser.getIdentifierForUpdateCommand(userText);
                String updatedContent = Parser.parseInputForUpdateCommand(userText);
                int index = implementUpdate(flashcards, indexInMatchList, identifier, updatedContent);
                display.printSuccessfulUpdateMessage(flashcards.get(index));
                checker = 1;
            } catch (InvalidIdentifierException e) {
                System.out.println(INVALID_IDENTIFIER_ERROR);
            } catch (UpdatedContentIsEmptyException e) {
                System.out.println(EMPTY_CONTENT_ERROR);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(INCORRECT_FORMAT_ERROR);
            } catch (NumberFormatException e) {
                System.out.println(EMPTY_INPUT_ERROR);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(INDEX_OUT_OF_LIST_ERROR);
                System.out.println(" There are only " + matchingFlashcards.size()
                        + " flashcards that match the query.");
            } catch (DateTimeParseException e) {
                System.out.println(INVALID_DATE_FORMAT_ERROR);
            }
        }
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashCards();
        findFlashcard(flashcards, query);
        if (matchingFlashcards.size() > 0) {
            System.out.println(
                    "Found " + matchingFlashcards.size() + " cards with the query \"" + query + "\":");
            display.printFlashCards(matchingFlashcards);
            System.out.println("Which flashcard do you want to update?");
            updateFlashcard(flashcards, display);
        } else {
            System.out.println("There are no flashcards with the query \"" + query + "\".");
        }
    }
}

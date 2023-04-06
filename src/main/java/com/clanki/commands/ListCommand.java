package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ListCommand extends Command {
    private static final String INVALID_DATE_FORMAT_ERROR = "Please enter the date in the format: yyyy-mm-dd";
    String input;

    public ListCommand(String input) {
        super();
        this.input = input;
    }

    private LocalDate convertDate(String dueDate) throws DateTimeParseException {
        return LocalDate.parse(dueDate);
    }

    public void printAllFlashcards(ArrayList<Flashcard> flashcards, Ui display) {
        if (flashcards.size() > 0) {
            System.out.println("Here is your list of flashcards:");
            display.printFlashCards(flashcards);
        }
        if (flashcards.size() == 0) {
            System.out.println("Your list of flashcards is empty.");
        }
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashCards();
        if (input.equals("all")) {
            printAllFlashcards(flashcards, display);
        } else {
            LocalDate dueDate = null;
            try {
                dueDate = convertDate(input);
                display.printFlashCardsOnDate(flashcards, dueDate);
            } catch (DateTimeParseException e) {
                System.out.println(INVALID_DATE_FORMAT_ERROR);
            }
        }
    }
}

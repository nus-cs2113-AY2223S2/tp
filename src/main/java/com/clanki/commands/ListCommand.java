package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ListCommand extends Command {
    String input;

    public ListCommand(String input) {
        super();
        this.input = input;
    }

    private LocalDate convertDate(String dueDate) throws DateTimeParseException {
        return LocalDate.parse(dueDate);
    }

    private void printFlashcards(ArrayList<Flashcard> flashcards, Ui display) {
        if (flashcards.size() == 0) {
            System.out.println("Your list of flashcards is empty.");
            return;
        }
        System.out.println("Here is your list of flashcards:");
        display.printFlashCards(flashcards);
    }

    public void printFlashcardsOnDate(ArrayList<Flashcard> flashcards, LocalDate date, Ui display) {
        ArrayList<Flashcard> flashcardsOnDate = new ArrayList<>();
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard currentFlashcard = flashcards.get(i);
            if (currentFlashcard.getDueDate().equals(date)) {
                flashcardsOnDate.add(currentFlashcard);
            }
        }
        printFlashcards(flashcardsOnDate, display);
    }

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashCards();
        if (input.equals("all")) {
            printFlashcards(flashcards, display);
            return;
        }

        try {
            LocalDate dueDate = convertDate(input);
            printFlashcardsOnDate(flashcards, dueDate, display);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter the date in the format: yyyy-mm-dd");
        }
    }
}

package seedu.moneymind.ui;

import seedu.moneymind.category.Category;
import seedu.moneymind.command.CategoryCommand;
import seedu.moneymind.event.Event;

import static seedu.moneymind.command.SearchCommand.getCategoryOfEvent;
import static seedu.moneymind.string.Strings.HORIZONTAL_LINE;
import static seedu.moneymind.string.Strings.GREETING;
import static seedu.moneymind.string.Strings.OFFER_HELP_FOR_COMMAND;
import static seedu.moneymind.string.Strings.BYE_MESSAGE;
import static seedu.moneymind.string.Strings.ERROR_LOADING_FILE;
import static seedu.moneymind.string.Strings.LOAD_ERROR_RISK_MESSAGE;
import static seedu.moneymind.string.Strings.ERROR;
import static seedu.moneymind.string.Strings.NO_SEARCH_RESULTS;
import static seedu.moneymind.string.Strings.DOT;

import java.util.ArrayList;
import java.util.Set;

/**
 * Interacts with the user via the command line.
 */
public class Ui {

    public void greet() {
        System.out.println(GREETING);
        System.out.println(OFFER_HELP_FOR_COMMAND);
    }

    public void goodbye() {
        System.out.println(BYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    public void loadingError(Exception e) {
        System.out.println(HORIZONTAL_LINE + ERROR_LOADING_FILE + e.getMessage());
        System.out.println(LOAD_ERROR_RISK_MESSAGE);
    }

    /**
     * Prints the error message when an exception is caught.
     * @param error Exception object containing the error message.
     */
    public void error(Exception error) {
        System.out.println(ERROR + "\n" + error);
    }

    /**
     * Prints search results for categories that match the query string.
     * @param matchingCategories Set containing categories that match the query string.
     */
    public static void printMatchingCategories(Set<Category> matchingCategories) {
        System.out.println("Matching Categories:");

        if (matchingCategories.size() == 0) {
            System.out.println(NO_SEARCH_RESULTS);
        }

        for (Category category : matchingCategories) {
            int categoryIndex = CategoryCommand.categoryMap.get(category.getName());
            System.out.println((categoryIndex + 1) + DOT + category.getName());
        }
    }

    /**
     * Prints search results for events that match the query string.
     * @param matchingEvents Set containing events that match the query string.
     */
    public static void printMatchingEvents(Set<Event> matchingEvents) {
        System.out.println("\nMatching Events:");

        if (matchingEvents.size() == 0) {
            System.out.println(NO_SEARCH_RESULTS);
        }

        for (Event event : matchingEvents) {
            Category categoryOfEvent = getCategoryOfEvent(event);
            assert categoryOfEvent != null;
            String categoryName = categoryOfEvent.getName();
            System.out.println(event.getDescription() + " (Category: " + categoryName + ")");
        }
    }

    /**
     * Prints search results for categories that are similar to the query string.
     * @param similarCategoriesList Array list containing the similar categories, sorted by similarity distance.
     */
    public static void printSimilarCategories(ArrayList<Category> similarCategoriesList) {
        System.out.println("\nSimilar Categories:");

        int similarCategoryCount = 3;
        if (similarCategoriesList.size() < 3) {
            similarCategoryCount = similarCategoriesList.size();
        }

        if (similarCategoryCount == 0) {
            System.out.println(NO_SEARCH_RESULTS);
            return;
        }

        for (int i = 0; i < similarCategoryCount; i++) {
            Category category = similarCategoriesList.get(i);
            int categoryIndex = CategoryCommand.categoryMap.get(category.getName());
            System.out.println((categoryIndex + 1) + DOT + category.getName());
        }
    }

    /**
     * Prints search results for events that are similar to the query string.
     * @param similarEventsList Array list containing the similar events, sorted by similarity distance.
     */
    public static void printSimilarEvents(ArrayList<Event> similarEventsList) {
        System.out.println("\nSimilar Events:");

        int similarEventsCount = 3;
        if (similarEventsList.size() < 3) {
            similarEventsCount = similarEventsList.size();
        }

        if (similarEventsCount == 0) {
            System.out.println(NO_SEARCH_RESULTS);
            return;
        }

        for (int i = 0; i < similarEventsCount; i++) {
            Event event = similarEventsList.get(i);
            Category categoryOfEvent = getCategoryOfEvent(event);
            assert categoryOfEvent != null;
            String categoryName = categoryOfEvent.getName();
            System.out.println(event.getDescription() + " (Category: " + categoryName + ")");
        }
    }

}

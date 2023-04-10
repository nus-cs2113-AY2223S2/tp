package seedu.moneymind.command;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;
import seedu.moneymind.ui.Ui;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

import static seedu.moneymind.category.CategoryList.categories;
import static seedu.moneymind.ui.Ui.printMatchingCategories;
import static seedu.moneymind.ui.Ui.printSimilarCategories;
import static seedu.moneymind.ui.Ui.printMatchingEvents;
import static seedu.moneymind.ui.Ui.printSimilarEvents;

/**
 * Command for searching categories and events based on
 * a query string provided by the user. Displays both categories
 * and events that contain the query string and the top 3 most similar
 * categories and events that do not contain the query string exactly.
 */
public class SearchCommand implements Command {
    private final String query;

    public SearchCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(Ui ui) {
        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();

        assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);

        ArrayList<Category> similarCategoriesList = new ArrayList<>();
        ArrayList<Event> similarEventsList = new ArrayList<>();

        similarCategoriesList.addAll(similarCategories.keySet());
        similarEventsList.addAll(similarEvents.keySet());

        sortCategoryBySimilarity(similarCategoriesList, similarCategories);
        sortEventBySimilarity(similarEventsList, similarEvents);

        printMatchingCategories(matchingCategories);
        printMatchingEvents(matchingEvents);
        printSimilarCategories(similarCategoriesList);
        printSimilarEvents(similarEventsList);
    }

    /**
     * Assign categories and events to appropriate collections,
     * depending on whether they match the query string or are
     * just similar to it.
     * @param matchingCategories Set containing categories that match the query string.
     * @param matchingEvents Set containing events that match the query string.
     * @param similarCategories Hash map containing categories that are similar to the query string.
     * @param similarEvents Hash map containing events that are similar to the query string.
     * @param query The query string
     */
    public void assignItemsBySimilarity(
            Set<Category> matchingCategories,
            Set<Event> matchingEvents,
            HashMap<Category, Integer> similarCategories,
            HashMap<Event, Integer> similarEvents,
            String query
    ) {
        for (Category category : categories) {
            // calculate similarity between this category and query, and add to the appropriate list
            int categorySimilarityDistance = calculateSimilarityDistance(query, category.getName());
            if (categorySimilarityDistance == 0) {
                matchingCategories.add(category);
                similarCategories.remove(category);
            } else if (
                    similarCategories.get(category) != null &&
                            similarCategories.get(category) < categorySimilarityDistance) {
                similarCategories.put(category, categorySimilarityDistance);
            } else {
                similarCategories.putIfAbsent(category, categorySimilarityDistance);
            }

            for (Event event : category.getEvents()) {
                // calculate similarity between this event and query, and add to the appropriate list
                int eventSimilarityDistance = calculateSimilarityDistance(query, event.getDescription());
                if (eventSimilarityDistance == 0) {
                    matchingEvents.add(event);
                    similarEvents.remove(event);
                } else if (similarEvents.get(event) != null &&
                        similarEvents.get(event) < eventSimilarityDistance) {
                    similarEvents.put(event, eventSimilarityDistance);
                } else {
                    similarEvents.putIfAbsent(event, eventSimilarityDistance);
                }
            }
        }
    }

    /**
     * Calculate the smallest similarity distance between a query string
     * and all possible substrings of the same length of a value string.
     * If value string is shorter than the query string, then the roles are
     * reversed, but the two strings are compared directly instead of using
     * equal length substrings.
     * @param queryString The query string.
     * @param valueString The value string.
     * @return The smallest Levenshtein distance between the 2 strings.
     */
    public int calculateSimilarityDistance(String queryString, String valueString) {
        if (queryString.length() >= valueString.length()) {
            // immediately calculate similarity and return
            return calculateLevenshteinDistance(valueString, queryString);
        }

        // list of all similarity to all possible substrings of same length
        ArrayList<Integer> distances = new ArrayList<Integer>();

        int lengthDifference = valueString.length() - queryString.length();

        // for every possible substring, calculate similarity distance to query
        for (int i = 0; i < lengthDifference; i++) {
            String substring = valueString.substring(i, i+queryString.length());
            int distance = calculateLevenshteinDistance(queryString, substring);
            distances.add(distance);
        }

        // find the lowest similarity distance and return
        int min = distances.get(0);
        for (int distance : distances) {
            if (distance < min) {
                min = distance;
            }
        }
        return min;
    }

    /**
     * Calculates the Levenshtein distance between 2 strings. Not case-sensitive.
     * @param firstString The first string.
     * @param secondString The second string.
     * @return An integer representing the Levenshtein distance between the 2 strings.
     */
    public int calculateLevenshteinDistance(String firstString, String secondString) {
        firstString = firstString.toLowerCase();
        secondString = secondString.toLowerCase();

        int[] costs = new int[secondString.length() + 1];
        for (int i = 0; i <= firstString.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= secondString.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else if (j > 0) {
                    int newValue = costs[j - 1];
                    if (firstString.charAt(i - 1) != secondString.charAt(j - 1)) {
                        newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                    }
                    costs[j - 1] = lastValue;
                    lastValue = newValue;
                }
            }
            if (i > 0) {
                costs[secondString.length()] = lastValue;
            }
        }
        return costs[secondString.length()];
    }

    /**
     * Sorts a list of categories depending on its similarity distance to a query string.
     * The similarity distance should be provided by a hash map.
     * @param input The list of categories
     * @param set The hash map of categories and their similarity distances
     */
    public void sortCategoryBySimilarity(ArrayList<Category> input, HashMap<Category, Integer> set) {
        Comparator<Category> comparator = (firstCategory, secondCategory) -> {
            int result = set.get(firstCategory) - set.get(secondCategory);
            if (result == 0) {
                result = firstCategory.getName().hashCode() - secondCategory.getName().hashCode();
            }
            return result;
        };
        input.sort(comparator);
    }

    /**
     * Sorts a list of events depending on its similarity distance to a query string.
     * The similarity distance should be provided by a hash map.
     * @param input The list of events
     * @param set The hash map of events and their similarity distances
     */
    public void sortEventBySimilarity(ArrayList<Event> input, HashMap<Event, Integer> set) {
        Comparator<Event> comparator = (firstEvent, secondEvent) -> {
            int result = set.get(firstEvent) - set.get(secondEvent);
            if (result == 0) {
                result = firstEvent.getDescription().hashCode() - secondEvent.getDescription().hashCode();
            }
            return result;
        };
        input.sort(Comparator.comparingInt(set::get));
    }

    /**
     * Finds the category of an event
     * @param event The event to find category for
     * @return Category of the event
     */
    public static Category getCategoryOfEvent(Event event) {
        for (Category category : categories) {
            if (category.events.contains(event)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

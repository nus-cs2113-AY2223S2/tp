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
import static seedu.moneymind.string.Strings.DOT;
import static seedu.moneymind.string.Strings.NO_SEARCH_RESULTS;

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
     * Prints search results for categories that match the query string.
     * @param matchingCategories Set containing categories that match the query string.
     */
    private void printMatchingCategories(Set<Category> matchingCategories) {
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
    private void printMatchingEvents(Set<Event> matchingEvents) {
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
    private void printSimilarCategories(ArrayList<Category> similarCategoriesList) {
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
    private void printSimilarEvents(ArrayList<Event> similarEventsList) {
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
    private void assignItemsBySimilarity(
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
    private int calculateSimilarityDistance(String queryString, String valueString) {
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
    private int calculateLevenshteinDistance(String firstString, String secondString) {
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
    private void sortCategoryBySimilarity(ArrayList<Category> input, HashMap<Category, Integer> set) {
        input.sort(Comparator.comparingInt(set::get));
    }

    /**
     * Sorts a list of events depending on its similarity distance to a query string.
     * The similarity distance should be provided by a hash map.
     * @param input The list of events
     * @param set The hash map of events and their similarity distances
     */
    private void sortEventBySimilarity(ArrayList<Event> input, HashMap<Event, Integer> set) {
        input.sort(Comparator.comparingInt(set::get));
    }

    /**
     * Finds the category of an event
     * @param event The event to find category for
     * @return Category of the event
     */
    private Category getCategoryOfEvent(Event event) {
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

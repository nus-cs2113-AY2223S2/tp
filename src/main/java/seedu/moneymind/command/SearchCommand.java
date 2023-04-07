package seedu.moneymind.command;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;
import seedu.moneymind.ui.Ui;

import java.util.*;

import static seedu.moneymind.category.CategoryList.categories;
import static seedu.moneymind.string.Strings.DOT;
import static seedu.moneymind.string.Strings.NO_SEARCH_RESULTS;

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
        printSimilarCategories(similarCategories, similarCategoriesList);
        printSimilarEvents(similarEvents, similarEventsList);
    }

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

    private void printSimilarCategories(HashMap<Category, Integer> similarCategories,
                                        ArrayList<Category> similarCategoriesList) {
        System.out.println("\nSimilar Categories:");

        int similarCategoryCount = 3;
        if (similarCategoriesList.size() < 3) {
            similarCategoryCount = similarCategories.size();
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

    private void printSimilarEvents(HashMap<Event, Integer> similarEvents,
                                    ArrayList<Event> similarEventsList) {
        System.out.println("\nSimilar Events:");

        int similarEventsCount = 3;
        if (similarEventsList.size() < 3) {
            similarEventsCount = similarEvents.size();
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

    private void sortCategoryBySimilarity(ArrayList<Category> input, HashMap<Category, Integer> set) {
        input.sort(Comparator.comparingInt(set::get));
    }

    private void sortEventBySimilarity(ArrayList<Event> input, HashMap<Event, Integer> set) {
        input.sort(Comparator.comparingInt(set::get));
    }

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

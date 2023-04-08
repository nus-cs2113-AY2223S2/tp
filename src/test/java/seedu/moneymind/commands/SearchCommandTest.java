package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;
import seedu.moneymind.category.Category;
import seedu.moneymind.command.SearchCommand;
import seedu.moneymind.event.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchCommandTest extends CommandTest {

    public SearchCommandTest() {
        super();
    }

    @Test
    void searchCommand_assignItemsBySimilarity_matchNoCategory() {
        String query = "test";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);

        assertEquals(0, matchingCategories.size());
        assertEquals(0, matchingEvents.size());
        assertEquals(2, similarCategories.size());
        assertEquals(4, similarEvents.size());

        clear();
    }

    @Test
    void searchCommand_assignItemsBySimilarity_matchOneCategory() {
        String query = "book";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);

        assertEquals(1, matchingCategories.size());
        assertEquals(0, matchingEvents.size());
        assertEquals(1, similarCategories.size());
        assertEquals(4, similarEvents.size());

        clear();
    }

    @Test
    void searchCommand_assignItemsBySimilarity_matchMultipleCategory() {
        String query = "oo";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);

        assertEquals(2, matchingCategories.size());
        assertEquals(0, matchingEvents.size());
        assertEquals(0, similarCategories.size());
        assertEquals(4, similarEvents.size());

        clear();
    }

    @Test
    void searchCommand_assignItemsBySimilarity_matchNoEvent() {
        String query = "test";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);

        assertEquals(0, matchingCategories.size());
        assertEquals(0, matchingEvents.size());
        assertEquals(2, similarCategories.size());
        assertEquals(4, similarEvents.size());

        clear();
    }

    @Test
    void searchCommand_assignItemsBySimilarity_matchOneEvent() {
        String query = "lord";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);

        assertEquals(0, matchingCategories.size());
        assertEquals(1, matchingEvents.size());
        assertEquals(2, similarCategories.size());
        assertEquals(3, similarEvents.size());

        clear();
    }

    @Test
    void searchCommand_assignItemsBySimilarity_matchMultipleEvent() {
        String query = "e";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);

        assertEquals(0, matchingCategories.size());
        assertEquals(2, matchingEvents.size());
        assertEquals(2, similarCategories.size());
        assertEquals(2, similarEvents.size());

        clear();
    }

    @Test
    void searchCommand_sortCategoryBySimilarity_expectSorted() {
        String query = "test";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();
        ArrayList<Category> similarCategoriesList = new ArrayList<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);
        similarCategoriesList.addAll(similarCategories.keySet());
        command.sortCategoryBySimilarity(similarCategoriesList, similarCategories);

        boolean sorted = true;
        for (int i = 0; i < similarCategoriesList.size()-1; i++) {
            int currentCategoryValue = similarCategories.get(similarCategoriesList.get(i));
            int nextCategoryValue = similarCategories.get(similarCategoriesList.get(i+1));
            if (currentCategoryValue > nextCategoryValue) {
                sorted = false;
            }
        }

        assertEquals(2, similarCategoriesList.size());
        assertTrue(sorted);

        clear();
    }

    @Test
    void searchCommand_sortEventBySimilarity_expectSorted() {
        String query = "test";

        SearchCommand command = new SearchCommand(query);

        Set<Category> matchingCategories = new LinkedHashSet<>();
        Set<Event> matchingEvents = new LinkedHashSet<>();
        HashMap<Category, Integer> similarCategories = new HashMap<>();
        HashMap<Event, Integer> similarEvents = new HashMap<>();
        ArrayList<Event> similarEventsList = new ArrayList<>();

        setup();

        command.assignItemsBySimilarity(matchingCategories, matchingEvents, similarCategories, similarEvents, query);
        similarEventsList.addAll(similarEvents.keySet());
        command.sortEventBySimilarity(similarEventsList, similarEvents);

        boolean sorted = true;
        for (int i = 0; i < similarEventsList.size()-1; i++) {
            int currentEventValue = similarEvents.get(similarEventsList.get(i));
            int nextEventValue = similarEvents.get(similarEventsList.get(i+1));
            if (currentEventValue > nextEventValue) {
                sorted = false;
            }
        }

        assertEquals(4, similarEventsList.size());
        assertTrue(sorted);

        clear();
    }

    @Test
    void searchCommand_noQuery_expectErrorMessage() {
        setup();
        String terminalOutput = executeInput("search").toString();
        String expected = "Please use the format: search <keyword>" + System.lineSeparator();
        assertEquals(expected, terminalOutput);
        clear();
    }
}

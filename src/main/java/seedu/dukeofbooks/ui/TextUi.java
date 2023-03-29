package seedu.dukeofbooks.ui;

import static seedu.dukeofbooks.common.Messages.WELCOME_STRING;
import static seedu.dukeofbooks.common.Messages.EXIT_STRING;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import seedu.dukeofbooks.command.CommandResult;
import seedu.dukeofbooks.data.book.BorrowableItem;

import java.util.ArrayList;
import java.util.List;

public class TextUi {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String LINE_PREFIX = "|| ";
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "===================================================";
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    private static final String LOGIN_PROMPT = "Welcome! Please login or signup.";
    private static final String GREETINGS_FORMAT = "Welcome %s!";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    private boolean toIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();

        while (toIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        //showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    public void showWelcomeMessage(String version) {
        showToUser(DIVIDER, WELCOME_STRING, version);
    }

    public void showLoginMessage() {
        showToUser(DIVIDER, LOGIN_PROMPT, DIVIDER);
    }

    public void showGreetingMessage(String name) {
        showToUser(DIVIDER, String.format(GREETINGS_FORMAT, name), DIVIDER);
    }

    public void showExitMessage() {
        showToUser(DIVIDER, EXIT_STRING, DIVIDER);
    }

    public void showResultToUser(CommandResult result) {
        final List<BorrowableItem> resultBooks = result.getRelevantBook();
        if (resultBooks != null) {
            showBookListView(resultBooks);
        }
        showToUser(result.feedbackToUser, DIVIDER);
    }

    private void showBookListView(List<BorrowableItem> books) {
        final List<String> formattedBooks = new ArrayList<>();
        for (BorrowableItem book : books) {
            formattedBooks.add(book.toString());
        }
        showToUserAsIndexedList(formattedBooks);
    }

    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}

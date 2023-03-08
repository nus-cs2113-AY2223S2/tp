package seedu.duke;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
    private String[] separatedKeywordAndDescription;
    private String keyword;
    // split the input into keyword and description
    public void splitKeywordAndDescription(String input) {
        separatedKeywordAndDescription = input.split(" ", 2);
        keyword = separatedKeywordAndDescription[0];
    }
    public void executeUserInput() {
        switch (keyword) {
        case "bye":
            executeByeCommand();
            break;
        case "view":
            executeViewCommand();
            break;
        case "delete":
            executeDeleteCommand();
            break;
        case "event":
            executeEventCommand();
            break;
        case "category":
            executeCategoryCommand();
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

    private void executeByeCommand() {
        ByeCommand byeCommand = new ByeCommand();
    }

    // execute view command
    private void executeViewCommand() {
        if (separatedKeywordAndDescription.length == 1) {
            new ViewCommand();
        } else {
            new ViewCommand(separatedKeywordAndDescription[1]);
        }
    }

    // execute delete command
    private void executeDeleteCommand() {
        Pattern pattern = Pattern.compile("c/(.+) e/(.+)?");
        Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);

        if (matcher.find()) {
            String categoryName = matcher.group(1);
            String eventName = matcher.group(2);

            System.out.println("Category name: " + categoryName);
            System.out.println("Event name: " + eventName);
            if (eventName == null) {
                new DeleteCommand(categoryName);
            } else {
                new DeleteCommand(categoryName, eventName);
            }
        }
    }

    // execute event command
    private void executeEventCommand() {
        Pattern pattern = Pattern.compile("(.+) b/(\\d+) e/(\\d+)?");
        Matcher matcher = pattern.matcher(separatedKeywordAndDescription[1]);

        if (matcher.find()) {
            String eventName = matcher.group(1);
            String budgetNumber = matcher.group(2);
            String expenseNumber = matcher.group(3);

            System.out.println("Event name: " + eventName);
            System.out.println("Budget number: " + budgetNumber);
            System.out.println("Expense number: " + expenseNumber);
            new EventCommand(eventName, Integer.parseInt(budgetNumber), Integer.parseInt(expenseNumber));
        }
    }

    // execute category command
    private void executeCategoryCommand() {
        new CategoryCommand(separatedKeywordAndDescription[1]);
    }

}

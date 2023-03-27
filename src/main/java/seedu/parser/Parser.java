package seedu.parser;


import seedu.commands.Command;
import seedu.commands.workoutcommands.EndWorkoutCommand;
import seedu.commands.ExitCommand;
import seedu.commands.workoutcommands.HelpWorkoutCommand;
import seedu.commands.IncorrectCommand;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandName>\\S+)(?<arguments>" +
            ".*)");

    public Command processCommand(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand();
        }

        String commandName = matcher.group("commandName");
        String arguments = matcher.group("arguments");

        switch (commandName) {
        case "/start":
            return CheckInputs.processStart(arguments);
        case "/add":
            return CheckInputs.processAdd(arguments);
        case "/delete":
            return CheckInputs.processDelete(arguments);
        case "/list":
            return CheckInputs.processList(arguments);
        case "/view":
            return CheckInputs.processView(arguments);
        case "/end":
            return new EndWorkoutCommand();
        case "/exit":
            return new ExitCommand();
        case "/help":
            return new HelpWorkoutCommand();
        case "/cadd":
            return CheckCaloriesInput.processAddCalories(arguments);
        default:
            return new IncorrectCommand();
        }
    }
}


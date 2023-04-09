package chching.parser;

import chching.ChChing;
import chching.ChChingException;
import chching.Ui;
import chching.command.AddExpenseCommand;
import chching.command.AddIncomeCommand;
import chching.command.AddTargetCommand;
import chching.command.ClearAllCommand;
import chching.command.ClearExpenseCommand;
import chching.command.ClearIncomeCommand;
import chching.command.ClearTargetCommand;
import chching.command.Command;
import chching.command.InvalidCommand;
import chching.command.ListIncomeCommand;
import chching.command.ListExpenseCommand;
import chching.command.ListCommand;
import chching.command.EditExpenseCommand;
import chching.command.DeleteIncomeCommand;
import chching.command.DeleteExpenseCommand;
import chching.command.BalanceCommand;
import chching.command.ExitCommand;
import chching.command.HelpCommand;
import chching.command.SetCurrencyCommand;
import chching.command.ShowTargetCommand;
import chching.command.UnsetCurrencyCommand;
import chching.command.FindCommand;
import chching.command.EditIncomeCommand;
import chching.record.Expense;
import chching.record.Income;
import chching.record.Target;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Parser {
    public static final String FIELD_DEMARCATION = " /";
    private static final Logger logger = Logger.getLogger(ChChing.class.getName());

    static {
        LogManager.getLogManager().reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
        try {
            new File("data/ParserLog.log").createNewFile();
            FileHandler fileHandler = new FileHandler("data/ParserLog.log");
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    /**
     * Method that parses command to the relevant classes to execute
     *
     * @param line User input
     * @param ui   User interface
     * @return Appropriate command to be executed
     */
    public static Command parse(String line, Ui ui) throws ChChingException {
        List<String> lineParts = splitLine(line);
        String instruction = lineParts.get(0);
        String instructionLowerCase = instruction.toLowerCase();
        List<String> arguments = lineParts.subList(1, lineParts.size());
        HashMap<String, String> argumentsByField = sortArguments(arguments);
        Command command = new Command();
        int index;
        try {
            switch (instructionLowerCase) {
                case "add income":
                    Income income = Incomes.parseIncome(argumentsByField);
                    command = new AddIncomeCommand(income);
                    break;
                case "add expense":
                    Expense expense = Expenses.parseExpense(argumentsByField);
                    command = new AddExpenseCommand(expense);
                    break;
                case "list income":
                    command = new ListIncomeCommand();
                    break;
                case "list expense":
                    command = new ListExpenseCommand();
                    break;
                case "list":
                    command = new ListCommand();
                    break;
                case "edit income":
                    command = new EditIncomeCommand(argumentsByField);
                    break;
                case "edit expense":
                    command = new EditExpenseCommand(argumentsByField);
                    break;
                case "delete income":
                    index = Incomes.getIndex(argumentsByField);
                    command = new DeleteIncomeCommand(index);
                    break;
                case "delete expense":
                    index = Expenses.getIndex(argumentsByField);
                    command = new DeleteExpenseCommand(index);
                    break;
                case "set currency":
                    String currency = Currency.getCurrency(argumentsByField);
                    command = new SetCurrencyCommand(currency);
                    break;
                case "unset currency":
                    currency = Currency.getCurrency(argumentsByField);
                    command = new UnsetCurrencyCommand(currency);
                    break;
                case "find":
                    String type = getType(argumentsByField);
                    String category = getCategory(argumentsByField);
                    String keyword = getDescription(argumentsByField);
                    LocalDate date = getDate(argumentsByField);
                    command = new FindCommand(type, category, keyword, date);
                    break;
                case "balance":
                    command = new BalanceCommand();
                    break;
                case "exit":
                    command = new ExitCommand();
                    break;
                case "help":
                    command = new HelpCommand();
                    break;
                case "clear income":
                    command = new ClearIncomeCommand();
                    ui.showListCleared();
                    break;
                case "clear expense":
                    command = new ClearExpenseCommand();
                    ui.showListCleared();
                    break;
                case "clear all":
                    command = new ClearAllCommand();
                    ui.showListCleared();
                    break;
                case "set target":
                    Target target = TargetParser.parseTarget(argumentsByField);
                    command = new AddTargetCommand(target);
                    ui.showTargetAdded();
                    break;
                case "show target":
                    command = new ShowTargetCommand();
                    break;
                case "clear target":
                    command = new ClearTargetCommand();
                    ui.clearTarget();
                    break;
                default:
                    command = new InvalidCommand();
            }
        } catch (ChChingException e) {
            ui.showError(e.getMessage());
        }
        logger.info("Parser works");
        return command;

    }

    /**
     * Split the String of user input into relevant partitions
     *
     * @param line User input
     * @return An ArrayList of Strings
     */
    public static ArrayList<String> splitLine(String line) {
        ArrayList<String> lineParts = new ArrayList<String>();
        lineParts.addAll(Arrays.asList(line.split(FIELD_DEMARCATION)));
        return lineParts;
    }

    /**
     * Sort the arguments
     *
     * @param arguments arguments
     * @return Hashmap of sorted arguments
     */

    public static HashMap<String, String> sortArguments(List<String> arguments) throws ChChingException {
        HashMap<String, String> argumentsByField = new HashMap<String, String>();
        int argumentsCount = arguments.size();
        for (int i = 0; i < argumentsCount; i++) {
            String argument = arguments.get(i);
            String[] fieldAndValue = argument.split(" ", 2);
            String field = null;
            String value = null;
            try {
                field = fieldAndValue[0].trim().toLowerCase();
                value = fieldAndValue[1].trim();
            } catch (Exception e) {
                if (field == null) {
                    throw new ChChingException("Improper use of \" / \"");
                } else {
                    throw new ChChingException("Arguments not inputted correctly / Missing details");
                }
            }

            // checks if it is an existing field
            boolean isDuplicateField = argumentsByField.containsKey(field);
            // check if field/value is empty or just spaces
            boolean isEmptyFieldOrValue = field.isBlank() || value.isBlank();
            if (isDuplicateField) {
                throw new ChChingException("Duplicate fields detected");
            } else if (isEmptyFieldOrValue) {
                throw new ChChingException("Empty detail detected or improper use of \" / \"");
            } else {
                argumentsByField.put(field, value);
            }
        }

        return argumentsByField;
    }

    public static String getType(HashMap<String, String> argumentsByField) throws ChChingException {
        String type = null;
        try {
            type = argumentsByField.get("t");
        } catch (Exception e) {
            throw new ChChingException("missing/invalid type");
        }
        return type;
    }

    public static String getCategory(HashMap<String, String> argumentsByField) throws ChChingException {
        String category = null;
        try {
            category = argumentsByField.get("c");
        } catch (Exception e) {
            throw new ChChingException("missing/invalid category");
        }
        return category;
    }

    public static String getDescription(HashMap<String, String> argumentsByField) throws ChChingException {
        String description = null;
        try {
            description = argumentsByField.get("de");
        } catch (Exception e) {
            throw new ChChingException("missing/invalid description");
        }
        return description;
    }

    public static LocalDate getDate(HashMap<String, String> argumentsByField) throws ChChingException {
        String dateString = null;
        dateString = argumentsByField.get("da");
        LocalDate date;
        if (dateString == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ChChingException("Date must be valid and have format: \"DD-MM-YYYY\"");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new ChChingException("Date cannot be in the future");
        }
        return date;
    }

}

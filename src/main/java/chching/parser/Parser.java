package chching.parser;
import chching.command.Command;
import chching.command.AddExpenseCommand;
import chching.command.AddIncomeCommand;
import chching.command.BalanceCommand;
import chching.command.ExitCommand;
import chching.command.HelpCommand;
import chching.command.ListCommand;
import chching.command.ListExpenseCommand;
import chching.command.ListIncomeCommand;
import chching.record.ExpenseList;
import chching.record.Expense;
import chching.record.Income;
import chching.record.IncomeList;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser {
    
    public static final String FIELD_DEMARCATION = " /";

    public static Command parse(String line, IncomeList incomeList, ExpenseList expenseList) {
        List<String> lineParts = splitLine(line);
        String instruction = lineParts.get(0);
        List<String> arguments = lineParts.subList(1, lineParts.size());
        HashMap<String, String> argumentsByField = sortArguments(arguments);
        Command command = new ExitCommand();
        switch(instruction) {
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
            Incomes.editIncome(argumentsByField, incomeList);
            break;
        case "edit expense":
            Expense updatedExpense = Expenses.parseUpdateExpense(argumentsByField, expenseList);
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
        default:
            System.out.println("Command not recognized, please enter a valid command!");
        }
        return command;
    }
    
    public static ArrayList<String> splitLine(String line) {
        ArrayList<String> lineParts = new ArrayList<String>();
        lineParts.addAll(Arrays.asList(line.split(FIELD_DEMARCATION)));
        return lineParts;
    }
    
    public static HashMap<String, String> sortArguments(List<String> arguments) {
        HashMap<String, String> argumentsByField = new HashMap<String, String>();
        int argumentsCount = arguments.size();
        
        // split each argument according to their field and their value, and add into hashmap accordingly
        // Hashmap's key is its field, value is the value of the field
        for (int i = 0; i < argumentsCount; i++) {
            String argument = arguments.get(i);
            String[] fieldAndValue = argument.split(" ", 2);
            try {
                String field = fieldAndValue[0].trim();
                String value = fieldAndValue[1].trim();
                argumentsByField.put(field, value);
            } catch (NullPointerException e) {
                System.out.println("Error: arguments not inputted correctly/missing a value");
            }
        }
        return argumentsByField;
    }

}

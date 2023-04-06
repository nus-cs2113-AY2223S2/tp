package seedu.duke;

import org.junit.jupiter.api.Test;

import seedu.duke.budget.BudgetPlanner;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCurrentCommand;
import seedu.duke.command.ListPuCommand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class ParserTest {
    @Test
    public void parseUserCommand_listPU_success() {
        String userInput = "/list pu";
        Parser parser = new Parser();
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();;
        Storage storage = Storage.getInstance();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof ListPuCommand);
    }

    @Test
    public void parseUserCommand_listCurrent_success() {
        String userInput = "/list current";
        Parser parser = new Parser();
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();;
        Storage storage = Storage.getInstance();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof ListCurrentCommand);
    }

    @Test
    public void parseUserCommand_exit_success() {
        String userInput = "/exit";
        Parser parser = new Parser();
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();;
        Storage storage = Storage.getInstance();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof ExitCommand);
    }

    @Test
    public void parseUserCommand_help_success() {
        String userInput = "/help";
        Parser parser = new Parser();
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();
        Storage storage = Storage.getInstance();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof HelpCommand);
    }

}



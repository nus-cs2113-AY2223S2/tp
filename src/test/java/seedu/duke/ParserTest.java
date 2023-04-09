package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.budget.BudgetPlanner;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCurrentCommand;
import seedu.duke.command.ListPuCommand;
import seedu.duke.command.ListMappableNusModsCommand;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class ParserTest {
    @Test
    public void parseUserCommand_listPU_success() {
        String userInput = "/list pu";
        Parser parser = Parser.getInstance();
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
        Parser parser = Parser.getInstance();
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
        Parser parser = Parser.getInstance();
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
        Parser parser = Parser.getInstance();
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

    @Test
    public void parseUserCommand_nusModsList_success() {
        String userInput = "/search /mods";
        Parser parser = Parser.getInstance();
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();;
        Storage storage = Storage.getInstance();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof ListMappableNusModsCommand);
    }

}



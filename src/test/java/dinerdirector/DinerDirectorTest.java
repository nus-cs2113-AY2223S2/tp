package dinerdirector;

import static org.junit.jupiter.api.Assertions.assertTrue;

import commands.Command;
import commands.IncorrectCommand;
import commands.meeting.AddMeetingCommand;
import commands.meeting.DeleteMeetingCommand;
import commands.meeting.ViewMeetingCommand;

import commands.ExitCommand;
import commands.HelpCommand;
import commands.deadline.AddDeadlineCommand;
import commands.deadline.DeleteDeadlineCommand;
import commands.deadline.ViewDeadlineCommand;

import commands.menu.AddDishCommand;
import commands.menu.ViewDishCommand;
import commands.staff.AddStaffCommand;
import commands.staff.DeleteStaffCommand;
import commands.staff.ViewStaffCommand;
import org.junit.jupiter.api.Test;
import utils.Parser;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class DinerDirectorTest {
    @Test
    void runCommandLoopUntilExit_userInput_meetingCommand() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("add_meeting n/a t/3pm");
        listOfCommands.add("view_meetings");
        listOfCommands.add("delete_meeting n/a");
        listOfCommands.add("view_meeting");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("view_meeting")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_meetings")) {
                assertTrue(command instanceof ViewMeetingCommand);
            } else if (listOfCommand.equals("add_meeting n/a t/3pm")) {
                assertTrue(command instanceof AddMeetingCommand);
            } else if (listOfCommand.equals("delete_meeting n/a")) {
                assertTrue(command instanceof DeleteMeetingCommand);
            }
        }
    }

    @Test
    void runCommandLoopUntilExit_userInput_command() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("help");
        listOfCommands.add("exit");
        listOfCommands.add("asdf");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("help")) {
                assertTrue(command instanceof HelpCommand);
            } else if (listOfCommand.equals("exit")) {
                assertTrue(command instanceof ExitCommand);
            } else {
                assertTrue(command instanceof IncorrectCommand);
            }
        }
    }

    @Test
    void runCommandLoopUntilExit_userInput_deadlineCommand() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("add_deadline");
        listOfCommands.add("add_deadline n/add command");
        listOfCommands.add("add_deadline t/when to command");
        listOfCommands.add("add_deadline n/add command t/when to command");
        listOfCommands.add("view_deadlines");
        listOfCommands.add("view_deadlines dsjfnskldf");
        listOfCommands.add("delete_deadline");
        listOfCommands.add("delete_deadline 1");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("add_deadline") ||
                    listOfCommand.equals("add_deadline n/add command") ||
                    listOfCommand.equals("add_deadline t/when to command") ||
                    listOfCommand.equals("view_deadline dsjfnskldf") ||
                    listOfCommand.equals("delete_deadline")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_deadlines")) {
                assertTrue(command instanceof ViewDeadlineCommand);
            } else if (listOfCommand.equals("add_deadline n/add command t/when to command")) {
                assertTrue(command instanceof AddDeadlineCommand);
            } else if (listOfCommand.equals("delete_deadline 1")) {
                assertTrue(command instanceof DeleteDeadlineCommand);
            }
        }
    }

    @Test
    void runCommandLoopUntilExit_userInput_viewDishCommand() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("view_dish");
        listOfCommands.add("sadsajdkjasdas");
        listOfCommands.add("view_dish uweweiwi");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("view_dish")) {
                assertTrue(command instanceof ViewDishCommand);
            } else {
                assertTrue(command instanceof IncorrectCommand);
            }
        }
    }

//    @Test
//    void runCommandLoopUntilExit_userInput_addDishCommand() {
//        ArrayList<String> listOfCommands = new ArrayList<>();
//
//        final String TEST_CASE_BLANK_SPACE = " ";
//        final String TEST_CASE_DECIMAL_NUMBER = "apple pie 2nd edition" + System.lineSeparator() +
//                "3.93";
//        final String TEST_CASE_NEGATIVE_INTEGER = "apple pie 2nd edition" + System.lineSeparator() +
//                "-1";
//        final String TEST_CASE_EMPTY_INGREDIENT_LIST = "apple pie 2nd edition" + System.lineSeparator() +
//                "321" + System.lineSeparator() +
//                "";
//        final String TEST_CASE_CORRECT_INPUT = "apple pie 2nd edition" + System.lineSeparator() +
//                "321" + System.lineSeparator() +
//                "apple flour water butter";
//
//
//        listOfCommands.add(TEST_CASE_BLANK_SPACE);
//        listOfCommands.add(TEST_CASE_DECIMAL_NUMBER);
//        listOfCommands.add(TEST_CASE_NEGATIVE_INTEGER);
//        listOfCommands.add(TEST_CASE_EMPTY_INGREDIENT_LIST);
//        listOfCommands.add(TEST_CASE_CORRECT_INPUT);
//
//        for (String listOfCommand : listOfCommands) {
//
//            ByteArrayInputStream in = new ByteArrayInputStream(listOfCommand.getBytes());
//            System.setIn(in);
//            Command addCommand = new Parser().parseCommand("add_dish");
//
//            if (listOfCommand.equals(TEST_CASE_BLANK_SPACE)) {
//                assertTrue(addCommand instanceof IncorrectCommand);
//            } else if (listOfCommand.equals(TEST_CASE_DECIMAL_NUMBER)) {
//                assertTrue(addCommand instanceof IncorrectCommand);
//            } else if (listOfCommand.equals(TEST_CASE_NEGATIVE_INTEGER)) {
//                assertTrue(addCommand instanceof IncorrectCommand);
//            } else if (listOfCommand.equals(TEST_CASE_EMPTY_INGREDIENT_LIST)) {
//                assertTrue(addCommand instanceof IncorrectCommand);
//            } else if (listOfCommand.equals(TEST_CASE_CORRECT_INPUT)) {
//                assertTrue(addCommand instanceof AddDishCommand);
//            }
//        }
//    }

    @Test
    void runCommandLoopUntilExit_userInput_deleteDishCommand() {
        String setup = "apple pie 2nd edition" + System.lineSeparator() +
                "321" + System.lineSeparator() +
                "apple flour water butter";

        ByteArrayInputStream setupIn = new ByteArrayInputStream(setup.getBytes());
        System.setIn(setupIn);

        ArrayList<String> listOfCommands = new ArrayList<>();

        final String SETUP_ADD_DISH = "add_dish";

        final String TEST_CASE_NO_INDEX = "delete_dish";
        final String TEST_CASE_NEGATIVE_INDEX = "delete_dish -1";
        final String TEST_CASE_ZERO_INDEX = "delete_dish 0";
        final String TEST_CASE_OUT_OF_BOUNDS_INDEX = "delete_dish 2";
        final String TEST_CASE_VALID_INDEX = "delete_dish 1";

        Command setupCommand = new Parser().parseCommand(SETUP_ADD_DISH);

        listOfCommands.add(TEST_CASE_NO_INDEX);
        listOfCommands.add(TEST_CASE_NEGATIVE_INDEX);
        listOfCommands.add(TEST_CASE_ZERO_INDEX);
        listOfCommands.add(TEST_CASE_OUT_OF_BOUNDS_INDEX);
        listOfCommands.add(TEST_CASE_VALID_INDEX);

        for (String listOfCommand : listOfCommands) {
            Command deleteCommand = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals(TEST_CASE_NO_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_NEGATIVE_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_ZERO_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_OUT_OF_BOUNDS_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            }
        }
    }

    @Test
    void runCommandLoopUntilExit_userInput_staffCommand() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("add_staff");
        listOfCommands.add("add_staff n/John Doe");
        listOfCommands.add("add_staff p/123-456-7890 d/1990-01-01 w/Monday");
        listOfCommands.add("add_staff n/John Doe p/123-456-7890 w/Monday d/1990-01-01");
        listOfCommands.add("view_staff");
        listOfCommands.add("delete_staff n/John");
        listOfCommands.add("delete_staff John");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("add_staff") ||
                    listOfCommand.equals("add_staff n/John Doe") ||
                    listOfCommand.equals("add_staff p/123-456-7890 d/1990-01-01 w/Monday") ||
                    listOfCommand.equals("delete_staff John")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_deadlines")) {
                assertTrue(command instanceof ViewStaffCommand);
            } else if (listOfCommand.equals("add_staff n/John Doe p/123-456-7890 w/Monday d/1990-01-01")) {
                assertTrue(command instanceof AddStaffCommand);
            } else if (listOfCommand.equals("delete_deadline n/John")) {
                assertTrue(command instanceof DeleteStaffCommand);
            }
        }
    }
}

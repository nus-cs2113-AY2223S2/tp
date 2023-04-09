package dinerdirector;

import static org.junit.jupiter.api.Assertions.assertTrue;

import commands.Command;
import commands.IncorrectCommand;
import commands.deadline.FindDeadlineCommand;
import commands.meeting.AddMeetingCommand;
import commands.meeting.DeleteMeetingCommand;
import commands.meeting.ViewMeetingCommand;
import commands.meeting.FindMeetingCommand;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.deadline.AddDeadlineCommand;
import commands.deadline.DeleteDeadlineCommand;
import commands.deadline.ViewDeadlineCommand;

import commands.menu.AddDishCommand;
import commands.menu.FindDishCommand;
import commands.menu.ViewDishCommand;
import commands.staff.AddStaffCommand;
import commands.staff.DeleteStaffCommand;
import commands.staff.FindStaffCommand;
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
        listOfCommands.add("add_meeting n/meeting with boss   t/3pm");
        listOfCommands.add("add_meeting n/ t/3pm");
        listOfCommands.add("add_meeting n/b t/");
        listOfCommands.add("add_meeting t/3pm n/name");
        listOfCommands.add("view_meetings");
        listOfCommands.add("delete_meeting 1");
        listOfCommands.add("view_meeting");
        listOfCommands.add("find_meeting a");
        listOfCommands.add("find_meeting");
        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("view_meeting")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_meetings")) {
                assertTrue(command instanceof ViewMeetingCommand);
            } else if (listOfCommand.equals("add_meeting n/a t/3pm")) {
                assertTrue(command instanceof AddMeetingCommand);
            } else if (listOfCommand.equals("add_meeting n/meeting with boss   t/3pm")) {
                assertTrue(command instanceof AddMeetingCommand);
            } else if (listOfCommand.equals("add_meeting n/ t/3pm")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("add_meeting n/b t/")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("add_meeting t/3pm n/name")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("delete_meeting 1")) {
                assertTrue(command instanceof DeleteMeetingCommand);
            } else if(listOfCommand.equals("find_meeting a")){
                assertTrue(command instanceof FindMeetingCommand);
            } else if(listOfCommand.equals("find_meeting")){
                assertTrue(command instanceof IncorrectCommand);
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
        listOfCommands.add("find_deadline");
        listOfCommands.add("find_deadline hi");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("add_deadline") ||
                    listOfCommand.equals("add_deadline n/add command") ||
                    listOfCommand.equals("add_deadline t/when to command") ||
                    listOfCommand.equals("view_deadline dsjfnskldf") ||
                    listOfCommand.equals("delete_deadline") ||
                    listOfCommand.equals("find_deadline")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_deadlines")) {
                assertTrue(command instanceof ViewDeadlineCommand);
            } else if (listOfCommand.equals("add_deadline n/add command t/when to command")) {
                assertTrue(command instanceof AddDeadlineCommand);
            } else if (listOfCommand.equals("delete_deadline 1")) {
                assertTrue(command instanceof DeleteDeadlineCommand);
            } else if (listOfCommand.equals("find_deadline hi")) {
                assertTrue(command instanceof FindDeadlineCommand);
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

    @Test
    void runCommandLoopUntilExit_userInput_addDishCommand() {
        ArrayList<String> listOfCommands = new ArrayList<>();


        final String TEST_CASE_CORRECT_INPUT_FULL = "add_dish n/Chicken Burger pc/1099 " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";
        final String TEST_CASE_CORRECT_INPUT_EMPTY_INGREDIENT_LIST = "add_dish n/Chicken Burger pc/1099 []";
        final String TEST_CASE_CORRECT_INPUT_EMPTY_INGREDIENT_LIST_WITH_SEMICOLON = "add_dish n/Chicken Burger " +
                "pc/1099 [;;]";
        final String TEST_CASE_CORRECT_INPUT_WITH_WHITESPACE_INGREDIENTS_IN_LIST = "add_dish n/Chicken Burger " +
                "pc/1099 [cc;   ;cc]";
        final String TEST_CASE_CORRECT_INPUT_TRAILING_WHITESPACE_INGREDIENTS_IN_LIST = "add_dish n/Chicken Burger " +
                "pc/1099 [ddd  ;dc; dc  ]";

        final String TEST_CASE_ALL_BLANK = "add_dish";
        final String TEST_CASE_BLANK_NAME = "add_dish n/ pc/1099 " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";
        final String TEST_CASE_NAME_STARTS_WITH_SPACE = "add_dish n/ Chicken Burger pc/1099 " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";
        final String TEST_CASE_NAME_WITH_ONLY_SPACES = "add_dish n/  pc/1099 " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";
        final String TEST_CASE_BLANK_PRICE = "add_dish n/Chicken Burger pc/ " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";
        final String TEST_CASE_DECIMAL_NUMBER = "add_dish n/Chicken Burger pc/1099.00 " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";
        final String TEST_CASE_NEGATIVE_INTEGER = "add_dish n/Chicken Burger pc/-1099 " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";

        listOfCommands.add(TEST_CASE_CORRECT_INPUT_FULL);
        listOfCommands.add(TEST_CASE_CORRECT_INPUT_EMPTY_INGREDIENT_LIST);
        listOfCommands.add(TEST_CASE_CORRECT_INPUT_EMPTY_INGREDIENT_LIST_WITH_SEMICOLON);
        listOfCommands.add(TEST_CASE_CORRECT_INPUT_WITH_WHITESPACE_INGREDIENTS_IN_LIST);
        listOfCommands.add(TEST_CASE_CORRECT_INPUT_TRAILING_WHITESPACE_INGREDIENTS_IN_LIST);

        listOfCommands.add(TEST_CASE_ALL_BLANK);
        listOfCommands.add(TEST_CASE_BLANK_NAME);
        listOfCommands.add(TEST_CASE_NAME_STARTS_WITH_SPACE);
        listOfCommands.add(TEST_CASE_NAME_WITH_ONLY_SPACES);
        listOfCommands.add(TEST_CASE_BLANK_PRICE);
        listOfCommands.add(TEST_CASE_DECIMAL_NUMBER);
        listOfCommands.add(TEST_CASE_NEGATIVE_INTEGER);

        for (String listOfCommand : listOfCommands) {

            ByteArrayInputStream in = new ByteArrayInputStream(listOfCommand.getBytes());
            System.setIn(in);
            Command addCommand = new Parser().parseCommand(listOfCommand);

            if (listOfCommand.equals(TEST_CASE_CORRECT_INPUT_FULL)) {
                assertTrue(addCommand instanceof AddDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_CORRECT_INPUT_EMPTY_INGREDIENT_LIST)) {
                assertTrue(addCommand instanceof AddDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_CORRECT_INPUT_EMPTY_INGREDIENT_LIST_WITH_SEMICOLON)) {
                assertTrue(addCommand instanceof AddDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_CORRECT_INPUT_WITH_WHITESPACE_INGREDIENTS_IN_LIST)) {
                assertTrue(addCommand instanceof AddDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_CORRECT_INPUT_TRAILING_WHITESPACE_INGREDIENTS_IN_LIST)) {
                assertTrue(addCommand instanceof AddDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_ALL_BLANK)) {
                assertTrue(addCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_BLANK_NAME)) {
                assertTrue(addCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_NAME_STARTS_WITH_SPACE)) {
                assertTrue(addCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_NAME_WITH_ONLY_SPACES)) {
                assertTrue(addCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_BLANK_PRICE)) {
                assertTrue(addCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_DECIMAL_NUMBER)) {
                assertTrue(addCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_NEGATIVE_INTEGER)) {
                assertTrue(addCommand instanceof IncorrectCommand);
            }
        }
    }

    @Test
    void runCommandLoopUntilExit_userInput_deleteDishCommand() {
        String dishSetup = "add_dish n/Chicken Burger pc/1099 " +
                "[tomatoes;chicken fillet;cheese;bread with sesame seeds]";

        ArrayList<String> listOfCommands = new ArrayList<>();

        final String TEST_CASE_NO_INDEX = "delete_dish";
        final String TEST_CASE_DECIMAL_INDEX = "delete_dish 1.0";
        final String TEST_CASE_NEGATIVE_INDEX = "delete_dish -1";
        final String TEST_CASE_ZERO_INDEX = "delete_dish 0";
        final String TEST_CASE_OUT_OF_BOUNDS_INDEX = "delete_dish 2";
        final String TEST_CASE_VALID_INDEX = "delete_dish 1";

        listOfCommands.add(dishSetup);
        listOfCommands.add(TEST_CASE_NO_INDEX);
        listOfCommands.add(TEST_CASE_DECIMAL_INDEX);
        listOfCommands.add(TEST_CASE_NEGATIVE_INDEX);
        listOfCommands.add(TEST_CASE_ZERO_INDEX);
        listOfCommands.add(TEST_CASE_OUT_OF_BOUNDS_INDEX);
        listOfCommands.add(TEST_CASE_VALID_INDEX);


        for (String listOfCommand : listOfCommands) {
            Command deleteCommand = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals(dishSetup)) {
                assertTrue(deleteCommand instanceof AddDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_NO_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_DECIMAL_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_NEGATIVE_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_ZERO_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_OUT_OF_BOUNDS_INDEX)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals((TEST_CASE_VALID_INDEX))) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            }
        }
    }

    @Test
    void runCommandLoopUntilExit_userInput_findDishCommand() {

        ArrayList<String> listOfCommands = new ArrayList<>();

        final String TEST_CASE_VALID_INPUT = "find_dish Burger";

        final String TEST_CASE_NO_WORD = "find_dish";
        final String TEST_CASE_JUST_ONE_SPACE = "find_dish ";
        final String TEST_CASE_JUST_SPACES = "find_dish   ";
        final String TEST_CASE_WORD_WITH_SPACES_IN_BETWEEN = "find_dish   Burger";
        final String TEST_CASE_VALID_INPUT_WORD_WITH_TRAILING_SPACE = "find_dish Burger  ";
        final String TEST_CASE_WORD_WITH_MULTIPLE_KEYWORDS = "find_dish fish Burger";
        final String TEST_CASE_VALID_INPUT_WORD_WITH_MULTIPLE_KEYWORDS_WITH_SPACES_IN_BETWEEN
                = "find_dish fish   Burger";

        listOfCommands.add(TEST_CASE_VALID_INPUT);
        listOfCommands.add(TEST_CASE_VALID_INPUT_WORD_WITH_MULTIPLE_KEYWORDS_WITH_SPACES_IN_BETWEEN);
        listOfCommands.add(TEST_CASE_VALID_INPUT_WORD_WITH_TRAILING_SPACE);

        listOfCommands.add(TEST_CASE_NO_WORD);
        listOfCommands.add(TEST_CASE_JUST_ONE_SPACE);
        listOfCommands.add(TEST_CASE_JUST_SPACES);
        listOfCommands.add(TEST_CASE_WORD_WITH_SPACES_IN_BETWEEN);
        listOfCommands.add(TEST_CASE_WORD_WITH_MULTIPLE_KEYWORDS);

        for (String listOfCommand : listOfCommands) {
            Command deleteCommand = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals(TEST_CASE_VALID_INPUT)) {
                assertTrue(deleteCommand instanceof FindDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_WORD_WITH_SPACES_IN_BETWEEN)) {
                assertTrue(deleteCommand instanceof FindDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_VALID_INPUT_WORD_WITH_TRAILING_SPACE)) {
                assertTrue(deleteCommand instanceof FindDishCommand);
            } else if (listOfCommand.equals(TEST_CASE_NO_WORD)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_JUST_ONE_SPACE)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals(TEST_CASE_JUST_SPACES)) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            } else if (listOfCommand.equals((TEST_CASE_WORD_WITH_MULTIPLE_KEYWORDS))) {
                assertTrue(deleteCommand instanceof IncorrectCommand);
            }
        }
    }

    @Test
    void runCommandLoopUntilExit_userInput_staffCommand() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("add_staff");
        listOfCommands.add("add_staff n/John Doe");
        listOfCommands.add("add_staff p/82813828 d/1990-01-01 w/Monday");
        listOfCommands.add("add_staff n/John Doe p/2413131313 w/Monday d/1990-01-01");
        listOfCommands.add("view_staff");
        listOfCommands.add("find_staff John");
        listOfCommands.add("find_staff");
        listOfCommands.add("delete_staff 1");
        listOfCommands.add("delete_staff 100");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("add_staff") ||
                    listOfCommand.equals("add_staff n/John Doe") ||
                    listOfCommand.equals("add_staff p/8281231327127327312773273721713828 d/1990-01-01 w/Monday") ||
                    listOfCommand.equals("delete_staff 100") ||
                    listOfCommand.equals("find_staff")){
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_deadlines")) {
                assertTrue(command instanceof ViewStaffCommand);
            } else if (listOfCommand.equals("add_staff n/John Doe w/sunday d/2003-12-12 p/123113")) {
                assertTrue(command instanceof AddStaffCommand);
            } else if (listOfCommand.equals("delete_deadline 1")) {
                assertTrue(command instanceof DeleteStaffCommand);
            } else if(listOfCommand.equals("find_staff John")){
                assertTrue(command instanceof FindStaffCommand);
            }
        }
    }
}

package utils;

import commands.meeting.FindMeetingCommand;
import commands.menu.FindDishCommand;
import commands.staff.AddStaffCommand;
import commands.staff.FindStaffCommand;
import commands.staff.ViewStaffCommand;
import commands.staff.DeleteStaffCommand;
import commands.HelpCommand;
import commands.ExitCommand;
import commands.deadline.AddDeadlineCommand;
import commands.deadline.ViewDeadlineCommand;
import commands.deadline.DeleteDeadlineCommand;
import commands.deadline.FindDeadlineCommand;
import commands.menu.AddDishCommand;
import commands.menu.DeleteDishCommand;
import commands.menu.ViewDishCommand;
import commands.IncorrectCommand;
import commands.meeting.AddMeetingCommand;
import commands.meeting.DeleteMeetingCommand;
import commands.meeting.ViewMeetingCommand;
import commands.Command;

import common.Messages;
import exceptions.DinerDirectorException;
import entity.Deadline;
import manager.StaffManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static manager.DishManager.getDishesSize;

/**
 * Parser to tokenize the input
 */
public class Parser {


    public Command parseCommand(String userInput) {
        assert userInput != null : "userInput should not be null";
        String[] userInputSplit = userInput.split(" ");
        String commandWord = userInputSplit[0];
        String userInputNoCommand = userInput.replace(userInputSplit[0], "");
        //@@damithc darrenangwx-reused
        //Source:
        //https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/parser/Parser.java
        //Reused the switch skeleton
        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand();
        case ExitCommand.COMMAND_WORD:
            return prepareExitCommand();
        case AddMeetingCommand.COMMAND_WORD:
            return prepareAddMeetingCommand(userInputNoCommand);
        case DeleteMeetingCommand.COMMAND_WORD:
            return prepareDeleteMeetingCommand(userInputNoCommand);
        case ViewMeetingCommand.COMMAND_WORD:
            return prepareViewMeetingCommand(userInputNoCommand);
        case FindMeetingCommand.COMMAND_WORD:
            return prepareFindMeetingCommand(userInputNoCommand);
        case AddStaffCommand.COMMAND_WORD:
            return prepareAddStaffCommand(userInputNoCommand);
        case DeleteStaffCommand.COMMAND_WORD:
            return prepareDeleteStaffCommand(userInputNoCommand);
        case ViewStaffCommand.COMMAND_WORD:
            return prepareViewStaffCommand(userInputNoCommand);
        case FindStaffCommand.COMMAND_WORD:
            return prepareFindStaffCommand(userInputNoCommand);
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineCommand(userInputNoCommand);
        case DeleteDeadlineCommand.COMMAND_WORD:
            return prepareDeleteDeadlineCommand(userInputNoCommand);
        case ViewDeadlineCommand.COMMAND_WORD:
            return prepareViewDeadlineCommand(userInputNoCommand);
        case FindDeadlineCommand.COMMAND_WORD:
            return prepareFindDeadlineCommand(userInputNoCommand);
        case AddDishCommand.COMMAND_WORD:
            return prepareAddDishCommand(userInputNoCommand);
        case DeleteDishCommand.COMMAND_WORD:
            return prepareDeleteDishCommand(userInputNoCommand);
        case ViewDishCommand.COMMAND_WORD:
            return prepareViewDishCommand(userInputNoCommand);
        case FindDishCommand.COMMAND_WORD:
            return prepareFindDishCommand(userInputNoCommand);
        default:
            return new IncorrectCommand();
        }
        //@@damithc
    }

    //Solution below adapted from https://github.com/Stella1585/ip/blob/master/src/main/java/duke/Parser.java
    private Command prepareAddMeetingCommand(String description) {
        String[] words = (description.trim()).split("t/");
        String[] testName = (description.trim()).split("n/");
        try {
            if (((description.trim()).isEmpty()) || (!description.contains("n/")) || (words.length < 2)) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_PARAM);
            } else if ((testName.length > 2) || (words.length > 2)) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_EXCESS_ADD_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        String issue = (words[0].substring(2)).trim();
        String time = words[1].trim();
        return new AddMeetingCommand(time, issue);
    }

    private Command prepareViewMeetingCommand(String userInput) {
        try {
            if (!userInput.isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_EXCESS_VIEW_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new ViewMeetingCommand();
    }

    private Command prepareDeleteMeetingCommand(String description) {
        int index;
        try {
            index = Integer.parseInt((description.trim())) - 1;
            if ((description.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_INDEX);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }catch (NumberFormatException e) {
            System.out.println(Messages.ERROR_MEETING_MISSING_INDEX);
            return new IncorrectCommand();
        }
        assert index >= 0 : "Index of meeting should be 0 or greater.";
        return new DeleteMeetingCommand(index);
    }
    private Command prepareFindMeetingCommand(String description){
        try {
            if ((description.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new FindMeetingCommand(description.trim());
    }

    /**
     * Parsing the input into properties of Staff class and checking for error.
     * @param userInputNoCommand Cleaned UserInput without the Command keyword.
     * @return AddStaffCommand object if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareAddStaffCommand(String userInputNoCommand) {
        String[] userInputNoCommandSplitBySlash = userInputNoCommand.trim().split("/");
        try {
            if (userInputNoCommandSplitBySlash.length < 5 || userInputNoCommand.trim().isEmpty()
                    || !userInputNoCommand.contains("n/") || !userInputNoCommand.contains("w/")
                    || !userInputNoCommand.contains("d/") || !userInputNoCommand.contains("p/")) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_MISSING_PARAM);
            } else if (userInputNoCommandSplitBySlash.length > 5) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_EXCESS_PARAM);
            }
                String pattern = "n/(?<name>[\\w\\s]+)" +
                        "\\sw/(?<workingDay>[\\w\\s]+)" +
                        "\\sd/(?<dateOfBirth>(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01]))" +
                        "\\sp/(?<phoneNumber>[\\d\\s]+)";

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(userInputNoCommand);
            String staffName = "";
            String staffWorkingDay = "";
            String staffPhoneNumber = "";
            String staffDateOfBirth = "";
            if (matcher.find()) {
                staffName = matcher.group("name");
                if (staffName.equals("")) {
                    throw new DinerDirectorException(Messages.INVALID_STAFF_ADD_NAME);
                }
                staffWorkingDay = matcher.group("workingDay");
                if (staffWorkingDay.equals("")) {
                    throw new DinerDirectorException(Messages.INVALID_STAFF_ADD_WORKING_DAY);
                }
                staffPhoneNumber = matcher.group("phoneNumber");
                if (staffPhoneNumber.equals("")) {
                    throw new DinerDirectorException(Messages.INVALID_STAFF_ADD_PHONE_NUMBER);
                }
                staffDateOfBirth = matcher.group("dateOfBirth");
                if (staffDateOfBirth.equals("") || staffDateOfBirth.length() != 10) {
                    throw new DinerDirectorException(Messages.INVALID_STAFF_ADD_DATE_OF_BIRTH);
                }

                LocalDate today = LocalDate.now();
                LocalDate parsedStaffDateOfBirth = LocalDate.parse(staffDateOfBirth);
                if (parsedStaffDateOfBirth.isAfter(today)) {
                    throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_FUTURE_DOB);
                }
            }
            if (staffPhoneNumber.length() > 15) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_EXCESS_PHONE_NUMBER);
            }

            return new AddStaffCommand(staffName, staffWorkingDay, staffDateOfBirth, staffPhoneNumber);
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
    }

    /**
     * Check whether there is excess parameter.
     * @param userInputNoCommand Cleaned UserInput without the Command keyword.
     * @return ViewStaffCommand object if the input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareViewStaffCommand(String userInputNoCommand) {
        try {
            if (!userInputNoCommand.isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_EXCESS_VIEW_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new ViewStaffCommand();
    }

    /**
     * Check whether user gives a number and that number is a valid staff index.
     * @param userInputNoCommand userInputNoCommand Cleaned UserInput without the Command keyword.
     * @return DeleteStaffCommand object if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareDeleteStaffCommand(String userInputNoCommand) {
        int indexToRemove = 0;
        try {
            indexToRemove = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if (indexToRemove < 0 || indexToRemove >= StaffManager.getStaffs().size()) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_INVALID_INDEX);
            }
            assert indexToRemove >= 0 : "indexToRemove should be 0 or greater";
        } catch (NumberFormatException e) {
            return new IncorrectCommand();
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new DeleteStaffCommand(indexToRemove);
    }

    /**
     * Check whether user gives a description or not.
     * @param description The substring that is contained in Staff name to be searched.
     * @return FindStaffCommand object if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareFindStaffCommand(String description){
        try {
            if ((description.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_FIND_MISSING_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new FindStaffCommand(description.trim());

    }
    private Command prepareHelpCommand() {
        return new HelpCommand();
    }

    private Command prepareExitCommand() {
        return new ExitCommand();
    }

    //Solution below adapted from https://github.com/Stella1585/ip/blob/master/src/main/java/duke/Parser.java

    /**
     * Creates a deadline item based on descriptions given by the user, then returns
     * an add deadline command.
     *
     * @param description contains the deadline description and due date.
     * @return the add deadline command.
     */
    private Command prepareAddDeadlineCommand(String description) { //  n/ t/
        String[] words = (description.trim()).split("t/"); // n/
        try {
            String[] testName = (words[0].trim()).split("n/");  // n/
            if (((description.trim()).isEmpty()) || (!description.contains("n/"))
                    || (words.length < 2) || (testName.length < 1)) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_MISSING_PARAM);
            } else if ((testName.length > 2) || (words.length > 2)) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_EXCESS_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        String name = (words[0].substring(2)).trim();
        String dueDate = words[1].trim();
        assert !name.isEmpty() : "name should be present";
        assert !dueDate.isEmpty() : "dueDate should be present";
        Deadline deadline = new Deadline(name, dueDate);
        return new AddDeadlineCommand(deadline);
    }

    /**
     * Checks for error in the view deadline command, then returns a view deadline command.
     *
     * @param userInput view deadline command
     * @return the view deadline command.
     */
    private Command prepareViewDeadlineCommand(String userInput) {
        try {
            if (!(userInput.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_EXCESS_LIST_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        assert (userInput.trim()).isEmpty() : Messages.ERROR_DEADLINE_EXCESS_LIST_PARAM;
        return new ViewDeadlineCommand();
    }


    /**
     * Checks for error in the delete deadline command, then returns
     * a delete deadline command.
     *
     * @param description contains the index number.
     * @return the delete deadline command.
     */
    private Command prepareDeleteDeadlineCommand(String description) {
        int index;
        try {
            index = Integer.parseInt((description.trim())) - 1;
            if (description.isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_MISSING_INDEX);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.ERROR_DEADLINE_MISSING_INDEX);
            return new IncorrectCommand();
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        assert index >= 0 : "indexToRemove should be 0 or greater.";
        return new DeleteDeadlineCommand(index);
    }

    /**
     * Checks for error in the find deadline command, then returns
     * a find deadline command.
     *
     * @param keyword the keyword to search for.
     * @return the find deadline command.
     */
    private Command prepareFindDeadlineCommand(String keyword) {
        try {
            if ((keyword.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_MISSING_KEYWORD);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new FindDeadlineCommand((keyword.trim()));
    }
    
    private Command prepareDeleteDishCommand(String userInputNoCommand) {
    
        int indexToRemove = 0;

        try {
            indexToRemove = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if (indexToRemove < 0 || indexToRemove >= getDishesSize()) {
                throw new DinerDirectorException(Messages.ERROR_DISH_INVALID_INDEX);
            }
            assert indexToRemove >= 0 : "indexToRemove should be 0 or greater";
        } catch (NumberFormatException e) {
            System.out.println(Messages.ERROR_DISH_NOT_A_VALID_INTEGER);
            return new IncorrectCommand();
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new DeleteDishCommand(indexToRemove);
    }

    private Command prepareViewDishCommand(String userInputNoCommand) {
        try {
            if (!userInputNoCommand.isBlank()) {
                throw new DinerDirectorException(Messages.ERROR_COMMAND_INVALID);
            }
        } catch (DinerDirectorException e) {
            return new IncorrectCommand();
        }
        return new ViewDishCommand();
    }

    private Command prepareAddDishCommand(String userInputNoCommand) {
        //MENU COMMANDS: add_dish n/<name>
        //                      pc/<price in cents>
        //                      [<ingredient 1>;<ingredients 2>;<ingredient 3>; ... ]

        String name = "";
        int price = 0;
        ArrayList<String> ingredients = new ArrayList<>();

        String regex = " n/(?=\\S)(.*?) pc/(\\d+) \\[(.*?)]";

        Pattern dishPattern = Pattern.compile(regex);
        Matcher parsedDishInput = dishPattern.matcher(userInputNoCommand);

        try {
            if (parsedDishInput.matches()) {
                name = parsedDishInput.group(1);
                price = Integer.parseInt(parsedDishInput.group(2));
                String[] ingredientList = parsedDishInput.group(3).split(";");
                for (String ingredient : ingredientList) {
                    if (!ingredient.isBlank()) {
                        ingredients.add(ingredient);
                    }
                }
            } else {
                throw new DinerDirectorException(Messages.ERROR_COMMAND_INVALID);
            }
        } catch (DinerDirectorException e) {
            return new IncorrectCommand();
        }
        return new AddDishCommand(name, price, ingredients);
    }

    private Command prepareFindDishCommand(String userInputNoCommand) {
        String stringToFind = "";

        try {
            String[] keywords;
            keywords = userInputNoCommand.trim().split(" ");
            if (keywords[0].isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_DISH_MISSING_KEYWORD);
            } else if (keywords.length > 1) {
                throw new DinerDirectorException(Messages.ERROR_DISH_MULTIPLE_KEYWORDS);
            } else if (keywords.length == 1) {
                stringToFind = keywords[0];
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new FindDishCommand(stringToFind);
    }

}

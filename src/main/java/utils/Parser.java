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
import manager.DishManager;
import manager.StaffManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static manager.DishManager.getDishesSize;

public class Parser {

    /**
     * Returns the Command object to execute.
     * Prepares the command to ensure execution works.
     *
     * @param userInput The raw input of what the user typed.
     * @return Command object in order for the program to know what command to execute.
     */
    public Command parseCommand(String userInput) {
        assert userInput != null : "userInput should not be null";
        String[] userInputSplit = userInput.trim().split(" ");
        String commandWord = userInputSplit[0];
        String userInputNoCommand = userInput.replace(userInputSplit[0], "");
        //@@damithc darrenangwx-reused
        //Source:
        //https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/parser/Parser.java
        //Reused the switch skeleton
        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand(userInputNoCommand);
        case ExitCommand.COMMAND_WORD:
            return prepareExitCommand(userInputNoCommand);
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
    /**
     * Parsing the input into properties of Meeting class and checking for error.
     *
     * @param description Cleaned UserInput without the Command keyword.
     * @return AddMeetingCommand object based on the info in the description,
     *     if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareAddMeetingCommand(String description) {
        String[] words = (description.trim()).split("t/");
        String[] testName = (description.trim()).split("n/");
        try {
            if (((description.trim()).isEmpty()) || (!description.contains("n/")) ||
                    (!description.contains("t/")) || (words.length < 2) || (words[0].trim().length() < 3)) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_PARAM);
            } else if ((testName.length > 2) || (words.length > 2)) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_EXCESS_ADD_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        String issue = (words[0].substring(2)).trim();
        String time = words[1].trim();
        return new AddMeetingCommand(time, issue);
    }
    /**
     * Checking for whether user input has excess command.
     *
     * @param userInput Cleaned UserInput without the Command keyword.
     * @return ViewMeetingCommand object if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareViewMeetingCommand(String userInput) {
        try {
            if (!userInput.isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_EXCESS_VIEW_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new ViewMeetingCommand();
    }
    /**
     * Checking whether user input has an number and the number format.
     *
     * @param description Cleaned UserInput without the Command keyword.
     * @return DeleteMeetingCommand object if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareDeleteMeetingCommand(String description) {
        int index;
        try {
            index = Integer.parseInt((description.trim())) - 1;
            if ((description.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_INDEX);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        } catch (NumberFormatException e) {
            System.out.println(Messages.ERROR_MEETING_MISSING_INDEX);
            return new IncorrectCommand();
        }
        assert index >= 0 : "Index of meeting should be 0 or greater.";
        return new DeleteMeetingCommand(index);
    }
    /**
     * Checking whether the user input is empty.
     *
     * @param description Cleaned UserInput without the Command keyword.
     * @return FindMeetingCommand object if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareFindMeetingCommand(String description) {
        try {
            if ((description.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new FindMeetingCommand(description.trim());
    }

    /**
     * Parsing the input into properties of Staff class and checking for error.
     *
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
                    "\\sp/(?<phoneNumber>\\d+)$";

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(userInputNoCommand);
            String staffName = "";
            String staffWorkingDay = "";
            String staffPhoneNumber = "";
            String staffDateOfBirth = "";
            if (matcher.find()) {
                staffName = matcher.group("name");
                staffWorkingDay = matcher.group("workingDay");
                staffPhoneNumber = matcher.group("phoneNumber");
                staffDateOfBirth = matcher.group("dateOfBirth");
                LocalDate today = LocalDate.now();
                LocalDate parsedStaffDateOfBirth = LocalDate.parse(staffDateOfBirth);
                if (parsedStaffDateOfBirth.isAfter(today)) {
                    throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_FUTURE_DOB);
                }

            }

            if (staffName.equals("") || staffWorkingDay.equals("") || staffPhoneNumber.equals("")
                || staffDateOfBirth.equals("") || staffDateOfBirth.length() != 10
            ) {
                throw new DinerDirectorException(Messages.INVALID_STAFF_ADD_PARAMETERS);
            }

            if (staffPhoneNumber.length() > 15) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_EXCESS_PHONE_NUMBER);
            }

            return new AddStaffCommand(staffName, staffWorkingDay, staffDateOfBirth, staffPhoneNumber);
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
    }

    /**
     * Check whether there is excess parameter.
     *
     * @param userInputNoCommand Cleaned UserInput without the Command keyword.
     * @return ViewStaffCommand object if the input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareViewStaffCommand(String userInputNoCommand) {
        try {
            if (!userInputNoCommand.isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_EXCESS_VIEW_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new ViewStaffCommand();
    }

    /**
     * Check whether user gives a number and that number is a valid staff index.
     *
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
     *
     * @param description The substring that is contained in Staff name to be searched.
     * @return FindStaffCommand object if input is valid, otherwise IncorrectCommand object.
     */
    private Command prepareFindStaffCommand(String description) {
        try {
            if ((description.trim()).isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_FIND_MISSING_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new FindStaffCommand(description.trim());

    }

    /**
     * Returns the Help Command object.
     * If invalid Help command, returns IncorrectCommand object.
     *
     * @param userInputNoCommand userInput without the command portion.
     * @return Command object.
     */
    private Command prepareHelpCommand(String userInputNoCommand) {
        try {
            if (!userInputNoCommand.isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_HELP_EXCESS_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new HelpCommand();
    }

    /**
     * Returns the Exit Command object.
     * If invalid Exit command, returns IncorrectCommand object.
     *
     * @param userInputNoCommand userInput without the command portion.
     * @return Command object.
     */
    private Command prepareExitCommand(String userInputNoCommand) {
        try {
            if (!userInputNoCommand.isEmpty()) {
                throw new DinerDirectorException(Messages.ERROR_EXIT_EXCESS_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
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
    private Command prepareAddDeadlineCommand(String description) {
        String[] words = (description.trim()).split("t/");
        try {
            String[] testName = (words[0].trim()).split("n/");
            if (((description.trim()).isEmpty()) || (!description.contains("n/"))
                    || (words.length < 2) || (testName.length < 1)) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_MISSING_PARAM);
            } else if ((testName.length > 2) || (words.length > 2)) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_EXCESS_PARAM);
            } else if (words[1].contains("n/")) {
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_WRONG_ORDER);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new FindDeadlineCommand((keyword.trim()));
    }

    /**
     * Deletes a Dish based on the index given by the user
     * If index is valid, returns AddDishCommand().
     * Otherwise, it returns IncorrectCommand().
     *
     * @param userInputNoCommand Contains the index for deleting a dish.
     * @return The delete dish command.
     */
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

    /**
     * Returns the ViewDishCommand after checking the input command by the user.
     * If the command keyed in is valid, returns ViewDishCommand().
     * Otherwise it returns IncorrectCommand().
     *
     * @param userInputNoCommand The view Dish command.
     * @return The ViewDishCommand.
     */
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

    /**
     * Creates a Dish based on parameters given by the user
     * If successfully created, returns AddDishCommand().
     * Otherwise, it returns IncorrectCommand().
     *
     * @param userInputNoCommand Contains the parameters for creating a dish.
     * @return The add dish command.
     */
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
                if (DishManager.isInsideDishes(name)) {
                    throw new DinerDirectorException(Messages.ERROR_DUPLICATE_DISH_NAME);
                }
                try {
                    price = Integer.parseInt(parsedDishInput.group(2));
                } catch (NumberFormatException e) {
                    throw new DinerDirectorException(Messages.ERROR_PRICE_EXCEED_INTEGER_BOUNDS);
                }
                String[] ingredientList = parsedDishInput.group(3).split(";");
                for (String ingredient : ingredientList) {
                    String regexNumbers = "^[+-]?\\d+(?:\\.\\d+)?$";
                    Pattern pattern = Pattern.compile(regexNumbers);
                    Matcher parsedIngredient = pattern.matcher(ingredient);
                    if (!ingredient.isBlank() && !parsedIngredient.matches()) {
                        ingredients.add(ingredient);
                    }
                }
            } else {
                throw new DinerDirectorException(Messages.ERROR_COMMAND_INVALID);
            }
        } catch (DinerDirectorException e) {
            if (!e.getMessage().equals(Messages.ERROR_COMMAND_INVALID)) {
                System.out.println(e.getMessage());
            }
            return new IncorrectCommand();
        }

        return new AddDishCommand(name, price, ingredients);
    }

    /**
     * Finds a Dish based on a keyword given by the user.
     * If keyword is valid, returns AddDishCommand().
     * Otherwise, it returns IncorrectCommand().
     *
     * @param userInputNoCommand Contains the keyword to find the dishes.
     * @return The find dish command.
     */
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

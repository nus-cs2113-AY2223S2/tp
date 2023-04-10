package functionalities.parser;

import exception.SniffException;
import functionalities.commands.ArchiveCommand;
import functionalities.commands.Command;
import functionalities.commands.ConsultationCommand;
import functionalities.commands.ExitCommand;
import functionalities.commands.FindCommand;
import functionalities.commands.HelpCommand;
import functionalities.commands.ListCommand;
import functionalities.commands.MarkCommand;
import functionalities.commands.RemoveCommand;
import functionalities.commands.SurgeryCommand;
import functionalities.commands.UnMarkCommand;
import functionalities.commands.VaccinationCommand;
import functionalities.commands.EditConsultationCommand;
import functionalities.commands.EditSurgeryCommand;
import functionalities.commands.EditVaccinationCommand;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

public class Parser {

    protected static Command command;
    static Logger logger = Logger.getLogger("parser");

    public static Command parse(String userCommand) throws SniffException {
        try {
            String command = getCommand(userCommand);
            String[] task = userCommand.split(command);
            if (command.equals("consultation")) {
                parseConsultationCommand(task[1]);
            } else if (command.equals("vaccination")) {
                parseVaccinationCommand(task[1]);
            } else if (command.equals("surgery")) {
                parseSurgeryCommand(task[1]);
            } else if (command.equals("find")) {
                parseFindCommand(userCommand.trim());
            } else if (command.equals("remove")) {
                parseRemoveCommand(userCommand.trim());
            } else if (command.equals("mark")) {
                parseMarkCommand(userCommand.trim());
            } else if (command.equals("unmark")) {
                parseUnmarkCommand(userCommand.trim());
            } else if (command.equals("edit")) {
                parseEditCommand(task[1]);
            } else if (userCommand.equals("list")) {
                parseListCommand(userCommand.trim());
            } else if (userCommand.equals("help")) {
                parseHelpCommand();
            } else if (userCommand.equals("archive")) {
                parseArchiveCommand();
            } else if (userCommand.equals("bye")) {
                parseByeCommand();
            } else {
                throw new SniffException(" Not a recognized Sniff command!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The Sniff command is incomplete!");
        }
        return command;
    }

    /**
     * Parses the user's input command and returns only the first command before any command input descriptions.
     *
     * @param input the full user input.
     * @return the command before any other input descriptions
     * @throws SniffException if the command is of an invalid format.
     */
    private static String getCommand(String input) throws SniffException {
        try {
            String[] firstSplit = input.split("(at/|an/|on/|cn/|cd/|ct/|vd/|vn/|v/|" +
                    "sd/|st/|ed/|et/|p/|a/|t/|uID/|d/|uid/)", 2);
            return firstSplit[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException("  Not a recognized Sniff command!");
        }
    }

    /**
     * Parses the user's consultation command into its respective inputs and creates a new ConsultationCommand object.
     * The consultation command is expected to have the following format:
     * consultation at/[animal type] an/[animal name] on/[owner name] cn/[contact number] cd/[date] ct/[time]
     *
     * @param task the Snifftask class that handles the adding of the consultation appointment.
     * @throws SniffException if the consultation command or its descriptions are in the wrong format,
     *                        or if the date/time description is invalid.
     */
    private static void parseConsultationCommand(String task) throws SniffException {
        try {
            String animalType = splitConsultationInput(task, "at/");
            String animalName = splitConsultationInput(task, "an/");
            String ownerName = splitConsultationInput(task, "on/");
            String contactNumber = splitConsultationInput(task, "cn/");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = splitConsultationInput(task, "cd/");
            LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
            String time = splitConsultationInput(task, "ct/");
            LocalTime parsedTime = LocalTime.parse(time, timeFormatter);
            command = new ConsultationCommand(animalType, animalName, ownerName, contactNumber, parsedDate, parsedTime);
        } catch (DateTimeParseException e) {
            throw new SniffException("The date/time description is invalid.");
        } catch (NullPointerException e) {
            throw new SniffException("The consultation description is invalid!");
        }
    }

    /**
     * Splits the given consultation input command using the specified splitter and returns the specified description.
     *
     * @param input    the full consultation command from the user.
     * @param splitter the tag of the specified description.
     * @return the actual specified description.
     * @throws SniffException if the consultation command contains other command tags, or if there are repeated
     *                        descriptions, or if the descriptions are invalid format.
     */
    private static String splitConsultationInput(String input, String splitter) throws SniffException {
        if (input.contains("v/") || input.contains("vd/") || input.contains("vt/") || input.contains("sd/") ||
                input.contains("st/") || input.contains("ed/") || input.contains("et/") || input.contains("p/")) {
            throw new SniffException(" The consultation command is of the wrong format!");
        }
        try {
            String[] firstSplit = input.split(splitter, 2);
            if (firstSplit[1].contains(splitter)) {
                throw new SniffException(" The consultation command cannot contain repeated descriptions!");
            }
            String[] secondSplit = firstSplit[1].split("(at/|an/|on/|cn/|cd/|ct/)", -1);
            return secondSplit[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The " + splitter + " description is invalid!");
        }
    }

    /**
     * Parses the user's vaccination command into its respective inputs and creates a new VaccinationCommand object.
     * The vaccination command is expected to have the following format:
     * vaccination at/[animal type] an/[animal name] on/[owner name] cn/[contact number] v/[vaccine] vd/[date]
     * vt/[time]
     *
     * @param task the Snifftask class that handles the adding of the vaccination appointment.
     * @throws SniffException if the vaccination command or its descriptions are in the wrong format,
     *                        or if the date/time description is invalid.
     */
    private static void parseVaccinationCommand(String task) throws SniffException {
        try {
            String animalType = splitVaccinationInput(task, "at/");
            String animalName = splitVaccinationInput(task, "an/");
            String ownerName = splitVaccinationInput(task, "on/");
            String contactNumber = splitVaccinationInput(task, "cn/");
            String vaccine = splitVaccinationInput(task, "v/");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = splitVaccinationInput(task, "vd/");
            LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
            String time = splitVaccinationInput(task, "vt/");
            LocalTime parsedTime = LocalTime.parse(time, timeFormatter);
            command = new VaccinationCommand(animalType, animalName, ownerName, contactNumber,
                    vaccine, parsedDate, parsedTime);
        } catch (DateTimeParseException e) {
            throw new SniffException("The date/time description is invalid.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException("The vaccination description is invalid!");
        }
    }

    /**
     * Splits the given vaccination input command using the specified splitter and returns the specified description.
     *
     * @param input    the full vaccination command from the user.
     * @param splitter the tag of the specified description.
     * @return the actual specified description.
     * @throws SniffException if the vaccination command contains other command tags, or if there are repeated
     *                        descriptions, or if the descriptions are invalid format.
     */
    private static String splitVaccinationInput(String input, String splitter) throws SniffException {
        if (input.contains("cd/") || input.contains("ct/") || input.contains("sd/") || input.contains("st/") ||
                input.contains("ed/") || input.contains("et/") || input.contains("p/")) {
            throw new SniffException(" The vaccination command is of the wrong format!");
        }
        try {
            String[] firstSplit = input.split(splitter, 2);
            if (firstSplit[1].contains(splitter)) {
                throw new SniffException(" The vaccination command cannot contain repeated descriptions!");
            }
            String[] secondSplit = firstSplit[1].split("(at/|an/|on/|cn/|vd/|vt/|v/)", -1);
            return secondSplit[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The " + splitter + " description is invalid!");
        }
    }

    /**
     * Parses the user's surgery command into its respective inputs and creates a new SurgeryCommand object.
     * The surgery command is expected to have the following format:
     * surgery at/[animal type] an/[animal name] on/[owner name] cn/[contact number] sd/[start date]
     * st/[start time] ed/[end date] et/[end time] p/[priority level]
     *
     * @param task the Snifftask class that handles the adding of the surgery appointment.
     * @throws SniffException if the surgery command or its descriptions are in the wrong format,
     *                        or if the date/time description is invalid.
     */
    private static void parseSurgeryCommand(String task) throws SniffException {
        try {
            String animalType = splitSurgeryInput(task, "at/");
            String animalName = splitSurgeryInput(task, "an/");
            String ownerName = splitSurgeryInput(task, "on/");
            String contactNumber = splitSurgeryInput(task, "cn/");
            String priority = splitSurgeryInput(task, "p/");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDate = splitSurgeryInput(task, "sd/");
            LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
            String startTime = splitSurgeryInput(task, "st/");
            LocalTime parsedStartTime = LocalTime.parse(startTime, timeFormatter);
            String endDate = splitSurgeryInput(task, "ed/");
            LocalDate parsedEndDate = LocalDate.parse(endDate, dateFormatter);
            String endTime = splitSurgeryInput(task, "et/");
            LocalTime parsedEndTime = LocalTime.parse(endTime, timeFormatter);
            if (parsedStartDate.isAfter(parsedEndDate)) {
                throw new SniffException(" The start date must be before the end date!");
            } else if (parsedStartDate.equals(parsedEndDate) && parsedStartTime.isAfter(parsedEndTime)) {
                throw new SniffException(" The start time must be before the end time!");
            } else if (parsedStartDate.equals(parsedEndDate) && parsedStartTime.equals(parsedEndTime)) {
                throw new SniffException(" The start time cannot be the same as the end time!");
            }
            command = new SurgeryCommand(animalType, animalName, ownerName, contactNumber,
                    parsedStartDate, parsedStartTime, parsedEndDate, parsedEndTime, priority);
        } catch (DateTimeParseException e) {
            throw new SniffException("The date/time description is invalid.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException("The vaccination description is invalid!");
        }
    }

    /**
     * Splits the given surgery input command using the specified splitter and returns the specified description.
     *
     * @param input    the full surgery command from the user.
     * @param splitter the tag of the specified description.
     * @return the actual specified description.
     * @throws SniffException if the surgery command contains other command tags, or if there are repeated
     *                        descriptions, or if the descriptions are invalid format.
     */
    private static String splitSurgeryInput(String input, String splitter) throws SniffException {
        if (input.contains("cd/") || input.contains("ct/") || input.contains("vd/") || input.contains("vt/") ||
                input.contains("v/")) {
            throw new SniffException(" The surgery command is of the wrong format!");
        }
        try {
            String[] firstSplit = input.split(splitter, 2);
            if (firstSplit[1].contains(splitter)) {
                throw new SniffException(" The surgery command cannot contain repeated descriptions!");
            }
            String[] secondSplit = firstSplit[1].split("(at/|an/|on/|cn/|sd/|st/|ed/|et/|p/)", -1);
            return secondSplit[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The " + splitter + " description is invalid!");
        }
    }

    private static void parseFindCommand(String task) throws SniffException {
        try {
            // find a/dog or find t/surgery or find aID/123
            int animalIndex = task.indexOf("a/");
            int typeIndex = task.indexOf("t/");
            int aIDIndex = task.indexOf("uID/");
            int dateIndex = task.indexOf("d/");
            if (animalIndex != -1) {
                String details = task.substring(animalIndex + 2);
                command = new FindCommand("animal", details);
            } else if (typeIndex != -1) {
                String details = task.substring(typeIndex + 2);
                command = new FindCommand("type", details);
            } else if (aIDIndex != -1) {
                String details = task.substring(aIDIndex + 4);
                command = new FindCommand("appointment", details);
            } else if (dateIndex != -1) {
                String details = task.substring(dateIndex + 2);
                command = new FindCommand("date", details);
            } else {
                logger.warning(" NULL command returned to Sniff.run");
                throw new SniffException(" Invalid details provided for find command. Unable to execute find command.");
            }
        } catch (ArrayIndexOutOfBoundsException emptyFind) {
            logger.warning(" Invalid details provided for find command. Unable to execute find command.");
            throw new SniffException(" The find command description cannot be empty!");
        } catch (NumberFormatException e) {
            logger.warning(" Invalid appointment ID format provided. Integer numbers are expected.");
            throw new SniffException(" The user Id to view appointment details must be a number!");
        }
    }

    private static void parseListCommand(String task) throws SniffException {
        if (task.equals("list")) {
            command = new ListCommand();
        } else {
            throw new SniffException(" Not a recognized Sniff command!");
        }
    }

    private static void parseRemoveCommand(String task) throws SniffException {
        try {
            String uid = splitInputBy(task, "uid/");
            assert uid != null;
            command = new RemoveCommand(uid);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.warning("No appointment ID provided for remove command. Unable to execute view command.");
            throw new SniffException(" The remove command description cannot be empty!");
        } catch (NumberFormatException e) {
            logger.warning("Invalid appointment ID format. Integer numbers are expected.");
            throw new SniffException(" The remove command description must be a number!");
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Out of Range appointment ID provided for remove command.");
            throw new SniffException(" The remove command index is invalid!");
        }
    }

    private static void parseMarkCommand(String task) throws SniffException {
        try {
            int markIndex = task.indexOf("uID/");
            String markTask = task.substring(markIndex + 4);
            command = new MarkCommand(markTask);
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException(" The mark description is invalid!");
        }
    }

    private static void parseUnmarkCommand(String task) throws SniffException {
        try {
            int unmarkIndex = task.indexOf("uID/");
            String unmarkTask = task.substring(unmarkIndex + 4);
            command = new UnMarkCommand(unmarkTask);
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException(" The unmark description is invalid!");
        }
    }

    /**
     * Splits the given input command using the specified splitter and returns the specified description.
     *
     * @param input    the full command from the user.
     * @param splitter the tag of the specified description.
     * @return the actual specified description.
     * @throws SniffException if there are repeated descriptions, or if the descriptions are invalid format.
     */
    private static String splitInputBy(String input, String splitter) throws SniffException {
        try {
            String[] firstSplit = input.split(splitter, 2);
            if (firstSplit[1].contains(splitter)) {
                throw new SniffException(" The command cannot contain repeated descriptions!");
            }
            String[] secondSplit = firstSplit[1].split("(at/|an/|on/|cn/|cd/|ct/|vd/|vn/|v/|" +
                    "sd/|st/|ed/|et/|p/)", -1);
            return secondSplit[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The " + splitter + " description is invalid!");
        }
    }

    private static void parseEditCommand(String task) throws SniffException {
        try {
            int uidIndex = task.indexOf("uID/");
            String type = task.substring(uidIndex + 4, uidIndex + 5);
            String uid = task.substring(uidIndex + 4, uidIndex + 14);
            if (type.equals("C")) {
                try {
                    String animalType = splitInputBy(task, "at/");
                    String animalName = splitInputBy(task, "an/");
                    String ownerName = splitInputBy(task, "on/");
                    String contactNumber = splitInputBy(task, "cn/");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String date = splitInputBy(task, "cd/");
                    LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
                    String time = splitInputBy(task, "ct/");
                    LocalTime parsedTime = LocalTime.parse(time, timeFormatter);
                    command = new EditConsultationCommand(uid, animalType, animalName, ownerName, contactNumber,
                            parsedDate, parsedTime);
                } catch (DateTimeParseException e) {
                    throw new SniffException("The date/time description is invalid.");
                } catch (NullPointerException e) {
                    throw new SniffException("The consultation description is invalid!");
                }
            } else if (type.equals("S")) {
                try {
                    String animalType = splitInputBy(task, "at/");
                    String animalName = splitInputBy(task, "an/");
                    String ownerName = splitInputBy(task, "on/");
                    String contactNumber = splitInputBy(task, "cn/");
                    String priority = splitInputBy(task, "p/");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String startDate = splitInputBy(task, "sd/");
                    LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
                    String startTime = splitInputBy(task, "st/");
                    LocalTime parsedStartTime = LocalTime.parse(startTime, timeFormatter);
                    String endDate = splitInputBy(task, "ed/");
                    LocalDate parsedEndDate = LocalDate.parse(endDate, dateFormatter);
                    String endTime = splitInputBy(task, "et/");
                    LocalTime parsedEndTime = LocalTime.parse(endTime, timeFormatter);
                    if (parsedStartDate.isAfter(parsedEndDate)) {
                        throw new SniffException(" The start date must be before the end date!");
                    } else if (parsedStartDate.equals(parsedEndDate) && parsedStartTime.isAfter(parsedEndTime)) {
                        throw new SniffException(" The start time must be before the end time!");
                    } else if (parsedStartDate.equals(parsedEndDate) && parsedStartTime.equals(parsedEndTime)) {
                        throw new SniffException(" The start time cannot be the same as the end time!");
                    }
                    command = new EditSurgeryCommand(uid, animalType, animalName, ownerName, contactNumber,
                            parsedStartDate, parsedStartTime, parsedEndDate, parsedEndTime, priority);
                } catch (DateTimeParseException e) {
                    throw new SniffException("The date/time description is invalid.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new SniffException("The vaccination description is invalid!");
                }
            } else if (type.equals("V")) {
                try {
                    String animalType = splitInputBy(task, "at/");
                    String animalName = splitInputBy(task, "an/");
                    String ownerName = splitInputBy(task, "on/");
                    String contactNumber = splitInputBy(task, "cn/");
                    String vaccine = splitInputBy(task, "v/");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String date = splitInputBy(task, "vd/");
                    LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
                    String time = splitInputBy(task, "vt/");
                    LocalTime parsedTime = LocalTime.parse(time, timeFormatter);
                    command = new EditVaccinationCommand(uid, animalType, animalName, ownerName, contactNumber,
                            vaccine, parsedDate, parsedTime);
                } catch (DateTimeParseException e) {
                    throw new SniffException("The date/time description is invalid.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new SniffException("The vaccination description is invalid!");
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException("The edit uID/ is invalid!");
        }
    }

    private static void parseArchiveCommand() {
        command = new ArchiveCommand();
    }

    private static void parseByeCommand() {
        command = new ExitCommand();
    }


    /**
     * Parses the help command and creates a new HelpCommand object to show the help message to the user.
     */
    private static void parseHelpCommand() {
        command = new HelpCommand();
    }
}



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
            String[] task = userCommand.trim().split(" ", 2);
            if (task[0].equals("consultation")) {
                parseConsultationCommand(task[1]);
            } else if (task[0].equals("vaccination")) {
                parseVaccinationCommand(task[1]);
            } else if (task[0].equals("surgery")) {
                parseSurgeryCommand(task[1]);
            } else if (task[0].equals("find")) {
                parseFindCommand(userCommand.trim());
            } else if (task[0].equals("remove")) {
                parseRemoveCommand(userCommand.trim());
            } else if (task[0].equals("mark")) {
                parseMarkCommand(userCommand.trim());
            } else if (task[0].equals("unmark")) {
                parseUnmarkCommand(userCommand.trim());
            } else if (task[0].equals("edit")) {
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

    private static void parseConsultationCommand(String task) throws SniffException {
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
            command = new ConsultationCommand(animalType, animalName, ownerName, contactNumber, parsedDate, parsedTime);
        } catch (DateTimeParseException e) {
            throw new SniffException("The date/time description is invalid.");
        } catch (NullPointerException e) {
            throw new SniffException("The consultation description is invalid!");
        }
    }

    private static String splitInputBy(String input, String splitter) throws SniffException {
        try {
            String[] firstSplit = input.split(splitter);
            String[] secondSplit = firstSplit[1].split("(at/|an/|on/|cn/|cd/|ct/|vd/|vt/" +
                    "|v/|sd/|st/|ed/|et/|p/)", -1);
            return secondSplit[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SniffException(" The " + splitter + " description is invalid!");
        }
    }

    private static void parseVaccinationCommand(String task) throws SniffException {
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
            command = new VaccinationCommand(animalType, animalName, ownerName, contactNumber,
                    vaccine, parsedDate, parsedTime);
        } catch (DateTimeParseException e) {
            throw new SniffException("The date/time description is invalid.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException("The vaccination description is invalid!");
        }
    }

    private static void parseSurgeryCommand(String task) throws SniffException {
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
            command = new SurgeryCommand(animalType, animalName, ownerName, contactNumber,
                    parsedStartDate, parsedStartTime, parsedEndDate, parsedEndTime, priority);
        } catch (DateTimeParseException e) {
            throw new SniffException("The date/time description is invalid.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException("The vaccination description is invalid!");
        }
    }

    private static void parseFindCommand(String task) throws SniffException {
        try {
            // find a/dog or find t/surgery or find aID/123
            int animalIndex = task.indexOf("a/");
            int typeIndex = task.indexOf("t/");
            int aIDIndex = task.indexOf("aID/");
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
            String uid = task.split(" ", 2)[1];
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

    private static void parseEditCommand(String task) throws SniffException{
        int uidIndex = task.indexOf("uID/");
        String uid = task.substring(uidIndex + 4, uidIndex + 14);
        String type = task.substring(uidIndex + 4, uidIndex + 5);
        if (type.equals("C")){
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
                command = new EditConsultationCommand(uid, animalType, animalName, ownerName, contactNumber, parsedDate,
                       parsedTime);
            } catch (DateTimeParseException e) {
                throw new SniffException("The date/time description is invalid.");
            } catch (NullPointerException e) {
                throw new SniffException("The consultation description is invalid!");
            }
        }
        else if (type.equals("S")){
            try{
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
                command = new EditSurgeryCommand(uid,animalType, animalName, ownerName, contactNumber,
                        parsedStartDate, parsedStartTime, parsedEndDate, parsedEndTime, priority);
            } catch (DateTimeParseException e) {
                throw new SniffException("The date/time description is invalid.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new SniffException("The vaccination description is invalid!");
            }
        }
        else if (type == "V"){
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
                command = new EditVaccinationCommand(uid,animalType, animalName, ownerName, contactNumber,
                        vaccine, parsedDate, parsedTime);
            } catch (DateTimeParseException e) {
                throw new SniffException("The date/time description is invalid.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new SniffException("The vaccination description is invalid!");
            }
        }
    }

    private static void parseArchiveCommand() {
        command = new ArchiveCommand();
    }

    private static void parseByeCommand() {
        command = new ExitCommand();
    }

    private static void parseHelpCommand() {
        command = new HelpCommand();
    }
}

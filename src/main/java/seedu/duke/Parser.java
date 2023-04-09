package seedu.duke;

import seedu.duke.storage.JsonNusModuleLoader;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Parser {
    public static final Map<Integer, LocalDate> SEMESTER_START_DATES = Map.of(1, LocalDate.of(2022, 8, 8), 2,
            LocalDate.of(2023, 1, 9), 3, LocalDate.of(2023, 5, 8), 4, LocalDate.of(2023, 6, 19));

    public static final Map<Integer, LocalDate> SEMESTER_END_DATES = Map.of(1, LocalDate.of(2022, 12, 3), 2,
            LocalDate.of(2023, 5, 6), 3, LocalDate.of(2023, 6, 17), 4, LocalDate.of(2023, 7, 29));

    
    private static final int OFFSET = 1;
    private static final JsonNusModuleLoader converter = new JsonNusModuleLoader();

    // command types
    private static final String ADD_T = "add";
    private static final String DELETE_T = "delete";
    private static final String LIST_T = "list";
    private static final String EDIT_T = "edit";

    // flags
    private static final String EVENT_NAME_F = "e";
    private static final String MODULE_CODE_F = "m";
    private static final String CLASS_NUMBER_F = "n";
    private static final String LESSON_TYPE_F = "l";
    private static final String START_TIME_F = "st";
    private static final String START_DATE_F = "sd";
    private static final String END_TIME_F = "et";
    private static final String END_DATE_F = "ed";
    private static final String VENUE_F = "v";
    private static final String RECURRING_TIME_F = "r";
    private static final String INDEX_OF_EVENT_F = "i";

    // exceptions
    private static final String DUPLICATE_FLAGS_E = "Cannot have duplicate flags a command!";
    private static final String INVALID_FLAG_E = "Please input a valid flag!";
    private static final String NO_FLAG_E = "Need a flag to specify your action!";
    private static final String TOO_MANY_FLAGS_E = "Too many flags, or negative index entered";
    private static final String NO_EVENT_DESC_AND_START_DAY_E = 
        "Event description and start day of your event are strictly required!";
    private static final String NO_START_DAY_E = "Empty starting date/time detected! Please add starting date.";
    private static final String UNDEFINED_FLAG_E = "Undefined flag!";
    private static final String INCORRECT_CMD_FORMAT_E = 
        "Event name/index, start time/date or module info is missing!\n" +
        "Please use correct command format!";
    private static final String INVALID_EVENT_INDEX_E = "Event index is in valid!";
    private static final String EVENT_INDEX_OUT_OF_BOUND_E = "Event index out of bound!";
    private static final String WEEK_NUMBER_NOT_POSITIVE_E = "Please use positive value for your week number!";

    private final Ui ui;

    public Parser() {
        ui = new Ui();
    }

    public static void parseCommand(String userInput, EventList eventList) {
        try {
            userInput = userInput.trim();
            String command = "";
            String remainder = "";
            if (!userInput.contains(" ")) {
                command = userInput;
            } else {
                command = userInput.substring(0, userInput.indexOf(" "));
                remainder = userInput.substring(userInput.indexOf(" ") + 1);
            }

            switch (command.toLowerCase()) {
            case ADD_T:
                parseAddCommand(remainder, eventList);
                break;
            case DELETE_T:
                parseDeleteCommand(remainder, eventList);
                break;
            case LIST_T:
                parseListCommand(remainder, eventList);
                break;
            case EDIT_T:
                parseEditCommand(remainder, eventList);
                break;
            default:
                Ui.addErrorMsg();
                break;
            }
        } catch (Exception e) {
            Ui.printErrorMsg(e.getMessage());
        }
    }

    private static void parseListCommand(String remainder, EventList eventList) throws NPExceptions {
        String[] details = remainder.split("-");

        if (details.length <= 1) {
            Ui.listTask(eventList.getFullList());
            return;
        }

        String information = details[1].substring(2).trim();
        int weekNumber = Integer.parseInt(information);

        // Exception: week number is not a positive value
        if(weekNumber <= 0) {
            throw new NPExceptions(WEEK_NUMBER_NOT_POSITIVE_E);
        }

        Ui.printScheduleTable(eventList.getFullList(), weekNumber);

    }

    private static void parseDeleteCommand(String remainder, EventList eventList) throws NPExceptions {
        String[] details = remainder.split("-");

        if (details.length <= 1) {
            throw new NPExceptions(NO_FLAG_E);
        }
        if (details.length > 2){
            throw new NPExceptions(TOO_MANY_FLAGS_E);
        }

        String information = details[1].substring(0, 1).trim();
        if (information.equals("s")) {
            int index = Integer.parseInt(details[1].substring(1).trim()) - OFFSET;
            String deletedTask = eventList.getDetails(index);
            eventList.deleteThisTask(index);
            // TODO: Show successful add on UI. (For all cases)
            Duke.LOGGER.log(Level.INFO, "User deleted event in event list.");
            Ui.deleteSuccessMsg(deletedTask);
        } else if (details[1].length()<3){
            throw new NPExceptions(INVALID_FLAG_E);
        }else if (details[1].substring(0, 3).trim().equals("all")) {
            eventList.deleteAll();
            Duke.LOGGER.log(Level.INFO, "User deleted all events in event list.");
        } else {
            throw new NPExceptions(INVALID_FLAG_E);
        }

    }

    private static boolean extractFields(boolean[] duplicity, String[] information, String[] details,
            boolean isModuleFlag) throws NPExceptions {

        for (int i = 1; i < details.length; i++) {
            String field = details[i].substring(0, 2).trim();
            String change = details[i].substring(2).trim();
            switch (field) {
            case MODULE_CODE_F:
                isModuleFlag = true;
                if (!duplicity[0]) {
                    information[0] = change;
                    duplicity[0] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            case EVENT_NAME_F:
                if (!duplicity[0]) {
                    information[0] = change;
                    duplicity[0] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            case CLASS_NUMBER_F:
            case START_TIME_F:
                if (!duplicity[1]) {
                    information[1] = change;
                    duplicity[1] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            case START_DATE_F:
            case LESSON_TYPE_F:
                if (!duplicity[2]) {
                    information[2] = change;
                    duplicity[2] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            case END_TIME_F:
                if (!duplicity[3]) {
                    information[3] = change;
                    duplicity[3] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            case END_DATE_F:
                if (!duplicity[4]) {
                    information[4] = change;
                    duplicity[4] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            case RECURRING_TIME_F:
                if (!duplicity[5]) {
                    information[5] = change;
                    duplicity[5] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            case VENUE_F: // venue/location of the event
                if (!duplicity[6]) {
                    information[6] = change;
                    duplicity[6] = true;
                } else {
                    throw new NPExceptions(DUPLICATE_FLAGS_E);
                }
                break;
            default:
                break;
            }
        }

        return isModuleFlag;
    }

    private static void parseAddCommand(String remainder, EventList eventList) throws NPExceptions {
        // Method is still broken, someone will have to fix it fully later on when handling exceptions
        // Note no "-" anywhere else.
        String[] details = remainder.split("-");

        boolean isModuleFlag = false;

        if (details.length <= 1) {
            throw new NPExceptions(NO_EVENT_DESC_AND_START_DAY_E);
        }
        boolean[] duplicity = new boolean[7]; // to detect duplicate flags in command
        Arrays.fill(duplicity, false);
        String[] information = new String[7];
        Arrays.fill(information, "");

        isModuleFlag =  extractFields(duplicity, information, details, isModuleFlag);

        addFormatChecker(information);

        // if body executed when user adds a module. Code inside "else" is same as before.
        if (isModuleFlag) {
            // count to store the number of classes added into eventList.
            int count = 0;

            // fetching information
            String moduleCode = information[0].toUpperCase();
            String classNumber = information[1];
            String lectureType = information[2];

            // loading modules. Need to update when singleton design is utilized.
            HashMap<String, NusModule> nusmods = converter.loadModules();
            Duke.LOGGER.log(Level.INFO, "loadModules() called");
            // Fetch NusModule from module code
            NusModule nusModule = nusmods.get(moduleCode);
            if (nusModule == null) {
                Duke.LOGGER.log(Level.INFO, "User selected module that does not exist.");
                throw new NPExceptions("Module " + moduleCode + " does not exist!");
            }

            // Fetch lessons from module
            List<Lesson> lessons =
                    nusModule.getLesson(UserUtility.getUser().getSemester(), lectureType, classNumber);
            if (lessons == null || lessons.isEmpty()) {
                Duke.LOGGER.log(Level.INFO, "User selected module that is unavailable for semester.");
                Ui.printErrorMsg("Selected module is not available for semester "
                        + UserUtility.getUser().getSemester());
                return;
            }

            // Create event for each day of module
            try{
                for (Lesson lesson : lessons) {
                    String venue = lesson.getVenue();
                    for (Integer week : lesson.getWeeks()) {
                    
                        // Method to get date on the lesson's day in a given week number.
                        if (week >= 7) {
                            week++;
                        }

                        String startDate =
                                findDateOfWeek(UserUtility.getUser().getSemester(), week, lesson.getDay());

                        // Converting time to HH:mm format.
                        StringBuilder sb = new StringBuilder(lesson.getStartTime());
                        String startTime = sb.insert(2, ':').toString();
                        sb = new StringBuilder(lesson.getEndTime());
                        String endTime = sb.insert(2, ':').toString();

                        int size = eventList.getSize();
                        eventList.addEvent(nusModule.getModuleCode(), startTime, startDate, endTime,
                                startDate);
                        eventList.reviseLocation(size++, venue);
                        count++;
                    }
                }
                Duke.LOGGER.log(Level.INFO, "User added module to event list.");
                Ui.addSuccessMsg("Added " + count + " classes of Module: " + moduleCode);
            } catch (NPExceptions e) {
                System.out.println(e.getMessage());
            }

        } else {
            String eventName = information[0];
            String startTime = information[1];
            String startDate = information[2];

            if (!information[3].equals("")) {

                String endTime = information[3];
                String endDate = information[4].equals("") ? startDate : information[4];
                if (information[5].equals("")) {
                    eventList.addEvent(eventName, startTime, startDate, endTime, endDate);
                } else {
                    eventList.addEvent(eventName, startTime, startDate, endTime, endDate, information[5]);
                }

            } else {
                if (!information[4].equals("")) {
                    Ui.printEDOmitted();
                }
                if (information[5].equals("")) {
                    eventList.addEvent(eventName, startTime, startDate);
                } else {
                    eventList.addEvent(eventName, startTime, startDate, information[5]);
                }
            }

            // add location(venue)
            int eventNum = eventList.getSize() - 1;
            if (duplicity[6] == true) {
                eventList.reviseLocation(eventNum, information[6]);
            }
            Ui.addSuccessMsg(eventList.getLastTaskDescription());
        }
    }

    private static String findDateOfWeek(int semester, Integer weekNumber, String dayOfWeek) {
        // Specify the start date of the semester
        LocalDate semesterStartDate = SEMESTER_START_DATES.get(semester);

        // Calculate the date for the specified day of the week in the specified week
        LocalDate weekStartDate = semesterStartDate.plusWeeks(weekNumber - 1);
        LocalDate desiredDate =
                weekStartDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(dayOfWeek.toUpperCase())));

        // Output the result
        return desiredDate.toString().replace('-', '/');
    }

    private static void parseEditCommand(String remainder, EventList eventList) throws NPExceptions {
        String[] details = remainder.split("-");
        String[] information = new String[6];

        if (!(details[1].substring(0, 2).trim().equalsIgnoreCase(INDEX_OF_EVENT_F)
                || details[1].substring(0, 2).trim().equalsIgnoreCase(EVENT_NAME_F))) {
            throw new NPExceptions(UNDEFINED_FLAG_E);
        }

        Arrays.fill(information, "");
        for (int i = 1; i < details.length; i++) {
            String field = details[i].substring(0, 2).trim();
            String change = details[i].substring(2).trim();
            switch (field) {
            case INDEX_OF_EVENT_F:
                information[0] = change;
                break;
            case START_TIME_F:
                information[1] = change;
                break;
            case START_DATE_F:
                information[2] = change;
                break;
            case END_TIME_F:
                information[3] = change;
                break;
            case END_DATE_F:
                information[4] = change;
                break;
            case VENUE_F:
                information[5] = change;
                break;
            default:
                break;
            }
        }

        addFormatChecker(information);

        if (information[2].equals("") || information[1].equals("")) { // Starting date field MUST NOT be empty.
            throw new NPExceptions(NO_START_DAY_E);
        }

        if (information[0].equals("")) {
            information[0] = details[1].substring(2).trim();
            reviseTimeInfoUsingName(information, eventList);
        } else {
            reviseTimeInfoUsingIndex(information, eventList);
        }
    }

    private static void addFormatChecker(String[] information) throws NPExceptions {
        boolean isValidFormat = !information[0].equalsIgnoreCase("") && !information[2].equalsIgnoreCase("")
                && !information[1].equalsIgnoreCase("");

        if (!isValidFormat) {
            throw new NPExceptions(INCORRECT_CMD_FORMAT_E);
        }
    }

    private static void reviseTimeInfoUsingIndex(String[] information, EventList eventList)
            throws NPExceptions {
        int eventIndex = -1;
        try {
            eventIndex = Integer.parseInt(information[0]);
            eventIndex--;
        } catch (NumberFormatException e) {
            throw new NPExceptions(INVALID_EVENT_INDEX_E);
        }

        if (eventIndex < 0 || eventIndex >= eventList.getSize()) {
            throw new NPExceptions(EVENT_INDEX_OUT_OF_BOUND_E);
        }

        if (!information[4].equals("")) {
            eventList.reviseTimeInfo(eventIndex, information[1], information[2], information[3],
                    information[4]);
        } else {
            eventList.reviseTimeInfo(eventIndex, information[1], information[2]);
        }

        // revise location info
        if (!information[5].equals("")) {
            eventList.reviseLocation(eventIndex, information[5]);
        }

        Duke.LOGGER.log(Level.INFO, "User edited time of event.");
        Ui.editSuccessMsg(eventList.getDescription(eventIndex), eventList.getTime(eventIndex));
    }

    private static void reviseTimeInfoUsingName(String[] information, EventList eventList)
            throws NPExceptions {
        if (!information[4].equals("")) {
            eventList.reviseTimeInfo(information[0], information[1], information[2], information[3],
                    information[4]);
            
        } else {
            eventList.reviseTimeInfo(information[0], information[1], information[2]);
        }

        int eventIndex = eventList.searchTaskIndex(information[0]);

        // revise location info
        if (!information[5].equals("")) {
            eventList.reviseLocation(eventIndex, information[5]);
        }
        Duke.LOGGER.log(Level.INFO, "User edited time of event.");
        Ui.editSuccessMsg(eventList.getDescription(eventIndex), eventList.getTime(eventIndex));
    }
}

package seedu.duke;

public class Parser {

    public static void parseCommand(String userInput, EventList eventList) {
        userInput = userInput.trim();
        String command = userInput.substring(0, userInput.indexOf(" "));
        String remainder = userInput.substring(userInput.indexOf(" ") + 1);
        switch (command) {
        case "add":
            parseAddCommand(remainder, eventList);
            break;
        case "delete":
            parseDeleteCommand(remainder, eventList);
            break;
        case "list":
            parseListCommand(eventList);
            break;
        case "edit":
            // parseEditCommand();
        default:
            Ui.addErrorMsg();
            break;
        }
    }

    private static void parseListCommand(EventList eventList) {
        Ui.listTask(eventList.fullList());
    }

    private static void parseDeleteCommand(String remainder, EventList eventList) {
        eventList.deleteThisTask(Integer.parseInt(remainder));

        //TODO: Show successful add on UI. (For all cases)
        Ui.deleteSuccessMsg();
    }

    private static void parseAddCommand(String remainder, EventList eventList) {
        // Note no "-" anywhere else.
        String[] details = remainder.split("-");
        String eventName = details[1].substring(2).trim();
        String startTime = details[2].substring(3).trim();
        String startDate = details[3].substring(3).trim();
        String endTime = details[4].substring(2).trim();

        if (details.length == 6) {
            String endDate = details[5].substring(2);
            eventList.addEvent(eventName, startTime, startDate, endTime, endDate);
        } else {
            eventList.addEvent(eventName, startTime, startDate, endTime);
        }

        //TODO: Show successful add on UI. (For all cases)
        Ui.addSuccessMsg();
    }
}

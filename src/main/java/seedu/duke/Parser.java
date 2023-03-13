package seedu.duke;

import java.util.Arrays;

public class Parser {

    private static final int OFFSET = 1;
    private final Ui ui;

    public Parser(){
        ui = new Ui();
    }

    public static void parseCommand(String userInput, EventList eventList) {
        userInput = userInput.trim();
        String command = "";
        String remainder = "";
        if (userInput.toLowerCase().equals("list")) {
            command = "list";
        }
        else {
            command = userInput.substring(0, userInput.indexOf(" "));
            remainder = userInput.substring(userInput.indexOf(" ") + 1);

        }
        switch (command.toLowerCase()) {
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
            parseEditCommand(remainder, eventList);
            break;
        default:
            Ui.addErrorMsg();
            break;
        }
    }

    private static void parseListCommand(EventList eventList) {
        Ui.listTask(eventList.fullList());
    }

    private static void parseDeleteCommand(String remainder, EventList eventList) {
        eventList.deleteThisTask(Integer.parseInt(remainder) - OFFSET);

        //TODO: Show successful add on UI. (For all cases)
        Ui.deleteSuccessMsg();
    }

    private static void parseAddCommand(String remainder, EventList eventList) {
        // Method is still broken, someone will have to fix it fully later on when handling exceptions
        // Note no "-" anywhere else.
        String[] details = remainder.split("-");
        String eventName = details[1].substring(2).trim();
        String startTime = details[2].substring(2).trim();
        String startDate = details[3].substring(2).trim();
        String endTime = details[4].substring(2).trim();
        boolean isValidFormat = (details[1].substring(0,1).equalsIgnoreCase("e")||
                details[2].substring(0,2).equalsIgnoreCase("st")||
                details[3].substring(0,2).equalsIgnoreCase("sd")||
                details[4].substring(0,2).equalsIgnoreCase("et"));
        //TODO: refactor isValidFormat into formatChecker method that raises exception.
        if (isValidFormat) {
            if (details.length == 6) {
                String endDate = details[5].substring(2).trim();
                eventList.addEvent(eventName, startTime, startDate, endTime, endDate);
            } else {
                eventList.addEvent(eventName, startTime, startDate, endTime);
            }
            Ui.addSuccessMsg();
        } else{
            Ui.addErrorMsg(); //placeholder
        }
        //TODO: Show successful add on UI. (For all cases)
    }
    private static void parseEditCommand(String remainder, EventList eventList){
        String[] details = remainder.split("-");
        String[] information = new String[4];
        Arrays.fill(information, "");
        for (int i = 2; i < details.length; i++){
            String field = details[i].substring(0,2);
            String change = details[i].substring(2).trim();
            switch(field) {
            case ("st"):
                information[0] = change;
                break;
            case ("sd"):
                information[1] = change;
                break;
            case ("et"):
                information[2] = change;
                break;
            case ("ed"):
                information[3] = change;
                break;
            default:
                break;
            }
        }
        if (information[1].equals("")){  //Starting date field MUST NOT be empty.
            //TODO: add exception for empty starting date
            Ui.addErrorMsg();
        }
        else {
            //TODO: link to taskList edit event
            System.out.println(details[1].substring(2) + "," + information[0] + "," + information[1] + "," 
                    + information[2] + "," + information[3]);
        }
    }
}

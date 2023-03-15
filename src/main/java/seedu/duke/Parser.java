package seedu.duke;

import java.util.Arrays;

public class Parser {

    private static final int OFFSET = 1;
    private final Ui ui;

    public Parser(){
        ui = new Ui();
    }

    public static void parseCommand(String userInput, EventList eventList) {
        try {
            userInput = userInput.trim();
            String command = "";
            String remainder = "";
            if (!userInput.contains(" ")){
                command = userInput;
            } else {
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
        } catch(Exception e) {
            Ui.printErrorMsg(e.getMessage());
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

    private static void parseAddCommand(String remainder, EventList eventList) throws NPExceptions {
        // Method is still broken, someone will have to fix it fully later on when handling exceptions
        // Note no "-" anywhere else.
        String[] details = remainder.split("-");
        if(details.length <= 1) {
            throw new NPExceptions("Event description and start day of your event are strictly required!");
        }

        String[] information = new String[5];
        Arrays.fill(information, "");
        for (int i = 1; i < details.length; i++){
            String field = details[i].substring(0,2).trim();
            String change = details[i].substring(2).trim();
            switch(field) {
            case ("e"):
                information[0] = change;
                break;
            case ("st"):
                information[1] = change;
                break;
            case ("sd"):
                information[2] = change;
                break;
            case ("et"):
                information[3] = change;
                break;
            case ("ed"):
                information[4] = change;
                break;
            default:
                break;
            }
        }
        addFormatChecker(information);

        String eventName = information[0];
        String startTime = information[1];
        String startDate = information[2];

        if (details.length == 6) {
            String endTime = information[3];
            String endDate = information[4];
            eventList.addEvent(eventName, startTime, startDate, endTime, endDate);
        } else {
            eventList.addEvent(eventName, startTime, startDate);
        }

        Ui.addSuccessMsg();
    }
    private static void parseEditCommand(String remainder, EventList eventList) throws NPExceptions{
        String[] details = remainder.split("-");
        String[] information = new String[4];
        Arrays.fill(information, "");
        for (int i = 1; i < details.length; i++){
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
            throw new NPExceptions("Empty starting date detected! Please add starting date.");
        } else {
            int eventIndex = -1;

            details[0] = details[0].trim();
            try{
                eventIndex = Integer.parseInt(details[0]);
                eventIndex--;
            } catch(NumberFormatException e){
                eventIndex = eventList.searchTaskIndex(details[0]);
            }

            if(eventIndex < 0 || eventIndex >= eventList.getSize()){
                throw new NPExceptions("Event index out of bound!");
            }

            if(!information[3].equals("")) {
                eventList.reviseTimeInfo(eventIndex, information[0], information[1], information[2], information[3]);
            } else {
                eventList.reviseTimeInfo(eventIndex, information[0], information[1]);
            }
        }
    }
    private static void addFormatChecker(String[] information) throws NPExceptions {
        boolean isValidFormat = !information[0].equalsIgnoreCase("") &&
                                !information[1].equalsIgnoreCase("") &&
                                !information[2].equalsIgnoreCase("") ;
        
        if (!isValidFormat) {
            throw new NPExceptions("Please use correct command format!");
        }
    }
}

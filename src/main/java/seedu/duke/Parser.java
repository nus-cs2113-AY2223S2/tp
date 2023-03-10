// package seedu.duke;

// public class Parser {


//     public static void parseCommand(String userInput, EventList eventList){
//         userInput = userinput.trim();
//         String command = userInput.substring(0, userInput.indexOf(" "));
//         String remainder = userInput.substring(userInput.indexOf(" ")+ 1);
//         switch (command) {
//         case "add":
//             parseAddCommand(remainder);
//             break;
//         case "delete":
//             parseDeleteCommand(remainder);
//             break;
//         case "list":

//             break;
//         case "edit":

//             break;
//         default:

//             break;
//         }
//     }

//     private static void parseAddCommand(String remainder) {
//         //Note no - anywhere else.
//         String[] details = remainder.split("-");
//         String eventName = details[0];
//         String startTime = details[1];
//         String startDate = details[2];
//         String endTime = details[3];
//         if (details.length == 5){
//             String endDate = details[4];
//             EventList.add(eventName, startTime, startDate, endTime, endDate);
//         }
//         else{
//             EventList.add(eventName, startTime, startDate, endTime);
//         }
//         //TODO: Show successful add on UI. (For all cases)
//     }
// }

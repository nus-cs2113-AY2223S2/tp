package manager;

import common.Messages;
import entity.Meeting;
import ui.TextUi;

import java.util.ArrayList;

public class MeetingManager {
    private static ArrayList<Meeting> meetings = new ArrayList<Meeting>();

    public static void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public static String printMeetings() {
        String meetingList = "";
        for (Meeting meeting : meetings) {
            meetingList += meeting.getIssue() + " at " + meeting.getTime() + System.lineSeparator();
        }
        return meetingList;
    }

    public static void deleteMeeting(int index) {
        if(index<meetings.size()){
            System.out.println("Meeting "+meetings.get(index).getIssue()+" deleted!");
            meetings.remove(index);
        } else{
            System.out.println(Messages.ERROR_MEETING_INVALID_INDEX);
        }

    }
    public static void findMeeting(String keyword, TextUi ui){
        ArrayList<Meeting> meetingFound=new ArrayList<>();
        for(Meeting m:meetings){
            if( m.getIssue().contains(keyword)){
                meetingFound.add(m);
            }
        }
        if(meetingFound.isEmpty()){
            ui.printMessage("There's no such meeting in the meeting list!");
        }
        else{
            ui.printMessage("Here's the matching meeting list:");
            for(Meeting n:meetingFound){
                ui.printMessage(n.getIssue());
            }
        }

    }
}

package manager;

import common.Messages;
import entity.Meeting;
import ui.TextUi;
import utils.MeetingStorage;

import java.io.IOException;
import java.util.ArrayList;

public class MeetingManager {
    private static ArrayList<Meeting> meetings = new ArrayList<>();

    public MeetingManager(ArrayList<Meeting> meetings) {
        MeetingManager.meetings = meetings;
    }

    public static void addMeeting(Meeting meeting, TextUi ui) {
        meetings.add(meeting);
        ui.printMessage(meeting.getIssue() + " at " + meeting.getTime());
        try {
            MeetingStorage meetingStorage = new MeetingStorage();
            meetingStorage.writeToDeadlineFile(meetings);
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_STORAGE_INVALID_WRITE_LINE);
        }
    }

    public static String printMeetings() {
        String meetingList = "";
        int index=1;
        for (Meeting meeting : meetings) {
            meetingList += index+". "+meeting.getIssue() + " at: " + meeting.getTime() + System.lineSeparator();
            index++;
        }
        return meetingList;
    }

    public static void deleteMeeting(int index, TextUi ui) {
        if(index<0){
            ui.printMessage(Messages.ERROR_MEETING_INVALID_INDEX);
            return;
        }
        if (index < meetings.size()) {
            ui.printMessage("Meeting " + meetings.get(index).getIssue() + " deleted!");
            meetings.remove(index);
        } else {
            System.out.println(Messages.ERROR_MEETING_INVALID_INDEX);
            return;
        }

        try {
            MeetingStorage meetingStorage = new MeetingStorage();
            meetingStorage.writeToDeadlineFile(meetings);
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_STORAGE_DELETE_FAILED);
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
            ui.printMessage(Messages.MESSAGE_MEETING_NOT_FOUND);
        } else{
            ui.printMessage(Messages.MESSAGE_MEETING_FOUND);
            for(Meeting n:meetingFound){
                ui.printMessage(n.getIssue());
            }
        }

    }
}

package manager;

import common.Messages;
import entity.Meeting;
import ui.TextUi;
import utils.MeetingStorage;

import java.io.IOException;
import java.util.ArrayList;

public class MeetingManager {
    private static ArrayList<Meeting> meetings = new ArrayList<>();
    /**
     * Creates MeetingList with input list.
     *
     * @param meetings is the list of meetings.
     */
    public MeetingManager(ArrayList<Meeting> meetings) {
        MeetingManager.meetings = meetings;
    }
    /**
     * Adds a meeting item to the meeting list.
     *
     * @param meeting is the meeting item to be added.
     * @param ui       manages user output.
     */
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
    /**
     * Print the meeting list.
     *
     * @return a String of all the meetings
     */
    public static String viewMeetings() {
        String meetingList = "";
        int index=1;
        for (Meeting meeting : meetings) {
            meetingList += index+". "+meeting.getIssue() + " at: " + meeting.getTime() + System.lineSeparator();
            index++;
        }
        return meetingList;
    }
    /**
     * Deletes a meeting from the meeting list.
     *
     * @param index is the index of meeting to be deleted.
     * @param ui    manages user output.
     */
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
    /**
     * Prints all the meetings that contain the keyword.
     *
     * @param keyword is the keyword to be searched.
     * @param ui manages user output.
     */
    public static void findMeeting(String keyword, TextUi ui){
        ArrayList<Meeting> meetingsFound=new ArrayList<>();
        for(Meeting m:meetings){
            if( m.getIssue().contains(keyword)){
                meetingsFound.add(m);
            }
        }
        if(meetingsFound.isEmpty()){
            ui.printMessage(Messages.MESSAGE_MEETING_NOT_FOUND);
        } else{
            ui.printMessage(Messages.MESSAGE_MEETING_FOUND);
            int index=1;
            for(Meeting n:meetingsFound){
                ui.printMessage(index+". "+n.getIssue()+" at "+n.getTime());
                index++;
            }
        }

    }
}

package manager;

import entity.Meeting;

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

    public static boolean deleteMeeting(String issue) {
        boolean hasMeeting = false;
        for (int i = 0; i < meetings.size(); i++) {
            Meeting m = meetings.get(i);
            if (m.getIssue().equals(issue)) {
                meetings.remove(i);
                hasMeeting = true;
            }
        }
        return hasMeeting;
    }

}

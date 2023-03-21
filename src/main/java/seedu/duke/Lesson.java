package seedu.duke;

import java.util.ArrayList;

public class Lesson {
    public String classNumber;
    public String startTime;
    public String endTime;
    public ArrayList<Integer> weeks; // + days * (7*(1-each integer in weeks))
    public String venue;
    public String lessonType;
    public String day;
    //discarding size.


    public Lesson(String classNumber, String startTime, String endTime, ArrayList<Integer> weeks, String venue,
                  String lessonType, String day) {
        this.classNumber = classNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weeks = weeks;
        this.venue = venue;
        this.lessonType = lessonType;
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public ArrayList<Integer> getWeeks() {
        return weeks;
    }

    public String getVenue() {
        return venue;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getDay() {
        return day;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public String toString(){
        return getLessonType() + " " + getClassNumber() + ": " + getDay() + " " + getStartTime() + " to " +
                getEndTime() + " at " + getVenue() + " on weeks " + getWeeks().toString();
    }
}

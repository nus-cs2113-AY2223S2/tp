package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    public String classNumber;
    public String startTime;
    public String endTime;
    public ArrayList<Integer> weeks;
    public String venue;
    public String lessonType;
    //discarding size.


    public Lesson(String classNumber, String startTime, String endTime, ArrayList<Integer> weeks, String venue,
                  String lessonType) {
        this.classNumber = classNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weeks = weeks;
        this.venue = venue;
        this.lessonType = lessonType;
    }
}

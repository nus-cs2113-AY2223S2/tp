package seedu.duke.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import seedu.duke.Lesson;
import seedu.duke.NusModule;

import com.google.gson.stream.JsonWriter;
import seedu.duke.SemData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static seedu.duke.Parser.SEMESTER_START_DATES;
import static seedu.duke.Parser.SEMESTER_END_DATES;
/**
 * A deserializer to deserialize NusMods data from its Json format
 */
public class ModuleAdapter extends TypeAdapter<HashMap<String, NusModule>> {

    /**
     * Dummy function to extend TypeAdapter
     * @param out JsonWriter
     * @param value the Java object to write. May be null.
     * @throws IOException
     */
    @Override
    public void write(JsonWriter out, HashMap<String, NusModule> value) throws IOException {
        //No need to write anything to file. Method should be empty!
    }

    /**
     * Reads nusmods.json file and parses it into a hash map for use by other components in the application.
     * @param reader JsonReader to parse the JSON file.
     * @return Hash Map containing all modules in NUS and with its module code as the key.
     * @throws IOException if there is any issue in parsing the file.
     */
    @Override
    public HashMap<String, NusModule> read(JsonReader reader) throws IOException {
        HashMap<String, NusModule> nusModuleMap = new HashMap<>();
        reader.beginArray();
        while(reader.hasNext()){
            NusModule module = readModule(reader);
            nusModuleMap.put(module.getModuleCode(), module);
        }
        reader.endArray();
        return nusModuleMap;
    }

    /**
     * Deserializes a module object from Json to a java Module class.
     * @param reader Json Reader to parse the file
     * @return Module class containing module information
     * @throws IOException if there is any issue while parsing the file
     */
    public NusModule readModule(JsonReader reader) throws IOException{
        String description = null;
        String title = null;
        String faculty = null;
        String moduleCredit = null;
        String moduleCode = null;
        HashMap<Integer, SemData> semesterData = new HashMap<>();
        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            switch(name) {
            case "description":
                description = reader.nextString();
                break;
            case "title":
                title = reader.nextString();
                break;
            case "faculty":
                faculty = reader.nextString();
                break;
            case "moduleCredit":
                moduleCredit = reader.nextString();
                break;
            case "moduleCode":
                moduleCode = reader.nextString();
                break;
            case "semesterData":
                semesterData = readListSemData(reader);
                break;
            default:
                reader.skipValue();

                break;
            }
        }
        reader.endObject();
        return new NusModule(description, title, faculty, moduleCredit, moduleCode, semesterData);
    }

    private HashMap<Integer, SemData> readListSemData(JsonReader reader) throws IOException{
        HashMap<Integer, SemData> semDataMap = new HashMap<>();
        reader.beginArray();
        while(reader.hasNext()){
            SemData semester = readSemData(reader);
            semDataMap.put(semester.getSemester(), semester);
        }
        reader.endArray();
        return semDataMap;
    }


    private SemData readSemData(JsonReader reader) throws IOException{
        //TODO: write semdata into thing
        int semester = 0;
        List<Lesson> timetable = null;
        String examDate = null;
        int examDuration = 0;
        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            switch(name){
            case "semester":
                semester = reader.nextInt();
                break;
            case "timetable":
                timetable = readTimetable(reader, semester);
                break;
            case "examDate":
                examDate = reader.nextString();
                break;
            case "examDuration":
                examDuration = reader.nextInt();
                break;
            default:
                reader.skipValue();
                break;
            }
        }
        reader.endObject();
        if (examDate == null && examDuration == 0){
            return new SemData(semester, timetable, false);
        } else{
            return new SemData(semester, timetable, examDate, examDuration);
        }
    }

    private List<Lesson> readTimetable(JsonReader reader, int semester) throws IOException{
        List<Lesson> timetable = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()){
            Lesson lesson = readLesson(reader, semester);
            timetable.add(lesson);
        }
        reader.endArray();
        return timetable;
    }

    private Lesson readLesson(JsonReader reader, int semester) throws IOException{
        String classNumber = null;
        String startTime = null;
        String endTime = null;
        ArrayList<Integer> weeks = null;
        String venue = null;
        String lessonType = null;
        String day = null;
        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            switch (name){
            case "classNo":
                classNumber = reader.nextString();
                break;
            case "startTime":
                startTime = reader.nextString();
                break;
            case "endTime":
                endTime = reader.nextString();
                break;
            case "weeks":
                weeks = readWeeks(reader, semester);
                break;
            case "venue":
                venue = reader.nextString();
                break;
            case "lessonType":
                lessonType = reader.nextString();
                switch(lessonType){
                case("Lecture"):
                    lessonType = "LEC";
                    break;
                case("Sectional Teaching"):
                    lessonType = "SEC";
                    break;
                case("Design Lecture"):
                    lessonType = "DLEC";
                    break;
                case("Recitation"):
                    lessonType = "REC";
                    break;
                case("Packaged Lecture"):
                    lessonType = "PLEC";
                    break;
                case("Seminar-Style Module Class"):
                    lessonType = "SEM";
                    break;
                case("Tutorial"):
                    lessonType = "TUT";
                    break;
                case("Tutorial Type 2"):
                    lessonType = "TUT2";
                    break;
                case("Packaged Tutorial"):
                    lessonType = "PTUT";
                    break;
                case("Laboratory"):
                    lessonType = "LAB";
                    break;
                default:
                    break;
                }
                break;
            case "day":
                day = reader.nextString();
                break;
            default:
                reader.skipValue();
                break;
            }
        }
        reader.endObject();
        return new Lesson(classNumber, startTime, endTime, weeks, venue, lessonType, day);
    }

    private ArrayList<Integer> readWeeks(JsonReader reader, int semester) throws IOException{
        ArrayList<Integer> weeks = new ArrayList<>();
        if (reader.peek() == JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
            while (reader.hasNext()) {
                weeks.add(reader.nextInt());
            }
            reader.endArray();
            return weeks;
        }
        if (reader.peek() == JsonToken.BEGIN_OBJECT) { //TODO proper implementation in later stage. For now can ignore.
            reader.beginObject();
            String startDate = "";
            String endDate = "";
            int weekInterval = 1;
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("start")) {
                    startDate = reader.nextString();
                }
                if (name.equals("end")) {
                    endDate = reader.nextString();
                }
                if (name.equals("weeks")){
                    reader.beginArray();
                    while (reader.hasNext()) {
                        weeks.add(reader.nextInt());
                    }
                    reader.endArray();
                }
                if (name.equals("weekInterval")){
                    weekInterval = reader.nextInt();
                }
            }
            reader.endObject();
            if ((semester > 2) && (weeks.isEmpty())) {
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = start;
                if (endDate.equals("")) {
                    end = SEMESTER_END_DATES.get(semester);
                } else {
                    end = LocalDate.parse(endDate);
                }
                LocalDate semStart = SEMESTER_START_DATES.get(semester);
                int currWeek = (int) ChronoUnit.WEEKS.between(semStart, start) + 1;
                weeks.add(currWeek);
                start = start.plusWeeks(weekInterval);
                while (start.isBefore(end) || start.isEqual((end))) {
                    currWeek += weekInterval;
                    weeks.add(currWeek);
                    start = start.plusWeeks(weekInterval);
                }
            }
            return weeks;
        }
        return weeks;
    }
}

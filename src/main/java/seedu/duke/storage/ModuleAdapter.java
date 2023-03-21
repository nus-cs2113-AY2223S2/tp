package seedu.duke.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import seedu.duke.Lesson;
import seedu.duke.NusModule;

import com.google.gson.stream.JsonWriter;
import seedu.duke.SemData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModuleAdapter extends TypeAdapter<HashMap<String, NusModule>> {

    @Override
    public void write(JsonWriter out, HashMap<String, NusModule> value) throws IOException {
        //No need to write anything!
    }

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

    public NusModule readModule(JsonReader reader) throws IOException{
        String description = null;
        String title = null;
        String faculty = null;
        String moduleCredit = null;
        String moduleCode = null;
        List<SemData> semesterData = null;
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

    private List<SemData> readListSemData(JsonReader reader) throws IOException{
        List<SemData> semDataList = new ArrayList<SemData>();
        reader.beginArray();
        while(reader.hasNext()){
            semDataList.add(readSemData(reader));
        }
        reader.endArray();
        return semDataList;
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
                timetable = readTimetable(reader);
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

    private List<Lesson> readTimetable(JsonReader reader) throws IOException{
        List<Lesson> timetable = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()){
            Lesson lesson = readLesson(reader);
            timetable.add(lesson);
        }
        reader.endArray();
        return timetable;
    }

    private Lesson readLesson(JsonReader reader) throws IOException{
        String classNumber = null;
        String startTime = null;
        String endTime = null;
        ArrayList<Integer> weeks = null;
        String venue = null;
        String lessonType = null;
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
                weeks = readWeeks(reader);
                break;
            case "venue":
                venue = reader.nextString();
                break;
            case "lessonType":
                lessonType = reader.nextString();
                break;
            default:
                reader.skipValue();
                break;
            }
        }
        reader.endObject();
        return new Lesson(classNumber, startTime, endTime, weeks, venue, lessonType);
    }

    private ArrayList<Integer> readWeeks(JsonReader reader) throws IOException{
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
            return weeks;
        }
        return weeks;
    }
}

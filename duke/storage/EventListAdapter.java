package seedu.duke.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import seedu.duke.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/**
 * EventListAdapter is a custom serializer/deserializer type adapter for saving and loading information.
 */
public class EventListAdapter extends TypeAdapter<ArrayList<Event>> {
    private static final DateTimeFormatter dfWithTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    /**
     * Custom serializer for ArrayList of class Event.
     * @param writer the JsonWriter that writes the object to file.
     * @param eventList the Java object to write. May be null.
     * @throws IOException If there is an error writing to file
     */
    @Override
    public void write(JsonWriter writer, ArrayList<Event> eventList) throws IOException {
        writer.beginArray();
        for (Event event : eventList){
            writeEvent(writer, event);
        }
        writer.endArray();
    }

    /**
     * Custom serializer for the Event class. Called by the write function
     * @param writer the JsonWriter that writes the object to file.
     * @param event the Event object to write.
     * @throws IOException If there is an error writing to file
     */
    public void writeEvent(JsonWriter writer, Event event) throws IOException {
        writer.beginObject();
        writer.name("description").value(event.getDescription());
        if (event.hasStartTime()){
            writer.name("startTime");
            writer.beginObject();
            writer.name("date");
            writer.beginObject();
            writer.name("year").value(event.getStartTime().getYear());
            writer.name("month").value(event.getStartTime().getMonthValue());
            writer.name("day").value(event.getStartTime().getDayOfMonth());
            writer.endObject();
            writer.name("time");
            writer.beginObject();
            writer.name("hour").value(event.getStartTime().getHour());
            writer.name("minute").value(event.getStartTime().getMinute());
            writer.endObject();
            writer.endObject();
        }
        if (event.hasEndTime()) {
            writer.name("endTime");
            writer.beginObject();
            writer.name("date");
            writer.beginObject();
            writer.name("year").value(event.getEndTime().getYear());
            writer.name("month").value(event.getEndTime().getMonthValue());
            writer.name("day").value(event.getEndTime().getDayOfMonth());
            writer.endObject();
            writer.name("time");
            writer.beginObject();
            writer.name("hour").value(event.getEndTime().getHour());
            writer.name("minute").value(event.getEndTime().getMinute());
            writer.endObject();
            writer.endObject();
        }
        writer.name("hasEndInfo").value(event.hasEndInfo());
        writer.name("hasStartTime").value(event.hasStartTime());
        writer.name("hasEndTime").value(event.hasEndTime());
        writer.endObject();
    }

    /**
     * Reads information from a json file and deserializes it into an ArrayList of Event class.
     * @param reader reader to receive data from JSON file.
     * @return ArrayList of type event. This is the previously saved data from the application.
     * @throws IOException If there was an error reading the JSON file.
     */
    @Override
    public ArrayList<Event> read (JsonReader reader) throws IOException{
        ArrayList<Event> eventList = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()){
            eventList.add(readEvent(reader));
        }
        reader.endArray();
        return eventList;
    }

    /**
     * Deserializes an Event Json Object into Event Java Object
     * @param reader Json Reader to interpret JSON file.
     * @return  Event. This is the deserialized Event java object.
     * @throws IOException If there was an error deserializing the event class.
     */
    public Event readEvent(JsonReader reader) throws IOException{
        String description = null;
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        boolean hasEndInfo = false;
        boolean hasStartTime = false;
        boolean hasEndTime = false;
        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            if (name.equals("description")){
                description = reader.nextString();
            } else if (name.equals("startTime")){
                String date = null;
                String time = null;
                reader.beginObject();
                while(reader.hasNext()){
                    String startName = reader.nextName();
                    if (startName.equals("date")){
                        date = readDate(reader);
                    } else if (startName.equals("time")){
                        time = readTime(reader);
                    }
                }
                reader.endObject();
                String combination = date + " " + time;
                startTime = LocalDateTime.parse(combination, dfWithTime);
            } else if (name.equals("endTime")){
                String date = null;
                String time = null;
                reader.beginObject();
                while(reader.hasNext()){
                    String startName = reader.nextName();
                    if (startName.equals("date")){
                        date = readDate(reader);
                    } else if (startName.equals("time")){
                        time = readTime(reader);
                    }
                }
                reader.endObject();
                String combination = date + " " + time;
                endTime = LocalDateTime.parse(combination, dfWithTime);
            } else if (name.equals("hasEndInfo")){
                hasEndInfo = reader.nextBoolean();
            } else if (name.equals("hasStartTime")){
                hasStartTime = reader.nextBoolean();
            } else if (name.equals("hasEndTime")){
                hasEndTime = reader.nextBoolean();
            } else{
                reader.skipValue();
            }
        }
        reader.endObject();
        if (hasEndTime == true) {
            return new Event(description, startTime, endTime, hasStartTime, hasEndTime);
        } else{
            return new Event(description, startTime, hasStartTime);
        }
    }

    private String readDate(JsonReader reader) throws IOException {
        int day = 1;
        int month = 1;
        int year = 1;
        reader.beginObject();
        while(reader.hasNext()){
            String dateName = reader.nextName();
            if (dateName.equals("year")){
                year = reader.nextInt();
            } else if (dateName.equals("month")){
                month = reader.nextInt();
            } else if (dateName.equals("day")){
                day = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        String monthString = Integer.toString(month);
        String dayString = Integer.toString(day);
        if (month < 10){
            monthString = "0" + monthString;
        }
        if (day < 10){
            dayString = "0" + dayString;
        }
        return year + "/" + monthString + "/" + dayString;

    }

    private String readTime(JsonReader reader) throws IOException {
        int hour = 0;
        int minute = 0;
        reader.beginObject();
        while(reader.hasNext()){
            String timeName = reader.nextName();
            if (timeName.equals("hour")){
                hour = reader.nextInt();
            } else if (timeName.equals("minute")){
                minute = reader.nextInt();
            } else{
                reader.skipValue();
            }
        }
        reader.endObject();
        String hourString = Integer.toString(hour);
        String minString = Integer.toString(minute);
        if (hour < 10){
            hourString = "0" + hourString;
        }
        if (minute < 10){
            minString = "0" + minString;
        }
        return hourString + ":" + minString;
    }
}

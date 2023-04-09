package seedu.duke.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import seedu.duke.Event;
import seedu.duke.NPExceptions;
import seedu.duke.Ui;

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
            writeStartTime(writer, event);
        }
        if (event.hasEndTime()) {
            writeEndTime(writer, event);
        }
        if (event.hasLocation()){
            writer.name("location").value(event.getLocation());
            writer.name("hasLocation").value(event.hasLocation());
        }
        if (event.isRecurring()){
            writer.name("isRecurring").value(event.isRecurring());
            writer.name("timeInterval").value(event.getTimeInterval());
        }
        writer.name("hasEndInfo").value(event.hasEndInfo());
        writer.name("hasStartTime").value(event.hasStartTime());
        writer.name("hasEndTime").value(event.hasEndTime());
        writer.endObject();
    }

    private static void writeEndTime(JsonWriter writer, Event event) throws IOException {
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

    private static void writeStartTime(JsonWriter writer, Event event) throws IOException {
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

    /**
     * Reads information from a json file and deserializes it into an ArrayList of Event class.
     * @param reader reader to receive data from JSON file.
     * @return ArrayList of type event. This is the previously saved data from the application.
     * @throws IOException If there was an error reading the JSON file.
     */
    @Override
    public ArrayList<Event> read (JsonReader reader) throws IOException {
        ArrayList<Event> eventList = new ArrayList<>();
        boolean isCorrupt = false;
        reader.beginArray();
        while(reader.hasNext()){
            try {
                eventList.add(readEvent(reader));
            } catch (NPExceptions e){
                Ui.printErrorMsg(e.getMessage());
                isCorrupt = true;
            }
        }
        reader.endArray();
        if (isCorrupt){
            eventList.clear();
            JsonEventListStorage.wipeFile();
        }
        return eventList;
    }

    /**
     * Deserializes an Event Json Object into Event Java Object
     * @param reader Json Reader to interpret JSON file.
     * @return  Event. This is the deserialized Event java object.
     * @throws IOException If there was an error deserializing the event class.
     */
    public Event readEvent(JsonReader reader) throws IOException, NPExceptions{
        String description = null;
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        boolean hasEndInfo = false;
        boolean hasStartTime = false;
        boolean hasEndTime = false;
        //Additions start here
        boolean hasLocation = false;
        boolean isRecurring = false;
        String timeInterval = null;
        String location = null;


        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
            case "description":
                description = reader.nextString();
                break;
            case "startTime": {
                startTime = readLocalDateTime(reader);
                break;
            }
            case "endTime": {
                endTime = readLocalDateTime(reader);
                break;
            }
            case "hasEndInfo":
                hasEndInfo = reader.nextBoolean();
                break;
            case "hasStartTime":
                hasStartTime = reader.nextBoolean();
                break;
            case "hasEndTime":
                hasEndTime = reader.nextBoolean();
                break;
            case "hasLocation":
                hasLocation = reader.nextBoolean();
                break;
            case "isRecurring":
                isRecurring = reader.nextBoolean();
                break;
            case "timeInterval":
                timeInterval = reader.nextString();
                break;
            case "location":
                location = reader.nextString();
                break;
            default:
                throw new NPExceptions("File Corrupted"); //catches un-parsable modifications to fields.

            }
        }
        reader.endObject();
        if (!hasStartTime){
            throw new NPExceptions("No Start Time detected. File is corrupted");
        }
        return createEvent(description, startTime, endTime, hasStartTime, hasEndTime, hasLocation,
                isRecurring, timeInterval, location);
    }

    private LocalDateTime readLocalDateTime(JsonReader reader) throws IOException, NPExceptions {
        LocalDateTime dateTime;
        String date = null;
        String time = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String startName = reader.nextName();
            if (startName.equals("date")) {
                date = readDate(reader);
            } else if (startName.equals("time")) {
                time = readTime(reader);
            }
        }
        if ((date == null) || (time == null)) { //checks that date and time fields were not abused.
            throw new NPExceptions("Event Corrupted");
        }
        reader.endObject();
        String combination = date + " " + time;
        dateTime = LocalDateTime.parse(combination, dfWithTime);
        return dateTime;
    }

    /**
     * Helper method to deserialize Json object into Event object
     * @param description Description field of event
     * @param startTime startTime field of event
     * @param endTime endTime field of event
     * @param hasStartTime Boolean - Must be true always
     * @param hasEndTime Boolean - if there is endTime, this must be true
     * @param hasLocation Boolean - if there is location, this must to true
     * @param isRecurring Boolean - if object is recurring (ie has a non-null timeInterval field), this must be true
     * @param timeInterval Time interval between each event.
     * @param location Venue field of event
     * @return
     */
    private static Event createEvent(String description, LocalDateTime startTime, LocalDateTime endTime,
                                     boolean hasStartTime, boolean hasEndTime, boolean hasLocation, boolean isRecurring,
                                     String timeInterval, String location) throws NPExceptions{
        if (hasEndTime && isRecurring && hasLocation){
            if ((timeInterval == null) || (endTime == null) || (location == null) || (description == null) ||
                    (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            Event temp = new Event(description, startTime, endTime, hasStartTime, hasEndTime, timeInterval);
            temp.changeLocation(location);
            return temp;
        } else if (hasEndTime && isRecurring){
            if ((timeInterval == null) || (endTime == null)  || (description == null) || (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            return new Event(description, startTime, endTime, hasStartTime, hasEndTime, timeInterval);
        } else if (hasEndTime && hasLocation){
            if ( (endTime == null) || (location == null) || (description == null) || (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            Event temp = new Event(description, startTime, endTime, hasStartTime, hasEndTime);
            temp.changeLocation(location);
            return temp;
        } else if (isRecurring && hasLocation){
            if ((timeInterval == null) || (location == null) || (description == null) || (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            Event temp = new Event(description, startTime,hasStartTime);
            temp.changeLocation(location);
            return temp;
        } else if (hasEndTime){
            if ( (endTime == null) || (description == null) || (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            return new Event(description, startTime, endTime, hasStartTime, hasEndTime);
        } else if (hasLocation){
            if ( (location == null) || (description == null) || (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            Event temp = new Event(description, startTime, hasStartTime);
            temp.changeLocation(location);
            return temp;
        } else if (isRecurring){
            if ((timeInterval == null) || (description == null) || (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            return new Event(description, startTime, hasStartTime, timeInterval);
        } else{
            if ( (description == null) || (startTime == null)){
                throw new NPExceptions("Event Corrupted!");
            }
            return new Event(description, startTime, hasStartTime);
        }
    }

    /**
     * readDate is used to parse timeandflag objects in events
     * @param reader JsonReader to be used
     * @return String representing date
     * @throws IOException
     */
    private String readDate(JsonReader reader) throws IOException, NPExceptions {
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
                throw new NPExceptions("File Corrupted!");
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

    /**
     * ReadTime is used to parse timeandflag objects
     * @param reader JsonReader to be used
     * @return String representing time
     * @throws IOException
     */
    private String readTime(JsonReader reader) throws IOException, NPExceptions {
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
                throw new NPExceptions("File Corrupted!");
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

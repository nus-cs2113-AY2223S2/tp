package seedu.duke.storage;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//@@author EangJS

/**
 * Custom gson serializer for LocalDateTime.
 */
class LocalDateAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    /**
     * Deserializes the formatted LocalDateTime from json file.
     *
     * @param jsonElement The json element to deserialize.
     * @param jsonDeserializationContext Context for deserialization.
     * @return The LocalDateTime parsed from json element.
     *
     * @throws JsonParseException Occurs when invalid json format is given.
     */
    @Override
    public LocalDateTime deserialize (JsonElement jsonElement,
                                      Type type,
                                      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Serializes the LocalDateTime to ISO_LOCAL_DATE_TIME format.
     *
     * @param localDateTime The localDateTime to be serialized.
     * @return The serialized json element from localDateTime.
     */
    @Override
    public JsonElement serialize (LocalDateTime localDateTime, Type type,
                                  JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(
            localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

}

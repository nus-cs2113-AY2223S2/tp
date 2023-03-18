package utils.storage.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.UUID;
import model.TagUUID;

public class TagUuidJsonAdapter extends TypeAdapter<TagUUID> {

    @Override
    public void write(JsonWriter out, TagUUID value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public TagUUID read(JsonReader in) throws IOException {
        UUID uuid = UUID.fromString(in.nextString());
        return new TagUUID(uuid);
    }
}

package utils.storage.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.UUID;
import model.CardUUID;

public class CardUuidJsonAdapter extends TypeAdapter<CardUUID> {

    @Override
    public void write(JsonWriter out, CardUUID value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public CardUUID read(JsonReader in) throws IOException {
        UUID uuid = UUID.fromString(in.nextString());
        return new CardUUID(uuid);
    }
}

package model;

import java.util.HashSet;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class InkaUUIDTest {

    private UUID UUID1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private UUID UUID2 = UUID.fromString("11111111-1111-1111-1111-111111111111");

    private CardUUID cardUUID1 = new CardUUID(UUID1);
    private CardUUID cardUUID2 = new CardUUID(UUID2);
    private DeckUUID deckUUID1 = new DeckUUID(UUID1);
    private DeckUUID deckUUID2 = new DeckUUID(UUID2);
    private TagUUID tagUUID1 = new TagUUID(UUID1);
    private TagUUID tagUUID2 = new TagUUID(UUID2);

    @Test
    public void uuid_notEquals() {
        assert !cardUUID1.equals(cardUUID2);
        assert !deckUUID1.equals(deckUUID2);
        assert !tagUUID1.equals(tagUUID2);

        assert !cardUUID1.equals(deckUUID1);
        assert !cardUUID1.equals(tagUUID1);
        assert !deckUUID1.equals(tagUUID1);
    }

    @Test
    public void uuid_equals() {
        CardUUID testCardUUID = new CardUUID(UUID1);
        DeckUUID testDeckUUID = new DeckUUID(UUID1);
        TagUUID testTagUUID = new TagUUID(UUID1);

        // Same instance
        assert cardUUID1.equals(cardUUID1);
        assert deckUUID1.equals(deckUUID1);
        assert tagUUID1.equals(tagUUID1);

        // Different instance
        assert cardUUID1.equals(testCardUUID);
        assert deckUUID1.equals(testDeckUUID);
        assert tagUUID1.equals(testTagUUID);
    }

    @Test
    public void uuid_contains() {
        HashSet<CardUUID> set = new HashSet<>();
        set.add(cardUUID1);

        CardUUID testCardUUID = new CardUUID(cardUUID1.uuid);
        assert testCardUUID.hashCode() == cardUUID1.hashCode();

        assert set.contains(cardUUID1);
        assert set.contains(testCardUUID);
        assert !set.contains(cardUUID2);
        assert !set.contains(deckUUID1);
    }

    @Test
    public void uuid_equals_overload() {
        assert cardUUID1.equals(UUID1);
        assert !cardUUID1.equals(UUID2);
    }
}

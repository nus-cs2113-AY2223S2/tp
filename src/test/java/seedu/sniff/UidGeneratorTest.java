package seedu.sniff;

import functionalities.Uid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UidGeneratorTest {

    @Test
    public void uidNotNullTest() {
        String uid = Uid.uidGenerator("A");
        assertNotNull(uid, "Uid generated is null");
    }

    @Test
    public void uidLengthTest() {
        String uid = Uid.uidGenerator("A");
        assertEquals(10, uid.length());
        uid = Uid.uidGenerator("");
        assertEquals(9, uid.length());
        uid = Uid.uidGenerator("Abc");
        assertEquals(12, uid.length());
    }

    @Test
    public void uidFirstCharTest() {
        String uid = Uid.uidGenerator("S");
        assertEquals('S', uid.charAt(0));
        uid = Uid.uidGenerator("V");
        assertEquals('V', uid.charAt(0));
        uid = Uid.uidGenerator("C");
        assertEquals('C', uid.charAt(0));
    }

    @Test
    public void uidLastCharTest() {
        String uid = Uid.uidGenerator("A");
        assertTrue(('A' <= uid.charAt(9)) && (uid.charAt(9) <= 'Z'),
                "Letter should be between A to Z");
    }
}

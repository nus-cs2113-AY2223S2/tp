package seedu.badmaths.trigograph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrigoGraphVisualiserTest {

    @Test
    public void invalidtTrigo_expect_exception() {
        TrigoGraphVisualiserStub test = new TrigoGraphVisualiserStub("tann");
        assertEquals(false, test.startVisualiser());

    }

}

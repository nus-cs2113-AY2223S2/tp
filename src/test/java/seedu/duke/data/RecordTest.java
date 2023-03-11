package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Record;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class RecordTest {

    @Test
    void getRecordCategory_expected() {
        String test = "income";
        Record record = new Record("income", "tuition", "1st apr 2023", (float) 40);
        assertEquals("income", record.getCategory());
    }
}

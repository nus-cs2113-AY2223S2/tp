package seedu.bigpp.command.viewercommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.pc.PC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewCommandTest {
    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_viewIndexCommand_success() throws PPException {
        DataStorage dataStorage = new DataStorage();

        dataStorage.pcList.add(new PC("Test PC", false));

        ViewerViewCommand command = new ViewerViewCommand("1");
        String result = command.executeCommand(dataStorage);

        assertEquals("Custom-PC: [Test PC] - $0.0/infinite - Incomplete\n" +
                "Power Consumption: 0.0W/0W\n" +
                "Components:\n" +
                "CPU        : - Not Selected -\n" +
                "CPU Cooler : - Not Selected -\n" +
                "GPU        : - Not Selected -\n" +
                "Motherboard: - Not Selected -\n" +
                "RAM        : - Not Selected -\n" +
                "Storage    : - Not Selected -\n" +
                "PSU        : - Not Selected -\n" +
                "Chassis    : - Not Selected -\n" , result);
    }
    @Test
    public void  executeCommand_emptyIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerEditCommand("")
                .executeCommand(dataStorage));

        assertEquals("Please input an index", exception.getMessage());
    }
    @Test
    public void executeCommand_nonIntegerIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerEditCommand("abc")
                .executeCommand(dataStorage));

        assertEquals("Please enter a positive integer",  exception.getMessage());
    }
    @Test
    public void executeCommand_invalidIndex_exceptionThrown() {
        DataStorage dataStorage = new DataStorage();

        Exception exception = assertThrows(PPException.class, () -> new ViewerDeleteCommand("0")
                .executeCommand(dataStorage));

        assertEquals("Please enter a valid index", exception.getMessage());
    }
}

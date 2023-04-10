package seedu.bigpp.command.buildercommand;

import org.junit.jupiter.api.Test;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;
import static seedu.bigpp.component.ComponentType.CPU_TYPE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderListComponentCommandTest {

    /**
     *
     */

    private static final String EXPECTED_OUTPUT = "Here are all available components of type 'cpu': \n" +
            "1.\nIntel core i3-10100\n================\n" +
            "2.\nIntel core i5-10600k\n================\n" +
            "3.\nIntel core i7-10700k\n================\n" +
            "4.\nIntel core i3-12100f\n================\n" +
            "5.\nIntel core i5-12600k\n================\n" +
            "6.\nIntel core i7-12700k\n================\n" +
            "7.\nAMD Ryzen 3 3200G\n================\n" +
            "8.\nAMD Ryzen 5 5600X\n================\n" +
            "9.\nAMD Ryzen 7 5800X\n================\n" +
            "10.\nAMD Ryzen 5 7600X\n================\n" +
            "11.\nAMD Ryzen 7 7700X\n================\n" +
            "12.\nAMD Ryzen 9 7950X\n================\n";

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_listCpu_success() throws PPException {

        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        String listComponentCommandResult = new BuilderListComponentCommand(CPU_TYPE).executeCommand(dataStorage);

        assertEquals(EXPECTED_OUTPUT, listComponentCommandResult);
    }

    @Test
    public void executeCommand_listCpuMissingName_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand("cpu -name")
                .executeCommand(dataStorage));
        assertEquals("Please enter a name after the name flag", exception.getMessage());
    }

    @Test
    public void executeCommand_listCpuInvalidName_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand("cpu -name -name")
                .executeCommand(dataStorage));
        assertEquals("Flag detected as a name. Please enter another name after the flag", exception.getMessage());
    }

    @Test
    public void executeCommand_listCpuMissingBrand_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand("cpu -brand")
                .executeCommand(dataStorage));
        assertEquals("Please enter a brand after the brand flag", exception.getMessage());
    }

    @Test
    public void executeCommand_listCpuInvalidBrand_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand("cpu -brand -price")
                .executeCommand(dataStorage));
        assertEquals("Flag detected as a brand. Please enter another brand after the flag", exception.getMessage());
    }

    @Test
    public void executeCommand_listCpuMissingPriceDescription_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand("cpu -price")
                .executeCommand(dataStorage));
        assertEquals("Please enter a price description after the price flag",
                exception.getMessage());
    }

    @Test
    public void executeCommand_listCpuIncompletePriceDescription_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand(
                "cpu -price /from 10")
                        .executeCommand(dataStorage));
        assertEquals("Please enter the full price description after the flag containing the start and end price range",
                exception.getMessage());
    }

    @Test
    public void executeCommand_listCpuInvalidPriceType_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand(
                "cpu -price /from ten /to 100").executeCommand(dataStorage));
        assertEquals("Please enter a float for price start range", exception.getMessage());
    }

    @Test
    public void executeCommand_listcpuInvalidPrice_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand(
                "cpu -price /from -100 /to 100").executeCommand(dataStorage));
        assertEquals("Price must be greater than 0", exception.getMessage());
    }

    @Test
    public void executeCommand_listcpuInvalidPriceDescription_exceptionThrown() {
        dataStorage.initStringToComponentListMap();
        dataStorage.loadAll();

        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderListComponentCommand(
                "cpu -price from 100 to 100").executeCommand(dataStorage));
        assertEquals("Please use /from to specify the start price range", exception.getMessage());
    }
}

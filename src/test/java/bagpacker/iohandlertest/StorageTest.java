package bagpacker.iohandlertest;

import bagpacker.commands.*;
import bagpacker.iohandler.Storage;
import bagpacker.iohandler.Ui;
import org.junit.jupiter.api.Test;
import bagpacker.packingfunc.PackingList;
import bagpacker.packingfunc.Item;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * StorageTest class test Storage methods
 */
public class StorageTest {
    //@@author sunilphua

    private static final String properSave = "./src/test/data/properSaveDirectory.txt";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui;
    private final PrintStream standardOut = System.out;

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }
    //@@author coregano

    @Test
    public void sampleTest() throws FileNotFoundException {
        Storage storage = new Storage(properSave);
        PackingList packingList1 = new PackingList();
        AddCommand add1 = new AddCommand(new Item(2, 1, "cats"));
        add1.execute(packingList1);
        Command add2 = new AddCommand(new Item(4, 0, "dogs"));
        add2.execute(packingList1);
        setUp();
        storage.save(packingList1);
        DeleteListCommand cleanList = new DeleteListCommand();
        cleanList.execute(packingList1);
        storage.load();
        ListCommand listCmd = new ListCommand();
        listCmd.execute(packingList1);
        String listLine1 = "Here are the items in your list";
        String listLine2 = "[1/2] cats";
        String listLine3 = "[0/4] dogs";
        assert(outputStreamCaptor.toString().trim().contains(listLine1)
                & outputStreamCaptor.toString().trim().contains(listLine2)
                & outputStreamCaptor.toString().trim().contains(listLine3));
        tearDown();
        cleanList.execute(packingList1);
    }
}

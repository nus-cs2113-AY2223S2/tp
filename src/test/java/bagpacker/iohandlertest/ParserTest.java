package bagpacker.iohandlertest;
import bagpacker.commands.AddCommand;
import bagpacker.commands.ByeCommand;
import bagpacker.commands.DeleteCommand;
import bagpacker.commands.DeleteListCommand;
import bagpacker.commands.EditQuantityCommand;
import bagpacker.commands.FindCommand;
import bagpacker.commands.HelpCommand;
import bagpacker.commands.IncorrectCommand;
import bagpacker.commands.ListCommand;
import bagpacker.commands.ListUnpackedCommand;
import bagpacker.commands.PackAllCommand;
import bagpacker.commands.PackCommand;
import bagpacker.commands.UnpackAllCommand;
import bagpacker.commands.UnpackCommand;
import bagpacker.exception.EmptyInputException;
import bagpacker.iohandler.Parser;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ParserTest to test Parser Class
 */
public class ParserTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void fullInputTest() {
        String userInput = "  add this item  ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        Parser.parse();
        assertEquals(userInput.trim(), Parser.getFullInput());
    }

    @Test
    public void createAddObjTest_Successful() {
        PackingList dummyPackingList = new PackingList();
        String userInput = "  add 500 /of this item  ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        assertEquals(AddCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createAddObjTest_NoItemName() {
        PackingList dummyPackingList = new PackingList();
        String userInput = "  add 500 /of   ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        assertEquals(IncorrectCommand.class,Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void createAddObjTest_RepeatItem() {
        PackingList dummyPackingList = new PackingList();
        String userInput = "  add 500 /of something ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);
        InputStream inStream2 = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream2);
        Parser.parse().execute(dummyPackingList);
        assertEquals(1000, PackingList.get(0).getTotalQuantity());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void emptyInputTest() {
        String userInput = "   ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        setUp();
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        assert(outputStreamCaptor.toString().trim().contains("Empty input received")
                & outputStreamCaptor.toString().trim().contains("try to input a command, to view all commands input 'help'"));
        tearDown();
    }

    @Test
    public void createDeleteObjTest() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  delete 1 ";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(DeleteCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createPackObjTest() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  pack 1 /of 1";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(PackCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createUnpackObjTest_Successful() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  pack 2 /of 1";
        String userInput3 = "  Unpack 1 /of 1";
        PackingList dummyPackingList = new PackingList();

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput3.getBytes());
        System.setIn(inStream);
        assertEquals(UnpackCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createUnpackObjTest_InvalidItemQuantity() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  pack 2 /of 1";
        String userInput3 = "  Unpack 10 /of 1";
        PackingList dummyPackingList = new PackingList();

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput3.getBytes());
        System.setIn(inStream);
        setUp();
        Parser.parse().execute(dummyPackingList);
        assert(outputStreamCaptor.toString().trim().contains("Invalid Item Quantity")
                & outputStreamCaptor.toString().trim().contains("Can only unpack a positive quantity that is less " +
                "than or"));
        tearDown();
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createPackAllObjTest() {
        String userInput1 = "  add 1357 /of this item  ";
        String userInput2 = "  packall /of 1  ";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(PackAllCommand.class, Parser.parse().getClass());

        PackAllCommand packallList = new PackAllCommand(1);
        packallList.execute(dummyPackingList);
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createUnpackAllObjTest_Successful() {
        String userInput1 = "  add 2468 /of this item  ";
        String userInput2 = "  unpackall /of 1  ";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(UnpackAllCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void createUnpackAllObjTest_InvalidIndex() {
        String userInput1 = "  add 2468 /of this item  ";
        String userInput2 = "  unpackall /of 1000  ";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        setUp();
        Parser.parse().execute(dummyPackingList);
        assert(outputStreamCaptor.toString().trim().contains("Invalid Item Index")
                & outputStreamCaptor.toString().trim().contains("Try to input a positive integer number " +
                "that is at most"));
        tearDown();
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void createEditQuantityObjTest() {
        String userInput1 = "  add 12345 /of this item  ";
        String userInput2 = "  editquantity 67890 /of 1  ";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(EditQuantityCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createListObjTest() {
        String userInput1 = "  add 1 /of this item  ";
        String userInput2 = "  lIST ";
        PackingList dummyPackingList = new PackingList();

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);

        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);

        assertEquals(ListCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createListUnpackedObjTest() {
        String userInput1 = "  add 3 /of this item  ";
        String userInput2 = "  listunpacked  ";
        PackingList dummyPackingList = new PackingList();
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(ListUnpackedCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createFindObjTest_Successful() {
        String userInput1 = "  add 123 /of this item  ";
        String userInput2 = "  find item  ";
        PackingList dummyPackingList = new PackingList();
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(FindCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void createFindObjTest_EmptyKeyword() {
        String userInput1 = "  add 123 /of this item  ";
        String userInput2 = "  find   ";
        PackingList dummyPackingList = new PackingList();
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        setUp();
        Parser.parse().execute(dummyPackingList);
        assert(outputStreamCaptor.toString().trim().contains("Blank keyword")
                & outputStreamCaptor.toString().trim().contains("Try to input a keyword to be searched in the list"));
        tearDown();
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }
    @Test
    public void createDeleteListObjTest() {
        String userInput1 = "  add 123 /of this item  ";
        String userInput2 = "  deletelist  ";
        PackingList dummyPackingList = new PackingList();
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(DeleteListCommand.class, Parser.parse().getClass());
        DeleteListCommand delList = new DeleteListCommand();
        delList.execute(dummyPackingList);
    }

    @Test
    public void createHelpObjTest() {
        String userInput1 = "  HelP  ";

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);

        assertEquals(HelpCommand.class, Parser.parse().getClass());
    }

    @Test
    public void createByeObjTest() {
        String userInput1 = "  byE  ";

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);

        assertEquals(ByeCommand.class, Parser.parse().getClass());
    }

    @Test
    public void createIncorrectCommandTest() {
        String userInput1 = " thisIsNOTaVALID command  ";

        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);

        assertEquals(IncorrectCommand.class, Parser.parse().getClass());
    }
}

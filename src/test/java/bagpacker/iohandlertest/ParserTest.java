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
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * ParserTest to test Parser Class
 */
public class ParserTest {
    @Test
    public void fullInputTest() {
        String userInput = "  add this item  ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        Parser.parse();
        assertEquals(userInput.trim(), Parser.getFullInput());
    }

    @Test
    public void createAddObjTest() {
        String userInput = "  add 500 /of this item  ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        assertEquals(AddCommand.class, Parser.parse().getClass());
    }

    @Test
    public void emptyInputTest() {
        String userInput = "   ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        Exception exception = assertThrows(EmptyInputException.class, Parser::readLine);
        assertEquals(EmptyInputException.class, exception.getClass());
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
    }

    @Test
    public void createUnpackObjTest() {
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
    }

    @Test
    public void createUnpackAllObjTest() {
        String userInput1 = "  add 2468 /of this item  ";
        String userInput2 = "  unpackall /of 1  ";
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        PackingList dummyPackingList = new PackingList();
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(UnpackAllCommand.class, Parser.parse().getClass());
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
    }

    @Test
    public void createFindObjTest() {
        String userInput1 = "  add 123 /of this item  ";
        String userInput2 = "  find item  ";
        PackingList dummyPackingList = new PackingList();
        InputStream inStream = new ByteArrayInputStream(userInput1.getBytes());
        System.setIn(inStream);
        Parser.parse().execute(dummyPackingList);
        inStream = new ByteArrayInputStream(userInput2.getBytes());
        System.setIn(inStream);
        assertEquals(FindCommand.class, Parser.parse().getClass());
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

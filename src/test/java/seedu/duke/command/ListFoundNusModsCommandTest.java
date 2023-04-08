package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import seedu.duke.Module;

import seedu.duke.University;
import seedu.duke.DataReader;


class ListFoundNusModsCommandTest {
    private static final String LINE = "____________________________________________________________";
    private static final String FOUND_LIST_MESSAGE = "Here is/are the list/s of modules that can map "
                                                        + "this NUS module code: ";
    DataReader dataReader = DataReader.getDataReaderOneInstance();
    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStreamCatcher = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCatcher));
    }

    @Test
    void listFoundNusModsCommand_correctFormatSuccess() {
        String nusModCode = "ME4661";
        ArrayList<University> universities = dataReader.getUniversities();
        ArrayList<Module> foundNusModList = new ArrayList<>();
        Module module1 = new Module(1, "IWC109", "Engineering Design", 3,
                "ME4661", "Exchange Elective", 4);
        Module module2 = new Module(2, "AE405", "Satellite Systems",3,
                "ME4661", "Technical Elective", 4);
        Module module3 = new Module(2, "ENGME203","Mechatronics system design",
                3,"ME4661","Exchange Elective",4);
        Module module4 = new Module(3,"MECH441","METAL FORMING",3,
                "ME4661","Technical Elective",4);
        Module module5 = new Module(4,"406.752","VEHICLE ERGONOMICS",3,
                "ME4661","Technical Elective 4",4);
        Module module6 = new Module(4,"ZXX4582.503","FUNCTIONAL POLYMER NANOMATERIALS"
                ,0,"ME4661","Technical Elective",4);
        Module module7 = new Module(4,"M2794.008600","INVISCID FLOW",0,
                "ME4661","Technical Elective",4);
        Module module8 = new Module(4,"M2795.006500","AIR BREATHING PROPULSION THEORY",
                0,"ME4661","Technical Elective",4);
        Module module9 = new Module(4,"457.206","SOIL MECHANICS",0,
                "ME4661","Technical Elective",4);
        Module module10 = new Module(4,"M2795.00400","HIGH ENERGY THERMOFLUID DYNAMICS",
                0,"ME4661","Technical Elective",4);
        foundNusModList.add(module1);
        foundNusModList.add(module2);
        foundNusModList.add(module3);
        foundNusModList.add(module4);
        foundNusModList.add(module5);
        foundNusModList.add(module6);
        foundNusModList.add(module7);
        foundNusModList.add(module8);
        foundNusModList.add(module9);
        foundNusModList.add(module10);
        ListFoundNusModsCommand listFoundNusModsCommand = new ListFoundNusModsCommand(nusModCode, foundNusModList,
                                                                                        universities);
        listFoundNusModsCommand.execute();

        assertEquals(FOUND_LIST_MESSAGE + nusModCode + System.lineSeparator() + LINE + System.lineSeparator()
                + LINE + System.lineSeparator() + "KU" + System.lineSeparator() + LINE + System.lineSeparator()
                + "1. [IWC109][Engineering Design][3]" + System.lineSeparator()
                + LINE + System.lineSeparator() + "KAIST" + System.lineSeparator() + LINE + System.lineSeparator()
                + "1. [AE405][Satellite Systems][3]" + System.lineSeparator()
                + "2. [ENGME203][Mechatronics system design][3]" + System.lineSeparator() + LINE
                + System.lineSeparator() + "POSTECH" + System.lineSeparator() + LINE + System.lineSeparator()
                + "1. [MECH441][METAL FORMING][3]" + System.lineSeparator() + LINE + System.lineSeparator()
                + "SNU" + System.lineSeparator() + LINE + System.lineSeparator()
                + "1. [406.752][VEHICLE ERGONOMICS][3]" + System.lineSeparator()
                + "2. [ZXX4582.503][FUNCTIONAL POLYMER NANOMATERIALS][0]" + System.lineSeparator()
                + "3. [M2794.008600][INVISCID FLOW][0]" + System.lineSeparator()
                + "4. [M2795.006500][AIR BREATHING PROPULSION THEORY][0]" + System.lineSeparator()
                + "5. [457.206][SOIL MECHANICS][0]" + System.lineSeparator()
                + "6. [M2795.00400][HIGH ENERGY THERMOFLUID DYNAMICS][0]"
                , outputStreamCatcher.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOutput);
    }
}

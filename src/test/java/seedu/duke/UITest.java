package seedu.duke;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.InvalidPuException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UITest {
    private static final String LIST_PU_MESSAGE = "This is the list of PUs:";
    private static final String LIST_CURRENT_MESSAGE = "List of Added modules:";
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_MOD_MESSAGE = "This module has been added to the current module list!";
    private static final String DELETE_MOD_MESSAGE = "This module has been deleted from the current module list!";

    private static final String ADD_MOD_FAILURE_MESSAGE = "Save Module Failed";
    private static final String COMMAND_INPUT_ERROR = "Please type in the correct command input";
    private static final String WELCOME_MESSAGE = "~Welcome to SEP Helper~";
    private static final String READ_COMMAND_INPUT = "What can I do for you?";
    private static final String HELP_MESSAGE = "\nType /help if you need help getting started :)";
    private static final String INPUT_NOT_INT_MESSAGE = "The input for the given command is not an integer";
    private static final String INVALID_PU_MESSAGE = "PU not found :( Please type in the correct PU name";
    private static final String INVALID_MODULE_MESSAGE = "Module not found :( Please type in the correct MODULE name";
    private static final String CURRENT_LIST_EMPTY = "The current module list is empty";

    /*
        Testing below sets up an ByteArrayOutputStream where prints to System.out would go to.
        Enable checks to be done as outContent.toString() is called and String there is compared
        to expected String output.
    */
    //@@author dfa-reused
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStream() {
        System.setOut(originalOut);
    }

    //Solution below adapted from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    /*
        In the tests below, stripTrailing is used as Git Actions automatically strips trailing spaces in their checks
     */
    @Test
    void printPUListMessage_correctLines_success() {
        UI ui = new UI();
        ui.printPUListMessage();
        assertEquals(LIST_PU_MESSAGE,
                    outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printPUModListMessage_correctLines_success() {
        UI ui = new UI();
        String univName = "Korea University";
        ui.printPUModListMessage(univName);
        assertEquals(univName + " Modules" + System.lineSeparator() + LINE.stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printAddModMessage_correctLines_success() {
        UI ui = new UI();
        ui.printAddModMessage();
        assertEquals(ADD_MOD_MESSAGE + System.lineSeparator() + LINE.stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printDeleteModMessage_correctLines_success() {
        UI ui = new UI();
        ui.printDeleteModMessage();
        assertEquals( DELETE_MOD_MESSAGE + System.lineSeparator() + LINE.stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printInputNotNumMessage_correctLines_success() {
        UI ui = new UI();
        ui.printInputNotNumMessage();
        assertEquals(INPUT_NOT_INT_MESSAGE + System.lineSeparator() + LINE.stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void getInvalidPuMessage_correctLines_success() {
        UI ui = new UI();
        String invalidPuMessage = ui.getInvalidPuMessage();
        assertEquals(INVALID_PU_MESSAGE, invalidPuMessage.stripTrailing());
        outContent.reset();
    }

    @Test
    void getInvalidModuleMessage_correctLines_success() {
        UI ui = new UI();
        String invalidModuleMessage = ui.getInvalidModuleMessage();
        assertEquals(INVALID_MODULE_MESSAGE, invalidModuleMessage.stripTrailing());
        outContent.reset();
    }

    @Test
    void printGreetingMessage_correctLines_success() {
        UI ui = new UI();
        ui.printGreetingMessage();
        assertEquals("\n" +
                        "  ____  _____ ____    _   _      _                 \n" +
                        " / ___|| ____|  _ \\  | | | | ___| |_ __   ___ _ __ \n" +
                        " \\___ \\|  _| | |_) | | |_| |/ _ \\ | '_ \\ / _ \\ '__|\n" +
                        "  ___) | |___|  __/  |  _  |  __/ | |_) |  __/ |   \n" +
                        " |____/|_____|_|     |_| |_|\\___|_| .__/ \\___|_|   \n" +
                        "                                  |_|              \n" + System.lineSeparator() +
                WELCOME_MESSAGE + System.lineSeparator() + READ_COMMAND_INPUT + System.lineSeparator()
                + HELP_MESSAGE + System.lineSeparator() + LINE.stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printPUModules_correctLines_success() {
        UI ui = new UI();
        ui.printPUModules(1);
        assertEquals("1. [AMSE216][Introduction to biomaterials][3]" + System.lineSeparator() +
                 "   maps to ----> [ME4253][Biomaterials Engineering][4]" + System.lineSeparator() +
                "2. [IWC311][Heat Transfer][3]" + System.lineSeparator() +
                "   maps to ----> [ME3122][Heat Transfer][4]" + System.lineSeparator() +
                "3. [AE320][Aerodynamics II][3]" + System.lineSeparator() +
                "   maps to ----> [ME4231][Aerodynamics][4]" + System.lineSeparator() +
                "4. [IWC109][Engineering Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Exchange Elective][4]" + System.lineSeparator() +
                "____________________________________________________________\n".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
        ui.printPUModules(2);
        assertEquals("1. [HSS362][Special Lectures on Art<Korean Popular Culture and the Korean Wave>][3]"
                        + System.lineSeparator() +
                "   maps to ----> [EX3887][Exchange UEM][4]" + System.lineSeparator() +
                "2. [ME320][Applied Fluid Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2135][Intermediate Fluid Mechanics][4]" + System.lineSeparator() +
                "3. [NQE202][Introduction to Nuclear Engineering I][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "4. [ME311][Heat Transfer][3]" + System.lineSeparator() +
                "   maps to ----> [ME3122][Heat Transfer][4]" + System.lineSeparator() +
                "5. [AE410][Combustion Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME4227][Internal Combusion Engines][4]" + System.lineSeparator() +
                "6. [ME251][Dynamics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2115][Mechanics of Machines][4]" + System.lineSeparator() +
                "7. [ME221][Fluid Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2134][Fluid Mechanics I][4]" + System.lineSeparator() +
                "8. [ME453][Introduction to Robotics Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME4245][Robot Mechanics and Control][4]" + System.lineSeparator() +
                "9. [MAE221][Fluid Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2134][Fluid Mechanics I][4]" + System.lineSeparator() +
                "10. [AE405][Satellite Systems][3]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective][4]" + System.lineSeparator() +
                "11. [COE202][Basics of Artificial Intelligence<Learning Artificial Intelligence with LEGO>][3]"
                        + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "12. [MAE552][Introduction to Acoustics][0]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "13. [ENGME440][Engineering Design via FEM][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "14. [ENGEE488][Special Topics in Electrical Engineering<Introduction to Multi-disciplinary Robotics>" +
                        "][3]" + System.lineSeparator() +
                "   maps to ----> [ME4245][Robot Mechanics and Control][4]" + System.lineSeparator() +
                "15. [AE321][Compressible Aerodynamics][3]" + System.lineSeparator() +
                "   maps to ----> [ME4231][Aerodynamics][4]" + System.lineSeparator() +
                "16. [ME361][MODELING AND CONTROL OF ENGINEERING SYSTEMS][3]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "17. [AE310][Propulsion system][3]" + System.lineSeparator() +
                "   maps to ----> [ME5309][Aircraft Engines and Rocket Propulsion][4]" + System.lineSeparator() +
                "18. [ME535][Finite Element Analysis of Structures][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "19. [AE522][Computational Fluid Dynamics][3]" + System.lineSeparator() +
                "   maps to ----> [ME4233][Computational Methods in Fluid Mechanics][4]" + System.lineSeparator() +
                "20. [ME521][Viscous Fluid Flow][3]" + System.lineSeparator() +
                "   maps to ----> [ME2135][Intermediate Fluid Mechanics][4]" + System.lineSeparator() +
                "21. [AE331][Aerospace Structures II][3]" + System.lineSeparator() +
                "   maps to ----> [ME4212][Aircraft Structures][4]" + System.lineSeparator() +
                "22. [ENGME203][Mechatronics system design][3]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Exchange Elective][4]" + System.lineSeparator() +
                "23. [ENGME320][Applied Fluid Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2135][Intermediate Fluid Mechanics][4]" + System.lineSeparator() +
                "24. [ME489][Special Topics in Mechanical Engineering Practice<Programming for Signal and " +
                        "image processing>][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "25. [ME207][Applied Electronics][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "26. [AE530][Flight Vehicle Structures][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "27. [ME492][Special Topics in Mechanical Engineering Practice<Mobile System Programming>][3]"
                        + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "28. [CTP445][Augmented Reality][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "29. [ME491][Special Topics in Mechanical Engineering<Visual Intelligence>][3]"
                        + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "30. [AE455][Global Positioning System][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "31. [CS374][Introduction to Human-Computer Interaction][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "32. [AE300][Flight Mechanics Project][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "33. [ID212][Basic Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "34. [ID303][Design Methods][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "35. [ME510][Advanced Fluid Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2135][Intermediate Fluid Mechanics][4]" + System.lineSeparator() +
                "36. [PH221][Classical Mechanics I][3]" + System.lineSeparator() +
                "   maps to ----> [ME2115][Mechanics of Machines][4]" + System.lineSeparator() +
                "37. [ME502][Introduction to Finite Element Method][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "38. [AE330][Aerospace Structures I][3]" + System.lineSeparator() +
                "   maps to ----> [ME4212][Aircraft Structures][4]" + System.lineSeparator() +
                "39. [ME200][Basic Mechanical Practice][3]" + System.lineSeparator() +
                "   maps to ----> [ME2102][Engineering Innovation and Mod][4]" + System.lineSeparator() +
                "40. [EE381][Control System Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "41. [STP213][Silicon Valley in Perspective][3]" + System.lineSeparator() +
                "   maps to ----> [EX4883][Exchange UEM][4]" + System.lineSeparator() +
                "42. [BIS200][Bioengineering Fundamentals][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "43. [ID301][Interactive Product Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "44. [IE312][Introduction to Human Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "45. [CE201][Mechanics of Materials I][3]" + System.lineSeparator() +
                "   maps to ----> [ME2112][Strength of Materials][4]" + System.lineSeparator() +
                "46. [ME231][Solid Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2112][Strength of Materials][4]" + System.lineSeparator() +
                "47. [CBE331][Fluid Mechanics for Chemical Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME2134][Fluid Mechanics I][4]" + System.lineSeparator() +
                "48. [IE321][PRODUCTION MANAGEMENT I][0]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "49. [CE202][STRUCTURAL MECHANICS][0]" + System.lineSeparator() +
                "   maps to ----> [ME2112][Strength of Materials][3]" + System.lineSeparator() +
                "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
        ui.printPUModules(3);
        assertEquals("1. [MECH240][Solid mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2112][Strength of Materials][4]" + System.lineSeparator() +
                "2. [MECH370][Fluid mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2134][Fluid Mechanics I][4]" + System.lineSeparator() +
                "3. [SOSC351][Media and Society][3]" + System.lineSeparator() +
                "   maps to ----> [EX3887][Exchange UEM][4]" + System.lineSeparator() +
                "4. [AMSE361][Introduction to Polymers][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "5. [AMSE211][MATERIALS DESIGN][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "6. [AMSE212][Nanoscience and Nanotechnology][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "7. [IMEN585][Financial Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "8. [MECH541][MECHANICS OF COMPOSITE MATERIALS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "9. [MECH678][FLOW VISUALISATION][0]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "10. [AMSE464][PHYSICAL PROPERTIES OF POLYMERS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "11. [MEIE583][INTRO TO FINITE ELEMENT METHOD][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "12. [MECH490G][Introduction to Appropriate Technology][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "13. [MECH490K][Artificial Intelligence for M.E.][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "14. [CITE490K][Fundamental of Robotics][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "15. [PHYS203][Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2115][Mechanics of Machines][4]" + System.lineSeparator() +
                "16. [MECH583][Intro. to Finite Element Method][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "17. [MECH583][Intro. to Finite Element Method][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "18. [MECH240][Solid Mechanics I][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "19. [MECH501][Analytical Methods in Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "20. [MECH303][MATHEMATICAL METHODS IN ENGINEERING][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "21. [MECH441][METAL FORMING][3]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective][4]" + System.lineSeparator() +
                "22. [MECH211][Dynamics][3]" + System.lineSeparator() +
                "   maps to ----> [ME2115][Mechanics of Machines][4]" + System.lineSeparator() +
                "23. [MECH515][CONTINUUM MECHANICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "24. [MECH550][ADVANCED THERMODYNAMICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "25. [MECH433][SYSTEMS DESIGN II][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "26. [AMSE321][Intro. to Metallic Materials][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "27. [MECH470][Applied Fluid Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "28. [MECH381][ELECTRONICS FOR MECHANICAL ENGINEERS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "29. [MECH465][ENGINEERING OF CREATIVE DESIGN][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "30. [AMSE501][ADVANCED THERMODYNAMICS OF MATERIALS][0]" + System.lineSeparator() +
                "   maps to ----> [ME3995][Technical Elective][4]" + System.lineSeparator() +
                "31. [MECH371][Heat Transfer][3]" + System.lineSeparator() +
                "   maps to ----> [ME3122][Heat Transfer][4]" + System.lineSeparator() +
                "32. [MECH323][SYSTEMS CONTROL][0]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "33. [MECH579][INTRO TO MICROFLUIDS][0]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "34. [MECH450][APPLIED THERMAL ENGINEERING][0]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "35. [MECH471][AERODYNAMICS][0]" + System.lineSeparator() +
                "   maps to ----> [ME4231][Aerodynamics and Propulsion][4]" + System.lineSeparator() +
                "36. [ADMS568][NANO BIOMATERIALS][0]" + System.lineSeparator() +
                "   maps to ----> [ME4253][Biomaterials Engineering][4]" + System.lineSeparator() +
                "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
        ui.printPUModules(4);
        assertEquals("1. [M3228001000 001][Hypersonic Vehicle Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "2. [M1586001000 001][Computational Fluid Dynamics for Civil and Environmental Engineering][3]"
                        + System.lineSeparator() +
                "   maps to ----> [ME4233][Computational Methods in Fluid Mechanics][4]" + System.lineSeparator() +
                "3. [046006 001][Man and the Universe][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "4. [458308][Process Control and Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "5. [M2794.001700][MECHANICAL PRODUCT DESIGN][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "6. [414336][Fundamentals in Finite Element Analysis][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "7. [M2795.002400][Mechanics of Aerospace Structures][3]" + System.lineSeparator() +
                "   maps to ----> [ME4212][Aircraft Structures][4]" + System.lineSeparator() +
                "8. [M2794.00400][Micro-nano Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME3281][Microsystems Design and Applications][4]" + System.lineSeparator() +
                "9. [M2794.01360][Micro-nano Manufacturing][3]" + System.lineSeparator() +
                "   maps to ----> [ME3281][Microsystems Design and Applications][4]" + System.lineSeparator() +
                "10. [M2795.00830][Computational Structural Analysis][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "11. [M2794.0055][Robot Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME4245][Robot Mechanics and Control][4]" + System.lineSeparator() +
                "12. [M2794.007300][Finite Element Analysis][0]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "13. [400.505][INTRODUCTION TO THE FINITE ELEMENT METHOD][0]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "14. [M2794.003000][COMPUTER SIMULATION & DESIGN][0]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "15. [446.345][INTRODUCTION TO ROBOTICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME4245][Robot Mechanics and Control][4]" + System.lineSeparator() +
                "16. [406.752][VEHICLE ERGONOMICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective 4][4]" + System.lineSeparator() +
                "17. [ME446.632][CONTROL SYSTEM I][0]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "18. [M2795.002600][PRINCIPLES OF FLIGHT VEHICLE CONTROL][0]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "19. [446.781][DECISION MAKING FOR AUTONOMOUS AEROSPACE SYSTEMS][0]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "20. [M2794.00350][Micro Manufacturing][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "21. [M2794.01340][Mechatronics][3]" + System.lineSeparator() +
                "   maps to ----> [ME3241][Microprocessor Applications][4]" + System.lineSeparator() +
                "22. [M2794.00490][Digital Embedded Mechanical Systems][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "23. [M2794.01310][Mechanobiology][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "24. [M2794.00400][Micro-nano Mechanics][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "25. [M2794.00360][Optimal Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "26. [M2794.01350][Mechanical System Modeling and Control][3]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "27. [M2794.0022][Applied Thermodynamics][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "28. [M2794.0041][Combustion and Environmental Engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "29. [ZXX430.610A][Advanced Electricity Market Theory][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "30. [ZXX459.6][Topics in Petroleum and Gas engineering][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "31. [ZXX400.512][Design and Practice of Wind Turbine System][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "32. [M2794.0053][Control Systems 1][3]" + System.lineSeparator() +
                "   maps to ----> [ME4246][Modern Control System][4]" + System.lineSeparator() +
                "33. [ZXX5261.326][Biosystems Control and Lab][3]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "34. [M2794.0077][Smart materials and design][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "35. [M2795.01070][Introduction to Innovative Aerospace Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "36. [M2794.00880][Advanced Gas Turbines][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "37. [M2794.00380][Flow and Design][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "38. [M2794.0068][Instrumentation for Measurement Analysis and Control][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "39. [M2794.0023][Internal Combusion Engines][3]" + System.lineSeparator() +
                "   maps to ----> [ME4227][Internal Combusion Engines][4]" + System.lineSeparator() +
                "40. [M2794.00270][INTRODUCTION TO ROBOTICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME4245][Robot Mechanics and Control][4]" + System.lineSeparator() +
                "41. [M2795.0026][PRINCIPLES OF FLIGHT VEHICLE CONTROL][3]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "42. [M2794.0073][Finite Element Analysis][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "43. [M2794.0026][Heat Transfer][3]" + System.lineSeparator() +
                "   maps to ----> [ME3122][Heat Transfer][4]" + System.lineSeparator() +
                "44. [M2794.0104][FUEL CELL FUNDAMENTALS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "45. [55006][LIFE IN CONTEMPORARY KOREA][0]" + System.lineSeparator() +
                "   maps to ----> [EX1000][Exchange UEM][4]" + System.lineSeparator() +
                "46. [ZXX4582.503][FUNCTIONAL POLYMER NANOMATERIALS][0]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective][4]" + System.lineSeparator() +
                "47. [M2794.00200][APPLIED FLUID MECHANICS][0]" + System.lineSeparator() +
                "   maps to ----> [ME2135][Intermediate Fluid Mechanics][4]" + System.lineSeparator() +
                "48. [ZXX458.305][PROCESS FLUID MECHANICS][0]" + System.lineSeparator() +
                "   maps to ----> [ME2135][Intermediate Fluid Mechanics][4]" + System.lineSeparator() +
                "49. [409.33][SYSTEMS ENGINEERING THERMODYNAMICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3221][Energy Conversion Processes][4]" + System.lineSeparator() +
                "50. [M2794.008600][INVISCID FLOW][0]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective][4]" + System.lineSeparator() +
                "51. [M2795.006500][AIR BREATHING PROPULSION THEORY][0]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective][4]" + System.lineSeparator() +
                "52. [457.206][SOIL MECHANICS][0]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective][4]" + System.lineSeparator() +
                "53. [M2795.002600][PRINCIPLES OF FLIGHT VEHICLE CONTROL][0]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "54. [M2795.004300][ROCKET PROPULSION][0]" + System.lineSeparator() +
                "   maps to ----> [ME4662][Technical Elective][4]" + System.lineSeparator() +
                "55. [M2795.00400][HIGH ENERGY THERMOFLUID DYNAMICS][0]" + System.lineSeparator() +
                "   maps to ----> [ME4661][Technical Elective][4]" + System.lineSeparator() +
                "56. [M2794.002600][Heat Transfer][0]" + System.lineSeparator() +
                "   maps to ----> [ME3122][Heat Transfer][4]" + System.lineSeparator() +
                "57. [406.436][MANUFACTURING PROCESS DESIGN FOR INDUSTRIAL ENGINEERS][0]" + System.lineSeparator() +
                "   maps to ----> [ME2162][Manufacturing Processes][4]" + System.lineSeparator() +
                "58. [M2794.012000][MECHANICAL STRENGTHS AND BEHAVIORS OF SOLIDS][0]" + System.lineSeparator() +
                "   maps to ----> [ME4255][Material Failure][4]" + System.lineSeparator() +
                "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
        ui.printPUModules(5);
        assertEquals("1. [EEE3314][INTRODUCTION ARTIFICIAL INTELLIGENCE][3]" + System.lineSeparator() +
                "   maps to ----> [ME3663][Technical Elective][4]" + System.lineSeparator() +
                "2. [MEU3710][NANO MECHANICAL ENGINEERING][3]" + System.lineSeparator() +
                "   maps to ----> [ME3662][Technical Elective][4]" + System.lineSeparator() +
                "3. [MEU3700][BIOMECHANICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME3661][Technical Elective][4]" + System.lineSeparator() +
                "4. [MEU3600][ADVANCED MECHANICS OF MATERIALS][3]" + System.lineSeparator() +
                "   maps to ----> [ME2114][Mechanics of Materials][4]" + System.lineSeparator() +
                "5. [MEU3680][MECHANICAL SYSTEM CONTROL][3]" + System.lineSeparator() +
                "   maps to ----> [ME2142][Feedback Control Systems][4]" + System.lineSeparator() +
                "6. [MEU3010][Micro Mechanical system][3]" + System.lineSeparator() +
                "   maps to ----> [ME3281][Microsystems Design and Applications][4]" + System.lineSeparator() +
                "7. [MEU5370][Finite Element Method][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "8. [MEU6230][VISCOUS FLUID DYNAMICS][3]" + System.lineSeparator() +
                "   maps to ----> [ME2135][Intermediate Fluid Mechanics][4]" + System.lineSeparator() +
                "9. [DAA3250][CHEM ENG THERMODYNAMICS I][0]" + System.lineSeparator() +
                "   maps to ----> [ME3221][Sustainable Energy Conversion][4]" + System.lineSeparator() +
                "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printPUList_correctLines_success() {
        UI ui = new UI();
        ui.printPUList();
        assertEquals("____________________________________________________________" +
                System.lineSeparator() +
                "1. KOREA UNIVERSITY KU" + System.lineSeparator() +
                "2. KOREA ADVANCED INSTITUTE OF SCIENCE & TECHNOLOGY KAIST" + System.lineSeparator() +
                "3. POHANG UNIVERSITY OF SCIENCE & TECHNOLOGY POSTECH" + System.lineSeparator() +
                "4. SEOUL NATIONAL UNIVERSITY SNU" + System.lineSeparator() +
                "5. YONSEI UNIVERSITY YU" + System.lineSeparator() +
                "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printCurrentModList_correctLines_success() {
        UI ui = new UI();
        ArrayList<Module> modules = new ArrayList<>();
        Module module1 = new Module(1, "AE320", "Aerodynamics II", 3,
                "ME4231", "Aerodynamics", 4);
        Module module2 = new Module(4, "M2794.0073", "Finite Element Analysis", 3,
                "ME4291", "Finite Element Analysis", 4);
        modules.add(module1);
        modules.add(module2);
        ui.printCurrentModList(modules);
        assertEquals(LIST_CURRENT_MESSAGE + System.lineSeparator()
                        + "____________________________________________________________" + System.lineSeparator() +
                        "1.[AE320][Aerodynamics II][3]" + System.lineSeparator() +
                "   maps to ----> [ME4231][Aerodynamics][4]" + System.lineSeparator() +
                "2.[M2794.0073][Finite Element Analysis][3]" + System.lineSeparator() +
                "   maps to ----> [ME4291][Finite Element Analysis][4]" + System.lineSeparator() +
                "____________________________________________________________".stripTrailing()
                , outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printInvalidInputMessage_correctLines_success() {
        UI ui = new UI();
        ui.printInvalidInputMessage();
        assertEquals("Invalid Input", outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printAddModuleFailureMessage_correctLines_success() {
        UI.printAddModuleFailureMessage();
        assertEquals(ADD_MOD_FAILURE_MESSAGE, outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printHelpCommandMessage_correctLines_success() {
        UI.printHelpCommandMessage();
        assertEquals("Here are the list of commands:\n"
                + "LIST PU                     : Provides the list of Partner Universities available\n"
                + "LIST [PU ABBRV]             : Provides the list of all modules available " +
                "in the specified Partner University\n"
                + "LIST [PU INDEX]             : Provides the list of all modules available " +
                "in the specified Partner University\n"
                + "                              by index of LIST PU\n"
                + "LIST CURRENT                : Provides the list of modules that the user has added to his/her " +
                "list of interest\n"
                + "LIST CURRENT [PU ABBRV]     : Provides the list of modules that user has added to his list of " +
                "list of interest for the specified PU\n"
                + "ADD [PU ABBRV]/[MODULE CODE]: Adds the specified module into user's current list of modules\n"
                + "REMOVE [INDEX]              : Removes the specified module by index from user's current list\n"
                + "EXIT                        : Exits the program\n\n"
                + System.lineSeparator()
                + READ_COMMAND_INPUT + System.lineSeparator()
                + LINE.stripTrailing(), outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printExceptionErrorMessage_correctLines_success() {
        InvalidCommandException testException1 = new InvalidCommandException("Test Message 1");
        UI.printExceptionErrorMessage(testException1);
        assertEquals("Test Message 1", outContent.toString().stripTrailing());
        outContent.reset();

        InvalidModuleException testException2 = new InvalidModuleException("Test Message 2");
        UI.printExceptionErrorMessage(testException2);
        assertEquals("Test Message 2", outContent.toString().stripTrailing());
        outContent.reset();

        InvalidPuException testException3 = new InvalidPuException("Test Message 3");
        UI.printExceptionErrorMessage(testException3);
        assertEquals("Test Message 3", outContent.toString().stripTrailing());
        outContent.reset();
    }

    @Test
    void printExitMessage_correctLines_success() {
        UI ui = new UI();
        ui.printExitMessage();
        assertEquals("Exiting program now", outContent.toString().stripTrailing());
        outContent.reset();
    }
}

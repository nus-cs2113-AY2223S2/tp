package seedu.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightUiTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream initialOutput = System.out;
    private int weight = 50;

    @BeforeEach
    public void getStream() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void resetStreams() {
        System.setOut(initialOutput);
    }

    @Test
    void requestWeight() {
        GeneralUi ui = new WeightUi();
        ui.requestWeight();
        assertEquals("Please enter your weight (in kg):", output.toString().trim());
    }

    @Test
    void showLatestWeight_input50_expect50() {
        GeneralUi ui = new WeightUi();
        ui.showLatestWeight(weight);
        assertEquals("This is the latest weight you have entered in: 50kg", output.toString().trim());
    }
}


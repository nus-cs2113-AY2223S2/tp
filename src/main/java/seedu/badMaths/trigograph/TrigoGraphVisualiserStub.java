package seedu.badMaths.trigograph;

import seedu.badMaths.ui.Ui;

import javax.swing.*;
import java.awt.*;

public class TrigoGraphVisualiserStub {
    private String trig;

    public TrigoGraphVisualiserStub(String trig) {
        this.trig = trig;
    }

    public boolean startVisualiser() {
        JFrame frame = new JFrame("Trigonometric Graph");
        try {
            switch (trig) {
            case ("sin"):
            case ("cos"):
            case ("tan"):
                return true;
            default:
                throw new IllegalStateException("Unexpected value: " + trig);
            }
        } catch (IllegalStateException e) {
            Ui.printIncorrectFormatEntered();
            return false;
        }
    }
}

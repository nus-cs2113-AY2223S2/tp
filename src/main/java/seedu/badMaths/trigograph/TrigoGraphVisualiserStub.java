package seedu.badMaths.trigograph;

import seedu.badMaths.ui.Ui;


import javax.swing.JFrame;

public class TrigoGraphVisualiserStub {
    private String trig;

    public TrigoGraphVisualiserStub(String trig) {
        this.trig = trig;
    }

    public boolean startVisualiser() {
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

package seedu.badMaths.trigograph;

import org.junit.jupiter.api.Test;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrigoGraphVisualiserTest {

    @Test
    public void InvalidTrigo_expect_exception(){
        TrigoGraphVisualiserStub test = new TrigoGraphVisualiserStub("tann");
        assertEquals(false,test.startVisualiser());
        
    }        
    
}
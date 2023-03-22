package seedu.badMaths.trigograph;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.lang.Math;



public class TrigoGraphVisualiser extends JPanel {
    private double amplitude;
    private double phase;
    private double freqInHz;
    private double verticalShift;
    private String trig;
    private double xMin;
    private double xMax;
    private final double yMin = -10;
    private final double yMax = 10;



    public TrigoGraphVisualiser(double amplitude, double phase, double freqInHz, double verticalShift, String trig) {
        this.amplitude = amplitude;
        this.trig = trig;
        this.phase = phase;
        this.freqInHz = freqInHz;
        this.verticalShift = verticalShift;
        xMin = -2 * Math.PI * freqInHz;
        xMax = 2 * Math.PI * freqInHz;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        int width = getWidth();
        int height = getHeight();

        // Calculate scaling factors
        double xScale = width / (xMax - xMin);
        double yScale = height / (yMax - yMin);

        // Translate to make origin at the center
        g.translate(width / 2, height / 2);

        createXAxis(g,xScale,yScale);
        createYAxis(g,xScale,yScale);

        switch (trig) {
            case ("sin"):
                drawSinCurve(g,xScale,yScale);
                break;
            case("cos"):
                drawCosCurve(g,xScale,yScale);
                break;
            case("tan"):
                drawTanCurve(g,xScale,yScale);
                break;
        }
    }

    public void createXAxis(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLACK);
        g.drawLine(0, (int) (yMin * yScale), 0, (int) (yMax * yScale));
    }

    public void createYAxis(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLACK);
        g.drawLine((int) (xMin * xScale), 0, (int) (xMax * xScale), 0);
    }

    public void drawSinCurve(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLUE);
        for (double x = xMin; x <= xMax; x += 0.01) {
            double y = amplitude * Math.sin(freqInHz*x)+verticalShift;
            int xPixel = (int) Math.round(x * xScale);
            int yPixel = (int) Math.round(-y * yScale);
            g.drawLine(xPixel - 1, yPixel, xPixel, yPixel);
        }
    }

    public void drawCosCurve(Graphics g, double xScale, double yScale) {
        g.setColor(Color.RED);
        for (double x = xMin; x <= xMax; x += 0.01) {
            double y = amplitude * Math.cos(freqInHz*x)+verticalShift;
            int xPixel = (int) Math.round(x * xScale);
            int yPixel = (int) Math.round(-y * yScale);
            g.drawLine(xPixel - 1, yPixel, xPixel, yPixel);
        }
    }

    public void drawTanCurve(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLACK);
        for (double x = xMin; x <= xMax; x += 0.01) {
            double y = amplitude * Math.tan(freqInHz*x)+verticalShift;
            int xPixel = (int) Math.round(x * xScale);
            int yPixel = (int) Math.round(-y * yScale);
            g.drawLine(xPixel - 1, yPixel, xPixel, yPixel);
        }
    }


    public void startVisualiser() {
        JFrame frame = new JFrame("Trigonometric Graph");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setSize(screenWidth/2, screenHeight/2);
        frame.setLocationRelativeTo(null);
        frame.add(new TrigoGraphVisualiser(amplitude,phase,freqInHz,verticalShift,trig));
        frame.setVisible(true);
    }

}

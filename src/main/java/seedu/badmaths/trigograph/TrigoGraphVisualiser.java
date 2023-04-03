package seedu.badmaths.trigograph;

import seedu.badmaths.ui.Ui;


import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TrigoGraphVisualiser extends JPanel {
    private static final double PRECISION_OF_GRAPH = 0.00001;
    private static final double START_TIME = 0.0;
    private static final int ZERO_X_COORDINATES = 0;
    private static final int ZERO_Y_COORDINATES = 0;
    private double amplitude;
    private double phase;
    private double freqInHz;
    private double verticalShift;
    private String trig;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;


    public TrigoGraphVisualiser(double amplitude, double phase, double freqInHz, double verticalShift, String trig) {
        this.amplitude = amplitude;
        this.trig = trig;
        this.phase = phase;
        this.freqInHz = freqInHz;
        this.verticalShift = verticalShift;
        xMin = (-2 * Math.PI) / freqInHz;
        xMax = (2 * Math.PI) / freqInHz;
        yMin = -(amplitude + Math.abs(verticalShift)) * 2;
        yMax = (amplitude + Math.abs(verticalShift)) * 2;
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
        createXAxis(g, xScale, yScale);
        createYAxis(g, xScale, yScale);
        labelOrigin(g, xScale, yScale);
        try {
            switch (trig) {
            case ("sin"):
                drawSinCurve(g, xScale, yScale);
                break;
            case ("cos"):
                drawCosCurve(g, xScale, yScale);
                break;
            case ("tan"):
                drawTanCurve(g, xScale, yScale);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + trig);
            }
        } catch (IllegalStateException e) {
            Ui.printIncorrectFormatEntered();
        }
    }

    public void labelOrigin(Graphics g, double xScale, double yScale) {
        g.drawString("0", ZERO_X_COORDINATES, ZERO_Y_COORDINATES);
    }

    public void createYAxis(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLACK);
        g.drawLine(ZERO_X_COORDINATES, (int) (yMin * yScale), ZERO_Y_COORDINATES, (int) (yMax * yScale));
        g.drawString("Amplitude", ZERO_X_COORDINATES, (int) ((yMin * yScale) * 0.9));
    }

    public void createXAxis(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLACK);
        g.drawLine((int) (xMin * xScale), ZERO_Y_COORDINATES, (int) (xMax * xScale), ZERO_Y_COORDINATES);
        g.drawString("time", (int) ((xMax * xScale) - 0.2 * xMax * xScale), ZERO_Y_COORDINATES);
    }

    public void drawSinCurve(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLUE);
        labelAmplitude(g, yScale);
        for (double x = START_TIME; x <= xMax; x += PRECISION_OF_GRAPH) {
            double y = amplitude * Math.sin(freqInHz * x + phase) + verticalShift;
            int xPixel = (int) Math.round(x * xScale);
            int yPixel = (int) Math.round(-y * yScale);
            g.drawLine(xPixel - 1, yPixel, xPixel, yPixel);
        }
    }

    public void labelAmplitude(Graphics g, double yScale) {
        g.drawString(String.valueOf(verticalShift + amplitude), ZERO_X_COORDINATES,
                (int) (Math.round(-(verticalShift + amplitude) * yScale)));
        g.drawString(String.valueOf(verticalShift - amplitude), ZERO_X_COORDINATES,
                (int) (Math.round(-(verticalShift - amplitude) * yScale)));
    }

    public void drawCosCurve(Graphics g, double xScale, double yScale) {
        g.setColor(Color.RED);
        labelAmplitude(g, yScale);
        for (double x = START_TIME; x <= xMax; x += PRECISION_OF_GRAPH) {
            double y = amplitude * Math.cos(freqInHz * x + phase) + verticalShift;
            int xPixel = (int) Math.round(x * xScale);
            int yPixel = (int) Math.round(-y * yScale);
            g.drawLine(xPixel - 1, yPixel, xPixel, yPixel);
        }
    }


    public void drawTanCurve(Graphics g, double xScale, double yScale) {
        g.setColor(Color.BLACK);
        for (double x = START_TIME; x <= xMax; x += PRECISION_OF_GRAPH) {
            double y = amplitude * Math.tan(freqInHz * x + phase) + verticalShift;
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
        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocationRelativeTo(null);
        frame.add(new TrigoGraphVisualiser(amplitude, phase, freqInHz, verticalShift, trig));
        frame.setVisible(true);
    }

}

package common;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WelcomeMessage {

    /**
     * This class is used to print the welcome message.
     * Since Expense Tracker is quite long, we print "ET" instead to represent it.
     */
    private static final String WORD = "E T";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    public static void printString(String toPrint) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Graphics2D graphics = (Graphics2D) g;
        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.drawString(toPrint, 5, 10);

        for (int y = 0; y < HEIGHT; y++) {
            StringBuilder stringbuilder = new StringBuilder();
            for (int x = 0; x < WIDTH; x++) {

                stringbuilder.append(image.getRGB(x, y) == -16777216 ? " " : "*");

            }
            if (stringbuilder.toString().trim().isEmpty()) {
                continue;
            }
            System.out.println(stringbuilder);
        }
    }

    public static void printWelcomeLogo() {
        printString(WORD);
        System.out.println();
    }

}

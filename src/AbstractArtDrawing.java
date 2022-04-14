import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * draws black lines including their intersecting points and middle points.
 */
public class AbstractArtDrawing {
    /**
     * starts the drawing of the abstract art.
     * @param args unused
     */
    public static void main(String[] args) {

        AbstractArtDrawing example = new AbstractArtDrawing();
        example.boardGui();
    }

    /**
     * creates a random line.
     *
     * @param width  we may choose size of board width when we call.
     * @param height we may choose size of board height when we call.
     * @return a new random line
     */
    public Line generateRandomLine(int width, int height) {
        Random rand = new Random(); // create a random-number generator
        int x1 = rand.nextInt(width) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(height) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(width) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(height) + 1; // get integer in range 1-300
        return new Line(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * creates drawing.
     * draws board, lines,red dots for intersection point and blur for middle pf the line.
     *
     * @return return gui- the drawing.
     */
    public GUI boardGui() {
        GUI gui = new GUI("board for lines", 400, 300);  //dictate the size of the board
        DrawSurface ds = gui.getDrawSurface();
        Line[] drawing = new Line[10];  //creating an array of 10 lines
        for (int i = 0; i < drawing.length; i++) {
            Line line = generateRandomLine(400, 300);  //line creation.
            drawing[i] = line;
            lineDraw(ds, line);
            intersectDraw(line, ds, drawing);
            middleDot(drawing[i], ds);

        }
        gui.show(ds);  //board print out.
        return gui;
    }

    /**
     * drawing the line function.
     *
     * @param ds   draw surface.
     * @param line the line we receive and draw.
     */
    public void lineDraw(final DrawSurface ds, final Line line) {

        ds.setColor(Color.BLACK);
        Point start = line.start();
        Point end = line.end();
        int startX = (int) start.getX();
        int startY = (int) start.getY();
        int endX = (int) end.getX();
        int endY = (int) end.getY();
        ds.drawLine(startX, startY, endX, endY);
    }

    private void drawDot(Color color, DrawSurface ds, Point mark) {
        ds.setColor(color);
        int r = 2;
        ds.fillCircle((int) mark.getX(), (int) mark.getY(), r);
    }

    private void intersectDraw(final Line addedLine, final DrawSurface ds, final Line[] c) {
        for (int i = 0; c[i] != addedLine; i++) {
            if (addedLine.isIntersecting(c[i])) {
                drawDot(Color.RED, ds, addedLine.intersectionWith(c[i]));
            }
        }
    }

    private void middleDot(final Line line, final DrawSurface ds) {
        drawDot(Color.BLUE, ds, line.middle());
    }
}
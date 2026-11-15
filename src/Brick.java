import acm.graphics.GRect;

import java.awt.*;

public class Brick {

    private GRect brick;

    public Brick (double x, double y, Color color) {
        brick = new GRect(x, y,
                Breakout.BRICK_WIDTH, Breakout.BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(color);
    }

    public GRect getBrick() {
        return brick;
    }
}

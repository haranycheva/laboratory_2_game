import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;

import java.awt.*;

public class Brick extends GCompound {

    public Brick (double x, double y, Color color) {
        GRect brick = new GRect(Breakout.BRICK_WIDTH, Breakout.BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(color);

        add(brick);
        setLocation(x, y);
    }

    public boolean isThisBrick(GObject object) {
        return object == this;
    }
}

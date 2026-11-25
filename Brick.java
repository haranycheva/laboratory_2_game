import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

import java.awt.*;

public class Brick extends GCompound {

    private int lives = 1;
    private GLabel livesLabel;


    public Brick (double x, double y, Color color) {
        GRect brick = new GRect(Breakout.BRICK_WIDTH, Breakout.BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(color);

        add(brick);
        setLocation(x, y);
    }

    public Brick(double x, double y, Color color, int lives) {
        this.lives = Math.max(1, lives);

        GRect brick = new GRect(Breakout.BRICK_WIDTH, Breakout.BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(color);

        livesLabel = new GLabel("");
        livesLabel.setColor(Color.WHITE);
        livesLabel.setFont(new Font("Minecraft", Font.PLAIN, 14));

        add(brick);
        add(livesLabel);

        updateLabel();

        setLocation(x, y);
    }

    private void updateLabel() {
        if(livesLabel == null) return;
        if (lives > 1) {
            livesLabel.setLabel(Integer.toString(lives));
            double labelX = (Breakout.BRICK_WIDTH - livesLabel.getWidth()) / 2.0;
            double labelY = (Breakout.BRICK_HEIGHT + livesLabel.getAscent()) / 2.0;
            livesLabel.setLocation(labelX, labelY);
        } else {
            livesLabel.setLabel("");
        }
    }

    public void hit() {
        if (lives > 0) {
            lives--;
            updateLabel();
        }
    }

    public boolean isDestroyed() {
        return lives <= 0;
    }

    public int getLives() {
        return lives;
    }

    public boolean isThisBrick(GObject object) {
        return object == this;
    }
}

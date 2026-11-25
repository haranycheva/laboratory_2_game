import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

import java.awt.*;

/** Цегла з кількістю життів; якщо життів більше одного, показує їх число. */
public class Brick extends GCompound {

    private int lives = 1;
    private GLabel livesLabel;


    /**
     * Цегла з одним життям.
     * @param x координата X лівого верхнього кута
     * @param y координата Y лівого верхнього кута
     * @param color колір цегли
     */
    public Brick (double x, double y, Color color) {
        GRect brick = new GRect(Breakout.BRICK_WIDTH, Breakout.BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(color);

        add(brick);
        setLocation(x, y);
    }

    /**
     * Цегла із заданою кількістю життів.
     * @param x координата X лівого верхнього кута
     * @param y координата Y лівого верхнього кута
     * @param color колір цегли
     * @param lives кількість життів (мінімум 1)
     */
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

    /** Оновлює число життів на цеглі. */
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

    /** Віднімає одне життя. */
    public void hit() {
        if (lives > 0) {
            lives--;
            updateLabel();
        }
    }

    /** @return true, якщо цегла зруйнована */
    public boolean isDestroyed() {
        return lives <= 0;
    }

    /** @return поточна кількість життів */
    public int getLives() {
        return lives;
    }
}

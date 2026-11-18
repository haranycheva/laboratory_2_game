import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

public class Paddle {

    private GRect paddle;
    private GOval leftCorner;
    private GOval rightCorner;

    private Breakout game;

    public Paddle (Breakout game, double x, double y) {
        this.game = game;

        paddle = new GRect(x, y,
                Breakout.PADDLE_WIDTH, Breakout.PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(new Color(0x383961));
        paddle.setColor(new Color(0x000000));

        game.addToBoard(paddle);
    }

    public void move(double x) {
        paddle.setLocation(x, paddle.getY());
    }

    public GRect getPaddle() {
        return paddle;
    }
}

import acm.graphics.GRect;

import java.awt.*;

public class Paddle {

    private GRect paddle;

    public Paddle (double x, double y) {
        paddle = new GRect(x, y,
                Breakout.PADDLE_WIDTH, Breakout.PADDLE_HEIGHT);
        paddle.setFilled(true);
    }

    public void move(double x) {
        paddle.setLocation(x, paddle.getY());
    }

    public GRect getPaddle() {
        return paddle;
    }
}

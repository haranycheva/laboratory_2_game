import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

/** Платформа гравця. */
public class Paddle {

    private GRect paddle;

    private Breakout game;

    /**
     * Створює платформу і додає її на поле.
     * @param game гра, на полі якої малюється платформа
     * @param x початкова координата X
     * @param y координата Y
     */
    public Paddle (Breakout game, double x, double y) {
        this.game = game;

        paddle = new GRect(x, y,
                Breakout.PADDLE_WIDTH, Breakout.PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(new Color(0x383961));
        paddle.setColor(new Color(0x000000));

        game.addToBoard(paddle);
    }

    /**
     * Рухає платформу по горизонталі.
     * @param x нова координата X
     */
    public void move(double x) {
        paddle.setLocation(x, paddle.getY());
    }

    /** @return графічний об'єкт платформи */
    public GRect getPaddle() {
        return paddle;
    }
}
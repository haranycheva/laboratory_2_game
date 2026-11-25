import acm.graphics.GObject;
import acm.util.RandomGenerator;

import java.awt.*;

/** Рівень 2: цегла з 1-3 життями та швидший м'яч. */
public class Level2 extends Level {

    private RandomGenerator rgen = RandomGenerator.getInstance();

    /** @param game гра, до якої належить рівень */
    public Level2(Breakout game) {
        super(game);
    }

    /** Створює цеглу з випадковою кількістю життів. */
    public void createBricks() {
        double totalRowWidth = Breakout.NBRICKS_PER_ROW * Breakout.BRICK_WIDTH + (Breakout.NBRICKS_PER_ROW - 1) * Breakout.BRICK_SEP;
        double startX = (Breakout.WIDTH - totalRowWidth) / 2.0;

        for (int i = 0; i < Breakout.NBRICK_ROWS; i++) { // рядки
            for (int j = 0; j < Breakout.NBRICKS_PER_ROW; j++) { // стовпці
                double x = startX + j * (Breakout.BRICK_WIDTH + Breakout.BRICK_SEP);
                int y = Breakout.BRICK_Y_OFFSET + i * (Breakout.BRICK_HEIGHT + Breakout.BRICK_SEP);

                Color color;

                if (i / 2 == 0) {
                    color = new Color(0x3D1F2B);
                } else if (i / 2 == 1) {
                    color = new Color(0x751F2C);
                } else if (i / 2 == 2) {
                    color = new Color(0xA21B32);
                } else if (i / 2 == 3) {
                    color = new Color(0xDB374A);
                } else {
                    color = new Color(0xEA7772);
                }

                int roll = rgen.nextInt(1, 100);
                int lives;

                if (roll <= 70) {
                    lives = 1;
                } else if (roll <= 85) {
                    lives = 2;
                } else {
                    lives = 3;
                }

                Brick brick = new Brick(x, y, color, lives);
                this.increaseBrickAmount();
                this.game.addToBoard(brick);
            }
        }
    }

    /**
     * Збільшує швидкість м'яча.
     * @param newBall м'яч, який налаштовуємо
     */
    @Override
    protected void configureBall(Ball newBall) {
        newBall.setVelocityX(4.5);
        newBall.setVelocityY(-5.5);
    }
}

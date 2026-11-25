/*
 * File: Level2.java
 * -------------------
 * Authors: Horoshko Yehor, Haranysheva Maria
 *
 */

import acm.graphics.GObject;
import acm.util.RandomGenerator;

import java.awt.*;

/** Рівень 3: цегла опускається вниз з часом, м'яч дуже швидкий. */
public class Level3 extends Level {
    private int shiftCounter = 0;
    private int shiftIntervalTicks = 50;
    private double shiftAmountY = 12.0;

    /** @param game гра, до якої належить рівень */
    public Level3(Breakout game) {
        super(game);
    }

    private RandomGenerator rgen = RandomGenerator.getInstance();

    /** Скидає лічильник зсуву. */
    @Override
    public void setup() {
        super.setup();
        shiftCounter = 0;
    }

    /** Раз на кілька тіків зсуває цеглу вниз. */
    @Override
    public void onTick() {
        shiftCounter++;
        if (shiftCounter >= shiftIntervalTicks) {
            shiftCounter = 0;
            shiftBricksDown();
        }
    }

    /** Створює новий м'яч над платформою. */
    @Override
    public void spawnBall() {
        if (ball != null) {
            game.remove(ball);
        }
        ball = new Ball(game,Breakout.WIDTH / 2.0, Breakout.HEIGHT / 2.0 + 100);
        configureBall(ball);
        game.addToBoard(ball);
    }

    /** Зсуває цеглу в кожній колонці, не нижче за платформу. */
    private void shiftBricksDown() {
        double totalRowWidth = Breakout.NBRICKS_PER_ROW * Breakout.BRICK_WIDTH
                + (Breakout.NBRICKS_PER_ROW - 1) * Breakout.BRICK_SEP;
        double startX = (Breakout.WIDTH - totalRowWidth) / 2.0;
        double stepX = Breakout.BRICK_WIDTH + Breakout.BRICK_SEP;

        double paddleLimitY = paddle.getPaddle().getY() - Breakout.BRICK_HEIGHT - 25;

        for (int col = 0; col < Breakout.NBRICKS_PER_ROW; col++) {
            double sampleX = startX + col * stepX + Breakout.BRICK_WIDTH / 2.0;
            double lowestAllowedY = paddleLimitY;
            Brick lastBrick = null;

            for (int yScan = (int) paddleLimitY + Breakout.BRICK_HEIGHT; yScan >= 72; yScan -= 3) {
                GObject obj = game.getElementAt(sampleX, yScan);
                if (!(obj instanceof Brick)) {
                    continue;
                }

                Brick b = (Brick) obj;
                if (b == lastBrick) {
                    continue;
                }
                lastBrick = b;

                double desiredY = b.getY() + shiftAmountY;
                double newY = Math.min(desiredY, lowestAllowedY);

                if (newY > b.getY()) {
                    b.setLocation(b.getX(), newY);
                }
                lowestAllowedY = b.getY() - Breakout.BRICK_HEIGHT - Breakout.BRICK_SEP;
            }
        }
    }

    /** Створює цеглу з випадковою кількістю життів (до 5). */
    public void createBricks() {
        double totalRowWidth = Breakout.NBRICKS_PER_ROW * Breakout.BRICK_WIDTH + (Breakout.NBRICKS_PER_ROW - 1) * Breakout.BRICK_SEP;
        double startX = (Breakout.WIDTH - totalRowWidth) / 2.0;

        for (int i = 0; i < Breakout.NBRICK_ROWS; i++) {
            for (int j = 0; j < Breakout.NBRICKS_PER_ROW; j++) {
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
                } else {
                    lives = rgen.nextInt(1, 5);
                }

                Brick brick = new Brick(x, y, color, lives);
                this.increaseBrickAmount();
                this.game.addToBoard(brick);
            }
        }
    }

    /**
     * Ставить найвищу швидкість м'яча.
     * @param newBall м'яч, який налаштовуємо
     */
    @Override
    public void configureBall(Ball newBall) {
        newBall.setVelocityX(7.5);
        newBall.setVelocityY(-8.5);
    }
}
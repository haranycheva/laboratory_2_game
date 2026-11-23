import acm.graphics.GObject;
import acm.util.RandomGenerator;

import java.awt.*;

public class Level2 extends Level {

    private RandomGenerator rgen = RandomGenerator.getInstance();

    public Level2(Breakout game) {
        super(game);
    }

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

    protected boolean isBrick(GObject object) {
        double totalRowWidth = Breakout.NBRICKS_PER_ROW * Breakout.BRICK_WIDTH + (Breakout.NBRICKS_PER_ROW - 1) * Breakout.BRICK_SEP;
        double startX = (Breakout.WIDTH - totalRowWidth) / 2.0;

        for (int i = 0; i < Breakout.NBRICK_ROWS; i++) { // рядки
            for (int j = 0; j < Breakout.NBRICKS_PER_ROW; j++) { // стовпці
                double x = startX + j * (Breakout.BRICK_WIDTH + Breakout.BRICK_SEP);
                int y = Breakout.BRICK_Y_OFFSET + i * (Breakout.BRICK_HEIGHT + Breakout.BRICK_SEP);

                if (Math.abs(object.getX() - x) < 0.001
                        && Math.abs(object.getY() - y) < 0.001) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void configureBall(Ball newBall) {
        newBall.setVelocityX(4.5);
        newBall.setVelocityY(-5.5);
    }
}

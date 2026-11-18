import acm.graphics.GRect;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class Level1 {

    private Breakout game;

    public Level1(Breakout game) {
        this.game = game;
    }

    public void createBackground() {
        GRect background = new GRect(0,0 , Breakout.APPLICATION_WIDTH, Breakout.APPLICATION_HEIGHT);
        Color back = new Color(0xDBDFAC);
        background.setFilled(true);
        background.setFillColor(back);
        game.addToBoard(background);
    }

    public void createBricks() {
        double totalRowWidth = Breakout.NBRICKS_PER_ROW * Breakout.BRICK_WIDTH + (Breakout.NBRICKS_PER_ROW - 1) * Breakout.BRICK_SEP;
        double startX = (Breakout.WIDTH - totalRowWidth)/ 2.0;

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
                } else  {
                    color = new Color(0xEA7772);
                }

                Brick brick = new Brick(x, y, color);

                game.addToBoard(brick.getBrick());
            }
        }
    }
}

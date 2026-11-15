import java.awt.*;

public class Level1 {

    private Breakout game;

    public Level1(Breakout game) {
        this.game = game;
    }

    public void createBricks() {
        for (int i = 0; i < Breakout.NBRICK_ROWS; i++) { // рядки
            for (int j = 0; j < Breakout.NBRICKS_PER_ROW; j++) { // стовпці
                int x = j * (Breakout.BRICK_WIDTH + Breakout.BRICK_SEP) + 1;
                int y = i * (Breakout.BRICK_HEIGHT + Breakout.BRICK_SEP) + Breakout.BRICK_Y_OFFSET;

                Color color;

                if (i / 2 == 0) {
                    color = Color.RED;
                } else if (i / 2 == 1) {
                    color = Color.ORANGE;
                } else if (i / 2 == 2) {
                    color = Color.YELLOW;
                } else if (i / 2 == 3) {
                    color = Color.GREEN;
                } else  {
                    color = Color.CYAN;
                }

                Brick brick = new Brick(x, y, color);

                game.addToBoard(brick.getBrick());
            }
        }
    }
}

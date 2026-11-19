import acm.graphics.*;
import java.awt.Color;

public class Level {
    protected Breakout game;
    protected Ball ball;
    protected Paddle paddle;
    protected GRect background;

    public Level(Breakout game) {
        this.game = game;
    }

    public void setup() {
        createBackground();
        createPaddle();
        createBricks();
        spawnBall();
    }

    public void createBricks() {
    }

    public void play() {
        boolean isRunning = true;
        while (isRunning) {
            int boardWidth = (game.getWidth() > 0) ? game.getWidth() : Breakout.WIDTH;
            ball.moveAndBounceWalls(boardWidth);

            if (ball.isHitBottom(Breakout.HEIGHT)) {
                game.remove(ball);
                ball = null;
                isRunning = false;
                gameOver();
            } else {
                checkCollisions();
            }

            game.pause(20);
        }
    }

    public void movePaddle(int mouseX) {
        if (paddle == null) return;
        int x = mouseX - Breakout.PADDLE_WIDTH / 2;
        if (x < 0) {
            x = 0;
        }
        int boardWidth = (game.getWidth() > 0) ? game.getWidth() : Breakout.WIDTH;
        if (x + Breakout.PADDLE_WIDTH > boardWidth) {
            x = boardWidth - Breakout.PADDLE_WIDTH;
        }

        paddle.move(x);
    }

    private void createBackground() {
        background = new GRect(0, 0, Breakout.APPLICATION_WIDTH, Breakout.APPLICATION_HEIGHT);
        background.setFilled(true);
        background.setFillColor(new Color(0xDBDFAC));
        game.addToBoard(background);
    }



    private void createPaddle() {
        double x = (game.getWidth() - Breakout.PADDLE_WIDTH) / 2.0;
        double y = game.getHeight() - Breakout.PADDLE_HEIGHT - Breakout.PADDLE_Y_OFFSET;
        paddle = new Paddle(game, x, y);
    }

    protected void spawnBall() {
        if (ball != null) {
            game.remove(ball);
        }
        ball = new Ball(Breakout.WIDTH / 2.0, Breakout.HEIGHT / 2.0);
        game.addToBoard(ball);
    }

    protected void checkCollisions() {
        if (ball == null) return;

        double bx = ball.getX();
        double by = ball.getY();
        double r = ball.getRadius();
        GObject paddleObj = paddle.getPaddle();

        GObject bottomObj = game.getElementAt(bx, by + r + 1);
        if (bottomObj != null && bottomObj != ball && bottomObj != background) {
            if (bottomObj == paddleObj) {
                ball.setLocation(bx, paddleObj.getY() - r - 1);
            } else {
                game.remove(bottomObj);
            }
            ball.bounceY();
            return;
        }

        GObject topObj = game.getElementAt(bx, by - r - 1);
        if (topObj != null && topObj != ball && topObj != background) {
            if (topObj != paddleObj) game.remove(topObj);
            ball.bounceY();
            return;
        }

        GObject leftObj = game.getElementAt(bx - r - 1, by);
        if (leftObj != null && leftObj != ball && leftObj != background) {
            if (leftObj != paddleObj) game.remove(leftObj);
            ball.bounceX();
            return;
        }

        GObject rightObj = game.getElementAt(bx + r + 1, by);
        if (rightObj != null && rightObj != ball && rightObj != background) {
            if (rightObj != paddleObj) game.remove(rightObj);
            ball.bounceX();
        }
    }

    public void gameOver() {
    }
    

}
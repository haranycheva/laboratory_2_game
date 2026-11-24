import acm.graphics.*;
import acm.util.RandomGenerator;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Color;
import java.io.File;

public class Level {
    protected Breakout game;
    protected Ball ball;
    protected Ball secondBall;
    protected Ball thirdBall;
    protected Paddle paddle;
    protected GRect background;
    protected GameStats stats;
    protected GRect statsWall;

    protected int bricksRemaining = 0;
    private boolean didWin = false;
    private boolean multiBallEnabled = true;

    private Clip hitSound;
    private Clip levelMusic;
    private Clip gameOverSound;
    private Clip gameWinSound;
    private Clip lifeLostSound;

    private RandomGenerator rgen = RandomGenerator.getInstance();

    public Level(Breakout game) {
        this.game = game;
    }

    public Level(Breakout game, boolean multiBallEnabled) {
        this.game = game;
        this.multiBallEnabled = multiBallEnabled;
    }

    public void setup() {
        loadSounds();
        bricksRemaining = 0;
        didWin = false;
        createBackground();
        stats = new GameStats(game);
        stats.drawLives();
        stats.drawScore();
        createStatsWall();
        createPaddle();
        createBricks();
        spawnBall();
    }

    private void loadSounds(){
        hitSound = loadSingleSound("brickHit.wav");
        levelMusic = loadSingleSound("shreksophone.wav");
        gameOverSound = loadSingleSound("gameOver.wav");
        gameWinSound = loadSingleSound("gameWin.wav");
        lifeLostSound = loadSingleSound("lifeLost.wav");
    }

    public void increaseBrickAmount(){
        bricksRemaining+=1;
    }

    private Clip loadSingleSound(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) return null;
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void playSingleSound(Clip clip) {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    protected void startLevelMusic() {
        if (levelMusic != null && !levelMusic.isRunning()) {
            levelMusic.setFramePosition(0);
            levelMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    protected void stopLevelMusic() {
        if (levelMusic != null) {
            if (levelMusic.isRunning()) {
                levelMusic.stop();
            }
            levelMusic.close();
            levelMusic = null;
        }
    }

    protected void onBrickDestroyed(GObject obj) {
        if (!(obj instanceof Brick)) return;
        Brick brick = (Brick) obj;
        brick.hit();
        playSingleSound(hitSound);
        if (!brick.isDestroyed()) {
            return;
        }

        game.remove(obj);
        bricksRemaining--;
        if (multiBallEnabled && rgen.nextInt(1,5) == 1) {
            if (secondBall == null) {
                secondBall = new Ball(game, Breakout.WIDTH / 2.0, Breakout.HEIGHT / 2.0);
                secondBall.setColor(Color.RED);
                configureBall(secondBall);
                game.addToBoard(secondBall);
            } else if (thirdBall == null) {
                thirdBall = new Ball(game, Breakout.WIDTH / 2.0, Breakout.HEIGHT / 2.0);
                thirdBall.setColor(Color.RED);
                configureBall(thirdBall);
                game.addToBoard(thirdBall);
            }
        }
        if (bricksRemaining <= 0) {
            bricksRemaining = 0;
            didWin = true;
        }
        stats.increaseScore();
    }

    public boolean didWin() {
        return didWin;
    }

    public void createBricks() {
    }


    protected void createStatsWall() {
        statsWall = new GRect(0, 65, Breakout.APPLICATION_WIDTH, 5);
        statsWall.setFilled(true);
        statsWall.setFillColor(Color.BLACK);

        game.addToBoard(statsWall);
    }

    public void play() {
        startLevelMusic();
        boolean isRunning = true;
        while (isRunning) {
            int boardWidth = (game.getWidth() > 0) ? game.getWidth() : Breakout.WIDTH;

            if (ball != null) {
                ball.moveAndBounceWalls(boardWidth);
                if (ball.isHitBottom(Breakout.HEIGHT)) {
                    game.remove(ball);
                    ball.clearTrail();
                    ball = null;
                } else {
                    checkCollisions(ball);
                }
            }

            if (secondBall != null) {
                secondBall.moveAndBounceWalls(boardWidth);
                if (secondBall.isHitBottom(Breakout.HEIGHT)) {
                    game.remove(secondBall);
                    secondBall.clearTrail();
                    secondBall = null;
                } else {
                    checkCollisions(secondBall);
                }
            }

            if (thirdBall != null) {
                thirdBall.moveAndBounceWalls(boardWidth);
                if (thirdBall.isHitBottom(Breakout.HEIGHT)) {
                    game.remove(thirdBall);
                    thirdBall.clearTrail();
                    thirdBall = null;
                } else {
                    checkCollisions(thirdBall);
                }
            }

            if (didWin) {
                removeAllBalls();
                isRunning = false;
                gameWon();
                break;
            }

            if (ball == null && secondBall == null && thirdBall == null) {
                if (stats.decreaseLives()) {
                    isRunning = false;
                    gameOver();
                } else {
                    playSingleSound(lifeLostSound);
                    spawnBall();
                }
            }
            onTick();
            game.pause(20);
        }
    }

    private void removeAllBalls() {
        if (ball != null) {
            game.remove(ball);
            ball.clearTrail();
            ball = null;
        }
        if (secondBall != null) {
            game.remove(secondBall);
            secondBall.clearTrail();
            secondBall = null;
        }
        if (thirdBall != null) {
            game.remove(thirdBall);
            thirdBall.clearTrail();
            thirdBall = null;
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
        ball = new Ball(game,Breakout.WIDTH / 2.0, Breakout.HEIGHT / 2.0);
        configureBall(ball);
        game.addToBoard(ball);
    }

    protected boolean isBrick(GObject object) {
        return object instanceof  Brick;
    }

    protected void checkCollisions(Ball currentBall) {
        if (currentBall == null) return;

        double bx = currentBall.getX();
        double by = currentBall.getY();
        double r = currentBall.getRadius();
        GObject paddleObj = paddle.getPaddle();

        GObject bottomObj = game.getElementAt(bx, by + r + 1);
        if (bottomObj != null && (bottomObj == paddleObj || isBrick(bottomObj))) {
            if (bottomObj == paddleObj) {
                currentBall.setLocation(bx, paddleObj.getY() - r - 1);
            } else {
                onBrickDestroyed(bottomObj);
                if (bottomObj.getParent() != null) {
                    currentBall.setLocation(bx, bottomObj.getY() - r - 1);
                }
            }
            currentBall.bounceY();
            return;
        }

        GObject topObj = game.getElementAt(bx, by - r - 1);
        if (topObj != null && (topObj == statsWall || isBrick(topObj))) {
            if (isBrick(topObj)) {
                onBrickDestroyed(topObj);
                if (topObj.getParent() != null) {
                    currentBall.setLocation(bx, topObj.getY() + topObj.getHeight() + r + 1);
                }
            }
            currentBall.bounceY();
            return;
        }

        GObject leftObj = game.getElementAt(bx - r - 1, by);
        if (leftObj != null && leftObj != currentBall && leftObj != background) {
            if (isBrick(leftObj)) {
                onBrickDestroyed(leftObj);
                if (leftObj.getParent() != null) {
                    currentBall.setLocation(leftObj.getX() - r - 1, by);
                }
            }
            currentBall.bounceX();
            return;
        }

        GObject rightObj = game.getElementAt(bx + r + 1, by);
        if (rightObj != null && rightObj != currentBall && rightObj != background) {
            if (isBrick(rightObj)) {
                onBrickDestroyed(rightObj);
                if (rightObj.getParent() != null) {
                    currentBall.setLocation(rightObj.getX() + rightObj.getWidth() + r + 1, by);
                }
            }
            currentBall.bounceX();
        }
    }

    protected void configureBall(Ball newball) {

    }

    public void gameOver() {
        stopLevelMusic();
        playSingleSound(gameOverSound);
        game.setGameOver(true);

        GImage gameOverImage = new GImage("gameOver.png");
        gameOverImage.setSize(300, 90);

        double x = (Breakout.APPLICATION_WIDTH - 300) / 2.0;
        double y = (Breakout.APPLICATION_HEIGHT - 150) / 2.0;

        gameOverImage.setLocation(x, y);
        game.addToBoard(gameOverImage);
    }

    public void gameWon(){
        stopLevelMusic();
        playSingleSound(gameWinSound);
        game.setGameOver(true);

        GImage gameWinImage = new GImage("gameWin.png");
        gameWinImage.setSize(300, 90);

        double x = (Breakout.APPLICATION_WIDTH - 300) / 2.0;
        double y = (Breakout.APPLICATION_HEIGHT - 150) / 2.0;

        gameWinImage.setLocation(x, y);
        game.addToBoard(gameWinImage);
    }

    public void onTick() {
    }

}
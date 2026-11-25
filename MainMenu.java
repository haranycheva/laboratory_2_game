import acm.graphics.GImage;
import acm.graphics.GRect;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class MainMenu {
    private static final int TOP_BRICKS_Y = 100;
    private static final int BOTTOM_BRICKS_Y = 200;
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_GAP = 4;

    private Breakout game;
    private LevelButton level1;
    private LevelButton level2;
    private LevelButton level3;

    public MainMenu(Breakout game) {
        this.game = game;
    }

    public void createBricks() {
        int width = (Breakout.APPLICATION_WIDTH - 20 - 3 * 4) / 5;
        int totalWidth = 4 * width + 3 * BRICK_GAP;
        int startX = (Breakout.APPLICATION_WIDTH - totalWidth) / 2;
        Color bricks = new Color(0x3B1F2B);

        for (int i = 0; i < 4; i++) {
            int x = startX + i * (width + BRICK_GAP);

            Color brickColor;
            if (i == 0) {
                brickColor = new Color(0x3B1F2B);
            } else if (i == 1) {
                brickColor = new Color(0x7A1C2D);
            } else if (i == 2) {
                brickColor = new Color(0xB81D33);
            } else {
                brickColor = new Color(0xDB3A48);
            }

            GRect topBrick = new GRect(x, TOP_BRICKS_Y, width, BRICK_HEIGHT);
            topBrick.setFilled(true);
            topBrick.setFillColor(brickColor);
            topBrick.setColor(bricks);

            GRect bottomBrick = new GRect(x, BOTTOM_BRICKS_Y, width, BRICK_HEIGHT);
            bottomBrick.setFilled(true);
            bottomBrick.setFillColor(brickColor);
            bottomBrick.setColor(bricks);

            game.addToBoard(topBrick);
            game.addToBoard(bottomBrick);
        }
    }

    public void createTitle() {
        GImage title = new GImage("breakout.png");
        int brickWidth = (Breakout.APPLICATION_WIDTH - 20 - 3 * BRICK_GAP) / 4;
        double bricksTotalWidth = 4 * brickWidth + 3 * BRICK_GAP;

        double availableHeight = (BOTTOM_BRICKS_Y - (TOP_BRICKS_Y + BRICK_HEIGHT)) - 10;

        double scaleX = bricksTotalWidth / title.getWidth();
        double scaleY = availableHeight / title.getHeight();
        double scale = Math.min(scaleX, scaleY);

        title.scale(scale);

        double x = (Breakout.APPLICATION_WIDTH - title.getWidth()) / 2;
        double middleY = (TOP_BRICKS_Y + BRICK_HEIGHT + BOTTOM_BRICKS_Y) / 2.0;
        double y = middleY - title.getHeight() / 2;
        title.setLocation(x, y);

        game.addToBoard(title);
    }

    public void createButtons() {
        int levelX = (Breakout.APPLICATION_WIDTH - 200) / 2;
        int levelY = 300;
        int gap = 30;
        level1 = new LevelButton(game, levelX, levelY, "LEVEL 1", new Color(0x391F28));
        level2 = new LevelButton(game, levelX, levelY + 45 + gap, "LEVEL 2", new Color(0x761D2F));
        level3 = new LevelButton(game, levelX, levelY + 2 * (45 + gap), "LEVEL 3", new Color(0xA51B33));
        level1.addToBoard();
        level2.addToBoard();
        level3.addToBoard();
    }

    public void createBackground() {
        GRect background = new GRect(0,0 , Breakout.APPLICATION_WIDTH, Breakout.APPLICATION_HEIGHT);
        Color back = new Color(0xDBDFAC);
        background.setFilled(true);
        background.setFillColor(back);
        game.addToBoard(background);
    }

    public void playMenuMusic()  {
        try {
            File file = new File("mainMenuIntro.wav");
            menuMusic = AudioSystem.getClip();
            menuMusic.open(AudioSystem.getAudioInputStream(file));
            menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopMainMenuMusic() {
        if (menuMusic != null) {
            menuMusic.stop();
        }
    }

    public boolean isLevel1Clicked(double x, double y) {
        if (level1.contains(x, y)) {
            stopMainMenuMusic();
            return true;
        }
        return false;
    }

    public boolean isLevel2Clicked(double x, double y) {
        if (level2.contains(x, y)) {
            stopMainMenuMusic();
            return true;
        }
        return false;
    }

    public boolean isLevel3Clicked(double x, double y) {
        if (level3.contains(x, y)) {
            stopMainMenuMusic();
            return true;
        }
        return false;
    }

    private Clip menuMusic;
}

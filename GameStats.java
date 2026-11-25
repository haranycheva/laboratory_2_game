import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

public class GameStats {
    private Breakout game;
    private int score = 0;
    private int lives = 3;
    private GImage heart;
    private GLabel livesLabel;
    private GLabel scoreLabel;

    public GameStats(Breakout game) {
        this.game = game;
    }

    public void drawLives() {
        heart = new GImage("heart.png", Breakout.APPLICATION_WIDTH - 100, 10);
        heart.setSize(50,50);
        game.addToBoard(heart);

        livesLabel = new GLabel("x " + lives);
        livesLabel.setLocation(Breakout.APPLICATION_WIDTH - 50, 50);
        livesLabel.setFont(new Font("Minecraft", Font.PLAIN, 28));
        game.addToBoard(livesLabel);
    }

    public void drawScore() {
        scoreLabel = new GLabel("SCORE: " + score);
        scoreLabel.setLocation(10, 50);
        scoreLabel.setFont(new Font("Minecraft", Font.PLAIN, 28));
        game.addToBoard(scoreLabel);
    }

    public void increaseScore() {
        score += 10;
        scoreLabel.setLabel("SCORE: " + score);
    }

    public boolean decreaseLives() {
        lives--;
        livesLabel.setLabel("x " + lives);
        return lives == 0;
    }
}

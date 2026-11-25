/*
 * File: GameStats.java
 * -------------------
 * Authors: Horoshko Yehor, Haranysheva Maria
 *
 */

import acm.graphics.GImage;
import acm.graphics.GLabel;

import java.awt.*;

/** Рахунок і життя гравця та їх відображення зверху поля. */
public class GameStats {
    private Breakout game;
    private int score = 0;
    private int lives = 3;
    private GImage heart;
    private GLabel livesLabel;
    private GLabel scoreLabel;

    /** @param game гра, на полі якої малюється статистика */
    public GameStats(Breakout game) {
        this.game = game;
    }

    /** Малює сердечко і кількість життів. */
    public void drawLives() {
        heart = new GImage("heart.png", Breakout.APPLICATION_WIDTH - 100, 10);
        heart.setSize(50,50);
        game.addToBoard(heart);

        livesLabel = new GLabel("x " + lives);
        livesLabel.setLocation(Breakout.APPLICATION_WIDTH - 50, 50);
        livesLabel.setFont(new Font("Minecraft", Font.PLAIN, 28));
        game.addToBoard(livesLabel);
    }

    /** Малює рахунок. */
    public void drawScore() {
        scoreLabel = new GLabel("SCORE: " + score);
        scoreLabel.setLocation(10, 50);
        scoreLabel.setFont(new Font("Minecraft", Font.PLAIN, 28));
        game.addToBoard(scoreLabel);
    }

    /** Додає 10 очок. */
    public void increaseScore() {
        score += 10;
        scoreLabel.setLabel("SCORE: " + score);
    }

    /**
     * Віднімає життя.
     * @return true, якщо життя закінчились
     */
    public boolean decreaseLives() {
        lives--;
        livesLabel.setLabel("x " + lives);
        return lives == 0;
    }
}
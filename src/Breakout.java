/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 *
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.event.*;

/** Головний клас гри: вікно, головне меню та запуск рівнів. */
public class Breakout extends GraphicsProgram {
    public static void main(String[] args) {
        new Breakout().start();
    }

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 700;

    /** Dimensions of game board (usually the same) */
    public static final int WIDTH = APPLICATION_WIDTH;
    public static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    public static final int PADDLE_WIDTH = 80;
    public static final int PADDLE_HEIGHT = 20;

    /** Offset of the paddle up from the bottom */
    public static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    public static final int NBRICKS_PER_ROW = 15;

    /** Number of rows of bricks */
    public static final int NBRICK_ROWS = 5;

    /** Separation between bricks */
    public static final int BRICK_SEP = 10;

    /** Width of a brick */
    public static final int BRICK_WIDTH =
            (WIDTH - BRICK_SEP - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    public static final int BRICK_HEIGHT = 15;

    /** Radius of the ball in pixels */
    public static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    public static final int BRICK_Y_OFFSET = 110;


    /** Показує меню і чекає, поки гравець обере рівень, потім запускає його. */
    public void run() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        addMouseListeners();
        while (true) {
            mainMenu();
            while (selectedLevel == null) {
                pause(20);
            }

            currentLevel = selectedLevel;
            selectedLevel = null;

            removeAll();
            currentLevel.setup();
            currentLevel.play();

            if (gameOver) {
                pause(3000);
            }

            currentLevel = null;
        }
    }

    /** Створює і малює головне меню. */
    private void mainMenu() {
        mainMenu = new MainMenu(this);
        mainMenu.createBackground();
        mainMenu.createBricks();
        mainMenu.createTitle();
        mainMenu.createButtons();
        mainMenu.playMenuMusic();
    }

    /**
     * Додає об'єкт на ігрове поле.
     * @param object об'єкт, який додаємо
     */
    public void addToBoard(GObject object) {
        add(object);
    }

    /**
     * Обробляє клік по кнопках рівнів у меню.
     * @param e подія кліку миші
     */
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (mainMenu.isLevel1Clicked(x, y)) {
            selectedLevel = new Level1(this);
        }

        if (mainMenu.isLevel2Clicked(x, y)) {
            selectedLevel = new Level2(this);
        }

        if (mainMenu.isLevel3Clicked(x, y)) {
            selectedLevel = new Level3(this);
        }
    }

    /**
     * Рухає платформу за мишею.
     * @param e подія руху миші
     */
    public void mouseMoved(MouseEvent e) {
        if (currentLevel != null) {
            currentLevel.movePaddle(e.getX());
        }
    }

    /**
     * Позначає, що гра закінчилась (виграш або програш).
     * @param gameOver true, якщо гра закінчилась
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    private boolean gameOver = false;
    private MainMenu mainMenu;
    private Level currentLevel;
    private Level selectedLevel;
}
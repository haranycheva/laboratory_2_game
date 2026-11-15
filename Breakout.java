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
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
    public static void main(String[] args) {
        new Breakout().start();
    }

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    public static final int WIDTH = APPLICATION_WIDTH;
    public static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    public static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    public static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    public static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    public static final int BRICK_SEP = 4;

    /** Width of a brick */
    public static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    public static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    public static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    public static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    public static final int NTURNS = 3;

    /* Method: run() */
    /** Runs the Breakout program. */
    public void run() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        addMouseListeners();

        Level1 level1 = new Level1(this);
        level1.createBricks();

        drawPaddle();
    }

    public void addToBoard(GObject object) {
        add(object);
    }

    private void drawPaddle() {
        int x = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
        int y = APPLICATION_HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET;

        paddle = new Paddle(x, y);
        add(paddle.getPaddle());
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX() - Breakout.PADDLE_WIDTH / 2;

        if (x < 0) {
            x = 0;
        }

        if (x + Breakout.PADDLE_WIDTH > Breakout.APPLICATION_WIDTH) {
            x = Breakout.APPLICATION_WIDTH - Breakout.PADDLE_WIDTH;
        }

        paddle.move(x);
    }

    private Paddle paddle;
}
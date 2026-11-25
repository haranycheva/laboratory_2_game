import acm.graphics.*;
import java.awt.Color;

public class Ball extends GCompound {
    private double velocityX = 3.0;
    private double velocityY = -4.0;
    private double radius = Breakout.BALL_RADIUS;
    private GOval oval;
    private double previousX;
    private double previousY;
    private GLine segment1;
    private GLine segment2;
    private GLine segment3;
    private GLine segment4;
    private GLine segment5;
    private GLine segment6;
    private GLine segment7;
    private GLine segment8;
    private GLine segment9;
    private GLine segment10;
    private GLine segment11;
    private GLine segment12;
    private GLine segment13;
    private GLine segment14;
    private GLine segment15;
    private GLine segment16;


    private Breakout game;

    public Ball(Breakout game, double x, double y) {
        this.game = game;
        double diameter = radius * 2.0;
        oval = new GOval(diameter, diameter);
        oval.setFilled(true);
        oval.setColor(Color.BLACK);
        oval.setFillColor(Color.BLACK);

        add(oval, -radius, -radius);
        setLocation(x, y);
        previousX = x;
        previousY = y;
    }

    public void moveAndBounceWalls(double width) {
        move(velocityX, velocityY);
        double x = getX();
        double y = getY();

        if (x - radius <= 0.0) {
            setLocation(radius, y);
            bounceX();
        } else if (x + radius >= width) {
            setLocation(width - radius, y);
            bounceX();
        }
        if (y - radius <= 0.0) {
            setLocation(getX(), radius);
            bounceY();
        }

        double currentX = getX();
        double currentY = getY();

        GLine trail = new GLine(previousX, previousY, currentX, currentY);

        if (segment16 != null) {
            game.remove(segment16);
        }
        segment16 = segment15;
        segment15 = segment14;
        segment14 = segment13;
        segment13 = segment12;
        segment12 = segment11;
        segment11 = segment10;
        segment10 = segment9;
        segment9 = segment8;
        segment8 = segment7;
        segment7 = segment6;
        segment6 = segment5;
        segment5 = segment4;
        segment4 = segment3;
        segment3 = segment2;
        segment2 = segment1;
        segment1 = trail;

        // game.addToBoard(trail);
        previousX = currentX;
        previousY = currentY;
    }

    public void clearTrail() {
        if (segment1 != null) {
            game.remove(segment1);
            segment1 = null;
        }

        if  (segment2 != null) {
            game.remove(segment2);
            segment2 = null;
        }

        if  (segment3 != null) {
            game.remove(segment3);
            segment3 = null;
        }

        if  (segment4 != null) {
            game.remove(segment4);
            segment4 = null;
        }

        if  (segment5 != null) {
            game.remove(segment5);
            segment5 = null;
        }

        if  (segment6 != null) {
            game.remove(segment6);
            segment6 = null;
        }

        if  (segment7 != null) {
            game.remove(segment7);
            segment7 = null;
        }

        if  (segment8 != null) {
            game.remove(segment8);
            segment8 = null;
        }

        if  (segment9 != null) {
            game.remove(segment9);
            segment9 = null;
        }

        if  (segment10 != null) {
            game.remove(segment10);
            segment10 = null;
        }

        if  (segment11 != null) {
            game.remove(segment11);
            segment11 = null;
        }

        if  (segment12 != null) {
            game.remove(segment12);
            segment12 = null;
        }

        if  (segment13 != null) {
            game.remove(segment13);
            segment13 = null;
        }

        if  (segment14 != null) {
            game.remove(segment14);
            segment14 = null;
        }

        if  (segment15 != null) {
            game.remove(segment15);
            segment15 = null;
        }

        if  (segment16 != null) {
            game.remove(segment16);
            segment16 = null;
        }
    }


    public boolean isHitBottom(double height) {
        return getY() + radius >= height;
    }

    public void bounceY() {
        velocityY = -velocityY;
    }

    public void bounceX() {
        velocityX = -velocityX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getRadius() {
        return radius;
    }
}
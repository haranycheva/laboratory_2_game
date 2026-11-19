import acm.graphics.*;
import java.awt.Color;

public class Ball extends GCompound {
    private double velocityX = 3.0;
    private double velocityY = -4.0;
    private double radius = Breakout.BALL_RADIUS;
    private GOval oval;

    public Ball(double x, double y) {
        double diameter = radius * 2.0;
        oval = new GOval(diameter, diameter);
        oval.setFilled(true);
        oval.setColor(Color.BLACK);
        oval.setFillColor(Color.BLACK);

        add(oval, -radius, -radius);
        setLocation(x, y);
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
            setLocation(x, radius);
            bounceY();
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
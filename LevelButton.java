import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

public class LevelButton {
    private Breakout game;

    public LevelButton(Breakout game, double x, double y, String text, Color fillColor) {
        this.game = game;
        double width = 200;
        double height = 55;
        button = new GRect(x, y, width, height);
        button.setFilled(true);
        button.setFillColor(fillColor);
        button.setColor(new Color(0x5F758E));

        label = new GLabel(text);
        label.setFont(new Font("Minecraft", Font.PLAIN, 28));
        label.setColor(new Color(0xDBDFAC));

        double labelX = x + (width - label.getWidth()) / 2;
        double labelY = y + (height + label.getAscent()) / 2;

        label.setLocation(labelX, labelY);

        game.addToBoard(button);
        game.addToBoard(label);
    }

    public void addToBoard() {
        game.addToBoard(button);
        game.addToBoard(label);
    }

    public boolean contains(double x, double y) {
        return button.contains(x, y);
    }

    private GRect button;
    private GLabel label;
}

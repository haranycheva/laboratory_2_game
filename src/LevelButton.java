import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

/** Кнопка вибору рівня з написом. */
public class LevelButton {
    private Breakout game;

    /**
     * Створює кнопку з написом по центру.
     * @param game гра, на полі якої малюється кнопка
     * @param x координата X лівого верхнього кута
     * @param y координата Y лівого верхнього кута
     * @param text напис на кнопці
     * @param fillColor колір заливки
     */
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

    /** Додає кнопку і напис на поле. */
    public void addToBoard() {
        game.addToBoard(button);
        game.addToBoard(label);
    }

    /**
     * Перевіряє, чи точка знаходиться всередині кнопки.
     * @param x координата X точки
     * @param y координата Y точки
     * @return true, якщо точка в межах кнопки
     */
    public boolean contains(double x, double y) {
        return button.contains(x, y);
    }

    private GRect button;
    private GLabel label;
}
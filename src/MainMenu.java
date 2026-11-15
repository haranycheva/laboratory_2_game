import acm.graphics.GRect;

public class MainMenu {
    private Breakout game;

    public MainMenu(Breakout game) {
        this.game = game;
    }

    private void createTopBricks() {
        int width = (Breakout.APPLICATION_WIDTH - 20 - 3 * 4) / 4;
        GRect mainBrick = new GRect(10, 100, 0, 0);
    }
}

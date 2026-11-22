public class Level2 extends Level1 {
    public Level2(Breakout game) {
        super(game);
    }

    @Override
    protected void configureBall(Ball newBall) {
        newBall.setVelocityX(4.5);
        newBall.setVelocityY(-5.5);
    }
}

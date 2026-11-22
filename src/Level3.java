public class Level3 extends Level1 {
    public Level3(Breakout game) {
        super(game);
    }

    @Override
    protected void configureBall(Ball newBall) {
        newBall.setVelocityX(7.5);
        newBall.setVelocityY(-8.5);
    }
}

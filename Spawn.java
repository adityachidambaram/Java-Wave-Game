import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random  r = new Random();
    
    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }
    public void tick() {
        if(Player.scoreCount == 1) {
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));
            handler.addObject(new Coin((int)(Math.random()*Game.WIDTH - 100) + 100, (int)(Math.random()*Game.HEIGHT - 100) + 100, ID.Coin));
            Player.scoreCount = 0;
        }
    }
}
